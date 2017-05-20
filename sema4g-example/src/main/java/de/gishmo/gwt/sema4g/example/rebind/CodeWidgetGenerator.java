/*
 * Copyright 2015-2017 Frank Hossfeld
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.gishmo.gwt.sema4g.example.rebind;


import com.google.gwt.core.ext.*;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.util.tools.Utility;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import de.gishmo.gwt.sema4g.example.client.cases.code.BaseCodeWidget;
import de.gishmo.gwt.sema4g.example.client.cases.code.CodeWidget;
import de.gishmo.gwt.sema4g.example.client.cases.code.Example;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CodeWidgetGenerator
  extends IncrementalGenerator {

  /*
   * A version id. Increment this as needed, when structural changes are made to
   * the generated output, specifically with respect to it's effect on the
   * caching and reuse of previous generator results. Previously cached
   * generator results will be invalidated automatically if they were generated
   * by a version of this generator with a different version id.
   */
  private static final long GENERATOR_VERSION_ID = 1L;

  /**
   * The class loader used to get resources.
   */
  private ClassLoader classLoader = null;

  /**
   * The {@link TreeLogger} used to log messages.
   */
  private TreeLogger logger = null;


  private String packageName;
  private String generatedClassName;

  @Override
  public RebindResult generateIncrementally(TreeLogger treeLogger,
                                            GeneratorContext generatorContext,
                                            String s)
    throws UnableToCompleteException {

    this.logger = treeLogger;

    this.classLoader = Thread.currentThread()
                             .getContextClassLoader();

    this.packageName = CodeWidget.class.getPackage()
                                       .getName();
    this.generatedClassName = CodeWidget.class.getSimpleName() + "Impl";

    // PrintWriter erzeugen
    PrintWriter printWriter = generatorContext.tryCreate(logger,
                                                         packageName,
                                                         generatedClassName);
    if (printWriter == null) {
      // generation already done, use already generated classes
      logger.log(TreeLogger.INFO,
                 "CodeWidgetGenerator: reuse already generated classes",
                 null);
      // don't expect this to occur, but could happen if an instance was
      // recently generated but not yet committed
      new RebindResult(RebindMode.USE_EXISTING,
                       generatedClassName);
    }

    List<JClassType> wcsTypes = new ArrayList<>();
    // Get the CodeWidget subtypes to examine
    for (JPackage pack : generatorContext.getTypeOracle()
                                         .getPackages()) {
      for (JClassType type : pack.getTypes()) {
        if (type.getAnnotation(Example.class) != null) {
          wcsTypes.add(type);
        }
      }
    }
    if (wcsTypes.size() == 0) {
      this.logger.log(TreeLogger.ERROR,
                      "Cannot find examples classes",
                      null);
      throw new UnableToCompleteException();
    }
    return generateClass(treeLogger,
                         generatorContext,
                         printWriter,
                         wcsTypes);
  }

  @Override
  public long getVersionId() {
    return CodeWidgetGenerator.GENERATOR_VERSION_ID;
  }

  private RebindResult generateClass(TreeLogger treeLogger,
                                     GeneratorContext generatorContext,
                                     PrintWriter printWriter,
                                     List<JClassType> types)
    throws UnableToCompleteException {

    Date start = new Date();

    // Log
    this.logger.log(TreeLogger.INFO,
                    "Start generating file ... ",
                    null);


    TypeSpec.Builder typeSpec = TypeSpec.classBuilder(this.generatedClassName)
                                        .superclass(ClassName.get(BaseCodeWidget.class))
                                        .addModifiers(Modifier.PUBLIC);

    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                                               .addModifiers(Modifier.PUBLIC)
                                               .addStatement("super()");
    typeSpec.addMethod(constructor.build());

    MethodSpec.Builder initializeMethod = MethodSpec.methodBuilder("initialize")
                                                    .addAnnotation(Override.class)
                                                    .addModifiers(Modifier.PUBLIC)
                                                    .returns(void.class);

    for (JClassType type : types) {
      initializeMethod.addStatement("create$L()",
                                    type.getName());
    }
    typeSpec.addMethod(initializeMethod.build());


    for (JClassType type : types) {
      MethodSpec.Builder createMethod = MethodSpec.methodBuilder("create" + type.getName())
                                                  .addModifiers(Modifier.PRIVATE)
                                                  .returns(void.class);
      List<String> sourceCoudeLines = getSourceCodeLines(logger,
                                                         type);
      createMethod.addStatement("$T fp = new $T()", FlowPanel.class, FlowPanel.class);
      for (String codeLine : sourceCoudeLines) {
        createMethod.addStatement("fp.add(super.createLabel($S))", codeLine);
      }
      createMethod.addStatement("code.put($S, fp)", type.getName());
      typeSpec.addMethod(createMethod.build());
    }

    JavaFile javaFile = JavaFile.builder(packageName,
                                         typeSpec.build())
                                .build();

//    System.out.println(javaFile.toString());

    printWriter.print(javaFile.toString());
    printWriter.flush();
    printWriter.close();

    generatorContext.commit(logger,
                            printWriter);

    Date end = new Date();

    this.logger.log(TreeLogger.INFO,
                    "Compilation finished in: " + (end.getTime() - start.getTime()) + "ms.");

    return new RebindResult(RebindMode.USE_ALL_NEW_WITH_NO_CACHING,
                            packageName + "." + generatedClassName);
  }

  private List<String> getSourceCodeLines(TreeLogger logger,
                                          JClassType type)
    throws UnableToCompleteException {
    String filename = type.getQualifiedSourceName()
                          .replace('.',
                                   '/') + ".java";
    List<String> linesOdCode;
    List<String> methodCodeLines = new ArrayList<>();
    try {
      String sourceCode = Utility.getFileFromClassPath(filename);
      linesOdCode = Pattern.compile(System.lineSeparator(),
                                    Pattern.LITERAL)
                           .splitAsStream(sourceCode)
                           .collect(Collectors.toList());
      boolean methodCode = false;
      for (String s : linesOdCode) {
        if (s.contains(  "public void createContextAndRun() {")) {
          methodCode = true;
        }
        if (methodCode) {
          methodCodeLines.add(s.replace(" ", "&nbsp;"));
        }
        if (s.startsWith("  }")) {
          methodCode = false;
        }
      }
    } catch (IOException e) {
      logger.log(TreeLogger.Type.ERROR,
                 e.getMessage());
      throw new UnableToCompleteException();
    }
    return methodCodeLines;
  }
}
