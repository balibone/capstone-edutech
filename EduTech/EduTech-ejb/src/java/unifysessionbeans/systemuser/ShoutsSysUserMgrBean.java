package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
import java.text.DateFormat;
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
import commoninfrastructureentities.UserEntity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import unifyentities.common.CategoryEntity;
import unifyentities.shouts.ShoutsEntity;
import unifyentities.shouts.ShoutsCommentsEntity;
import unifyentities.shouts.ShoutsBookmarksEntity;
import unifyentities.shouts.ShoutsReportEntity;
import unifyentities.shouts.ShoutsCommentsReportEntity;
import unifyentities.shouts.ShoutsLikesEntity;

@Stateless
public class ShoutsSysUserMgrBean implements ShoutsSysUserMgrBeanRemote {

    @PersistenceContext
    private EntityManager em;

    //private CategoryEntity categoryEntity;
    private ShoutsEntity shoutsEntity;
    private ShoutsCommentsEntity shoutsCommentsEntity;
    private ShoutsReportEntity shoutsReportEntity;
    private ShoutsCommentsReportEntity shoutsCommentReportEntity;
    private ShoutsBookmarksEntity shoutsBookmarksEntity;
    private ShoutsLikesEntity shoutsLikesEntity;
    private UserEntity userEntity;

