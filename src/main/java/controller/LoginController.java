package controller;
import exceptions.*;
import services.UserService;
import  view.LoginView;

import java.io.IOException;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public boolean checkAvailability(String username, String password) {
        try {
            UserService.loginTry(username, password);
            return true;
        } catch (IncorrectPasswordException e) {
            return false;
        } catch (IncorrectUsernameException e) {
            return false;

        }
    }
}
