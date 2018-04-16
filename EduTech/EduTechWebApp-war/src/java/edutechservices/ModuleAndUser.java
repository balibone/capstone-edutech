/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModuleAndUser {

    private String moduleCode = "";
    private String username = "";
    
    public ModuleAndUser() {
    }
    
    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

