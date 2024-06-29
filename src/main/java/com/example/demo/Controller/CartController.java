package com.example.demo.Controller;


import com.example.demo.Model.Cart;
//import com.example.demo.Model.CartItem;
import com.example.demo.Model.CartItem;
import com.example.demo.Model.User;
import com.example.demo.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {


    private final CartService cartService;


    @GetMapping("/user")
    public ResponseEntity<List<CartItem>> getAuthenticatedUserCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser.getCart().getCartItems());
    }
    @PostMapping("/{slug}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable String slug) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        cartService.addItemToCart(currentUser, slug);

        return ResponseEntity.ok(currentUser.getCart());
    }
    @DeleteMapping("/{slug}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String slug) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        cartService.removeItemFromCart(currentUser, slug);

        return ResponseEntity.ok(currentUser.getCart());
    }
}
