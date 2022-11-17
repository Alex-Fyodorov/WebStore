package fyodorov.ws;

import fyodorov.ws.items.Product;
import fyodorov.ws.items.User;
import fyodorov.ws.repositories.*;
import fyodorov.ws.repositories.ProductRepository;
import fyodorov.ws.services.CartService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("fyodorov.ws")
public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);
        CartService cartService = context.getBean("cartService", CartService.class);

//ADD USERS
//        userRepository.save(new User("Admin"));
//        userRepository.save(new User("John"));
//        userRepository.save(new User("Mark"));
//        userRepository.save(new User("Jimmy"));
//ADD PRODUCTS
//        productRepository.save(new Product("Milk", 89));
//        productRepository.save(new Product("Bread", 39));
//        productRepository.save(new Product("Cheese", 139));
//        productRepository.save(new Product("Potato", 26));
//        productRepository.save(new Product("Carrot", 42));
//        productRepository.save(new Product("Water", 15));
//
//        cartService.addToCart(1L, 1L);
//        cartService.addToCart(2L, 1L);
//        cartService.addToCart(3L, 1L);
//        cartService.addToCart(6L, 1L);
//        cartService.addToCart(6L, 3L);

//Не работает
//        List<User> users = cartService.findUserByProductId(6L);
//        List<String> names = new ArrayList<>();
//        for (User user : users) {
//            names.add(user.getName());
//        }
//
//        System.out.println();
//        System.out.println(names);
//        System.out.println();
//
        List<Product> products = cartService.getCurrentCart(1L);
        List<String> titles = new ArrayList<>();
        for (Product product : products) {
            titles.add(product.getTitle());
        }

        System.out.println();
        System.out.println(titles);
        System.out.println();
        System.out.println(cartService.sum(1L));
        System.out.println();
    }
}
