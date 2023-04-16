package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableAsteroidModel;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.Factories.CharacterFactory;
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
    private final CharacterFactory characterFactory;
    private List<BaseCharacter> UFOList = new ArrayList<>();
    private List<Asteroid> asteroidList = new ArrayList<>();
    private List<Bullet> bulletList = new ArrayList<>();
    private final int mapWidth;
    private final int mapHeight;


    public AsteroidsModel(CharacterFactory characterFactory) {
        this.characterFactory = characterFactory;
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new PlayerShip(new Vector2(200, 200));
        this.gameState = GameState.ACTIVE_GAME;
        this.mapWidth = 1000;
        this.mapHeight = 1000;



        // Temp until the character factory is implemented properly.
//        Asteroid asteroid1 = new Asteroid(new Vector2(300, 100), new Vector2(-15, -20), random.nextFloat(-30, 30));
//        Asteroid asteroid2 = new Asteroid(new Vector2(600, 150), new Vector2(10, -10), random.nextFloat(-30, 30));
//        Asteroid asteroid3 = new Asteroid(new Vector2(300, 400), new Vector2(5, 10), random.nextFloat(-30, 30));

        int asteroidCount = 10;
        for (int i = 0; i < asteroidCount; i++) {
            asteroidList.add(characterFactory.getAsteroid());
        }

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
        this.player.accelerate(100f, deltaTime);
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
        // Since the game happens in space, objects should drift with a constant velocity.
        this.player.move(deltaTime, mapWidth, mapHeight);

        for (Asteroid asteroid : asteroidList) {
            asteroid.move(deltaTime, mapWidth, mapHeight);
            asteroid.rotateShapeBy(asteroid.getDegreesOfRotationPerSecond() * Settings.getIntervalMillis() / 1000f);
        }

        for (Bullet bullet : bulletList) {
            bullet.move(deltaTime, mapWidth, mapHeight);
        }
    }

    @Override
    public int getScore() {
        return this.playerScore;
    }

    @Override
    public List<Bullet> getBulletList() {
        return bulletList;
    }
}
