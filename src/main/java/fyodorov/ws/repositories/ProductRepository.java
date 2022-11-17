package fyodorov.ws.repositories;

import fyodorov.ws.items.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByPriceBetween(int min, int max);

    List<Product> findProductByPriceGreaterThan(int min);

    @Query(value = "select * from products p where (:min is null or p.price >= :min) and (:max is null or p.price <= :max)",
            countQuery = "select count(*) from products p where (:min is null or p.price >= :min) and (:max is null or p.price <= :max)",
            nativeQuery = true)
    Page<Product> priceFilter(Integer min, Integer max, Pageable pageable);

    Product findByTitle(String title);
}
