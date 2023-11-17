package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    1. 파라미터 전송 기능
    http://localhost:8080/request-param?username=hello&age=29
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterNames().asIterator()
                .forEachRemaining(params -> System.out.println(params + " : " + request.getParameter(params)));

        System.out.println();

        // 단일 파라미터 조회는 getParamter~!
        // 하나의 key에 중복이 나타나면, 내부 우선순위로 결정됨
        // 이름이 같은 복수 파라미터 조회하기
        // 중복일 때 getParameter() 를 쓰게 되면, 첫 번째 값이 반환됨 (애초에 중복일 땐 쓰지 말 것)
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }

        response.getWriter().write("OK");


        // application/www-form-urlencoded 컨텐츠 타입이 반드시 들어있어야 함.
    }
}
