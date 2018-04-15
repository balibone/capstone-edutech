package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ShoutsSysUserMgrBeanRemote {
    
    public List<Vector> viewShoutList();
    public List<Vector> viewShoutList(String shoutID);
    public List<Vector> viewShoutList2(String username);
    public List<Vector> viewMyShoutList(String username);
    public List<Vector> viewMyBookmarkedShoutList(String username);
    public List<Vector> viewCommentList(String commentShoutID);
    public String viewShoutContent(String shoutID);
    public String createShout(String shoutContent, String shoutPoster);
    public String deleteShout(String shoutID);
    public String createShoutReport(String shoutReportContent, String shoutPoster, String shoutID);
    public String createShoutCommentReport(String shoutReportContent, String shoutPoster, String shoutCommentID);
    public String createShoutComment(String shoutCommentContent, String shoutPoster, String shoutID);
    public String editShoutComment(String shoutCommentContent, String shoutPoster, String commentID);
    public String deleteShoutComment(String shoutCommentID);
    public String bookmarkShout(String user, String shoutID);
    public String unbookmarkShout(String user, String shoutID);
    public String likeShout(String user, String shoutID);
    public String unlikeShout(String user, String shoutID);
    
}