package no.uib.inf101.sem2;

import no.uib.inf101.sem2.controller.AsteroidController;
import no.uib.inf101.sem2.model.AsteroidsModel;
import no.uib.inf101.sem2.model.Factories.RandomCharacterFactory;
import no.uib.inf101.sem2.view.AsteroidsView;

import javax.swing.JFrame;

public class Main {
    public static final String WINDOW_TITLE = "Asteroids";
    public static void main(String[] args) {
        AsteroidsModel asteroidsModel = new AsteroidsModel(new RandomCharacterFactory());
        AsteroidsView asteroidsView = new AsteroidsView(asteroidsModel);
        new AsteroidController(asteroidsModel, asteroidsView);
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(asteroidsView);

        frame.pack();
        frame.setVisible(true);
    }
}
