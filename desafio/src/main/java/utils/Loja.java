package utils;

import entities.Piloto;
import java.util.Scanner;

public class Loja {
    private Scanner sc;
    public Loja(Scanner sc) {
        this.sc = sc;
    }
    public boolean querEntrar(int moedas) {
        while (true) {
            System.out.println("\n            Você tem " + moedas + " moeda(s). Quer entrar na loja? (S/N)");
            String resposta = sc.next().trim().toUpperCase();
            if (resposta.equals("S")) {
                return true;
            }
            if (resposta.equals("N")) {
                return false;
            }
            System.out.println("            Digite S ou N!");
        }
    }

    public int abrir(Piloto piloto, int moedas) {
        boolean comprando = true;
        while (comprando) {
            System.out.println("\n            ======================== LOJA =========================");
            System.out.println("            Moedas: " + moedas + " | Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima()
                    + " | Ataque: " + piloto.getAtaque() + " | Defesa: " + piloto.getDefesa()
                    + " | Poções: " + piloto.getNroPocoes());
            System.out.println("            1 - +5 de ataque (3 moedas)");
            System.out.println("            2 - +10 de defesa (6 moedas)");
            System.out.println("            3 - +3 de defesa (2 moedas)");
            System.out.println("            4 - +20 de vida máxima (6 moedas)");
            System.out.println("            5 - Recuperar toda a vida (6 moedas)");
            System.out.println("            6 - +1 poção (2 moedas)");
            System.out.println("            7 - +1 uso de " + piloto.getNomeEspecial() + " (3 moedas)");
            System.out.println("            0 - Sair da loja");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("            Digite um número!");
                continue;
            }
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    if (temMoedas(moedas, 3)) {
                        moedas -= 3;
                        piloto.setAtaque(piloto.getAtaque() + 5);
                        System.out.println("            Ataque agora: " + piloto.getAtaque());
                    }
                    break;
                case 2:
                    if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setDefesa(piloto.getDefesa() + 10);
                        System.out.println("            Defesa agora: " + piloto.getDefesa());
                    }
                    break;
                case 3:
                    if (temMoedas(moedas, 2)) {
                        moedas -= 2;
                        piloto.setDefesa(piloto.getDefesa() + 3);
                        System.out.println("            Defesa agora: " + piloto.getDefesa());
                    }
                    break;
                case 4:
                    if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setVidaMaxima(piloto.getVidaMaxima() + 20);
                        piloto.setVida(piloto.getVida() + 20); // sobe a vida atual junto
                        System.out.println("            Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
                    }
                    break;
                case 5:
                    if (piloto.getVida() == piloto.getVidaMaxima()) {
                        System.out.println("            Sua vida já está cheia!");
                    } else if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setVida(piloto.getVidaMaxima());
                        System.out.println("            Vida restaurada: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
                    }
                    break;
                case 6:
                    if (temMoedas(moedas, 2)) {
                        moedas -= 2;
                        piloto.adicionarPocao();
                        System.out.println("            Poções: " + piloto.getNroPocoes());
                    }
                    break;
                case 7:
                    if (temMoedas(moedas, 3)) {
                        moedas -= 3;
                        piloto.adicionarUsoEspecial();
                        System.out.println("            "+piloto.getNomeEspecial() + ": " + piloto.getUsosEspecial() + " restantes");
                    }
                    break;
                case 0:
                    comprando = false;
                    break;
                default:
                    System.out.println("            Opção inválida!");
            }
        }
        return moedas; // devolve o troco pro main guardar
    }

    private boolean temMoedas(int moedas, int custo) {
        if (moedas < custo) {
            System.out.println("            Moedas insuficientes!");
            return false;
        }
        return true;
    }
}