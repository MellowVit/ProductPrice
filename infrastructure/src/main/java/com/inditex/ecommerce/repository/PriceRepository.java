package com.inditex.ecommerce.repository;

import com.inditex.ecommerce.entity.PriceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents the repository interface with available operations
 */
@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Long>  {

    @Modifying
    @Query("select new PriceEntity(id,brandId,startDate,endDate,priceList,productId,priority,price,currency) " +
            "from PriceEntity p " +
            "where p.brandId = :brandId and p.productId = :productId and p.startDate <= :applicationDate and p.endDate > :applicationDate")
    Optional<List<PriceEntity>> getPriceByBrandProductAndDate(@Param("brandId") int brandId, @Param("productId") int productId,
                                                              LocalDateTime applicationDate);

}
