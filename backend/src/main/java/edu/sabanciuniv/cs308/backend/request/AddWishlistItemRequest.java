package edu.sabanciuniv.cs308.backend.request;

public class AddWishlistItemRequest {

    private String productId;
    private String sku;

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
}

