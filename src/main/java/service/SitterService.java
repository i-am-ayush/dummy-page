package service;

import bean.Application;
import bean.Job;
import bean.Sitter;
import dao.ApplicationDao;
import dao.JobDao;
import dao.SitterDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SitterService {
    public static Sitter getSitterById(int id){
        Sitter sitter= SitterDao.getById(id);
        return sitter;
    }
    public static boolean registerSitter(Sitter sitter){
        return SitterDao.save(sitter);
    }
    public static List<Map<String, Object>> allAppliedJobBySitter(int memberId){
        List<Application> listApplication= ApplicationDao.getAllApplicationById(memberId);
        List<Job> listJob=new ArrayList<>();
        List<Map<String, Object>> listMap=new ArrayList<>();
        for(int i=0;i<listApplication.size();i++){
            listJob.add(JobDao.getById(listApplication.get(i).getJobId()));
            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("applicationId",listApplication.get(i).getApplicationId() );
            tempMap.put("title",JobDao.getById(listApplication.get(i).getJobId()).getTitle() );
            tempMap.put("payPerHour",JobDao.getById(listApplication.get(i).getJobId()).getPayPerHour());
            tempMap.put("status", listApplication.get(i).getStatus());
            tempMap.put("expectedPay", listApplication.get(i).getExpectedPay());
            listMap.add(tempMap);
        }
        return listMap;


    }

    public static void main(String[] args) {
        List<Map<String, Object>> l = new ArrayList<>();

        l = SitterService.allAppliedJobBySitter(5);
        for (int i = 0; i < l.size(); i++) {
            System.out.println("------------neww job-----------");
            for (Map.Entry<String, Object> entry : l.get(i).entrySet()) {
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            }
        }
    }
}
