# spring-microservices

Microservices is an architectural style, alternative to traditional monolithic applications. 
Decomposition of single system into a suite of small services, each running as independent processes and intercommunicating via open protocols.

Note: microservices can be seperately written, deployed, scaled and maintained. 

Monolithic example
------------------
Consider a monolith shopping cart application. It has the following functional areas.
product search
product catalog
reviews
inventory management
shopping kart
checkout
Fullfillment

A typical monolithic application contains layered approach with controllers, services and DAO's for each functionality and UI layer(jsps).

why is the paradigm shift?
Ans: Ten years ago applications have only web interfaces. With advancement in technology, there are wide variety of clients like mobile,tablets, gaming consoles and TV's etc. The controllers that were defined are not suitable for these latest devices. Also persitence storages have changed a lot like document based, key/value based, column based etc unlike one size fits all solutions like RDBMS. For ex, for the above monolith application modules, for product search elastic search may be more efficient. similarly for, reviews module mongo db may be the better solution.

Note: It's very difficult for a monolithic application written in a single language to interact with plenty of persitent storages.

Advantages of Monolithic
------------------------
1)Easy to understand the architecture, but not easy to digest the codebase
2)Modularity is based on single program language construct. i.e. based on packages, classes, functions etc.

Challenges with Monolithic
--------------------------
1)Single codebase is difficult to manage when the size is beyond certain limit.
2)If the codebase is split into multiple, then assembling multiple codebases into a release artifact needs a seperate team to manage the releases.
3)If a particular language is not the best choice(for example if a module needs sharepoint access, then .net is the better choice), then we are struck.
4)cannot independently deploy a single change to single component.

Microservices
-------------
Microservices are independent applications built around the functional areas of our system.
Microservices are easy to scale. Each microservice is a small application, each one can have it's own DB system based on technology(or business need) that is best suited for the given scenario. 

Note:
Each of the services have to be accessed by different kinds of clients like mobile,tablets etc accessing directly can lead to a number of issues. We usually have a component called API gateway between client and services. It's purpose is to be present an easy to use interface for the clients. API gateway can do additional things like authentication, caching, response transforamtion etc.

Microservices communicate to each other via lightweight protocols like HTTP,TCP,UDP,messaging etc.
Microservices are not based on technology stack, they are based on business functionality. It is suitable for cross functional teams.
Microservices are easy to comprehend,alter,test,version,deploy,manage,replace etc.

Note: Microservices architectural if of decentralized governance. Services evolve at different speeds, deployed and managed according to different needs.

Note: Microservices has to be tolerant readers. i.e. if a new field appears in response, it should not be considered as failure.

Microservices are the best when comes to the use of polyglot persitence. i.e. instead of one size fits all DB, each microservice can go with its own sql/nosql technology suited for the scenario. 

Note: With microservices we can't rely on traditional ACID transactions, instead we should rely on eventual consistency.

Challenges with Microservices
-----------------------------
1)coordination of services is not easy
2)Services may not be available
3)More monitoring is needed
4)Remote calls are expensive than in-process calls
5)no transactions
6)features span multiple services, so change management is difficult
7)Refactoring of monolith across module boundaries is difficult. sometimes, the data needed for one microservice may be present in another.

Fallacies of distributed computing
----------------------------------
Network is reliable
Latency is zero
Bandwidth is infinite
Network is secure
Toplogy doesn't change
There is one administrator
Transport cost is zero
Network is homogenious

How to break Monolith into microservices
----------------------------------------
Primary consideration is business functionality: 
This can be either of the following.
1)Noun-based(catalog,cart,customer)
2)verb-based(search,checkout,shipping)
3)Single responsibility principle(SRP)
4)Bounded context(DDD- domain driven design)

Difference between SOA and microservices
----------------------------------------
SOA is about integrating various enterprise applications, where as microservices are mainly about decomposing single applications.
SOA relies on archestration, Microservices rely on choreography.
SOA rely on smart integration and dumb services where as microservice rely on smart services and dumb integration technology.

Spring Boot
-----------
If you want to start a new Spring project, it takes about one or two days only to setup the project. Most of the setup time is consumed in searching for the dependencies either for maven or gradle and choosing proper versions etc. 

Spring Boot is designed to address the above concerns. It is an opinionated approach to configuration and defaults. It basically provides you intelligent defaults automatically. For ex, if you want to start a web project, spring boot configures embedded tomcat by default.

In a nutshell, spring boot offers the following.
1)Easier dependency management
2)Automatic configuration and reasonable defaults
3)Different build and deployment options

Note: Spring boot is not a code generator. Also, it is not a plugin to any specific IDE. you can use it with any IDE.

If you create a spring starter project using sts or with spring initializer the following things happen.
1)Project structure is be created(Mostly folder structures like src/main/java,src/main/resources,src/main/test etc)
2)Application class and a Test class is created
3)Maven pom file is created with spring boot dependencies

Note: spring-boot-starter-parent is the main configuration with spring boot version details. For a simple spring boot project, it creates two dependencies.
1)spring-boot-starter
2)spring-boot-starter-test

Note: If you have to start any simple spring project with maven, you have to provide atleast 10 to 20 dependencies. With spring boot you just have to provide the above mentioned two dependencies. Those two dependencies bring lot of transitive dependencies. you can overwrite any of the depencies you want. For ex, spring boot uses junit, mockito , hamcrest(assertion library) etc as default. you can overwrite any of these depending on the project or business need.

Running spring boot application is like running any java program. It contains one line in main method. 

	SpringApplication.run(SpringbootdemoApplication.class, args);
	
SpringApplication is a class from the package org.springframework.boot.SpringApplication, which will start the spring application context. SpringApplication class needs configuration to run. It generally takes the same class in which it is running. It contains a single configuration annotation @SpringBootApplication.

SpringBootApplication annotation is a combination of three annotations. 
1)@Configuration(used to define additional java configuration classes)
2)@ComponentScan(used typically to scan the stereo type annatations like @Component, @Service and @Repository)
3)@EnableAutoConfiguration

@EnableAutoConfiguration: This is the most import configuration in the spring boot application. It scans the classpath and creates missing beans based on intelligent defaults. For example, if it finds a web application jars like tomcat, it creates web application context. Similary, if it finds database jars, it will create transaction manager bean.

