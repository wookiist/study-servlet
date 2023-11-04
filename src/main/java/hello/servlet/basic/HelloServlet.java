package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // HelloServlet이 생성되면 `service` 메서드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("HelloServlet.service");
        // 응답이 없어서 빈 화면이 출력 됨
        // 서블릿 http 요청이 오면 서블릿 컨테이너가 request 와 response 객체를 만들어서 리턴함
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 서블릿은 개발자들의 귀찮음을 해소해주는데 MVC 프레임워크들이 더 쉽게 해결해주려고 하고 있다.
        String username = request.getParameter("username"); // 쿼리 파라미터 조회
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
