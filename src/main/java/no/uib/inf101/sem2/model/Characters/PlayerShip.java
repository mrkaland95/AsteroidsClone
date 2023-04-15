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

    @Override
    public void accelerate(float acceleration, double deltaTime) {
        // Beacuse the angle starts paralel to the x axis, we need to subtract by 90 degrees.
        double radians = Math.toRadians(getCurrentAngle() - 90d);

        float thrustX = (float) (acceleration * Math.cos(radians));
        float thrustY = (float) (acceleration * Math.sin(radians));
        setVelocity(Vector2.translateOverTime(getVelocity(), new Vector2(thrustX, thrustY), deltaTime));
        // Sets the acceleration to true if the acceleration is greater or lesser than 0.
        this.setAccelerating((acceleration != 0f));
    }


    public boolean isAccelerating() {
        return accelerating;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

}
