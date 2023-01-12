package controller;

import db.DataBase;
import java.util.Map;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public void signUp(final String requestPath, Map<String, String> params) {
        if (!"/create/user".equals(requestPath)) {
            throw new IllegalArgumentException("해당 url로의 요청이 잘못되었습니다.");
        }

        DataBase.addUser(new User(
            params.get("userId"),
            params.get("password"),
            params.get("name"),
            params.get("email"))
        );

        final User savedUser = DataBase.findUserById(params.get("userId"));
        log.info("저장 완료 {}", savedUser);
    }
}
