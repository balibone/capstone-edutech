package commoninfrasessionbeans;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Remote;

@Remote
public interface CommonInfraMgrBeanRemote {
    public boolean empLogin(String username, String password);
    public boolean createSysUser(String salutation, String firstName, String lastName, String username, String password, String email, String contactNum);

    public boolean sendResetEmail(String username);
    public boolean sendCreateEmail(String username);

//    public boolean validateToken(String username, String token);

    public boolean resetPassword(String username, String oldPassword, String password);
    public boolean isValidUser(String username);
    
    public String encodePassword(String username, String password)throws NoSuchAlgorithmException, UnsupportedEncodingException ;
}
