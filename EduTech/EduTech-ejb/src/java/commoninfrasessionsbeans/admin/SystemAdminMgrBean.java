package commoninfrasessionsbeans.admin;

import commoninfrastructureentities.UserEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import commoninfrasessionbeans.CommonInfraMgrBeanRemote;

@Stateless
public class SystemAdminMgrBean implements SystemAdminMgrBeanRemote {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    CommonInfraMgrBeanRemote cmb;
    
    @Override
    public boolean createNewStudent(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName) { 
        try{
            UserEntity newStudent= new UserEntity(username,salutation,firstName,lastName,cmb.encodePassword(username, String.valueOf(Math.random())),"student",fileName,email,contactNum);
            em.persist(newStudent);
            cmb.sendCreateEmail(username, localPort);
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
            //0
            singleUser.add(userE.getImgFileName());
            //1
            singleUser.add(userE.getUserFirstName()+" "+userE.getUserLastName());
            //2
            singleUser.add(userE.getUsername());
            //3
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            //4
            boolean active = userE.getUserActiveStatus();
            if(active){
                singleUser.add("Active");
            }else{
                singleUser.add("Inactive");
            }
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
            //0
            userInfo.add(user.getUserSalutation());
            //1
            userInfo.add(user.getUserFirstName());
            //2
            userInfo.add(user.getUserLastName());
            //3
            userInfo.add(user.getEmail());
            //4
            userInfo.add(user.getContactNum());
            //5
            userInfo.add(user.getUsername());
            //6
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(user.getUserCreationDate());
            userInfo.add(creationDate);
            //7
            userInfo.add(user.getUserType());
            //8
            userInfo.add(user.getImgFileName());
        }
        return userInfo;
    }
    
    @Override
    public boolean editUser(String username, String salutation, String firstName, String lastName, String userType, String fileName, String email, String contactNum) {
        System.out.println("user being edited is "+username);
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username= :username");
        q1.setParameter("username", username);
        for(Object o:q1.getResultList()){
            UserEntity u = (UserEntity) o;
            u.setUserSalutation(salutation);
            u.setUserFirstName(firstName);
            u.setUserLastName(lastName);
            u.setUserType(userType);
            //update image file name. 
            u.setImgFileName(fileName);
            u.setEmail(email);
            u.setContactNum(contactNum);
            return true; 
        }
        System.out.println("No such user found");
        return false; 
    }
    
    @Override
    public boolean createNewInstructor(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName) { 
        try{
            UserEntity newInstructor= new UserEntity(username,salutation,firstName,lastName,cmb.encodePassword(username, String.valueOf(Math.random())),"instructor",fileName,email,contactNum);
            em.persist(newInstructor);
            cmb.sendCreateEmail(username, localPort);
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
            //0
            singleUser.add(userE.getImgFileName());
            //1
            singleUser.add(userE.getUserFirstName()+" "+userE.getUserLastName());
            //2
            singleUser.add(userE.getUsername());
            //3
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            //4
            boolean active = userE.getUserActiveStatus();
            if(active){
                singleUser.add("Active");
            }else{
                singleUser.add("Inactive");
            }
            entityList.add(singleUser);
            
        }
        return entityList;
    }
    
    @Override
    public void toggleUserStatus(String username) {
        Query q1 = em.createQuery("SELECT u FROM SystemUser u WHERE u.username= :username");
        q1.setParameter("username", username);
        for(Object o : q1.getResultList()){
            UserEntity u = (UserEntity) o;
            Boolean activeStatus = u.getUserActiveStatus();
            u.setUserActiveStatus(!activeStatus);
            if(u.getUserActiveStatus()){
                System.out.println(username+" is now active");
            }else{
                System.out.println(username+" is now inactive");
            }
        }
    }

    @Override
    public ArrayList<ArrayList> getAllAdmins() {
        List results = em.createQuery("SELECT u FROM SystemUser u WHERE u.userType='unifyadmin' OR u.userType='edutechadmin' OR u.userType='dualadmin' OR u.userType='superadmin'").getResultList();
        ArrayList entityList = new ArrayList();//to store the set of ArrayLists containing 1 entity information each. 
        for(Object o: results){
            UserEntity userE = (UserEntity) o;
            ArrayList singleUser = new ArrayList();
            //0
            singleUser.add(userE.getImgFileName());
            //1
            singleUser.add(userE.getUserFirstName()+" "+userE.getUserLastName());
            //2
            singleUser.add(userE.getUsername());
            //3
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String creationDate = dateFormat.format(userE.getUserCreationDate());
            singleUser.add(creationDate);
            //4 (admin type)
            singleUser.add(userE.getUserType());
            //5
            boolean active = userE.getUserActiveStatus();
            if(active){
                singleUser.add("Active");
            }else{
                singleUser.add("Inactive");
            }
            entityList.add(singleUser);           
        }
        return entityList;    
    }
    
    @Override
    public boolean createNewAdmin(int localPort, String salutation, String firstName, String lastName, String email, String contactNum, String username, String fileName, String adminType) { 
        try{
            UserEntity newAdmin= new UserEntity(username,salutation,firstName,lastName,cmb.encodePassword(username, String.valueOf(Math.random())),adminType,fileName,email,contactNum);
            em.persist(newAdmin);
            cmb.sendCreateEmail(username, localPort);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false; 
        }
    }

    @Override
    public Long getUserCount(String type) {
        Query q1 = em.createQuery("SELECT COUNT(DISTINCT u.username) FROM SystemUser u WHERE u.userActiveStatus=1 AND u.userType = :type");
        q1.setParameter("type", type);
        Long count = new Long(0);
        try{
            count = (Long)q1.getSingleResult();
        }catch(Exception e){
            System.out.println("Exception in SystemAdminMgrBean.getUserCount().getSingleResult()");
            e.printStackTrace();
        }
        return count;
    }
    
}
