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
package de.gishmo.gwt.sema4g.example.client.cases;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import de.gishmo.gwt.sema4g.client.SeMa4g;
import de.gishmo.gwt.sema4g.client.command.SeMa4gCommand;
import de.gishmo.gwt.sema4g.client.exception.SeMa4gException;
import de.gishmo.gwt.sema4g.example.client.cases.code.Example;

@Example
public class Case04
  extends AbstractCase {

  public Case04(FlowPanel fp,
                PopupPanel popup) {
    super(fp,
          popup);

    buttonText = "Start Foue";
    descriptionText = "Several service calls with different duration on the server. With cycle dependencies, exception expected. No service will be called.";
    labelText = "Test Case 04";
    startText = "Execution for test case four started";
    successText = "Execution for case four finished";
    errorText = "Execution has detected a cycle dependency";
  }

  public void createContextAndRun() {
    SeMa4gCommand command01 = this.createAsyncCommandRPC(9250,
                                                         "one");
    SeMa4gCommand command02 = this.createAsyncCommandRPC(3255,
                                                         "two");
    SeMa4gCommand command03 = this.createAsyncCommandRPC(125,
                                                         "three");
    SeMa4gCommand command04 = this.createAsyncCommandRPC(5200,
                                                         "four");
    SeMa4gCommand command05 = this.createAsyncCommandRPC(250,
                                                         "five");
    SeMa4gCommand command06 = this.createAsyncCommandRPC(6000,
                                                         "six");
    SeMa4gCommand command07 = this.createAsyncCommandRPC(7250,
                                                         "seven");
    SeMa4gCommand command08 = this.createAsyncCommandRPC(2400,
                                                         "eight");
    SeMa4gCommand command09 = this.createAsyncCommandRPC(5100,
                                                         "nine");
    SeMa4gCommand command10 = this.createAsyncCommandRPC(200,
                                                         "ten");

    try {
      SeMa4g.builder()
            .addInitCommand(super.createInitCommand())
            .addFinalCommand(super.createFinalCommand())
            .add(command02.dependingOn(command06))
            .add(command06.dependingOn(command04,
                                    command07,
                                    command09))
            .add(command07)
            .add(command01)
            .add(command04.dependingOn(command02))
            .add(command08)
            .add(command09)
            .add(command03.dependingOn(command08,
                                    command05))
            .add(command10)
            .add(command05)

            .build()
            .run();
    } catch (SeMa4gException e) {
      fp.add(new Label("SeMa4g has detected a cycle dependency. A exception is thrown and the context is not started!"));
    }
  }
}
