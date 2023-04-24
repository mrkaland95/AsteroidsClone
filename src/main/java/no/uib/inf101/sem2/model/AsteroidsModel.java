package no.uib.inf101.sem2.model;

import no.uib.inf101.sem2.controller.ControllableAsteroidModel;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.Factories.CharacterFactory;
import no.uib.inf101.sem2.model.Utils.Vector2;
import no.uib.inf101.sem2.view.ViewableAsteroidsModel;

import java.util.ArrayList;
import java.util.List;

import static no.uib.inf101.sem2.model.Settings.*;

public class AsteroidsModel implements ViewableAsteroidsModel, ControllableAsteroidModel {
    private int playerScore;
    private int playerLives;
    private final PlayerShip player;
    private GameState gameState;
    private final CharacterFactory characterFactory;
    private List<Asteroid> asteroidList = new ArrayList<>();
    private List<Bullet> bulletList = new ArrayList<>();
    private int mapWidth;
    private int mapHeight;
    private float invulnerableTime;



    /** Constructor for the model holding the game state.
     * @param characterFactory Character factory to create character objects.
     */
    public AsteroidsModel(CharacterFactory characterFactory) {
        this.characterFactory = characterFactory;
        this.mapWidth = MAP_WIDTH;
        this.mapHeight = MAP_HEIGHT;
        this.playerScore = 0;
        this.playerLives = INITIAL_PLAYER_LIVES;
        this.invulnerableTime = 0f;
        // Spawn the player in the middle of the map.
        this.player = new PlayerShip(new Vector2(this.mapWidth / 2f, this.mapHeight / 2f));
        this.gameState = GameState.ACTIVE_GAME;
    }

    @Override
    public void update(double deltaTime) {
        // Since the game happens in space, objects should drift with a constant velocity.
        player.move(deltaTime, mapWidth, mapHeight);

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
                    playerScore += ASTEROID_KILLED_SCORE;

                    // If a large asteroid was destroyed, spawn 2 smaller ones at the position of the large one.
                    if (asteroid.getBaseSize() >= 2) {
                        List<Asteroid> smallAsteroids = characterFactory.getSmallAsteroidPair(asteroid.getPosition());
                        asteroidsToAdd.addAll(smallAsteroids);
                    }
                }
            }
        }

        // Remove objects that have collided, and spawn smaller asteroids if applicable.
        bulletList.removeAll(bulletsToRemove);
        asteroidList.removeAll(asteroidsToRemove);
        asteroidList.addAll(asteroidsToAdd);


        if (invulnerableTime > 0) {
            invulnerableTime -= deltaTime;
            if (invulnerableTime <= 0) {
                player.setInvulnerable(false);
            }
        }

        asteroidsToRemove = new ArrayList<>();
        // Handle player -> asteroid collisions. Reduce the players lives when collisions happen
        // And respawn the player to the center of the map.
        if (!player.isInvulnerable()) {
            for (Asteroid asteroid : asteroidList) {
                if (asteroid.collisionOccurred(player)) {
                    asteroidsToRemove.add(asteroid);
                    playerLives -= 1;
                    player.setPosition(new Vector2(mapWidth / 2f, mapHeight / 2f));
                    player.setVelocity(new Vector2(0, 0));
                    player.setInvulnerable(true);
                    invulnerableTime = 2f; // Set respawn time (in seconds)
                    break;
                }
            }
        }

        asteroidList.removeAll(asteroidsToRemove);
        if (playerLives <= 0) {
            this.gameState = GameState.GAME_OVER;
        }
        // Creates new asteroids if at the start of the game, or too many has been destroyed.
        generateNewAsteroids();
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
        player.accelerate(SHIP_ACCELERATION_PER_SECOND, deltaTime);
    }

    @Override
    public void rotateShip(double deltaTime, float angle) {
        float effectiveAngle = (float) (angle * deltaTime);
        player.rotateCurrentShape(effectiveAngle);
    }

    @Override
    public void fireFromShip(double deltaTime) {
        bulletList.add(this.player.shootBullet(BULLET_VELOCITY_PER_SECOND));
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

    @Override
    public int getMapHeight() {
        return mapHeight;
    }
    @Override
    public int getMapWidth() {
        return mapWidth;
    }

    public void resetGame() {
        this.gameState = GameState.ACTIVE_GAME;
        this.playerLives = Settings.INITIAL_PLAYER_LIVES;
        this.playerScore = 0;
        this.player.setPosition(new Vector2(this.mapWidth / 2f, this.mapHeight / 2f));
        this.player.setVelocity(new Vector2(0, 0));
        this.asteroidList = new ArrayList<>();
        this.bulletList = new ArrayList<>();
    }

    @Override
    public void setShipAccelerationState(boolean accelerationState) {
        this.player.setAccelerating(accelerationState);
    }

    public void setMapDimensions(int newWidth, int newHeight) {
        this.mapWidth = newWidth;
        this.mapHeight = newHeight;
    }

    public void scaleAllGameObjects(float widthScale, float heightScale) {
        player.scale(widthScale, heightScale);
        for (Asteroid asteroid : asteroidList) {
            asteroid.scale(widthScale, heightScale);
        }
        for (Bullet bullet : bulletList) {
            bullet.scale(widthScale, heightScale);
        }
    }

    /** Helper method to ensure an asteroid doesn't spawn too close to a player.
     * @param asteroid
     * @return
     */
    boolean checkValidAsteroidSpawn(Asteroid asteroid) {
        Vector2 playerPosition = player.getPosition();
        Vector2 asteroidPosition = asteroid.getPosition();

        double distance = Vector2.distance(playerPosition, asteroidPosition);
        // Check if the distance is greater than the minimum spawn distance
        return distance > MIN_SPAWN_DISTANCE_FROM_SHIP;
    }

    /**
     * Generates new asteroids, up to the maximum amount allowed. Only allows asteroids that are not too close to the player.
     */
    void generateNewAsteroids() {
        if (asteroidList.size() < MINIMUM_ASTEROID_COUNT) {
            int asteroidsToSpawn = MAXIMUM_ASTEROID_COUNT - asteroidList.size();

            for (int i = 0; i < asteroidsToSpawn; i++) {
                Asteroid newAsteroid = characterFactory.getLargeAsteroid();

                // Ensure that the asteroid is not too close to the player
                while (!checkValidAsteroidSpawn(newAsteroid)) {
                    newAsteroid = characterFactory.getLargeAsteroid();
                }
                asteroidList.add(newAsteroid);
            }
        }
    }
}
