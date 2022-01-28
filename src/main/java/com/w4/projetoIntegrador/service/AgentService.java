package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Agent;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    SectionService sectionService;

    public Agent get(Long id) {
        //TODO: lançar exceção se nulo
        return agentRepository.findById(id).orElse(null);
    }

    public Agent save(Agent a) {
        Section s = sectionService.get(a.getSectionId());
        Agent ag = Agent.builder()
                .name(a.getName())
                .section(s)
                .build();
        return agentRepository.save(ag);
    }
}

