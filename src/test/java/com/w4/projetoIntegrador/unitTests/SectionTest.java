package com.w4.projetoIntegrador.unitTests;

import com.w4.projetoIntegrador.dtos.SectionDto;
import com.w4.projetoIntegrador.dtos.WarehouseDto;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.entities.Warehouse;
import com.w4.projetoIntegrador.repository.SectionRepository;
import com.w4.projetoIntegrador.service.SectionService;
import com.w4.projetoIntegrador.service.WarehouseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectionTest {

    private Warehouse warehouse = Warehouse.builder().id(1l).name("warehouse").build();
    private WarehouseDto warehouseDto = WarehouseDto.builder().name("warehouse").build();
    private Section section = Section.builder().id(1l).warehouse(warehouse).build();
    private SectionDto sectionDto = SectionDto.builder().warehouseId(1l).build();

    @Test
    public void deveCadastrarUmWarehouse() {

        //arrange
        WarehouseService mockWarehouseService = Mockito.mock(WarehouseService.class);
        SectionRepository mockSectionRepository = Mockito.mock(SectionRepository.class);

        Mockito.when(mockWarehouseService.getWarehouse(Mockito.any())).thenReturn(warehouse);
        Mockito.when(mockSectionRepository.save(Mockito.any())).thenReturn(section);

        SectionService sectionService = new SectionService(mockSectionRepository,mockWarehouseService);

        //act
        SectionDto s = sectionService.save(sectionDto);

        //assertion
        assertEquals(s.getWarehouseId(), sectionDto.getWarehouseId());
    }

}

