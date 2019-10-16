package game;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(){
        this(0, 0);
    }

    public Vector2D(Vector2D other){
        this(other.x, other.y);
    }

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D other){
        this.add(other.x, other.y);
    }

    public void add(double x, double y){
        this.x += x;
        this.y += y;
    }

    public void substract(Vector2D other){
        this.substract(other.x, other.y);
    }

    public void substract(double x, double y){
        this.x -= x;
        this.y -= y;
    }

    public void scale(double rate){
        this.x *= rate;
        this.y *= rate;
    }

    public void set(Vector2D other){
        this.set(other.x,other.y);
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D clone(){
        Vector2D newvt = new Vector2D(this.x, this.y);
        return newvt;
    }

    public double getLength(){
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    public void setLength(double length){
        double currentLength = getLength();
        if(currentLength != 0){
            this.x = this.x * length/currentLength;
            this.y = this.y * length/currentLength;
        }
    }

    public double getAngle(){
        return Math.atan(this.y/this.x);
    }

    public void setAngle(double angle){
        double length = getLength();
        if(length != 0){
            this.x = length * Math.cos(angle);
            this.y = length * Math.sin(angle);
        }
    }

}
