package no.uib.inf101.sem2.model.Factories;
import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;
import no.uib.inf101.sem2.model.Utils.Vector2;

import java.util.Random;

public class RandomAdversaryFactory implements CharacterFactory {
    Random random = new Random();

    @Override
    public BaseCharacter getCharacter() {
        return getRandomAsteroid();
    }

    @Override
    public Asteroid getAsteroid() {
        return getRandomAsteroid();
    }

    /**
     * Returns an asteroid with random parameters, i.e a random start position, random velocity, random rotation speed.
     * @return
     */
    private Asteroid getRandomAsteroid() {
        float velocityUpperLimit = 30f;
        Vector2 startPosition = new Vector2(random.nextFloat(0, 600), random.nextFloat(0, 800));
        Vector2 startVelocity = new Vector2(random.nextFloat(-velocityUpperLimit, velocityUpperLimit), random.nextFloat(-velocityUpperLimit, velocityUpperLimit));
        float startRotation = random.nextFloat(-30, 30);
        return new Asteroid(startPosition, startVelocity, startRotation);
    }
}
