package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDetailService userDetailService;

    private final CategoryImageRepository categoryImageRepository;

    private final ProductImageRepository productImageRepository;
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

        CategoryImage yx1EarphoneCategory = CategoryImage.builder()
                .mobile("/Product/Earphones/mobile/earphone-mobile.png")
                .tablet("/Product/Earphones/tablet/earphone-tablet.png")
                .desktop("/Product/Earphones/desktop/earphone-desktop.png")
                .productDetail(yx1EarphoneDetail)
                .build();

        yx1EarphoneDetail.setCategoryImage(yx1EarphoneCategory);


// Create and save the user
        User user = User.builder()
                .email("arda")
                .password(bCryptPasswordEncoder.encode("arda"))
                .build();
        userRepository.save(user);


        ProductImage yx1_image = ProductImage.builder()
                .mobile("/Product/Earphones/mobile/earphone-mobile.png")
                .tablet("/Product/Earphones/tablet/earphone-tablet.png")
                .desktop("/Product/Earphones/desktop/earphone-desktop.png")
                .build();

// Create the product and save it before creating the cart item
        Product yx1Earphone = Product.builder()
                .slug("yx-earphones")
                .name("YX1 Wireless Earphones")
                .productDetail(yx1EarphoneDetail)
                .image(yx1_image)
                .build();
        productService.save(yx1Earphone);


