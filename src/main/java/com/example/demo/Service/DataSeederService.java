package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataSeederService implements CommandLineRunner {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductDetailRepository productDetailRepository;

    private final UserDetailRepository userDetailRepository;

    private final ProductService productService;
    @Override
    public void run(String... args) throws Exception {
        ProductDetail yx1EarphoneDetail = ProductDetail.builder()
                .category("earphones")
                .isNew(true)
                .price(599.0)
                .description("Tailor your listening experience with bespoke dynamic drivers from the new YX1 Wireless Earphones. Enjoy incredible high-fidelity sound even in noisy environments with its active noise cancellation feature")
                .features("Experience unrivalled stereo sound thanks to innovative acoustic technology. With improved ergonomics designed for full day wearing, these revolutionary earphones have been finely crafted to provide you with the perfect fit, delivering complete comfort all day long while enjoying exceptional noise isolation and truly immersive sound. The YX1 Wireless Earphones features customizable controls for volume, music, calls, and voice assistants built into both earbuds. The new 7-hour battery life can be extended up to 28 hours with the charging case, giving you uninterrupted play time. Exquisite craftsmanship with a splash resistant design now available in an all new white and grey color scheme as well as the popular classic black.")
                .build();

// Create and set includes
        List<ProductInclude> includes = List.of(
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Earphone Unit")
                        .productDetail(yx1EarphoneDetail)
                        .build(),
                ProductInclude.builder()
                        .quantity(6L)
                        .item("Multi-size Ear plugs")
                        .productDetail(yx1EarphoneDetail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(yx1EarphoneDetail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("USB-C Charging Cable")
                        .productDetail(yx1EarphoneDetail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("Travel Pouch")
                        .productDetail(yx1EarphoneDetail)
                        .build()
        );

        yx1EarphoneDetail.setIncludes(includes);

// Create and save the user
        User user = User.builder()
                .email("arda")
                .password("arda")
                .build();
        userRepository.save(user);

// Create the product and save it before creating the cart item
        Product yx1Earphone = Product.builder()
                .slug("yx-earphones")
                .name("YX1 Wireless Earphones")
                .productDetail(yx1EarphoneDetail)
                .build();
        productService.save(yx1Earphone);

// Create and save the cart
        Cart cart = Cart.builder()
                .user(user)
                .build();
        cartRepository.save(cart);

// Create the cart item
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(yx1Earphone)
                .quantity(1L)
                .build();

// Save the cart item
        cartItemRepository.save(cartItem);
    }
}
