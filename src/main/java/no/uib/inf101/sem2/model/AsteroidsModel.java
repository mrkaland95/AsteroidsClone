package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableAsteroidModel;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.Player;
import no.uib.inf101.sem2.model.Utils.Vector2;
import no.uib.inf101.sem2.view.ViewableAsteroidsModel;

import java.util.ArrayList;
import java.util.List;

public class AsteroidsModel implements ViewableAsteroidsModel, ControllableAsteroidModel {
    private int playerScore;
    private final int playerLives;
    private final Player player;
    private final GameState gameState;
    private List<BaseCharacter> adversaryList;
//    private double deltaTime;

    public AsteroidsModel() {
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new Player(new Vector2(200, 200));
        this.gameState = GameState.ACTIVE_GAME;
        this.adversaryList = new ArrayList<>();

        // Temp until the character factory is implemented properly.
        BaseCharacter asteroid1 = new Asteroid(new Vector2(300, 100), new Vector2(-15, -20));
        BaseCharacter asteroid2 = new Asteroid(new Vector2(600, 150), new Vector2(10, -10));
        BaseCharacter asteroid3 = new Asteroid(new Vector2(300, 400), new Vector2(5, 10));

        adversaryList.add(asteroid1);
        adversaryList.add(asteroid2);
        adversaryList.add(asteroid3);

    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public BaseCharacter getPlayerShip() {
        return player;
    }
    @Override
    public List<BaseCharacter> getAdversaryList() {
        return adversaryList;
    }

    @Override
    public void accelerateShip(double deltaTime) {
        this.player.accelerate(40f, deltaTime);
    }

    @Override
    public void rotateShip(double deltaTime, float angle) {
        this.player.rotateShapeBy(angle);
    }

    @Override
    public void fireFromShip(double deltaTime) {

    }

    @Override
    public void update(double deltaTime) {
        this.player.move(deltaTime);

//        this.playerScore += 1;
        for (BaseCharacter asteroid : adversaryList) {
            asteroid.move(deltaTime);
        }
    }

    @Override
    public int getScore() {
        return this.playerScore;
    }
}
