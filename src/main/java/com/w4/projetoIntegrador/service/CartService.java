package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.CartDto;
import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.BatchRepository;
import com.w4.projetoIntegrador.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    BuyerService buyerService;

    @Autowired
    ItemCartService itemCartService;

    @Autowired
    ProductAnnouncementService productAnnouncementService;

    public List<ItemCart> getCart(Long id) {

        try {
            List<ItemCart> itemCarts = cartRepository.findById(id).orElse(new Cart()).getItemCarts();

            List<ProductAnnouncement> productAnnouncements = new ArrayList<ProductAnnouncement>();
            for (ItemCart p : itemCarts) {
                productAnnouncements.add(p.getProductAnnouncement());
            }
            return itemCarts;
        } catch (RuntimeException e) {
            throw new NotFoundException("Product " + id + " não encontrado na base de dados.");
        }
    }

    public String create(Cart cart) {

        Buyer buyer = buyerService.getBuyer(cart.getBuyerId());
        cart.setBuyer(buyer);

        BigDecimal value = new BigDecimal(0);

        List<ItemCart> itemCartList = cart.getItemCarts();

        for (ItemCart itemCart : itemCartList) {
            ProductAnnouncement p = productAnnouncementService.get(itemCart.getProductAnnouncementId());
            List<BatchRepository.SoldStock> gt = batchRepository.getStock(p.getId());
            itemCart.setProductAnnouncement(p);
            BigDecimal itemValue = p.getPrice().multiply(new BigDecimal(String.valueOf(itemCart.getQuantity())));
            value = value.add(itemValue);
            itemCart.setCart(cart);
        }

        cartRepository.save(cart);

        return String.valueOf(value);
    }

    public CartDto updateCart(Long id, CartDto cartDto){
        Cart cart = cartRepository.findById(id).orElse(null);
        cart.setBuyer(buyerService.getBuyer(cartDto.getBuyerId()));
        cart.setDate(cartDto.getDate());

        List<ItemCart> itemCarts = new ArrayList<>();

        for (ItemCart itemCart: cartDto.getProducts()) {
            ItemCart itemCart1 = itemCartService.getPurchaseProduct(itemCart.getId());
            itemCart1.setQuantity(itemCart.getQuantity());
            ProductAnnouncement productAnnouncement = productAnnouncementService.get(itemCart.getProductAnnouncementId());
            itemCart1.setProductAnnouncement(productAnnouncement);
            itemCart1.setCart(cart);
            itemCarts.add(itemCart1);
        }
        cart.setItemCarts(itemCarts);
        cart.setStatusCode(cartDto.getStatusCode());
        cartRepository.save(cart);

        return cartDto;
    }

//    public Cart updateCart(Long id, Cart cartDto){
//        Cart cart = cartRepository.findById(id).orElse(null);
//        cart.setBuyer(buyerService.getBuyer(cartDto.getBuyerId()));
//        cart.setDate(cartDto.getDate());
//
//        List<ItemCart> itemCarts = new ArrayList<>();
//
//        for (ItemCart itemCart: cartDto.getItemCarts()) {
//            ItemCart itemCart1 = itemCartService.getPurchaseProduct(itemCart.getId());
//            itemCart1.setQuantity(itemCart.getQuantity());
//            ProductAnnouncement productAnnouncement = productAnnouncementService.get(itemCart.getProductAnnouncementId());
//            itemCart1.setProductAnnouncement(productAnnouncement);
//            itemCart1.setCart(cart);
//            itemCarts.add(itemCart1);
//        }
//        cart.setItemCarts(itemCarts);
//        cart.setStatusCode(cartDto.getStatusCode());
//        cartRepository.save(cart);
//
//        return cartDto;
//    }

}
