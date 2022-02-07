package com.w4.projetoIntegrador.unitTests;

import com.w4.projetoIntegrador.dtos.WarehouseDto;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.repository.WarehouseRepository;
import com.w4.projetoIntegrador.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseTest {


    private Warehouse warehouse = Warehouse.builder().id(1l).name("warehouse").build();

    private WarehouseDto warehouseDto = WarehouseDto.builder().name("warehouse").build();

    @Test
    public void deveCadastrarUmWarehouse() {

        //arrange
        WarehouseRepository mock = Mockito.mock(WarehouseRepository.class);
        Mockito.when(mock.save(Mockito.any())).thenReturn(warehouse);
        WarehouseService warehouseService = new WarehouseService(mock);

        //act
        WarehouseDto w = warehouseService.save(warehouse);

        //assertion
        assertEquals(w.getName(), warehouse.getName());
    }

}

