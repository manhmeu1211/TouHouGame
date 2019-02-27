import java.util.Vector;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void subtracts(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void scale(double rate) {
        this.x *= rate;
        this.y *= rate;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void setLength(double length) {
        double currentLength = this.getLength();
        if (currentLength != 0) {
            this.x = this.x * length / currentLength;
            this.y = this.y * length / currentLength;
        }
    }

    public double getAngle() {
        return Math.atan(y / x);
    }

    public void setAngel(double angel) {
        double length = this.getLength();
        if (length != 0) {
            this.x = length * Math.cos(angel);
            this.y = length * Math.sin(angel);
        }
    }
}
