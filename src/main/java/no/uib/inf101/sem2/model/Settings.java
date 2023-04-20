package no.uib.inf101.sem2.model;

/**
 * Class responsible for storing settings for various parameters in the game.
 * Things like framerate, asteroid count, etc.
 */
public class Settings {
    static private final int FRAMES_PER_SECOND = 100;
    static public final int mapWidth = 1000;
    static public final int mapHeight = 500;

    static public final int MAXIMUM_ASTEROID_COUNT = 25;
    static public final int MINIMUM_ASTEROID_COUNT = 5;

    static public final int ASTEROID_KILLED_SCORE = 50;
    static public final int UFO_KILLED_SCORE = 100;
    static public final float BULLET_VELOCITY_PER_SECOND = 400f;
    static public final float PLAYERSHIP_SPEED_LIMIT = 100f;
    static public final float DEGREES_ROTATED_PER_TICK = 3f;
    static public final float SHIP_ACCELERATION_PER_SECOND = 100f;



    static public int getFramesPerSecond() {
    return Settings.FRAMES_PER_SECOND;
    }
    static public int getIntervalMillis() {
        return (int) ((1f / Settings.FRAMES_PER_SECOND) * 1000);
    }
    static public float getUpdateIntervalFloat() {
        return (1f / Settings.FRAMES_PER_SECOND);
    }



}
