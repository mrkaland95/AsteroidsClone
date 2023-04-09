package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public class Asteroid extends baseCharacter {
    public Asteroid(Vector2 startPosition, Vector2 startVelocity) {
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
    }

    public Asteroid(Vector2 startPosition) {
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
    }
}
