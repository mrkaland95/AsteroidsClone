package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Utils.DimensionCustom;

import java.util.List;

public interface ViewableAsteroidsModel {
    int getScore();
    GameState getGameState();
    PlayerShip getPlayerShip();
    List<Asteroid> getAsteroidList();
    List<Bullet> getBulletList();
}
