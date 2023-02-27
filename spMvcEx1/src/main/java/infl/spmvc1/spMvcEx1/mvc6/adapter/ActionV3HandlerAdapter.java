package infl.spmvc1.spMvcEx1.mvc6.adapter;

import infl.spmvc1.spMvcEx1.mvc6.action3.ActionV3;
import infl.spmvc1.spMvcEx1.mvc6.controller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//ActionV3를 지원하는 HandlerAdapter
// 1.해당 Action을 처리 할 수 있는지 판단 : supports()
// 2. 처리 할수 있다면 해당 Action을 호출하고, 그 결과로 ModelView를 반환 : handle()

public class ActionV3HandlerAdapter implements MyHandlerAdapter{
    //해당 Action을 처리 할 수 있는지 판단하는 함수
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ActionV3);
    }

    //Action을 호출하고, 그 결과로 ModelView를 반환하는 Adapter 역할
    //이전에는 FrontController가 직접 Action을 호출했지만, 이제는 Adapter를 통해서 Action 호출
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ActionV3 action = (ActionV3) handler;

        //createParamMap() 호출 : 클라이언트로 부터의 parameter 값들 얻어옴
        Map<String, String> paramMap = createParamMap(req);

        //각 파라미터들을 전송하면서 Action을 수행하고, 그 결과를 ModelView 객체로 받아옴
        ModelView mv = action.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        //클라이언트에서 넘어온 모든 파라미터값을 가져와 paramMap에 저장 후 return
        req.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
