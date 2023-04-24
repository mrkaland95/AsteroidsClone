package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Bullet extends BaseCharacter {

    public Bullet(Vector2 startPosition, Vector2 startVelocity, float startAngle) {
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
        this.setCurrentAngle(startAngle);
        // These are stored like this, because abstract classes do not allow accessing the "currentShape" field variable directly.
        this.setCurrentShape(getBaseShape());
    }

    @Override
    public float[][] getBaseShape() {
        // Shape of a small square
        float[][] shape = {
            {-1, -1},
            {1, -1},
            {1, 1},
            {-1, 1}
        };
        return shape;
    }

    @Override
    public void move(double deltaTime, int mapWidth, int mapHeight) {
        this.setPosition(Vector2.translateOverTime(this.getPosition(), this.getVelocity(), deltaTime));
    }

    /** Checks if the bullet is within the bounds of the map.
     * @param mapWidth
     * @param mapHeight
     * @return Boolean of whether the object is inside the map or not.
     */
    public boolean isOutOfBounds(int mapWidth, int mapHeight) {
        if (this.getPosition().x() < 0 || this.getPosition().x() > mapWidth) {
            return true;
        } else if (this.getPosition().y() < 0 || this.getPosition().y() > mapHeight) {
            return true;
        }
        return false;
    }
}
