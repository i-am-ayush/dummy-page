package dao;

import bean.Member;
import bean.Sitter;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SitterDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();
    public static boolean save(Sitter sitter){
        String emailId=sitter.getEmail();
        MemberDao.save(sitter);
        Member m=MemberDao.getByEmailId(emailId);
        int id=m.getId();
        try {

            PreparedStatement stmt = conn.prepareStatement("insert into sitter(memberId,yearsOfExp,expectedPay) values(?,?,?)");
            stmt.setInt(1,id);
            stmt.setInt(2,sitter.getYearsOfExp());
            stmt.setDouble(3,sitter.getExpectedPay());
            boolean result=stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
    public static void update(Sitter sitter){
        MemberDao.update(sitter);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE sitter "
                    + "SET yearsOfExp=?, expectedPay=? "
                    + "WHERE memberId=?");
            stmt.setInt(1, sitter.getYearsOfExp());
            stmt.setDouble(2, sitter.getExpectedPay());
            stmt.setInt(3, sitter.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static boolean delete(int sitterId){

        return MemberDao.delete(sitterId);
    }
    public static Sitter getById(int sitterId){
        Sitter sitter=new Sitter();
        Member member=MemberDao.getById(sitterId);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sitter,member where memberId=id and memberId =?");
            stmt.setInt(1, sitterId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                sitter.setMemberId(res.getInt(1));
                sitter.setYearsOfExp(res.getInt(2));
                sitter.setExpectedPay(res.getDouble(3));
                sitter.setId(res.getInt("id"));
                sitter.setAddress(res.getString("address"));
                sitter.setEmail(res.getString("email"));
                sitter.setFirstName(res.getString("firstName"));
                sitter.setLastName(res.getString("lastName"));
                sitter.setPhoneNumber(res.getInt("phoneNumber"));
                sitter.setType(Member.MemberType.stringToEnum(res.getString("type")));
            }
            res.close();
            stmt.close();
            return sitter;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    public static List<Sitter> getAllSitter(){
        List<Sitter> list=new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sitter,member where memberId=id");
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                Sitter sitter=new Sitter();
                sitter.setMemberId(res.getInt("memberId"));
                sitter.setYearsOfExp(res.getInt("yearsOfExp"));
                sitter.setExpectedPay(res.getDouble("expectedPay"));
                sitter.setId(res.getInt("id"));
                sitter.setAddress(res.getString("address"));
                sitter.setEmail(res.getString("email"));
                sitter.setFirstName(res.getString("firstName"));
                sitter.setLastName(res.getString("lastName"));
                sitter.setPhoneNumber(res.getInt("phoneNumber"));
                sitter.setType(Member.MemberType.stringToEnum(res.getString("type")));
                list.add(sitter);
            }
            res.close();
            stmt.close();
            return list;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

  //  public static void main(String[] args) {
//        Sitter s=new Sitter();
//        s.setAddress("kota");
//        s.setEmail("rahul@gmail.com");
//        s.setFirstName("rahul");
//        s.setLastName("jha");
//        s.setPassword("rahul123");
//        s.setPhoneNumber(990143288);
//        s.setExpectedPay(750.0);
//        s.setYearsOfExp(2);
//        s.setType(Member.MemberType.SITTER);
//        SitterDao.save(s);
////            Sitter s=getById(6);
////        System.out.println(s.getId());
////        System.out.println(s.getLastName());
//        Sitter s=getById(5);
//        System.out.println(s.getLastName());
//        List<Sitter> l=getAllSitter();
//        for(int i=0;i<l.size();i++){
//            System.out.println(l.get(i).getLastName());
//        }
//  }
}

