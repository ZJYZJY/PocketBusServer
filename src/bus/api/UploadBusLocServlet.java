package bus.api;

import bus.StatuCode;
import bus.orm.entity.BusLocation;
import bus.orm.service.impl.BusLocationDAOImpl;
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

@WebServlet(name = "UploadBusLocServlet")
public class UploadBusLocServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        String info = IOUtils.read(new InputStreamReader(request.getInputStream()));
        Gson gson = new Gson();
        BusLocation location = gson.fromJson(info, BusLocation.class);

        JSONObject result = new JSONObject();
        try {
            BusLocationDAOImpl busLoc = new BusLocationDAOImpl();
            if(busLoc.uploadLocation(location)){
                result.put("code", StatuCode.SUCCESS);
                result.put("message", "上传成功");
            } else {
                result.put("code", StatuCode.FAIL);
                result.put("message", "上传失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Hello, I am UploadBusLocServlet");
    }
}
