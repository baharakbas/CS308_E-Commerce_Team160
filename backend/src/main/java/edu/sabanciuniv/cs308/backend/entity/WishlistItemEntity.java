package edu.sabanciuniv.cs308.backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "wishlist_items")
public class WishlistItemEntity {

    @Id
    private String id;

    private String userId;
    private String productId;
    private String sku;
    private Instant addedAt;

    // Embedded product details
    private String name;
    private String mainImageUrl;
    private List<String> imageUrls;
    private BigDecimal price;

    public WishlistItemEntity() {}

    public WishlistItemEntity(
            String userId,
            String productId,
            String sku,
            String name,
            String mainImageUrl,
            List<String> imageUrls,
            BigDecimal price
    ) {
        this.userId = userId;
        this.productId = productId;
        this.sku = sku;
        this.addedAt = Instant.now();
        this.name = name;
        this.mainImageUrl = mainImageUrl;
        this.imageUrls = imageUrls;
        this.price = price;
    }

    // GETTERS & SETTERS

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getProductId() { return productId; }
    public String getSku() { return sku; }
    public Instant getAddedAt() { return addedAt; }
    public String getName() { return name; }
    public String getMainImageUrl() { return mainImageUrl; }
    public List<String> getImageUrls() { return imageUrls; }
    public BigDecimal getPrice() { return price; }

    public void setId(String id) { this.id = id; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setProductId(String productId) { this.productId = productId; }
    public void setSku(String sku) { this.sku = sku; }
    public void setAddedAt(Instant addedAt) { this.addedAt = addedAt; }
    public void setName(String name) { this.name = name; }
    public void setMainImageUrl(String mainImageUrl) { this.mainImageUrl = mainImageUrl; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
