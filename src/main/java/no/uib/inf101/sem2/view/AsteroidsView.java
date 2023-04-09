package no.uib.inf101.sem2.view;


import no.uib.inf101.sem2.model.Characters.BaseCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

/*
Used my implementation of the "View" in Tetris as a template to follow here.
 */
public class AsteroidsView extends JPanel {
    private final ViewableAsteroidsModel asteroidsModel;
    private final int windowWidth = 1000;
    private final int windowHeight = 700;
    private final Color backgroundColor = new Color(0, 0, 0);
    private final Color frameColor = new Color(1, 1, 1);


    public AsteroidsView(ViewableAsteroidsModel asteroidsModel) {
        // Set parameters for the window.
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(backgroundColor);

        this.asteroidsModel = asteroidsModel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle test = this.getBounds();
        drawGameBorder(g2);
        drawGame(g2);
        drawScore(g2);

//        System.out.println(test.height);
//        System.out.println(test.width);

//        gameLoop();
//        Shape shape = new Point();
    }


    private void drawGame(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawCharacter(g2d, asteroidsModel.getPlayerShip());
        for (BaseCharacter character : asteroidsModel.getAdversaryList()) {
            drawCharacter(g2d, character);
        }


    }

    private void drawScore(Graphics2D g2d) {
        String score = "" + asteroidsModel.getScore();
        Point2D point = new Point(this.getWidth() / 2, 20);
        Inf101Graphics.drawCenteredString(g2d, score, point);

    }


    private void drawGameBorder(Graphics2D g2d) {
        this.getHeight();
    }

    private void drawCharacter(Graphics2D g2d, BaseCharacter character) {
        float[][] points = character.getShape();
        int numPoints = points.length;

        float posX = character.getPosition().x();
        float posY = character.getPosition().y();

        int[] xPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[0] + posX)).toArray();
        int[] yPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[1] + posY)).toArray();
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }


}
