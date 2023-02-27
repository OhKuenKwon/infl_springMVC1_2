package infl.spmvc1.spMvcEx1.mvc6.controller;

import infl.spmvc1.spMvcEx1.mvc6.action3.MemberFormActionV3;
import infl.spmvc1.spMvcEx1.mvc6.action3.MemberListActionV3;
import infl.spmvc1.spMvcEx1.mvc6.action3.MemberSaveActionV3;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberFormActionV4;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberListActionV4;
import infl.spmvc1.spMvcEx1.mvc6.action4.MemberSaveActionV4;
import infl.spmvc1.spMvcEx1.mvc6.adapter.ActionV3HandlerAdapter;
import infl.spmvc1.spMvcEx1.mvc6.adapter.ActionV4HandlerAdapter;
import infl.spmvc1.spMvcEx1.mvc6.adapter.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //모든 Action이 들어갈수 있도록 Object형으로 지정
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    //여러개의 핸들러 어댑터를 담아 둘수 있는 List 객체 생성
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    //FrontController 객체 생성, Action Mapping,
    //key:servlet url, value: 각 url에 대한 Action 객체
    public FrontControllerServletV5() {
        //각 URI에 해당하는 Action클래스를 Mapping하여 Map 객체에 저장(key-URL,value-Action)
        initHandlerMappingMap();

        //핸들러 어댑터 추가
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormActionV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveActionV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/list", new MemberListActionV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormActionV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveActionV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/list", new MemberListActionV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ActionV3HandlerAdapter());
        handlerAdapters.add(new ActionV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //handler를 찾아옴(각 URI에 맵핑된 Action 객체를 찾아옴)
        Object handler = getHandler(req);

        //만약 Mapping된 Action이 없으면, 어떤 수행도 없이 바로 return
        if(handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러 어댑터 목록에서 핸들러 어댑터 찾아옴
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //핸들러 어댑터의 handle()에 의해 Action 수행한 후, 결과를 ModelView 객체로 return
        ModelView mv = adapter.handle(req, resp, handler);

        //ModelView 에서 이동할 viewName 정보를 얻어옴
        String viewName = mv.getViewName();

        //이동할 View의 실제 이름 조립 - view의 논리이름 획득 후 실제 이름 조립
        MyView view = viewResolver(viewName); //실제 이름 조립

        //model에 담긴 Action 실행 결과를 가지고 해당 view로 forward
        view.render(mv.getModel(), req, resp);
    }

    //핸들러(Action) 찾아 오기
    private Object getHandler(HttpServletRequest req) {
        //클라이언트에서 전송되어온 URI 읽음
        String reqURI = req.getRequestURI();

        //handlerMappingMap에서 해당 URI에 대한 Action 가져와 Object형으로 return
        Object handler = handlerMappingMap.get(reqURI);
        return handler;
    }

    //핸들러 처리를 위한 핸들러 어댑터 찾아 오기
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    //view의 논리이름 viewName을 이용하여, 실제 이동할 View의 이름 조립
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
