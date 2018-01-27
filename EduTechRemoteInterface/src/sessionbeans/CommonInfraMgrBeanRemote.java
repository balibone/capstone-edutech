package sessionbeans;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface CommonInfraMgrBeanRemote {
    public boolean empLogin(String empNRIC, String empPassword);
}
