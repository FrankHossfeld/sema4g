package de.gishmo.gwt.sema4g.test;

import de.gishmo.gwt.sema4g.client.command.FinalCommand;
import de.gishmo.gwt.sema4g.client.command.InitCommand;
import de.gishmo.gwt.sema4g.client.command.SeMa4gCommand;
import de.gishmo.gwt.sema4g.client.command.SyncCommand;

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
