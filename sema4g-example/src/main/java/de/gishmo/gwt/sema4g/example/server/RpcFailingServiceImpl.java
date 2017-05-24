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
package de.gishmo.gwt.sema4g.example.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.gishmo.gwt.sema4g.example.client.service.RpcFailingService;
import de.gishmo.gwt.sema4g.example.shared.exception.ServiceException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcFailingServiceImpl
  extends RemoteServiceServlet
  implements RpcFailingService {

  public String callServer(long wait,
                           String name)
    throws ServiceException {

    try {
      Thread.sleep(wait);
    } catch (InterruptedException e) {
      throw new ServiceException();
    }

    throw new ServiceException("Service (RPC) " + name + "throws ServiceExceptio nafter " + Long.toString(wait) + " ms");
  }
}