- API tests can run with "**maven test**" command 
or directly run from **src/test/java/api/BookingApiTest**

- Cucumber tests (Mobile and Web Browser) can run with **src/test/java/runner/TestRunner.java**
or directly from feature files in sources folder

  - Web Browser: Hepsiburada.feature
  - Mobile App: Akakce.feature
  - Cucumber Tags: @regression, @mobile, @browser

- Browser tests using bonigarcia WebDriverManager

- Mobile test setup may a little hardcoded, you can change capabilities for your environment from **src/test/java/driver/DriverManager.java**

  - Developed for just Android with Appium

- Project SDK: Oracle OpenJDK 21.0.2
  
    Thank You.
