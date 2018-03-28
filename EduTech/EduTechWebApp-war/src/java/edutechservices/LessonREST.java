/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.module.LessonEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author nanda88
 */
@RequestScoped
@Path("lesson")
public class LessonREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    
    @Context
    private ServletContext context;
    @Context 
    private HttpServletRequest request;
    @Context 
    private HttpServletResponse response;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> getAllLessons() {
        return cmb.getAllLessons();
    }
    
    @GET 
    @Path("allAttachments/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> getAllLessonAttachments(@PathParam("id") String id) {
        return cmb.getAllLessonAttachments(Long.valueOf(id));
    }
    
    @GET 
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ScheduleItemEntity getOneLesson(@PathParam("id") String id){
        return cmb.getOneLesson(Long.valueOf(id));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ScheduleItemEntity createLesson(LessonEntity lesson) {
        return cmb.createLesson(lesson);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> deleteLesson(@PathParam("id") String id) {
        cmb.deleteLesson(id);
        return cmb.getAllLessons();
    }
    
    @PUT 
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ScheduleItemEntity editLesson(@PathParam("id") String id, ScheduleItemEntity replacement) {
        return cmb.editLesson(id, replacement);
    }
    
    @POST
    @Path("uploadAttachment/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> uploadLessonAttachment(@PathParam("id") String id) throws IOException, ServletException, FileUploadException, Exception {
        String title ="";
        String fileName="";
        
        String appPath = context.getRealPath("");
        System.out.println("app path is "+ appPath);
        String truncatedAppPath = appPath.replace("build"
                +File.separator+"web", "");
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
                    }
                }else if(!item.isFormField()){
                    fileName = item.getName();
                    System.out.println("file name is "+fileName);
                    
                    
                    //where to save file.
                    String fileDir = truncatedAppPath + "web" + File.separator
                            + "uploads" + File.separator + "edutech" + File.separator + "lesson" + File.separator + id;
                           
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
            cmb.uploadLessonAttachment(id, att);
        }
        return cmb.getAllLessonAttachments(Long.valueOf(id));
    }
    
    @DELETE
    @Path("deleteAttachment/{lessonId}/{attachmentId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> deleteOneLessonAttachment(@PathParam("lessonId") String lessonId, @PathParam("attachmentId") String attachmentId ) throws IOException, ServletException, FileUploadException, Exception {
        String fileName="";
        
        String appPath = context.getRealPath("");
        System.out.println("app path is "+ appPath);
        String truncatedAppPath = appPath.replace("build"+File.separator+"web", "");
        System.out.println("truncated path is "+truncatedAppPath);
        fileName = cmb.deleteAttachment(attachmentId);
        //delete local file
        try{
            Files.deleteIfExists(Paths.get(truncatedAppPath + "web" + File.separator+ "uploads" + File.separator + "edutech" 
                    + File.separator + "lesson" + File.separator + lessonId + File.separator + fileName));
        }catch(NoSuchFileException e){
            System.out.println("No such file/directory exists");
        }catch(DirectoryNotEmptyException e){
            System.out.println("Directory is not empty.");
        }catch(IOException e){
            System.out.println("Invalid permissions.");
        }
        System.out.println("Deletion of "+fileName+" is successful.");
        return cmb.getAllLessonAttachments(Long.valueOf(lessonId));
    }
    
    @GET
    @Path("downloadAttachment/{lessonId}/{attachmentId}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadOneLessonAttachment(@PathParam("lessonId") String lessonId, @PathParam("attachmentId") String attachmentId) throws IOException, ServletException, FileUploadException, Exception {
        String appPath = context.getRealPath("");
        System.out.println("app path is "+ appPath);
        String truncatedAppPath = appPath.replace("build"+File.separator+"web", "");
        System.out.println("truncated path is "+truncatedAppPath);
        //Extract file name of this attachment entity to display on HTTP response.
        String fileName= cmb.getOneAttachment(Long.valueOf(attachmentId)).getFileName();
        //extract local file
        File resFile = new File(truncatedAppPath + "web" + File.separator+ "uploads" + File.separator + "edutech" 
                    + File.separator + "lesson" + File.separator + lessonId + File.separator + fileName);
        return Response
            .ok(FileUtils.readFileToByteArray(resFile))
            .type("application/octet-stream")
            .header("Content-Disposition", "attachment; filename=\""+fileName+"\"")
            .build();
    }
    
    @GET
    @Path("downloadAllAttachments/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces("application/zip")
    public Response downloadAllLessonAttachments(@PathParam("id") String id) throws IOException {
        //Declare the directory which the zip will be created at.
        String zipPath = context.getRealPath("").replace("build"+File.separator+"web", "")
                .concat("web" + File.separator
                            + "uploads" + File.separator + "edutech" + File.separator + "lesson");
        System.out.println("ZIP DIRECTORY IS AT "+zipPath);
        //creates directory path if not present.
        Files.createDirectories(Paths.get(zipPath+File.separator+id));
        String zipName = "Lesson_"+id+"_Attachments.zip";
        //create new file at that location.
        File zipFile = new File(zipPath+File.separator+zipName);
        ZipUtil.pack(new File(zipPath+File.separator+id), zipFile);
        
        return Response
            .ok(FileUtils.readFileToByteArray(zipFile))
            .type("application/zip")
            .header("Content-Disposition", "attachment; filename=\""+zipName+"\"")
            .build();
    }

}
