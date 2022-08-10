# example-actuator

- look at `pom.xml` to see git and maven build info being added
- look at `application.yml` to see:
  - endpoints being exposed
  - static values being put into the `/actuator/info` endpoint
- look at `/src/java/com/example` to see:
  - custom `/actuator/metrics`
  - custom `/actuator/info`
