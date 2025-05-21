package com.projeto.util;

public class CPFValidator {
        public static boolean validarCPF(String cpf) {
            cpf = cpf.replaceAll("\\D", "");
            if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
                return false;
            }

            int[] numeros = new int[11];
            for (int i = 0; i < 11; i++) {
                numeros[i] = Character.getNumericValue(cpf.charAt(i));
            }

            int soma1 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += numeros[i] * (10 - i);
            }
            int digito1 = 11 - (soma1 % 11);
            if (digito1 >= 10) digito1 = 0;

            int soma2 = 0;
            for (int i = 0; i < 10; i++) {
                soma2 += numeros[i] * (11 - i);
            }
            int digito2 = 11 - (soma2 % 11);
            if (digito2 >= 10) digito2 = 0;

            return numeros[9] == digito1 && numeros[10] == digito2;
        }
    }

