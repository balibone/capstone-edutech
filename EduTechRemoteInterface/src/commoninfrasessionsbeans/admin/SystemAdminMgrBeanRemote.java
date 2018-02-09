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
    public boolean editStudent(String email, String fileName);
    public ArrayList<ArrayList> getAllStudents();
    public ArrayList getStudentInfo(String id);
    
    public boolean createNewInstructor(String salutation, String firstName, String lastName, String email, String password, String fileName);
    public boolean editInstructor(String fileName);
    public ArrayList<ArrayList> getAllInstructors();
    
    public boolean createNewEduTechAdmin(String salutation, String firstName, String lastName, String email, String password, String fileName);
    public boolean editEduTechAdmin(String fileName);
    public ArrayList<ArrayList> getAllEduTechAdmins();
    
    public boolean createNewUnifyAdmin(String salutation, String firstName, String lastName, String email, String password, String fileName);
    public boolean editUnifyAdmin(String fileName);
    public ArrayList<ArrayList> getAllUnifyAdmins();

    public void deleteUser(String email);





    

}
