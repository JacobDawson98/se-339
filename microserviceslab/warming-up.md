1. (1 point) Open the project in your IDE and make sure you can
    get all of the microservices running. How do the
    microservices communicate with each other?
    * The microservices communicate with each other via HTTP
      requests. Those http requests are handled by each
      microservice's controller that acts as an API.
2. (1 point) Look up Constructor Injection for Java Spring on
    the Internet. How are different classes passed to each
    together (or instantiated) in Spring?
3. (1 point) Look up gradle build dependencies. Gradle is used
    primarily as a dependency management solution,
    and is used both in Spring Boot applications and in
    Android development. What is a gradle ’dependency’,
    and how does it help development?
    * A gradle dependency is a module/package that is required
      for the specific project gradle needs to build.
    * Gradle dependencies are helpful in development because
      developers can use third party/officially supported
      modules/packages in their projects. Using gradle
      dependencies allows the developer to specify which
      dependencies are necessary to build the project. Gradle
      will read this file and install the modules before
      building.
