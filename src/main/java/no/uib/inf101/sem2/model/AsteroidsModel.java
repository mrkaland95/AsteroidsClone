package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableAsteroidModel;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Player;
import no.uib.inf101.sem2.model.Utils.Vector2;
import no.uib.inf101.sem2.view.ViewableAsteroidsModel;

import java.util.List;

public class AsteroidsModel implements ViewableAsteroidsModel, ControllableAsteroidModel {
    private final int playerScore;
    private final int playerLives;
    private final Player player;
    private final GameState gameState;
    private List<Asteroid> adversaryList;
//    private double deltaTime;

    public AsteroidsModel() {
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new Player(new Vector2(300, 300));
        this.gameState = GameState.ACTIVE_GAME;
//        this.asteroidList = null;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void accelerateShip(double deltaTime) {
        this.player.accelerate(new Vector2(0, 2f), deltaTime);
    }

    @Override
    public void rotateShip(double deltaTime) {

    }

    @Override
    public void fireFromShip(double deltaTime) {

    }

    @Override
    public void update(double deltaTime) {
        this.player.move(deltaTime);

        for (Asteroid asteroid : adversaryList) {
            asteroid.move(deltaTime);
        }

        System.out.println(player.getVelocity());

    }
}
