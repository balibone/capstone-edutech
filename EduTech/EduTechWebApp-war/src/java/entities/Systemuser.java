/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Derian
 */
@Entity
@Table(name = "SYSTEMUSER")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Systemuser.findAll", query = "SELECT s FROM Systemuser s"), @NamedQuery(name = "Systemuser.findByUsername", query = "SELECT s FROM Systemuser s WHERE s.username = :username"), @NamedQuery(name = "Systemuser.findByImgfilename", query = "SELECT s FROM Systemuser s WHERE s.imgfilename = :imgfilename"), @NamedQuery(name = "Systemuser.findByUseractivestatus", query = "SELECT s FROM Systemuser s WHERE s.useractivestatus = :useractivestatus"), @NamedQuery(name = "Systemuser.findByUsercreationdate", query = "SELECT s FROM Systemuser s WHERE s.usercreationdate = :usercreationdate"), @NamedQuery(name = "Systemuser.findByUserfirstname", query = "SELECT s FROM Systemuser s WHERE s.userfirstname = :userfirstname"), @NamedQuery(name = "Systemuser.findByUserlastname", query = "SELECT s FROM Systemuser s WHERE s.userlastname = :userlastname"), @NamedQuery(name = "Systemuser.findByUserpassword", query = "SELECT s FROM Systemuser s WHERE s.userpassword = :userpassword"), @NamedQuery(name = "Systemuser.findByUsersalutation", query = "SELECT s FROM Systemuser s WHERE s.usersalutation = :usersalutation"), @NamedQuery(name = "Systemuser.findByUsertype", query = "SELECT s FROM Systemuser s WHERE s.usertype = :usertype")})
public class Systemuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @Basic(optional = false) @NotNull @Size(min = 1, max = 255) @Column(name = "USERNAME")
    private String username;
    @Size(max = 255) @Column(name = "IMGFILENAME")
    private String imgfilename;
    @Column(name = "USERACTIVESTATUS")
    private Short useractivestatus;
    @Column(name = "USERCREATIONDATE") @Temporal(TemporalType.DATE)
    private Date usercreationdate;
    @Size(max = 255) @Column(name = "USERFIRSTNAME")
    private String userfirstname;
    @Size(max = 255) @Column(name = "USERLASTNAME")
    private String userlastname;
    @Size(max = 255) @Column(name = "USERPASSWORD")
    private String userpassword;
    @Size(max = 255) @Column(name = "USERSALUTATION")
    private String usersalutation;
    @Size(max = 255) @Column(name = "USERTYPE")
    private String usertype;

    public Systemuser() {
    }

    public Systemuser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgfilename() {
        return imgfilename;
    }

    public void setImgfilename(String imgfilename) {
        this.imgfilename = imgfilename;
    }

    public Short getUseractivestatus() {
        return useractivestatus;
    }

    public void setUseractivestatus(Short useractivestatus) {
        this.useractivestatus = useractivestatus;
    }

    public Date getUsercreationdate() {
        return usercreationdate;
    }

    public void setUsercreationdate(Date usercreationdate) {
        this.usercreationdate = usercreationdate;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersalutation() {
        return usersalutation;
    }

    public void setUsersalutation(String usersalutation) {
        this.usersalutation = usersalutation;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Systemuser)) {
            return false;
        }
        Systemuser other = (Systemuser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commoninfracontrollers.Systemuser[ username=" + username + " ]";
    }
    
}
