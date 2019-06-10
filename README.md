# ws-demo

## ws-server

module to produce SOAP web service running in port 8080. Please go into ws-server module and execute the below command.

```
mvn spring-boot:run
```

## ws-client
module to consume SOAP web service running in port 8081. It exposes REST API which integrates to SOAP web service.
Please go into ws-client module and execute the below command.

```
mvn spring-boot:run
```

Now you have both server and client running.

Please execute the below curl command to see the output.

```
curl -X GET http://localhost:8081/countries/spain
```