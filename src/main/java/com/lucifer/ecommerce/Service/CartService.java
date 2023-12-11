package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CartDto;

public interface CartService {

    CartDto createCart();

    CartDto addProductToCart(CartDto cartDto);

    CartDto updateCart(CartDto cartDto);

    void removeProductFromCart(String cartId, String productId);

    CartDto getCartByUserId(String userId);
    CartDto getCartById(String id);

}
