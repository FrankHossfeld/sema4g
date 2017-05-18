SeMa4g
======

Description
-----------

A light-weighted framework to manage asynchronous calls in GWT.

In many cases it is neccessary to do several server calls before a GWT application starts or a view can be displayed. sema4g will help you to get this calls managed. 

Features
--------

* init commands: commands, that will be executed on start
* final comands: commands, that will be executed after all calls have finished
* unlimited server calls
* commands can depend on other commands<br/>(A comand will not start before the depending command has finished)
* supports GWT RPC, GWT RequestBuilder and Resty-GWT
* supports synchronous code execution in a call chain

Using
-----

To use SeMa4g it is neccessary to create a SeMa4g Builder, add all commands, build the context and call run.

Create a SeMa4g context and commands:

`````
SeMa4g.Builder semagLoadContext = SeMa4g.builder()
                                        .addInitCommand(createInitCommand())
                                              .addFinalCommand(createFinalCommand(historyName,
                                                                                  params))


````









Downloading
-----------

Use the "Clone or download" button at the top right of this page to get the source. You can get a pre-built JAR (usable in JRE 1.7 or later) from Sonatype, or add the following Maven Central dependency:

<dependency>
    <groupId>de.gishmo.gwt</groupId>
    <artifactId>sema4g</artifactId>
    <version>LATEST</version>
</dependency>

License
-------

The MIT License (MIT)

Copyright (c) 2016 Frank Hossfeld

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
