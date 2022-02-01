package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
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
    BuyerService buyerService;

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
            itemCart.setProductAnnouncement(p);
            BigDecimal itemValue = p.getPrice().multiply(new BigDecimal(String.valueOf(itemCart.getQuantity())));
            value = value.add(itemValue);
            itemCart.setCart(cart);
        }


        cartRepository.save(cart);


        return String.valueOf(value);
    }
}
