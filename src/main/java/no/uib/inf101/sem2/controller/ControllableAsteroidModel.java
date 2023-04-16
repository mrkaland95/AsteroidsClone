package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;

public interface ControllableAsteroidModel {
    GameState getGameState();
    void accelerateShip(double deltaTime);
    void rotateShip(double deltaTime, float angle);
    void fireFromShip(double deltaTime);

    /** Updates the state of the model, i.e object's position etc.
     *
     * @param deltaTime The time since the last frame was rendered.
     */
    void update(double deltaTime);
}
