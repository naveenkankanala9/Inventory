# barclays_challenge
Problem 3 Inventory Management 1
```
Mr. X owns a store that sells almost everything you think about. Now he wants a inventory management system to manage his inventory. Mr. X feels that controlling his inventory through SMS from his mobile will be revolutionary. So as a prequel, he decides that he wants a system that accepts one line commands and performs the respective operation.

Below is the list of commands he needs in the system:
 
a) create itemName costPrice sellingPrice
       	Whenever Mr. X wants to add a new item to his store he issues a create command. This command creates a new item in the inventory with the given cost price and selling price. The prices are rounded off to two decimal places.
 
b) delete itemName
      	If Mr. X decides not to sell an item anymore, then he simply issues a delete command. This command will remove the item from the inventory.
 
c) updateBuy itemName quantity
      	Whenever Mr. X purchases additional quantity of the mentioned item, then he issues a updateBuy command. This command should increase the quantity of the mentioned item.
 
d) updateSell itemName quantity
      	Whenever Mr. X sells some item, then he issues a updateSell command. This command should deduct the quantity of the mentioned item.				
 
e) report
      	Whenever Mr. X wants to view his inventory list he issues the report command. This command should print the current inventory details in the specified format sorted by alphabetical order. Apart from printing the inventory it has to report on the profit made by Mr. X since last report generation.
Where profit is calculated by:  ∑ (sellingPrice-costPrice) of the sold items multiplied by no. of items sold- costPrice of the deleted items.
```
# Development
```
The project is developed using 
  Spring Boot
  Java8
  Hibernate
  InMemory H2 Database 
  using Eclipse as IDE
```
# Build the Jar
Project Jar can be built using Maven Build in Eclipse or IntelliJ 

# Running the Jar
The java jar can be run from console using the below command
```
java -jar <project-filename>.jar
```
Sample Input
```
create Book01 10.50 13.79
create Food01 1.47 3.98
create Med01 30.63 34.29
create Tab01 57.00 84.98
updateBuy Tab01 100
updateSell Tab01 2
updateBuy Food01 500
updateBuy Book01 100
updateBuy Med01 100
updateSell Food01 1
updateSell Food01 1
updateSell Tab01 2
report
delete Book01
updateSell Tab01 5
create Mobile01 10.51 44.56
updateBuy Mobile01 250
updateSell Food01 5
updateSell Mobile01 4
updateSell Med01 10
report
#


```
Expected Output
```
              	INVENTORY REPORT
Item Name 	Bought At    	Sold At       	AvailableQty    	Value
--------- 	---------    	-------       	-----------     	-------
Book01    	10.50          	13.79               	100    	1050.00
Food01     	1.47           	3.98               	498     	732.06
Med01     	30.63          	34.29               	100    	3063.00
Tab01     	57.00          	84.98                	96    	5472.00
---------------------------------------------------------------------------
Total value                                                     	10317.06
Profit since previous report                                      	116.94
 
 
              	INVENTORY REPORT
Item Name 	Bought At    	Sold At  	AvailableQty    	Value
--------- 	---------    	-------  	-----------     	-------
Food01          	1.47      	3.98       	493           	724.71
Med01          	30.63     	34.29        	90          	2756.70
Mobile01       	10.51     	44.56       	246          	2585.46
Tab01          	57.00     	84.98        	91          	5187.00
---------------------------------------------------------------------------
Total value                                                   	11253.87
Profit since previous report                                   	-724.75

```

# Design 
Followed MVC design pattern and SOA 
```
Profit Calculation : I have used a property 'profit' for storing profit value inside store class, calculated and updated the profie value inside the database whenever there is a sell or delete item transaction is performed. By doing so I have handled the correct profit calculation despite change in the selling price.

```

# Model
Store and Items are the Business Objects
Used Hibernate for mapping between entities

# Controller
StoreController will handle all the operations performed from console

# Service
InventoryService contains the business logic for handling the necessary operations

# Repository
Handled the database transactions inside StoreRepository

# Validation 
Validated user input for null and non string values for number input using InputValidator class



