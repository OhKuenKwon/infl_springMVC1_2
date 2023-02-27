package infl.spmvc1.spMvcEx1.mvc6.action3;

import infl.spmvc1.spMvcEx1.mvc6.controller.ModelView;
import infl.spmvc1.spMvcEx1.mvc6.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.mvc6.vo.MemberVO;

import java.util.Map;

public class MemberSaveActionV3 implements ActionV3 {

    MemberRepository mr = MemberRepository.getInstance();

    //version 3에서 수정된 코드
    @Override
    public ModelView process(Map<String, String> paramMap) {

        //request객체에서 변수값을 가져오는 것이 아니라
        //ModelView에서 저장된 값 가져옴
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);

        //view 이름을 지정하며 ModelView 객체 생성
        ModelView mv = new ModelView("save-result");

        //ModelView의 getModel() 호출하여 Map에 <이름, 객체> 넣어 return
        //이때 이름은 MemberVO객체 mb를 전달할 key 값이다.
        mv.getModel().put("member", mb);
        return mv;
    }

/*  @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);

        //모델에 데이터 보관 및 전달
        req.setAttribute("member", mb);

        //version 1 코드
*//*      String url = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);*//*

        //version 2에서 수정된 코드
        MyView myView = new MyView("/WEB-INF/views/save-result.jsp");
        return myView;
    }*/
}
