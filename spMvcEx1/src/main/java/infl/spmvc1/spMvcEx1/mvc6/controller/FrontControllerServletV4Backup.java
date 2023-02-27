package infl.spmvc1.spMvcEx1.mvc6.controller;

import infl.spmvc1.spMvcEx1.mvc6.action4.ActionV4;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberFormActionV4;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberListActionV4;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberSaveActionV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontControllerServletV4Backup extends HttpServlet {

    private Map<String, ActionV4> handlerMap = new HashMap<>();

    //FrontController 객체 생성, Action Mapping,
    //key:servlet url, value: 각 url에 대한 Action 객체
    public FrontControllerServletV4Backup() {
        //각 URI에 해당하는 Action클래스를 Mapping하여 Map 객체에 저장(key-URL,value-Action)
        handlerMap.put("/front-controller/v5/members/new-form", new MemberFormActionV4());
        handlerMap.put("/front-controller/v5/members/save", new MemberSaveActionV4());
        handlerMap.put("/front-controller/v5/members/list", new MemberListActionV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //클라이언트에서 전송되어온 URI 읽음
        //handlerMap의 Mapping 정보에서 해당 URI에 대한 Action 가져옴
        String reqURI = req.getRequestURI();
        ActionV4 av5 = handlerMap.get(reqURI);

        //만약 Mapping된 Action이 없으면, 어떤 수행도 없이 바로 return
        if(av5 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //createParamMap() 호출 : 클라이언트로 부터의 parameter 값들 얻어옴
        Map<String, String> paramMap = createParamMap(req);

        //Action 실행후 결과를 담아두기 위한 빈 객체(model) 생성
        Map<String, Object> model = new HashMap<>();

        //process메소드로 Action 실행,
        //클라이언트 파라미터정보(paramMap)와 Action 실행 결과를 담을 객체(model)를 같이 전송
        //Action 실행 후 이동할 view 이름을 return 받음
        String viewName = av5.process(paramMap, model);

        //이동할 View의 실제 이름 조립 - view의 논리이름 획득 후 실제 이름 조립
        MyView view = viewResolver(viewName); //실제 이름 조립

        //model에 담긴 Action 실행 결과를 가지고 해당 view로 forward
        view.render(model, req, resp);
    }

    //view의 논리이름 viewName을 이용하여, 실제 이동할 View의 이름 조립
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        //클라이언트에서 넘어온 모든 파라미터값을 가져와 paramMap에 저장 후 return
        req.getParameterNames().asIterator().forEachRemaining(
                paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
