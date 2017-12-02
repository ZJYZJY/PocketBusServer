package bus.api;

import bus.StatuCode;
import bus.orm.entity.User;
import bus.orm.service.impl.UserDAOImpl;
import com.alibaba.dubbo.common.utils.IOUtils;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        String info = IOUtils.read(new InputStreamReader(request.getInputStream()));
        Gson gson = new Gson();
        User user = gson.fromJson(info, User.class);

        JSONObject result = new JSONObject();
        try {
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            if(userDAOImpl.userRegister(user)){
                result.put("code", StatuCode.SUCCESS);
                result.put("message", "注册成功");
            } else {
                result.put("code", StatuCode.FAIL);
                result.put("message", "注册失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello, I am RegisterServlet");
    }
}
