package no.kvikshaug.reminder

import javax.mail.PasswordAuthentication
import java.util.Properties
import java.io.{File, FileReader}
import javax.mail.internet.{InternetAddress, MimeMessage}
import javax.mail.{Transport, Message, Session, Authenticator}

object Mailer {

  val mailProps = new Properties

  def initialize = {
    val mailPropsFile = new File("mail.properties")
    if(!mailPropsFile.exists) {
      printf("Error: Missing properties file '%s'.\n", mailPropsFile.getAbsolutePath)
      System.exit(-1)
    }
    mailProps.load(new FileReader(mailPropsFile))
  }

  def mail(subject: String, messageText: String) = {
    val sess = Session.getInstance(mailProps, new SMTPAuthenticator)
    val message = new MimeMessage(sess);
    message.setSubject(subject);
    message.setText(messageText);
    message.setFrom(new InternetAddress(mailProps.get("mail.smtp.user").asInstanceOf[String]));
    message.addRecipient(Message.RecipientType.TO,
      new InternetAddress(mailProps.get("mail.smtp.recipient").asInstanceOf[String]));
    Transport.send(message);
  }

  class SMTPAuthenticator extends Authenticator {
    override def getPasswordAuthentication(): PasswordAuthentication = {
      return new PasswordAuthentication(mailProps.get("mail.smtp.user").asInstanceOf[String],
        mailProps.get("mail.smtp.password").asInstanceOf[String])
    }
  }
}
