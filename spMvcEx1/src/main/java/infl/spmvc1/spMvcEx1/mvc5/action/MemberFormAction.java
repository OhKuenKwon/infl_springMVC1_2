package infl.spmvc1.spMvcEx1.mvc5.action;

import java.util.Map;

public class MemberFormAction implements ActionV4 {

    //version 3에서 수정된 코드
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        //view 이름을 지정하며 ModelView 객체 생성후 return
        return "new-form";
    }
}
