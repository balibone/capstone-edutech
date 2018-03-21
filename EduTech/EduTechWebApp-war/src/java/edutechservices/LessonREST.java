/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.module.LessonEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    ServletContext context;
    String appPath = context.getRealPath("");
    String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LessonEntity> getAllLessons() {
        return cmb.getAllLessons();
    }
    
    @GET 
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity getOneLesson(@PathParam("id") String id){
        return cmb.getOneLesson(Long.valueOf(id));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity createLesson(LessonEntity lesson) {
        return cmb.createLesson(lesson);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LessonEntity> deleteLesson(@PathParam("id") String id) {
        cmb.deleteLesson(id);
        return cmb.getAllLessons();
    }
    
    @PUT 
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity editLesson(@PathParam("id") String id, LessonEntity replacement) {
        return cmb.editLesson(id, replacement);
    }
    
    @POST
    @Path("attachment/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> uploadLessonAttachment(@PathParam("id") String id, @Context HttpServletRequest request) throws IOException, ServletException {
        String title = request.getParameter("title");
        //0: document, 1: image, 2: video
        String fileType = request.getParameter("type");
        
        //START OF FILE UPLOAD

        
        //where to save file. 
        String fileDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                + "uploads" + File.separator + "edutech" + File.separator + "lesson" + File.separator + id
                + File.separator + fileType;
        
        //creates directory path if not present.
        Files.createDirectories(Paths.get(fileDir));
        
        //extract part from multipart/form-data
        Part filePart = request.getPart("file");
        
        //extract file name
        final String fileName = filePart.getSubmittedFileName();
        
        //output stream to write file to local disk.
        FileOutputStream out = null;
        //input stream to receive file from Part.
        InputStream fileContent = null;
        try {
            //creates output stream that is gonna produce a file at location fileDir with name fileName.
            out = new FileOutputStream(new File(fileDir + File.separator + fileName));
            //gets the incoming bytes from request as an input stream.
            fileContent = filePart.getInputStream();
            
            int bytesRead = 0;
            final byte[] bytes = new byte[1024];
            //read bytes from input stream until finish.
            while ((bytesRead = fileContent.read(bytes)) != -1) {
                
                out.write(bytes, 0, bytesRead);
            }
        } catch (FileNotFoundException fne) {
            System.out.println("***********FILE NOT FOUND EXCEPTION");
                fne.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }        
        // END OF FILE UPLOAD
        //create new attachment entity
        AttachmentEntity att = new AttachmentEntity();
        att.setTitle(title);
        att.setFileName(fileName);
        att.setType(Integer.valueOf(fileType));
        return cmb.uploadLessonAttachment(id,att);
    }
    
    @GET
    @Path("attachment/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> downloadAllLessonAttachments(@PathParam("id") String id) {
        return cmb.downloadAllLessonAttachments(id);
    }
}
