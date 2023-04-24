package no.uib.inf101.sem2.view;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.Bullet;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.GameState;

import java.util.List;

public interface ViewableAsteroidsModel {



    /** Gets the amount of lives the player has left.
     * @return
     */
    int getPlayerLives();
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

    /** Gets the width of the game map.
     * @return
     */
    int getMapWidth();

    /** Gets the height of the game map.
     * @return
     */
    int getMapHeight();

    /** Sets the dimensions of the map.
     * @param newWidth
     * @param newHeight
     */
    void setMapDimensions(int newWidth, int newHeight);

    /** Scales all the objects within the game in case of resizing of the window. As explain the view class
     *  The intent is to keep the size of objects consistent with the size of the window, but it's not a great solution
     *  Sadly I ran out of time to implement/find a better solution.
     * @param widthScale
     * @param heightScale
     */
    void scaleAllGameObjects(float widthScale, float heightScale);
}
