
package school;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
public class mailer{  
    public static void send(String to,String msg){  
          //Get properties object   
          String from="eyamariemsana@gmail.com";
          String password="sanaeyamariem21";
          String sub="registration result";
          
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true"); 
           props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.debug", "true");
            props.put("mail.smtp.socketFactory.fallback", "false");

          props.put("mail.smtp.port", "465"); 

          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
}  


