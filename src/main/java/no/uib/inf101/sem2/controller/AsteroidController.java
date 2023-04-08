package no.uib.inf101.sem2.controller;

import no.uib.inf101.sem2.model.AsteroidsModel;
import no.uib.inf101.sem2.view.AsteroidsView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AsteroidController implements KeyListener {
    private AsteroidsModel asteroidsModel;
    private AsteroidsView asteroidsView;
    public AsteroidController(AsteroidsModel asteroidModel, AsteroidsView asteroidsView) {
        this.asteroidsModel = asteroidModel;
        this.asteroidsView = asteroidsView;
        this.asteroidsView.addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Shoot
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
