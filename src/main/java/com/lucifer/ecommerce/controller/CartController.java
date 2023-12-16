package com.lucifer.ecommerce.controller;

import com.lucifer.ecommerce.Service.CartService;
import com.lucifer.ecommerce.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Annotation to make this class a REST controller
@RequestMapping("/api/v1/carts") // Base path for all endpoints in this controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("products/{id}")
    public ResponseEntity<CartDto> addProductToCart(@RequestBody CartDto cartDto, @PathVariable(value = "id") Long productId) {
        return new ResponseEntity<>(cartService.addProductToCart(cartDto,productId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<String> removeQuantityProductFromCart(@PathVariable(name = "cartId") Long cartId,
                                                                   @PathVariable(name = "productId") Long productId) {
        cartService.removeQuantityProductFromCart(cartId, productId);
        return new ResponseEntity<>("Product Quantity removed from cart successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/products/delete/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable(name = "cartId") Long cartId,
                                                        @PathVariable(name = "productId") Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return new ResponseEntity<>("Product removed from cart successfully", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
    }
}
