package infl.spmvc1.spMvcEx1.mvc6.adapter;

import infl.spmvc1.spMvcEx1.mvc6.controller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Adapter 역할
// 1.해당 Action을 처리 할 수 있는지 판단 : supports()
// 2. 처리 할수 있다면 해당 Action을 호출하고, 그 결과로 ModelView를 반환 : handle()
public interface MyHandlerAdapter {

    //해당 Action을 처리 할 수 있는지 판단하는 함수
    boolean supports(Object handler);

    //Action을 호출하고, 그 결과로 ModelView를 반환하는 Adapter 역할
    //이전에는 FrontController가 직접 Action을 호출했지만, 이제는 Adapter를 통해서 Action 호출
    ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException;
}
