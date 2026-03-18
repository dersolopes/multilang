package com.anderson.multilang.controller;

import com.anderson.multilang.service.RuleEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller responsável pelo gerenciamento das regras dinâmicas.
 */
@RestController
@RequestMapping("/rules")
public class RuleController {

    private final RuleEngineService ruleEngine;

    public RuleController(RuleEngineService ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    /**
     * Força o reload das regras de negócio.
     *
     * @return mensagem de confirmação
     */
    @PostMapping("/reload")
    public String reload() {
        ruleEngine.clearCache();
        return "Regras recarregadas com sucesso!";
    }

    /**
     * Retorna informações sobre o estado do engine.
     *
     * @return dados de monitoramento (ex: quantidade de regras em cache)
     */
    @GetMapping("/info")
    public Map<String, Object> info() {
        return Map.of(
                "cachedRules", ruleEngine.getCacheSize()
        );
    }
}