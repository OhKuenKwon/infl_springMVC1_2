package infl.spmvc1.spMvcEx1.mvc5.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //vewsion 2 코드
/*    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dp = req.getRequestDispatcher(viewPath);
        dp.forward(req, resp);
    }*/

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        modelToRequestAttribute(model, req);
        RequestDispatcher dp = req.getRequestDispatcher(viewPath);
        dp.forward(req, resp);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));
    }
}
