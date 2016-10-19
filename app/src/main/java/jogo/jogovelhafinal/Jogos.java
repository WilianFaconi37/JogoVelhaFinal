package jogo.jogovelhafinal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WKS-TI-001 on 19/10/2016.
 */

public class Jogos {
    private List<Jogo> jogos = new ArrayList<>();

    public Jogos(Jogo...jogos) {
        for (Jogo j : jogos)
            this.jogos.add(j);
    }

    public Jogo gamesAddSerie(int jogador) {
        Jogo j;
        for (Jogo jogo : jogos) {
            j = jogo.addSerie(jogador);
            if (j != null) return j;
        }
        return null;
    }
}