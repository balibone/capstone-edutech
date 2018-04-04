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

    public boolean createNewStudent(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName);
    public void toggleUserStatus(String username);
    public boolean editUser(String username, String salutation, String firstName, String lastName, String userType, String fileName, String email, String contactNum);
    public ArrayList<ArrayList> getAllStudents();
    public ArrayList getUserInfo(String id);    
    public boolean createNewInstructor(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName);
    public ArrayList<ArrayList> getAllInstructors();
    
    public ArrayList<ArrayList> getAllAdmins();
    public boolean createNewAdmin(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName,String adminType);

    public Long getUserCount(String type);
}
