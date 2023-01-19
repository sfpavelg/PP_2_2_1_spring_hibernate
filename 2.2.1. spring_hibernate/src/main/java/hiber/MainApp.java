package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User userTom = new User("Tom", "Brown", "tom@mail.ru");
        userService.add(userTom);
        carService.addCar(new Car("BMW", 100, userTom));

        User userIvan = new User("Ivan", "Ivanov", "ivan@mail.ru");
        userService.add(userTom);
        carService.addCar(new Car("Lada", 001, userIvan));

        User userPetr = new User("Petr", "Petrov", "petr@mail.ru");
        userService.add(userPetr);
        carService.addCar(new Car("Volvo", 202, userPetr));

        User userVasiliy = new User("Vasiliy", "Vasiliyev", "vasiliy@mail.ru");
        userService.add(userVasiliy);
        carService.addCar(new Car("Ford", 400, userVasiliy));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();

        }
        System.out.println("Пользователь машины BMW100: " + carService.getUserByCar("BMW", 100).toString());
        System.out.println();

        context.close();
    }
}
