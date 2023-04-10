package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.GameState;

public interface ControllableAsteroidModel {
    GameState getGameState();
    void accelerateShip(double deltaTime);
    void rotateShip(double deltaTime, float angle);
    void fireFromShip(double deltaTime);
    void update(double deltaTime);
}
