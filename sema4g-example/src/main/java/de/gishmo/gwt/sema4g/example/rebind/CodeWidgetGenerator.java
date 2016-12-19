/*
 * Copyright 2015 Frank Hossfeld
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


import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.IncrementalGenerator;
import com.google.gwt.core.ext.RebindResult;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

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
   * The generator context.
   */
  private GeneratorContext context = null;

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

//    this.logger = treeLogger;
//    this.context = generatorContext;
//    this.classLoader = Thread.currentThread().getContextClassLoader();
//
//    this.packageName = CodeWidget.class.getPackage()
//                                         .getName();
//    this.generatedClassName = CodeWidget.class.getSimpleName() + "Impl";
////    // Only generate files on the first permutation
////    if (!isFirstPass()) {
////      return null;
////    }
//
//    List<JClassType> wcsTypes = new ArrayList<JClassType>();
//    // Get the CodeWidget subtypes to examine
//    for (JPackage pack : this.context.getTypeOracle().getPackages()) {
//      for (JClassType type : pack.getTypes()) {
////        if (type.getAnnotation(AbstractCodeWidget.WidgetCodeSource.class) != null) {
////          Annotation t = type.getAnnotation(AbstractCodeWidget.WidgetCodeSource.class);
////          wcsTypes.add(type);
////        }
//      }
//    }
//    if (wcsTypes.size() == 0) {
//      this.logger.log(TreeLogger.ERROR,
//                      "Cannot find ExampleCases classes",
//                      null);
//      throw new UnableToCompleteException();
//    }
//    return generateClass(treeLogger,
//                         generatorContext,
//                         wcsTypes);
    return null;
  }

  @Override
  public long getVersionId() {
    return CodeWidgetGenerator.GENERATOR_VERSION_ID;
  }

