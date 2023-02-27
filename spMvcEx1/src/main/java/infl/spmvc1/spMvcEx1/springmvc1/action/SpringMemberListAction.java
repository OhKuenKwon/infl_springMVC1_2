package infl.spmvc1.spMvcEx1.springmvc1.action;

import infl.spmvc1.spMvcEx1.springmvc1.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.springmvc1.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberListAction {

    MemberRepository mr = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/list")
    public ModelAndView process(){
        List<MemberVO> list = mr.findAll();
        ModelAndView mav = new ModelAndView("members");
        mav.addObject("members", list);
        return mav;
    }
}
