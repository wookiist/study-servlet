package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("age = " + age);

        Member newMember = new Member(username, age);
        memberRepository.save(newMember);

        // 꺼내기
        Member findMember = memberRepository.findById(newMember.getId());
        System.out.println("findMember = " + findMember.getUsername());
        System.out.println("findMember = " + findMember.getAge());

        // response 보내기
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        response.getWriter().write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+newMember.getId()+"</li>\n" +
                " <li>username="+newMember.getUsername()+"</li>\n" +
                " <li>age="+newMember.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
