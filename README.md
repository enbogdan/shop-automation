Dependencies:

* [Maven 3](http://maven.apache.org) or above installed
* At least Java 1.7

Tests run automatically in the default environment (env-qa) after starting the build:

    mvn test
	
Optional Maven parameters:
* test.env : name of the environment configuration to be used, allows easy switching to other environments

	eg: mvn test -Dtest.env=env-qa