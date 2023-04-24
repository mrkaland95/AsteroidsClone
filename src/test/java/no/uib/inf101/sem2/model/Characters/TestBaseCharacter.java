package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*
Class for testing the basecharacter, but also partially the asteroid class.
 */
public class TestBaseCharacter {
    private Asteroid character;

    @BeforeEach
    void setUp() {
        float startRotation = 5f;
        float baseSize = 1f;
        character = new Asteroid(new Vector2(0, 0), new Vector2(0, 0), startRotation, baseSize);
    }

    @Test
    void testMove() {
        character.setVelocity(new Vector2(1, 1));
        character.move(1, 100, 100);
        assertEquals(new Vector2(1, 1), character.getPosition());
    }
    @Test
    void testWrapAround() {
        character.setPosition(new Vector2(-1, -1));
        character.wrapAround(100, 100);
        assertEquals(new Vector2(99, 99), character.getPosition());
    }

    @Test
    void testRotateCurrentShape() {
        character.rotateCurrentShape(90);
        assertEquals(90, character.getCurrentAngle());
    }
}
