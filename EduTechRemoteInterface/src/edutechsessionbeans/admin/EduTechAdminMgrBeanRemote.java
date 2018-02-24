/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans.admin;

import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Derian
 */
@Remote
public interface EduTechAdminMgrBeanRemote {

    public Object getModuleCount();

    public Object getSemesterCount();

    public boolean createSemester(String parameter, String parameter0, String parameter1);

    public ArrayList getAllSemesters();

    public ArrayList getSemesterInfo(String id);

    public void deactivateSemester(String id);

    public boolean editSemester(String parameter, String parameter0, String parameter1, String parameter3);

    public boolean createModule(String moduleCode, String name, Long modularCredit, String description, Long semID);

    public ArrayList getAllModules();

    public void deactivateModule(String id);
    
}
