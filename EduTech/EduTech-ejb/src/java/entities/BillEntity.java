package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Bill")
public class BillEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billNum;
    private Long poNum;
    @Temporal(TemporalType.DATE)
    private Date creationDateTime;
    @Temporal(TemporalType.DATE)
    private Date dueDateTime;
    private String paymentReferenceNum;
    private Double discountFactor;
    @Temporal(TemporalType.DATE)
    private Date discountDateTime;
    private String paymentMode;
    private String status;
    @Temporal(TemporalType.DATE)
    private Date paymentDateTime;
    private boolean active;

    public Long getPoNum() {
        return poNum;
    }

    public void setPoNum(Long poNum) {
        this.poNum = poNum;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Date dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public String getPaymentReferenceNum() {
        return paymentReferenceNum;
    }

    public void setPaymentReferenceNum(String paymentReferenceNum) {
        this.paymentReferenceNum = paymentReferenceNum;
    }

    public Double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(Double discountFactor) {
        this.discountFactor = discountFactor;
    }

    public Date getDiscountDateTime() {
        return discountDateTime;
    }

    public void setDiscountDateTime(Date discountDateTime) {
        this.discountDateTime = discountDateTime;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getBillNum() {
        return billNum;
    }

    public void setBillNum(Long billNum) {
        this.billNum = billNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billNum != null ? billNum.hashCode() : 0);
        return hash;
    }
}