// Create and save the cart
        Cart cart = Cart.builder()
                .user(user)
                .build();
        cartRepository.save(cart);

        CartItemProduct yx1EarphoneCartItemProduct = CartItemProduct.builder()
                .name("YX1")
                .slug("yx1-earphones")
                .quantity(1L)
                .price(599.0)
                .image("/cart/cart-3.png")
                .build();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .cartItemProduct(yx1EarphoneCartItemProduct)
                .build();

        yx1EarphoneCartItemProduct.setCartItem(cartItem);



        cartItemRepository.save(cartItem);


        UserDetail userDetail = UserDetail.builder()
                .Country("Turkey")
                .City("Istanbul")
                .Address("Istanbul")
                .PostalCode("34000")
                .PhoneNumber("1234567890")
                .build();
        userDetailRepository.save(userDetail);
        user.setUserDetail(userDetail);
        userRepository.save(user);

        ProductDetail xx59_earphones = ProductDetail.builder()
                .category("headphones")
                .isNew(false)
                .features("These headphones have been created from durable, high-quality materials tough enough to take anywhere. Its compact folding design fuses comfort and minimalist style making it perfect for travel. Flawless transmission is assured by the latest wireless technology engineered for audio synchronization with videos.\n\nMore than a simple pair of headphones, this headset features a pair of built-in microphones for clear, hands-free calling when paired with a compatible smartphone. Controlling music and calls is also intuitive thanks to easy-access touch buttons on the earcups. Regardless of how you use the XX59 headphones, you can do so all day thanks to an impressive 30-hour battery life that can be rapidly recharged via USB-C.")
                .description("Enjoy your audio almost anywhere and customize it to your specific tastes with the XX59 headphones. The stylish yet durable versatile wireless headset is a brilliant companion at home or on the move.")
                .price(899.0)
                .build();

        List<ProductInclude> xx59_includes = List.of(
                ProductInclude.builder()
                        .quantity(1L)
                        .item("Headphone Unit")
                        .productDetail(xx59_earphones)
                        .build(),
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Replacement earcups")
                        .productDetail(xx59_earphones)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(xx59_earphones)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("3.5mm 5m audio cable")
                        .productDetail(xx59_earphones)
                        .build()
        );

        xx59_earphones.setIncludes(xx59_includes);

        CategoryImage xx59_categoryImage = CategoryImage.builder()
                .mobile("/public/product-xx59-headphones/mobile/image-category-page-preview.jpg")
                .tablet("/public/product-xx59-headphones/tablet/image-category-page-preview.jpg")
                .desktop("/public/product-xx59-headphones/desktop/image-category-page-preview.jpg")
                .productDetail(xx59_earphones)
                .build();

        xx59_earphones.setCategoryImage(xx59_categoryImage);

        ProductImage xx59_earphonesImage = ProductImage.builder()
                .mobile("/Product/Headphones/mobile/xx59/xx59-mobile.png")
                .tablet("/Product/Headphones/tablet/x59/x59-tablet.png")
                .desktop("/Product/Headphones/desktop/xx59/xx59-desktop.png")
                .build();

        Product xx59_earphone = Product.builder()
                .name("XX59 Headphones")
                .slug("xx59-headphones")
                .productDetail(xx59_earphones)
                .image(xx59_earphonesImage)
                .build();
        productService.save(xx59_earphone);


        ProductDetail mark2_mark1 = ProductDetail.builder()
                .category("headphones")
                .isNew(false)
                .features("As the headphones all others are measured against, the XX99 Mark I demonstrates over five decades of audio expertise, redefining the critical listening experience. This pair of closed-back headphones are made of industrial, aerospace-grade materials to emphasize durability at a relatively light weight of 11 oz.\n\nFrom the handcrafted microfiber ear cushions to the robust metal headband with inner damping element, the components work together to deliver comfort and uncompromising sound. Its closed-back design delivers up to 27 dB of passive noise cancellation, reducing resonance by reflecting sound to a dedicated absorber. For connectivity, a specially tuned cable is includes with a balanced gold connector.")
                .description("As the gold standard for headphones, the classic XX99 Mark I offers detailed and accurate audio reproduction for audiophiles, mixing engineers, and music aficionados alike in studios and on the go.")
                .price(1750.0)
                .build();

        List<ProductInclude> mark2_mark1_includes = List.of(
                ProductInclude.builder()
                        .quantity(1L)
                        .item("Headphone Unit")
                        .productDetail(mark2_mark1)
                        .build(),
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Replacement earcups")
                        .productDetail(mark2_mark1)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(mark2_mark1)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("3.5mm 5m audio cable")
                        .productDetail(mark2_mark1)
                        .build()
        );
        mark2_mark1.setIncludes(mark2_mark1_includes);

        CategoryImage mark2_mark1_categoryImage = CategoryImage.builder()
                .mobile("./public/product-mark2-mark-one-headphones/mobile/image-category-page-preview.jpg")
                .tablet("./public/product-mark2-mark-one-headphones/tablet/image-category-page-preview.jpg")
                .desktop("./public/product-mark2-mark-one-headphones/yaml/image-category-page-preview.jpg")
                .productDetail(mark2_mark1)
                .build();

        mark2_mark1.setCategoryImage(mark2_mark1_categoryImage);

        ProductImage mark2_mark1Image = ProductImage.builder()
                .mobile("/Product/Headphones/mobile/mark1/xx99-mark1.png")
                .tablet("/Product/Headphones/tablet/mark1/x99-mark1.png")
                .desktop("/Product/Headphones/desktop/mark1/xx99-mark1.png")
                .build();

        Product xx99_mark1 = Product.builder()
                .name("XX99 Mark I Headphones")
                .slug("xx99-mark1-headphones")
                .productDetail(mark2_mark1)
                .image(mark2_mark1Image)
                .build();
        productService.save(xx99_mark1);


        ProductDetail mark2_mark2 = ProductDetail.builder()
                .category("headphones")
                .isNew(true)
                .price(2999.0)
                .features("Featuring a genuine leather head strap and premium earcups, these headphones deliver superior comfort for those who like to enjoy endless listening. It includes intuitive controls designed for any situation. Whether you’re taking a business call or just in your own personal space, the auto on/off and pause features ensure that you’ll never miss a beat.\n\nThe advanced Active Noise Cancellation with built-in equalizer allow you to experience your audio world on your terms. It lets you enjoy your audio in peace, but quickly interact with your surroundings when you need to. Combined with Bluetooth 5. 0 compliant connectivity and 17 hour battery life, the XX99 Mark II headphones gives you superior sound, cutting-edge technology, and a modern design aesthetic.")
                .description("The new XX99 Mark II headphones is the pinnacle of pristine audio. It redefines your premium headphone experience by reproducing the balanced depth and precision of studio-quality sound.")
                .build();

        List<ProductInclude> mark2_mark2_includes = List.of(
                ProductInclude.builder()
                        .quantity(1L)
                        .item("Headphone Unit")
                        .productDetail(mark2_mark2)
                        .build(),
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Replacement earcups")
                        .productDetail(mark2_mark2)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(mark2_mark2)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("3.5mm 5m audio cable")
                        .productDetail(mark2_mark2)
                        .build()
        );
        mark2_mark2.setIncludes(mark2_mark2_includes);

        CategoryImage mark2_mark2_categoryImage = CategoryImage.builder()
                .mobile("./public/product-mark2-mark-two-headphones/mobile/image-category-page-preview.jpg")
                .tablet("./public/product-mark2-mark-two-headphones/tablet/image-category-page-preview.jpg")
                .desktop("./public/product-mark2-mark-two-headphones/yaml/image-category-page-preview.jpg")
                .build();
        mark2_mark2.setCategoryImage(mark2_mark2_categoryImage);

        ProductImage mark2_mark2_productImage = ProductImage.builder()
                .mobile("/Product/Headphones/mobile/mark2/xx99-mark2.png")
                .tablet("/Product/Headphones/tablet/mark2/xx99-mark2.png")
                .desktop("/Product/Headphones/desktop/mark2/xx99-mark2.png")
                .build();

        Product mark2_mark2Product = Product.builder()
                .name("XX99 Mark II Headphones")
                .slug("xx99-mark2-headphones")
                .productDetail(mark2_mark2)
                .image(mark2_mark2_productImage)
                .build();

        productService.save(mark2_mark2Product);


        ProductDetail zx7_speaker_details = ProductDetail.builder()
                .category("speakers")
                .isNew(false)
                .price(3500.0)
                .features("Reap the advantages of a flat diaphragm tweeter cone. This provides a fast response rate and excellent high frequencies that lower tiered bookshelf speakers cannot provide. The woofers are made from aluminum that produces a unique and clear sound. XLR inputs allow you to connect to a mixer for more advanced usage.\n\nThe ZX7 speaker is the perfect blend of stylish design and high performance. It houses an encased MDF wooden enclosure which minimises acoustic resonance. Dual connectivity allows pairing through bluetooth or traditional optical and RCA input. Switch input sources and control volume at your finger tips with the included wireless remote. This versatile speaker is equipped to deliver an authentic listening experience.")
                .description("Stream high quality sound wirelessly with minimal to no loss. The ZX7 speaker uses high-end audiophile components that represents the top of the line powered speakers for home or studio use.")
                .build();

        List<ProductInclude> zx7_includes = List.of(
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Speaker Unit")
                        .productDetail(zx7_speaker_details)
                        .build(),
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Speaker Cloth Panel")
                        .productDetail(zx7_speaker_details)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(zx7_speaker_details)
                                .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("3.5mm 7.5m audio cable")
                        .productDetail(zx7_speaker_details)
                                .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("7.5m Optical Cable")
                        .build()
        );

        zx7_speaker_details.setIncludes(zx7_includes);

        CategoryImage zx7_categoryImage = CategoryImage.builder()
                .mobile("./public/product-zx7-speaker/mobile/image-category-page-preview.jpg")
                .tablet("./public/product-zx7-speaker/tablet/image-category-page-preview.jpg")
                .desktop("./public/product-zx7-speaker/yaml/image-category-page-preview.jpg")
                .productDetail(zx7_speaker_details)
                .build();

        zx7_speaker_details.setCategoryImage(zx7_categoryImage);


        ProductImage zx7_speaker_image = ProductImage.builder()
                .mobile("/Product/Speakers/mobile/zx7/zx7.png")
                .tablet("/Product/Speakers/tablet/zx7/zx7.png")
                .desktop("/Product/Speakers/desktop/zx7/zx7.png")
                .build();

        Product zx7_speaker = Product.builder()
                .name("ZX7 Speaker")
                .slug("zx7-speaker")
                .productDetail(zx7_speaker_details)
                .image(zx7_speaker_image)
                .build();

        productService.save(zx7_speaker);


        ProductDetail zx9_detail = ProductDetail.builder()
                .category("speakers")
                .isNew(true)
                .description("Upgrade your sound system with the all new ZX9 active speaker. It’s a bookshelf speaker system that offers truly wireless connectivity -- creating new possibilities for more pleasing and practical audio setups.")
                .features("Connect via Bluetooth or nearly any wired source. This speaker features optical, digital coaxial, USB Type-B, stereo RCA, and stereo XLR inputs, allowing you to have up to five wired source devices connected for easy switching. Improved bluetooth technology offers near lossless audio quality at up to 328ft (100m).\n\nDiscover clear, more natural sounding highs than the competition with ZX9’s signature planar diaphragm tweeter. Equally important is its powerful room-shaking bass courtesy of a 6.5” aluminum alloy bass unit. You’ll be able to enjoy equal sound quality whether in a large room or small den. Furthermore, you will experience new sensations from old songs since it can respond to even the subtle waveforms.")
                .price(4500.0)
                .build();

        List<ProductInclude> zx9_includes = List.of(
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Speaker Unit")
                        .productDetail(zx9_detail)
                        .build(),
                ProductInclude.builder()
                        .quantity(2L)
                        .item("Speaker Cloth Panel")
                        .productDetail(zx9_detail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("User Manual")
                        .productDetail(zx9_detail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("3.5mm 7.5m audio cable")
                        .productDetail(zx9_detail)
                        .build(),
                ProductInclude.builder()
                        .quantity(1L)
                        .item("7.5m Optical Cable")
                        .productDetail(zx9_detail)
                        .build()
        );
        zx9_detail.setIncludes(zx9_includes);

        CategoryImage zx9_categoryImage = CategoryImage.builder()
                .mobile("./public/product-zx9-speaker/mobile/image-category-page-preview.jpg")
                .tablet("./public/product-zx9-speaker/tablet/image-category-page-preview.jpg")
                .desktop("./public/product-zx9-speaker/yaml/image-category-page-preview.jpg")
                .productDetail(zx9_detail)
                .build();
        zx9_detail.setCategoryImage(zx9_categoryImage);

        ProductImage zx9_image = ProductImage.builder()
                .mobile("/Product/Speakers/mobile/zx9/zx9.png")
                .tablet("/Product/Speakers/tablet/zx9/zx9.png")
                .desktop("/Product/Speakers/desktop/zx9/zx9.png")
                .build();

        Product zx9_speaker = Product.builder()
                .name("ZX9 Speaker")
                .slug("zx9-speaker")
                .productDetail(zx9_detail)
                .image(zx9_image)
                .build();

        productService.save(zx9_speaker);
    }
}
