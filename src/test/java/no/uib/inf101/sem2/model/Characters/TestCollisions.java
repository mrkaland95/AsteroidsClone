package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCollisions {


    @Test
    void playerShipCollisionWithAsteroid() {
        PlayerShip playerShip = new PlayerShip(new Vector2(200, 200));
        Asteroid asteroid = new Asteroid(new Vector2(200, 200), new Vector2(0, 0), 0, 1);

        assertTrue(playerShip.collisionOccurred(asteroid), "Player ship should collide with asteroid");
    }

    @Test
    void playerShipNoCollisionWithAsteroid() {
        PlayerShip playerShip = new PlayerShip(new Vector2(100, 100));
        Asteroid asteroid = new Asteroid(new Vector2(200, 200), new Vector2(0, 0), 0, 1);

        assertFalse(playerShip.collisionOccurred(asteroid), "Player ship should not collide with asteroid");
    }

    @Test
    void bulletCollisionWithAsteroid() {
        Vector2 startVelocity = new Vector2(0, 0);
        Bullet bullet = new Bullet(new Vector2(150, 150), startVelocity, 0);
        Asteroid asteroid = new Asteroid(new Vector2(150, 150), startVelocity, 0, 1);

        assertTrue(bullet.collisionOccurred(asteroid), "Bullet should collide with asteroid");
    }

    @Test
    void bulletNoCollisionWithAsteroid() {
        Bullet bullet = new Bullet(new Vector2(100, 100), new Vector2(0, 0), 0);
        Asteroid asteroid = new Asteroid(new Vector2(200, 200), new Vector2(0, 0), 0, 1);

        assertFalse(bullet.collisionOccurred(asteroid), "Bullet should not collide with asteroid");
    }
}
