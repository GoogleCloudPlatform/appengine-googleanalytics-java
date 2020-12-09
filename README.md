![status: inactive](https://img.shields.io/badge/status-inactive-red.svg)

This project is no longer actively developed or maintained.

For new work on this check out [this repository](https://github.com/googleapis/java-analytics-data).

## appengine-googleanalytics-java

Integrating App Engine with Google Analytics

## Project setup, installation, and configuration

- Register for [Google Analytics](http://www.google.com/analytics/), create
an application, and get a tracking id.
- Use that tracking id when you are Instantiating a `GoogleAnalyticsTracking` object.

Requires [Apache Maven](http://maven.apache.org) 3.0 or greater, and JDK 7+ in order to run.

To build the library, run

    mvn package

This will create `tracking-1.0-SNAPSHOT.jar` in the `target` directory.

Building will run the tests, but to explicitly run tests you can use the test target

    mvn test

## Running the demo

- Modify the `tracking.jsp` to use your own tracking id if you wish to test
tracking events to your own account. 

To build the demo, run

    mvn package

To start the app, use the [App Engine Maven Plugin](http://code.google.com/p/appengine-maven-plugin/) that is already included in this demo.  Just run the command.

    mvn appengine:devserver

For further information, consult the [Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.

To see all the available goals for the App Engine plugin, run

    mvn help:describe -Dplugin=appengine

## Contributing changes

* See [CONTRIB.md](CONTRIB.md)

## Licensing

* See [LICENSE](LICENSE)
