package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// name이나 urlPattern은 중복이 있으면 안 됨
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // HelloServlet http 요청 인입시,  `service` 메서드가 호출된다.
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

        // springboot엔 tomcat 서버가 내장되어 있음.
        // 이 tomcat 서버가 서블릿 컨테이너 기능을 갖고 있고
        // 이 컨테이너를 활ㅇ용해 서블릿을 생성해준다.

        // 서버에 request 요청이 인입되면
        // 누군진 모르겠지만, http 요청 메시지를 기반으로 web application server 에서 request 와 response 객체를 만들어서
        // 서블릿 컨테이너에 '싱글톤'으로 떠있는 Servlet을 호출함 (서비스 메서드)
        // 그것을 호출하면서 WAS에서 생성한 request, response 객체를 파라미터로 딱 넘겨줌!

        // 그러고 나서, 거기서 필요한 작업 (service 메서드) 을 수행하고 response 객체에 contents type 이나 정보들을 담아서
        // 보내면, 얘가 종료되고 나가면서, WAS 서버가 response 객체 정보를 갖고, 실제 응답할 HTTP Response Message를 만들어서 반환함!!!!
    }
}
