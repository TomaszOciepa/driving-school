package mail;


import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class SendMail {

    final String username = "driveschool.tomek@gmail.com";
    final String password = "Pass123!";

    public void sendMail( String userEmail, String userPassword){

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mail"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userEmail));
			message.setSubject("Reset your password");
			message.setText("We've reset the password. Log in with a temporary password and set a new password."
				+ "\n\n Your temporary password: " + userPassword);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}



    }


}
