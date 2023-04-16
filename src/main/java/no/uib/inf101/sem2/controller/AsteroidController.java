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

        asteroidsModel.update(Settings.getUpdateIntervalFloat());
        asteroidsView.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        float rotationAngle = 10f;

        if (asteroidsModel.getGameState() == GameState.GAME_OVER) return;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            asteroidsModel.accelerateShip(Settings.getUpdateIntervalFloat());
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Rotates the ship to the left.
            asteroidsModel.rotateShip(Settings.getUpdateIntervalFloat(), -rotationAngle);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Rotates the ship to the right.
            asteroidsModel.rotateShip(Settings.getUpdateIntervalFloat(), rotationAngle);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Shoot
        }

        asteroidsView.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            asteroidsModel.accelerateShip(Settings.getUpdateIntervalFloat());
        }
    }


    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastUpdateTime) / 1e9; // Convert nanoseconds to seconds
        this.lastUpdateTime = currentTime;
        return deltaTime;
    }

}
