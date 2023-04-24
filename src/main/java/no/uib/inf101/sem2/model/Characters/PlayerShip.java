package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.model.Utils.Vector2;


public class PlayerShip extends BaseCharacter {
    private boolean accelerating;
    private boolean invulnerable;


    public PlayerShip(Vector2 startPosition) {
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
        this.setCurrentShape(getBaseShape());
        this.accelerating = false;
        this.invulnerable = false;

    }
    public float[][] getBaseShape() {
        float[][] shape = {
            {0f,  -10f},
            {10f,  10f},
            {0f,   5f},
            {-10f, 10f}
        };

        return shape;
    }

    /** Gets the fire shape of the ship.
     * @return
     */
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
        // Beacuse the angle starts parallel to the x axis, we need to subtract by 90 degrees.
        double radians = Math.toRadians(getCurrentAngle() - 90d);

        float thrustX = (float) (acceleration * Math.cos(radians));
        float thrustY = (float) (acceleration * Math.sin(radians));
        Vector2 newVelocity = Vector2.translateOverTime(getVelocity(), new Vector2(thrustX, thrustY), deltaTime);

        // Velocity limit
        float maxVelocity = Settings.SHIP_MAX_VELOCITY;
        float velocityMagnitude = (float) Math.sqrt(newVelocity.x() * newVelocity.x() + newVelocity.y() * newVelocity.y());

        if (velocityMagnitude > maxVelocity) {
            newVelocity = new Vector2(newVelocity.x() * (maxVelocity / velocityMagnitude),
                                      newVelocity.y() * (maxVelocity / velocityMagnitude));
        }

        this.setVelocity(newVelocity);
        // Sets the acceleration to true if the acceleration is greater or lesser than 0.
        // Variable used for tracking whether the "flame" graphics should be drawn or not.
        this.setAccelerating((acceleration != 0f));
    }


    /** Gets the state of whether the ship is invulnerable or not.
     * @return
     */
    public boolean isInvulnerable() {
        return invulnerable;
    }

    /**
     * @param invulnerable
     */
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    /** Gets the ships state of acceleration.
     * @return
     */
    public boolean isAccelerating() {
        return accelerating;
    }

    /** Sets the ships state of acceleration
     * @param accelerating
     */
    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

}
