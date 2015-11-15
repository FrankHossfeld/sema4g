# SEMA4G
A light-weighted framework to manage asynchronous calls in GWT.

In same cases it is necessary that a GWT application has to do several server calls
before the application can proceed. Maybe in some cases one service depends on the 
result of another one. Solving this dependencies can be quite difficult.
 
SEMA4G helps you to manage asynchronous server calls.
 
You get:
- InitCommand that will be executed at the beginning
- FinalCommand that will be executed after all commands have completed
- AsyncCommand to do aasynchronous call
- SyncCommand to do synchronous call 

To start, download the latest release and add the sema4g-x.x.x.jar to your classpath.




