package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Player;
import no.uib.inf101.sem2.view.ViewableAsteroidsModel;

import java.util.List;

public class AsteroidsModel implements ViewableAsteroidsModel {
    private final int playerScore;
    private final int playerLives;
    private final Player player;
    private final GameState gameState;
    private List<Asteroid> asteroidList;
    private double deltaTime;
    int timerDelay;

    public AsteroidsModel() {
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new Player(null);
        this.gameState = GameState.ACTIVE_GAME;
//        this.asteroidList = null;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public int getTimerDelay() {
        return timerDelay;
    }
}
