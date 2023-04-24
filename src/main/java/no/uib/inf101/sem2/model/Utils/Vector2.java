package no.uib.inf101.sem2.model.Utils;

/** Record class intended to represent at 2d vector. Used to store position and velocity primarily.
 * @param x
 * @param y
 */
public record Vector2(float x, float y) {

    /** Calculates the distance between two points.
     *
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static double distance(Vector2 vectorA, Vector2 vectorB) {
        float x = vectorB.x() - vectorA.x();
        float y = vectorB.y() - vectorA.y();
        return Math.sqrt(x * x + y * y);
    }

    /** Translates this vector by the specified distances and returns a new one.
     * @param dx
     * @param dy
     * @return
     */
    public Vector2 translate(float dx, float dy) {
        float x = this.x();
        float y = this.y();
        x += dx;
        y += dy;

        return new Vector2(x, y);
    }

    public static Vector2 translateOverTime(Vector2 start, Vector2 shift, double deltaTime) {
        // Updates the position of a character every frame by the given shift, multiplied by the deltatime.
        double newX = start.x() + shift.x() * deltaTime;
        double newY = start.y() + shift.y() * deltaTime;
        return new Vector2((float) newX,(float) newY);
    }

    /**
     * Calculates the dot product of this matrix and another matrix.
     * @param other
     * @return
     */
    public float dotProduct(Vector2 other) {
        return this.x() * other.x() + this.y() * other.y();
    }

}
