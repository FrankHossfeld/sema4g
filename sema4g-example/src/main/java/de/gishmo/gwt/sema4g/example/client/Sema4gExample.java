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

package de.gishmo.gwt.sema4g.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sema4gExample
  implements EntryPoint {

  static {
    CssResources.CSS.exampleStyle()
                    .ensureInjected();
  }

  private ExampleStyle    style           = CssResources.CSS.exampleStyle();
  //  //  private CodeWidget         codeWidgets     = GWT.create(CodeWidget.class);
  private DockLayoutPanel root            = new DockLayoutPanel(Style.Unit.PX);
  private FlowPanel       fpSourceListing = new FlowPanel();
  private FlowPanel       fpDescription   = new FlowPanel();
  private FlowPanel       fpResult        = new FlowPanel();
  private PopupPanel      popup           = new PopupPanel();
  private Button          runButton       = new Button("Run");
  private Button          resetButton     = new Button("Reset");
//  private ListBox            lbCases         = new ListBox(false);
//  private List<AbstractCase> cases           = new ArrayList<>();
//  private AbstractCase       selectedCase;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
//    this.createListOfCases();
    this.createPopUp();
    this.bind();
//
//    this.setUpCase();

    root.setSize("100%",
                 "100%");
    root.addNorth(this.createNorthWidget(),
                  66);
    root.add(this.createCenter());
    RootLayoutPanel.get()
                   .add(root);
  }

  private void createPopUp() {
    this.popup.setSize("150px",
                       "42px");
    this.popup.setModal(true);

    FlowPanel fp = new FlowPanel();
    fp.addStyleName(style.popup());

    Label label = new Label("in progress ...");
    label.addStyleName(style.popupLabel());
    fp.add(label);

    this.popup.add(fp);
  }

  private void bind() {
    // bind button
    resetButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
//        selectedCase.getContext()
//                    .reset();
//        clear();
//        setUpCase();
      }
    });

    runButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
//        if (selectedCase != null) {
//          try {
//            selectedCase.getContext()
//                        .run();
//          } catch (SeMa4gException e) {
//            Window.alert("Ups, Panic!!!!!!\n\nmessage ==> " + e.getMessage());
//          }
//        }
      }
    });

//    lbCases.addChangeHandler(new ChangeHandler() {
//      @Override
//      public void onChange(ChangeEvent event) {
//        clear();
//        selectedCase = cases.get(lbCases.getSelectedIndex());
//        setUpCase();
//      }
//    });
  }

