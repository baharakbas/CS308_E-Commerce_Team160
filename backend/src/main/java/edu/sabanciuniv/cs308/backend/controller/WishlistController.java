package edu.sabanciuniv.cs308.backend.controller;

import edu.sabanciuniv.cs308.backend.dto.WishlistItemDTO;
import edu.sabanciuniv.cs308.backend.request.AddWishlistItemRequest;
import edu.sabanciuniv.cs308.backend.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<List<WishlistItemDTO>> list(Authentication auth) {
        String userId = auth.getName();
        return ResponseEntity.ok(wishlistService.listForUser(userId));
    }

    @PostMapping
    public ResponseEntity<WishlistItemDTO> add(Authentication auth,
                                               @RequestBody AddWishlistItemRequest request) {
        String userId = auth.getName();
        return ResponseEntity.ok(wishlistService.addItem(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth,
                                       @PathVariable String id) {
        String userId = auth.getName();
        wishlistService.removeItem(userId, id);
        return ResponseEntity.ok().build();
    }
}
