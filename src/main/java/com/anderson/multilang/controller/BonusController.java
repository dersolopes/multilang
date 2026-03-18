package com.anderson.multilang.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderson.multilang.model.BonusRequest;
import com.anderson.multilang.service.RuleEngineService;

/**
 * Controller responsável pelo cálculo de bônus utilizando regras dinâmicas em Groovy.
 */
@RestController
@RequestMapping
public class BonusController {

    private final RuleEngineService ruleEngine;

    public BonusController(RuleEngineService ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    /**
     * Calcula o bônus com base no salário informado.
     *
     * @param request dados do funcionário
     * @return valor do bônus calculado
     * @throws Exception erro ao executar script Groovy
     */
    @PostMapping
    public double calcularBonus(@RequestBody BonusRequest request) throws Exception {
        return ruleEngine.executeBonusRule(request.getSalario());
    }

    /**
     * Calcula o bônus retornando informações detalhadas.
     *
     * @param request dados do funcionário
     * @return mapa contendo salário, bônus e percentual aplicado
     * @throws Exception erro ao executar script Groovy
     */
    @PostMapping("/detalhado")
    public Map<String, Object> calcularBonusDetalhado(@RequestBody BonusRequest request) throws Exception {

        double bonus = ruleEngine.executeBonusRule(request.getSalario());
        double percentual = bonus / request.getSalario();

        return Map.of(
                "salario", request.getSalario(),
                "bonus", bonus,
                "percentual", percentual
        );
    }

}
