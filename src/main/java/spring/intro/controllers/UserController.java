package spring.intro.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.config.AppConfig;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private UserService userService = context.getBean(UserService.class);
    private List<UserResponseDto> usersDto = new ArrayList<>();

    @GetMapping("/inject")
    public void injectIntoDB() {
        userService.add(new User("bob", "bob@gmail.com"));
        userService.add(new User("alisa", "alisa@gmail.com"));
        userService.add(new User("john", "john@gmail.com"));
        userService.add(new User("mick", "mick@gmail.com"));
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return usersDto.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .get();
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        List<User> users;
        users = userService.listUsers();
        for (User user : users) {
            UserResponseDto userDto = new UserResponseDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setEmail(user.getEmail());
            usersDto.add(userDto);
        }
        return usersDto;
    }
}
