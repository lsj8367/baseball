package controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import db.DataBase;
import java.util.HashMap;
import java.util.Map;
import model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    final UserController userController = new UserController();

    @Test
    @DisplayName("요청 url이 맞지 않는 경우 예외 발생")
    void notMatchedRequestUrlThenThrowException() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> userController.signUp("/signUp", new HashMap<>()))
            .withMessage("해당 url로의 요청이 잘못되었습니다.");
    }

    @Test
    void signUpUser() {
        userController.signUp("/create/user",
            Map.of(
                "userId", "lsj8367",
                "password", "1234",
                "name", "홍길동",
                "email", "lsj8367@naver.com"
            )
        );

        assertThat(DataBase.findUserById("lsj8367"))
            .isEqualTo(new User("lsj8367", "1234", "홍길동",
                "lsj8367@naver.com"));
    }

}
