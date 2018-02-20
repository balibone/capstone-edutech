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

    public boolean createNewStudent(String salutation, String firstName, String lastName, String username, String password, String fileName);
    public void deleteUser(String username);
    public boolean editUser(String username, String salutation, String firstName, String lastName, String password, String userType, String fileName);
    public ArrayList<ArrayList> getAllStudents();
    public ArrayList getUserInfo(String id);    
    public boolean createNewInstructor(String salutation, String firstName, String lastName, String username, String password, String fileName);
    public ArrayList<ArrayList> getAllInstructors();
    
    public ArrayList<ArrayList> getAllAdmins();
    public boolean createNewAdmin(String salutation, String firstName, String lastName, String username, String password, String fileName,String adminType);

    public Long getUserCount(String type);
}
