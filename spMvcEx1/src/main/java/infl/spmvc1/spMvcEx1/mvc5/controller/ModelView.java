package infl.spmvc1.spMvcEx1.mvc5.controller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    //객체 생성하면서 전달받은 view 이름 저장
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    //이동할 View 이름 가져오기
    public String getViewName() {
        return viewName;
    }

    //이동할 View 이름 지정
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    //Model <이름,객체> 가져오기
    public Map<String, Object> getModel() {
        return model;
    }

    //Model <이름,객체> 설정하기
    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
