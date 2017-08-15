# SeMa4g

## Description

A light-weighted, client-sided framework to manage asynchronous calls in GWT.

In many cases it is neccessary to do several server calls, before a GWT application starts or a view can be displayed. SeMa4g will help you to get this calls managed.

The idea of SeMa4g is to use a callback proxy. The asyncronized server call will use the callback classes of SeMa4g. This allows SeMa4g to get informed if a server call has finished.

## Features
* init commands: commands, that will be executed on start
* final comands: a command, that will be executed after all calls have finished
* unlimited server calls
* commands can depend on other commands<br/>(A comand will not start before the depending command has finished)
* supports GWT RPC, GWT RequestBuilder and Resty-GWT
* supports synchronous code execution in a call chain

## Using
### Configure the Module Descriptor

Configure the GWT project to inherit the SeMa4g module.

* Insert the SeMa4g module into the project's module descriptor
```GWT
  <!-- Other module inherits -->
  <inherits name="com.google.gwt.debug.Debug"/>
```


* Depending on the type of server communication, add the corresponding SeMa4g Module:
    * GWT RPC:
    ```
    <inherits name="de.gishmo.gwt.sema4g.rpc.SeMa4gRPC"/>
    ```
    * GWT RequestBuilder:
    ```
    <inherits name="de.gishmo.gwt.sema4g.requestbuilder.SeMa4gRequestBuilder"/>
    ```
    * GWT Resty-GWT:
    ```
    <inherits name="de.gishmo.gwt.sema4g.resty.SeMa4gResty"/>
    ```


### Create a SeMa4g Context
To use SeMa4g it is neccessary to create a SeMa4g Builder, add all commands, build the context and call run.

Create a SeMa4g context:

```Java
SeMa4g.Builder sema4gContext = SeMa4g.builder();
```

### Create a InitCommand and add it to the SeMa4g context 
Add a `InitCommanmd` to the SeMa4g context:

```Java
sema4gContext.addInitCommand(new InitCommand() {
                                   @Override
                                   public void onStart() {
                                     // Enter here your code, that
                                     // should be executed when
                                     // the context is started
                                   }
                                 });
```
It is possible to add more then one ```InitCommand``` command to the SeMa4g context.

### Create a FinalCommand and add it to the SeMa4g context
Add a `FinalCommanmd` to the SeMa4g context:
```Java
sema4gContext.addFinalCommand(new FinalCommand() {
                                    @Override
                                    public void onSuccess() {
                                      // Enter here the code, that will
                                      // be executed in case the context
                                      // ended without error
                                    }

                                    @Override
                                    public void onFailure() {
                                      // Enter here the code, that will
                                      // be executed in case the context
                                      // ended with an error
                                    });
```
It is possible to add more then one ```FinalCommand``` command to the SeMa4g context. At least you need to add one ```FinalCommand```.

### Create a command and add it to the SeMa4g context
Adding a command to the SeMa4g context is quite easy:
```Java
sema4gContext.add(new SyncCommand() {
                        @Override
                        public void execute() {
                          // Enter here the code, that will
                          // be executed, in case the SeMa4g
                          // context will execute the command.
                        }
                      });
```

### Type of commands
SeMa4g knows two types of commands.

#### SyncCommands
A SyncComannd is a command that contains code which will be executed synchronously. That means, SeMa4g will call the `execute`-method of the command and execute the next command.
**Do not implement server actions in this command!**

Example:
```Java
private SeMa4gCommand createSyncCommand() {
  return new SyncCommand() {
               @Override
               public void execute() {
                 // Enter here the code, that will
                 // be executed, in case the SeMa4g
                 // context will execute the command.
               }
             };
}
```

#### AsyncCommands
A AsyncCommand is a command that contains asynchronously executed code. In this case you have to use one of the SeMa4g callback classes. This callback classes will inform SeMa4g when a server call returns from the server.

Example:
```Java
private SeMa4gCommand createAsyncCommand() {
  return new AsyncCommand() {
      // create callback
      MethodCallbackProxy<String> proxy = new MethodCallbackProxy<String>(this) {
        @Override
        protected void onProxyFailure(Method method,
                                      Throwable caught) {
          // Enter here the code, that will
          // be executed in case of failure
        }

        @Override
        protected void onProxySuccess(Method method,
                                      String response) {
          // Enter here the code, that will
          // be executed in case of success
        }
      };

      @Override
      public void execute() {
        // That's the place for the server call ...
        greetingService.greet(name, proxy);
      }
    };
  }
}
```

In case that one AsyncCommad ends in error, SeMa4g stops the excecution, waits for running commands and calls the `onFailure`-method of the FinalCommand.

In case that all commands finished without errors the `onSuccess`-method of the FinalCommand is called.

#### Conditional commands
In case the SeMa4g context is started by calling the `run`-method, the `execute`-method of all commands will be called. In some cases it might be necessary that one command needs the response of another command. To handle such things, SeMa4g offers the possibility to add depending commands to a command.

If a command depends on another command (or maybe more than one command), the execution of this command will wait until all depending commands have been successfully finished.

