package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "PurchaseOrder")
public class PurchaseOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long poNum;
    private String rAddress;
    private String rContactPerson;
    private String rContactNum;
    @Temporal(TemporalType.DATE)
    private Date deliveryDateTime;
    private String poNotes;
    @Temporal(TemporalType.DATE)
    private Date poDateTime;
    private boolean active;
    private Long vContactNum;
    private String vContactPerson;
    private Double shippingAmount; 
    private Long vBillingAddress;
    private String status;
    private String vCompanyName;
    private List<Vector> itemList;
    
    public Long getPoNum() {
        return poNum;
    }

    public void setPoNum(Long id) {
        this.poNum = id;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrContactPerson() {
        return rContactPerson;
    }

    public void setrContactPerson(String rContactPerson) {
        this.rContactPerson = rContactPerson;
    }

    public String getrContactNum() {
        return rContactNum;
    }

    public void setrContactNum(String rContactNum) {
        this.rContactNum = rContactNum;
    }

    public Date getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(Date deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getPoNotes() {
        return poNotes;
    }

    public void setPoNotes(String poNotes) {
        this.poNotes = poNotes;
    }

    public Date getPoDateTime() {
        return poDateTime;
    }

    public void setPoDateTime(Date poDateTime) {
        this.poDateTime = poDateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getvContactNum() {
        return vContactNum;
    }

    public void setvContactNum(Long vContactNum) {
        this.vContactNum = vContactNum;
    }

    public String getvContactPerson() {
        return vContactPerson;
    }

    public void setvContactPerson(String vContactPerson) {
        this.vContactPerson = vContactPerson;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Long getvBillingAddress() {
        return vBillingAddress;
    }

    public void setvBillingAddress(Long vBillingAddress) {
        this.vBillingAddress = vBillingAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getvCompanyName() {
        return vCompanyName;
    }

    public void setvCompanyName(String vCompanyName) {
        this.vCompanyName = vCompanyName;
    }

    public List<Vector> getItemList() {
        return itemList;
    }

    public void setItemList(List<Vector> itemList) {
        this.itemList = itemList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poNum != null ? poNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrderEntity)) {
            return false;
        }
        PurchaseOrderEntity other = (PurchaseOrderEntity) object;
        if ((this.poNum == null && other.poNum != null) || (this.poNum != null && !this.poNum.equals(other.poNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchaseOrderEntity[ id=" + poNum + " ]";
    }
    
}