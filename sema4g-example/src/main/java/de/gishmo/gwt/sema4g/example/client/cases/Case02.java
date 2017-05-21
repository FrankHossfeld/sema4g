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
import de.gishmo.gwt.sema4g.client.SeMa4g;
import de.gishmo.gwt.sema4g.client.exception.SeMa4gException;
import de.gishmo.gwt.sema4g.example.client.cases.code.Example;

@Example
public class Case02
  extends AbstractCase {

  public Case02(FlowPanel fp,
                PopupPanel popup) {
    super(fp,
          popup);

    buttonText = "Start Two";
    descriptionText = "Several service calls with no dependencies and a InitCommand and a FinishCommand.";
    labelText = "Test Case 02";
    startText = "Execution for test case two started";
    successText = "Execution for case two finished";
    errorText = "Execution for case two failed";
  }

  public void createContextAndRun() {
    try {
      SeMa4g.builder()
            .addInitCommand(super.createInitCommand())
            .addFinalCommand(super.createFinalCommand())
            .add(this.createAsyncCommandRPC(2500,
                                            "one"))
            .add(this.createAsyncCommandRPC(125,
                                            "two"))
            .add(this.createAsyncCommandRPC(12500,
                                            "three"))
            .add(this.createAsyncCommandRPC(8750,
                                            "four"))
            .add(this.createAsyncCommandRPC(125,
                                            "five"))
            .build()
            .run();
    } catch (SeMa4gException e) {
      Window.alert("Panic!");
    }
  }
}
