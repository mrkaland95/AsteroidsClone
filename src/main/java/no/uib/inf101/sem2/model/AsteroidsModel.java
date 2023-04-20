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

public class AsteroidsModel implements ViewableAsteroidsModel, ControllableAsteroidModel {
    private int elapsedGameTime;
    private int playerScore;
    private int playerLives;
    private final PlayerShip player;
    private GameState gameState;
    private final CharacterFactory characterFactory;
    private List<BaseCharacter> UFOList = new ArrayList<>();
    private List<Asteroid> asteroidList = new ArrayList<>();
    private List<Bullet> bulletList = new ArrayList<>();
    private final int mapWidth;
    private final int mapHeight;


    /** Constructor for the model holding the game state.
     * @param characterFactory Character factory to create character objects.
     */
    public AsteroidsModel(CharacterFactory characterFactory) {
        this.characterFactory = characterFactory;
        this.elapsedGameTime = 0;
        this.mapWidth = Settings.mapWidth;
        this.mapHeight = Settings.mapHeight;
        this.playerScore = 0;
        this.playerLives = 3;
        this.player = new PlayerShip(new Vector2(this.mapWidth / 2f, this.mapHeight / 2f));
        this.gameState = GameState.ACTIVE_GAME;


        // Initialize asteroids.
        int asteroidCount = Settings.MAXIMUM_ASTEROID_COUNT;
        for (int i = 0; i < asteroidCount; i++) {
            asteroidList.add(this.characterFactory.getLargeAsteroid());
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
        float shipAccelerationPerSecond = Settings.SHIP_ACCELERATION_PER_SECOND;
        this.player.accelerate(shipAccelerationPerSecond, deltaTime);
    }

    @Override
    public void rotateShip(double deltaTime, float angle) {
        this.player.rotateCurrentShape(angle);
    }

    @Override
    public void fireFromShip(double deltaTime) {
        float bulletVelocity = Settings.BULLET_VELOCITY_PER_SECOND;
        bulletList.add(this.player.shootBullet(bulletVelocity));
    }

    @Override
    public void update(double deltaTime) {
        // Since the game happens in space, objects should drift with a constant velocity.
        this.player.move(deltaTime, mapWidth, mapHeight);

        // Move and rotate the asteroids
        for (Asteroid asteroid : asteroidList) {
            asteroid.move(deltaTime, mapWidth, mapHeight);
            asteroid.rotateCurrentShape(asteroid.getDegreesOfRotationPerSecond() * Settings.getIntervalMillis() / 1000f);
        }

        // Move bullets and remove ones that are out of the map.
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
        List<Asteroid> asteroidsToAdd = new ArrayList<>();

        // Handle bullet -> asteroid collisions, and add score.
        for (Bullet bullet : bulletList) {
            for (Asteroid asteroid : asteroidList) {
                if (bullet.collisionOccurred(asteroid)) {
                    bulletsToRemove.add(bullet);
                    asteroidsToRemove.add(asteroid);
                    playerScore += Settings.ASTEROID_KILLED_SCORE;

                    // If a large asteroid was destroyed, spawn 2 smaller ones.
                    if (asteroid.getBaseSize() >= 2) {
                        List<Asteroid> smallAsteroids = characterFactory.getSmallAsteroidPair(asteroid.getPosition());
                        asteroidsToAdd.addAll(smallAsteroids);
                    }
                }
            }
        }
        // Remove objects that have collided, and spawn smaller asteroids if relevant.
        bulletList.removeAll(bulletsToRemove);
        asteroidList.removeAll(asteroidsToRemove);
        asteroidList.addAll(asteroidsToAdd);


        asteroidsToRemove = new ArrayList<>();
        // Handle player -> asteroid collisions. Reduce the players lives when collisions happen
        for (Asteroid asteroid : asteroidList) {
            if (player.collisionOccurred(asteroid)) {
                asteroidsToRemove.add(asteroid);
                playerLives -= 1;
            }
        }
        System.out.println(playerLives);

//        asteroidList.removeAll(asteroidsToRemove);
//        if (playerLives <= 0) {
//            this.gameState = GameState.GAME_OVER;
//        }
    }

    @Override
    public int getPlayerLives() {
        return playerLives;
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
