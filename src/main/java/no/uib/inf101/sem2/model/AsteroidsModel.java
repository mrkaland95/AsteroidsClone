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
        this.mapWidth = 1000;
        this.mapHeight = 1000;
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new PlayerShip(new Vector2(this.mapWidth / 2f, this.mapHeight / 2f));
        this.gameState = GameState.ACTIVE_GAME;


        // Initialize asteroids.
        int asteroidCount = 10;
        for (int i = 0; i < asteroidCount; i++) {
            asteroidList.add(this.characterFactory.getAsteroid());
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
        float shipAccelerationPerSecond = 100f;
        this.player.accelerate(shipAccelerationPerSecond, deltaTime);
    }

    @Override
    public void rotateShip(double deltaTime, float angle) {
        this.player.rotateShapeBy(angle);
    }

    @Override
    public void fireFromShip(double deltaTime) {
        float bulletVelocityPerSecond = 400f;
        bulletList.add(this.player.shootBullet(bulletVelocityPerSecond, deltaTime));
    }

    @Override
    public void update(double deltaTime) {
        // Since the game happens in space, objects should drift with a constant velocity.
        this.player.move(deltaTime, mapWidth, mapHeight);

        // Move the asteroid and rotate it according to it's set rotation speed.
        for (Asteroid asteroid : asteroidList) {
            asteroid.move(deltaTime, mapWidth, mapHeight);
            asteroid.rotateShapeBy(asteroid.getDegreesOfRotationPerSecond() * Settings.getIntervalMillis() / 1000f);
        }

        List<Bullet> newBulletList = new ArrayList<>();
        for (Bullet bullet : bulletList) {
            bullet.move(deltaTime, mapWidth, mapHeight);
            if (!bullet.isOutOfBounds(mapWidth, mapHeight)) {
                newBulletList.add(bullet);
            }
        }
        bulletList = newBulletList;

        List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Asteroid> asteroidsToRemove = new ArrayList<>();

        for (Bullet bullet : bulletList) {
            for (Asteroid asteroid : asteroidList) {
                if (bullet.collisionOccurred(asteroid)) {
                    bulletsToRemove.add(bullet);
                    asteroidsToRemove.add(asteroid);
                    playerScore += 10;
                }
            }
        }

        bulletList.removeAll(bulletsToRemove);
        asteroidList.removeAll(asteroidsToRemove);

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
