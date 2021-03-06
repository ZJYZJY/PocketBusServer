package bus.api;

import bus.StatuCode;
import bus.orm.entity.Collection;
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
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        String info = IOUtils.read(new InputStreamReader(request.getInputStream()));
        Gson gson = new Gson();
        User user = gson.fromJson(info, User.class);

        JSONObject result = new JSONObject();
        try {
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            User u = userDAOImpl.userLogin(user);
            if (u != null) {
                StringBuilder stars = new StringBuilder();
                List<Collection> collections = userDAOImpl.getStaredBusLines(u.getId());
                for (int i = 0; i < collections.size(); i++) {
                    if (i == 0) {
                        stars.append(collections.get(i).getBusId());
                    } else {
                        stars.append("-").append(collections.get(i).getBusId());
                    }
                }
                result.put("code", StatuCode.SUCCESS);
                result.put("message", "登录成功");
                result.put("data", u.toJson().put("star", stars.toString()));
            } else {
                result.put("code", StatuCode.FAIL);
                result.put("message", "登录失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello, I am LoginServlet");
    }
}
