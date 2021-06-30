# Spring Rest Template Project:

The application is a Spring Boot Rest Github api application

### Tech stack & Open-source libraries
===================
This is a basic Java Rest application projects. This template uses the following foundational technologies:

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
* 	[RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) Synchronous client to perform HTTP requests, exposing a simple, template method API over underlying HTTP client libraries such as the JDK HttpURLConnection, Apache HttpComponents, and others.

##### I used GitHub REST API service for this example project.
##### I used [jsonschema2pojo](https://www.jsonschema2pojo.org/) for creating POJOs from JSON
##### End points: 
https://api.github.com/orgs/apache/repos
https://api.github.com/repos/apache/zookeeper/contributors
https://api.github.com/users/jacopoc 

### Getting Your Development Environment Setup
#### Recommended Versions
 | Recommended | Reference
| ----------- | ---------
| Oracle Java 8 JDK | [Download](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) |
| Maven 3.8.1 or higher | [Download](https://maven.apache.org/download.cgi) | [Installation Instructions](https://maven.apache.org/install.html)|
| Spring Boot 2.5.2 or higher | [What's new](https://spring.io/blog/2021/06/24/spring-boot-2-5-2-is-now-available) 
| Spring 5.3.8 or higher | [What's new](https://spring.io/blog/2021/06/09/spring-framework-5-3-8-available-now) 

### Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.tahayavuz.apacherestapp.ApacheRestAppApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/tahayvz/apacheRestApp.git
$ cd apacheRestApp
$ mvn spring-boot:run
```

Or you can click on the `run.bat` file

### Output of application
After running the project will create a file `output.txt` in the project directory

To get the code:
-------------------
Clone the repository:

    $ git clone https://github.com/tahayvz/apacheRestApp.git

If this is your first time using Github, review https://docs.github.com/en/free-pro-team@latest/github/creating-cloning-and-archiving-repositories/cloning-a-repository to learn the basics.
