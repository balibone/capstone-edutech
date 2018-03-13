package sessionbeans;

import commoninfrastructureentities.UserEntity;
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
        try{ hashedPassword = encodePassword(userPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
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
        // if(uEntity.getUserPassword().equals(hashedPassword)) { return true; }
        if(uEntity.getUserPassword().equals(userPassword)) { return true; }
        return false;
    }
    
    @Override
    public boolean createSysUser(String salutation, String firstName, String lastName, String username, String password, String email, String contactNum){
        try{
            UserEntity u = new UserEntity(salutation, firstName, lastName, username, password, email, contactNum);
            em.persist(u);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /* MISCELLANEOUS METHODS */
    public UserEntity lookupUser(String username){
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
    
    public String encodePassword(String password) throws NoSuchAlgorithmException {
        String hashedValue = "";
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            hashedValue = sb.toString();
        }      
        return hashedValue;
    }

    @Override
    public boolean sendResetEmail(String username) {
        Query q1 = em.createQuery("SELECT s FROM SystemUser s WHERE s.username = :uName");
        q1.setParameter("uName", username);
        UserEntity u = (UserEntity) q1.getSingleResult();
        if(u != null){
            String resetToken = generateAlphaNum(5);
            u.setResetToken(resetToken);
            try {
                generateAndSendEmail(u.getUsername(), u.getEmail(),resetToken);
            } catch (MessagingException ex) {
                System.out.println("**************Email sending failed!");
                ex.printStackTrace();
                return false;
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
    
    public void generateAndSendEmail(String username, String email, String token) throws AddressException, MessagingException {
 
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
                + "Click on this <a href='http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=PasswordReset&username="+username+"&token="+token+"'>link</a> to reset your password.<br>"
                + "Alternatively, you click <a href='http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=PasswordReset&username="+username+"'>here</a> and paste your reset token <strong>"+ token +"</strong> manually."
                + "<a></a>" +"<br><br>Cheers,<br>EduBox Team";
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
    public boolean validateToken(String username, String token) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username=:uname");
        q1.setParameter("uname", username);
        UserEntity u = (UserEntity) q1.getSingleResult();
        if(u != null && u.getResetToken().equals(token)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean resetPassword(String username, String password) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username=:uname");
        q1.setParameter("uname", username);
        
        UserEntity u = (UserEntity) q1.getSingleResult();
        if(u != null && !u.getUserPassword().equals(password)){
            System.out.println("RECEIVED PASSWORD IS "+password);
            u.setUserPassword(password);
            return true;
        }else{
            return false;
        }
    }
}