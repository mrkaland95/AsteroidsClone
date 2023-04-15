package no.uib.inf101.sem2.view;


import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.Utils.Vector2;

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
    private final int mapWidth = 2000;
    private final int mapHeight = 2000;


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
        drawGameBorder(g2);
        drawGame(g2);
        drawScore(g2);
    }


    private void drawGame(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        PlayerShip playerShip = asteroidsModel.getPlayerShip();

        drawCharacter(g2d, playerShip);
        if (playerShip.isAccelerating()) {
            drawFlame(g2d, playerShip);
        }

        for (BaseCharacter character : asteroidsModel.getAsteroidList()) {
            drawCharacter(g2d, character);
        }
    }

    private void drawScore(Graphics2D g2d) {
        String score = "Score: " + asteroidsModel.getScore();
        Point2D point = new Point(this.getWidth() / 2, 40);
        Inf101Graphics.drawCenteredString(g2d, score, point);
    }



    private void drawGameBorder(Graphics2D g2d) {
        this.getHeight();
    }

    private void drawCharacter(Graphics2D g2d, BaseCharacter character) {
        float[][] points = character.getCurrentShape();
        int numPoints = points.length;

        float posX = character.getPosition().x();
        float posY = character.getPosition().y();

        int[] xPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[0] + posX)).toArray();
        int[] yPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[1] + posY)).toArray();
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }


    private void drawFlame(Graphics2D g2d, PlayerShip playerShip) {
        if (playerShip.isAccelerating()) {
            float[][] fireShape = playerShip.getFireShape();
            float[][] translatedFireShape = translateShape(fireShape, playerShip.getPosition());

            int[] fireXPoints = Arrays.stream(translatedFireShape).mapToInt(point -> Math.round(point[0])).toArray();
            int[] fireYPoints = Arrays.stream(translatedFireShape).mapToInt(point -> Math.round(point[1])).toArray();
            g2d.drawPolygon(fireXPoints, fireYPoints, fireShape.length);
        }
    }

    private float[][] translateShape(float[][] shape, Vector2 translation) {
        float[][] translatedShape = new float[shape.length][2];

        for (int i = 0; i < shape.length; i++) {
            translatedShape[i][0] = shape[i][0] + translation.x();
            translatedShape[i][1] = shape[i][1] + translation.y();
        }

        return translatedShape;
    }



    /**
     * I know there is a method that already does this in "Inf101graphics", but you can't change the font or size
     * in that one.
     * @param g2d
     * @param string
     * @param point
     */
    private void drawCenteredString(Graphics2D g2d, String string, Point2D point) {
        Font font = new Font("Arial", Font.BOLD, 40 * 2);
        FontMetrics fontMetrics = g2d.getFontMetrics(font);

        int textWidth = fontMetrics.stringWidth(string);
        int textHeight = fontMetrics.getHeight();

        int x = (int) ((point.getX() - textWidth) / 2);
        int y = (int) ((point.getY() - textHeight) / 2);

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString(string, x, y);
    }
}
