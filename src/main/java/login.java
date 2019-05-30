import bean.Member;
import bean.Seeker;
import bean.Sitter;
import dao.MemberDao;
import dao.SeekerDao;
import dao.SitterDao;
import service.MemberService;
import service.SeekerService;
import service.SitterService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("pass");

        Member member = new Member();
        Sitter sitter = new Sitter();
        Seeker seeker = new Seeker();

        Boolean authenticate = MemberService.authenticate(email, password);

        if (authenticate) {
            int memberid = MemberService.getMemberIdByEmail(email);
            member = MemberService.getMemberById(memberid);

            if (member.getType().equals(Member.MemberType.SEEKER)) {
                seeker = SeekerService.getSeekerById(memberid);
                HttpSession session = req.getSession();
                session.setAttribute("member", seeker);
                resp.sendRedirect("seekeraccount.jsp");
                System.out.println("Redirecting to seeker.jsp");
            } else if (member.getType().equals(Member.MemberType.SITTER)){
                sitter = SitterService.getSitterById(memberid);
                HttpSession session = req.getSession();
                session.setAttribute("member", sitter);
                resp.sendRedirect("sitteraccount.jsp");
                System.out.println("Redirecting to sitter.jsp");
            }

        }
        else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h4>Invalid username or password</h4><br><a href=\"login.jsp\">Try Again</a>");
        }

    }
}
