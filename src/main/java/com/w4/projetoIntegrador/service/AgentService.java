package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.dtos.AgentDto;
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

    public Agent getAgent(Long id){
        try {
            return agentRepository.findById(id).orElse(null);

        } catch (RuntimeException e) {
            throw new NotFoundException("Agent " + id + " não encontrado na base de dados.");
        }
    }

    public AgentDto get(Long id) {
        return AgentDto.convert(getAgent(id));
    }

    public AgentDto save(AgentDto agentDto) {
        Section section = sectionService.getSection(agentDto.getSectionId());
        Agent agent = AgentDto.convert(agentDto, section);
        agentRepository.save(agent);
        agentDto.setId(agent.getId());
        return agentDto;
    }
}
