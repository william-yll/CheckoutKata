# CheckoutKata
Supermarket checkout implementation.

## Installation
Install latest version of Java and Maven.
You may need to set your JAVA_HOME and MAVEN_HOME

Open command line in the project roo directory, and run below 

    mvn clean install

## Running implementation

The application can be ran through the test class ScenarioTest, which contains a number of different scenarios; 
taking in a list of items, rules, and items to be scanned. 

### Assumptions
Assumptions made are:
1) Initial items that are available contain positive prices
2) The rules contain positive quantities and positive prices
3) All prices are of the same unit

### Extra time
If extra time, would work on:
1) adding validation on construction of item/rules to prevent e.g. negative quantities/prices
2) add better handling of invalid item, such that the valid item prices are still calculated, but also returning
 list of invalid items
 