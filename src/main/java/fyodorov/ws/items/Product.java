package fyodorov.ws.items;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    @NotBlank(message = "Product title cannot be empty")
    private String title;

    @Column(name = "price", nullable = false)
    @Min(value = 10, message = "The price cannot be lower than 10 eurodollars")
    private int price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    @JsonIgnore
    private List<Cart> carts;

    public Product() {
        this.carts = new ArrayList<>();
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
        this.carts = new ArrayList<>();
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }
}
