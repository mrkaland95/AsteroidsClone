package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;


public class PlayerShip extends BaseCharacter {
    private boolean accelerating;

    public PlayerShip(Vector2 startPosition) {
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
        this.setCurrentShape(getBaseShape());
        this.accelerating = false;
    }

    protected float[][] getBaseShape() {
        float[][] shape = {
            {0f,  -10f},
            {10f,  10f},
            {0f,   5f},
            {-10f, 10f}
        };

        return shape;
    }
    public float[][] getFireShape() {
        return new float[][]{
            {0.5f, -1f},
            {1f, 0f},
            {0.5f, 1f},
            {-0.5f, 1f},
            {-1f, 0f},
            {-0.5f, -1f},
        };
    }

    /**
     * Shoots a bullet, firing in the same direction as the ship is pointing.
     * @param bulletSpeed
     * @return
     */
    public Bullet shootBullet(float bulletSpeed) {
        Vector2 bulletPosition = getPosition();
        float angle = getCurrentAngle() - 90f;

        double radians = Math.toRadians(angle);

        float bulletVelocityX = (float) (bulletSpeed * Math.cos(radians));
        float bulletVelocityY = (float) (bulletSpeed * Math.sin(radians));

        Vector2 bulletVelocity = new Vector2(bulletVelocityX, bulletVelocityY);
        return new Bullet(bulletPosition, bulletVelocity, angle);
    }

    @Override
    public void accelerate(float acceleration, double deltaTime) {
        // Beacuse the angle starts paralel to the x axis, we need to subtract by 90 degrees.
        double radians = Math.toRadians(getCurrentAngle() - 90d);

        float thrustX = (float) (acceleration * Math.cos(radians));
        float thrustY = (float) (acceleration * Math.sin(radians));
        this.setVelocity(Vector2.translateOverTime(getVelocity(), new Vector2(thrustX, thrustY), deltaTime));
        // Sets the acceleration to true if the acceleration is greater or lesser than 0.
        // Variable used for tracking whether the "flame" graphics should be drawn or not.
        this.setAccelerating((acceleration != 0f));
    }


    public boolean isAccelerating() {
        return accelerating;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

}
