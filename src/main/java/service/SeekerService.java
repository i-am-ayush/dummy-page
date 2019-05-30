package service;

import bean.Application;
import bean.Job;
import bean.Seeker;
import dao.ApplicationDao;
import dao.JobDao;
import dao.SeekerDao;
import dao.SitterDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeekerService {
public static Seeker getSeekerById(int id){
    Seeker seeker=SeekerDao.getById(id);
    return seeker;
}
public static boolean registerSeeker(Seeker seeker){
    boolean result=SeekerDao.save(seeker);
    return result;
}

public static List<Job> allJobPostedBySeeker(int memberId){
    return JobDao.getJobsPostedBy(memberId);
}
public static List<Map<String,Object>> getAllAppliedJobBySitter(int jobId){
    List<Map<String,Object>> listMap=new ArrayList<>();
    List<Application> listApplication=new ArrayList<>();
    listApplication=ApplicationDao.getAllApplicationByJobId(jobId);
    for(int i=0;i<listApplication.size();i++){
        Map<String,Object> tempMap=new HashMap<>();
        tempMap.put("title",JobDao.getById(jobId).getTitle() );
        tempMap.put("sitterName", SitterDao.getById(listApplication.get(i).getMemberId()).getFirstName() );
        tempMap.put("expectedPay",listApplication.get(i).getExpectedPay());
        tempMap.put("status", listApplication.get(i).getStatus());
        listMap.add(tempMap);
    }
    return listMap;
}
public static boolean editJob(Job job){
    return JobDao.update(job);
}

    public static void main(String[] args) {
        List<Map<String, Object>> l = new ArrayList<>();

        l = SeekerService.getAllAppliedJobBySitter(2);
        for (int i = 0; i < l.size(); i++) {
            System.out.println("------------neww job-----------");
            for (Map.Entry<String, Object> entry : l.get(i).entrySet()) {
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            }
        }
    }
}
