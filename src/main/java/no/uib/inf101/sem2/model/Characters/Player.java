package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;




public class Player extends BaseCharacter {

    public Player(Vector2 startPosition) {
        this.setPosition(startPosition);
        this.setVelocity(new Vector2(0, 0));
    }

    public float[][] getShape() {
        float[][] shape = {
            {0f,  -10f},
            {10f,  10f},
            {0f,   5f},
            {-10f, 10f}
        };

        return shape;
    }
}
