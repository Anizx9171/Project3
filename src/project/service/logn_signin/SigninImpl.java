package project.service.logn_signin;
import project.service.user.UserImpl;

import java.util.List;

public class SigninImpl {
    public static void newAcc() {
        UserImpl user = new UserImpl();
        user.add();
    }
}
