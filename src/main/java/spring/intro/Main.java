package spring.intro;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("bob", "bob@gmail.com"));
        userService.add(new User("alisa", "alisa@gmail.com"));
        userService.add(new User("john", "john@gmail.com"));
        userService.add(new User("mick", "mick@gmail.com"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id: " + user.getId());
            System.out.println("Login: " + user.getLogin());
            System.out.println("Email: " + user.getEmail());
            System.out.println();
        }
    }
}