    public List<Vector> viewShoutList() {
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutStatus='Active' ORDER BY c.shoutDate DESC");
        List<Vector> shoutList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsEntity shoutE = (ShoutsEntity) o;

            Vector shoutVec = new Vector();

            shoutVec.add(shoutE.getShoutID());
            shoutVec.add(df.format(shoutE.getShoutDate()));
            shoutVec.add(shoutE.getShoutStatus());
            shoutVec.add(shoutE.getShoutContent());
            shoutVec.add(shoutE.getShoutLat());
            shoutVec.add(shoutE.getShoutLong());
            shoutVec.add(shoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(shoutE.getShoutEditedDate()));

            //find listing posted time from current time
            long diff = currentDate.getTime() - shoutE.getShoutDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }

            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(shoutE.getShoutID()));
            shoutVec.add(getShoutsCommentsCount(shoutE.getShoutID()));

            //shoutVec.add(checkBookmarkShout(shoutE.getUserEntity().getUsername(),shoutE.getShoutID()));
            System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

    public List<Vector> viewShoutList(String shoutID) {
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutID= :shoutID");
        Long shoutIDLong = Long.parseLong(shoutID);
        q.setParameter("shoutID", shoutIDLong);
        List<Vector> shoutList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsEntity shoutE = (ShoutsEntity) o;

            Vector shoutVec = new Vector();

            shoutVec.add(shoutE.getShoutID());
            shoutVec.add(df.format(shoutE.getShoutDate()));
            shoutVec.add(shoutE.getShoutStatus());
            shoutVec.add(shoutE.getShoutContent());
            shoutVec.add(shoutE.getShoutLat());
            shoutVec.add(shoutE.getShoutLong());
            shoutVec.add(shoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(shoutE.getShoutEditedDate()));

            //find listing posted time from current time
            long diff = currentDate.getTime() - shoutE.getShoutDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }

            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(shoutE.getShoutID()));
            shoutVec.add(getShoutsCommentsCount(shoutE.getShoutID()));

            System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

    public List<Vector> viewShoutList2(String username) {
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutStatus='Active' ORDER BY c.shoutDate DESC");
        List<Vector> shoutList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsEntity shoutE = (ShoutsEntity) o;

            Vector shoutVec = new Vector();

            shoutVec.add(shoutE.getShoutID());
            shoutVec.add(df.format(shoutE.getShoutDate()));
            shoutVec.add(shoutE.getShoutStatus());
            shoutVec.add(shoutE.getShoutContent());
            shoutVec.add(shoutE.getShoutLat());
            shoutVec.add(shoutE.getShoutLong());
            shoutVec.add(shoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(shoutE.getShoutEditedDate()));

            //find listing posted time from current time
            long diff = currentDate.getTime() - shoutE.getShoutDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else {
                dateString = "now";
            }

            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(shoutE.getShoutID()));
            shoutVec.add(getShoutsCommentsCount(shoutE.getShoutID()));

            //check for user bookmarked of this post
            shoutVec.add(checkBookmarkShout(username, shoutE.getShoutID()));
            shoutVec.add(checkLikeShout(username, shoutE.getShoutID()));

            //System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

    public List<Vector> viewMyShoutList(String username) {
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutUser.username = :username ORDER BY c.shoutDate DESC");
        q.setParameter("username", username);
        List<Vector> myShoutList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsEntity myShoutE = (ShoutsEntity) o;

            Vector shoutVec = new Vector();

            shoutVec.add(myShoutE.getShoutID());
            shoutVec.add(df.format(myShoutE.getShoutDate()));
            shoutVec.add(myShoutE.getShoutStatus());
            shoutVec.add(myShoutE.getShoutContent());
            shoutVec.add(myShoutE.getShoutLat());
            shoutVec.add(myShoutE.getShoutLong());
            shoutVec.add(myShoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(myShoutE.getShoutEditedDate()));

            //find listing posted time from current time
            long diff = currentDate.getTime() - myShoutE.getShoutDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }else {
                dateString = "now";
            }

            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(myShoutE.getShoutID()));
            shoutVec.add(getShoutsCommentsCount(myShoutE.getShoutID()));

            //check for user bookmarked of this post
            shoutVec.add(checkBookmarkShout(username, myShoutE.getShoutID()));
            shoutVec.add(checkLikeShout(username, myShoutE.getShoutID()));

            //System.out.println(shoutVec.size());
            myShoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return myShoutList;
    }

    public List<Vector> viewMyBookmarkedShoutList(String username) {
        Query q = em.createQuery("SELECT c FROM ShoutsBookmarks c WHERE c.shoutsEntity.shoutStatus='Active' AND c.userEntity.username = :username ORDER BY c.shoutsEntity.shoutDate DESC");
        q.setParameter("username", username);
        List<Vector> myShoutList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsBookmarksEntity myShoutE = (ShoutsBookmarksEntity) o;

            Vector shoutVec = new Vector();

            shoutVec.add(myShoutE.getShoutsEntity().getShoutID());
            shoutVec.add(df.format(myShoutE.getShoutsEntity().getShoutDate()));
            shoutVec.add(myShoutE.getShoutsEntity().getShoutStatus());
            shoutVec.add(myShoutE.getShoutsEntity().getShoutContent());
            shoutVec.add(myShoutE.getShoutsEntity().getShoutLat());
            shoutVec.add(myShoutE.getShoutsEntity().getShoutLong());
            shoutVec.add(myShoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(myShoutE.getShoutsEntity().getShoutEditedDate()));

            //find listing posted time from current time
            long diff = currentDate.getTime() - myShoutE.getShoutsEntity().getShoutDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else {
                dateString = "Just posted";
            }

            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(myShoutE.getShoutsEntity().getShoutID()));
            shoutVec.add(getShoutsCommentsCount(myShoutE.getShoutsEntity().getShoutID()));

            //check for user bookmarked of this post
            shoutVec.add(checkBookmarkShout(username, myShoutE.getShoutsEntity().getShoutID()));
            shoutVec.add(checkLikeShout(username, myShoutE.getShoutsEntity().getShoutID()));

            //System.out.println(shoutVec.size());
            myShoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return myShoutList;
    }

    @Override
    public List<Vector> viewCommentList(String commentShoutID) {
        Query q = em.createQuery("SELECT l FROM ShoutsComments l WHERE l.shoutsEntity.shoutID = :shoutID AND l.commentStatus = 'Active' ORDER BY l.commentDate DESC");
        Long commentShoutIDLong = Long.parseLong(commentShoutID);
        q.setParameter("shoutID", commentShoutIDLong);
        List<Vector> commentList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ShoutsCommentsEntity shoutCommentE = (ShoutsCommentsEntity) o;

            Vector commentVec = new Vector();

            commentVec.add(shoutCommentE.getCommentID());
            //System.out.println(shoutCommentE.getCommentID());
            commentVec.add(df.format(shoutCommentE.getCommentDate()));
            //System.out.println(df.format(shoutCommentE.getCommentDate()));
            commentVec.add(shoutCommentE.getCommentContent());
            //System.out.println(shoutCommentE.getCommentContent());
            commentVec.add(shoutCommentE.getUserEntity().getUsername());
            //System.out.println(shoutCommentE.getUserEntity().getUsername());

            //find listing posted time from current time
            long diff = currentDate.getTime() - shoutCommentE.getCommentDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }else {
                dateString = "now";
            }

            commentVec.add(dateString);
            //System.out.println(dateString);

            //System.out.println(commentVec.size());
            commentList.add(commentVec);
        }
        System.out.println("ViewCommentList retrieved");
        return commentList;
    }

    @Override
    public String viewShoutContent(String shoutID) {

        shoutsEntity = lookupShout(shoutID);

        String shoutContent = shoutsEntity.getShoutContent();

        System.out.println("Shout content retrieved (ShoutsSysUserMgrBean.viewShoutContent)");
        return shoutContent;

    }

    @Override
    public String createShout(String shoutContent, String shoutPoster) {

        shoutsEntity = new ShoutsEntity();

        //System.out.println(shoutPoster);
        userEntity = lookupUnifyUser(shoutPoster);

        if (shoutsEntity.createShout(shoutContent)) {
            shoutsEntity.setUserEntity(userEntity);

            //temp
            shoutsEntity.setShoutEditedDate();
            shoutsEntity.setShoutLat("0");
            shoutsEntity.setShoutLong("0");

            em.persist(shoutsEntity);
            System.out.println("Shout created (ShoutsSysUserMgrBean.createShout)");
            return "Shout has been posted successfully!";
        } else {
            return "System is not feeling well and cannot shout at the moment :'(' Please try again later.";
        }

    }

    @Override
    public String deleteShout(String shoutID) {

        if (lookupShout(shoutID) == null) {
            return "There are some issues with this shout. Please try again.";
        } else {
            shoutsEntity = lookupShout(shoutID);

            UserEntity userE = shoutsEntity.getUserEntity();

            //ShoutsBookmarksEntity bookmarksE = shoutsEntity.getShoutsBookmarksSet();
            //shoutE.getShoutsCommentsSet().remove(shoutsEntity);
            //em.merge(shoutE);
            em.merge(userE);

            em.remove(shoutsEntity);
            em.flush();
            em.clear();
            return "Shout has been deleted successfully!";
        }
    }

    @Override
    public String createShoutReport(String shoutReportContent, String shoutPoster, String shoutID) {

        shoutsReportEntity = new ShoutsReportEntity();

        //System.out.println(shoutPoster);
        userEntity = lookupUnifyUser(shoutPoster);

        shoutsEntity = lookupShout(shoutID);

        if (shoutsReportEntity.addNewReport(shoutReportContent)) {
            shoutsReportEntity.setUserEntity(userEntity);
            shoutsReportEntity.setShoutsEntity(shoutsEntity);

            em.persist(shoutsReportEntity);
            System.out.println("Shout report created (ShoutsSysUserMgrBean.createShoutReport)");
            return "Shout has been reported successfully!";
        } else {
            return "System is not feeling well and cannot report your shout at the moment :'(' Please try again later.";
        }

    }

    @Override
    public String createShoutCommentReport(String shoutReportContent, String shoutPoster, String shoutCommentID) {

        shoutsCommentReportEntity = new ShoutsCommentsReportEntity();

        //System.out.println(shoutPoster);
        userEntity = lookupUnifyUser(shoutPoster);

        shoutsCommentsEntity = lookupShoutComment(shoutCommentID);

        if (shoutsCommentReportEntity.addNewReport(shoutReportContent)) {
            shoutsCommentReportEntity.setUserEntity(userEntity);
            shoutsCommentReportEntity.setShoutsCommentsEntity(shoutsCommentsEntity);

            em.persist(shoutsCommentReportEntity);
            System.out.println("Shout report created (ShoutsSysUserMgrBean.createShoutCommentReport)");
            return "Shout Comment #" + shoutCommentID + " has been reported successfully!";
        } else {
            return "System is not feeling well and cannot report your shout comment at the moment :'(' Please try again later.";
        }

    }

    @Override
    public String createShoutComment(String shoutCommentContent, String shoutPoster, String shoutID) {

        shoutsCommentsEntity = new ShoutsCommentsEntity();

        //System.out.println(shoutPoster);
        userEntity = lookupUnifyUser(shoutPoster);

        shoutsEntity = lookupShout(shoutID);

        if (shoutsCommentsEntity.addNewComment(shoutCommentContent)) {
            shoutsCommentsEntity.setCommentStatus("Active");
            shoutsCommentsEntity.setUserEntity(userEntity);
            shoutsCommentsEntity.setShoutsEntity(shoutsEntity);

            em.persist(shoutsCommentsEntity);
            System.out.println("Shout comment created (ShoutsSysUserMgrBean.createShoutComment)");
            return "Comment created for Shout #" + shoutID + " successfully!";
        } else {
            return "System is not feeling well and cannot create your shout comment at the moment :'(' Please try again later.";
        }

    }

    @Override
    public String editShoutComment(String shoutCommentContent, String shoutPoster, String commentID) {

        //shoutsCommentsEntity = new ShoutsCommentsEntity();
        //System.out.println(shoutPoster);
        //userEntity = lookupUnifyUser(shoutPoster);
        if (lookupShoutComment(commentID) == null) {
            //shoutsCommentsEntity.setUserEntity(userEntity);
            //shoutsCommentsEntity.setShoutsEntity(shoutsEntity);

            //em.persist(shoutsCommentsEntity);
            //System.out.println("Shout comment created (ShoutsSysUserMgrBean.createShoutComment)");
            return "System is not feeling well and cannot edit your shout comment at the moment :'(' Please try again later.";
            //return "Comment created for Shout #" + shoutID + " successfully!";
        } else {
            shoutsCommentsEntity = lookupShoutComment(commentID);
            shoutsCommentsEntity.setCommentContent(shoutCommentContent);
            em.merge(shoutsCommentsEntity);
            //return "System is not feeling well and cannot create your shout comment at the moment :'(' Please try again later.";
            System.out.println("Shout comment edited (ShoutsSysUserMgrBean.editShoutComment)");
            return "Comment updated for Shout Comment #" + commentID + " successfully!";
        }

    }

    @Override
    public String deleteShoutComment(String shoutCommentID) {

        if (lookupShoutComment(shoutCommentID) == null) {
            return "There are some issues with this comment. Please try again.";
        } else {
            shoutsCommentsEntity = lookupShoutComment(shoutCommentID);

            UserEntity userE = shoutsCommentsEntity.getUserEntity();
            ShoutsEntity shoutE = shoutsCommentsEntity.getShoutsEntity();

            shoutE.getShoutsCommentsSet().remove(shoutsCommentsEntity);
            userE.getShoutsCommentsSet().remove(shoutsCommentsEntity);

            em.merge(shoutE);
            em.merge(userE);

            em.remove(shoutsCommentsEntity);
            em.flush();
            em.clear();
            return "Shout comment has been deleted successfully!";
        }
    }

    @Override
    public String bookmarkShout(String user, String shoutID) {

        shoutsBookmarksEntity = new ShoutsBookmarksEntity();

        userEntity = lookupUnifyUser(user);

        shoutsEntity = lookupShout(shoutID);

        Long shoutIDLong = Long.parseLong(shoutID);

        if (lookupBookmarkUser(shoutIDLong, user) != null) {
            return "Shout has already been bookmarked";
        } else {
            shoutsBookmarksEntity.addBookmark(userEntity, shoutsEntity);
            //shoutsBookmarksEntity.setUserEntity(userEntity);
            //shoutsBookmarksEntity.setShoutsEntity(shoutsEntity);

            em.persist(shoutsBookmarksEntity);
            System.out.println("Shout bookmark created (ShoutsSysUserMgrBean.bookmarkShout)");
            return "Shout has been bookmarked successfully!";
        }

    }

    @Override
    public String unbookmarkShout(String user, String shoutID) {

        shoutsBookmarksEntity = new ShoutsBookmarksEntity();

        userEntity = lookupUnifyUser(user);

        shoutsEntity = lookupShout(shoutID);

        Long shoutIDLong = Long.parseLong(shoutID);

        if (lookupBookmarkUser(shoutIDLong, user) != null) {
            //remove bookmark
            shoutsBookmarksEntity = lookupBookmarkUser(shoutIDLong, user);

            UserEntity userE = shoutsBookmarksEntity.getUserEntity();

            ShoutsEntity shoutE = shoutsBookmarksEntity.getShoutsEntity();

            shoutE.getShoutsBookmarksSet().remove(shoutsBookmarksEntity);
            userE.getShoutsBookmarksSet().remove(shoutsBookmarksEntity);

            em.merge(shoutE);
            em.merge(userE);

            em.remove(shoutsBookmarksEntity);
            em.flush();
            em.clear();
            return "Bookmark has been removed successfully!";
        } else {
            return "Error removing bookmark. Please try again later.";
        }

    }

    //@Override
    public Boolean checkBookmarkShout(String user, Long shoutID) {

        shoutsBookmarksEntity = new ShoutsBookmarksEntity();

        userEntity = lookupUnifyUser(user);

        String shoutIDString = String.valueOf(shoutID);

        shoutsEntity = lookupShout(shoutIDString);

        //Long shoutIDLong = Long.parseLong(shoutID);
        if (lookupBookmarkUser(shoutID, user) != null) {
            return true;
        } else {
            //shoutsBookmarksEntity.addBookmark(userEntity, shoutsEntity);
            //shoutsBookmarksEntity.setUserEntity(userEntity);
            //shoutsBookmarksEntity.setShoutsEntity(shoutsEntity);

            //em.persist(shoutsBookmarksEntity);
            //System.out.println("Shout bookmark created (ShoutsSysUserMgrBean.bookmarkShout)");
            return false;
        }

    }

    @Override
    public String likeShout(String user, String shoutID) {

        shoutsLikesEntity = new ShoutsLikesEntity();

        userEntity = lookupUnifyUser(user);

        shoutsEntity = lookupShout(shoutID);

        Long shoutIDLong = Long.parseLong(shoutID);

        if (lookupLikeUser(shoutIDLong, user) != null) {
            return "Shout has already been liked";
        } else {
            shoutsLikesEntity.addNewLike(userEntity, shoutsEntity);
            //shoutsBookmarksEntity.setUserEntity(userEntity);
            //shoutsBookmarksEntity.setShoutsEntity(shoutsEntity);

            em.persist(shoutsLikesEntity);
            System.out.println("Shout like created (ShoutsSysUserMgrBean.likeShout)");
            return "Shout has been liked successfully!";
        }

    }

    @Override
    public String unlikeShout(String user, String shoutID) {

        shoutsLikesEntity = new ShoutsLikesEntity();

        userEntity = lookupUnifyUser(user);

        shoutsEntity = lookupShout(shoutID);

        Long shoutIDLong = Long.parseLong(shoutID);

        if (lookupLikeUser(shoutIDLong, user) != null) {
            //unlike
            shoutsLikesEntity = lookupLikeUser(shoutIDLong, user);

            UserEntity userE = shoutsLikesEntity.getUserEntity();

            ShoutsEntity shoutE = shoutsLikesEntity.getShoutsEntity();

            shoutE.getShoutsLikesSet().remove(shoutsLikesEntity);
            userE.getShoutsLikesSet().remove(shoutsLikesEntity);

            em.merge(shoutE);
            em.merge(userE);

            em.remove(shoutsLikesEntity);
            em.flush();
            em.clear();
            return "Shout has been unliked successfully!";
        } else {

            return "Error unliking shout. Please try again later.";
        }

    }

    //@Override
    public Boolean checkLikeShout(String user, Long shoutID) {

        shoutsLikesEntity = new ShoutsLikesEntity();

        userEntity = lookupUnifyUser(user);

        String shoutIDString = String.valueOf(shoutID);

        shoutsEntity = lookupShout(shoutIDString);

        //Long shoutIDLong = Long.parseLong(shoutID);
        if (lookupLikeUser(shoutID, user) != null) {
            return true;
        } else {

            return false;
        }

    }

    // SHOUTS LIKES COUNT
    public Long getShoutsLikesCount(long shoutID) {
        Long shoutsLikesCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM ShoutsLikes l WHERE l.shoutsEntity.shoutID = :shoutID");
        q.setParameter("shoutID", shoutID);
        try {
            shoutsLikesCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ShoutsSysUserMgrBean.getShoutsLikesCount().getSingleResult()");
            ex.printStackTrace();
        }
        return shoutsLikesCount;
    }

    // SHOUTS COMMENTS COUNT
    public Long getShoutsCommentsCount(long shoutID) {
        Long shoutsLikesCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.commentID) FROM ShoutsComments l WHERE l.shoutsEntity.shoutID = :shoutID AND l.commentStatus = 'Active'");
        q.setParameter("shoutID", shoutID);
        try {
            shoutsLikesCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ShoutsSysUserMgrBean.getShoutsCommentsCount().getSingleResult()");
            ex.printStackTrace();
        }
        return shoutsLikesCount;
    }

    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity) q.getSingleResult();
            //System.out.println("FOUND USER");
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }

    public ShoutsEntity lookupShout(String shoutID) {
        ShoutsEntity se = new ShoutsEntity();
        Long shoutIDLong = Long.parseLong(shoutID);
        try {
            Query q = em.createQuery("SELECT u FROM Shouts u WHERE u.shoutID = :shoutID");
            q.setParameter("shoutID", shoutIDLong);
            se = (ShoutsEntity) q.getSingleResult();
            //System.out.println("FOUND SHOUT");
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Shout cannot be found. " + enfe.getMessage());
            em.remove(se);
            se = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Shout does not exist. " + nre.getMessage());
            em.remove(se);
            se = null;
        }
        return se;
    }

    public ShoutsCommentsEntity lookupShoutComment(String shoutCommentID) {
        ShoutsCommentsEntity sce = new ShoutsCommentsEntity();
        Long shoutCommentIDLong = Long.parseLong(shoutCommentID);
        try {
            Query q = em.createQuery("SELECT u FROM ShoutsComments u WHERE u.commentID = :commentID");
            q.setParameter("commentID", shoutCommentIDLong);
            sce = (ShoutsCommentsEntity) q.getSingleResult();
            System.out.println("FOUND SHOUT COMMENT");
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Shout Comment cannot be found. " + enfe.getMessage());
            em.remove(sce);
            sce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Shout Comment does not exist. " + nre.getMessage());
            em.remove(sce);
            sce = null;
        }
        return sce;
    }

    public ShoutsBookmarksEntity lookupBookmark(long shoutID, String username) {
        ShoutsBookmarksEntity sbe = new ShoutsBookmarksEntity();
        try {
            Query q = em.createQuery("SELECT l FROM ShoutsBookmarks l WHERE l.shoutsEntity.shoutID = :shoutID AND l.userEntity.username = :username");
            q.setParameter("shoutID", shoutID);
            q.setParameter("username", username);
            sbe = (ShoutsBookmarksEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Bookmark cannot be found. " + enfe.getMessage());
            em.remove(sbe);
            sbe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Bookmark does not exist. " + nre.getMessage());
            em.remove(sbe);
            sbe = null;
        }
        return sbe;
    }

    public ShoutsBookmarksEntity lookupBookmarkUser(long shoutID, String username) {
        ShoutsBookmarksEntity sbe = new ShoutsBookmarksEntity();
        try {
            Query q = em.createQuery("SELECT l FROM ShoutsBookmarks l WHERE l.shoutsEntity.shoutID = :shoutID AND l.userEntity.username = :username");
            q.setParameter("shoutID", shoutID);
            q.setParameter("username", username);
            //em.remove(sbe);
            //System.out.println("ShoutsSysUserMgrBean.lookupBookmarkUser");
            sbe = (ShoutsBookmarksEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Bookmark cannot be found. " + enfe.getMessage());
            em.remove(sbe);
            sbe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Bookmark does not exist. " + nre.getMessage());
            em.remove(sbe);
            sbe = null;
        }
        return sbe;
    }

    public ShoutsLikesEntity lookupLikeUser(Long shoutID, String username) {
        ShoutsLikesEntity sbe = new ShoutsLikesEntity();
        try {
            Query q = em.createQuery("SELECT l FROM ShoutsLikes l WHERE l.shoutsEntity.shoutID = :shoutID AND l.userEntity.username = :username");
            q.setParameter("shoutID", shoutID);
            q.setParameter("username", username);
            //em.remove(sbe);
            //System.out.println("ShoutsSysUserMgrBean.lookupLikeUser");
            sbe = (ShoutsLikesEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Like cannot be found. " + enfe.getMessage());
            em.remove(sbe);
            sbe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Like does not exist. " + nre.getMessage());
            em.remove(sbe);
            sbe = null;
        }
        return sbe;
    }

}
