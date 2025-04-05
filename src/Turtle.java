package turtleGraphicsJohnathanAye2025;

import turtleGraphicsJohnathanAye2025.tools.Publisher;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Turtle extends Publisher implements Serializable{
    public static Integer WORLD_SIZE = 250;
    private ArrayList<TPoint> path = new ArrayList<>();
    private final int turtleSize = 10;

    //constructed with default TPoint for turtle starting position in middle of view
    public Turtle(){
        //uses linkedList implementation of Publisher list
        super("linked");
        path.add(new TPoint());
    }
    //adds a TPoint with parameters specified
    private void addPoint(Color c, boolean p, int x, int y){
        path.add(new TPoint(c, p, x, y));
    }
    //adds TPoint to ArrayList path in the direction and amount of steps away, notifies view to redraw
    public void addPoint(int amount, String direction){
        //exclude redundant TPoints
        if(amount == 0)
            return;
        //turtle adds a new TPoint that is up by amount from the lastPoint
        if(direction.equals("North")) {
            addPoint(getCurrentColor(), getCurrentPen(), getCurrentX(), getCurrentY() - amount);
        }
        //turtle adds a new TPoint that is right by amount from the lastPoint
        else if(direction.equals("East")) {
            addPoint(getCurrentColor(), getCurrentPen(), getCurrentX() + amount, getCurrentY());
        }
        //turtle adds a new TPoint that is left by amount from the lastPoint
        else if(direction.equals("West")) {
            addPoint(getCurrentColor(), getCurrentPen(), getCurrentX() - amount, getCurrentY());
        }
        //turtle adds a new TPoint that is down by amount from the lastPoint
        else if(direction.equals("South")) {
            addPoint(getCurrentColor(), getCurrentPen(), getCurrentX(), getCurrentY() + amount);
        }
        notifySubscribers();
    }
    public Iterator<TPoint> iterator(){ return path.iterator(); }
    private TPoint getCurrent(){ return path.getLast(); }

    public boolean getCurrentPen(){ return getCurrent().getPen(); }
    public int getCurrentX(){return getCurrent().getX();}
    public int getCurrentY(){ return getCurrent().getY(); }
    public Color getCurrentColor(){ return getCurrent().getColor(); }
    public int getTurtleSize(){ return turtleSize; }

    public void changePen(){ getCurrent().changePen(); }
    //clears path except for current (last) TPoint
    public void clear(){
        TPoint temp = getCurrent();
        path.clear();
        path.add(temp);
    }
    public void changeColor(){ getCurrent().changeColor(); }
    public void turtleChange(){ notifySubscribers(); }
}
