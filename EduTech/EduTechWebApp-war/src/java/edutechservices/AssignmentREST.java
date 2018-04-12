/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.module.AssignmentEntity;
import edutechsessionbeans.CommonMgrBean;
import edutechsessionbeans.GroupMgrBean;
import edutechsessionbeans.ModuleMgrBean;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@RequestScoped
@Path("assignment")
public class AssignmentREST {
    
    @EJB
    CommonMgrBean cmb;
    @EJB
    GroupMgrBean etr;
    @EJB
    ModuleMgrBean mmb;
    @Context
    private ServletContext context;
    @Context 
    private HttpServletRequest request;
    @Context 
    private HttpServletResponse response;
    
    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> getAllAssignments() {
        return mmb.getAllAssignments();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity getOneAssignment(@PathParam("id") String id){
        return mmb.getOneAssignment(Long.valueOf(id));
    }
    
    @POST 
    @Path("individual")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity createIndividualAssignment(AssignmentEntity ass) {
//        return ass;
        return mmb.createIndividualAssignment(ass);
    }
    
    @POST 
    @Path("group/{numOfGroups}/{groupSize}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity createGroupAssignment(AssignmentEntity ass, @PathParam("numOfGroups") String numOfGroups,
            @PathParam("groupSize") String groupSize) {
        return mmb.createGroupAssignment(ass, numOfGroups, groupSize);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> deleteAssignment(@PathParam("id") String id) {
        mmb.deleteAssignment(Long.valueOf(id));
        return mmb.getAllAssignments();
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity editAssignment(@PathParam("id") String id, AssignmentEntity replacement){
        return mmb.editAssignment(Long.valueOf(id), replacement);
    }

    @GET
    @Path("module/{moduleCode}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> getModuleAssignments(@PathParam("moduleCode") String moduleCode){
        return etr.getModuleAssignments(moduleCode);
    }
    
    @POST
    @Path("submit/{assignmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public AssignmentEntity submitAssignment(@PathParam("assignmentId") String assignmentId) throws FileUploadException, IOException, Exception{
        String title ="";
        String fileName="";
        String username="";
        AssignmentEntity ass = new AssignmentEntity();
        String appPath = context.getRealPath("");
        System.out.println("app path is "+ appPath);
        String truncatedAppPath = appPath.replace("dist"
                +File.separator+"gfdeploy"+File.separator+"EduTech"+File.separator+"EduTechWebApp-war_war", "");
        System.out.println("truncated path is "+truncatedAppPath);
        
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            //START OF FILE UPLOAD
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Configure a repository (to ensure a secure temp location is used)
            File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                
                if (item.isFormField()) {
                    if(item.getFieldName().trim().equalsIgnoreCase("title")){
                        title=item.getString();
                        System.out.println("title is "+title);
                    }else if(item.getFieldName().trim().equalsIgnoreCase("createdBy")){
                        username=item.getString();
                        System.out.println("uploader is "+username);
                    }
                }else if(!item.isFormField()){
                    fileName = LocalDateTime.now().withNano(0).toString().replaceAll("-", "").replaceAll(":", "")+"qup"+item.getName();
                    System.out.println("file name is "+fileName);
                    
                    
                    //where to save file.
                    String fileDir = truncatedAppPath + "EduTechWebApp-war"+File.separator+"web" + File.separator
                            + "uploads" + File.separator + "edutech" + File.separator + "assignment" + File.separator + assignmentId;
                           
                    System.out.println("FILE IS GETTING SAVED TO "+fileDir);
                    //creates directory path if not present.
                    Files.createDirectories(Paths.get(fileDir));
                    // Process a file upload
                    File uploadedFile = new File(fileDir + File.separator + fileName);
                    item.write(uploadedFile);
                    
                }
            }
            //END OF FILE UPLOAD
            //create new attachment entity
            AttachmentEntity att = new AttachmentEntity();
            att.setTitle(title);
            att.setFileName(fileName);
            ass = mmb.submitAssignment(assignmentId, att, username);
        }
        return ass;
    }
    
    @GET
    @Path("download/{assignmentId}/{attachmentId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadAssignmentSubmission(@PathParam("assignmentId") String assignmentId, @PathParam("attachmentId") String attachmentId) throws IOException, ServletException, FileUploadException, Exception {
        String appPath = context.getRealPath("");
        System.out.println("app path is "+ appPath);
        String truncatedAppPath = appPath.replace("dist"
                +File.separator+"gfdeploy"+File.separator+"EduTech"+File.separator+"EduTechWebApp-war_war", "");
        System.out.println("truncated path is "+truncatedAppPath);
        //Extract file name of this attachment entity to display on HTTP response.
        String fileName= cmb.getOneAttachment(Long.valueOf(attachmentId)).getFileName();
        //extract local file
        File resFile = new File(truncatedAppPath + "EduTechWebApp-war"+File.separator+ "web" + File.separator+ "uploads" + File.separator + "edutech" 
                    + File.separator + "assignment" + File.separator + assignmentId + File.separator + fileName);
        return Response
            .ok(FileUtils.readFileToByteArray(resFile))
            .type("application/octet-stream")
            .header("Content-Disposition", "attachment; filename=\""+fileName+"\"")
            .build();
    } 
}
