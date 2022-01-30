package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.entities.Agent;
import com.w4.projetoIntegrador.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/{id}")
    public Agent getAgent(@PathVariable Long id) {

        return agentService.get(id);
    }

    @PostMapping()
    public Agent newAgent(@RequestBody Agent a) {

        return agentService.save(a);
    }
}


