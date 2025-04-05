package turtleGraphicsJohnathanAye2025;

import turtleGraphicsJohnathanAye2025.tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TurtleController extends JPanel implements ActionListener{
    private Turtle turtle = new Turtle();
    private TurtleView view = new TurtleView(turtle);
    private JPanel controls = new JPanel();
    private JFrame frame = new JFrame();

    private JButton north = new JButton("North");
    private JButton east = new JButton("East");
    private JButton west = new JButton("West");
    private JButton south = new JButton("South");
    private JButton clear = new JButton("Clear");
    private JButton pen = new JButton("Pen");
    private JButton color = new JButton("Color");

    //sets up layout of menu, controls, view, closing operation, and packs to preferred sizes of components
    public TurtleController(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        JMenuBar menu = createMenuBar();
        frame.setJMenuBar(menu);
        frame.setTitle("Turtle Graphics");
        addButtons(controls);
        setListener();
        add(view);
        frame.pack();
        frame.setVisible(true);
    }

    //add buttons to controls panel
    private void addButtons(JPanel buttons) {
        setLayout(new GridLayout(1, 2));
        buttons.setLayout(new GridLayout(4,2));
        buttons.setPreferredSize(new Dimension(Turtle.WORLD_SIZE, Turtle.WORLD_SIZE));
        JPanel p = new JPanel();
        p.add(north);
        buttons.add(p);
        p=new JPanel();
        p.add(east);
        buttons.add(p);
        p=new JPanel();
        p.add(west);
        buttons.add(p);
        p=new JPanel();
        p.add(south);
        buttons.add(p);
        p=new JPanel();
        p.add(clear);
        buttons.add(p);
        p=new JPanel();
        p.add(pen);
        buttons.add(p);
        p=new JPanel();
        p.add(color);
        buttons.add(p);
        add(buttons);
    }
    //creates menu bar
    private JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "East", "West", "South", "Clear", "Pen", "Color"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
    //makes this controller listen to JButton presses
    private void setListener(){
        north.addActionListener(this);
        east.addActionListener(this);
        south.addActionListener(this);
        west.addActionListener(this);
        clear.addActionListener(this);
        pen.addActionListener(this);
        color.addActionListener(this);
    }
    //run the application
    public static void main(String[] args){
        TurtleController tv = new TurtleController();
    }
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                //North: Moves turtle steps inputted in up (negative y) direction, movement is capped at 0 boundary
                case "North":{
                    int input = promptForSteps(frame);
                    if(turtle.getCurrentY() - input < 0)
                        turtle.addPoint(turtle.getCurrentY(), "North");
                    else
                        turtle.addPoint(input, "North");
                    break;
                }
                //East: Moves turtle steps inputted in right (positive x) direction, movement is capped at Turtle.WORLD_SIZE boundary
                case "East":{
                    int input = promptForSteps(frame);
                    if(turtle.getCurrentX() + input > Turtle.WORLD_SIZE)
                        turtle.addPoint(Turtle.WORLD_SIZE - turtle.getCurrentX(), "East");
                    else
                        turtle.addPoint(input, "East");
                    break;
                }
                //West: Moves turtle steps inputted in left (negative x)direction, movement is capped at 0 boundary
                case "West":{
                    int input = promptForSteps(frame);
                    if(turtle.getCurrentX() - input < 0)
                        turtle.addPoint(turtle.getCurrentX(), "West");
                    else
                        turtle.addPoint(input, "West");
                    break;
                }
                //South: Moves turtle steps inputted in down (positive y) direction, movement is capped at Turtle.WORLD_SIZE boundary
                case "South":{
                    int input = promptForSteps(frame);
                    if(turtle.getCurrentY() + input > Turtle.WORLD_SIZE)
                        turtle.addPoint(Turtle.WORLD_SIZE - turtle.getCurrentY(), "South");
                    else
                        turtle.addPoint(input, "South");
                    break;
                }
                //Clear: Clears canvas, turtle left at last TPoint
                case "Clear":{
                    turtle.clear();
                    break;
                }
                //Pen: Changes pen status to on from off or off from on
                case "Pen":{
                    turtle.changePen();
                    break;
                }
                //Color: Brings up colorChooser menu to change color
                case "Color":{
                    turtle.changeColor();
                    break;
                }
                //saves current Turtle and its ArrayList of TPoints to a file name entered by user
                case "Save": {
                    String fName = Utilities.getFileName( null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.turtle);
                    os.close();
                    break;
                }
                //opens a saved file containing a Turtle and its ArrayList of TPoints, current changes are lost if not saved
                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName( null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        turtle = (Turtle) is.readObject();
                        view.setTurtle(turtle);
                        is.close();
                    }
                    break;
                }
                //creates a new Turtle and sets the TurtleView to follow it, current changes are lost if not saved
                case "New": {
                    turtle = new Turtle();
                    view.setTurtle(turtle);
                    break;
                }
                //close application, current changes are lost if not saved
                case "Quit": {
                    System.exit(0);
                    break;
                }
                //contains name and date
                case "About": {
                    Utilities.inform("Cyberdellic Designs Turtle Graphics, Johnathan Aye, 2025. All rights reserved.");
                    break;
                }
                //gives description to edit menu commands available
                case "Help": {
                    String[] cmmds = new String[]{
                            "North: Moves turtle steps inputted in up (negative y) direction, movement is capped at canvas boundary",
                            "East: Moves turtle steps inputted in right (positive x) direction, movement is capped at canvas boundary",
                            "West: Moves turtle steps inputted in left (negative x) direction, movement is capped at canvas boundary",
                            "South: Moves turtle steps inputted in down (positive y) direction, movement is capped at canvas boundary",
                            "Clear: Clears canvas, turtle left at current (last) point with same color and pen status",
                            "Pen: Changes pen status to on from off or to off from on",
                            "Color: Brings up colorChooser menu to change color"
                    };
                    Utilities.inform(cmmds);
                    break;
                }
                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
        turtle.turtleChange();
    }
    // Prompt for a positive integer input (because direction is already specified by Button), returns an error message if input is invalid
    private static int promptForSteps(JFrame frame) {
        // Prompt for a positive integer input
        String input = JOptionPane.showInputDialog(frame, "How many steps? Enter a positive integer:");

        // Try to convert the input to an integer
        try {
            int number = Integer.parseInt(input);
            //only use positive steps input
            if(number > 0)
                return number;
            //be positive
            JOptionPane.showMessageDialog(frame, "Invalid input! Why so negative?", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            // Handle invalid input
            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //only reaches for invalid input - does nothing
        return 0;
    }
}
