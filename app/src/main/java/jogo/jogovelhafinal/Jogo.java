package jogo.jogovelhafinal;

import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WKS-TI-001 on 19/10/2016.
 */

public class Jogo {

    private List<ImageButton> imageButtons = new ArrayList<>();
    private int[] serie = new int[2];

    public Jogo(ImageButton...imageButtons) {
        for (ImageButton btn : imageButtons)
            this.imageButtons.add(btn);
        this.serie[0] = 0;
        this.serie[1] = 0;
    }

    public Jogo addSerie(int jogador) {
        Jogo j = (++serie[jogador] < 3) ? null : this;
        return j;
    }

    public List<ImageButton> getImageButtons() {
        return this.imageButtons;
    }
}

