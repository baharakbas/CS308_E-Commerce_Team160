package edu.sabanciuniv.cs308.backend.service;

import edu.sabanciuniv.cs308.backend.dto.WishlistItemDTO;
import edu.sabanciuniv.cs308.backend.entity.WishlistItemEntity;
import edu.sabanciuniv.cs308.backend.repository.WishlistRepository;
import edu.sabanciuniv.cs308.backend.request.AddWishlistItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductService productService;

    public WishlistService(WishlistRepository wishlistRepository,
                           ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.productService = productService;
    }

    public List<WishlistItemDTO> listForUser(String userId) {
        return wishlistRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public WishlistItemDTO addItem(String userId, AddWishlistItemRequest request) {

        // Prevent duplicates
        wishlistRepository.findByUserIdAndProductIdAndSku(
                userId,
                request.getProductId(),
                request.getSku()
        ).ifPresent(item -> {
            throw new RuntimeException("Item already in wishlist");
        });

        // Fetch product
        var product = productService.getById(request.getProductId());

        // Fetch variant for price & details
        var variant = productService.getVariantBySku(product, request.getSku());

        // Create wishlist item
        WishlistItemEntity entity = new WishlistItemEntity(
                userId,
                request.getProductId(),
                request.getSku(),
                product.getName(),
                product.getMainImageUrl(),
                product.getImageUrls(),
                variant.getPrice()
        );

        return toDTO(wishlistRepository.save(entity));
    }

    public void removeItem(String userId, String wishlistItemId) {
        var item = wishlistRepository.findById(wishlistItemId)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found"));

        if (!item.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized delete");
        }

        wishlistRepository.deleteById(wishlistItemId);
    }

    private WishlistItemDTO toDTO(WishlistItemEntity e) {
        WishlistItemDTO dto = new WishlistItemDTO();
        dto.setId(e.getId());
        dto.setProductId(e.getProductId());
        dto.setSku(e.getSku());
        dto.setPrice(e.getPrice());
        dto.setName(e.getName());
        dto.setMainImageUrl(e.getMainImageUrl());
        dto.setImageUrls(e.getImageUrls());
        dto.setAddedAt(e.getAddedAt());
        return dto;
    }
}
