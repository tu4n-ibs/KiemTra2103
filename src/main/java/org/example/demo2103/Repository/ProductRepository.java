package org.example.demo2103.Repository;

import org.example.demo2103.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByPriceBetween(int price1, int price2);
    List<Product> findAllByOrderByAmountAsc();
    @Modifying
    @Query(value = "select * from product order by price desc limit 3",nativeQuery = true)
    List<Product> findAllByPrice();

    List<Product> findAllByCategoryName(String name);

}
