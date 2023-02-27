package infl.spmvc1.spMvcEx1.mvc6.adapter;

import infl.spmvc1.spMvcEx1.mvc6.action4.ActionV4;
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

public class ActionV4HandlerAdapter implements MyHandlerAdapter{
    //해당 Action을 처리 할 수 있는지 판단하는 함수
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ActionV4);
    }

    //Action을 호출하고, 그 결과로 ModelView를 반환하는 Adapter 역할
    //이전에는 FrontController가 직접 Action을 호출했지만, 이제는 Adapter를 통해서 Action 호출
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ActionV4 action = (ActionV4) handler;

        //createParamMap() 호출하여 클라이언트로 부터의 넘어온 parameter 값들을 얻어옴
        Map<String, String> paramMap = createParamMap(req);

        //Action 실행후 그 결과가 model에 담겨져 있으므로 그것을 가져오기 위한 객체
        Map<String, Object> model = new HashMap<>();

        //클라이언트에서 온 각 파라미터들을 paramMap에 담아 전송하고,
        //Action 실행 결과를 담을 model 객체를 같이 전송(이때, model 객체는 비어있는 객체)
        //Action 실행 후 이동할 view 이름을 return 받음
        String viewName = action.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

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
