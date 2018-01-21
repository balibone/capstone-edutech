package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "SalesOrder")
public class SalesOrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long salesOrderNumber;
    private String deliveryNotes;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @OneToOne
    private ContactEntity customer;
    private String creditCardNum;
    @OneToOne
    private InvoiceEntity invoice;
    private Double discountAmt;
    private Double shippingAmt;
    private Double totalPrice;
    @ManyToMany
    @JoinTable(name="Ordered Items")
    private Collection<ItemEntity> itemList = new ArrayList<ItemEntity>();
    private String status;
    private boolean isPaid;
    
    /* DEFAULT CONSTRUCTOR */
    public SalesOrderEntity(){
        this.creationDateTime = new Date();
        this.isPaid = false;
    }
    
    public SalesOrderEntity(String deliveryNotes, ContactEntity customer, String creditCardNum, InvoiceEntity invoice, Double discountAmt, Double shippingAmt, String status) {       
        this.deliveryNotes = deliveryNotes;
        this.creationDateTime = new Date();
        this.customer = customer;
        this.creditCardNum = creditCardNum;
        this.invoice = invoice;
        this.discountAmt = discountAmt;
        this.shippingAmt = shippingAmt;
        this.status = status;
        this.isPaid = false;
    }

    /* GETTER METHODS */
    public Double getTotalPrice() { return totalPrice; }
    public String getDeliveryNotes() { return deliveryNotes; }
    public Date getCreationDateTime() { return creationDateTime; }
    public ContactEntity getCustomer() { return customer; }
    public String getCreditCardNum() { return creditCardNum; }
    public InvoiceEntity getInvoice() { return invoice; }
    public Double getDiscountAmt() { return discountAmt; }
    public Double getShippingAmt() { return shippingAmt; }
    public Collection<ItemEntity> getItemList() { return itemList; }
    public String getStatus() { return status; }
    public boolean isIsPaid() { return isPaid; }
    public Long getSalesOrderNumber() { return salesOrderNumber; }
    
    /* SETTER METHODS */
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public void setDeliveryNotes(String deliveryNotes) { this.deliveryNotes = deliveryNotes; }
    public void setCreationDateTime(Date creationDateTime) { this.creationDateTime = creationDateTime; }
    public void setCustomer(ContactEntity customer) { this.customer = customer; }
    public void setCreditCardNum(String creditCardNum) { this.creditCardNum = creditCardNum;}
    public void setInvoice(InvoiceEntity invoice) { this.invoice = invoice; }
    public void setDiscountAmt(Double discountAmt) { this.discountAmt = discountAmt; }
    public void setShippingAmt(Double shippingAmt) { this.shippingAmt = shippingAmt; }
    public void setItemList(Collection<ItemEntity> itemList) { this.itemList = itemList; }
    public void setStatus(String status) { this.status = status; }
    public void setIsPaid(boolean isPaid) { this.isPaid = isPaid; }
    public void setSalesOrderNumber(Long salesOrderNumber) { this.salesOrderNumber = salesOrderNumber; }
}