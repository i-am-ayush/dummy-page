package dao;

import bean.Member;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);

    static Connection conn = DatabaseConnector.getConnection();

    public static boolean save(Member member) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into member(firstName,lastName,phoneNumber,email,type,address,password) values(?,?,?,?,?,?,?)");
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setInt(3, member.getPhoneNumber());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, String.valueOf(member.getType()));
            stmt.setString(6, member.getAddress());
            stmt.setString(7, member.getPassword());
            stmt.execute();
            stmt.close();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static void update(Member member) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE member "
                    + "SET firstName=?, lastName=?, phoneNumber=?, email=?, address=?"
                    + "WHERE id=?");
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setInt(3, member.getPhoneNumber());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, member.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static boolean delete(int memberId) {

        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE member "
                    + "SET status='INACTIVE' "
                    + "WHERE id=?");
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return false;
        }

    }


    public static Member getById(int memberId) {
        try {
            // List<Member> listOfMember = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MEMBER where id=?");
            stmt.setInt(1,memberId);
            return getAll(stmt).get(0);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public static Member getByEmailIdAndPassword(String emailId, String password) {
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from member where email=? and password=?");
            stmt.setString(1, emailId);
            stmt.setString(2, password);
            return getAll(stmt).get(0);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static Member getByEmailId(String emailId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from member where email=?");
                stmt.setString(1,emailId);
            return getAll(stmt).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getAllEmail(String emailId) {
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select email from member where email like ?");
            stmt.setString(1, "%" + emailId + "%");
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                list.add(res.getString("email"));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }
    public static List<Member> getAllMember(){
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from member;");
            return getAll(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Member> getAll(PreparedStatement stmt) {
        List<Member> list = new ArrayList<>();
        try {
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                Member member = new Member();
                member.setId(res.getInt("id"));
                member.setFirstName(res.getString("firstName"));
                member.setLastName(res.getString("lastName"));
                member.setPhoneNumber(res.getInt("phoneNumber"));
                member.setEmail(res.getString("email"));
                member.setType(Member.MemberType.stringToEnum(res.getString("type")));
                member.setAddress(res.getString("address"));
                member.setStatus(Member.Status.valueOf(res.getString("status")));
                list.add(member);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

//    public static void main(String[] args) {
//        List<Member> g=getAllMember();
//        for(int i=0;i<g.size();i++){
//                System.out.println(g.get(i).getType());
//            }
//       Member m= getById(1);
//        System.out.println(m.getEmail());
//        Member m=getByEmailIdAndPassword("harsh@gmail.com","harsh123");
//        Member m1=getByEmailId("harsh@gmail.com");
//        System.out.println(m.getLastName());
//        System.out.println(m1.getLastName());
//    }
}


