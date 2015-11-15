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

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwt4e.sema4g.client.context.commands.AsyncCommand;

/**
 * <p>headline</p>
 * <br /><br />
 * <p>description</p>
 * <br /><br />
 * User: hoss
 * Date: 14.02.14
 * Time: 10:12
 *
 * @author Frank Hossfeld
 * @version 1.0.0
 */


public class AsyncCallbackProxy<C extends AsyncCallback<T>, T>
  implements SeMa4gProxy,
             AsyncCallback<T> {

  private final C            asyncCallback;
  /* execution command of this proxy */
  private       AsyncCommand command;

//------------------------------------------------------------------------------

  public AsyncCallbackProxy(AsyncCommand command,
                            C asyncCallback) {
    super();
    this.command = command;
    this.asyncCallback = asyncCallback;
  }

//------------------------------------------------------------------------------

  @Override
  public void onFailure(Throwable caught) {
    // do the default handling
    asyncCallback.onFailure(caught);
    // do the SeMa4g handling
    command.onFailure(caught);
  }

  @Override
  public void onSuccess(T result) {
    // do the default handling
    asyncCallback.onSuccess(result);
    // do the SeMa4g handling
    command.onSuccess();
  }
}