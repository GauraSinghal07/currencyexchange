# Currency Exchange and Discount Calculation

## Overview
This Spring Boot application integrates with a third-party currency exchange API to calculate the total payable amount for a bill in a specified currency after applying applicable discounts. It allows users to submit bills in one currency and receive the net payable amount in another currency.

## Features
- Real-time currency exchange rate retrieval
- Discount calculations based on user type and tenure
- API endpoint for bill calculation
- Authentication for secure access
- Caching for exchange rates to reduce API calls

## Technology Stack
- Java
- Spring Boot
- Spring Security
- JUnit
- Mockito
- Maven

## Prerequisites
- Java 17 or higher
- Maven
- An API key from [Open Exchange Rates](https://openexchangerates.org/)

## To Test below use cases:
- Expose an API endpoint (/api/calculate) to accept bill details including items, their categories, total amount, user type, customer tenure, original currency, and target currency. 
- The endpoint should return the net payable amount in the specified target currency after applying applicable discounts and currency conversion.

## Running the application locally Successfully
There are several ways to run a Spring Boot application on your local machine.

- execute the main method in the CurrencyExchangeAndDiscountCalculationApplication class from your IDE.
- Right click on Project
- Select Run As
- Select Java Application (JRE - Java 21)
- Select Main Class CurrencyExchangeAndDiscountCalculationApplication and click on Run.
- Run through command line
- GO to the directory of project where pom.xml exists in windows
- mvn clean install
- mvn spring-boot:run
- Run as executable Runnable JAR 1. Cmd 2. Go to path where currency_exchange_and_discount_calculation-0.0.1-SNAPSHOT.jar file present 3. You can run the application from the command line using: java -jar currency_exchange_and_discount_calculation-0.0.1-SNAPSHOT.jar

## Test from postman

curl --location 'http://localhost:8090/api/calculate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=6CF7B4AF34D77E2C4A7EB23FD0827422' \
--data '{
"items": [
{"name": "Apple", "category": "groceries", "price": 60},
{"name": "Laptop", "category": "electronics", "price": 40}
],
"userType": "customer",
"customerTenure": 3,
"originalCurrency": "USD",
"targetCurrency": "AFN"
}
'

## Use Case Example
To demonstrate the functionality of the application, consider the following example:

# Input Details
- Items Purchased:
Apple (Groceries): $60
Laptop (Electronics): $40
User Type: Customer
Customer Tenure: 3 years
Original Currency: USD
Target Currency: AFN
Discount Calculation Steps
Total Before Discounts:

Total = $60 (Apple) + $40 (Laptop) = $100
- Discounts:

Eligible for a 5% discount on the Laptop due to customer tenure.
Discount on Laptop: 5% of $40 = $2
Discounted Price of Laptop = $40 - $2 = $38
Final Total = $60 (Apple) + $38 (Discounted Laptop) = $98
- Currency Conversion:

Assuming an exchange rate of 1 USD = 90 AFN:
Final Amount in AFN = $98 * 90 = 8820 AFN
Expected Output
When the above details are submitted through the API endpoint, the response will include:

Final Amount in AFN: 8820 AFN

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/currency-exchange-app.git
   cd currency-exchange-app
