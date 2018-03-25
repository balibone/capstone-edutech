package commoninfrasessionbeans;

import commoninfrasessionbeans.CommonInfraMgrBeanRemote;
import commoninfrastructureentities.UserEntity;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommonInfraMgrBean implements CommonInfraMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    private UserEntity uEntity;
    
    //for mail
    private Properties mailServerProperties;
    private Session mailSession;
    private MimeMessage mailMessage;
    
    @Override
    public boolean empLogin(String username, String userPassword) {
        String hashedPassword = "";
        try{ 
            hashedPassword = encodePassword(username, userPassword); 
        }
        catch(NoSuchAlgorithmException ex){
            ex.printStackTrace(); 
            System.out.println("PASSWORD HASHING FAILED");
        }
        catch(UnsupportedEncodingException ex){
            ex.printStackTrace();
            System.out.println("SALT ENCODING FAILED");
        }
        
        uEntity = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username AND u.userActiveStatus = 1");
            q.setParameter("username", username);
            uEntity = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(uEntity);
            uEntity = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(uEntity);
            uEntity = null;
        }
        if(uEntity == null) { return false; }
        if(uEntity.getUserPassword().equals(hashedPassword)) { return true; }
        return false;
    }
    
    @Override
    public boolean createSysUser(String salutation, String firstName, String lastName, String username, String password, String email, String contactNum){
        try{
            UserEntity u = new UserEntity(salutation, firstName, lastName, username, encodePassword(username, password), email, contactNum);
            em.persist(u);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isValidUser(String username){
        if(em.find(UserEntity.class, username)!=null){
            return true;
        }else{
            return false; 
        }
    }
    
    @Override
    public String encodePassword(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String hashedValue = "";
        byte[] salt = username.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        byte[] passwordBytes = md.digest(password.getBytes("UTF-8"));
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < passwordBytes.length; i++){
            sb.append(Integer.toString((passwordBytes[i] & 0xff) + 0x100, 16).substring(1));
        }     
        hashedValue = sb.toString();
        return hashedValue;
    }
    
    @Override
    public boolean sendResetEmail(String username) {
        Query q1 = em.createQuery("SELECT s FROM SystemUser s WHERE s.username = :uName");
        q1.setParameter("uName", username);
        UserEntity u = (UserEntity) q1.getSingleResult();
        if(u != null){
            String tempPassword = generateAlphaNum(8);
            try {
                generateAndSendEmail(u.getUsername(), u.getEmail(),tempPassword);
                u.setUserPassword(encodePassword(username,tempPassword));
            } catch (MessagingException ex) {
                System.out.println("**************Email sending failed!");
                ex.printStackTrace();
                return false;
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(CommonInfraMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }else{
            return false;
        }
    }
    
    @Override 
    public boolean sendCreateEmail(String username){
        Query q1 = em.createQuery("SELECT s FROM SystemUser s WHERE s.username = :uName");
        q1.setParameter("uName", username);
        UserEntity u = (UserEntity) q1.getSingleResult();
        if(u != null){
            String tempPassword = generateAlphaNum(8);
            try {
                sendCreateEmail(u.getUsername(), u.getEmail(),tempPassword);
                u.setUserPassword(encodePassword(username,tempPassword));
            } catch (MessagingException ex) {
                System.out.println("**************Email sending failed!");
                ex.printStackTrace();
                return false;
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(CommonInfraMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }else{
            return false;
        }
    }
    
    public String generateAlphaNum(int length) {

        char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        
        char[] generated = new char[length];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            //generates random next int from 0 to ch.length-1
            generated[i] = ch[random.nextInt(ch.length)];
        }
        
        return new String(generated);
    }
    
    public void generateAndSendEmail(String username, String email, String tempPassword) throws AddressException, MessagingException {
 
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        //mail server settings
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");
        
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        mailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setSubject("Greetings from EduBox");
        String emailBody = "Hey "+ username + ",<br><br>"
                + "Here is your temporary password: <strong>"+ tempPassword +"</strong><br>"
                + "Please click on this <a href='http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=PasswordReset'>link</a> to reset your password."
                + "<br><br>Cheers,<br>EduBox Team";
        mailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");
        
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = mailSession.getTransport("smtp");
        
        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "41capstone03@gmail.com", "8mccapstone");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
    
    public void sendCreateEmail(String username, String email, String tempPassword) throws AddressException, MessagingException {
 
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        //mail server settings
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");
        
        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        mailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setSubject("Greetings from EduBox");
        String emailBody = "Hey "+ username + ",<br><br>"
                + "We just created an account for you on EduBox!<br>"
                + "Here is your temporary password to get started: <strong>"+ tempPassword +"</strong><br>"
                + "We highlighy recommend you to <strong>change your password</strong> first."
                + "You can click on this <a href='http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=PasswordReset'>link</a> to reset your password.<br>"
                + "Or click <a href='http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=goToLogout'>here</a> to login. "
                + "<br><br>Cheers,<br>EduBox Team";
        mailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");
        
        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = mailSession.getTransport("smtp");
        
        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "41capstone03@gmail.com", "8mccapstone");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    @Override
    public boolean resetPassword(String username, String oldPassword, String password) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username=:uname");
        q1.setParameter("uname", username);
        String encPassword = "";
        String encOldPassword = "";
        try {
            encPassword = encodePassword(username,password);
            encOldPassword = encodePassword(username, oldPassword);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(CommonInfraMgrBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserEntity u = (UserEntity) q1.getSingleResult();
        //if user exists & old password is correct, proceed with reset.
        if(u != null && u.getUserPassword().equals(encOldPassword)){
            System.out.println("RECEIVED PASSWORD IS "+encPassword);
            u.setUserPassword(encPassword);
            return true;
        }else{
            return false;
        }
    }
}