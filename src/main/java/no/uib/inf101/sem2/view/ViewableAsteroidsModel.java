package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Utils.DimensionCustom;

import java.util.List;

public interface ViewableAsteroidsModel {
    /**
     * Gets the score the player has accumulated so far.
     * @return
     */
    int getScore();

    /**
     * Gets the state of the game, i.e "Active", "Game over etc."
     * @return
     */
    GameState getGameState();

    /**
     * Gets the player's ship
     * @return
     */
    PlayerShip getPlayerShip();

    /**
     * Gets the list of asteroids that are still active
     * @return
     */
    List<Asteroid> getAsteroidList();

    /**
     * Gets a list of bullets that are still active.
     * @return
     */
    List<Bullet> getBulletList();
}
