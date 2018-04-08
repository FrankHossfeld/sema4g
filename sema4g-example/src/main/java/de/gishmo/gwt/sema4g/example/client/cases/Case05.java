/*
 * Copyright (c) 2017 - 2018 - Frank Hossfeld
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
import com.github.mvp4g.sema4g.client.command.FinalCommand;
import com.github.mvp4g.sema4g.client.command.SeMa4gCommand;
import com.github.mvp4g.sema4g.client.exception.SeMa4gException;
import de.gishmo.gwt.sema4g.example.client.cases.code.Example;

@Example
public class Case05
  extends AbstractCase {

  public Case05(FlowPanel fp,
                PopupPanel popup) {
    super(fp,
          popup);

    descriptionText = "A single service call (service no. 13) which will fail and throw an exception." +
                      "<ul>" +
                      "<li>service 'one': the waiting duration on the server is: 2500 ms.</li>" +
                      "</ul>" +
                      "The context will end in error because of a server exception!" +
                      AbstractCase.SERVICE_FAILURE_DESCRIPTION +
                      AbstractCase.INITCOMMAND_DESCRIPTION +
                      AbstractCase.FINALCOMMAND_DESCRIPTION;
    labelText = "Test Case 05";
    startText = "Execution for test case five started";
    successText = "Execution for case five finished";
    errorText = "Execution for case five failed";
  }

  public void createContextAndRun() {
    SeMa4gCommand command = this.createAsyncFailingCommandRPC(9250,
                                                              "thirteen");

    try {
      SeMa4g.builder()
            .addInitCommand(super.createInitCommand())
            .addFinalCommand(new FinalCommand() {
              @Override
              public void onSuccess() {
                popup.hide();
                Window.alert("Ups, seeing this means ... something is wrong!");
              }
              @Override
              public void onFailure() {
                popup.hide();
                Window.alert("Fine, that's what we expected ... ");
              }
            })
            .add(command)
            .build()
            .run();
    } catch (SeMa4gException e) {
      Window.alert("panic!!!!");
    }
  }
}
