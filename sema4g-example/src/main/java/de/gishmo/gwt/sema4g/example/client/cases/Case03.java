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

public class Case03
  extends AbstractCase
  implements IsExampleCase {

  public Case03(FlowPanel fp,
                PopupPanel popup) {
//    super(fp,
//          popup);
//
//    buttonText = "Start Two";
//    descriptionText = "Several service calls with a InitCommand and a FinishCommand and dependencies.";
//    labelText = "Test Case 03";
//    startText = "Execution for test case three started";
//    successText = "Execution for case three finished";
//    errorText = "Execution for case three failed";
  }

  @SourceCode
  protected void createContext() {
//    Command command01 = this.createAsyncCommandRPC(9250,
//                                                         "one");
//    Command command02 = this.createAsyncCommandRPC(3255,
//                                                         "two");
//    Command command03 = this.createAsyncCommandRPC(125,
//                                                         "three");
//    Command command04 = this.createAsyncCommandRPC(5200,
//                                                         "four");
//    Command command05 = this.createAsyncCommandRPC(250,
//                                                         "five");
//    Command command06 = this.createAsyncCommandRPC(6000,
//                                                         "six");
//    Command command07 = this.createAsyncCommandRPC(7250,
//                                                         "seven");
//    Command command08 = this.createAsyncCommandRPC(2400,
//                                                         "eight");
//    Command command09 = this.createAsyncCommandRPC(5100,
//                                                         "nine");
//    Command command10 = this.createAsyncCommandRPC(200,
//                                                         "ten");
//
//    try {
//      context.addInit(this.initCommand())
//             .add(command01.dependingOn(command03,
//                                        command10))
//             .add(command02)
//             .add(command03.dependingOn(command05))
//             .add(command04.dependingOn(command05))
//             .add(command05.dependingOn(command08,
//                                        command09))
//             .add(command06)
//             .add(command07)
//             .add(command08)
//             .add(command09)
//             .add(command10)
//             .addFinal(this.finalCommand());
//    } catch (SeMa4gException e) {
//      Window.alert(e.getMessage());
//    }
  }
}
