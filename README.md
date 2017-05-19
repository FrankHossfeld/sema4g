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
```
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
SeMa4g.Builder semagContext = SeMa4g.builder();
```

### Create a InitCommand and add it to the SeMa4g context 
Add a `InitCommanmd` to the SeMa4g context:

```Java
semagContext.addInitCommand(new InitCommand() {
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
semagContext.addInitCommand(new FinalCommand() {
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
semagContext.add(new SyncCommand() {
                       @Override
                       public void execute() {
                         // Enter here the code, that will
                         // be executed, in case the SeMa4g
                         // context will execute the command.
                       }
                     });
```

#### Type of commands
SeMa4g knows two types of commands.

##### SyncCommand
A SyncComannd is command that 


# More documentation: TO BE DONE ...


## Downloading (in progress)
Use the "Clone or download" button at the top right of this page to get the source. You can get a pre-built JAR (usable in JRE 1.7 or later) from Sonatype, or add the following Maven Central dependency:

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
