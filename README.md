# myRetailApp
This application is used to retrieve the product details based on its id. The product's name is retrieved from the internal target API using https rest call, and the product's price details is retrieved from the local noSql database. This app also takes a PUT request on the same resource path to update the price details in the database.
  
Pre-requisites
1. Install JDK >= 1.7
2. Install maven - 3.5.3
3. Install MongoDb - 3.6.5
4. Install git - 2.17.1
5. Install postman for testing the endpoints

How to launch the application?
1. Git clone the repository in your local machine
2. Import the maven project in the IDE
3. Start the local mongoDb using command "mongod" 
4. In a new terminal/shell, run command "mongo" to access the mongo client
5. See all the available databases - command "show dbs"
6. Since this application uses local db, switch mongo to use local database with command "use local" 
7. Create and insert price details in to the collection using command "> db.price.insert([..]{"id":13860434,"value":34,"currencyCode":"USD"})"
8. Right click on the com.myretailapp.MyRetailApplication.class and run the main method
9. This starts the application as SpringBoot
10. This application can also be started by command line
11. After cloning the project, run command "mvn clean package"
12. This generates the myRetailServices-1.0-SNAPSHOT.jar in the output folder
13. Run the command "java -jar /<--OutputFolderPath-->/myRetailServices-1.0-SNAPSHOT.jar --spring.config.location=/classpath:/default.properties"
14. This starts the application in port 8080

How to access the application:

1. Application starts in local embedded tomcat in port 8080, it can be accessed through http://localhost:8080/api
2. Launch postman
3. Access the product details from URI - http://localhost:8080/api/products/13860428
4. Update product details by a PUT request to the URI http://localhost:8080/api/products/13860428 with the request body in the format below.
{
  	"id":13860428,
    "value": 23.22,
    "currency_code": "USD"
}
 

