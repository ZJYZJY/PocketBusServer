package bus.orm.service;

import bus.orm.entity.Collection;
import bus.orm.entity.User;

import java.util.List;

public interface UserDAO {

    // 用户登录方法
    User userLogin(User user);

    // 用户注册方法
    boolean userRegister(User user);

    boolean starBusLine(Collection collection);

    boolean unstarBusLine(Collection collection);

    List<Collection> getStaredBusLines(int userId);
}
