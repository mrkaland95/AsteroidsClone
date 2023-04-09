package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

/*
Base class for the player and computer controlled character classes.

The reason for using an abstract class, is because these classes share a lot of functionality.
 */


public abstract class BaseCharacter {
    private Vector2 position;
    private Vector2 velocity;
    private float angle;
    private int size;
    private int direction;
//    private Shape shape;

//    public baseCharacter(Vector2 startPosition) {
//        this.position = startPosition;
//        this.velocity = new Vector2(0, 0);
//    }

    public abstract float[][] getShape();
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

    public void rotate() {}
}
