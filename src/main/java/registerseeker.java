import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import bean.Member;
import bean.Seeker;
import dao.SeekerDao;
import service.SeekerService;

public class registerseeker extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seeker seeker = new Seeker();

        String memberType = req.getParameter("membertype");
        if(memberType.equals("sitter")){
            seeker.setType(Member.MemberType.SITTER);
        }
        else if(memberType.equals("seeker")){
            seeker.setType(Member.MemberType.SEEKER);
        }
        seeker.setFirstName(req.getParameter("firstname"));
        seeker.setLastName(req.getParameter("lastname"));
        seeker.setPhoneNumber(Integer.parseInt(req.getParameter("phone")));
        seeker.setEmail(req.getParameter("email"));
        seeker.setAddress(req.getParameter("address"));
        seeker.setSpouseName(req.getParameter("spousename"));
        seeker.setNoOfChildren(Integer.parseInt(req.getParameter("children")));
        seeker.setPassword(req.getParameter("password"));

        SeekerService.registerSeeker(seeker);

        PrintWriter out = resp.getWriter();
        out.println("Saved in Database");

    }
}
