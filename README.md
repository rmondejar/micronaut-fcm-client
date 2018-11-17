# mn-fcm-client
Micronaut Sending Push Notification (ex. Google Firebase Cloud Messaging)

In this example we want to consume this service :
https://firebase.google.com/docs/cloud-messaging/send-message

* Taking as a starting point this Spring Boot example :
https://grokonez.com/spring-framework/spring-boot/firebase-cloud-messaging-server-spring-to-push-notification-example-spring-boot

* Porting the idea to the Micronaut framework : http://micronaut.io/

* Using the Micronaut HTTP Client : http://guides.micronaut.io/micronaut-http-client-groovy/guide/index.html

* Adding groovy as the JVM language, gradle as the building tool, and spock as the testing framework

## FCM Setup ##

* Go to the Firebase website : https://firebase.google.com/
* Login using your Google account to access to the Console
* Add a project (for example "MN-FCM-TEST") to create a new one
* In the project dashboard, select "project settings" (gear icon in the left menu)
* Jump to the Cloud Messaging tab and copy the Legacy Server Key to the application.yml as your own SERVER_KEY
* Remember to configure your app for Android or iOS too


## Micronaut Push Service ##

Test :

```
$ ./gradlew check
...
BUILD SUCCESSFUL in 0s
4 actionable tasks: 4 up-to-date
```

Start :

```
$ ./gradlew run
...
[main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 770ms. Server Running: http://localhost:8080
```

Request example :

```json
curl -X POST -H "Content-Type: application/json" -d '{
	"to" : "/topics/test",
	"title": "TITLE",
	"body": "BODY",
	"data": 
	{
	  "Key-1": "VALUE 1",
	  "Key-2": "VALUE 2"
	}
}' 
http://localhost:8080/msg/push
```

Response :

```json
{
    "message_id": "5548062101043273972"
}
```