Example:
```Java
// the execution of comand03 will stsrt, in case that command01 and command02
// successfully finished.
SeMa4gCommand command01 = createAsyncCommand01();
SeMa4gCommand command02 = createAsyncCommand02();
SeMa4gCommand command03 = createAsyncCommand03();

sema4gContext.add(command01)
             .add(command02)
             .add(command03.dependingOn(command01, command02);
```

#### Execute the context
To execute the context, you have to build the context by calling the `build`-method and then call the `run`-method to run the context.

Example:
```Java
sema4gContext.build()
             .run();
```
#### Order of Execution
Executing the context will:
* execute all InitCommands
* execute all commands
* execute all FinalCommands

That means:
* First all InitCommands will be executed.
* After all InitCommands are executed, all commands which have no dependencies to other commands will be started. When a preconditioned command has finish, the depending command will start (in case there is no other precondition command that has not finished yet). If a command ends in error, preconditioned commands which have not been started, will not be executed!
* After all commands have finished, all FinalCommands will be executed. If one or more commands ended in error, the onFailure-method will be executed. In case that no command ended in error, the onSuccess-method will be executed.

The order of executions of the InitCommands and FinalCommands is not defined!

#### Manually stop execution
SeMa4g offers two commands, that will interrupt the execution of a running SeMa4gContext.

* `signalFinish()`-method<br/>Calling this method will stop the execution.<br/>* SeMa4g will wait until all running commands has ended<br/>* SeMa4g will not start any new command<br/>* SeMa4g will call the `onSuccess`-method of the `FinalCommand`

* `signalError()`-method<br/>Calling this method will stop the execution.<br/>* SeMa4g will wait until all running commands has ended<br/>* SeMa4g will not start any new command<br/>* SeMa4g will call the `onFailure`-method of the `FinalCommand`


#### Complex example
```Java
private void doServerCalls() {
  // the execution of comand03 will stsrt, in case that command01 and command02
  // successfully finished.
  SeMa4gCommand command01 = createAsyncCommand01();
  SeMa4gCommand command02 = createAsyncCommand02();
  SeMa4gCommand command03 = createAsyncCommand03();

  try {
    SeMa4g.builder()
          .addInitCommand(new InitCommand() {
                               @Override
                               public void onStart() {
                                 // Enter here your code, that
                                 // should be executed when
                                 // the context is started
                               })
    .addFinalCommand(new FinalCommand() {
                           @Override
                           public void onSuccess() {
                             // Enter here the code, that will
                             // be executed in case the context
                             // ended without error
                           }

                           @Override
                           public void onFailure() {
                             // Enter here the code, that will
                             // be executed in case the context
                             // ended with an error
                           })
    .add(command01)
    .add(command02)
    .add(command03.dependingOn(command01, command02)
    .build()
    .run();
  } catch (SeMa4gException e) {
    // Ups, something wrong with the context ...
  }
}

private SeMa4gCommand createAsyncCommand01() {
  return new AsyncCommand() {
      // create callback
      MethodCallbackProxy<String> proxy = new MethodCallbackProxy<String>(this) {
        @Override
        protected void onProxyFailure(Method method,
                                      Throwable caught) {
          // Enter here the code, that will
          // be executed in case of failure
          // ended with an error
        }

        @Override
        protected void onProxySuccess(Method method,
                                      String response) {
          // Enter here the code, that will
          // be executed in case of success
          // ended without error
       }
      };

      @Override
      public void execute() {
        // That's the place to do the server call ...
        greetingService.greet(name, proxy);
      }
    };
  }
}

private SeMa4gCommand createAsyncCommand02() {
  return new AsyncCommand() {
      // create callback
      MethodCallbackProxy<String> proxy = new MethodCallbackProxy<String>(this) {
        @Override
        protected void onProxyFailure(Method method,
                                      Throwable caught) {
          // Enter here the code, that will
          // be executed in case of failure
           // ended with an error
       }

        @Override
        protected void onProxySuccess(Method method,
                                      String response) {
          // Enter here the code, that will
          // be executed in case of success
          // ended without error
        }
      };

      @Override
      public void execute() {
        // That's the place to do the server call ...
        greetingService.greet(name, proxy);
      }
    };
  }
}

private SeMa4gCommand createAsyncCommand02() {
  return new AsyncCommand() {
      // create callback
      MethodCallbackProxy<String> proxy = new MethodCallbackProxy<String>(this) {
        @Override
        protected void onProxyFailure(Method method,
                                      Throwable caught) {
          // Enter here the code, that will
          // be executed in case of failure
          // ended with an error
       }

        @Override
        protected void onProxySuccess(Method method,
                                      String response) {
          // Enter here the code, that will
          // be executed in case of success
          // ended without error
        }
      };

      @Override
      public void execute() {
        // That's the place to do the server call ...
        greetingService.greet(name, proxy);
      }
    };
  }
}
```

