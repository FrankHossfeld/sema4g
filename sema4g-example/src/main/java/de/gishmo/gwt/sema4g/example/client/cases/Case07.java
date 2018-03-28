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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.github.mvp4g.sema4g.client.SeMa4g;
import com.github.mvp4g.sema4g.client.command.SeMa4gCommand;
import com.github.mvp4g.sema4g.client.exception.SeMa4gException;
import de.gishmo.gwt.sema4g.example.client.cases.code.Example;

@Example
public class Case07
  extends AbstractCase {

  public Case07(FlowPanel fp,
                PopupPanel popup) {
    super(fp,
          popup);

    descriptionText = "Several asynchronous and one synchronous service calls with a InitCommand and a FinishCommand and dependencies." +
                      "<ul>" +
                      "<li>service 'one': the waiting duration on the server is: 9250 ms.</li>" +
                      "<li>service 'two': the waiting duration on the server is: 12000 ms.<br><b>This is a synchronous command</b></li>" +
                      "<li>service 'three': the waiting duration on the server is: 125 ms. The service depends on the execution of service 'two'.</li>" +
                      "</ul>" +
                      "The context will successfully end!" +
                      AbstractCase.SERVICE_DESCRIPTION +
                      AbstractCase.SERVICE_SYNCHRONOUS_DESCRIPTION +
                      AbstractCase.INITCOMMAND_DESCRIPTION +
                      AbstractCase.FINALCOMMAND_DESCRIPTION;
    labelText = "Test Case 07";
    startText = "Execution for test case seven started";
    successText = "Execution for case seven finished";
    errorText = "Execution for case seven failed";
  }

  public void createContextAndRun() {
    SeMa4gCommand command01 = this.createAsyncCommandRPC(9250,
                                                         "one");
    SeMa4gCommand command02 = this.createSyncCommand("two");
    SeMa4gCommand command03 = this.createAsyncCommandRPC(125,
                                                         "three");

    try {
      SeMa4g.builder()
            .addInitCommand(super.createInitCommand())
            .addFinalCommand(super.createFinalCommand())
            .add(command01)
            .add(command02)
            .add(command03.dependingOn(command02))
            .build()
            .run();
    } catch (SeMa4gException e) {
      Window.alert("Panic!");
    }
  }
}
