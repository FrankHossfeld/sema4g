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

package org.gwt4e.sema4g.client.commands.proxies;

import org.gwt4e.sema4g.client.commands.AsyncCommand;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * <p>Add support for RequestCallback in GWT {@link RequestCallback}</p>
 * <br /><br />
 * <p>If you are using SeMa4g, you have to this RequestCallbackProxy
 * class instead of the RequestCallback. Otherwise SeMa4g will not work.</p>
 */
public abstract class RequestCallbackProxy
  implements SeMa4gProxy,
             RequestCallback {

  /* execution command of this proxy */
  private       AsyncCommand    command;

  public RequestCallbackProxy(AsyncCommand command) {
    super();
    this.command = command;
  }

  @Override
  public void onResponseReceived(Request request,
                                 Response response) {
    // Set State
    command.setStateFinish();
    // do the default handling
    onProxyResponseReceived(request,
                                     response);
    // do the SeMa4g handling
    command.trigger();
  }

  @Override
  public void onError(Request request,
                      Throwable exception) {
    // Set State
    command.setStateError();
    // do the default handling
    onProxyError(request,
                          exception);
    // do the SeMa4g handling
    command.failure(exception);
  }

  protected abstract void onProxyError(Request request,
                                       Throwable exception);

  protected abstract void onProxyResponseReceived(Request request,
                                                  Response response);
}
