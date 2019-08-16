package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.users.User;
import feedbacksystem.com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;

    public UserController() {}

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login (User user){

    }


}
