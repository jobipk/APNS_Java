APNS_Java
=========

Java source code for Apple Notification Service


Edit the following lines with the appropriate values in the main function of the PushNotify.java.

	String tokens = "";  // Set the device token
	String certificatesPath = ""; // Set .p12 file name 
	String password = ""; // password

Note:

Modify the below line as detailed below for development and distribution scenarios.

List<PushedNotification> NOTIFICATIONS = Push.payload(payload,
					certificatesPath, password, false, tokens);
false => development
true => distribution


Set JAVA_HOME environment variable

How to Compile?

 ./compile.sh

How to Run?

 ./run.sh
