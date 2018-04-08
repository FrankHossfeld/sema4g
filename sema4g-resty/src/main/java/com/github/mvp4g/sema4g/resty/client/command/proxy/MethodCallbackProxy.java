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
package com.github.mvp4g.sema4g.resty.client.command.proxy;

import com.github.mvp4g.sema4g.client.command.AsyncCommand;
import com.github.mvp4g.sema4g.client.command.proxy.SeMa4gProxy;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * <p>Add support for RestyGWT {@link MethodCallback}</p>
 * <br /><br />
 * <p>If you are using SeMa4g, you have to this MethodCallbackProxy
 * class instead of the MethodCallback from the RestyGWT. Otherwise
 * SeMa4g will not work.</p>
 */
public abstract class MethodCallbackProxy<T>
  implements SeMa4gProxy,
             MethodCallback<T> {

  /* execution command of this proxy */
  private AsyncCommand command;

  public MethodCallbackProxy(AsyncCommand command) {
    super();
    this.command = command;
  }

  @Override
  public void onFailure(Method method,
                        Throwable caught) {
    // Set State
    command.setStateError();
    // do the default handling
    onProxyFailure(method,
                   caught);
    // do the SeMa4g handling
    command.failure(caught);
  }

  @Override
  public void onSuccess(Method method,
                        T result) {
    // Set State
    command.setStateFinish();
    // do the default handling
    onProxySuccess(method,
                   result);
    // do the SeMa4g handling
    command.trigger();
  }

  protected abstract void onProxySuccess(Method method,
                                         T result);

  protected abstract void onProxyFailure(Method method,
                                         Throwable caught);

}