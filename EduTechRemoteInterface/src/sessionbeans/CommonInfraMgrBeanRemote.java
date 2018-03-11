package sessionbeans;

import javax.ejb.Remote;

@Remote
public interface CommonInfraMgrBeanRemote {
    public boolean empLogin(String username, String password);
    public boolean createSysUser(String salutation, String firstName, String lastName, String username, String password, String email, String contactNum);
}
