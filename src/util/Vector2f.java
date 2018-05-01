package util;

public class Vector2f {
    public float x;
    public float y;
    public float w;

    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.w = 1.0f; // !?!
    }

    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
        this.w = v.w; // !?!
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
        this.w = 1.0f; // !?!
    }

    public Vector2f(float x, float y, float w) {
        this.x = x;
        this.y = y;
        this.w = w; // !?!
    }

    public void translate(float tx, float ty) {
        x += tx;
        y += ty;
    }

    public void translate ( Vector2f tv ) {

        x += tv.x;
        y += tv.y;

    }

    public void scale(float sx, float sy) {
        x *= sx;
        y *= sy;
    }

    public void rotate(float rad) {
        float tmp = (float) (x * Math.cos(rad) - y * Math.sin(rad));
        y = (float) (x * Math.sin(rad) + y * Math.cos(rad));
        x = tmp;
    }

    public void shear(float sx, float sy) {
        float tmp = x + sx * y;
        y = y + sy * x;
        x = tmp;
    }

    public Vector2f inv() {

        return new Vector2f(-x, -y);

    }

    public Vector2f add ( Vector2f v ) {

        return new Vector2f(x+v.x, y + v.y);

    }

    public Vector2f subtract ( Vector2f v ) {

        return new Vector2f( x - v.x, y - v.y );

    }

    public Vector2f mul ( float scalar ) {

        return new Vector2f( scalar * x, scalar * y );

    }

    // div is not a safe operations check for divide by zero whenever it is used.
    public Vector2f div ( float scalar ) {

        return new Vector2f( x / scalar, y / scalar );

    }

    public float len() {

        return (float) Math.sqrt( x * x + y * y );

    }

    // Math.sqrt is a costly operation so comparing the sqr value may be used to avoid it.
    public float lenSqr() {

        return x * x + y * y;

    }

    public Vector2f norm() {

        return div( len() );

    }

    // useful for strafing
    public Vector2f perp() {

        return new Vector2f( -y, x );

    }

    // dot product projects one vector onto another, which forms a right triangle.
    // The results of a dot product is a scalar value, not a vector.
    // The vector receiving the projection must be normalized, then scaled by the
    // results to result in the vector that would produce the right triangle
    public float dot( Vector2f v ) {

        return x * v.x + y * v.y;

    }

    public float angle() {

        return (float) Math.atan2 ( y, x );

    }

    public static Vector2f polar ( float angle, float radius ) {

        return new Vector2f( radius * (float)  Math.cos( angle ), radius * (float) Math.sin( angle ) );

    }

}