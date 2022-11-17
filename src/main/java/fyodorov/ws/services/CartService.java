package fyodorov.ws.services;

import fyodorov.ws.items.*;
import fyodorov.ws.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartService {

    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    public CartService(ProductRepository productRepository,
                       CartRepository cartRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public User addCart (Long userId) {
        User user = userRepository.findById(userId).get();
        if (user.getCart() == null) {
            Cart cart = new Cart(user);
            user.setCart(cart);
            cartRepository.save(cart);
            userRepository.save(user);
        }
        return user;
    }

    public void addToCart(Long productId, Long userId) {
        User user = addCart(userId);
        Product product = productRepository.findById(productId).get();
        user.getCart().getCart().add(product);
        cartRepository.save(user.getCart());
    }

    public void clearingCart(Long userId) {
        User user = addCart(userId);
        user.getCart().clear();
        cartRepository.save(user.getCart());
    }

    public void removeFromCart(Long productId, Long userId) {
        User user = addCart(userId);
        user.getCart().removeFromCart(productRepository.findById(productId).get());
        cartRepository.save(user.getCart());
    }

    public Integer sum(Long userId) {
        User user = addCart(userId);
        int sum = 0;
        for (Product product : user.getCart().getCart()) {
            sum += product.getPrice();
        }
        return sum;
    }

    public List<Product> getCurrentCart(Long userId) {
        User user = addCart(userId);
        return Collections.unmodifiableList(user.getCart().getCart());
    }

//    public List<User> findUserByProductId(Long productId) {
//        List<Cart> carts = cartRepository.findCartByProductId(productId);
//        List<User> users = new ArrayList<>();
//        for (Cart cart : carts) {
//            users.add(cart.getUser());
//        }
//        return users;
//    }
}
