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
