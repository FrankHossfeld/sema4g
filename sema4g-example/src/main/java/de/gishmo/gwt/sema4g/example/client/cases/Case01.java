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

package de.gishmo.gwt.sema4g.example.client.cases;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;

import de.gishmo.gwt.sema4g.example.client.cases.code.IsExampleCase;
import de.gishmo.gwt.sema4g.example.client.cases.code.SourceCode;

public class Case01
    extends AbstractCase
  implements IsExampleCase {

  public Case01(FlowPanel fp,
                PopupPanel popup) {
//    super(fp,
//          popup);
//
//    buttonText = "Start One";
//    descriptionText = "A single service call with a InitCommand and a FinishCommand.";
//    labelText = "Test Case 01";
//    startText = "Execution for test case one started";
//    successText = "Execution for case one finished";
//    errorText = "Execution for case one failed";
  }

  @SourceCode
  protected void createContext() {
//    context.addInit(this.initCommand())
//           .add(this.createAsyncCommandRPC(2500,
//                                           "one"))
//           .addFinal(this.finalCommand());
  }
}
