package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Agent;
import com.w4.projetoIntegrador.entities.Section;
import com.w4.projetoIntegrador.exceptions.NotFoundException;
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
        try {
            Agent agent = agentRepository.findById(id).orElse(null);
            agent.setSectionId(agent.getSection().getId());
            return agent;

        } catch (RuntimeException e) {
            throw new NotFoundException("Agent " + id + " não encontrado na base de dados.");
        }
    }

    public Agent save(Agent agent) {
        Section s = sectionService.get(agent.getSectionId());
        agent.setSection(s);
        return agentRepository.save(agent);
    }

}

