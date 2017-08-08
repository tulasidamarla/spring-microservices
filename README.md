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

Webapplication with Spring boot
-------------------------------
To create a webapplication with spring boot two changes are required. 

1)change the pom.xml starter dependency to web.
	
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	
Note: This dependency downloads jars related to web like tomcat-embed-*.jar, spring-web.jar, spring-webmvc.jar etc. 

2)create a controller

	@Controller
	public class HelloController {
		@RequestMapping("/hi")
		public @ResponseBody String greeting(){
			return "hello world!";
		}
	}

Note: @ResponseBody indicates that the response is not tied to any view.
Note: @EnableAutoConfiguration annotations looks at the classpath and identifies the spring-webmvc jar and creates beans DispatcherServelt,Handler mappings, Adapters, view resolver etc.
Note: Springboot creates a jar file with embedded tomcat instance and runs it.

Creating a war with Spring Boot
-------------------------------
To convert from jar to war	
1)change packaging in pom.xml to war
2)Extend the SpringBootServletIntitalizer

	@SpringBootApplication
	public class SpringbootdemoApplication extends SpringBootServletInitializer{
		public static void main(String[] args) {
			SpringApplication.run(SpringbootdemoApplication.class, args);
		}

		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return builder.sources(SpringbootdemoApplication.class);
		}
	}

Creating Webpages
-----------------
Spring mvc supports wide variety of view options like Jsp's, Thymeleaf, Freemaker, velocity etc.
By default spring boot creates InterenalViewResolver bean for Jsp. If thymeleaf is present on the classpath, then ThymeleafViewResolver bean is created.

Note: Thymeleaf is a web templating technology. This is also natural templating, because it uses regular html, css and javascript.

To use thymeleaf, add the following thymeleaf dependency.
	
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		
Note: To know all the different starters available with spring boot, please go to the spring reference and search for the section called "starters" or use this link:
https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-starter		
As Spring boot is opinionated, it expects the folder "templates" for thymeleaf under src/main/resources folder.


Note: If you want to use use jsp's instead of thymeleaf, few changes are required.
1)Add the following dependencies to pom.xml
		
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>

2)Write a configuration file like this if auto configuration does not work:

	@Configuration
	@EnableWebMvc
	public class SpringMVCConfig {

		@Bean
		public InternalResourceViewResolver resolver() {
			InternalResourceViewResolver vr = new InternalResourceViewResolver();
			vr.setPrefix("/WEB-INF/views/");
			vr.setSuffix(".jsp");
			return vr;
		}
	}

3)write your jsp file in the /WEB-INF/views directory as mentioned in the above code. 

Spring & Rest
-------------
Rest capability is built into Spring MVC. It uses domain objects as parameters and return values.
Use the annotations @RequestBody and @ResponseBody for request parameters and return values.

Note: Spring MVC automatically handles XML/Json conversion based on converters available on classpath.

Write some domain classes to return response in json format. Here is the sample code for controller.

	@RestController
	public class TeamController {
		
		private Team team;
		
		@PostConstruct
		public void init(){
			Set<Player> players = new HashSet<>();
			
			players.add(new Player("Dravid","Rajastan"));
			players.add(new Player("Sachin","Mumbai"));
			team = new Team("India", "IPL", players);
		}
		
		@RequestMapping("/hi")
		public Team greeting(){
			return team;
		}

	}

Note: Spring MVC invokes correct HttpMessageConverters based on the requested format and jars on the classpath.

XML response format
-------------------
For XML response let's annotate domain classes with JAXB annotations. JAXB is part of Java SE.

When application starts spring creates message converters for JAXB based on classpath jars. So XML or Json is returned based on the request. Here is the change needed for domain object.

	@XmlRootElement
	public class Team {
		String name;
		String location;
		String mascotte;
		
		Set<Player> players;
	}

Note: @XmlRootElement is the only annotation needed for this example. The rest of the class is ignored for symplicity. 

Note: If you access the application using localhost:8080/hi.xml, xml response will come.
If you try to access using localhost:8080/hi.json, json response will come.

Spring Boot with JPA
--------------------
Spring boot has starter for jpa. i.e. sprint-boot-starter-data-jpa. It adds the following dependencies.
1)Spring JDBC/transaction management
2)Spring ORM
3)Hibernate/Entity managers
4)Spring data jpa 

Note: It won't add any database driver.

A Typical webapplication contains 3 layered architecture. It has Controller, Service and DAO layers. Rest Controllers provide CRUD interface to clients. DAO provide CRUD interface to DB.

As we know DAO layer contains lot of code which is common to CRUD operations. Spring data abstracts away all the common implementation by providing dynamic repositories. All we need to do is to provide interface, spring data dynamically implements JPA, MongoDB, Gemfire etc.

Note: Spring data has lot of sub projects for various data stores like RDBMS, Mongo, Solr,redis etc.

Spring Data JPA Demo
--------------------
This Demo uses hsqldb driver. The following steps are required.
1)Add the below maven dependencies.
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.hsqldb</groupId>
			<artifactId>hsqldb</artifactId>
		</dependency>
		
2)Create an interface say TeamDAO and extend from CRUDRepository
	
	public interface TeamDAO extends CrudRepository<Team, Long> {
		Team findByName(String name);
	}

Note: Spring data dynamically implements repositories with methods findBy[Fields][Operations],save,delete,findAll etc.
Note: Datasource and transaction management are all handled

3)Save some records immediately after startup of the container.For ex,

	@SpringBootApplication
	public class SpringbootdemoApplication {
		
		@Autowired
		TeamDAO teamDAO;

		public static void main(String[] args) {
			SpringApplication.run(SpringbootdemoApplication.class, args);
		}

		@PostConstruct
		public void init(){
			Set<Player> players = new HashSet<>();
			players.add(new Player("Dravid","Rajastan"));
			players.add(new Player("Sachin","Mumbai"));
			Team team = new Team("India", "IPL", players);
			teamDAO.save(team);
		}
	}
	
4)Annotate the domain objects with JPA. For ex,

	@Entity
	public class Player {

		@Id
		@GeneratedValue
		Long id;
		String name;
		String position;
	}

	@Entity
	public class Team {

		@Id
		@GeneratedValue
		Long id;
		String name;
		String location;
		String mascotte;
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name="teamId")
		Set<Player> players;
	}

5)change the controller to take some path variable and return the data.

	@RestController
	public class TeamController {
		@Autowired
		private TeamDAO teamDAO;

		@RequestMapping("/teams/{name}")
		public Team greeting(@PathVariable String name){
			return teamDAO.findByName(name);
		}
	}

Note: try the URL localhost:8090/teams/IPL to see the response.	

Spring Data Rest
----------------
Often applications expose DAO methods as REST resources. Controllers and Services will have some boilerplate code. If we use spring data rest, it handle these boilerplate code, so no need to write controller and service classes.

