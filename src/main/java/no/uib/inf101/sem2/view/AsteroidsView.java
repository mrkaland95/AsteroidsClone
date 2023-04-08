package no.uib.inf101.sem2.view;


import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

/*
Used my implementation of the "View" in Tetris as a template to follow here.
 */
public class AsteroidsView extends JPanel {
    private final ViewableAsteroidsModel asteroidsModel;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private final Color backgroundColor = new Color(0, 0, 0);
    private final Color frameColor = new Color(1, 1, 1);
    private final Instant lastUpdateTime;


    public AsteroidsView(ViewableAsteroidsModel asteroidsModel) {
        this.asteroidsModel = asteroidsModel;

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.lastUpdateTime = Instant.now();
        this.setBackground(backgroundColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle test = this.getBounds();
        System.out.println(test.height);
        System.out.println(test.width);

//        gameLoop();
//        Shape shape = new Point();
    }


    private void drawGame(Graphics2D g2d) {

    }


//    private void gameLoop() {
//        Instant lastUpdateTime = Instant.now();
//
//        Instant currentTime = Instant.now();
//        long deltaTimeMillis = Duration.between(lastUpdateTime, currentTime).toMillis();
//        double deltaTime = deltaTimeMillis / 1000d;
//
//            lastUpdateTime = currentTime;
//        }
//
//    }


}
