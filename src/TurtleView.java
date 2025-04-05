package turtleGraphicsJohnathanAye2025;

import turtleGraphicsJohnathanAye2025.tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Iterator;

public class TurtleView extends JPanel implements Subscriber {
    private Turtle turtle;

    //creates a new view starting with a blank white canvas and black border, draws lines from turtle's TPoints
    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        turtle.subscribe(this);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        setBorder(blackLine);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Turtle.WORLD_SIZE, Turtle.WORLD_SIZE));
        repaint();
    }
    //draws lines from turtle's TPoints
    public void drawLines(Graphics gc){
        Color oldColor = gc.getColor();
        Iterator<TPoint> it = turtle.iterator();
        TPoint from = it.next();
        //draw line from -> to
        while(it.hasNext()) {
            TPoint to = it.next();
            gc.setColor(from.getColor());
            if(from.getPen())
                gc.drawLine(from.getX(), from.getY(), to.getX(), to.getY());
            from = to;
        }
        gc.setColor(oldColor);
    }


    public void update() {
        repaint();
    }
    //changes turtle to subscribe to and repaints
    public void setTurtle(Turtle newTurtle) {
        turtle.unSubscribe(this);
        turtle = newTurtle;
        turtle.subscribe(this);
        repaint();
    }
    //drawsLines and turtle at current (last TPoint) position
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        drawLines(gc);
        TurtleShape tShape = new TurtleShape(turtle);
        tShape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}