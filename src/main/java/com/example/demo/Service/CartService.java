package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartItemProductRepository cartItemProductRepository;
    private final UserRepository userRepository;


    public Cart getCartByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getCart();
        }
        throw new RuntimeException("User not found");
    }

    public void addItemToCart(User authenticatedUser, String slug) {
        Optional<CartItemProduct> productOptional = cartItemProductRepository.findBySlug(slug);
        CartItemProduct product = productOptional.orElseThrow(() -> new RuntimeException("Product not found"));

        Cart userCart = authenticatedUser.getCart();
        List<CartItem> cartItems = userCart.getCartItems();

        boolean found = false;

        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemProduct().getSlug().equals(slug)) {
                cartItem.getCartItemProduct().setQuantity(cartItem.getCartItemProduct().getQuantity() + 1);
                cartItemRepository.save(cartItem);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem newCartItem = CartItem.builder()
                    .cart(userCart)
                    .cartItemProduct(product)
                    .build();
            cartItemRepository.save(newCartItem);
            cartItems.add(newCartItem);
        }

        cartRepository.save(userCart);
    }

    public void removeItemFromCart(User authenticatedUser, String slug) {
        Optional<CartItemProduct> product = cartItemProductRepository.findBySlug(slug);
        if(product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Cart userCart = authenticatedUser.getCart();
        List<CartItem> cartItems = userCart.getCartItems();
        Iterator<CartItem> iterator = cartItems.iterator();

        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartItem.getCartItemProduct().getSlug().equals(slug)) {
                if (cartItem.getCartItemProduct().getQuantity() > 1) {
                    cartItem.getCartItemProduct().setQuantity(cartItem.getCartItemProduct().getQuantity() - 1);
                    cartItemRepository.save(cartItem);
                } else {
                    iterator.remove();
                    cartItemRepository.delete(cartItem);
                }
            }
        }

        cartRepository.save(userCart);
    }

}
