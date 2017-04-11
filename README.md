SeMa4g
======

Description
-----------

A light-weighted framework to manage asynchronous calls in GWT.

In many cases it is neccessary to do several server calls to get a GWT application started. 

Features
--------

* init commands: commands, that will be executed on start
* final comands: commands, that will be executed after all calls have finished
* unlimited server calls
* commands can depend on other commands<br/>(A comand will not start before the depending command has finished)
* supports GWT RPC, GWT RequestBuilder and Resty-GWT
* supports synchronous code execution in a call chain
