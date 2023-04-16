package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Bullet extends BaseCharacter {

    public Bullet(Vector2 startPosition, Vector2 startVelocity, float angle) {
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
        this.setCurrentAngle(angle);
        this.setCurrentShape(getBaseShape());
    }

    @Override
    protected float[][] getBaseShape() {
        float[][] shape = {
            {-1, -1},
            {1, -1},
            {1, 1},
            {-1, 1}
        };
        return shape;
    }
}
