package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.AsteroidsModel;
import no.uib.inf101.sem2.model.Factories.CharacterFactory;
import no.uib.inf101.sem2.model.Factories.RandomCharacterFactory;
import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.model.Utils.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAsteroidsModel {
    private AsteroidsModel asteroidsModel;

    @BeforeEach
    public void setUp() {
        CharacterFactory characterFactory = new RandomCharacterFactory();
        asteroidsModel = new AsteroidsModel(characterFactory);
    }

    @Test
    public void testFireBullet() {
        PlayerShip playerShip = asteroidsModel.getPlayerShip();
        float updateInterval = Settings.getUpdateIntervalFloat();

        // Initial state: No bullets
        assertEquals(0, asteroidsModel.getBulletList().size());

        // Fire a bullet
        asteroidsModel.fireFromShip(updateInterval);

        // One bullet should be in the game
        assertEquals(1, asteroidsModel.getBulletList().size());

        Bullet bullet = asteroidsModel.getBulletList().get(0);

        // Check the bullet's initial position
        assertEquals(playerShip.getPosition(), bullet.getPosition());
        assertEquals(playerShip.getCurrentAngle() - 90, bullet.getCurrentAngle(), 0.001);
    }

    @Test
    public void testRotateShip() {
        PlayerShip playerShip = asteroidsModel.getPlayerShip();
        float updateInterval = Settings.getUpdateIntervalFloat();

        // Initial rotation angle
        float initialRotation = playerShip.getCurrentAngle();

        // Rotate the ship 10 degrees clockwise
        float rotationAngle = 10;
        asteroidsModel.rotateShip(updateInterval, rotationAngle);

        // Check the updated rotation angle
        assertEquals(initialRotation + rotationAngle, playerShip.getCurrentAngle(), 0.001);

        // Rotate the ship 10 degrees counterclockwise
        asteroidsModel.rotateShip(updateInterval, -rotationAngle);

        // Check the rotation angle is back to the initial value
        assertEquals(initialRotation, playerShip.getCurrentAngle(), 0.001);
    }
    @Test
    public void testAccelerateShip() {
        PlayerShip playerShip = asteroidsModel.getPlayerShip();
        float updateInterval = Settings.getUpdateIntervalFloat();

        // Initial velocity
        Vector2 initialVelocity = playerShip.getVelocity();

        // Accelerate the ship
        asteroidsModel.accelerateShip(updateInterval);

        // Check the updated velocity
        Vector2 updatedVelocity = playerShip.getVelocity();
        assertTrue(updatedVelocity.x() != initialVelocity.x() || updatedVelocity.y() != initialVelocity.y(),
                "The ship's velocity should change after acceleration.");
    }
    @Test
    public void testGetScore() {
        int initialScore = asteroidsModel.getScore();
        assertEquals(0, initialScore, "Initial score should be 0.");
    }


    @Test
    public void testGetBulletList() {
        float updateInterval = Settings.getUpdateIntervalFloat();

        // Initial bullet list
        List<Bullet> initialBulletList = asteroidsModel.getBulletList();
        assertEquals(0, initialBulletList.size(), "Initial bullet list should be empty.");

        // Fire a bullet
        asteroidsModel.fireFromShip(updateInterval);

        // Check the updated bullet list
        List<Bullet> updatedBulletList = asteroidsModel.getBulletList();
        assertEquals(1, updatedBulletList.size(), "Bullet list should contain 1 bullet after firing.");
    }
}

