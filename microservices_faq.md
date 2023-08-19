## DORA

- DORA metrics are used by DevOps teams to measure their performance and find out whether they are “low performers” to “elite performers”. 
- The four metrics used are 
  - deployment frequency (DF), 
  - lead time for changes (LT), 
  - meantime to recovery (MTTR), and 
  - change failure rate (CFR).
  
## Micro services vs SOA

- Inter-service communication
  - SOA
    - SOA applications typically use heavyweight technologies such as SOAP and other WS* standards.
    - They often use an ESB, a smart pipe that contains business and message-processing logic to integrate the services.
  - Micro services
    - microservice architecture tend to use lightweight, open source technologies. 
    - The services communicate via dumb pipes, such as message brokers or lightweight protocols like REST or gRPC.

- Data
  - SOA applications typically have a global data model and share databases.
  - In the microservice architecture each service has its own database.

- Service
  - SOA
    - SOA is typically used to integrate large, complex, monolithic applications.
    - SOA application usually consists of a few large services  
  - Microservices
    - Microservices are much smaller.
    - Microservices typically consists of dozens or hundreds of smaller services.

### Microservice architecture pattern language

- The pattern language consists of several groups of patterns.
- The application architecture pattern group consists of Monolithic architecture pattern and the Microservice architecture pattern.
- The patterns are also divided into three layers:
  - `Infrastructure patterns:` These solve problems that are mostly infrastructure issues outside of development.
  - `Application infrastructure:` These are for infrastructure issues that also impact development.
  - `Application patterns:` These solve problems faced by developers.

![Architecture patterns](images/architecture_pattern_language.png)

### PATTERNS FOR DECOMPOSING AN APPLICATION INTO SERVICES

- There are two decomposition patterns: 
  - Decompose by business capability, which organizes services around business capabilities.
  - Decompose by subdomain, which organizes services around domain-driven design (DDD) subdomains.
### COMMUNICATION PATTERNS

- An application built using the microservice architecture is a distributed system.
- Interprocess communication (IPC) is an important part of the microservice architecture.
  - Communication style—What kind of IPC mechanism should you use?
  - Discovery—How does a client of a service determine the IP address of a service
  instance so that, for example, it makes an HTTP request?
  - Reliability—How can you ensure that communication between services is reli-
  able even though services can be unavailable?
  - Transactional messaging—How should you integrate the sending of messages and
  publishing of events with database transactions that update business data?
  - External API—How do clients of your application communicate with the services?  

### DATA CONSISTENCY PATTERNS FOR IMPLEMENTING TRANSACTION MANAGEMENT

- Having a database per service introduces some significant issues.
- The traditional approach of using distributed transactions (2PC) isn’t a viable option for a modern application. 
- An application needs to maintain data consistency by using the Saga pattern.

### PATTERNS FOR QUERYING DATA IN A MICROSERVICE ARCHITECTURE

- Issue with using a database per service is that some queries need to join data that’s owned by multiple services. 
- Sometimes we can use the API composition pattern, which invokes the APIs of one or more services and aggregates results. 
- Sometimes, we must use the Command query responsibility segregation (CQRS) pattern, which maintains one or more easily queried replicas of the data.

### SERVICE DEPLOYMENT PATTERNS

- Deploying a microservice is much more complex than a monolith.
- There may be tens or hundreds of services that are written in a variety of languages and frameworks.
- Ideally, we should use a deployment platform that provides the developer with a simple UI (command-line or GUI) for deploying and managing their services. 
  - The deployment platform will typically be based on virtual machines (VMs), containers, or serverless technology.

### OBSERVABILITY PATTERNS PROVIDE INSIGHT INTO APPLICATION BEHAVIOR

- Understanding and diagnosing problems in a microservice architecture is complicated.
- A request can bounce around between multiple services before a response is finally returned to a client.
- Similarly, problems with latency are more difficult to diagnose because there are multiple suspects.
- We can use the following patterns to design observable services:
  - `Health check API:` Expose an endpoint that returns the health of the service.
  - `Log aggregation:` Log service activity and write logs into a centralized logging server, which provides searching and alerting.
  - `Distributed tracing:` Assign each external request a unique ID and trace requests as they flow between services.
  - `Exception tracking:` Report exceptions to an exception tracking service, which de-duplicates exceptions, alerts developers, and tracks the resolution of each exception.
  - `Application metrics:` Maintain metrics, such as counters and gauges, and expose them to a metrics server.
  - `Audit logging:` Log user actions.

