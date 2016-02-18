package sp6.engine;

import java.awt.Point;

/**
 *
 */
public class SimpleVector2D {
    // Scalar value
    private double speed = 1;
    private double lastXScale; // -1, 1
    private double lastYScale; // -1, 1
    private double xScale; // -1, 1
    private double yScale; // -1, 1
    private final double gravity = 1.0;

    private Point point = new Point();

    // Vector quantity
    private double xVelocity;
    private double yVelocity;
    private double acceleration = 0.05;
    private final double deAcceleration = 0.1;
    private double currentAcceleration;

    public SimpleVector2D(int x, int y) {
        point.x = x;
        point.y = y;

        setScale(0, 0);
    }


    public void setScale(double xScale, double yScale) {
        if (xScale == 0 && yScale == 0) {
            currentAcceleration = 0;
        } else {
            lastXScale = xScale;
            lastYScale = yScale;
            currentAcceleration += acceleration;
        }

        xVelocity = ((speed + currentAcceleration) * xScale);
        yVelocity = ((speed + currentAcceleration) * yScale);

        System.out.println("xvel: " + xVelocity + ", yvel: " + yVelocity);

        this.xScale = xScale;
        this.yScale = yScale;

    }

    public void setPosition(int x, int y) {
        point.setLocation(x, y);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public int getXScale() {
        return (int) xScale;
    }

    public int getYScale() {
        return (int) yScale;
    }

    public Point getNewVectorPoint() {
        point.x += xVelocity;
        point.y += yVelocity;

        return point;
    }

    public Point getlastVectorPoint() {
        return point;
    }

    public Point getDecreasedVectorPoint() {
        point.x += (-1 * lastXScale);
        point.y += (-1 * lastYScale);

        return point;
    }
}
