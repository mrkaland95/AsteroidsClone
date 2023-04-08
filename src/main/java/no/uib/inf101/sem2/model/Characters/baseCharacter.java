package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

import java.awt.*;

/*
Base class for the player and computer controlled character classes.

The reason for using an abstract class, is because these classes share a lot of functionality.
 */


public abstract class baseCharacter implements IBaseCharacter {
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

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean collidedWith(IBaseCharacter character) {
        // TODO implement this
        return false;
    }

    @Override
    public void update(double deltaTime) {
        // Updates the position of a character every frame by
        double newX = position.x() + velocity.x() * deltaTime;
        double newY = position.y() + velocity.y() * deltaTime;
        this.position = new Vector2((float) newX,(float) newY);
    }
}
