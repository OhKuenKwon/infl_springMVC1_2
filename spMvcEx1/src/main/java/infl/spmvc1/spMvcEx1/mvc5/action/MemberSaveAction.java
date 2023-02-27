package infl.spmvc1.spMvcEx1.mvc5.action;

import infl.spmvc1.spMvcEx1.mvc5.dao.MemberRepository;
import infl.spmvc1.spMvcEx1.mvc5.vo.MemberVO;

import java.util.Map;

public class MemberSaveAction implements ActionV4 {

    MemberRepository mr = MemberRepository.getInstance();

    //version 3에서 수정된 코드
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        //request객체에서 변수값을 가져오는 것이 아님
        //ModelView에서 저장된 parameter 값들을 가져와 Logic 실행
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        MemberVO mb = new MemberVO(username, age);
        mr.save(mb);

        //실행 결과는 model 객체에 담아두고, forward 할 주소를 return
        model.put("member", mb);
        return "save-result";
    }
}
