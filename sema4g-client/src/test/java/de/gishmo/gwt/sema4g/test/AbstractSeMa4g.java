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

package de.gishmo.gwt.sema4g.test;

import com.github.mvp4g.sema4g.client.command.FinalCommand;
import com.github.mvp4g.sema4g.client.command.InitCommand;
import com.github.mvp4g.sema4g.client.command.SeMa4gCommand;
import com.github.mvp4g.sema4g.client.command.SyncCommand;

abstract class AbstractSeMa4g {
  InitCommand createInitCommand() {
    return new InitCommand() {
      @Override
      public void onStart() {
        System.out.println("execute init command");
      }
    };
  }

  FinalCommand createFinalCommand() {
    return new FinalCommand() {
      @Override
      public void onSuccess() {
        System.out.println("execute final command - onSuccess");
      }

      @Override
      public void onFailure() {
        System.out.println("execute final command - onFailure");
      }
    };
  }

  SeMa4gCommand createSyncCommand(final long waitTimeInMillis) {
    return new SyncCommand() {
      @Override
      public void execute() {
        try {
          Thread.sleep(waitTimeInMillis);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
  }

//  /**
//   * Creates an instance of an {@link AsyncCommand} command.
//   * <br><br>
//   * The command will call a RPC service on the service which waits
//   * the number of milliseconds before returning to the client
//   *
//   * @param waitTime number of milliseconds the server waits before returning ot the client
//   * @param name identifier of the service
//   * @return instance of FinalCommand
//   */
//  Command createAsyncCommandRPC(final long waitTime,
//                                final String name) {
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
//                                                                                   }));
//      }
//    };
//  }
}
