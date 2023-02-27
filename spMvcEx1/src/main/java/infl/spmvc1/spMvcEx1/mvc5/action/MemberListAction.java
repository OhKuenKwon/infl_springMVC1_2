package infl.spmvc1.spMvcEx1.mvc5.action;

import infl.spmvc1.spMvcEx1.mvc5.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.mvc5.vo.MemberVO;

import java.util.List;
import java.util.Map;

public class MemberListAction implements ActionV4 {

    MemberRepository mr = MemberRepository.getInstance();

    //version 3에서 수정된 코드
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //Logic실행 : 저장소에서 모든 멤버 가져오기
        List<MemberVO> list = mr.findAll();

        //실행 결과를 model 객체에 담음
        model.put("members", list);

        //이동할 view 이름 return
        return "members";
    }
}
