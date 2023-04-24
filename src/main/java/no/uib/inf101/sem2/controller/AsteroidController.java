package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.AsteroidsModel;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.view.AsteroidsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class responsible for taking the user's input and change the state of the game.
 * Meant to loosely follow the same format as Tetris in the first compulsory assignment.
 */
public class AsteroidController implements KeyListener {
    private AsteroidsModel asteroidsModel;
    private AsteroidsView asteroidsView;
    Timer timer;

    // These are used to allow multiple keys to be pressed at the same time.
    // So for example the player is able to shoot and rotate at the same time.
    boolean leftArrowPressed = false;
    boolean rightArrowPressed = false;
    boolean upArrowPressed = false;
    boolean spaceBarPressed = false;

    // Variables used to control how fast bullets can be fired.
    private final long bulletCooldownMillis = 250;
    private long lastBulletFiredTime = 0;

    public AsteroidController(AsteroidsModel asteroidModel, AsteroidsView asteroidsView) {
        this.asteroidsModel = asteroidModel;
        this.asteroidsView = asteroidsView;
        this.asteroidsView.addKeyListener(this);

        this.timer = new Timer(Settings.getIntervalMillis(), this::performTick);
        this.timer.start();
    }

    /** Method responsible for updating the state of the game, and performing inputs given by the player.
     */
    private void performTick(ActionEvent event) {
        float shipDegreesRotated = Settings.DEGREES_ROTATED_PER_SECOND;
        float updateInterval = Settings.getUpdateIntervalFloat();

        // Fire bullets from the ship
        if (spaceBarPressed) {
            // Ensures that bullets can't be fired too fast.
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastBulletFiredTime >= bulletCooldownMillis) {
                asteroidsModel.fireFromShip(updateInterval);
                lastBulletFiredTime = currentTime;
            }
        }
        // Accelerates the player's ship.
        if (upArrowPressed) {
            asteroidsModel.accelerateShip(updateInterval);
        }
        // Rotate the ship, only allow rotation if, and only if, one key is pressed.
        if (!(leftArrowPressed && rightArrowPressed)) {
            if (leftArrowPressed) {
                asteroidsModel.rotateShip(updateInterval, -shipDegreesRotated);
            } else if (rightArrowPressed) {
                asteroidsModel.rotateShip(updateInterval, shipDegreesRotated);
            }
        }
        // Update the state of the game, and repaint the graphics.
        asteroidsModel.update(updateInterval);
        asteroidsView.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (asteroidsModel.getGameState() == GameState.GAME_OVER) return;


        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.upArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.spaceBarPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (asteroidsModel.getGameState() == GameState.GAME_OVER) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.upArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.spaceBarPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
