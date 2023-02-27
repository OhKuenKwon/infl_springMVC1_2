package infl.spmvc1.spMvcEx1.mvc5.controller;

import infl.spmvc1.spMvcEx1.mvc5.action.ActionV4;
import infl.spmvc1.spMvcEx1.mvc5.action.MemberFormAction;
import infl.spmvc1.spMvcEx1.mvc5.action.MemberListAction;
import infl.spmvc1.spMvcEx1.mvc5.action.MemberSaveAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ActionV4> handlerMap = new HashMap<>();

    //FrontController 객체 생성하면서, Action Mapping을 한 후,
    //handlerMap에서 <key, value> 형태로 보관
    //이때, key는 servlet url 이고 value는 각 usl에 대한 Action 객체
    public FrontControllerServletV4() {
        //각 URI에 해당하는 Action클래스를 Mapping하여 Map 객체에 저장(key-URL,value-Action)
        handlerMap.put("/front-controller/v4/members/new-form", new MemberFormAction());
        handlerMap.put("/front-controller/v4/members/save", new MemberSaveAction());
        handlerMap.put("/front-controller/v4/members/list", new MemberListAction());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //클라이언트에서 전송되어온 URI를 req.getRequestURI() 이용하여 읽어 온 후,
        //Map 객체에서 Mapping 해놓은 각 URI에 해당하는 Action 객체를 가져옴
        //이때, 어떤 Action이 수행되어야 할지가 결정됨
        String reqURI = req.getRequestURI();
        ActionV4 av4 = handlerMap.get(reqURI);

        //만약 Mapping된 Action이 없으면, 어떤 수행도 없이 바로 return
        if(av4 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //createParamMap() 호출하여 클라이언트로 부터의 넘어온 parameter 값들을 얻어옴
        Map<String, String> paramMap = createParamMap(req);

        //Action 실행후 그 결과가 model에 담겨져 있으므로 그것을 가져오기 위한 객체
        Map<String, Object> model = new HashMap<>();

        //클라이언트에서 온 각 파라미터들을 paramMap에 담아 전송하고,
        //Action 실행 결과를 담을 model 객체를 같이 전송(이때, model 객체는 비어있는 객체)
        //Action 실행 후 이동할 view 이름을 return 받음
        String viewName = av4.process(paramMap, model);

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
