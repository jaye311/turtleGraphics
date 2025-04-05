package turtleGraphicsJohnathanAye2025;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class TPoint implements Serializable {
    final private int X;
    final private int Y;
    private Color color;
    private boolean pen;

    //default constructor for turtle starting position in middle of view, turtles are green
    public TPoint(){
        color = Color.GREEN;
        X = Turtle.WORLD_SIZE/2;
        Y = Turtle.WORLD_SIZE/2;
        pen = true;
    }
    public TPoint(Color c, boolean p, int x, int y){
        X=x;
        Y=y;
        color = c;
        pen = p;
    }
    public Color getColor(){ return color; }
    public boolean getPen(){ return pen; }
    //switches pen status
    public void changePen(){ pen = !pen;}
    //updates color of TPoint
    public void changeColor(){ color = JColorChooser.showDialog(null, "Choose a color", getColor()); }
    public int getX(){return X; }
    public int getY(){ return Y; }
}
