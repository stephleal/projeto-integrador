package com.w4.projetoIntegrador.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.w4.projetoIntegrador.entities.*;
import com.w4.projetoIntegrador.enums.ProductTypes;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
import com.w4.projetoIntegrador.repository.ItemCartRepository;
import com.w4.projetoIntegrador.service.ItemCartService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemCartTest {

    private Product product1 = Product.builder().id(1L).name("product").productType(ProductTypes.congelado).build();
    private Seller seller1 = Seller.builder().id(1l).name("seller").build();


    private ProductAnnouncement productAnnouncement = ProductAnnouncement.builder()
    .id(1L)
    .name("teste")
    .brand("teste")
    .price(new BigDecimal(10.0))
    .volume(1.0f)
    .minimumTemperature(2.0f)
    .maximumTemperature(15.0f)
    .product(product1)
    .seller(seller1)
    .build();
   

    private ItemCart itemCart = ItemCart.builder().id(1L).productAnnouncement(productAnnouncement).quantity(10).build();


    @Test
    public void deveSerPossivelCriarUmItemCart() {
        ItemCartRepository mockItemCartRepository = Mockito.mock(ItemCartRepository.class);
        Mockito.when(mockItemCartRepository.save(Mockito.any())).thenReturn(itemCart);

        ItemCartService service = new ItemCartService(mockItemCartRepository);

        ItemCart itemSaved = service.create(itemCart);

        assertEquals(itemSaved.getId(), itemCart.getId());

    }

    @Test
    public void deveSerPossivelPegarUmProdutoDoItemCart() {
        ItemCartRepository mockItemCartRepository = Mockito.mock(ItemCartRepository.class);
        Mockito.when(mockItemCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(itemCart));

        ItemCartService service = new ItemCartService(mockItemCartRepository);

        ItemCart getItemCart = service.getPurchaseProduct(1L);

        assertEquals(getItemCart.getId(), itemCart.getId());

    }

    @Test
    public void deveLançarExcessaoAoBuscarUmPurchaseProductPorIdnexistente() {
        ItemCartRepository mockItemCartRepository = Mockito.mock(ItemCartRepository.class);
        Mockito.when(mockItemCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        ItemCartService service = new ItemCartService(mockItemCartRepository);


        NotFoundException notFoundException = assertThrows(NotFoundException.class,
        () -> service.getPurchaseProduct(100L));
        
        assertTrue(notFoundException.getMessage().contains("Product 100 não encontrada na base de dados."));

    }

    @Test
    public void deveLimparCartDosItemCarts(){
        List<ItemCart> itemCartList = new ArrayList<>();
        itemCartList.add(itemCart);
        Cart cart = Cart.builder().itemCarts(itemCartList).build();
        itemCart.setCart(cart);
        ItemCartRepository mockItemCartRepository = Mockito.mock(ItemCartRepository.class);
        ItemCartService itemCartService = new ItemCartService(mockItemCartRepository);
        itemCartService.clearCartForItemCarts(cart);
        Assertions.assertNull(itemCart.getCart());
    }
}
