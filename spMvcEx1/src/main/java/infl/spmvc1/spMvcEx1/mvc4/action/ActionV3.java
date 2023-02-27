package infl.spmvc1.spMvcEx1.mvc4.action;

import infl.spmvc1.spMvcEx1.mvc4.controller.ModelView;

import java.util.Map;

public interface ActionV3 {
    ModelView process(Map<String, String> paramMap);
}
