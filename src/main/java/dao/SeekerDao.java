package dao;

import bean.Member;
import bean.Seeker;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeekerDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();
    public static boolean save(Seeker seeker){
        String emailid=seeker.getEmail();
        MemberDao.save(seeker);
        Member m=MemberDao.getByEmailId(emailid);
        int member_id=m.getId();
        System.out.println(member_id);
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into seeker(memberId,noOfChildren,spouse) values(?,?,?)");
            stmt.setInt(1,member_id);
            stmt.setInt(2,seeker.getNoOfChildren());
            stmt.setString(3,seeker.getSpouseName());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
    public static void update(Seeker seeker){
        MemberDao.update(seeker);
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE seeker "
                    + "SET total_children=?, spouse_name=? "
                    + "WHERE memberId=?");

            stmt.setInt(1, seeker.getNoOfChildren());
            stmt.setString(2, seeker.getSpouseName());
            stmt.setInt(3, seeker.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }

    }
    public boolean delete(int seekerId){
        return MemberDao.delete(seekerId);
    }
    public static Seeker getById(int seekerId){
        Seeker seeker=new Seeker();
       // Member member=MemberDao.getById(seekerId);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seeker,member where memberId=id and memberId =?");
            stmt.setInt(1, seekerId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                seeker.setMemberId(res.getInt("memberId"));
                seeker.setNoOfChildren(res.getInt("noOfChildren"));
                seeker.setSpouseName(res.getString("spouse"));
                seeker.setId(res.getInt("id"));
                seeker.setAddress(res.getString("address"));
                seeker.setEmail(res.getString("email"));
                seeker.setFirstName(res.getString("firstName"));
                seeker.setLastName(res.getString("lastName"));
                seeker.setPhoneNumber(res.getInt("phoneNumber"));
                seeker.setType(Member.MemberType.stringToEnum(res.getString("type")));
            }

            res.close();
            stmt.close();
            return seeker;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    public static List<Seeker> getAllSeeker(){
            List<Seeker> list=new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seeker,member where memberId=id");
           // stmt.setInt(1, seekerId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                Seeker seeker=new Seeker();
                seeker.setMemberId(res.getInt("memberId"));
                seeker.setNoOfChildren(res.getInt("noOfChildren"));
                seeker.setSpouseName(res.getString("spouse"));
                seeker.setId(res.getInt("id"));
                seeker.setAddress(res.getString("address"));
                seeker.setEmail(res.getString("email"));
                seeker.setFirstName(res.getString("firstName"));
                seeker.setLastName(res.getString("lastName"));
                seeker.setPhoneNumber(res.getInt("phoneNumber"));
                seeker.setType(Member.MemberType.stringToEnum(res.getString("type")));
                list.add(seeker);
            }

            res.close();
            stmt.close();
            return list;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
//        Seeker s=new Seeker();
//        s.setAddress("delhi");
//        s.setEmail("adarsh@gmail.com");
//        s.setFirstName("adarsh");
//        s.setLastName("kumar");
//        s.setPassword("adarsh123");
//        s.setPhoneNumber(990193253);
//        s.setNoOfChildren(2);
//        s.setSpouseName("ambika");
//        s.setType(Seeker.MemberType.SEEKER);
//        SeekerDao.save(s);
//        Seeker s=getById(1);
//        System.out.println(s.getId());
//        System.out.println("start");
//        List<Seeker> l=getAllSeeker();
//        for(int i=0;i<l.size();i++){
//            System.out.println(l.get(i).getLastName());
//        }
//      Seeker s1=getById(1);
//       System.out.println(s1.getMemberId() + s1.getId());
   }
}

