package infl.spmvc1.spMvcEx1.springmvc2.action;

import infl.spmvc1.spMvcEx1.springmvc2.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.springmvc2.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SpringMemberAction {

    MemberRepository mr = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v2/members/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/springmvc/v2/members/save")
    protected ModelAndView save(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);

        ModelAndView mav = new ModelAndView("save-result");
        mav.addObject("member", mb);
        return mav;
    }

    @RequestMapping("/springmvc/v2/members/list")
    public ModelAndView memberList(){
        List<MemberVO> list = mr.findAll();
        ModelAndView mav = new ModelAndView("members");
        mav.addObject("members", list);
        return mav;
    }
}
