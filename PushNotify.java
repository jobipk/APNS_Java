import java.util.List;
import java.util.ResourceBundle;

import javapns.Push;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

public class PushNotify {
	// http://www.pushwoosh.com/programming-push-notification/android-push-notification-sdk-configuration-guide/
	// http://developer.android.com/google/gcm/gs.html
	// http://vardhan-justlikethat.blogspot.in/2012/10/java-push-notifications-for-ios-and.html
	// http://oodlestechnologies.com/blogs/Push-Notifications-using-Google-Cloud-Messaging-(GCM)-in-Android-(Part-2)

	// private static final String HOST = "gateway.sandbox.push.apple.com";

	/** APNs Port */
	// private static final int PORT = 2195;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tokens = "";  // Set the device token
		String certificatesPath = ""; // Set .p12 file name 
		String password = ""; // password
		_push(tokens, certificatesPath, password);
	}

	private static void _push(String tokens, String certificatesPath,
			String password) {

		try {
			PushNotificationPayload payload = PushNotificationPayload.complex();
			payload.addAlert("send notification");
			payload.addBadge(1);
			payload.addSound("default");
			payload.addCustomDictionary("id", 1);
			ResourceBundle bundle = null;

			List<PushedNotification> NOTIFICATIONS = Push.payload(payload,
					certificatesPath, password, false, tokens);

			for (PushedNotification NOTIFICATION : NOTIFICATIONS) {
				if (NOTIFICATION.isSuccessful()) {
					/* APPLE ACCEPTED THE NOTIFICATION AND SHOULD DELIVER IT */
					System.out
							.println("PUSH NOTIFICATION SENT SUCCESSFULLY TO: "
									+ NOTIFICATION.getDevice().getToken());
					/* STILL NEED TO QUERY THE FEEDBACK SERVICE REGULARLY */
				} else {
					String INVALIDTOKEN = NOTIFICATION.getDevice().getToken();
					/* ADD CODE HERE TO REMOVE INVALIDTOKEN FROM YOUR DATABASE */

					/* FIND OUT MORE ABOUT WHAT THE PROBLEM WAS */
					Exception THEPROBLEM = NOTIFICATION.getException();
					THEPROBLEM.printStackTrace();
					/*
					 * IF THE PROBLEM WAS AN ERROR-RESPONSE PACKET RETURNED BY
					 * APPLE, GET IT
					 */
					ResponsePacket THEERRORRESPONSE = NOTIFICATION
							.getResponse();
					if (THEERRORRESPONSE != null) {
						System.out.println(THEERRORRESPONSE.getMessage());
					}
				}
			}
		} catch (KeystoreException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
