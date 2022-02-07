//package com.w4.projetoIntegrador.unitTests;
//
//import com.w4.projetoIntegrador.dtos.*;
//import com.w4.projetoIntegrador.entities.*;
//import com.w4.projetoIntegrador.enums.ProductTypes;
//import com.w4.projetoIntegrador.repository.InboundRepository;
//import com.w4.projetoIntegrador.repository.ProductRepository;
//import com.w4.projetoIntegrador.service.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//import com.w4.projetoIntegrador.dtos.BatchDto;
//import com.w4.projetoIntegrador.dtos.InboundDto;
//import com.w4.projetoIntegrador.entities.*;
//import com.w4.projetoIntegrador.enums.ProductTypes;
//import com.w4.projetoIntegrador.repository.InboundRepository;
//import com.w4.projetoIntegrador.service.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ProductTests {
//
//
//
//    private Product product = Product.builder().id(1L).name("product").productType(ProductTypes.congelado).build();
//
//    private ProductDto productDto = ProductDto.builder().name("product").build();
////    private Seller seller = Seller.builder().id(1l).name("seller").build();
////
////    private ProductAnnouncement pa = ProductAnnouncement.builder()
////            .id(1l)
////            .name("product")
////            .brand("brand")
////            .price(new BigDecimal(1))
////            .volume(10f)
////            .minimumTemperature(- 10f)
////            .maximumTemperature(0f)
////            .product(product)
////            .seller(seller)
////            .build();
////
////    private BatchDto batchDto = BatchDto.builder().id(1l).initialQuantity(10).build();
////
////    private Agent agent = Agent.builder().id(1L).build();
////
////    private Section section = Section.builder().id(1L).totalSpace(10000f).build();
////
////    private InboundDto validInboundDto1 = InboundDto.builder()
////            .agentId(1L)
////            .date(LocalDateTime.now())
////            .sectionId(1L)
////            .batchDtoList(Arrays.asList(batchDto))
////            .build();
////
////    private Inbound inbound1 = Inbound.builder()
////            .agent(agent)
////            .section(section)
////            .build();
//
//    @Test
//    public void deveCadastrarUmProduct() {
//
//        //arrange
//        ProductRepository mock = Mockito.mock(ProductRepository.class);
//
//        Mockito.when(mock.save(Mockito.any())).thenReturn(productDto);
//
//        ProductService productService = new ProductService(mock);
//
//        //act
//        ProductDto p = productService.create(productDto);
//
//        //assertion
//        assertEquals(p.getName(), productDto.getName());
//    }
//
//}
