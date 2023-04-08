package no.uib.inf101.sem2.model.Factories;
import no.uib.inf101.sem2.model.Characters.baseCharacter;

import java.util.Random;

public class RandomAdversaryFactory implements CharacterFactory {
    Random random;

    @Override
    public baseCharacter getCharacter() {
        throw new RuntimeException("Not implemented yet");
    }
}
