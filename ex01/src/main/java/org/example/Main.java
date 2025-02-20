package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("> Digite um número inteiro: ");
        int n1 = scanner.nextInt();

        System.out.println("> Digite outro número inteiro: ");
        int n2 = scanner.nextInt();

        int result = n1 + n2;
        System.out.printf("> O resultado da operação %d + %d é %d.%n", n1, n2, result);
    }
}