package service;

import bean.Member;
import dao.MemberDao;

public class MemberService {
    public static boolean authenticate(String emailId, String password) {
        Member m=MemberDao.getByEmailIdAndPassword(emailId, password);
        if(m!=null){
            return true;
        }
        else{
            return false;
        }

    }


    public static  int getMemberIdByEmail(String emailId){
        Member member=MemberDao.getByEmailId(emailId);
        return member.getId();
    }
    public static Member getMemberById(int id){
        Member member=MemberDao.getById(id);
        return member;
    }

    public static void main(String[] args) {
        System.out.println(MemberService.authenticate("harsh@gmail.com","harsh123"));
        System.out.println(MemberService.authenticate("ram@gmail.com","ram123"));


    }
}