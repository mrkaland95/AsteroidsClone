package no.uib.inf101.sem2.model;

/**
 * Class responsible for storing settings for various parameters in the game.
 * Things like framerate, asteroid count, etc.
 */
public class Settings {
    static private final int FRAMES_PER_SECOND = 100;
    static public final int MAP_WIDTH = 1000;
    static public final int MAP_HEIGHT = 500;

    static public final int MAXIMUM_ASTEROID_COUNT = 33;
    static public final int MINIMUM_ASTEROID_COUNT = 8;

    static public final int ASTEROID_KILLED_SCORE = 75;
    static public final int UFO_KILLED_SCORE = 150;
    static public final int INITIAL_PLAYER_LIVES = 3;
    static public final long BULLET_COOLDOWN_MILLIS = 350;

    static public final float BULLET_VELOCITY_PER_SECOND = 400f;
    static public final float SHIP_MAX_VELOCITY = 100f;
    static public final float ASTEROID_MAX_VELOCITY = 85f;
    static public final float DEGREES_ROTATED_PER_SECOND = 240f;
    static public final float SHIP_ACCELERATION_PER_SECOND = 100f;


    static public final float MIN_SPAWN_DISTANCE_FROM_SHIP = 100f;


    static public int getIntervalMillis() {
        return (int) ((1f / Settings.FRAMES_PER_SECOND) * 1000);
    }
    static public float getUpdateIntervalFloat() {
        return (1f / Settings.FRAMES_PER_SECOND);
    }



}
