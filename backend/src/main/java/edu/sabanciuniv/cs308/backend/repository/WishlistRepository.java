package edu.sabanciuniv.cs308.backend.repository;

import edu.sabanciuniv.cs308.backend.entity.WishlistItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends MongoRepository<WishlistItemEntity, String> {

    List<WishlistItemEntity> findByUserId(String userId);

    Optional<WishlistItemEntity> findByUserIdAndProductIdAndSku(
            String userId,
            String productId,
            String sku
    );
}
