import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import bean.Member;
import bean.Seeker;
import bean.Sitter;
import dao.SeekerDao;
import dao.SitterDao;
import service.SitterService;

public class registersitter extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sitter sitter = new Sitter();

        String memberType = req.getParameter("membertype");
        if(memberType.equals("sitter")){
            sitter.setType(Member.MemberType.SITTER);
        }
        else if(memberType.equals("seeker")){
            sitter.setType(Member.MemberType.SEEKER);
        }
        sitter.setFirstName(req.getParameter("firstname"));
        sitter.setLastName(req.getParameter("lastname"));
        sitter.setPhoneNumber(Integer.parseInt(req.getParameter("phone")));
        sitter.setEmail(req.getParameter("email"));
        sitter.setAddress(req.getParameter("address"));
        sitter.setYearsOfExp(Integer.parseInt(req.getParameter("experience")));
        sitter.setExpectedPay(Double.parseDouble(req.getParameter("expectedpay")));
        sitter.setPassword(req.getParameter("password"));

        SitterService.registerSitter(sitter);

        PrintWriter out = resp.getWriter();
        out.println("Saved in Database");

    }
}
