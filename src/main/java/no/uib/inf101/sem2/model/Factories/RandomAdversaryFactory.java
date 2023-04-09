package no.uib.inf101.sem2.model.Factories;
import no.uib.inf101.sem2.model.Characters.BaseCharacter;

import java.util.Random;

public class RandomAdversaryFactory implements CharacterFactory {
    Random random;

    @Override
    public BaseCharacter getCharacter() {
        throw new RuntimeException("Not implemented yet");
    }
}
