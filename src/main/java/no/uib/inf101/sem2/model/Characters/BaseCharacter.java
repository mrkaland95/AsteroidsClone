package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

/*
Base class for the player and computer controlled character classes.

The reason for using an abstract class, is because these classes share a lot of functionality.
 */


public abstract class BaseCharacter {
    private Vector2 position;
    private Vector2 velocity;
    private float[][] currentShape;
    private float currentAngle;
    private int sizeScalar;
    abstract float[][] getBaseShape();
    public float[][] getCurrentShape() {
        return currentShape;
    }
    public void setCurrentShape(float[][] currentShape) {
        this.currentShape = currentShape;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean collidedWith(BaseCharacter character) {
        // TODO implement this
        return false;
    }

    public void move(double deltaTime) {
        this.position = Vector2.translateOverTime(this.position, this.velocity, deltaTime);
    }

    public void accelerate(Vector2 acceleration, double deltaTime) {
        this.velocity = Vector2.translateOverTime(this.velocity, acceleration, deltaTime);
    }

    public void rotateShapeBy(float angle) {
        this.currentAngle += angle;
        Vector2 centerPoint = calculateCenter(this.currentShape);
        this.currentShape = rotateShape(this.currentShape, angle, centerPoint);
    }



    /**
     * ChatGPT was used for figuring this one out
     * @param angle
     */
    private float[][] rotateShape(float[][] points, float angle, Vector2 centerPosition) {
        float[][] rotatedPoints = new float[points.length][2];
        double radians = Math.toRadians(angle);
        float cosAngle = (float) Math.cos(radians);
        float sinAngle = (float) Math.sin(radians);

        for (int i = 0; i < points.length; i++) {
            float dx = points[i][0] - centerPosition.x();
            float dy = points[i][1] - centerPosition.y();
            rotatedPoints[i][0] = centerPosition.x() + (cosAngle * dx - sinAngle * dy);
            rotatedPoints[i][1] = centerPosition.y() + (sinAngle * dx + cosAngle * dy);
        }

        return rotatedPoints;
    }

    /**
     * ChatGPT was partially used for making this one
     * Calculates the center point of a shape, which point will be used to rotate around.
     *
     * @param points
     */
    private Vector2 calculateCenter(float[][] points) {
        float[] center = {0, 0};

        for (float[] point : points) {
            center[0] += point[0];
            center[1] += point[1];
        }

        center[0] /= points.length;
        center[1] /= points.length;

        return new Vector2(center[0], center[1]);
    }

    public float getCurrentAngle() {
        return currentAngle;
    }
    public void setCurrentAngle(float currentAngle) {
        this.currentAngle = currentAngle;
    }

    public int getSizeScalar() {
        return sizeScalar;
    }

    public void setSizeScalar(int sizeScalar) {
        this.sizeScalar = sizeScalar;
    }

}
