/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import edutechentities.common.ScheduleItemEntity;
import edutechentities.module.AnswerEntity;
import edutechentities.module.FeedbackEntity;
import edutechentities.module.LessonEntity;
import edutechentities.module.ModuleEntity;
import edutechentities.module.PollEntity;
import edutechentities.module.PollOptionEntity;
import edutechentities.module.QuestionEntity;
import edutechentities.module.SessionEntity;
import edutechentities.module.SubmissionEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Derian
 */
@Stateless
@LocalBean
public class ModuleRESTMgrBean {

    @PersistenceContext
    private EntityManager em;

    public void createModule(ModuleEntity entity) {
        em.persist(entity);
    }

    public void editModule(String id, ModuleEntity entity) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        mod = entity;
        em.merge(mod);
    }

    public void deleteModule(String id) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        em.remove(mod);
    }

    public ModuleEntity getOneModule(String id) {
        return em.find(ModuleEntity.class, id);
    }

    public List<ModuleEntity> getAllModules() {
        Query q1 = em.createQuery("SELECT m FROM Module m");
        return q1.getResultList();
    }

    public List<AnswerEntity> getAllAnswers() {
        Query q = em.createQuery("select ans from MMAgenda mma");
        return q.getResultList();
    }

    public AnswerEntity getOneAnswer(Long id) {
        return em.find(AnswerEntity.class, id);
    }

    public AnswerEntity createAnswer(AnswerEntity mm) {
        em.persist(mm);
        return mm;
    }

    public void deleteAnswer(Long id) {
        AnswerEntity old= em.find(AnswerEntity.class, id);
        if(old!=null){
            em.remove(old);
        }
    }

    public AnswerEntity editAnswer(Long id, AnswerEntity replacement) {
        AnswerEntity old = em.find(AnswerEntity.class, id);
        old = replacement;
        em.merge(old);
        return old;
    }
    
    public List<QuestionEntity> getAllQuestions() {
        Query q = em.createQuery("select ent from Question ent");
        return q.getResultList();
    }

    public QuestionEntity getOneQuestion(Long id) {
        return em.find(QuestionEntity.class, id);
    }

    public QuestionEntity createQuestion(QuestionEntity mm) {
        em.persist(mm);
        return mm;
    }

    public void deleteQuestion(Long id) {
        QuestionEntity old= em.find(QuestionEntity.class, id);
        if(old!=null){
            em.remove(old);
        }
    }

    public QuestionEntity editQuestion(Long id, QuestionEntity replacement) {
        QuestionEntity old = em.find(QuestionEntity.class, id);
        old = replacement;
        em.merge(old);
        return old;
    }

    public List<FeedbackEntity> getAllFeedbacks() {
        Query q = em.createQuery("select ent from Feedback ent");
        return q.getResultList();
    }

    public FeedbackEntity getOneFeedback(Long id) {
        return em.find(FeedbackEntity.class, id);
    }

    public FeedbackEntity createFeedback(FeedbackEntity mm) {
        em.persist(mm);
        return mm;
    }

    public void deleteFeedback(Long id) {
        FeedbackEntity old= em.find(FeedbackEntity.class, id);
        if(old!=null){
            em.remove(old);
        }
    }

    public FeedbackEntity editFeedback(Long id, FeedbackEntity replacement) {
        FeedbackEntity old = em.find(FeedbackEntity.class, id);
        old = replacement;
        em.merge(old);
        return old;
    }

    public List<PollOptionEntity> getAllPollOptions() {
        Query q = em.createQuery("select ent from PollOption ent");
        return q.getResultList();
    }

    public PollOptionEntity getOnePollOption(Long id) {
        return em.find(PollOptionEntity.class, id);
    }

    public PollOptionEntity createPollOptions(PollOptionEntity mm) {
        em.persist(mm);
        return mm;
    }

    public void deletePollOption(Long id) {
        PollOptionEntity old= em.find(PollOptionEntity.class, id);
        if(old!=null){
            em.remove(old);
        }
    }

    public PollOptionEntity editPollOption(Long id, PollOptionEntity replacement) {
        PollOptionEntity old = em.find(PollOptionEntity.class, id);
        old = replacement;
        em.merge(old);
        return old;
    }

    public List<PollEntity> getAllPolls() {
        Query q = em.createQuery("select ent from Poll ent");
        return q.getResultList();
    }
    
    public PollEntity getOnePoll(Long id) {
        return em.find(PollEntity.class, id);
    }

    public PollEntity createPoll(PollEntity mm) {
        em.persist(mm);
        return mm;
    }

    public void deletePoll(Long id) {
        PollEntity old= em.find(PollEntity.class, id);
        if(old!=null){
            em.remove(old);
        }
    }

    public PollEntity editPoll(Long id, PollEntity replacement) {
        PollEntity old = em.find(PollEntity.class, id);
        old = replacement;
        em.merge(old);
        return old;
    }

    public List<SessionEntity> getAllSessions() {
        Query q = em.createQuery("select s from ClassSession s");
        return q.getResultList();
    }
    
    public SessionEntity getOneSession(Long id) {
        return em.find(SessionEntity.class, id);
    }
        
    public SessionEntity createSession(SessionEntity ses) {
        em.persist(ses);
        return ses;
    }

    public void deleteSession(Long id) {
        SessionEntity ses= em.find(SessionEntity.class, id);
        if(ses!=null){
            em.remove(ses);
        }
    }

    public SessionEntity editSession(Long id, SessionEntity replacement) {
        SessionEntity ses = em.find(SessionEntity.class, id);
        ses = replacement;
        em.merge(ses);
        return ses;
    }

    public List<SubmissionEntity> getAllSubmissions() {
        Query q = em.createQuery("select s from Submission s");
        return q.getResultList();
    }

    public SubmissionEntity getOneSubmission(Long id) {
        return em.find(SubmissionEntity.class, id);
    }

    public SubmissionEntity createSubmission(SubmissionEntity sub) {
        em.persist(sub);
        return sub;
    }

    public void deleteSubmission(Long id) {
        SessionEntity ses= em.find(SessionEntity.class, id);
        if(ses!=null){
            em.remove(ses);
        }
    }

    public SubmissionEntity editSubmission(Long id, SubmissionEntity replacement) {
        SubmissionEntity sub = em.find(SubmissionEntity.class, id);
        sub = replacement;
        em.merge(sub);
        return sub;
    }

    public List<ScheduleItemEntity> getAllModuleLessons(String id) {
        ArrayList<ScheduleItemEntity> modLessons = new ArrayList<>();
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        Collection<ScheduleItemEntity> lessons = mod.getModuleEvents();
        if(lessons!=null){
            System.out.println("TRY PRINTING......................");
            modLessons.addAll(lessons);
            for(ScheduleItemEntity l : lessons){
                System.out.println("TRY PRINTING ONE......................");
                System.out.println(l);
            }
        }
        return modLessons;
    }

}