//  private void createListOfCases() {
//    Case01 case01 = new Case01(fpResult,
//                               popup);
//    cases.add(case01);
//    lbCases.addItem(case01.getLabelText());
//
//
//    Case02 case02 = new Case02(fpResult,
//                               popup);
//    cases.add(case02);
//    lbCases.addItem(case02.getLabelText());
//
//
//    Case03 case03 = new Case03(fpResult,
//                               popup);
//    cases.add(case03);
//    lbCases.addItem(case03.getLabelText());
//
//
//    lbCases.setSelectedIndex(0);
//    selectedCase = cases.get(0);
//  }
//
//  private void setUpCase() {
//    if (selectedCase != null) {
//      fpDescription.add(createLabel(selectedCase.getDescriptionText()));
////      fpSourceListing.add(codeWidgets.getWidget(selectedCase.getClass()
////                                                            .getSimpleName()));
//    }
//  }
//
//
//////  private void createTest01() {
//////    this.createColumn(this.fpOutput01,
//////                      "Start One",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput01.clear();
//////                          fpOutput01.add(createLabel("Execution for button one started"));
//////                          setUpButton01(duration);
//////                        }
//////                      },
//////                      "A single service call.");
//////  }
//////
//////  private void createTest02() {
//////    this.createColumn(this.fpOutput02,
//////                      "Start Two",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput02.clear();
//////                          fpOutput02.add(createLabel("Execution for button two started"));
//////                          try {
//////                            setUpButton02(duration);
//////                          } catch (SeMa4gException e) {
//////                            GWT.debugger();
//////                            fpOutput02.add(createLabel("Execution aborted: " + e.getMessage()));
//////                          }
//////                        }
//////                      },
//////                      "Several service calls with different duration on the server. No dependencies.");
//////  }
//////
//////  private void createTest03() {
//////    this.createColumn(this.fpOutput03,
//////                      "Start Three",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput03.clear();
//////                          fpOutput03.add(createLabel("Execution for button three started"));
//////                          try {
//////                            setUpButton03(duration);
//////                          } catch (SeMa4gException e) {
//////                            GWT.debugger();
//////                            fpOutput03.add(createLabel("Execution aborted: " + e.getMessage()));
//////                          }
//////                        }
//////                      },
//////                      "Several service calls with different duration on the server. With dependencies, but no cycle dependency.");
//////  }
//////
//////  private void createTest04() {
//////    this.createColumn(this.fpOutput04,
//////                      "Start Four",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput04.clear();
//////                          fpOutput04.add(createLabel("Execution for button four started"));
//////                          try {
//////                            setUpButton04(duration);
//////                          } catch (SeMa4gException e) {
//////                            fpOutput04.add(createLabel("Execution aborted: " + e.getMessage()));
//////                          }
//////                        }
//////                      },
//////                      "Several service calls with different duration on the server. With cycle dependencies, exception expected. No service will be called.");
//////  }
//////
//////  private void createTest05() {
//////    this.createColumn(this.fpOutput05,
//////                      "Start Five",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput05.clear();
//////                          fpOutput05.add(createLabel("Execution for button five started"));
//////                          try {
//////                            setUpButton05(duration);
//////                          } catch (SeMa4gException e) {
//////                            fpOutput05.add(createLabel("Execution aborted: " + e.getMessage()));
//////                          }
//////                        }
//////                      },
//////                      "A single service call (service no. 13) which will fail and throw an exception.");
//////  }
//////
//////  private void createTest06() {
//////    this.createColumn(this.fpOutput06,
//////                      "Start Six",
//////                      new ClickHandler() {
//////                        @Override
//////                        public void onClick(ClickEvent event) {
//////                          Duration duration = new Duration();
//////                          fpOutput06.clear();
//////                          fpOutput06.add(createLabel("Execution for button six started"));
//////                          try {
//////                            setUpButton06(duration);
//////                          } catch (SeMa4gException e) {
//////                            fpOutput06.add(createLabel("Execution aborted: " + e.getMessage()));
//////                          }
//////                        }
//////                      },
//////                      "Several service calls with different duration on the server. One service (service no. 13) will fail and throw an exception.");
//////  }
//////
//////
//////  private void setUpButton01(Duration duration) {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    ctx.addInit(this.initCommand())
//////       .add(this.createCommand01(fpOutput01))
//////       .addFinal(this.finalCommand(this.fpOutput01,
//////                                   "Execution for button one finished",
//////                                   "Execution for button one failed",
//////                                   duration));
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////    }
//////  }
//////
//////
//////  private void setUpButton02(Duration duration)
//////      throws SeMa4gException {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    ctx.addInit(this.initCommand())
//////       .add(this.createCommand02(fpOutput02))
//////       .add(this.createCommand06(fpOutput02))
//////       .add(this.createCommand07(fpOutput02))
//////       .add(this.createCommand01(fpOutput02))
//////       .add(this.createCommand04(fpOutput02))
//////       .add(this.createCommand08(fpOutput02))
//////       .add(this.createCommand09(fpOutput02))
//////       .add(this.createCommand03(fpOutput02))
//////       .add(this.createCommand10(fpOutput02))
//////       .add(this.createCommand05(fpOutput02))
//////       .addFinal(this.finalCommand(this.fpOutput02,
//////                                   "Execution for button two finished",
//////                                   "Execution for button two failed",
//////                                   duration));
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////      throw e;
//////    }
//////  }
//////
//////
//////  private void setUpButton03(Duration duration)
//////      throws SeMa4gException {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    SeMa4gCommand command01 = this.createCommand01(fpOutput03);
//////    SeMa4gCommand command02 = this.createCommand02(fpOutput03);
//////    SeMa4gCommand command03 = this.createCommand03(fpOutput03);
//////    SeMa4gCommand command04 = this.createCommand04(fpOutput03);
//////    SeMa4gCommand command05 = this.createCommand05(fpOutput03);
//////    SeMa4gCommand command06 = this.createCommand06(fpOutput03);
//////    SeMa4gCommand command07 = this.createCommand07(fpOutput03);
//////    SeMa4gCommand command08 = this.createCommand08(fpOutput03);
//////    SeMa4gCommand command09 = this.createCommand09(fpOutput03);
//////    SeMa4gCommand command10 = this.createCommand10(fpOutput03);
//////    SeMa4gCommand command11 = this.createCommand11(fpOutput03);
//////    SeMa4gCommand command12 = this.createCommand12(fpOutput03);
//////    SeMa4gCommand command14 = this.createCommand14(fpOutput03);
//////    SeMa4gCommand command15 = this.createCommand15(fpOutput03);
//////
//////
//////    try {
//////      ctx.addInit(this.initCommand())
//////         .add(command02.dependingOn(command06))
//////         .add(command14)
//////         .add(command06.dependingOn(command04,
//////                                    command07,
//////                                    command09))
//////         .add(command11.dependingOn(command03))
//////         .add(command01)
//////         .add(command04.dependingOn(command10))
//////         .add(command08)
//////         .add(command15.dependingOn(command14))
//////         .add(command09)
//////         .add(command07.dependingOn(command11))
//////         .add(command05)
//////         .add(command03.dependingOn(command08,
//////                                    command05))
//////         .add(command10)
//////         .add(command05)
//////         .add(command12.dependingOn(command02))
//////         .addFinal(this.finalCommand(this.fpOutput03,
//////                                     "Execution for button three finished",
//////                                     "Execution for button three failed",
//////                                     duration));
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////      throw e;
//////    }
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////    }
//////  }
//////
//////
//////  private void setUpButton04(Duration duration)
//////      throws SeMa4gException {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    SeMa4gCommand command01 = this.createCommand01(fpOutput04);
//////    SeMa4gCommand command02 = this.createCommand02(fpOutput04);
//////    SeMa4gCommand command03 = this.createCommand03(fpOutput04);
//////    SeMa4gCommand command04 = this.createCommand04(fpOutput04);
//////    SeMa4gCommand command05 = this.createCommand05(fpOutput04);
//////    SeMa4gCommand command06 = this.createCommand06(fpOutput04);
//////    SeMa4gCommand command07 = this.createCommand07(fpOutput04);
//////    SeMa4gCommand command08 = this.createCommand08(fpOutput04);
//////    SeMa4gCommand command09 = this.createCommand09(fpOutput04);
//////    SeMa4gCommand command10 = this.createCommand10(fpOutput04);
//////
//////
//////    try {
//////      ctx.addInit(this.initCommand())
//////         .add(command02.dependingOn(command06))
//////         .add(command06.dependingOn(command04,
//////                                    command07,
//////                                    command09))
//////         .add(command07)
//////         .add(command01)
//////         .add(command04.dependingOn(command02))
//////         .add(command08)
//////         .add(command09)
//////         .add(command03.dependingOn(command08,
//////                                    command05))
//////         .add(command10)
//////         .add(command05)
//////         .addFinal(this.finalCommand(this.fpOutput04,
//////                                     "Execution for button four finished",
//////                                     "Execution for button four failed",
//////                                     duration));
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////      throw e;
//////    }
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////    }
//////  }
//////
//////
//////  private void setUpButton05(Duration duration)
//////      throws SeMa4gException {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    SeMa4gCommand command13 = this.createCommand13(fpOutput05);
//////
//////    ctx.addInit(this.initCommand())
//////       .add(command13)
//////       .addFinal(this.finalCommand(this.fpOutput05,
//////                                   "Execution for button five finished",
//////                                   "Execution for button five failed",
//////                                   duration));
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////    }
//////  }
//////
//////
//////  private void setUpButton06(Duration duration)
//////      throws SeMa4gException {
//////    // ExecutionContext
//////    SeMa4gExecutionContext ctx = SeMa4g.getNewExecutionContext();
//////
//////    SeMa4gCommand command01 = this.createCommand01(fpOutput06);
//////    SeMa4gCommand command02 = this.createCommand02(fpOutput06);
//////    SeMa4gCommand command03 = this.createCommand03(fpOutput06);
//////    SeMa4gCommand command04 = this.createCommand04(fpOutput06);
//////    SeMa4gCommand command05 = this.createCommand05(fpOutput06);
//////    SeMa4gCommand command06 = this.createCommand06(fpOutput06);
//////    SeMa4gCommand command07 = this.createCommand07(fpOutput06);
//////    SeMa4gCommand command08 = this.createCommand08(fpOutput06);
//////    SeMa4gCommand command09 = this.createCommand09(fpOutput06);
//////    SeMa4gCommand command10 = this.createCommand10(fpOutput06);
//////    SeMa4gCommand command13 = this.createCommand13(fpOutput06);
//////
//////
//////    try {
//////      ctx.addInit(this.initCommand())
//////         .add(command02.dependingOn(command06))
//////         .add(command06.dependingOn(command08,
//////                                    command13,
//////                                    command09))
//////         .add(command07)
//////         .add(command01)
//////         .add(command04.dependingOn(command02))
//////         .add(command08)
//////         .add(command09)
//////         .add(command13)
//////         .add(command03.dependingOn(command08,
//////                                    command05))
//////         .add(command10)
//////         .add(command05)
//////         .addFinal(this.finalCommand(this.fpOutput06,
//////                                     "Execution for button six finished",
//////                                     "Execution for button six failed",
//////                                     duration));
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////      throw e;
//////    }
//////
//////    try {
//////      ctx.run();
//////    } catch (SeMa4gException e) {
//////      e.printStackTrace();
//////    }
//////  }
//////
////////------------------------------------------------------------------------------
//////
//////  private InitCommand initCommand() {
//////    return new InitCommand() {
//////      @Override
//////      public void onStart() {
//////        popup.center();
//////      }
//////    };
//////  }
//////
//////  private FinalCommand finalCommand(final FlowPanel fp,
//////                                    final String successMessage,
//////                                    final String errorMessage,
//////                                    final Duration duration) {
//////    return new FinalCommand() {
//////      @Override
//////      public void onSuccess() {
//////        popup.hide();
//////        fp.add(createLabel(successMessage));
//////        fp.add(createLabel("Exection Time: " + Integer.toString(duration.elapsedMillis()) + " ms."));
//////      }
//////
//////      @Override
//////      public void onFailure() {
//////        popup.hide();
//////        fp.add(createLabel(errorMessage));
//////        fp.add(createLabel("Exection Time: " + Integer.toString(duration.elapsedMillis()) + " ms."));
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand01(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service01.callServer(1250,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service One failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service one");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand02(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service02.callServer(4250,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service two failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service two");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand03(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service03.callServer(1750,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service three failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service three");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand04(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service04.callServer(6250,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service four failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service four");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand05(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service05.callServer(12050,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service five failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service five");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand06(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service06.callServer(275,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service six failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service six");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand07(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service07.callServer(2450,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service Seven failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service seven");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand08(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service08.callServer(3800,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service Eight failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service eight");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand09(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service09.callServer(5350,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service Nine failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service nine");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand10(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service10.callServer(3250,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: service Ten failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service ten");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand11(final FlowPanel fp) {
//////    return new SeMa4gSyncCommand() {
//////      @Override
//////      public void execute() {
//////        Timer timer = new Timer() {
//////          public void run() {
//////            GWT.log("Ready - synchron");
//////          }
//////        };
//////        timer.schedule(1250);
//////        fp.add(createLabel("Service Eleven (synchron) returned after 1250 ms"));
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand12(final FlowPanel fp) {
//////    return new SeMa4gSyncCommand() {
//////      @Override
//////      public void execute() {
//////        Timer timer = new Timer() {
//////          public void run() {
//////            GWT.log("Ready - synchron");
//////          }
//////        };
//////        timer.schedule(1250);
//////        fp.add(createLabel("Service Twelve (synchron) returned after 1250 ms"));
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand13(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        service13.callServer(20512,
//////                             new SeMa4gAsyncCallbackProxy<AsyncCallback<String>, String>(this,
//////                                                                                         new AsyncCallback<String>() {
//////                                                                                           @Override
//////                                                                                           public void onFailure(Throwable caught) {
//////                                                                                             fp.add(createLabel("Ups: Service Thirteen failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createLabel(result));
//////                                                                                             GWT.log("success service thirteen");
//////                                                                                           }
//////                                                                                         })
//////        );
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand14(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        // URL
//////        String url = JSON_URL + "wait=" + Long.toString(4250);
//////        url = URL.encode(url);
//////
//////        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
//////                                                    url);
//////        try {
//////          @SuppressWarnings("unused")
//////          Request request = builder.sendRequest(null,
//////                                                new SeMa4gRequestCallbackProxy(this,
//////                                                                               new RequestCallback() {
//////                                                                                 public void onError(Request request,
//////                                                                                                     Throwable exception) {
//////                                                                                   fp.add(createLabel("Ups: Service Fourteen failure"));
//////                                                                                 }
//////
//////                                                                                 public void onResponseReceived(Request request,
//////                                                                                                                Response response) {
//////                                                                                   if (200 == response.getStatusCode()) {
//////                                                                                     Message message = deserializeMessageFromJSON(response.getText());
//////                                                                                     fp.add(createLabel(message.getMessage()));
//////                                                                                     GWT.log("success service fourteen");
//////                                                                                   } else {
//////                                                                                     fp.add(createLabel("Ups: Service Fourteen failure"));
//////                                                                                   }
//////                                                                                 }
//////                                                                               }));
//////        } catch (RequestException e) {
//////          fp.add(createLabel("Ups: Service Fourteen failure"));
//////        }
//////      }
//////    };
//////  }
//////
//////  private SeMa4gCommand createCommand15(final FlowPanel fp) {
//////    return new SeMa4gRcpCommand() {
//////      @Override
//////      public void execute() {
//////        // URL
//////        String url = REQUEST_URL + "wait=" + Long.toString(1750);
//////        url = URL.encode(url);
//////
//////        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
//////                                                    url);
//////        try {
//////          @SuppressWarnings("unused")
//////          Request request = builder.sendRequest(null,
//////                                                new SeMa4gRequestCallbackProxy(this,
//////                                                                               new RequestCallback() {
//////                                                                                 public void onError(Request request,
//////                                                                                                     Throwable exception) {
//////                                                                                   fp.add(createLabel("Ups: Service Fifteen failure"));
//////                                                                                 }
//////
//////                                                                                 public void onResponseReceived(Request request,
//////                                                                                                                Response response) {
//////                                                                                   if (200 == response.getStatusCode()) {
//////                                                                                     String message = response.getText();
//////                                                                                     fp.add(createLabel(message));
//////                                                                                     GWT.log("success service Fifteen");
//////                                                                                   } else {
//////                                                                                     fp.add(createLabel("Ups: Service Fifteen failure"));
//////                                                                                   }
//////                                                                                 }
//////                                                                               }));
//////        } catch (RequestException e) {
//////          fp.add(createLabel("Ups: Service Fifteen failure"));
//////        }
//////      }
//////    };
//////  }
//////
////////------------------------------------------------------------------------------
//////
//////  private Message deserializeMessageFromJSON(String json) {
//////    AutoBean<Message> bean = AutoBeanCodex.decode(myBeanFactory,
//////                                                  Message.class,
//////                                                  json);
//////    return bean.as();
//////  }
//////
////////------------------------------------------------------------------------------
//////
//////  private void createColumn(FlowPanel fpOutput,
//////                            String buttonText,
//////                            ClickHandler clickHandler,
//////                            String description) {
//////    final FlowPanel fp = new FlowPanel();
//////    fp.getElement()
//////      .getStyle()
//////      .setFloat(Style.Float.LEFT);
//////    fp.getElement()
//////      .getStyle()
//////      .setDisplay(Display.BLOCK);
//////    fp.setWidth("100%");
//////    root.add(fp);
//////
//////    Button buttonOne = new Button(buttonText);
//////    buttonOne.setWidth("100px");
//////    buttonOne.getElement()
//////             .getStyle()
//////             .setFloat(Style.Float.LEFT);
//////    buttonOne.getElement()
//////             .getStyle()
//////             .setMargin(8,
//////                        Unit.PX);
//////    buttonOne.addClickHandler(clickHandler);
//////    fp.add(buttonOne);
//////
//////    ScrollPanel sp = new ScrollPanel();
//////    sp.getElement()
//////      .getStyle()
//////      .setFloat(Style.Float.LEFT);
//////    sp.setSize("500px",
//////               "200px");
//////    fp.add(sp);
//////
//////    fpOutput.getElement()
//////            .getStyle()
//////            .setMarginTop(8,
//////                          Unit.PX);
//////    fpOutput.getElement()
//////            .getStyle()
//////            .setMarginLeft(8,
//////                           Unit.PX);
//////    sp.getElement()
//////      .getStyle()
//////      .setBorderColor("grey");
//////    sp.getElement()
//////      .getStyle()
//////      .setBorderWidth(1,
//////                      Unit.PX);
//////    sp.getElement()
//////      .getStyle()
//////      .setBorderStyle(Style.BorderStyle.SOLID);
//////    sp.getElement()
//////      .getStyle()
//////      .setMarginTop(8,
//////                    Unit.PX);
//////    sp.getElement()
//////      .getStyle()
//////      .setMarginLeft(8,
//////                     Unit.PX);
//////
//////    fpOutput.setHeight("100px");
//////    fpOutput.setWidth("500px");
//////    sp.add(fpOutput);
//////
//////    Label label = new Label(description);
//////    label.getElement()
//////         .getStyle()
//////         .setWidth(250,
//////                   Unit.PX);
//////    label.getElement()
//////         .getStyle()
//////         .setMarginTop(8,
//////                       Unit.PX);
//////    label.getElement()
//////         .getStyle()
//////         .setMarginLeft(42,
//////                        Unit.PX);
//////    label.getElement()
//////         .getStyle()
//////         .setFloat(Style.Float.LEFT);
//////    fp.add(label);
//////  }

  private Widget createNorthWidget() {
    FlowPanel fp = new FlowPanel();
    fp.setSize("100%",
               "42px");
    fp.addStyleName(style.containerNorth());
    fp.addStyleName(style.floatingLeft());

    Label label = new Label("Test:");
    label.addStyleName(style.floatingLeft());
    label.addStyleName(style.testLabel());
    fp.add(label);

//    lbCases.addStyleName(style.testCases());
//    fp.add(lbCases);

    runButton.addStyleName(style.testButton());
    fp.add(runButton);

    resetButton.addStyleName(style.testButton());
    fp.add(resetButton);

    return fp;
  }

  private Widget createCenter() {
    FlowPanel fp = new FlowPanel();
    fp.addStyleName(style.floatingLeft());

    FlowPanel fpLeft = new FlowPanel();
    fpLeft.addStyleName(style.floatingLeft());
    fpLeft.addStyleName(style.container());
    fpLeft.setSize("612px",
                   "100%");
    fp.add(fpLeft);

    FlowPanel fpRight = new FlowPanel();
    fpRight.addStyleName(style.floatingLeft());
    fpRight.addStyleName(style.container());
    fpRight.setSize("612px",
                    "100%");
    fp.add(fpRight);

    DockLayoutPanel leftPanel = new DockLayoutPanel(Style.Unit.PX);
    leftPanel.setSize("612px",
                      "100%");
    fpLeft.add(leftPanel);

    Label leftHeadline = new Label("Code");
    leftHeadline.addStyleName(style.headlineLabel());
    leftPanel.addNorth(leftHeadline,
                       32);

    ScrollPanel spLeft = new ScrollPanel();
    spLeft.addStyleName(style.border());
    spLeft.setSize("598px",
                   "770px");
    leftPanel.add(spLeft);
    spLeft.add(fpSourceListing);

    DockLayoutPanel rightPanel = new DockLayoutPanel(Style.Unit.PX);
    rightPanel.setSize("612px",
                       "100%");
    fpRight.add(rightPanel);

    Label rightHeadlineTop = new Label("Description");
    rightHeadlineTop.addStyleName(style.headlineLabel());
    rightPanel.addNorth(rightHeadlineTop,
                        32);

    ScrollPanel spRightTop = new ScrollPanel();
    spRightTop.addStyleName(style.border());
    spRightTop.setSize("598px",
                       "175px");
    rightPanel.addNorth(spRightTop,
                        188);
    spRightTop.add(fpDescription);

    Label rightHeadlineBottom = new Label("Result");
    rightHeadlineBottom.addStyleName(style.headlineLabel());
    rightPanel.addNorth(rightHeadlineBottom,
                        32);

    ScrollPanel spRightBottom = new ScrollPanel();
    spRightBottom.addStyleName(style.border());
    spRightBottom.setSize("598px",
                          "550px");
    rightPanel.add(spRightBottom);
    spRightBottom.add(fpResult);

    return fp;
  }

//  private void clear() {
//    fpSourceListing.clear();
//    fpDescription.clear();
//    fpResult.clear();
//  }
//
//  private Label createLabel(String value) {
//    Label label = new Label(value);
//    label.getElement()
//         .getStyle()
//         .setMargin(4,
//                    Style.Unit.PX);
//    return label;
//  }

  interface CssResources
    extends ClientBundle {

    CssResources CSS = GWT.create(CssResources.class);

    @CssResource.NotStrict
    @Source("resources/global.css")
    ExampleStyle exampleStyle();
  }

  public interface ExampleStyle
    extends CssResource {

    String border();

    String container();

    String containerNorth();

    String floatingLeft();

    String headlineLabel();

    String popup();

    String popupLabel();

    String testButton();

    String testLabel();

    String testCases();

  }

////==============================================================================
//
//  @Command(type = Command.CommandType.INIT)
//  class InitCommand
//    implements SeMa4gInitCommand {
//
//    public void onStart() {
//
//    }
//  }
//
//  @Command(type = Command.CommandType.FINAL)
//  class FinalCommand
//    implements SeMa4gFinalCommand {
//
//    public void onFailure() {
//    }
//
//    public void onSuccess() {
//    }
//  }
}
