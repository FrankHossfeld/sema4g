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

package org.gwt4e.sema4g.client.context.commands.proxies;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import org.gwt4e.sema4g.client.context.commands.AsyncCommand;

/**
 * <p>Add support for RequestCallback in GWT {@link RequestCallback}</p>
 * <br /><br />
 * <p>If you are using SeMa4g, you have to this RequestCallbackProxy
 * class instead of the RequestCallback. Otherwise SeMa4g will not work.</p>
 */
public class RequestCallbackProxy
  implements SeMa4gProxy,
             RequestCallback {

  private final RequestCallback asyncCallback;
  /* execution command of this proxy */
  private       AsyncCommand    command;

//------------------------------------------------------------------------------

  public RequestCallbackProxy(AsyncCommand command,
                              RequestCallback asyncCallback) {
    super();
    this.command = command;
    this.asyncCallback = asyncCallback;
  }

//------------------------------------------------------------------------------

  @Override
  public void onResponseReceived(Request request,
                                 Response response) {
    // do the default handling
    asyncCallback.onResponseReceived(request,
                                     response);
    // do the SeMa4g handling
    command.onSuccess();
  }

  @Override
  public void onError(Request request,
                      Throwable exception) {
    // do the default handling
    asyncCallback.onError(request,
                          exception);
    // do the SeMa4g handling
    command.onFailure(exception);
  }
}