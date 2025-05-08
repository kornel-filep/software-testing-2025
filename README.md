# University of Debrecen - Software testing - Java UI Test Automation Demo

## Description
Java UI Automation Project for Educational Purposes @ University of Debrecen Software Testing class.  

## Used tools
- Java 17
- Maven 3.8+

## How to run tests
- To run scenarios, use the following command: `mvn verify`
- To run in headless: `mvn verify -Dheadless=true`.
- Several browsers are supported. To use them, and `-DbrowserName=` to your command. Supported browsers:
  - chrome
  - firefox
  - safari