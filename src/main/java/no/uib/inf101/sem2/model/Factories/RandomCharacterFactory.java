package no.uib.inf101.sem2.model.Factories;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.UFO;
import no.uib.inf101.sem2.model.Settings;
import no.uib.inf101.sem2.model.Utils.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCharacterFactory implements CharacterFactory {
    Random random = new Random();
    float velocityUpperLimit = Settings.ASTEROID_MAX_VELOCITY;


    @Override
    public Asteroid getLargeAsteroid() {
        return getRandomLargeAsteroid();
    }

    /**
     * Returns an asteroid with random parameters, i.e a random start position, random velocity, random rotation speed.
     * @return
     */
    private Asteroid getRandomLargeAsteroid() {
        float mapWidth = Settings.MAP_WIDTH;
        float mapHeight = Settings.MAP_HEIGHT;


        float startRotation = random.nextFloat(-50, 50);
        Vector2 startPosition = new Vector2(random.nextFloat(0, mapWidth), random.nextFloat(0, mapHeight));
        Vector2 startVelocity = new Vector2(random.nextFloat(-velocityUpperLimit, velocityUpperLimit), random.nextFloat(-velocityUpperLimit, velocityUpperLimit));
        return new Asteroid(startPosition, startVelocity, startRotation, 2);
    }

    private Asteroid getSmallAsteroid() {
        return null;
    }



    @Override
    public List<Asteroid> getSmallAsteroidPair(Vector2 startPosition) {
        List<Asteroid> asteroids = new ArrayList<>();
        float startRotation = random.nextFloat(-100, 100);
        // Makes the asteroids velocity go towards the left.

        // Creates velocities for the two asteroids, which will make them go in opposite directions, one to the right, one to the left.
        float randomNegativeXVelocity = random.nextFloat(-velocityUpperLimit, 0);
        float randomPositiveXVelocity = random.nextFloat(0, velocityUpperLimit);

        float firstRandomYVelocity = random.nextFloat(-velocityUpperLimit, velocityUpperLimit);
        float secondRandomYVelocity = random.nextFloat(-velocityUpperLimit, velocityUpperLimit);

        float firstStartingAngle = random.nextFloat(360);
        float secondStartingAngle = random.nextFloat(360);

        Vector2 firstAsteroidVelocity = new Vector2(randomNegativeXVelocity, firstRandomYVelocity);
        Vector2 secondAsteroidVelocity = new Vector2(randomPositiveXVelocity, secondRandomYVelocity);
        
        asteroids.add(new Asteroid(startPosition, firstAsteroidVelocity, startRotation, firstStartingAngle, 1));
        asteroids.add(new Asteroid(startPosition, secondAsteroidVelocity, startRotation, secondStartingAngle, 1));

        return asteroids;
    }

    @Override
    public UFO getUFO() {
        return null;
    }

}
