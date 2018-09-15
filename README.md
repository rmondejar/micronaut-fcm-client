# mn-fcm-client
Micronaut Sending Push Notification (ex. Google Firebase Cloud Messaging)

Test : ./gradlew check

Start : ./gradlew run

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
