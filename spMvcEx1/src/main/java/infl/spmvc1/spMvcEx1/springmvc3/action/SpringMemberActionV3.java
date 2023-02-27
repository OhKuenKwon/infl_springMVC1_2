package infl.spmvc1.spMvcEx1.springmvc3.action;

import infl.spmvc1.spMvcEx1.springmvc3.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.springmvc3.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberActionV3 {

    MemberRepository mr = MemberRepository.getInstance();

    //@RequestMapping(value="/new-form", method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm(){
        return "new-form";
    }

    //@RequestMapping(value="/save", method = RequestMethod.POST)
    @PostMapping("/save")
    protected String save(@RequestParam("username") String username,
                                @RequestParam("age") int age,
                                Model model) {
        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);
        model.addAttribute("member", mb);
        return "save-result";
    }

    //@RequestMapping(value="/list",method = RequestMethod.GET )
    @GetMapping("/list")
    public String memberList(Model model){
        List<MemberVO> list = mr.findAll();
        model.addAttribute("members", list);
        return "members";
    }
}
