package com.anderson.multilang.controller;

import com.anderson.multilang.model.Funcionario;
import com.anderson.multilang.rules.BonusCalculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Projeto Multilang rodando!";
    }

    @GetMapping("/funcionario")
    public Funcionario funcionario() {
        return new Funcionario("Anderson", 7000.0);
    }

    @GetMapping("/bonus")
    public double bonus() {
        return BonusCalculator.calcularBonus(7000);
    }
}