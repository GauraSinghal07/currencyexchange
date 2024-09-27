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
   git clone https://github.com/your-username/currency-exchange-app.git
   cd currency-exchange-app
