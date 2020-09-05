# Travel Route Service
Implementation of a route service that given some connections among airports returns the cheapest route along with the cost. 

### Requirements
* Maven
* JDK 1.8

#### Running application
The application needs a csv file containing the routes as input, the file named input-routes.csv along with the initialization script is at infrasctructure folder. At project folder run the **sh start.sh** script passing the path to csv file as argument.
* E.g. sh start.sh "/Users/root/Downloads/input-routes.csv"

#### Usage
Exists two ways to use the application:
- **Command Line:** When the application starts you can see a welcome message, just type the route (e.g GRU-CDG) and press enter to see the result.
- **Rest API:** Using Rest API it's possible to do a GET request passing the source and destination as parameter (e.g http://localhost:8080/routes/GRU/ORL). 
  To create a new route do a POST request to append a new route at csv file.
  
  POST Request
  Request
```json
{
    "source": "ORL",
    "destination": "BRC",
    "cost": 1
}
```

#### Project Description
To implement the application base was used the [Dijkstra Algorithm](https://pt.wikipedia.org/wiki/Algoritmo_de_Dijkstra) and this Java Algorithm Implementation https://www.youtube.com/watch?v=DCJcH6LRkJk&t=932s.

The structure of the project is arranged in this way
- controller: contains application controllers, responsible for receiving requests;
- models: application models with the data estructure;
- repository: since we do not have a database the resources are been handle using Collection at this package;
- utility: Utility classes that can be used for one or more classes;
- processor: here we have the application processors (web and command line) implementing Route Processor and also the bussines logics for each interface;
- App: starts the application by using observer pattern to initialize all implementation of Route Processors.

