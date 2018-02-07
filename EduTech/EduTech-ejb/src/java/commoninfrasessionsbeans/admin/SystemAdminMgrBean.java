/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninfrasessionsbeans.admin;

import commoninfrastructure.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Derian
 */
@Stateless
public class SystemAdminMgrBean implements SystemAdminMgrBeanRemote {
    //Entity manager
    @PersistenceContext
    EntityManager em;

    @Override
    public boolean createNewStudent(String salutation, String firstName, String lastName, String email, String password, String fileName) { 
        try{
            UserEntity newStudent= new UserEntity(email,salutation,firstName,lastName,password,"student",fileName);
            em.persist(newStudent);//may throw null pointer if em is not created with proper syntax.
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }
    
    @Override
    public ArrayList<ArrayList> getAllStudents() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='student'").getResultList();
        ArrayList entityList = new ArrayList();//to store the set of ArrayLists containing 1 entity information each. 
        for(Object o: results){
            UserEntity userE = (UserEntity) o;
            ArrayList singleUser = new ArrayList();
            
            singleUser.add(userE.getImgFileName());
            singleUser.add(userE.getUserFirstName()+" "+userE.getUserLastName());
            singleUser.add(userE.getUserEmail());
            singleUser.add(userE.getUserCreationDate());
            
            entityList.add(singleUser);
            
        }
        return entityList;
    }
    
}
