package no.uib.inf101.sem2.model.Utils;

public class ShapeUtils {
//
//    /** Method responsible for rotating the direction and shape a character is pointing towards.
//     * 0/360 degrees is upwards.
//     *
//     * @param angle The angle that the shape of the character should be rotated by.
//     */
//    public void rotateShapeBy(float angle) {
//
//        // Limits to a number between 0-360 degrees.
//        this.currentAngle = (this.currentAngle + angle) % 360;
//        if (this.currentAngle < 0) {
//            this.currentAngle += 360;
//        }
//        // Calculates the center point of the shape and rotates the shape relative to that point.
//        Vector2 centerPoint = calculateCenter(this.currentShape);
//        this.currentShape = rotateShape(this.currentShape, angle, centerPoint);
//    }
//
//
//
//    /** Utility method used to rotate the points in a shape by a given angle.
//     * ChatGPT was used for figuring this one out
//     * @param angle
//     */
//    private static float[][] rotateShape(float[][] points, float angle, Vector2 centerPosition) {
//        float[][] rotatedPoints = new float[points.length][2];
//        double radians = Math.toRadians(angle);
//        float cosAngle = (float) Math.cos(radians);
//        float sinAngle = (float) Math.sin(radians);
//
//        for (int i = 0; i < points.length; i++) {
//            float dx = points[i][0] - centerPosition.x();
//            float dy = points[i][1] - centerPosition.y();
//            rotatedPoints[i][0] = centerPosition.x() + (cosAngle * dx - sinAngle * dy);
//            rotatedPoints[i][1] = centerPosition.y() + (sinAngle * dx + cosAngle * dy);
//        }
//
//        return rotatedPoints;
//    }
}
