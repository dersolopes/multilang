package com.anderson.multilang.controller;

import com.anderson.multilang.model.Funcionario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller responsável pela exposição de dados de funcionários.
 * Demonstra interoperabilidade com classes Kotlin.
 */
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    /**
     * Retorna um funcionário de exemplo.
     * @return objeto {@link Funcionario} criado em Kotlin
     */
    @GetMapping
    public List<Funcionario> getFuncionarios() {
        return List.of (
            new Funcionario("Anderson", 15996.0),
            new Funcionario("Bruno", 31453.0),
            new Funcionario("Carlos", 12100.0),
            new Funcionario("Daniela", 25952.0),
            new Funcionario("Ester", 19712.0)
        );
    }
}