//  private RebindResult generateClass(TreeLogger treeLogger,
//                                     GeneratorContext generatorContext,
//                                     List<JClassType> types)
//      throws UnableToCompleteException {
//
//    Date start = new Date();
//
//    // Log
//    this.logger.log(TreeLogger.INFO,
//                    "Start generating file ... ",
//                    null);
//
//    // No, there is non. Create a new one.
//    SourceWriter sourceWriter = getSourceWriter(generatorContext,
//                                                packageName,
//                                                generatedClassName);
//
//    if (sourceWriter != null) {
//      this.logger.log(TreeLogger.INFO,
//                      "Generating source for " + packageName + "." + generatedClassName + " ",
//                      null);
//      writeSource(treeLogger,
//                  sourceWriter, types);
//      sourceWriter.commit(this.logger);
//    } else {
//      // don't expect this to occur, but could happen if an instance was
//      // recently generated but not yet committed
//      new RebindResult(RebindMode.USE_EXISTING,
//                       generatedClassName);
//    }
//
//    Date end = new Date();
//
//    this.logger.log(TreeLogger.INFO,
//                    "Compilation finished in: " + (end.getTime() - start.getTime()) + "ms.");
//
//    return new RebindResult(RebindMode.USE_ALL_NEW_WITH_NO_CACHING,
//                            packageName + "." + generatedClassName);
//  }
//
//  private SourceWriter getSourceWriter(GeneratorContext context,
//                                       String packageName,
//                                       String generatedClassName)
//      throws UnableToCompleteException {
//
//    logger.log(TreeLogger.INFO,
//               "Generating class: " + packageName + "." + generatedClassName,
//               null);
//
//    PrintWriter printWriter = context.tryCreate(logger,
//                                                packageName,
//                                                generatedClassName);
//    if (printWriter == null) {
//      return null;
//    }
//
//    ClassSourceFileComposerFactory classFactory = new ClassSourceFileComposerFactory(packageName,
//                                                                                     generatedClassName);
//
//    classFactory.setSuperclass(BaseCodeWidget.class.getName());
//    classFactory.addImport(Label.class.getName());
//    classFactory.addImport(FlowPanel.class.getName());
//    classFactory.addImport(Widget.class.getName());
//
////
////    classFactory.addImplementedInterface(originalType.getName());
////    String[] classesToImport = getClassesToImport();
////    for (String classToImport : classesToImport) {
////      classFactory.addImport(classToImport);
////    }
//
//    return classFactory.createSourceWriter(context,
//                                           printWriter);
//  }
//
//  private void writeSource(TreeLogger treeLogger,
//                           SourceWriter sourceWriter,
//                           List<JClassType> types)
//      throws UnableToCompleteException {
//
//    sourceWriter.println();
//    sourceWriter.println("public void initialize() {");
//    sourceWriter.indent();
//    for (JClassType type : types) {
//      sourceWriter.println("code.put(\"" + type.getName() + "\", create" + type.getName() + "();");
//    }
//    sourceWriter.outdent();
//    sourceWriter.println("}");
//    for (JClassType type : types) {
//      writeCreateMethod(treeLogger,
//                        sourceWriter,
//                        type);
//    }
////    sourceWriter.outdent();
//    writeCreateLabelMethod(sourceWriter);
//  }
//
//  private void writeCreateMethod(TreeLogger treeLogger,
//                                 SourceWriter sourceWriter,
//                                 JClassType type)
//      throws UnableToCompleteException {
//    sourceWriter.println();
//    sourceWriter.println("private Widget create" + type.getName() + "() {");
//    sourceWriter.indent();
//    sourceWriter.println("FlowPanel fp = new FlowPanel();");
//    sourceWriter.println();
//    String methodSource = this.getSourceFile(treeLogger, type);
//
//    sourceWriter.println();
//    sourceWriter.println("return fp;");
//    sourceWriter.outdent();
//    sourceWriter.println( "}" );
////    sourceWriter.outdent();
//  }
//
//  private void writeCreateLabelMethod(SourceWriter sourceWriter) {
//    sourceWriter.println();
//    sourceWriter.println("private Label createLabel(String lineOfCode) {");
//    sourceWriter.indent();
//    sourceWriter.println("Label label = new Label(\"lineOfCode\");");
//    sourceWriter.println("return label;");
//    sourceWriter.outdent();
//    sourceWriter.println( "}" );
//  }
//
//  private String getSourceFile(TreeLogger treeLogger,
//                               JClassType type)
//      throws UnableToCompleteException {
//    String source = "";
//
//    String filename = type.getQualifiedSourceName().replace('.', '/') + ".java";
//    String fileContents = getResourceContents(treeLogger, filename);
//
//
//    return source;
//  }
//
//  /**
//   * Get the full contents of a resource.
//   *
//   * @param path the path to the resource
//   * @return the contents of the resource
//   */
//  private String getResourceContents(TreeLogger treeLogger,
//                                     String path)
//      throws UnableToCompleteException {
//
////
////    // Build a ResourceOracle capable of reading java files
////    ResourceOracleImpl sourceOracle = new ResourceOracleImpl(logger.branch(TreeLogger.Type.DEBUG, "Gathering sources"));
//////    Set<String> prefixes = context.getResourcesOracle().getPathNames();
////    PathPrefixSet prefixes = ((ResourceOracleImpl) context.getResourcesOracle()).getPathPrefixes();
////    sourceOracle.setPathPrefixes(new PathPrefixSet());
////    for (PathPrefix p : prefixes.values()) {
////      sourceOracle.getPathPrefixes().add(new PathPrefix(p.getPrefix(), null));
////    }
//////    ResourceOracleImpl.refresh(logger, sourceOracle);
//
//    try {
//      String sourceCode = Utility.getFileFromClassPath(path);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//
//    InputStream in = classLoader.getResourceAsStream(path);
//    if (in == null) {
//      logger.log(TreeLogger.ERROR, "Resource not found: " + path);
//      throw new UnableToCompleteException();
//    }
//
//    StringBuffer fileContentsBuf = new StringBuffer();
//    BufferedReader br = null;
//    try {
//      br = new BufferedReader(new InputStreamReader(in));
//      String temp;
//      while ((temp = br.readLine()) != null) {
//        fileContentsBuf.append(temp).append('\n');
//      }
//    } catch (IOException e) {
//      logger.log(TreeLogger.ERROR, "Cannot read resource", e);
//      throw new UnableToCompleteException();
//    } finally {
//      if (br != null) {
//        try {
//          br.close();
//        } catch (IOException e) {
//        }
//      }
//    }
//    // Return the file contents as a string
//    return fileContentsBuf.toString();
//  }
//
////	/**
////	 * Generate the formatted source code for a {@link ContentWidget}.
//// 	 *
//// 	 * @param type the {@link ContentWidget} subclass
//// 	 */
////	private void generateSourceFiles(JClassType type)
////		throws UnableToCompleteException {
////		// Get the file contents
////		String filename = type.getQualifiedSourceName().replace('.', '/') + ".java";
//////		System.out.println("Verarbeite: " + filename);
////		String fileContents = getResourceContents(filename);
////
////		// Get each data code block
////		String formattedSource = "";
////		String dataTag = "@ShowcaseData";
////		String sourceTag = "@ShowcaseSource";
////		int dataTagIndex = fileContents.indexOf(dataTag);
////		int srcTagIndex = fileContents.indexOf(sourceTag);
////		while (dataTagIndex >= 0 || srcTagIndex >= 0) {
////			if (dataTagIndex >= 0
////					&& (dataTagIndex < srcTagIndex || srcTagIndex < 0)) {
////				// Get the boundaries of a DATA tag
////				int beginIndex = fileContents.lastIndexOf("  /*", dataTagIndex);
////				if (beginIndex == -1) {
////					System.out.print("Index-Problem!");
////				}
////				int beginTagIndex = fileContents.lastIndexOf("\n", dataTagIndex) + 1;
////				if (beginTagIndex == -1) {
////					System.out.print("Index-Problem!");
////				}
////				int endTagIndex = fileContents.indexOf("\n", dataTagIndex) + 1;
////				if (endTagIndex == -1) {
////					System.out.print("Index-Problem!");
////				}
////				int endIndex = fileContents.indexOf(";", beginIndex) + 1;
////				if (endIndex == -1) {
////					System.out.print("Index-Problem!");
////				}
////
////				// Add to the formatted source
////				if (endTagIndex > endIndex) {
////					System.out.print("Index-Problem!");
////				}
////				if (beginIndex > beginTagIndex) {
////					System.out.print("Index-Problem!");
////					System.out.print(fileContents.substring(beginTagIndex));
////				}
////				String srcData = fileContents.substring(beginIndex, beginTagIndex)
////				+ fileContents.substring(endTagIndex, endIndex);
////				formattedSource += srcData + "\n\n\n\n";
////
////				// Get next tag
////				dataTagIndex = fileContents.indexOf(dataTag, endIndex + 1);
////			} else {
////				// Get the boundaries of a SRC tag
////				int beginIndex = fileContents.lastIndexOf("/*", srcTagIndex) - 2;
////				int beginTagIndex = fileContents.lastIndexOf("\n", srcTagIndex) + 1;
////				int endTagIndex = fileContents.indexOf("\n", srcTagIndex) + 1;
////				int endIndex = fileContents.indexOf("\n  }", beginIndex) + 4;
//////				System.out.println("fileContents: " + fileContents);
//////				System.out.println("begionIndex: " + beginIndex + " - beginTagIndex: "+ beginTagIndex + " - endTagIndex: " + endTagIndex + " - endIndex: " + endIndex);
////
////				// Add to the formatted source
////				String srcCode = "";
////					srcCode = fileContents.substring(beginIndex, beginTagIndex)
////					+ fileContents.substring(endTagIndex, endIndex);
////
////				formattedSource += srcCode + "\n\n\n\n";
////
////				// Get the next tag
////				srcTagIndex = fileContents.indexOf(sourceTag, endIndex + 1);
////			}
////		}
////			formattedSource = formattedSource.substring(0, formattedSource.lastIndexOf("\n\n\n\n"));
////
////		// Make the source pretty
////		formattedSource = formattedSource.replace("<", "&lt;");
////		formattedSource = formattedSource.replace(">", "&gt;");
////		formattedSource = formattedSource.replace("* \n   */\n", "*/\n");
////		formattedSource = "<pre>" + formattedSource + "</pre>";
////
////		// Save the source code to a file
////		String dstPath = Constants.DESTINATION_SOURCE_EXAMPLE + type.getSimpleSourceName() + ".html";
////		createPublicResource(dstPath, formattedSource);
////	}
////
//////------------------------------------------------------------------------------
////
////	/**
////	 * Set the full contents of a resource in the public directory.
////	 *
////	 * @param partialPath the path to the file relative to the public directory
////	 * @param contents the file contents
////	 */
////	private void createPublicResource(String partialPath, String contents)
////		throws UnableToCompleteException {
////		try {
////			OutputStream outStream = context.tryCreateResource(logger, partialPath);
////			if (outStream == null) {
////				String message = "Attempting to generate duplicate public resource: " +
////								 partialPath +
////								 ".\nAll generated source files must have unique names.";
////				logger.log(TreeLogger.ERROR, message);
////				throw new UnableToCompleteException();
////			}
////			outStream.write(contents.getBytes());
////			context.commitResource(logger, outStream);
////		} catch (IOException e) {
////			logger.log(TreeLogger.ERROR, "Error writing file: " + partialPath, e);
////			throw new UnableToCompleteException();
////		}
////	}
////
////	/**
////	 * Get the full contents of a resource.
////	 *
////	 * @param path the path to the resource
////	 * @return the contents of the resource
////	 */
////	private String getResourceContents(String path)
////		throws UnableToCompleteException {
////		InputStream in = classLoader.getResourceAsStream(path);
////		if (in == null) {
////			logger.log(TreeLogger.ERROR, "Resource not found: " + path);
////			throw new UnableToCompleteException();
////		}
////
////		StringBuffer fileContentsBuf = new StringBuffer();
////		BufferedReader br = null;
////		try {
////			br = new BufferedReader(new InputStreamReader(in));
////			String temp;
////			while ((temp = br.readLine()) != null) {
////				fileContentsBuf.append(temp).append('\n');
////			}
////		} catch (IOException e) {
////			logger.log(TreeLogger.ERROR, "Cannot read resource", e);
////			throw new UnableToCompleteException();
////		} finally {
////			if (br != null) {
////				try {
////					br.close();
////				} catch (IOException e) {
////				}
////			}
////		}
////
////		// Return the file contents as a string
////		return fileContentsBuf.toString();
////	}
////
////	/**
////	 * Ensure that we only generate files once by creating a placeholder file,
////	 * then looking for it on subsequent generates.
////	 *
////	 * @return true if this is the first pass, false if not
////	 */
////	private boolean isFirstPass() {
////		String placeholder = Constants.DESTINATION_SOURCE + "generated";
////		try {
////			OutputStream outStream = context.tryCreateResource(logger, placeholder);
////			if (outStream == null) {
////				return false;
////			} else {
////				context.commitResource(logger, outStream);
////			}
////		} catch (UnableToCompleteException e) {
////			logger.log(TreeLogger.ERROR, "Unable to generate", e);
////			return false;
////		}
////		return true;
////	}
}
