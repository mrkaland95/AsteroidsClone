package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.*;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Utils.Vector2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBullet {

    @Test
    public void testBulletOutOfBounds() {
        int mapWidth = 800;
        int mapHeight = 600;

        // Test bullet outside the map to the right
        Bullet bullet1 = new Bullet(new Vector2(mapWidth + 1, 300), new Vector2(0, 0), 0);
        assertTrue(bullet1.isOutOfBounds(mapWidth, mapHeight), "Bullet should be out of bounds");

        // Test bullet outside the map to the left
        Bullet bullet2 = new Bullet(new Vector2(-1, 300), new Vector2(0, 0), 0);
        assertTrue(bullet2.isOutOfBounds(mapWidth, mapHeight), "Bullet should be out of bounds");

        // Test bullet outside the map at the top
        Bullet bullet3 = new Bullet(new Vector2(400, mapHeight + 1), new Vector2(0, 0), 0);
        assertTrue(bullet3.isOutOfBounds(mapWidth, mapHeight), "Bullet should be out of bounds");

        // Test bullet outside the map at the bottom
        Bullet bullet4 = new Bullet(new Vector2(400, -1), new Vector2(0, 0), 0);
        assertTrue(bullet4.isOutOfBounds(mapWidth, mapHeight), "Bullet should be out of bounds");

        // Test bullet inside the map
        Bullet bullet5 = new Bullet(new Vector2(400, 300), new Vector2(0, 0), 0);
        assertFalse(bullet5.isOutOfBounds(mapWidth, mapHeight), "Bullet should be inside the bounds");
    }
}
