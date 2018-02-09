/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninfrasessionsbeans.admin;

import commoninfrastructure.UserEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType= 'student'");
        ArrayList entityList = new ArrayList();//to store the set of ArrayLists containing 1 entity information each. 
        for(Object o: q.getResultList()){
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
    @Override
    public ArrayList getStudentInfo(String id) {
        ArrayList userInfo = new ArrayList();
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.userEmail= :email");
        q1.setParameter("email", id);
        for(Object o: q1.getResultList()){
            UserEntity user = (UserEntity) o;
            //store full name
            userInfo.add(user.getUserSalutation()+" "+user.getUserFirstName()+" "+user.getUserLastName());
            //store email
            userInfo.add(user.getUserEmail());
            //format creation date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(user.getUserCreationDate());
            //store creation date
            userInfo.add(creationDate);
            //store user type
            userInfo.add("Student");
            //store image file
            userInfo.add(user.getImgFileName());
        }
        return userInfo;
    }
    
    @Override
    public boolean editStudent(String email, String fileName) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.userEmail= :email");
        q1.setParameter("email", email);
        for(Object o:q1.getResultList()){
            UserEntity u = (UserEntity) o;
            //possible upgrade: delete old image file
            //update image file name. 
            u.setImgFileName(fileName);
            return true; 
        }
        System.out.println("No such user found");
        return false; 
    }
    
    @Override
    public boolean createNewInstructor(String salutation, String firstName, String lastName, String email, String password, String fileName) { 
        try{
            UserEntity newInstructor= new UserEntity(email,salutation,firstName,lastName,password,"instructor",fileName);
            em.persist(newInstructor);//may throw null pointer if em is not created with proper syntax.
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }
    
    @Override
    public ArrayList<ArrayList> getAllInstructors() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='instructor'").getResultList();
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
    
    @Override
    public boolean editInstructor(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean createNewEduTechAdmin(String salutation, String firstName, String lastName, String email, String password, String fileName) { 
        try{
            UserEntity newStudent= new UserEntity(email,salutation,firstName,lastName,password,"edutechadmin",fileName);
            em.persist(newStudent);//may throw null pointer if em is not created with proper syntax.
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }
    
    @Override
    public ArrayList<ArrayList> getAllEduTechAdmins() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='edutechadmin'").getResultList();
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
    
    @Override
    public boolean editEduTechAdmin(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean createNewUnifyAdmin(String salutation, String firstName, String lastName, String email, String password, String fileName) { 
        try{
            UserEntity newStudent= new UserEntity(email,salutation,firstName,lastName,password,"unifyadmin",fileName);
            em.persist(newStudent);//may throw null pointer if em is not created with proper syntax.
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }
    
    @Override
    public ArrayList<ArrayList> getAllUnifyAdmins() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='unifyadmin'").getResultList();
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
    
    @Override
    public boolean editUnifyAdmin(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String email) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.userEmail= :email");
        q1.setParameter("email", email);
        for(Object o : q1.getResultList()){
            UserEntity u = (UserEntity) o;
            em.remove(o);
        }
    }
    

    

    

    
    
}
