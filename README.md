# SeMa4g

## Description

A light-weighted framework to manage asynchronous calls in GWT.

In many cases it is neccessary to do several server calls before a GWT application starts or a view can be displayed. sema4g will help you to get this calls managed. 

The idea of SeMa4g is to use a callback proxy. The asyncronized server call will use the callback classes for SeMa4g. This allows SeMa4g to get informed if a server call has finished.

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
I is possible to add more then one ```InitCommand``` command to the SeMa4g context.

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
SeMa4g allows only one `FinalCommanmd` adding to the SeMa4g context.

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

#### SyncCommand
A SyncComannd is a command that contains code which will be executed synchronously. That means, SeMa4g will call the `execute`-method of the cammand and execute the next command.

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

#### AsyncCommand
A AsyncCommand is a command that contains asynchronously executed code. In this case you have to use one of SeMa4g callback classes. This callback classes will inform SeMa4g when a server call returns from the server.

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

#### Execute the context
To execute te context, you have to build the context by calling the `build`-method and then call the `run`-method.

Example:
```Java
sema4gContext.build()
             .run();
```


#### Conditional commmands
In case the SeMa4g context is startet by calling the `run`-method, the `execute`-method of all commands will be called. In some cases it might be necessary that one command needs the response of another command. To handle such things, SeMa4g offers the possibility to add depending commands to a command.

If a command depends on another command (or maybe more than one command), the eecution of this command will wait until all depending commands have been successfully finished.

Example:
```Java
// the execution of comand03 will stsrt, wenn command01 and command02
// successfully finished.
SeMa4gCommand command01 = createAsyncCommand01();
SeMa4gCommand command02 = createAsyncCommand02();
SeMa4gCommand command03 = createAsyncCommand03();

sema4gContext.add(command01)
             .add(command02)
             .add(command03.dependingOn(command01, command02);
```

#### Manually stop execution
SeMa4g offers two commands, that will interrupt the execution of a runnung SeMa4gContext.

* `signalFisnish()`-method<br/>Calling this method will stop the execution.<br/>* SeMa4g will wait until all running commands has ended<br/>* SeMa4g will not start any new command<br/>* SeMa4g will call the `onSuccess`-mthod of the `FinalCommand`

* `signalError()`-method<br/>Calling this method will stop the execution.<br/>* SeMa4g will wait until all running commands has ended<br/>* SeMa4g will not start any new command<br/>* SeMa4g will call the `onFailure`-mthod of the `FinalCommand`


#### Complex example
```Java
private void doServerCalls() {
  // the execution of comand03 will stsrt, wenn command01 and command02
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

private SeMa4gCommand createAsyncCommand02() {
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

private SeMa4gCommand createAsyncCommand02() {
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

## Downloading
Use the "Clone or download" button at the top right of this page to get the source. You can get a pre-built JAR (usable in JRE 1.7 or later) from Sonatype (in progress), download the jar form [here](https://github.com/FrankHossfeld/sema4g/releases) , or add the following Maven Central dependency:

```
<dependency>
    <groupId>de.gishmo.gwt</groupId>
    <artifactId>sema4g</artifactId>
    <version>LATEST</version>
</dependency>
```

## License
The MIT License (MIT)

Copyright (c) 2015 - 2017 Frank Hossfeld

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
