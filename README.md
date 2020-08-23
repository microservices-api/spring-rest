# Spring-rest-database
A Spring RESTful microservice

This sample contains a way of how to use Swagger documentation within Liberty or Tomcat (SpringBoot).
It also introduces the database connection with DB2 by using the JDBC datasource and the JpaRepository.


*Note:* If you don't need to store any data in the database, please take a look at this "No DB Connection version".
https://github.com/microservices-api/spring-rest/tree/NoDBConnection

## 1. Clone application
#####  Import Maven projects into WDT (WebSphere Development Tools)
1.  In the Git Repository view, expand the sample repo to see the "Working Directory" folder
2.  Right-click on this folder, and select *Copy path to Clipboard*
3.  Select menu *File -> Import -> Maven -> Existing Maven Projects*
4.  In the Root Directory textbox, Paste in the repository directory.
5.  Select *Browse...* button and select *Finish* (confirm it finds 3 pom.xml files)
6.  This will create 3 projects in Eclipse: swagger-sample, swagger-sample-application, and swagger-sample-wlpcfg

*Note:* If you did not use Eclipse/WDT to clone the git repository, follow from step 3, but navigate to the cloned repository directory rather than pasting its name in step 4.

## 2. Create a deployable WAR file
#####  Run Maven install
1. Right-click on swagger-sample/pom.xml
2. *Run As > Maven build...*
3. In the *Goals* section enter "install"
4. Click *Run*

## 3. Liberty server setting
##### [1] Create a Runtime Environment in Eclipse
1. Open the 'Runtime Explorer' view:
    * *Window -> Show View -> Other*
    * type `runtime` in the filter box to find the view (it's under the Server heading).
2. Right-click in the view, and select *New -> Runtime Environment*
3. Give the Runtime environment a name, e.g. `wlp-2017.1.0.0` if you're using the January 2017 version.
4. Either:
    * Select an existing installation (perhaps what you downloaded earlier, if you followed those instructions), or
    * select *Install from an archive or a repository* to download a new Liberty archive.
5. Follow the prompts (and possibly choose additional features to install) until you *Finish* creating the Runtime Environment

##### [2] Add the User directory from the maven, and create a server
1. Right-click on the Runtime Environment created above in the 'Runtime Explorer' view, and select *Edit*
2. Click the `Advanced Options...` link
3. If the `swagger-sample-wlpcfg` directory is not listed as a User Directory, we need to add it:
    1. Click New
    2. Select the `swagger-sample-wlpcfg` project
    3. Select *Finish*, *OK*, *Finish*
4. Right-click on the `swagger-sample-wlpcfg` user directory listed under the target Runtime Environment in the Runtime Explorer view, and select *New Server*.
5. The resulting dialog should be pre-populated with the `swaggerSample` Liberty profile server.
   The default name for this server can vary, you might also opt to rename it from the Right-click menu in the Servers view to make it easier to identify.
6. Click *Finish*

## 4. Run application on the server
##### Case 1: Running Liberty and the application from WDT
1.  Select the `swagger-sample-application` project
2.  Right-click -> *Run As... -> Run On Server*
3.  Select the appropriate server (as created above) and select *Finish*

##### Case 2: Running application with SpringBoot
1.  Select the `swagger-sample-application` project
2.  Right-click -> *Run As... -> Spring Boot App*
*Note:* The Tomcat server will be automatically started.
"Started Application in ... seconds (JVM running for ...)" message suggests the compiled war file is deployed and started successfully on the Tomcat server.
3.  Access to http://localhost:8080/swagger-ui.html
