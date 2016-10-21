package common;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail
{
	public Boolean sendMail(String email, String subject,  String body)
	{
		Boolean respuesta = false;
		try
        {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "sgpmescom@gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            //props.setProperty("mail.smtp.ssl.trust", "smtpserver");
            props.put("mail.smtp.ssl.trust", "sgpmescom.gmail.com");
            
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage( session );
            message.setFrom( new InternetAddress("sgpmescom@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));    //destinarario
            message.setSubject(subject);
            BodyPart texto = new MimeBodyPart();
            texto.setText(body);
            
            //BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler( new DataHandler( new FileDataSource("C:\\Users\\Angel\\Documents\\ESCOM\\7\\Web\\Tareas\\Tarea7Mail\\Mail\\imagen.jpg ")) );
            //adjunto.setFileName("imagen.jpg");
            MimeMultipart mmp = new MimeMultipart();
            mmp.addBodyPart(texto);
            //mmp.addBodyPart(adjunto);
            
            message.setContent(mmp);
            
            Transport t = session.getTransport("smtp");
            t.connect("sgpmescom@gmail.com", "magictt100");
            
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
		
		
		return respuesta;
	}
	
	public static void main (String[] args)
	{
		Mail mail = new Mail(); 
		mail.sendMail("angelcreux@gmail.com", "hola", "Este es un mensaje");
	}

}
