package infl.spmvc1.spMvcEx1.springmvc1.action;

import infl.spmvc1.spMvcEx1.springmvc1.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.springmvc1.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SpringMemberSaveAction {

    MemberRepository mr = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    protected ModelAndView process(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);

        ModelAndView mav = new ModelAndView("save-result");
        mav.addObject("member", mb);
        return mav;
    }
}
