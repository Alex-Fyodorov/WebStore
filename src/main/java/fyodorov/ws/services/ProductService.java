package fyodorov.ws.services;

import fyodorov.ws.items.Product;
import fyodorov.ws.repositories.CartRepository;
import fyodorov.ws.repositories.ProductRepository;
import fyodorov.ws.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Page<Product> listOfProduct(Integer min, Integer max, Integer page, Integer size, String sortField) {
        Page<Product> productList = productRepository.priceFilter(min, max, PageRequest.of(page, size, Sort.by(sortField)));
        return productList;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void save(String title, Integer price) {
        productRepository.save(new Product(title, price));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product save(Product product) {
        productRepository.save(product);
        return productRepository.findByTitle(product.getTitle());
    }
}
