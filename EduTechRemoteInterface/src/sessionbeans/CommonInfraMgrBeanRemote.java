package sessionbeans;

import javax.ejb.Remote;

@Remote
public interface CommonInfraMgrBeanRemote {
    public boolean empLogin(String empNRIC, String empPassword);
}
