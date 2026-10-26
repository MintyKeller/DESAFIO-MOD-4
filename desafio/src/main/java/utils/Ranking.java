package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ranking {
    private static final int MAX_REGISTROS = 10;
    private List<Registro> registros = new ArrayList<>();

    public void adicionar(Registro registro) {
        registros.add(registro);
        // maior pontuação em cima (em empate, quem entrou primeiro fica na frente)
        registros.sort(Comparator.comparingInt(Registro::getPontuacao).reversed());
        if (registros.size() > MAX_REGISTROS) {
            registros.remove(registros.size() - 1); // sai o último colocado
        }
    }

    public void exibir() {
        System.out.println("\n            =========== RANKING ===========");
        for (int i = 0; i < registros.size(); i++) {
            Registro r = registros.get(i);
            System.out.println("            "+(i + 1) + ") " + r.getNome() + " - " + r.getPontuacao()
                    + " pts - Piloto: " + r.getPiloto().toUpperCase());
        }
        System.out.println("            ===============================");
    }
}