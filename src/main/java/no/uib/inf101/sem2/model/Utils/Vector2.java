package no.uib.inf101.sem2.model.Utils;

/** Record class intended to store a position, velocity in 2d space.
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
        return Math.sqrt(x*x + y*y);
    }
    public Vector2 translate(float dx, float dy) {
        float x = this.x();
        float y = this.y();
        x += dx;
        y += dy;

        return new Vector2(x, y);
    }
}
