package com.anderson.multilang.controller;

import com.anderson.multilang.model.Funcionario;
import com.anderson.multilang.service.RuleEngineService;
import com.anderson.multilang.model.BonusRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável por demonstrar a interoperabilidade entre Java, Kotlin e Groovy.
 * <p>
 * Esta classe expõe endpoints para verificação de status, integração com objetos Kotlin
 * e execução dinâmica de regras de negócio escritas em Groovy.
 * </p>
 * * @author Anderson
 * @version 1.1
 */
@RestController
public class HelloController {

    /**
     * Serviço responsável pelo motor de regras dinâmico.
     */
    private final RuleEngineService ruleEngine;

    /**
     * Construtor para injeção de dependência do motor de regras.
     * * @param ruleEngine Instância do serviço de execução de scripts Groovy.
     */
    public HelloController(RuleEngineService ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    /**
     * Endpoint de verificação de disponibilidade (Health Check).
     * * @return Uma {@link String} confirmando que a aplicação está operacional.
     */
    @GetMapping("/hello")
    public String hello() {
        return "Projeto Multilang rodando!";
    }

    /**
     * Recupera os dados de um funcionário.
     * <p>
     * Este método demonstra a interoperabilidade nativa com classes escritas em Kotlin.
     * </p>
     * * @return Um objeto {@link Funcionario} contendo dados de exemplo.
     */
    @GetMapping("/funcionario")
    public Funcionario funcionario() {
        return new Funcionario("Anderson", 7000.0);
    }

    /**
     * Calcula o bônus de um funcionário utilizando um script Groovy carregado em runtime.
     * <p>
     * O cálculo é delegado ao {@link RuleEngineService}, que gerencia o carregamento dinâmico
     * e o cache da regra de negócio externa.
     * </p>
     * * @param request Objeto contendo o salário informado no corpo da requisição JSON.
     * @return O valor do bônus calculado como um {@code double}.
     * @throws Exception Caso ocorra erro no carregamento, compilação ou execução do script Groovy.
     */
    @PostMapping("/bonus")
    public double calcularBonus(@RequestBody BonusRequest request) throws Exception {
        return ruleEngine.executeBonusRule(request.getSalario());
    }
}