### PATTERNS FOR THE AUTOMATED TESTING OF SERVICES

- The microservice architecture makes individual services easier to test because they’re much smaller than the monolithic application.
- Here are patterns for simplifying testing by testing services in isolation:
  - `Consumer-driven contract test:` Verify that a service meets the expectations of its clients.
  - `Consumer-side contract test:` Verify that the client of a service can communicate with the service.
  - `Service component test:` Test a service in isolation.
  
### PATTERNS FOR HANDLING CROSS-CUTTING CONCERNS

- In a microservice architecture, there are numerous concerns that every service must implement, including the observability patterns and discovery patterns.
- It must also implement the Externalized Configuration pattern, which supplies configuration parameters such as database credentials to a service at runtime. 
- Apply the Microservice Chassis pattern and build services on top of a framework that handles these concerns instead of reimplementing these from scratch.

### SECURITY PATTERNS

- In a microservice architecture, users are typically authenticated by the API gateway.
- It must pass information about the user, such as identity and roles, to the services it invokes.
- A common solution is to apply the Access token pattern.
  - The API gateway passes an access token, such as JWT (JSON Web Token), to the services, which can validate the token and obtain information about the user.


## Defining an application’s microservice architecture

- Defining microservice architecture is a three-step process.
  - Defining system operations.
    - It’s either a command, which updates data, or a query, which retrieves data.
    - The behavior of each command is defined in terms of an abstract domain model, which is also derived from the requirements.
  - Defining services.
    - This step is to determine the decomposition into services.
      - One strategy is to define services corresponding to business capabilities
      - Another strategy is to organize services around domain-driven design subdomains.
  - Defining service apis and collaborations.
    - Assign each system operation identified in the first step to a service.
      - A service might implement an operation entirely by itself.
      - It might need to collaborate with other services.

![Microservices architecture](images/microservices_architecture.png)

### Obstacles to use decomposition

- Network latency 
- Reduced availability due to synchronous communication 
- Maintaining data consistency across services
- Obtaining a consistent view of the data
- God classes preventing decomposition
- `TODO: Write complete notes on the above topic from the book`.

### Identifying system operations

- This is a two-step process.
  - The first step creates the high-level domain model consisting of the key classes that provide a vocabulary with which to describe the system operations.
  - The second step identifies the system operations and describes each one’s behavior in terms of the domain model.
  - The domain model is derived primarily from the nouns of the user stories, and the sys- tem operations are derived mostly from the verbs.
  
### Creating High level domain

- A domain model is created using standard techniques such as analyzing the nouns in the stories and scenarios and talking to the domain experts.
- We can analyze the story and expand that into user scenarios. 
- For ex, Place order story is divided into following user stories.

```
Given a consumer
  And a restaurant
  And a delivery address/time that can be served by that restaurant
  And an order total that meets the restaurant's order minimum
When the consumer places an order for the restaurant
Then consumer's credit card is authorized
  And an order is created in the PENDING_ACCEPTANCE state
  And the order is associated with the consumer
  And the order is associated with the restaurant
```
- The nouns in the above user scenario are Consumer, Order, Restaurant, and CreditCard.
- The Accept order story can be written like this.

```
Given an order that is in the PENDING_ACCEPTANCE state
  and a courier that is available to deliver the order
When a restaurant accepts an order with a promise to prepare by a particular
     time
Then the state of the order is changed to ACCEPTED
  And the order's promiseByTime is updated to the promised time
  And the courier is assigned to deliver the order
```
- This above scenario suggests the existence of Courier, Delivery, MenuItem and Address classes. 

![High level domain](images/high_level_domain.png)

