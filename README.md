Dependencies:

* [Maven 3](http://maven.apache.org) or above installed
* [Java 1.7](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or above

All tests (cucumber and webdriver) run automatically in the default environment (env-qa) after starting the build:

    mvn clean install
	
Optional Maven parameters:
* test.env : name of the environment configuration to be used, allows easy switching to other environments

	eg: mvn clean install -Dtest.env=env-qa
	
* For checking if approach is thread safe, 3 tests are defined and execution configured for 2 tests.
* Using reporting plugin for Cucumber improved reporting