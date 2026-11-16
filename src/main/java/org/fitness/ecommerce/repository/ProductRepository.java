package org.fitness.ecommerce.repository;

import org.fitness.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "CALL searchProducts(:keyword)",nativeQuery = true)
    List<Product> searchKeyWord(@Param("keyword") String keyword);
}

/*
                ============= SEARCH PRODUCTS USING KEYWORD ======================
DROP PROCEDURE IF EXISTS searchProducts;
DELIMITER //
CREATE PROCEDURE searchProducts (IN keyword VARCHAR(255))
BEGIN
   SELECT *
   FROM product
   WHERE
   LOWER(name) LIKE CONCAT('%',CONCAT(LOWER(keyword),'%'))
   OR
   LOWER(category_type) LIKE CONCAT('%',CONCAT(LOWER(keyword),'%'))
   OR
   LOWER(brand) LIKE CONCAT('%',CONCAT(LOWER(keyword),'%'))
   OR
   LOWER(description) LIKE CONCAT('%',CONCAT(LOWER(keyword),'%'));
END
// DELIMITER ;

USE college;
CALL searchProducts('FOOD');

 */