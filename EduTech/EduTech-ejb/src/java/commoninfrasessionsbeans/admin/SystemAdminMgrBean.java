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
    public boolean createNewStudent(String salutation, String firstName, String lastName, String username, String password, String fileName) { 
        try{
            UserEntity newStudent= new UserEntity(username,salutation,firstName,lastName,password,"student",fileName);
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
            singleUser.add(userE.getUsername());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            
            entityList.add(singleUser);
            
        }
        return entityList;
    }
    @Override
    public ArrayList getUserInfo(String id) {
        ArrayList userInfo = new ArrayList();
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username= :username");
        q1.setParameter("username", id);
        for(Object o: q1.getResultList()){
            UserEntity user = (UserEntity) o;
            //store salutation (editable)
            userInfo.add(user.getUserSalutation());
            //store first name (editable)
            userInfo.add(user.getUserFirstName());
            //store last name (editable)
            userInfo.add(user.getUserLastName());
            //store username (pass in front but cannot edit)
            userInfo.add(user.getUsername());
            //store password (editable)
            userInfo.add(user.getUserPassword());           
            //format creation date (editable)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(user.getUserCreationDate());
            //store creation date (pass in front but cannot edit)
            userInfo.add(creationDate);
            //store user type (editable)
            userInfo.add(user.getUserType());
            //store image file (editable)
            userInfo.add(user.getImgFileName());
        }
        return userInfo;
    }
    
    @Override
    public boolean editUser(String username, String salutation, String firstName, String lastName, String password, String userType, String fileName) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username= :username");
        q1.setParameter("username", username);
        for(Object o:q1.getResultList()){
            UserEntity u = (UserEntity) o;
            u.setUserSalutation(salutation);
            u.setUserFirstName(firstName);
            u.setUserLastName(lastName);
            u.setUserPassword(password);
            u.setUserType(userType);
            //possible upgrade: delete old image file
            //update image file name. 
            u.setImgFileName(fileName);
            return true; 
        }
        System.out.println("No such user found");
        return false; 
    }
    
    @Override
    public boolean createNewInstructor(String salutation, String firstName, String lastName, String username, String password, String fileName) { 
        try{
            UserEntity newInstructor= new UserEntity(username,salutation,firstName,lastName,password,"instructor",fileName);
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
            singleUser.add(userE.getUsername());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            
            entityList.add(singleUser);
            
        }
        return entityList;
    }
    
    @Override
    public void deleteUser(String username) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username= :username");
        q1.setParameter("username", username);
        for(Object o : q1.getResultList()){
            UserEntity u = (UserEntity) o;
            em.remove(o);
        }
    }

    @Override
    public ArrayList<ArrayList> getAllAdmins() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='unifyadmin' OR u.userType='edutechadmin' OR u.userType='dualadmin'").getResultList();
        ArrayList entityList = new ArrayList();//to store the set of ArrayLists containing 1 entity information each. 
        for(Object o: results){
            UserEntity userE = (UserEntity) o;
            ArrayList singleUser = new ArrayList();
            //profile image
            singleUser.add(userE.getImgFileName());
            //full name
            singleUser.add(userE.getUserFirstName()+" "+userE.getUserLastName());
            //username
            singleUser.add(userE.getUsername());
            //creation date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            //admin type
            singleUser.add(userE.getUserType());
            entityList.add(singleUser);           
        }
        return entityList;    
    }
    
    @Override
    public boolean createNewAdmin(String salutation, String firstName, String lastName, String username, String password, String fileName, String adminType) { 
        try{
            UserEntity newStudent= new UserEntity(username,salutation,firstName,lastName,password,adminType.trim().toLowerCase(),fileName);
            em.persist(newStudent);//may throw null pointer if em is not created with proper syntax.
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }
    
}
