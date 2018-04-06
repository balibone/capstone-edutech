/**
 * *************************************************************************************
 *   Title:                  ChatEntity.java
 *   Purpose:                LIST OF CHAT-RELATED ATTRIBUTES FOR USER CHATS
 *   Created & Modified By:  TAN CHIN WEE WINSTON
 *   Date:                   27 MARCH 2018
 *   Code version:           1.0
 *   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
 **************************************************************************************
 */
package unifyentities.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import unifyentities.marketplace.ItemEntity;
import commoninfrastructureentities.UserEntity;

@Entity(name = "Chat")
public class ChatEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatID;
    private String chatReceiverID;
    private String itemBuyerID;
    private String itemSellerID;
    private String chatStatus;
    private String chatContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date chatPostingDate;

    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private ItemEntity itemEntity;

    @PrePersist
    public void creationDate() {
        this.chatPostingDate = new Date();
    }

    /* MISCELLANEOUS METHODS */
    public boolean createChat(String chatReceiverID, String itemBuyerID, String itemSellerID, String chatContent) {
        this.chatReceiverID = chatReceiverID;
        this.itemBuyerID = itemBuyerID;
        this.itemSellerID = itemSellerID;
        this.chatStatus = "Unread";
        this.chatContent = chatContent;
        return true;
    }

    /* GETTER METHODS */
    public Long getChatID() { return chatID; }
    public String getChatReceiverID() { return chatReceiverID; }
    public String getItemBuyerID() { return itemBuyerID; }
    public String getItemSellerID() { return itemSellerID; }
    public String getChatStatus() { return chatStatus; }
    public String getChatContent() { return chatContent; }
    public Date getChatPostingDate() { return chatPostingDate; }
    public UserEntity getUserEntity() { return userEntity; }
    public ItemEntity getItemEntity() { return itemEntity; }
    
    /* SETTER METHODS */
    public void setChatID(Long chatID) { this.chatID = chatID; }
    public void setChatReceiverID(String chatReceiverID) { this.chatReceiverID = chatReceiverID; }
    public void setItemBuyerID(String itemBuyerID) { this.itemBuyerID = itemBuyerID; }
    public void setItemSellerID(String itemSellerID) { this.itemSellerID = itemSellerID; }
    public void setChatStatus(String chatStatus) { this.chatStatus = chatStatus; }
    public void setChatContent(String chatContent) { this.chatContent = chatContent; }
    public void setChatPostingDate(Date chatPostingDate) { this.chatPostingDate = chatPostingDate; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemEntity(ItemEntity itemEntity) { this.itemEntity = itemEntity; }
}