package no.uib.inf101.sem2.model.Factories;

import no.uib.inf101.sem2.model.Characters.Asteroid;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;

public interface CharacterFactory {
    BaseCharacter getCharacter();
    Asteroid getAsteroid();
}