## Downloading
Use the "Clone or download" button at the top right of this page to get the source. You can get a pre-built JAR (usable in JRE 1.7 or later) from Sonatype (in progress), download the jar form [here](https://github.com/FrankHossfeld/sema4g/releases) , or add the following Maven Central dependency:

```XML
<dependency>
    <groupId>de.gishmo.gwt</groupId>
    <artifactId>sema4g</artifactId>
    <version>LATEST</version>
</dependency>
```

## Example
SeMa4g provides an example web application to show some common use cases.

To run the example use:
```
mvn install
```
from the pom to install SeMa4g in your local repository (as long as SeMa4g is not provided via Maven Central.)

To run the example web application, use
```
mvn gwt:devmode
```
from the pom inside the sema4g-example directory.

In case the web applicaiton is running, select a test and press the 'Run'-Button.

### Test Case Description

For all test cases:

* The InitCommand will open a popup.
* The FinalCommand will close the popup and show a JavaScript alert depending on the server responses. If at least one command ends in error, the context will also ends in error.
* The createAsyncCommand-method creates a server call. It has two parameters. The first parameter is the name of the command and the second parameter is the duration, the server will wait. This method will look different for your calls.
* The createAsyncFailureCommand-method has two parameters. The first parameter is the name of the command and the second parameter is the duration, the server will wait. This service will throw an exception on the server side.
* The createSyncCommand-method has two parameters. The first parameter is the name of the command and the second parameter is the duration, the client will wait. This service does not call the server!

#### Test Case 01

A single service call with a InitCommand and a FinishCommand.

* service 'one': the waiting duration on the server is: 2500 ms.

The context will end successfully!

![Flow Test Case 03](https://github.com/FrankHossfeld/sema4g/blob/master/etc/graphics/TestaCase01.png)

#### Test Case 02

Several service calls with no dependencies and a InitCommand and a FinishCommand.

* service 'one': the waiting duration on the server is: 2500 ms.
* service 'two': the waiting duration on the server is: 125 ms.
* service 'three': the waiting duration on the server is: 1250 0ms.
* service 'four': the waiting duration on the server is: 8750 ms.
* service 'five': the waiting duration on the server is: 125 ms.

The context will successfully end!

![Flow Test Case 03](https://github.com/FrankHossfeld/sema4g/blob/master/etc/graphics/TestaCase02.png)


#### Test Case 03

Several service calls with a InitCommand and a FinishCommand and dependencies.

* service 'one': the waiting duration on the server is: 9250 ms. The service depends on the execution of service 'three' and 'ten'.
* service 'two': the waiting duration on the server is: 3255 ms.
* service 'three': the waiting duration on the server is: 125 ms. The service depends on the execution of service 'five'.
* service 'four': the waiting duration on the server is: 52000 ms. The service depends on the execution of service 'five'.
* service 'five': the waiting duration on the server is: 250 ms. The service depends on the execution of service 'eight' and 'nine'.
* service 'six': the waiting duration on the server is: 6000 ms.
* service 'seven': the waiting duration on the server is: 7250 ms.
* service 'eight': the waiting duration on the server is: 2400 ms.
* service 'nine': the waiting duration on the server is: 5100 ms.
* service 'ten': the waiting duration on the server is: 200 ms.

The context will end successfully.

![Flow Test Case 03](https://github.com/FrankHossfeld/sema4g/blob/master/etc/graphics/TestaCase03-100.png)

#### Test Case 04

Several service calls with different duration on the server. This test case chows the behaviour in case of a cycle dependencies. Exception expected. **No service will be called**.

#### Test Case 05

A single service call (service no. 13) which will fail and throw an exception.

* service 'one': the waiting duration on the server is: 2500 ms.

The context will end in error because of a server exception!

#### Test Case 06

Several service calls with a InitCommand and a FinishCommand and dependencies. One service (service no. 13) will fail and throw an exception.

* service 'one': the waiting duration on the server is: 9250 ms.
* service 'two': the waiting duration on the server is: 3255 ms. The service depends on the execution of service 'six'.
* service 'three': the waiting duration on the server is: 125 ms. The service depends on the execution of service 'five' and 'eight'.
* service 'four': the waiting duration on the server is: 52000 ms. The service depends on the execution of service 'two'.
* service 'five': the waiting duration on the server is: 250 ms.
* service 'six': the waiting duration on the server is: 6000 ms. The service depends on the execution of service 'eight', 'nine' and 'thirteen'. **This command will not be executed due to an error in command 'thirteen'**.
* service 'seven': the waiting duration on the server is: 7250 ms.
* service 'eight': the waiting duration on the server is: 2400 ms.
* service 'nine': the waiting duration on the server is: 5100 ms.
* service 'ten': the waiting duration on the server is: 200 ms.
* service 'thirteen': the waiting duration on the server is: 3000 ms. This service will fail on the server side. (throw an exception)

The context will end in error. Waiting commands, because of the dependencies to command 'thirteen', will not be started!

#### Test Case 07

Several asynchronous and one synchronous service calls with a InitCommand and a FinishCommand and dependencies.

* service 'one': the waiting duration on the server is: 9250 ms.
* service 'two': the waiting duration on the server is: 12000 ms. **This is a synchronous command**
* service 'three': the waiting duration on the server is: 125 ms. The service depends on the execution of service 'two'.

The context will end successfully.


## License
The MIT License (MIT)

Copyright (c) 2015 - 2017 Frank Hossfeld

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
