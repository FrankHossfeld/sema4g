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
 * Copyright 2014 Frank Hossfeld
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

public abstract class AbstractCase {
//
//  private static final String JSON_URL    = GWT.getModuleBaseURL() + "jsonMessage?";
//  private static final String REQUEST_URL = GWT.getModuleBaseURL() + "message?";
//
//  /**
//   * Create a remote service proxy to talk to the server-side Greeting service.
//   */
//  private final RpcServiceAsync service01 = RpcServiceAsync.Util.getInstance();
//  String           headline;
//  String           labelText;
//  String           descriptionText;
//  String           buttonText;
//  String           startText;
//  String           successText;
//  String           errorText;
//  ExecutionContext context;
//  private MyBeanFactory myBeanFactory = GWT.create(MyBeanFactory.class);
//  private Duration   duration;
//  private FlowPanel  fp;
//  private PopupPanel popup;
//
//  protected AbstractCase(FlowPanel fp,
//                         PopupPanel popup) {
//    context = SeMa4g.getNewExecutionContext();
//
//    this.fp = fp;
//    this.popup = popup;
//
//    this.createContext();
//  }
//
////------------------------------------------------------------------------------
//
//  protected abstract void createContext();
//
////--------------------------------------------------------------------
//
//  public ExecutionContext getContext() {
//    return context;
//  }
//
//  public String getDescriptionText() {
//    return descriptionText;
//  }
//
//  public String getLabelText() {
//    return labelText;
//  }
//
//  public String getButtonText() {
//    return buttonText;
//  }
//
//  public void run()
//    throws SeMa4gException {
//    duration = new Duration();
//    context.run();
//  }
//
////------------------------------------------------------------------------------
//
//  /**
//   * Creates an instance of an {@link SeMa4gInitCommand} to use in a TestCase.
//   * <br><br>
//   * The InitCommand opens a popup to lock the screen.
//   *
//   * @return instance of InitCommand
//   */
//  @SourceCode
//  SeMa4gInitCommand initCommand() {
//    return new SeMa4gInitCommand() {
//      @Override
//      public void onStart() {
//        popup.center();
//      }
//    };
//  }
//
//  /**
//   * Creates an instance of a {@link SeMa4gFinalCommand} to use in a TestCase.
//   * <br><br>
//   * The FinalCommand close the popup to unlock the screen and sets
//   * some information in the related fields.
//   *
//   * @return instance of FinalCommand
//   */
//  @SourceCode
//  SeMa4gFinalCommand finalCommand() {
//    return new SeMa4gFinalCommand() {
//      @Override
//      public void onSuccess() {
//        popup.hide();
//        fp.add(createLabel(successText));
//        fp.add(executionTime());
//      }
//
//      @Override
//      public void onFailure() {
//        popup.hide();
//        fp.add(createLabel(errorText));
//        fp.add(executionTime());
//      }
//    };
//  }
//
//  /**
//   * Creates an instance of an {@link SeMa4gAsyncCommand} command.
//   * <br><br>
//   * The command will call a RPC service on the service which waits
//   * the number of milliseconds before returning to the client
//   *
//   * @param waitTime number of milliseconds the server waits before returning ot the client
//   * @param name identifier of the service
//   * @return instance of FinalCommand
//   */
//  @SourceCode
//  Command createAsyncCommandRPC(final long waitTime,
//                                      final String name) {
//    return new SeMa4gAsyncCommand() {
//      @Override
//      public void execute() {
//        service01.callServer(waitTime,
//                             name,
//                             new AsyncCallbackProxy<AsyncCallback<String>, String>(this,
//                                                                                   new AsyncCallback<String>() {
//                                                                                     @Override
//                                                                                     public void onFailure(Throwable caught) {
//                                                                                       fp.add(createLabel("Ups: service " + name + " failure"));
//                                                                                     }
//
//                                                                                     @Override
//                                                                                     public void onSuccess(String result) {
//                                                                                       fp.add(createLabel(result));
//                                                                                       GWT.log("service " + name + " successful executed");
//                                                                                     }
//                                                                                   })
//        );
//      }
//    };
//  }
//
//
//  /**
//   * Creates an instance of an {@link SeMa4gAsyncCommand} command.
//   * <br><br>
//   * The command will call a service using a RequestBuilder on the service which waits
//   * the number of milliseconds before returning to the client
//   *
//   * @param waitTime number of milliseconds the server waits before returning ot the client
//   * @param name identifier of the service
//   * @return instance of FinalCommand
//   */
//  Command createAsyncCommandRequestBuilder(final long waitTime,
//                                                 final String name) {
//    return new SeMa4gAsyncCommand() {
//      @Override
//      public void execute() {
//        // URL
//        String url = JSON_URL + "wait=" + Long.toString(waitTime);
//        url = URL.encode(url);
//
//        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
//                                                    url);
//        try {
//          @SuppressWarnings("unused")
//          Request request = builder.sendRequest(null,
//                                                new RequestCallbackProxy(this,
//                                                                         new RequestCallback() {
//                                                                           @Override
//                                                                           public void onResponseReceived(Request request,
//                                                                                                          Response response) {
//                                                                             if (200 == response.getStatusCode()) {
//                                                                               Message message = deserializeMessageFromJSON(response.getText());
//                                                                               fp.add(createLabel(message.getMessage()));
//                                                                               GWT.log("success service fourteen");
//                                                                             } else {
//                                                                               fp.add(createLabel("Ups: Service "+ name + " failure"));
//                                                                             }
//                                                                           }
//                                                                           @Override
//                                                                           public void onError(Request request,
//                                                                                               Throwable exception) {
//                                                                             fp.add(createLabel("Ups: Service " + name + " failure"));
//                                                                           }
//                                                                         }));
//        } catch (RequestException e) {
//          fp.add(createLabel("Ups: Service Fourteen failure"));
//        }
//      }
//    };
//  }
//
//
//
//  /**
//   * Creates an instance of an {@link SeMa4gSyncCommand} command with a Timer, to simulate some actions.
//   * <br><br>
//   * The command will call a RPC service on the service which waits
//   * the number of milliseconds before returning to the client
//   *
//   * @param waitTime number of milliseconds the server waits before returning ot the client
//   * @param name identifier of the service
//   * @return instance of FinalCommand
//   */
//  Command createSyncCommand(final long waitTime,
//                                  final String name) {
//    return new SeMa4gSyncCommand() {
//      @Override
//      public void execute() {
//        Timer timer = new Timer() {
//          public void run() {
//            GWT.log("Ready - synchron");
//          }
//        };
//        timer.schedule(1250);
//        fp.add(createLabel("Service " + name + " (synchron) returned after " + Long.toString(waitTime) + " ms"));
//      }
//    };
//  }
//
//  /**
//   * end of sourcecode widgets *
//   */
//
////------------------------------------------------------------------------------
//
//  private Label createLabel(String value) {
//    Label label = new Label(value);
//    label.getElement()
//         .getStyle()
//         .setMargin(4,
//                    Style.Unit.PX);
//    return label;
//  }
//
//  private Label executionTime() {
//    return createLabel("Exection Time: " + Integer.toString(duration.elapsedMillis()) + " ms.");
//  }
//
////------------------------------------------------------------------------------
//
//  private Message deserializeMessageFromJSON(String json) {
//    AutoBean<Message> bean = AutoBeanCodex.decode(myBeanFactory,
//                                                  Message.class,
//                                                  json);
//    return bean.as();
//  }
//
////------------------------------------------------------------------------------
//
//  interface MyBeanFactory
//    extends AutoBeanFactory {
//
//    AutoBean<Message> message();
//
//  }

}
