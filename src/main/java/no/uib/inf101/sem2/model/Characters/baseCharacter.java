package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

abstract class baseCharacter implements IBaseCharacter {
    private Vector2 position;

    public baseCharacter(Vector2 startPosition) {
        this.position = startPosition;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }
}
