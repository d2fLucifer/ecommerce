package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CartDto;
import com.lucifer.ecommerce.dto.Response.CartResponse;

public interface CartService {

    CartDto createCart();

    CartDto addProductToCart(CartDto cartDto);

    CartDto updateCart(CartDto cartDto);

    void removeProductFromCart(String cartId, String productId);

    CartResponse getCartByUserId(String userId);
    CartResponse getCartById(String id);

}
