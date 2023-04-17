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

    @Override
    public void move(double deltaTime, int mapWidth, int mapHeight) {
        this.setPosition(Vector2.translateOverTime(this.getPosition(), this.getVelocity(), deltaTime));
    }

    public boolean isOutOfBounds(int mapWidth, int mapHeight) {
        if (this.getPosition().x() < 0 || this.getPosition().x() > mapWidth) {
            return true;
        } else if (this.getPosition().y() < 0 || this.getPosition().y() > mapHeight) {
            return true;
        }
        return false;
    }
}
