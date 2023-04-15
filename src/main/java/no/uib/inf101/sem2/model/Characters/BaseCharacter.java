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

    /** Defines the base shape of the character, defined as points, which lines will be drawn between.
     *  Default shape should be pointing upwards, if relevant.
     * @return
     */
    protected abstract float[][] getBaseShape();

    /**
     * Gets the state of the current shape of the object, including it's rotated state.
     * @return
     */
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
    public void move(double deltaTime, int mapWidth, int mapHeight) {
        this.position = Vector2.translateOverTime(this.position, this.velocity, deltaTime);
        this.wrapAround(mapWidth, mapHeight);

    }
    public void wrapAround(int mapWidth, int mapHeight) {
        float posX = position.x();
        float posY = position.y();

        if (posX < 0) {
            posX += mapWidth;
        } else if (posX > mapWidth) {
            posX -= mapWidth;
        }

        if (posY < 0) {
            posY += mapHeight;
        } else if (posY > mapHeight) {
            posY -= mapHeight;
        }
        this.setPosition(new Vector2(posX, posY));
    }





    public void accelerate(float acceleration, double deltaTime) {

        double radians = Math.toRadians(currentAngle - 90f);

        float thrustX = (float) (acceleration * Math.cos(radians));
        float thrustY = (float) (acceleration * Math.sin(radians));
        this.velocity = Vector2.translateOverTime(this.velocity, new Vector2(thrustX, thrustY), deltaTime);
    }
    /** Method responsible for rotating the direction and shape a character is pointing towards.
     * 0/360 degrees is upwards.
     *
     * @param angle The angle that the shape of the character should be rotated by.
     */
    public void rotateShapeBy(float angle) {

        // Limits to a number between 0-360 degrees.
        this.currentAngle = (this.currentAngle + angle) % 360;
        if (this.currentAngle < 0) {
            this.currentAngle += 360;
        }
        // Calculates the center point of the shape and rotates the shape relative to that point.
        Vector2 centerPoint = calculateCenter(this.currentShape);
        this.currentShape = rotateShape(this.currentShape, angle, centerPoint);
    }

    public Vector2 getShapeCenter() {
        float[][] points = getCurrentShape();
        return calculateCenter(points);
    }


    /** Utility method used to rotate the points in a shape by a given angle.
     * ChatGPT was used for figuring this one out
     * @param angle
     */
    private static float[][] rotateShape(float[][] points, float angle, Vector2 centerPosition) {
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

    /** Utility method for calculating the center point of a shape.
     * ChatGPT was partially used for making this one
     *
     * @param points
     */
    private static Vector2 calculateCenter(float[][] points) {
        float[] center = {0, 0};

        for (float[] point : points) {
            center[0] += point[0];
            center[1] += point[1];
        }

        center[0] /= points.length;
        center[1] /= points.length;

        return new Vector2(center[0], center[1]);
    }

    /**
     * @return
     */
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
