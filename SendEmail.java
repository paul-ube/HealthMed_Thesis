/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.sql.Connection;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
//import javax.mail.Message;
import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.MessagingException;


public class SendEmail
{
    public static void SendEmail(){
        
        final String username = "clinic.stiglobal@gmail.com";
        final String pass = "Password1234@@";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session sesh = Session.getInstance(props,
            new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(username, pass);
                    
                    //return sesh;

                }
            });
        
    }
}