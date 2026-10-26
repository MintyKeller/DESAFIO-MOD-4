import entities.*;
import utils.FabricaInimigos;
import utils.Loja;
import utils.Registro;
import utils.Ranking;
import utils.DadosAnjos;

import java.util.Scanner;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Loja loja = new Loja(sc);
        Ranking ranking = new Ranking();
        boolean jogarDeNovo = true;


        while (jogarDeNovo) {
            int moedas = 0;
            System.out.println("            Escolha seu piloto! (1-3)");
            mostrarPilotos();
            Piloto piloto = escolherPiloto(sc);

            int pontos = iniciarJogo(piloto, moedas, loja, sc);
            fimDeJogo(piloto, pontos);

            ranking.adicionar(new Registro(lerNome(sc), piloto.getNome(), pontos));
            ranking.exibir();
            jogarDeNovo = querJogarDeNovo(sc);
        }
        System.out.println("            Até a próxima!");
        sc.close();
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

    public static Piloto escolherPiloto(Scanner sc) {
        Piloto piloto = null;
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
                System.out.println("            Digite um número!");
            }

        }
        System.out.println("            Piloto escolhido: " + piloto.getNome());
        System.out.println("            Stats: \n             Vida: " + piloto.getVida() + "\n             Ataque:" + piloto.getAtaque() + "\n             Defesa:" + piloto.getDefesa());

        return piloto;
    }

    public static int iniciarJogo(Piloto piloto, int moedas, Loja loja, Scanner sc) {
        int pontos = 0;
        List<Inimigo> inimigos = FabricaInimigos.criarTodos();
        int numeroBatalha = 0; //para lutar com o boss final, Lilith

        for (Inimigo inimigo : inimigos) {
            numeroBatalha++;
            if (numeroBatalha == 4 || numeroBatalha == 7 || numeroBatalha == 10) {
                int vidaAntes = piloto.getVida();
                piloto.curar((int) Math.round(piloto.getVidaMaxima() * 0.5));
                int ganho = piloto.getVida() - vidaAntes;
                if (ganho > 0) {
                    System.out.println("            Você recuperou vida! (+" + ganho + ")");
                }
            }

            if (numeroBatalha == 10) {
                int vidaAntes = piloto.getVida();
                piloto.curar(15);
                if (piloto.getVida() > vidaAntes) {
                    System.out.println("            Última batalha, você ganhou um bônus de mais 15 de vida");
                }
            }
            if (moedas > 1 && loja.querEntrar(moedas)) {
                moedas = loja.abrir(piloto, moedas);
            }

            System.out.println("            \n            ===== BATALHA " + numeroBatalha + " de " + inimigos.size() + " =====");
            apresentarInimigo( inimigo);

            lutar(piloto, inimigo, sc);

            // 2ª fase: só na última batalha, e só se o piloto sobreviveu à fase 1
            if (piloto.estaVivo() && numeroBatalha == inimigos.size()) {
                System.out.println(DadosAnjos.REI_LILITH_TRANSICAO);
                inimigo = FabricaInimigos.criarLilithFase2();
                apresentarInimigo(inimigo);
                piloto.curar(20);
                System.out.println("            Você ganhou um bônus de mais 20 de vida!");
                lutar(piloto, inimigo, sc);
            }


            if (!piloto.estaVivo()) {
                break; // morreu, não tem próxima batalha
            }

            System.out.println("            \n            Você derrotou " + inimigo.getNome() + "!");
            int ganhos = pontosDaBatalha(numeroBatalha);
            pontos += ganhos;
            System.out.println("            +" + ganhos + " pontos! (Total: " + pontos + ")");
            if (numeroBatalha < inimigos.size()) {
                moedas++;
                System.out.println("            Você ganhou 1 moeda! (Total: " + moedas + ")");
            }
        }
        return pontos;
    }

    public static void apresentarInimigo(Inimigo inimigo) {
        System.out.println(inimigo.getAscii());
        System.out.println("            Inimigo encontrado: " + inimigo.getNome());
        System.out.println(inimigo.getDescricao());
    }

    public static void lutar(Piloto piloto, Inimigo inimigo, Scanner sc) {
        while (piloto.estaVivo() && inimigo.estaVivo()) {
            turnoDoPiloto(piloto, inimigo, sc);
            if (!inimigo.estaVivo()) {
                break;
            }
            turnoDoInimigo(inimigo, piloto);
        }
    }


    public static void turnoDoPiloto(Piloto piloto, Inimigo inimigo, Scanner sc) {
        piloto.iniciarTurno(); // zera o defendendo
        System.out.println("            ========================SEU TURNO========================");
        exibirStatus(inimigo, piloto);
        mostrarMenuAcoes(piloto);
        boolean agiu = false;
        while (!agiu) {
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("            Digite um número!");
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
                    System.out.println("            Opção inválida!");
            }
        }

    }

    public static void turnoDoInimigo(Inimigo inimigo, Piloto piloto) {
        inimigo.iniciarTurno();
        System.out.println("\n            ========================Turno de " + inimigo.getNome() + "========================");
        inimigo.turno(piloto);

    }

    public static void exibirStatus(Inimigo inimigo, Piloto piloto) {
        System.out.println("            "+piloto.getNome() + " (vida: " + piloto.getVida() + ")  x  "
                + inimigo.getNome() + " (vida: " + inimigo.getVida() + ")");

    }

    public static void exibirEstatisticas(Piloto piloto, Inimigo inimigo) {
        System.out.println("\n            =========== ESTATÍSTICAS ===========");
        System.out.println("            --- " + piloto.getNome() + " ---");
        System.out.println("            Vida: " + piloto.getVida() + "/" + piloto.getVidaMaxima());
        System.out.println("            Ataque: " + piloto.getAtaque() + " | Defesa: " + piloto.getDefesa()
                + " | Poções: " + piloto.getNroPocoes());
        System.out.println("            "+piloto.getNomeEspecial() + " (" + piloto.getUsosEspecial()
                + " restantes): " + piloto.getDescricaoEspecial());

        System.out.println("\n            --- " + inimigo.getNome() + " ---");
        System.out.println("            Vida: " + inimigo.getVida() + "/" + inimigo.getVidaMaxima());
        System.out.println("            Ataque: " + inimigo.getAtaque() + " | Defesa: " + inimigo.getDefesa());
        System.out.println("            Chance de defender: " + chanceDefender(inimigo.getpAtacarDefender1())
                + "% (" + chanceDefender(inimigo.getpAtacarDefender2()) + "% quando ferido)");
        System.out.println("            Estilo: " + inimigo.getEstilo());
        System.out.println("            ====================================");
    }

    public static void mostrarMenuAcoes(Piloto piloto) {
        System.out.println("            Ações: ");
        System.out.println("            1 - Atacar (" + piloto.getAtaque() + ")");
        System.out.println("            2 - Defender (" + piloto.getDefesa() + ")");
        System.out.println("            3 - " + piloto.getNomeEspecial() + " (" + piloto.getUsosEspecial() + " restantes)");
        System.out.println("            4 - Usar Poção (" + piloto.getNroPocoes() + " restantes)");
        System.out.println("            5 - Ver estatísticas");
    }

    public static int chanceDefender(Acao[] tabela) {
        int defesas = 0;
        for (Acao acao : tabela) {
            if (acao == Acao.DEFENDER) {
                defesas++;
            }
        }
        return defesas * 100 / tabela.length;
    }

    public static int pontosDaBatalha(int numeroBatalha) {
        if (numeroBatalha <= 3) {
            return 5;
        }
        if (numeroBatalha <= 6) {
            return 10;
        }
        if (numeroBatalha <= 9) {
            return 15;
        }
        return 20; // Lilith
    }


    public static void fimDeJogo(Piloto piloto, int pontos) {
        if (piloto.estaVivo()) {
            System.out.println("\n            Na praia, o mar vermelho ainda bate na areia.\n            Sem Anjos, sem Evas, sem Lilith. \n            O mundo pode recomeçar. Você venceu. \n            Agora resta viver ao lado dos outros, \n            mesmo sabendo que isso machuca.\n             Parabéns");
            System.out.println(DadosAnjos.FIM_ASCII);
        } else {
            System.out.println("\n            Você perdeu... Tente outra vez!");
        }
        System.out.println("            Pontuação final: " + pontos);
    }

    public static String lerNome(Scanner sc) {
        System.out.println("            Insira seu nome: (utilize apenas 4 letras)");
        String nome = sc.next().toUpperCase();
        while (nome.length() != 4 || !nome.matches("[A-Z]+")) {
            System.out.println("            Nome inválido! Digite exatamente 4 letras:");
            nome = sc.next().toUpperCase();
        }
        return nome;
    }

    public static boolean querJogarDeNovo(Scanner sc) {
        while (true) {
            System.out.println("\n            Quer jogar de novo? (S/N)");
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
}









