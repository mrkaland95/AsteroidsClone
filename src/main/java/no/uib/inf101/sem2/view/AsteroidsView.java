package no.uib.inf101.sem2.view;


import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Characters.PlayerShip;
import no.uib.inf101.sem2.model.GameState;
import no.uib.inf101.sem2.model.Utils.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
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
    private static final int BORDER_MARGIN = 2;


    public AsteroidsView(ViewableAsteroidsModel asteroidsModel) {
        // Set parameters for the window.
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(backgroundColor);

        this.asteroidsModel = asteroidsModel;
        this.addComponentListener(new ComponentAdapter() {

            // This is a little wonky, but essentially resizes the game map when the window gets resized
            // It creates some problems, but I didn't have time to do it over again with a better solution.
            @Override
            public void componentResized(ComponentEvent e) {
                int oldWidth = asteroidsModel.getMapWidth();
                int oldHeight = asteroidsModel.getMapHeight();

                // Update the dimensions of the model based on the new size of the view
                int newWidth = getWidth();
                int newHeight = getHeight();
                asteroidsModel.setMapDimensions(newWidth, newHeight);

                float widthScale = (float) newWidth / oldWidth;
                float heightScale = (float) newHeight / oldHeight;

                float scaleFactor = Math.min(widthScale, heightScale);

                // Scale all game objects with the same factor to maintain their aspect ratio
                asteroidsModel.scaleAllGameObjects(scaleFactor, scaleFactor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGameBorder(g2);
        drawGame(g2);
        if (asteroidsModel.getGameState() == GameState.ACTIVE_GAME) {
            drawPlayerLives(g2);
            drawScore(g2);
        } else {
            drawGameOverScreen(g2);
        }
    }

    /**
     *  Method responsible for drawing all the characters related to the actual game itself.
     * @param g2d
     */
    private void drawGame(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        // Enables "Anti-Aliasing" for the shapes, making them look less jagged.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        PlayerShip playerShip = asteroidsModel.getPlayerShip();

        // Only draw the ship if the game is not over.
        if (!(asteroidsModel.getGameState() == GameState.GAME_OVER)) {
            drawCharacter(g2d, playerShip);
            if (playerShip.isAccelerating()) {
                drawFlame(g2d, playerShip);
            }
        }

        // Draw asteroids
        for (BaseCharacter asteroid : asteroidsModel.getAsteroidList()) {
            drawCharacter(g2d, asteroid);
        }
        // Draw bullets
        for (BaseCharacter bullet : asteroidsModel.getBulletList()) {
            drawCharacter(g2d, bullet);
        }
    }

    /** Draws the score of the game to the top of the screen, centered in the x direction.
     * @param g2d
     */
    private void drawScore(Graphics2D g2d) {
        String score = "Score: " + asteroidsModel.getScore();
        Point2D point = new Point(this.getWidth() / 2, 40);
        Inf101Graphics.drawCenteredString(g2d, score, point);
    }

    /**
     * Uses the player ship as an icon indicating how many lives the player has left.
     * @param g2d
     */
    private void drawPlayerLives(Graphics2D g2d) {
        PlayerShip player = asteroidsModel.getPlayerShip();
        float[][] baseShape = player.getBaseShape();
        int spacing = 45;
        int iconSize = 15;
        float scale = (float) iconSize / 10f; // Since the original ship shape has a height of 10

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Player Lives remaining:", 50, 20);

        AffineTransform originalTransform = g2d.getTransform(); // Save the original transform

        for (int i = 0; i < asteroidsModel.getPlayerLives(); i++) {
            g2d.translate(i * (iconSize + spacing) + spacing, spacing);
            g2d.scale(scale, scale);

            Path2D lifeShape = new Path2D.Float();
            lifeShape.moveTo(baseShape[0][0], baseShape[0][1]);

            for (int j = 1; j < baseShape.length; j++) {
                lifeShape.lineTo(baseShape[j][0], baseShape[j][1]);
            }

            lifeShape.closePath();
            g2d.setColor(Color.WHITE);
            g2d.fill(lifeShape);

            g2d.setTransform(originalTransform); // Restore the original transform
        }
    }

    /** Draws a thin white border around the map.
     * @param g2d
     */
    private void drawGameBorder(Graphics2D g2d) {
        // Draw the map border
        g2d.setColor(Color.WHITE);
        g2d.drawRect(BORDER_MARGIN, BORDER_MARGIN, getWidth() - 2 * BORDER_MARGIN, getHeight() - 2 * BORDER_MARGIN);
    }

    /** Draws a game object to the screen, according to it's shape and position.
     * @param g2d
     * @param character
     */
    private void drawCharacter(Graphics2D g2d, BaseCharacter character) {
        float[][] points = character.getCurrentShape();
        int numPoints = points.length;

        float posX = character.getPosition().x();
        float posY = character.getPosition().y();

        int[] xPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[0] + posX)).toArray();
        int[] yPoints = Arrays.stream(points).mapToInt(point -> Math.round(point[1] + posY)).toArray();
        g2d.drawPolygon(xPoints, yPoints, numPoints);
    }

    /** Draws a flame at the back of the ship.
     * @param g2d
     * @param playerShip
     */
    private void drawFlame(Graphics2D g2d, PlayerShip playerShip) {
        float[][] flameShape = playerShip.getCurrentFlameShape();

        int[] flameXPoints = Arrays.stream(flameShape).mapToInt(point -> Math.round(point[0])).toArray();
        int[] flameYPoints = Arrays.stream(flameShape).mapToInt(point -> Math.round(point[1])).toArray();
        g2d.drawPolygon(flameXPoints, flameYPoints, flameShape.length);
    }

    /** Draws a game over screen.
     * @param g2d
     */
    private void drawGameOverScreen(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        String gameOverText = "Game Over";
        float gameOverTextWidth = (float) g2d.getFontMetrics().getStringBounds(gameOverText, g2d).getWidth();
        g2d.drawString(gameOverText, getWidth() / 2f - gameOverTextWidth / 2f, getHeight() / 2f - 50);

        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        String scoreText = "Your score was: " + asteroidsModel.getScore();
        float scoreTextWidth = (float) g2d.getFontMetrics().getStringBounds(scoreText, g2d).getWidth();
        g2d.drawString(scoreText, getWidth() / 2f - scoreTextWidth / 2, getHeight() / 2f);

        String newGameText = "Press Enter to start a new game";
        float newGameTextWidth = (float) g2d.getFontMetrics().getStringBounds(newGameText, g2d).getWidth();
        g2d.drawString(newGameText, getWidth() / 2f - newGameTextWidth / 2, getHeight() / 2f + 50);
    }
}
