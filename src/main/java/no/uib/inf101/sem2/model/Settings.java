package no.uib.inf101.sem2.model;

public class Settings {
    private static final int framesPerSecond = 40;

    static public int getFramesPerSecond() {
        return Settings.framesPerSecond;
    }
    static public int getIntervalMillis() {
        return (int) ((1f / Settings.framesPerSecond) * 1000);
    }
}
