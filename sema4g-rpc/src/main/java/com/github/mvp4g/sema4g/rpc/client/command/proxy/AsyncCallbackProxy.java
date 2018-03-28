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
package com.github.mvp4g.sema4g.rpc.client.command.proxy;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.github.mvp4g.sema4g.client.command.AsyncCommand;
import com.github.mvp4g.sema4g.client.command.proxy.SeMa4gProxy;

/**
 * <p>Add support for AsyncCallback in GWT {@link AsyncCallback}</p>
 * <br /><br />
 * <p>If you are using SeMa4g, you have to this AsyncCallbackProxy
 * class instead of the AsyncCallback. Otherwise SeMa4g will not work.</p>
 */
public abstract class AsyncCallbackProxy<T>
  implements SeMa4gProxy,
             AsyncCallback<T> {

  /* execution command of this proxy */
  private AsyncCommand command;

  public AsyncCallbackProxy(AsyncCommand command) {
    super();
    this.command = command;
  }

  @Override
  public void onFailure(Throwable caught) {
    // Set State
    command.setStateError();
    // do the default handling
    onProxyFailure(caught);
    // do the SeMa4g handling
    command.failure(caught);
  }

  protected abstract void onProxyFailure(Throwable caught);

  @Override
  public void onSuccess(T result) {
    // Set State
    command.setStateFinish();
    // do the default handling
    onProxySuccess(result);
    // do the SeMa4g handling
    command.trigger();
  }

  protected abstract void onProxySuccess(T result);

}
