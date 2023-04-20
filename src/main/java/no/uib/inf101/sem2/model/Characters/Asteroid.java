package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Asteroid extends BaseCharacter {
    private float degreesOfRotationPerSecond = 20f;
    private float baseSize;
    public Asteroid(Vector2 startPosition, Vector2 startVelocity, float rotationPerSecond, float baseSize) {
        this.setCurrentShape(getBaseShape());
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
        this.degreesOfRotationPerSecond = rotationPerSecond;
        this.baseSize = baseSize;
        this.setSizeScalar(1f);
        this.scaleCurrentShape(getSizeScalar());
    }

    public Asteroid(Vector2 startPosition, Vector2 startVelocity, float rotationPerSecond, float startingAngle, float baseSize) {
        this.setCurrentShape(getBaseShape());
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
        this.degreesOfRotationPerSecond = rotationPerSecond;
        this.baseSize = baseSize;
        this.setSizeScalar(1f);
        this.scaleCurrentShape(getSizeScalar());
        this.rotateCurrentShape(startingAngle);
    }



    @Override
    protected float[][] getBaseShape() {
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
    public float getDegreesOfRotationPerSecond() {
        return degreesOfRotationPerSecond;
    }

    public float getBaseSize() {
        return baseSize;
    }
    @Override
    public void scaleCurrentShape(float scaleFactor) {
        setSizeScalar(scaleFactor);
        setCurrentShape(this.scaleShape(getCurrentShape(), scaleFactor * this.baseSize));
    }
}
