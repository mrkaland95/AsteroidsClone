package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;

public interface ControllableAsteroidModel {
    GameState getGameState();

    /**
     * Accelerates the player's ship
     * @param deltaTime
     */
    void accelerateShip(double deltaTime);

    /**
     * Rotates the player's ship by the given angle.
     * @param deltaTime
     * @param angle
     */
    void rotateShip(double deltaTime, float angle);

    /**
     * Fires a projectile from the player's ship that can kill adversaries.
     * @param deltaTime
     */
    void fireFromShip(double deltaTime);

    /** Method responsible for updating the state of the game, i.e
     * Moving the game objects, perform player actions, remove killed game object.
     *
     * @param deltaTime The time since the last frame was rendered.
     */
    void update(double deltaTime);

    void resetGame();
}
