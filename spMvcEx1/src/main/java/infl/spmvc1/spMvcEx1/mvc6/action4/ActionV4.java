package infl.spmvc1.spMvcEx1.mvc6.action4;

import java.util.Map;

public interface ActionV4 {
    //Action 처리에 필요한 파라미터를 paramMap에 담아 전달
    //동시에 Action 처리 후 결과를 담을 model 객체를 같이 전달함
    //--> 각 Action 클래스는 로직 실행 후 그 결과를 model 객체에 담아둠
    //또한, return되는 값은 forward 할 view name
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
