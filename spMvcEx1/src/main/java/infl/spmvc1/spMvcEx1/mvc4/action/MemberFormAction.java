package infl.spmvc1.spMvcEx1.mvc4.action;

import infl.spmvc1.spMvcEx1.mvc4.controller.ModelView;
import java.util.Map;

public class MemberFormAction implements ActionV3 {

    //version 3에서 수정된 코드
    @Override
    public ModelView process(Map<String, String> paramMap) {
        //view 이름을 지정하며 ModelView 객체 생성후 return
        return new ModelView("new-form");
    }

/*  @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //version 1 코드
        *//*      String url = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);*//*

        //version 2에서 수정된 코드
        MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        return myView;
    }*/
}
