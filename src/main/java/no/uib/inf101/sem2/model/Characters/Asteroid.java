package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Asteroid extends BaseCharacter {
    public Asteroid(Vector2 startPosition, Vector2 startVelocity) {
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
    }

    public Asteroid(Vector2 startPosition) {
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
    }

    @Override
    public float[][] getShape() {
        float[][] points = {
            {0, 0},
            {10, 0},
            {20, 10},
            {20, 20},
            {10, 20},
            {0, 10},
        };
        return points;
    }
}
