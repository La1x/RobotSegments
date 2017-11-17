package laix.robotsegments.model;

import javafx.scene.paint.Color;

public class Piston extends Point {
    private double width;
    private double height;
    private Color color;

    public Piston(double x, double y, double width, double height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Piston(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = Color.DARKBLUE;
    }

    public void moveTo(double x) {
        this.setX( x );
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
