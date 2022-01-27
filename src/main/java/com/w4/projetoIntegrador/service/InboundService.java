package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.InboundDto;
import com.w4.projetoIntegrador.entities.Batch;
import com.w4.projetoIntegrador.entities.Inbound;
import com.w4.projetoIntegrador.repository.InboundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundService {

    private InboundRepository inboundRepository;

    public InboundService(InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    public List<Batch> create(InboundDto idto) {
        Inbound in = InboundDto.convert(idto);
        inboundRepository.save(in);
        return in.getBatchList();
    }
}
