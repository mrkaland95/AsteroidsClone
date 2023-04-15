package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableAsteroidModel;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.Utils.Vector2;
import no.uib.inf101.sem2.view.ViewableAsteroidsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidsModel implements ViewableAsteroidsModel, ControllableAsteroidModel {
    private int playerScore;
    private final int playerLives;
    private final PlayerShip player;
    private final GameState gameState;
    private List<Asteroid> asteroidList;
    private List<BaseCharacter> UFOList;
    private List<Bullet> bulletList;

    public AsteroidsModel() {
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new PlayerShip(new Vector2(200, 200));
        this.gameState = GameState.ACTIVE_GAME;
        this.asteroidList = new ArrayList<>();

        Random random = new Random();

        // Temp until the character factory is implemented properly.
        Asteroid asteroid1 = new Asteroid(new Vector2(300, 100), new Vector2(-15, -20), random.nextFloat(0, 30));
        Asteroid asteroid2 = new Asteroid(new Vector2(600, 150), new Vector2(10, -10), random.nextFloat(0, 30));
        Asteroid asteroid3 = new Asteroid(new Vector2(300, 400), new Vector2(5, 10), random.nextFloat(0, 30));

        asteroidList.add(asteroid1);
        asteroidList.add(asteroid2);
        asteroidList.add(asteroid3);

    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public PlayerShip getPlayerShip() {
        return player;
    }
    @Override
    public List<Asteroid> getAsteroidList() {
        return asteroidList;
    }

    @Override
    public void accelerateShip(double deltaTime) {
        this.player.accelerate(75f, deltaTime);
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
        for (Asteroid asteroid : asteroidList) {
            asteroid.move(deltaTime);
            asteroid.rotateShapeBy(asteroid.getDegreesOfRotationPerSecond() * Settings.getIntervalMillis() / 1000f);
        }
    }

    @Override
    public int getScore() {
        return this.playerScore;
    }
}
