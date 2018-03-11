package sessionbeans;

import commoninfrastructureentities.UserEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

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
}