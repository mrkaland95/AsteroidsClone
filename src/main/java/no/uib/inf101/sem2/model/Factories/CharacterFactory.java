package no.uib.inf101.sem2.model.Factories;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.UFO;
import no.uib.inf101.sem2.model.Utils.Vector2;

import java.util.List;

public interface CharacterFactory {

    /** Returns an asteroid.
     * @return An Asteroid object.
     */
    Asteroid getLargeAsteroid();

    /** Creates a pair of smaller asteroids, intended to be created when a large asteroid gets destroyed.
     *
     * @param startPosition A start position, intended to be at the point where a large asteroid was destroyed.
     * @return a small asteroid.
     */
    List<Asteroid> getSmallAsteroidPair(Vector2 startPosition);

    UFO getUFO();
}
