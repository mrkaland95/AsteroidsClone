package no.uib.inf101.sem2.model.Characters;

public class Bullet extends BaseCharacter {

    @Override
    protected float[][] getBaseShape() {
        float[][] shape = {
                {-2.5f, 2.5f},
                {2.5f, 2.5f},
                {2.5f, -2.5f},
                {-2.5f, -2.5f},
            };
        return shape;
    }
}
