/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unifyentities.voices;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Reference")
public class ReferenceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long referenceID;
    private String refereeName;
    private String refereeCompany;
    private String refereePosition;
    private String referenceContent;
    private ResumeEntity resumeEntity;
    
    public boolean create(String refereeName, String refereeCompany, String refereePosition, String referenceContent, ResumeEntity resumeEntity) {
        this.setReferenceID(System.nanoTime());
        this.refereeName = refereeName;
        this.refereeCompany = refereeCompany;
        this.refereePosition = refereePosition;
        this.referenceContent = referenceContent;
        this.resumeEntity = resumeEntity;
        return true;
    }

    public Long getReferenceID() { return referenceID; }
    public String getRefereeName() { return refereeName; }
    public String getRefereeCompany() { return refereeCompany; }
    public String getRefereePosition() { return refereePosition; }
    public String getReferenceContent() { return referenceContent; }
    public ResumeEntity getResumeEntity() { return resumeEntity; }

    public void setReferenceID(Long referenceID) { this.referenceID = referenceID; }
    public void setRefereeName(String refereeName) { this.refereeName = refereeName; }
    public void setRefereeCompany(String refereeCompany) { this.refereeCompany = refereeCompany; }
    public void setRefereePosition(String refereePosition) { this.refereePosition = refereePosition; }
    public void setReferenceContent(String referenceContent) { this.referenceContent = referenceContent;}
    public void setResumeEntity(ResumeEntity resumeEntity) { this.resumeEntity = resumeEntity;}
}
