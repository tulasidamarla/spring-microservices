## What is DORA?

- DORA metrics are used by DevOps teams to measure their performance and find out whether they are “low performers” to “elite performers”. 
- The four metrics used are
  - deployment frequency (DF),
  - lead time for changes (LT),
  - meantime to recovery (MTTR), and 
  - change failure rate (CFR).
  
## what is shared database pattern?

- The shared database pattern in microservices architecture involves multiple microservices sharing a single database
- This pattern can have its advantages and disadvantages, depending on the specific use case and implementation. 
- Some queries must join data that is owned by multiple services. 
  - For example, finding customers in a particular region and their recent orders requires a join between customers and 
    orders.

### _Pros_

- **Simplified Data Access**: With a shared database, microservices can access and update data more easily since they 
  all share the same data store. This can simplify data management and reduce the need for complex data synchronization 
  mechanisms.
- **Consistency**: Data consistency can be more easily maintained since all microservices operate on the same data source.
  This can lead to fewer data integrity issues and a more predictable data state.
- **Cost Savings**: Managing a single database can be less expensive than managing multiple databases, both in terms of 
  infrastructure and operational costs.
- **Performance**: In some cases, shared databases can offer better performance since they can take advantage of database
  optimizations and caching mechanisms.
- **Transactional Integrity**: If your application requires complex transactions that span multiple microservices, a 
  shared database can provide strong transactional support and ensure atomicity across microservices.

## _Cons_

- **Tight Coupling**: Sharing a database can introduce tight coupling between microservices. Changes to the schema or 
  data structure can impact multiple microservices, making it harder to evolve and deploy them independently.
- **Scaling Challenges**: Scaling can be challenging with a shared database. As traffic grows, you may need to scale the 
  database tier horizontally, which can be complex and costly.
- **Reduced Autonomy**: Microservices are meant to be autonomous, independently deployable units. A shared database can 
  reduce the autonomy of microservices, as changes to the database schema may require coordination and synchronization 
  between teams.
- **Complex Queries and Joins**: If microservices need to perform complex queries or joins across shared data, it can be 
  challenging to optimize and maintain query performance.
- **Security Risks**: A shared database can introduce security risks. Access controls must be carefully managed to ensure
  that only authorized microservices can access and modify data.
- **Data Versioning and Migration**: Data versioning and migration become more complex when multiple microservices share 
  a database, as schema changes must be coordinated across services.
- **Vendor Lock-In**: If you're using a database-as-a-service (DBaaS) or a specific database technology, sharing a 
  database can lead to vendor lock-in, making it difficult to switch to another technology in the future.

In summary, the decision to use the shared database pattern in a microservices architecture should be carefully 
considered, taking into account the specific requirements and constraints of your application. While it can simplify 
some aspects of data management, it can also introduce complexities and challenges related to autonomy, scalability, 
and maintenance. In many cases, a more decoupled approach, such as employing separate databases with well-defined APIs 
for data access between microservices, may be a better choice.

## How to version rest apis?

- Versioning in APIs is a technique used to manage changes and updates to the API's functionality over time while 
  ensuring backward compatibility for existing clients. 
- Two common approaches to API versioning are using path variables (also known as URI versioning) and content negotiation.
- `Path Variable (URI) Versioning:`
  - `URL Structure:` The API version is included directly in the URL path, typically as a segment of the path. 
    - For example, /v1/resource and /v2/resource represent different versions of the same resource.
  - `Clear Version Indication:` It provides a clear and explicit indication of the API version in the URL, making it easy
    for both clients and developers to understand and identify the version being used.
  - `Caching:` Caching can be more effective as the URL path includes version information, allowing caching mechanisms 
    to differentiate between different versions of the same resource.
  - `Backward Compatibility:` It encourages maintaining backward compatibility by keeping older versions of the API 
    accessible through their respective paths.
  - `Client Choice:` Clients can explicitly specify the version they want to use by constructing the URL accordingly.
- `Content Negotiation Versioning:`
  - `URL Structure:` Content negotiation versioning keeps the URL structure constant, without including the version in 
    the path.
  - `Header-Based:` The version information is conveyed through HTTP headers, typically the "Accept" header, 
    where clients specify the desired media type and version (e.g., Accept: application/vnd.myapi.v1+json).
  - `Cleaner URLs:` It keeps the URLs cleaner and less cluttered, as the version information is not part of the path.
  - `Flexibility:` It allows clients more flexibility in choosing the version, as they can specify it dynamically in 
    the request headers.
  - `Less Impact on Caching:` Content negotiation may have a minor impact on caching, as caching mechanisms may need 
    to rely on request headers or other factors to determine caching behavior.
  - `Potentially Complex for Clients:` Clients need to be aware of and correctly implement content negotiation, which 
    can be more complex than constructing versioned URLs.

- `Summary`  
  - Path variable versioning is more explicit and visible in the URL, making it easier to understand and cache. 
  - Content negotiation keeps URLs cleaner and offers more dynamic version selection but may require additional 
    handling in the client code. 
  - The choice between these methods often depends on your API design preferences, requirements, and the specific needs 
    of the clients.

## Difference between restful services and microservices?
## Deployment stratagies?
## What are web sockets or streaming rpc?
## Difference between apache kafka, rabbit mq and active mq?
## Difference between AMQP and STOMP?
## How does two phase commit works?
## DB Transaction isolation levels?
## Difference b/w choreography and orchestration sagas?
## what is memento pattern?
## TODO: IDEMPOTENT MESSAGE PROCESSING WHEN USING A NOSQL-BASED EVENT STORE
## How to handle processed_events table style in no sql db?
## Understand the difference b/w completable future and executorservice.submit(runnableList) with each runnable call back
## How does security-context threadlocal works in spring security?
## Non-blocking IO?



