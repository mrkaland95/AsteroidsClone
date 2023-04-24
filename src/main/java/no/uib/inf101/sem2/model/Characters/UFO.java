package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

/*
Did not implement this, ran out of time due to other assignments.
 */

public class UFO extends BaseCharacter {
        public UFO(Vector2 startPosition, Vector2 startVelocity) {
        this.setCurrentShape(getBaseShape());
        this.setPosition(startPosition);
        this.setVelocity(startVelocity);
    }

    public UFO(Vector2 startPosition) {
        this.setCurrentShape(getBaseShape());
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
    }


    @Override
    public float[][] getBaseShape() {
            // TODO implement this
        return new float[0][];
    }
}
