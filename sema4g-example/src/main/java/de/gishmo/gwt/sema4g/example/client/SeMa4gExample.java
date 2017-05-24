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
package de.gishmo.gwt.sema4g.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.*;
import de.gishmo.gwt.sema4g.example.client.cases.*;
import de.gishmo.gwt.sema4g.example.client.cases.code.CodeWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SeMa4gExample
  implements EntryPoint {

  static {
    CssResources.CSS.exampleStyle()
                    .ensureInjected();
  }

  private ExampleStyle       style           = CssResources.CSS.exampleStyle();
  private CodeWidget         codeWidgets     = GWT.create(CodeWidget.class);
  private DockLayoutPanel    root            = new DockLayoutPanel(Style.Unit.PX);
  private FlowPanel          fpSourceListing = new FlowPanel();
  private FlowPanel          fpDescription   = new FlowPanel();
  private FlowPanel          fpResult        = new FlowPanel();
  private PopupPanel         popup           = new PopupPanel();
  private Button             runButton       = new Button("Run");
  private Button             resetButton     = new Button("Reset");
  private ListBox            lbCases         = new ListBox();
  private List<AbstractCase> cases           = new ArrayList<>();
  private AbstractCase selectedCase;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    this.createListOfCases();
    this.createPopUp();
    this.bind();

    this.setUpCase();

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
    resetButton.addClickHandler((e) -> {
      clear();
      setUpCase();
    });

    runButton.addClickHandler((e) -> {
      GWT.debugger();
      if (selectedCase != null) {
        clear();
        setUpCase();
        selectedCase.createContextAndRun();
      }
    });

    lbCases.addChangeHandler((e) -> {
      clear();
      selectedCase = cases.get(lbCases.getSelectedIndex());
      setUpCase();
    });
  }

  private void createListOfCases() {
    Case01 case01 = new Case01(fpResult,
                               popup);
    cases.add(case01);
    lbCases.addItem(case01.getLabelText());


    Case02 case02 = new Case02(fpResult,
                               popup);
    cases.add(case02);
    lbCases.addItem(case02.getLabelText());


    Case03 case03 = new Case03(fpResult,
                               popup);
    cases.add(case03);
    lbCases.addItem(case03.getLabelText());


    Case04 case04 = new Case04(fpResult,
                               popup);
    cases.add(case04);
    lbCases.addItem(case04.getLabelText());


    Case05 case05 = new Case05(fpResult,
                               popup);
    cases.add(case05);
    lbCases.addItem(case05.getLabelText());


    Case06 case06 = new Case06(fpResult,
                               popup);
    cases.add(case06);
    lbCases.addItem(case06.getLabelText());


    Case07 case07 = new Case07(fpResult,
                               popup);
    cases.add(case07);
    lbCases.addItem(case07.getLabelText());


    lbCases.setSelectedIndex(0);
    selectedCase = cases.get(0);
  }

  private void setUpCase() {
    if (selectedCase != null) {
      fpDescription.add(createHtml(selectedCase.getDescriptionText()));
      fpSourceListing.add(codeWidgets.getWidget(selectedCase.getClass()
                                                            .getSimpleName()));
    }
  }
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
//////        fp.add(createHtml("Service Eleven (synchron) returned after 1250 ms"));
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
//////        fp.add(createHtml("Service Twelve (synchron) returned after 1250 ms"));
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
//////                                                                                             fp.add(createHtml("Ups: Service Thirteen failure"));
//////                                                                                           }
//////
//////                                                                                           @Override
//////                                                                                           public void onSuccess(String result) {
//////                                                                                             fp.add(createHtml(result));
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
//////                                                                                   fp.add(createHtml("Ups: Service Fourteen failure"));
//////                                                                                 }
//////
//////                                                                                 public void onResponseReceived(Request request,
//////                                                                                                                Response response) {
//////                                                                                   if (200 == response.getStatusCode()) {
//////                                                                                     Message message = deserializeMessageFromJSON(response.getText());
//////                                                                                     fp.add(createHtml(message.getMessage()));
//////                                                                                     GWT.log("success service fourteen");
//////                                                                                   } else {
//////                                                                                     fp.add(createHtml("Ups: Service Fourteen failure"));
//////                                                                                   }
//////                                                                                 }
//////                                                                               }));
//////        } catch (RequestException e) {
//////          fp.add(createHtml("Ups: Service Fourteen failure"));
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
//////                                                                                   fp.add(createHtml("Ups: Service Fifteen failure"));
//////                                                                                 }
//////
//////                                                                                 public void onResponseReceived(Request request,
//////                                                                                                                Response response) {
//////                                                                                   if (200 == response.getStatusCode()) {
//////                                                                                     String message = response.getText();
//////                                                                                     fp.add(createHtml(message));
//////                                                                                     GWT.log("success service Fifteen");
//////                                                                                   } else {
//////                                                                                     fp.add(createHtml("Ups: Service Fifteen failure"));
//////                                                                                   }
//////                                                                                 }
//////                                                                               }));
//////        } catch (RequestException e) {
//////          fp.add(createHtml("Ups: Service Fifteen failure"));
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

    lbCases.addStyleName(style.testCases());
    fp.add(lbCases);

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
    fpLeft.setSize("512px",
                   "100%");
    fp.add(fpLeft);

    FlowPanel fpRight = new FlowPanel();
    fpRight.addStyleName(style.floatingLeft());
    fpRight.addStyleName(style.container());
    fpRight.setSize("512px",
                    "100%");
    fp.add(fpRight);

    DockLayoutPanel leftPanel = new DockLayoutPanel(Style.Unit.PX);
    leftPanel.setSize("512px",
                      "100%");
    fpLeft.add(leftPanel);

    Label leftHeadline = new Label("Code");
    leftHeadline.addStyleName(style.headlineLabel());
    leftPanel.addNorth(leftHeadline,
                       32);

    ScrollPanel spLeft = new ScrollPanel();
    spLeft.addStyleName(style.border());
    spLeft.setSize("498px",
                   "770px");
    leftPanel.add(spLeft);
    spLeft.add(fpSourceListing);

    DockLayoutPanel rightPanel = new DockLayoutPanel(Style.Unit.PX);
    rightPanel.setSize("512px",
                       "100%");
    fpRight.add(rightPanel);

    Label rightHeadlineTop = new Label("Description");
    rightHeadlineTop.addStyleName(style.headlineLabel());
    rightPanel.addNorth(rightHeadlineTop,
                        32);

    ScrollPanel spRightTop = new ScrollPanel();
    spRightTop.addStyleName(style.border());
    spRightTop.setSize("498px",
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
    spRightBottom.setSize("498px",
                          "550px");
    rightPanel.add(spRightBottom);
    spRightBottom.add(fpResult);

    return fp;
  }

  private void clear() {
    fpSourceListing.clear();
    fpDescription.clear();
    fpResult.clear();
  }

  private HTML createHtml(String value) {
    HTML html = new HTML(value);
    html.getElement()
         .getStyle()
         .setMargin(4,
                    Style.Unit.PX);
    return html;
  }

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
}
