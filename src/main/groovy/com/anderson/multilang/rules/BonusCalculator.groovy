package com.anderson.multilang.rules

class BonusCalculator {

    static double calcularBonus(double salario) {
        if (salario > 5000) {
            return salario * 0.20
        }
        return salario * 0.10
    }
}