package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Asteroid extends BaseCharacter {
    private float degreesOfRotationPerSecond;
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

    /**
     *  Generates random shape, roughly looking like a sphere,
     *  but with enough protrusions that it generally looks like an asteroid.
     * @return
     */
    @Override
    public float[][] getBaseShape() {
        int numPoints = 8;
        float minRadius = 10f;
        float maxRadius = 15f;
        float[][] shape = new float[numPoints][2];
        double angleStep = 2 * Math.PI / numPoints;

        for (int i = 0; i < numPoints; i++) {
            double angle = i * angleStep;
            float radius = minRadius + (float) (Math.random() * (maxRadius - minRadius));
            shape[i][0] = radius * (float) Math.cos(angle);
            shape[i][1] = radius * (float) Math.sin(angle);
        }

        return shape;
    }
    @Override
    public void scaleCurrentShape(float scaleFactor) {
        setSizeScalar(scaleFactor);
        setCurrentShape(this.scaleShape(getCurrentShape(), scaleFactor * this.baseSize));
    }
    /** Gets the speed of rotation of the asteroid.
     * @return degrees rotated per second.
     */
    public float getDegreesOfRotationPerSecond() {
        return degreesOfRotationPerSecond;
    }

    /** Gets the base size of the asteroid.
     * @return Scalar of the base size of the asteroid.
     */
    public float getBaseSize() {
        return baseSize;
    }

}
