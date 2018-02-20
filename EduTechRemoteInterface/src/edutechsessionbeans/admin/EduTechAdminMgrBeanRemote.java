/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans.admin;

import javax.ejb.Remote;

/**
 *
 * @author Derian
 */
@Remote
public interface EduTechAdminMgrBeanRemote {

    public Object getModuleCount();

    public Object getSemesterCount();
    
}
