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

package org.gwt4e.sema4g.resty.client.commands.proxies;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.TextCallback;
import org.gwt4e.sema4g.client.commands.AsyncCommand;
import org.gwt4e.sema4g.client.commands.proxies.SeMa4gProxy;

/**
 * <p>Add support for RestyGWT {@link TextCallback}</p>
 * <br /><br />
 * <p>If you are using SeMa4g, you have to this TextCallbackProxy
 * class instead of the TextCallback from the RestyGWT. Otherwise
 * SeMa4g will not work.</p>
 */
public class TextCallbackProxy
  implements SeMa4gProxy,
             TextCallback {

  private final TextCallback asyncCallback;
  /* execution command of this proxy */
  private       AsyncCommand command;

//------------------------------------------------------------------------------

  public TextCallbackProxy(AsyncCommand command,
                           TextCallback asyncCallback) {
    super();
    this.command = command;
    this.asyncCallback = asyncCallback;
  }

//------------------------------------------------------------------------------

  @Override
  public void onFailure(Method method,
                        Throwable caught) {
    // do the default handling
    asyncCallback.onFailure(method,
                            caught);
    // do the SeMa4g handling
    command.onFailure(caught);
  }

  @Override
  public void onSuccess(Method method,
                        String result) {
    // do the default handling
    asyncCallback.onSuccess(method,
                            result);
    // do the SeMa4g handling
    command.onSuccess();
  }
}