package no.uib.inf101.sem2.controller;

public interface ControllableAsteroidModel {
    void accelerateShip(double deltaTime);
    void rotateShip(double deltaTime, float angle);
    void fireFromShip(double deltaTime);
    void update(double deltaTime);
}