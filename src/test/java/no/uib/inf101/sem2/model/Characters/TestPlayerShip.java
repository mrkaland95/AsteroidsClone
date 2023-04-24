package no.uib.inf101.sem2.model.Characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.model.Utils.Vector2;
import org.junit.jupiter.api.Test;

public class TestPlayerShip {

    @Test
    public void testShootBullet() {
        PlayerShip ship = new PlayerShip(new Vector2(0, 0));
        float bulletSpeed = 200f;
        Bullet bullet = ship.shootBullet(bulletSpeed);

        // Check bullet position
        assertEquals(ship.getPosition(), bullet.getPosition());

        // Check bullet velocity
        float expectedBulletVelocityX = (float) (bulletSpeed * Math.cos(Math.toRadians(-90f)));
        float expectedBulletVelocityY = (float) (bulletSpeed * Math.sin(Math.toRadians(-90f)));
        assertEquals(expectedBulletVelocityX, bullet.getVelocity().x(), 0.001f);
        assertEquals(expectedBulletVelocityY, bullet.getVelocity().y(), 0.001f);
    }
}