- The responsibilities of each class are as follows:
  - `Consumer:` A consumer who places orders.
  - `Order:` An order placed by a consumer. It describes the order and tracks its status.
  - `OrderLineItem:` A line item of an Order.
  - `DeliveryInfo:` The time and place to deliver an order.
  - `Restaurant:` A restaurant that prepares orders for delivery to consumers.
  - `MenuItem:` An item on the restaurant’s menu.
  - `Courier:` A courier who deliver orders to consumers. Tracks the availability of the courier and their current location.
  - `Address:` The address of a Consumer or a Restaurant.
  - `Location:` The latitude and longitude of a Courier.
  
### DEFINING SYSTEM OPERATIONS

- There are two types of system operations:
  - Commands—System operations that create, update, and delete data.
  - Queries—System operations that read (query) data.

#### Commands

- A good starting point to identifying system commands is by analyzing verbs in the user stories.

```
Actor         Story                     Command                       Description

Consumer      Create Order              createOrder()                 Creates an order
Restaurant    Accept Order              acceptOrder()                 Indicates that the restaurant has accepted the order and is committed to preparing it by the indicated time
Restaurant    Order Ready for pickup    noteOrderReadyForPickup()     Indicates that the order is ready for pickup.
Courier       Update Location           noteUpdatedLocation()         Updates the current location of the courier.
Courier       Delivery pickedup         noteDeliveryPickedUp()        Indicates that the courier has pickedup the order.
Courier       Delivery delivered        noteDeliveryDelivered()       Indicates that the courier has delivered the order.
```

- A command has a specification that defines its parameters, return value, and behavior in terms of the domain model classes. 
- Here is the createOrder() system operation.

```
Operation               createOrder (consumer id, payment method, delivery address, delivery time, restaurant id, order line items)
Returns                 orderId, ...
Preconditions           The consumer exists and can place orders.
                        The line items correspond to the restaurant’s menu items.
                        The delivery address and time can be serviced by the restaurant.
Post-conditions         The consumer’s credit card was authorized for the order total.
                        An order was created in the PENDING_ACCEPTANCE state.
```

- The pre-conditions mirror the givens in the Place Order user scenario described earlier.
- The post-conditions mirror the `thens` from the scenario.
- When a system operation is invoked it will verify the preconditions and perform the actions required to make the post-conditions true.
- Here’s the specification of the acceptOrder() system operation:
```
Operation               acceptOrder(restaurantId, orderId, readyByTime)
Returns                 -
Preconditions           - The order.status is PENDING_ACCEPTANCE.
                        - A courier is available to deliver the order.
Post-conditions         - The order.status was changed to ACCEPTED.
                        - The order.readyByTime was changed to the readyByTime.
                        - The courier was assigned to deliver the order.
```

#### Queries

- Besides implementing commands, an application must also implement queries.
- The queries provide the UI with the information a user needs to make decisions.
- For example, the flow when a consumer places an order:
  - User enters delivery address and time.
  - System displays available restaurants.
  - User selects restaurant.
  - System displays menu.
  - User selects item and checks out.
  - System creates order.
- This user scenario suggests the following queries:
  - findAvailableRestaurants(deliveryAddress, deliveryTime)
    - It is probably the most architecturally significant.
    - It’s a complex query involving geosearch.
  - findRestaurantMenu(id)
  
## Define services by applying the Decompose by Business capabilities pattern

- The set of capabilities for a given business depends on the kind of business.
  - For example, the capabilities of an insurance company typically include Underwriting, Claims management, Billing, Compliance, and so on.
  
#### Identifying Business capabilities

- An organization’s business capabilities are identified by analyzing the organization’s purpose, structure, and business processes.
- Its specification consists of various components, including inputs, outputs, and service-level agreements.
- A business capability is often focused on a particular business object.
- The business capabilities for FTGO include the following:

- Supplier management
  – Courier management —Managing courier information
  – Restaurant information management—Managing restaurant menus and other information, including location and open hours
- Consumer management—Managing information about consumers
- Order taking and fulfillment
  – Order management—Enabling consumers to create and manage orders
  – Restaurant order management—Managing the preparation of orders at a restaurant
  – Logistics
  – Courier availability management—Managing the real-time availability of couriers to delivery orders
  – Delivery management —Delivering orders to consumers
- Accounting
  – Consumer accounting —Managing billing of consumers
  – Restaurant accounting —Managing payments to restaurants
  – Courier accounting —Managing payments to couriers
