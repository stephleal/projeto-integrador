package com.w4.projetoIntegrador.unitTests;

import com.w4.projetoIntegrador.dtos.BuyerDto;
import com.w4.projetoIntegrador.entities.Buyer;
import com.w4.projetoIntegrador.repository.BuyerRepository;
import com.w4.projetoIntegrador.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerTest {

        private Buyer buyer = Buyer.builder().id(1l).name("buyer").build();

        private BuyerDto buyerDto = BuyerDto.builder().name("buyer").build();


        @Test
        public void deveCadastrarUmBuyer() {

            //arrange
            BuyerRepository mock = Mockito.mock(BuyerRepository.class);

            Mockito.when(mock.save(Mockito.any())).thenReturn(buyer);

            BuyerService buyerService = new BuyerService(mock);

            //act
            BuyerDto b = buyerService.create(buyerDto);

            //assertion
            assertEquals(b.getName(), buyerDto.getName());
        }

    @Test
    public void deveBuscarUmBuyer() {

        //arrange
        BuyerRepository mock = Mockito.mock(BuyerRepository.class);

        Mockito.when(mock.findById(Mockito.anyLong())).thenReturn(Optional.of(buyer));

        BuyerService buyerService = new BuyerService(mock);

        //act
        BuyerDto b = buyerService.get(1L);

        //assertion
        assertEquals(b.getName(), buyerDto.getName());
    }
    }

