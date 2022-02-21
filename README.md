
Selenium-Maven-Template
=======================

A maven template for Selenium 3 that has the latest dependencies so that you can just check out and start writing tests in four easy steps.

1. Open a terminal window/command prompt
2. Clone this project.
3. Go to project folder
4. `mvn clean verify`

All dependencies should now be downloaded and the example google test will have run successfully (Assuming you have Chrome installed in the default location)

### What should I know?

* To run any unit tests that test your Selenium framework you just need to ensure that all unit test file names end with "test" and they will be run as part of the build.
* You can specify which browser on which environment to use by using one of the following configuration options:
    * For run from IDE
        * set properties in [local.properties](src/test/resources/properties/local.properties) file
        * set environment variable in IDE. In IntelliJ Run > Edit Configuration > Defaults > TestNG, use `CONFIG_FILE, ENV, BROWSER`
        * create own test suite in `src/test/resources/suites` and run suite using right click on it and click Run 
    * From run using maven or maven wraper
        * edit default test suite it's [testing.xml](src/test/resources/suites/testing.xml)
        * create own test suite in src/test/resources/suites and run `mvn verify -DsuiteFile=<your_suite_name>`
* The [local.properties](src/test/resources/properties/local.properties) file is created automatically from [default.properties](src/test/resources/properties/default.properties) after first clean or first run from maven. Please don't commit it to repository.
* You don't need worry about downloading the IEDriverServer, MicrosoftWebDriver, chromedriver , operachromium, or wires binaries, this project will do that for you automatically.
* Not got PhantomJS?  Don't worry that will be automatically downloaded for you as well!
