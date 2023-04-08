package no.uib.inf101.sem2.model.Characters;

import no.uib.inf101.sem2.model.Utils.Vector2;

public interface IBaseCharacter {
    /**
     * Gets the position of a character.
     * @return
     */
    Vector2 getPosition();
    void setPosition(Vector2 position);
    Vector2 getVelocity();
    void setVelocity(Vector2 velocity);
    void update(double deltaTime);
}
