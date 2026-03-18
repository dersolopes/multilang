package com.anderson.multilang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por endpoints de verificação de saúde da aplicação.
 */
@RestController
public class HealthController {

    /**
     * Endpoint de health check.
     *
     * @return mensagem indicando que a aplicação está ativa
     */
    @GetMapping("/hello")
    public String hello() {
        return "Projeto Multilang rodando!";
    }
}