- Most top-level capabilities are decomposed into sub-capabilities. 
  - For example, Order taking and fulfillment is decomposed into five sub-capabilities.

### Business capabilities to services

- The decision of which level of the capability hierarchy to map to services, because is somewhat subjective.

![Business capabilities to services](images/business_2_services.png)

- The services defined above may evolve overtime. For ex,
  - We may discover that a particular decomposition is inefficient due to excessive interprocess communication and that you must combine services.
  - Alternatively, a service might grow in complexity to the point where it becomes worthwhile to split it into multiple services.

## Defining services by applying the Decompose by sub-domain pattern

- It is an approach for building complex software applications that is centered on the development of an object-oriented domain model.
- DDD has two concepts that are incredibly useful when applying the microservice architecture: 
  - subdomains.
  - Bounded contexts.
- DDD is quite different from the traditional approach to enterprise modeling.
  - It creates a single model for the entire enterprise. 
  - In this model there would be a single definition of each business entity, such as customer, order, and so on. 
  - Problems
    - Getting different parts of an organization to agree on a single model is a monumental task. 
    - From the perspective of a given part of the organization, the model is overly complex for their needs. 
    - The domain model can be confusing because different parts of the organization might use either the same term for different concepts or different terms for the same concept.
  - Solution    
    - DDD avoids these problems by defining multiple domain(subdomain) models, each with an explicit scope.
- These subdomains are very similar to the business capabilities described earlier.  

### Bounded Context

- DDD calls the scope of a domain model a bounded context.
- A bounded context includes the code artifacts that implement the model.
- Each bounded context is a service or possibly a set of services.
- The subdomains map to services, each with its own domain model as shown below.

![Domain driven design](images/ddd.png)

## Decomposition guidelines

- So far we have seen at the main ways to define a microservice architecture.
- We can also use a couple of principles from object-oriented design when applying the microservice architecture pattern.

### SINGLE RESPONSIBILITY PRINCIPLE

- A class should have only one reason to change.
- If a class has multiple responsibilities that change independently, the class won’t be stable.
- We can apply SRP when defining a microservice architecture and create small, cohesive services that each have a single responsibility.

### COMMON CLOSURE PRINCIPLE

- The classes in a package should be closed together against the same kinds of changes. 
  - A change that affects a package affects all the classes in that package.
- The goal is that when that business rule changes, developers only need to change code in a few packages (ideally only one).  


## Defining Service Apis

- A service api operation could be 
  - A system operation. 
  - A collaborating service.
    - A service publishes events primarily to enable it to collaborate with other services.
    - Events can be used to implement saga(Long lived transactions) or to update CQRS views.

### ASSIGNING SYSTEM OPERATIONS TO SERVICES

- Decide which service is the initial entry point for a request.
- Sometimes the mapping is less obvious. For ex:
  - The `noteUpdatedLocation()` operation, which updates the courier location.
    - Because it’s related to couriers, this operation should be assigned to the Courier service.
    - On the other hand, it’s the Delivery Service that needs the courier location.
    - In this case, assigning an operation to a service that needs the information provided by the operation is a better choice.
- Here is the assignment of system operations to services.

```
Consumer Service      createConsumer()
Order Service         createOrder()
Restaurant Service    findAvailableRestaurants()
Kitchen Service       acceptOrder()
                      noteOrderReadyForPickup()
Delivery Service      noteUpdatedLocation()
                      noteDeliveryPickedUp()
                      noteDeliveryDelivered()
```

### DETERMINING THE APIS REQUIRED TO SUPPORT COLLABORATION BETWEEN SERVICES

- Some system operations are handled entirely by a single service.
  - For ex, the Consumer Service handles the createConsumer() operation entirely by itself.
- But, many system operations span multiple services.
  - For example, in order to implement the createOrder() operation, the Order Service must invoke the following services in order to verify its preconditions and make the post-conditions become true:
    - Consumer Service—Verify that the consumer can place an order and obtain their payment information.
    - Restaurant Service—Validate the order line items, verify that the delivery address/time is within the restaurant’s service area, verify order minimum is met, and obtain prices for the order line items.
    - Kitchen Service—Create the Ticket.
    - AccountingService—Authorize the consumer’s credit card.

![System operations](images/system_operations_and_collaborations.png)

