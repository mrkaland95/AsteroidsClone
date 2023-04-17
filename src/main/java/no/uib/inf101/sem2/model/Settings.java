package no.uib.inf101.sem2.model;

public class Settings {
    private static final int framesPerSecond = 100;
    public static final int mapWidth = 2000;
    public static final int mapHeight = 2000;
    static public int getFramesPerSecond() {
        return Settings.framesPerSecond;
    }
    static public int getIntervalMillis() {
        return (int) ((1f / Settings.framesPerSecond) * 1000);
    }
    static public float getUpdateIntervalFloat() {
        return (1f / Settings.framesPerSecond);
    }

}
