/***************************************************************************************
*   Title:                  VoicesSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY COMPANY REVIEW - SYSUSER (EDUBOX)
*   Created & Modified By:  ZHU XINYI
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.CategoryEntity;
import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyRequestEntity;
import unifyentities.voices.CompanyReviewEntity;

@Stateless
public class VoicesSysUserMgrBean implements VoicesSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CompanyEntity companyEntity;
    private CategoryEntity categoryEntity;
    private CompanyReviewEntity reviewEntity;
    private CompanyRequestEntity requestEntity;
    
    public List<Vector> viewCompanyList() {
        Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyStatus='Active' AND c.categoryEntity.categoryActiveStatus = '1'");
        List<Vector> companyList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()) {
            CompanyEntity companyE = (CompanyEntity) o;
            Collection<CompanyReviewEntity> reviewList = companyE.getCompanyReviewSet();
            Vector companyVec = new Vector();
            
            companyVec.add(companyE.getCompanyID());
            companyVec.add(companyE.getCompanyImage());
            companyVec.add(companyE.getCompanyName());
            companyVec.add(companyE.getCategoryEntity().getCategoryName());
            companyVec.add(companyE.getCompanyWebsite());
            companyVec.add(companyE.getCompanyHQ());
            companyVec.add(companyE.getCompanySize());
            companyVec.add(reviewList.size());
            if (reviewList.size()!=0) {
                List<Date> reviewDateList = new ArrayList<Date>();
                double aveReviewRating = 0.0;
                for(Object obj: reviewList) {
                    CompanyReviewEntity cre = (CompanyReviewEntity) obj;
                    reviewDateList.add(cre.getReviewDate());
                    aveReviewRating += cre.getReviewRating();
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                companyVec.add(df.format(reviewDateList.get(reviewDateList.size()-1)));
                companyVec.add(aveReviewRating/reviewDateList.size());
            } else {
                companyVec.add("----/--/--");
                companyVec.add(0.0);
            }
            
            companyList.add(companyVec);
        }
        return companyList;
    }

}
