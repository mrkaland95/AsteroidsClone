package no.uib.inf101.sem2.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;


/**
 * ChatGPT was used to generate the code for this class.
 */
public class SoundManager {
    private Clip fireSound;
    private Clip accelerateSound;

    public SoundManager() {
        loadSounds();
    }

    private void loadSounds() {
        fireSound = loadSound("/sounds/fire/fire.wav");
        accelerateSound = loadSound("/sounds/thrust/thrust.wav");
    }

    public Clip loadSound(String filename) {
    Clip clip = null;
    try {
        URL url = getClass().getResource(filename);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
        return clip;
    }
    public void playFireSound() {
        if (fireSound != null) {
            fireSound.setFramePosition(0); // Rewind to the beginning of the sound
            fireSound.start();
        }
    }

    public void playAccelerateSound() {
        if (accelerateSound != null) {
            accelerateSound.setFramePosition(0); // Rewind to the beginning of the sound
            accelerateSound.start();
        }
    }
}
