package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CartDto;

public interface CartService {


    CartDto addProductToCart(CartDto cartDto, Long productId);


    void removeProductFromCart(Long cartId, Long productId);

    CartDto getCartByUserId(Long userId);

    CartDto getCartById(Long id);


    void removeQuantityProductFromCart(Long cartId, Long productId);
}
