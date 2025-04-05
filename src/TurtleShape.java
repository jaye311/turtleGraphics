package turtleGraphicsJohnathanAye2025;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TurtleShape {
    private Turtle turtle;
    private Ellipse2D.Double tShape;

    //turtle is represented by a circle (Ellipse)
    public TurtleShape(Turtle turtle, int xc, int yc, int size) {
        this.turtle = turtle;
        tShape = new Ellipse2D.Double(xc - size/2, yc - size/2, size, size);
    }
    public TurtleShape(Turtle turtle) {
        this(turtle, turtle.getCurrentX(), turtle.getCurrentY(), turtle.getTurtleSize());
    }
    //draws black turtle outline and fills with current color on view if pen is on
    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        gc.setColor(Color.BLACK);
        gc.draw(tShape);
        //if pen is on fill with current color
        if(turtle.getCurrentPen())
            gc.setColor(turtle.getCurrentColor());
        //else have white in black outline representing pen off
        else
            gc.setColor(Color.WHITE);
        gc.fill(tShape);
        gc.setColor(oldColor);
    }
}
