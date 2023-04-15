package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.GameState;

import java.util.List;

public interface ViewableAsteroidsModel {
    int getScore();
    GameState getGameState();
    PlayerShip getPlayerShip();
    List<Asteroid> getAsteroidList();

}
