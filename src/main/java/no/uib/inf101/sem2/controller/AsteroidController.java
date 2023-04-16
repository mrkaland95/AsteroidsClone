package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.AsteroidsModel;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.view.AsteroidsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AsteroidController implements KeyListener {
    private AsteroidsModel asteroidsModel;
    private AsteroidsView asteroidsView;
    private long lastUpdateTime;
    Timer timer;

    // These are used to allow multiple keys to be pressed at the same time.
    // So for example the player is able to shoot and rotate at the same time.
    boolean leftArrowPressed = false;
    boolean rightArrowPressed = false;
    boolean upArrowPressed = false;
    boolean spaceBarPressed = false;


    public AsteroidController(AsteroidsModel asteroidModel, AsteroidsView asteroidsView) {
        this.asteroidsModel = asteroidModel;
        this.asteroidsView = asteroidsView;
        this.asteroidsView.addKeyListener(this);

        this.timer = new Timer(Settings.getIntervalMillis(), this::performTick);
        this.timer.start();

        this.lastUpdateTime = System.nanoTime();
    }

    private void performTick(ActionEvent event) {
        if (!asteroidsModel.getGameState().equals(GameState.ACTIVE_GAME)) return;

        float rotationAngle = 5f;
        float updateInterval = Settings.getUpdateIntervalFloat();


        // Accelerates the player's ship.
        if (upArrowPressed) {
            asteroidsModel.accelerateShip(updateInterval);
        }

        // Rotate the ship, only allow rotation if one, and only one key is pressed.
        if (!(leftArrowPressed && rightArrowPressed)) {
            if (leftArrowPressed) {
                asteroidsModel.rotateShip(updateInterval, -rotationAngle);
            } else if (rightArrowPressed) {
                asteroidsModel.rotateShip(updateInterval, rotationAngle);
            }
        }

        // Update the state of the game, and repaint the graphics.
        asteroidsModel.update(updateInterval);
        asteroidsView.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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


    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastUpdateTime) / 1e9; // Convert nanoseconds to seconds
        this.lastUpdateTime = currentTime;
        return deltaTime;
    }

}
