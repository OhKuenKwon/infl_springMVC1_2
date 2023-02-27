package infl.spmvc1.spMvcEx1.mvc4.action;

import infl.spmvc1.spMvcEx1.mvc4.controller.ModelView;
import infl.spmvc1.spMvcEx1.mvc4.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.mvc4.vo.MemberVO;

import java.util.List;
import java.util.Map;

public class MemberListAction implements ActionV3 {

    MemberRepository mr = MemberRepository.getInstance();

    //version 3에서 수정된 코드
    @Override
    public ModelView process(Map<String, String> paramMap) {
        //저장소에서 모든 멤버 가져오기
        List<MemberVO> list = mr.findAll();

        //view 이름을 지정하며 ModelView 객체 생성
        ModelView mv = new ModelView("members");

        //ModelView의 getModel() 호출하여 Map에 <이름, 객체> 넣어 return
        //이때 이름은 List<MemberVO> 객체 list를 전달할 key 값이다.
        mv.getModel().put("members", list);
        return mv;
    }

/*  @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MemberVO> list = mr.findAll();
        req.setAttribute("members", list);

        //version 1 코드
*//*      String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);*//*

        //version 2에서 수정된 코드
        MyView myView = new MyView("/WEB-INF/views/members.jsp");
        return myView;
    }*/
}
