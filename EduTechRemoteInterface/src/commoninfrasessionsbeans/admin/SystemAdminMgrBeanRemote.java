/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninfrasessionsbeans.admin;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Derian
 */
@Remote
public interface SystemAdminMgrBeanRemote {

    public boolean createNewStudent(String salutation, String firstName, String lastName, String email, String password, String fileName);

    public ArrayList<ArrayList> getAllStudents();

}
