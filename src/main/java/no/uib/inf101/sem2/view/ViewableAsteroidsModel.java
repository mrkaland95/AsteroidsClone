package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.GameState;

public interface ViewableAsteroidsModel {
    GameState getGameState();
    void setDeltaTime(double deltaTime);
    int getTimerDelay();
}
