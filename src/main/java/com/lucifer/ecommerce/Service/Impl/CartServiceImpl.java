package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.CartService;
import com.lucifer.ecommerce.dto.CartDto;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Cart;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.User;
import com.lucifer.ecommerce.repository.CartRepository;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.repository.UserRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public CartDto addProductToCart(CartDto cartDto) {
        Cart cart = cartRepository.findByUserId(cartDto.getUserId())
                .orElseGet(() -> createNewCart(cartDto.getUserId()));

        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "ID", cartDto.getProductId()));

        cart.getProducts().add(product);
        product.getCarts().add(cart);

        cartRepository.save(cart);
        return genericMapper.map(cart, CartDto.class);
    }

    private Cart createNewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", "ID", userId));

        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void removeQuantityProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found", "ID", cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", productId));

        cart.getProducts().remove(product);
        product.getCarts().remove(cart);

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found", "ID", cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", productId));

        List<Product> productsToRemove = new ArrayList<>();

        for (Product p : cart.getProducts()) {
            if (p.getId().equals(productId)) {
                productsToRemove.add(p);
            }
        }

        System.out.println("Before removal: " + cart.getProducts().size());

        if (!productsToRemove.isEmpty()) {
            cart.getProducts().removeAll(productsToRemove);

            for (Product productToRemove : productsToRemove) {
                productToRemove.getCarts().removeIf(cart1 -> cart1.getId().equals(cartId));
            }

            productRepository.saveAll(productsToRemove);
            cartRepository.save(cart);

            System.out.println("After removal: " + cart.getProducts().size());
        } else {
            throw new ResourceNotFoundException("Product not found in cart", "Product ID", productId);
        }
    }




    @Override
    public CartDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user", "User ID", userId));
        return genericMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found", "ID", id));
        return genericMapper.map(cart, CartDto.class);
    }
}
