package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.model.Characters.Player;

public class AsteroidsModel {
    private final int score;
    private final Player player;

    public AsteroidsModel() {
        this.score = 0;
        this.player = new Player(null);
    }
}
