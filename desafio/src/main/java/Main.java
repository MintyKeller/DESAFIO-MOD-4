import entities.*;
import utils.FabricaInimigos;

import java.util.Scanner;
import java.util.List;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha seu piloto! (1-3)");
        mostrarPilotos();
        Personagem piloto = null;
        int moedas = 0;
        Random random = new Random();
        while (piloto == null) {
            if (sc.hasNextInt()) {
                int escolhaPersonagem = sc.nextInt();
                switch (escolhaPersonagem) {
                    case 1:
                        piloto = new Rei("Rei", 100, 17, 3, 100, 5);

                        break;
                    case 2:
                        piloto = new Shinji("Shinji", 120, 14, 8, 120, 3);
                        break;
                    case 3:
                        piloto = new Asuka("Asuka", 80, 22, 5, 80, 4);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } else {
                sc.next(); // descarta o que não é número
                System.out.println("Digite um número!");
            }

        }
        System.out.println("Piloto escolhido: " + piloto.getNome());
        System.out.println("Stats: \n Vida: " + piloto.getVida() + "\n Ataque:" + piloto.getAtaque() + "\n Defesa:" + piloto.getDefesa());


        List<Inimigo> inimigos = FabricaInimigos.criarTodos();
        int numeroBatalha = 0; //para lutar com o boss final, Lilith
        for (Inimigo inimigo : inimigos) {
            numeroBatalha++;
            if (numeroBatalha == 4 || numeroBatalha == 7 || numeroBatalha == 10) {
                int vidaAntes = piloto.getVida();
                piloto.curar((int) Math.round(piloto.getVidaMaxima() * 0.5));
                int ganho = piloto.getVida() - vidaAntes;
                if (ganho > 0) {
                    System.out.println("Você recuperou vida! (+" + ganho + ")");
                }
            }


            if (numeroBatalha == 10) {
                int vidaAntes = piloto.getVida();
                piloto.curar(15);
                if (piloto.getVida() > vidaAntes) {
                    System.out.println("Última batalha, você ganhou um bônus de mais 15 de vida");
                }
            }
            if (moedas > 1 && querEntrarNaLoja(sc, moedas)) {
                moedas = loja(piloto, moedas, sc);
            }
            System.out.println("\n===== BATALHA " + numeroBatalha + " de " + inimigos.size() + " =====");
            System.out.println(inimigo.getAscii());
            System.out.println("Inimigo encontrado: " + inimigo.getNome());
            System.out.println(inimigo.getDescricao());

            lutar(piloto, inimigo, sc);

            if (!piloto.estaVivo()) {
                break; // morreu, não tem próxima batalha
            }
            System.out.println("\nVocê derrotou " + inimigo.getNome() + "!");
            if (numeroBatalha < inimigos.size()) {
                moedas++;
                System.out.println("Você ganhou 1 moeda! (Total: " + moedas + ")");
            }
        }

        if (piloto.estaVivo()) {
            System.out.println("\nVocê venceu! Parabéns, campeão da arena!");
        } else {
            System.out.println("\nVocê perdeu... Tente outra vez!");
        }

    }

    public static void mostrarPilotos() {
        System.out.println("===============================REI AYANAMI (EVA 00)==================================");
        System.out.println("""
                Calma, silenciosa e quase sempre impassível, Rei cumpre cada missão sem
                hesitar, mesmo quando o custo é pessoal. No jogo, esse sangue-frio vira
                equilíbrio: vida e ataque intermediários, e um Ataque Infalível que
                atravessa a defesa de qualquer Anjo. Não importa como o inimigo se proteja,
                o golpe dela chega.""");
        System.out.println(" ");
        System.out.println("DIGITE 1");
        System.out.println("===============================SHINJI IKARI (EVA 01)==================================");
        System.out.println("""
                Um piloto relutante, que vive repetindo pra si mesmo que não pode fugir e,
                mesmo com medo, é quem mais aguenta apanhar. No jogo, ele é o tanque: a
                maior vida e a maior defesa do trio. O Ataque Forte nasce do desespero, ele
                sacrifica a própria vida pra bater mais forte, como o Eva-01 quando
                ultrapassa os limites.""");
        System.out.println(" ");
        System.out.println("DIGITE 2");
        System.out.println("===============================ASUKA LANGLEY SORYU (EVA 02)==================================");
        System.out.println("""
                Orgulhosa e competitiva, treinada desde criança pra ser a melhor piloto de
                todas. No jogo, ela é o canhão de vidro: o maior ataque do grupo, mas a
                menor vida. O Ataque Duplo lembra o estilo ousado dela e a luta contra
                Israfel, que só cai com golpes em dobro. Quando a aposta dá certo, o dano
                dobra, e quando falha, sobra um tiro normal.""");
        System.out.println(" ");
        System.out.println("DIGITE 3");
        System.out.println("===========================================================================");
    }

    public static void lutar(Personagem piloto, Inimigo inimigo, Scanner sc) {
        while (piloto.estaVivo() && inimigo.estaVivo()) {
            turnoDoPiloto(piloto, inimigo, sc);
            if (!inimigo.estaVivo()) {
                break;
            }
            turnoDoInimigo(inimigo, piloto);
        }
    }


    public static void turnoDoPiloto(Personagem piloto, Inimigo inimigo, Scanner sc) {
        piloto.iniciarTurno(); // zera o defendendo
        System.out.println("=========SEU TURNO=========");
        exibirStatus(inimigo, piloto);
        mostrarMenuAcoes(piloto);
        boolean agiu = false;
        while (!agiu) {
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Digite um número!");
                continue;
            }
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    piloto.atacar(inimigo);
                    agiu = true;
                    break;
                case 2:
                    piloto.defender();
                    agiu = true;
                    break;
                case 3:
                    agiu = piloto.usarEspecial(inimigo); // retorna falso se nao tem mais ataques especiais,
                    break;                               // e volta pro turno pra escolher outra ação.
                case 4:
                    agiu = piloto.tomarPocao();// retorna falso se nao tem mais poções,
                    break;                                           // e volta pro turno pra escolher outra ação.
                case 5:
                    exibirEstatisticas(piloto, inimigo);
                    mostrarMenuAcoes(piloto); // mostra o menu de novo;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

    public static void turnoDoInimigo(Inimigo inimigo, Personagem piloto) {
        inimigo.iniciarTurno();
        System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");

        // vida alta usa a tabela 1, vida baixa usa a t1abela 2
        int[] tabela = inimigo.getVida() > inimigo.getVidaMaxima() / 2
                ? inimigo.getpAtacarDefender1()
                : inimigo.getpAtacarDefender2(); //depois de metade da vida o anjo tem probabilidades maiores de defender (alguns)
        Random random = new Random();
        int acao = tabela[random.nextInt(tabela.length)]; // sorteia do array de probabilidades: 0 = atacar, 1 = defender
        if (acao == 0) {
            System.out.println(inimigo.getNome() + " ataca!");
            inimigo.atacar(piloto);
        } else {
            inimigo.defender();
        }

    }

    public static void exibirStatus(Inimigo inimigo, Personagem piloto) {
        System.out.println(piloto.getNome() + " (vida: " + piloto.getVida() + ")  x  "
                + inimigo.getNome() + " (vida: " + inimigo.getVida() + ")");

    }

    public static void exibirEstatisticas(Personagem piloto, Inimigo inimigo) {
        System.out.println("\n=========== ESTATÍSTICAS ===========");
        System.out.println("--- " + piloto.getNome() + " ---");
        System.out.println("Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
        System.out.println("Ataque: " + piloto.getAtaque() + " | Defesa: " + piloto.getDefesa()
                + " | Poções: " + piloto.getNroPocoes());
        System.out.println(piloto.getNomeEspecial() + " (" + piloto.getUsosEspecial()
                + " restantes): " + piloto.getDescricaoEspecial());

        System.out.println("\n--- " + inimigo.getNome() + " ---");
        System.out.println("Vida: " + inimigo.getVida() + "/" + inimigo.getVidaMaxima());
        System.out.println("Ataque: " + inimigo.getAtaque() + " | Defesa: " + inimigo.getDefesa());
        System.out.println("Chance de defender: " + chanceDefender(inimigo.getpAtacarDefender1())
                + "% (" + chanceDefender(inimigo.getpAtacarDefender2()) + "% quando ferido)");
        System.out.println("Estilo: " + inimigo.getEstilo());
        System.out.println("====================================");
    }

    public static int chanceDefender(int[] tabela) {
        int defesas = 0;
        for (int acao : tabela) {
            if (acao == 1) {
                defesas++;
            }
        }
        return defesas * 100 / tabela.length;
    }

    public static void mostrarMenuAcoes(Personagem piloto) {
        System.out.println("Ações: ");
        System.out.println("1 - Atacar (" + piloto.getAtaque() + ")");
        System.out.println("2 - Defender (" + piloto.getDefesa() + ")");
        System.out.println("3 - " + piloto.getNomeEspecial() + " (" + piloto.getUsosEspecial() + " restantes)");
        System.out.println("4 - Usar Poção (" + piloto.getNroPocoes() + " restantes)");
        System.out.println("5 - Ver estatísticas");
    }

    public static boolean querEntrarNaLoja(Scanner sc, int moedas) {
        while (true) {
            System.out.println("\nVocê tem " + moedas + " moeda(s). Quer entrar na loja? (S/N)");
            String resposta = sc.next().trim().toUpperCase();
            if (resposta.equals("S")) {
                return true;
            }
            if (resposta.equals("N")) {
                return false;
            }
            System.out.println("Digite S ou N!");
        }
    }

    public static int loja(Personagem piloto, int moedas, Scanner sc) {
        boolean comprando = true;
        while (comprando) {
            System.out.println("\n======================== LOJA =========================");
            System.out.println("Moedas: " + moedas + " | Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima()
                    + " | Ataque: " + piloto.getAtaque() + " | Defesa: " + piloto.getDefesa()
                    + " | Poções: " + piloto.getNroPocoes());
            System.out.println("1 - +5 de ataque (3 moedas)");
            System.out.println("2 - +10 de defesa (6 moedas)");
            System.out.println("3 - +3 de defesa (2 moedas)");
            System.out.println("4 - +20 de vida máxima (6 moedas)");
            System.out.println("5 - Recuperar toda a vida (6 moedas)");
            System.out.println("6 - +1 poção (2 moedas)");
            System.out.println("7 - +1 uso de " + piloto.getNomeEspecial() + " (3 moedas)");
            System.out.println("0 - Sair da loja");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Digite um número!");
                continue;
            }
            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    if (temMoedas(moedas, 3)) {
                        moedas -= 3;
                        piloto.setAtaque(piloto.getAtaque() + 5);
                        System.out.println("Ataque agora: " + piloto.getAtaque());
                    }
                    break;
                case 2:
                    if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setDefesa(piloto.getDefesa() + 10);
                        System.out.println("Defesa agora: " + piloto.getDefesa());
                    }
                    break;
                case 3:
                    if (temMoedas(moedas, 2)) {
                        moedas -= 2;
                        piloto.setDefesa(piloto.getDefesa() + 3);
                        System.out.println("Defesa agora: " + piloto.getDefesa());
                    }
                    break;
                case 4:
                    if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setVidaMaxima(piloto.getVidaMaxima() + 20);
                        piloto.setVida(piloto.getVida() + 20); // sobe a vida atual junto
                        System.out.println("Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
                    }
                    break;
                case 5:
                    if (piloto.getVida() == piloto.getVidaMaxima()) {
                        System.out.println("Sua vida já está cheia!");
                    } else if (temMoedas(moedas, 6)) {
                        moedas -= 6;
                        piloto.setVida(piloto.getVidaMaxima());
                        System.out.println("Vida restaurada: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
                    }
                    break;
                case 6:
                    if (temMoedas(moedas, 2)) {
                        moedas -= 2;
                        piloto.adicionarPocao();
                        System.out.println("Poções: " + piloto.getNroPocoes());
                    }
                    break;
                case 7:
                    if (temMoedas(moedas, 3)) {
                        moedas -= 3;
                        piloto.adicionarUsoEspecial();
                        System.out.println(piloto.getNomeEspecial() + ": " + piloto.getUsosEspecial() + " restantes");
                    }
                    break;
                case 0:
                    comprando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        return moedas; // devolve o troco pro main guardar
    }

    public static boolean temMoedas(int moedas, int custo) {
        if (moedas < custo) {
            System.out.println("Moedas insuficientes!");
            return false;
        }
        return true;
    }

}









