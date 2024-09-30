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

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/GauraSinghal07/currencyexchange.git
   cd currencyexchange
   
## To Test below use cases:
- Expose an API endpoint (/api/calculate) to accept bill details including items, their categories, total amount, user type, customer tenure, original currency, and target currency. 
- The endpoint should return the net payable amount in the specified target currency after applying applicable discounts and currency conversion.

## Running the application locally Successfully
There are several ways to run a Spring Boot application on your local machine.

- execute the main method in the CurrencyExchangeAndDiscountCalculationApplication class from your IDE.
- Right click on Project
- Select Run As
- Select Java Application (JRE - Java 17)
- Select Main Class CurrencyExchangeAndDiscountCalculationApplication and click on Run.
- Run through command line
- GO to the directory of project where pom.xml exists in windows
- mvn clean install
- mvn spring-boot:run
- Run as executable Runnable JAR 1. Cmd 2. Go to path where currency-exchange-discount-calculator-0.0.1-SNAPSHOT.jar file present 3. You can run the application from the command line using: java -jar currency-exchange-discount-calculator-0.0.1-SNAPSHOT.jar

## UML diagram

![UML](https://github.com/user-attachments/assets/2a1dc7f2-89eb-40b8-afa5-d9c9bcc3a4a0)

## Test from postman

curl --location 'http://localhost:8090/api/calculate' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=6CF7B4AF34D77E2C4A7EB23FD0827422; JSESSIONID=6CF7B4AF34D77E2C4A7EB23FD0827422' \
--data '{
"items": [
{"name": "Apple", "category": "groceries", "price": 50},
{"name": "Laptop", "category": "electronics", "price": 100}
],
"userType": "affiliate",
"customerTenure": 3,
"originalCurrency": "USD",
"targetCurrency": "ANG"
}
'

## Use Case Example
To demonstrate the functionality of the application, consider the following example:

### Input Details
## Example 1
- Items Purchased:
Apple (Groceries): $20
Laptop (Electronics): $80
User Type: Customer
Customer Tenure: 3 years
Original Currency: USD
Target Currency: ANG
Discount Calculation Steps
Total Before Discounts:

Total = $20 (Apple) + $80 (Laptop) = $100

- Discounts:

Eligible for a 5% discount on the Laptop due to customer tenure.
Discount on Laptop: 5% of $80 = $4
Discounted Price of Laptop = $80 - $4 = $76
Final Total = $20 (Apple) + $76 (Discounted Laptop) = $96

- Currency Conversion:

Assuming an exchange rate of 1 USD = 1.79 ANG:
Final Amount in ANG = $96 * 1.79 = 171.84 ANG

When the above details are submitted through the API endpoint, the response will include:
Final Amount in ANG: 171.84 ANG


 ## Example 2
- Items Purchased:
Apple (Groceries): $50
Laptop (Electronics): $100
User Type: Affiliate
Original Currency: USD
Target Currency: ANG
Discount Calculation Steps
Total Before Discounts:

Total = $50 (Apple) + $100 (Laptop) = $150

- Discounts:

Eligible for a 10% discount on the Laptop as user type is Affiliate.
Discount on Laptop: 10% of $100 = $10
Discounted Price of Laptop = $100 - $10 = $90
Discounted Total = $50 (Apple) + $90 (Discounted Laptop) = $140

Since Discounted Total is greater than 100, so user is eligible for $5 discount.
Final Total =  $140 - $5 = $135

- Currency Conversion:

Assuming an exchange rate of 1 USD = 1.79 ANG:
Final Amount in ANG = $135 * 1.79 = 241.65 ANG

When the above details are submitted through the API endpoint, the response will include:
Final Amount in ANG: 241.65 ANG


