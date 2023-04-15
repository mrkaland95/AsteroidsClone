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
     * Returns a random asteroid.
     * @return
     */
    private Asteroid getRandomAsteroid() {
        Vector2 startPosition = new Vector2(random.nextFloat(300, 600), random.nextFloat(200, 650));
        Vector2 startVelocity = new Vector2(random.nextFloat(-20, 20), random.nextFloat(-20, 20));
        float startRotation = random.nextFloat(-30, 30);
        return new Asteroid(startPosition, startVelocity, startRotation);
    }
}
