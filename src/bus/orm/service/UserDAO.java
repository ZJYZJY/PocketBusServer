package bus.orm.service;

import bus.orm.entity.User;

public interface UserDAO {

    // 用户登录方法
    User userLogin(User user);

    // 用户注册方法
    boolean userRegister(User user);
}
