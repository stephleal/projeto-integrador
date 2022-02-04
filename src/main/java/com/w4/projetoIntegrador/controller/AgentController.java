package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.dtos.AgentDto;
import com.w4.projetoIntegrador.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgent(@PathVariable Long id) {
        return ResponseEntity.ok().body(agentService.get(id));
    }

    @PostMapping("/")
    public ResponseEntity<AgentDto> newAgent(@Valid @RequestBody AgentDto agentDto) {
       return ResponseEntity.status(201).body(agentService.save(agentDto));
    }
}


