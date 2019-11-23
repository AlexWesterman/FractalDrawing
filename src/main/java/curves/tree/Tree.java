package main.java.curves.tree;

import main.java.Turtle;
import main.java.curves.Curve;

import java.util.ArrayList;

public class Tree extends Curve {
    // Each entry in the stack will store the turtle position and angle
    private ArrayList<Double[]> stack = new ArrayList<>();

    public Tree(String INIT_STRING, String PROD_RULE_A, String PROD_RULE_B, String REPLACEMENT_A, String REPLACEMENT_B) {
        super(INIT_STRING, PROD_RULE_A, PROD_RULE_B, REPLACEMENT_A, REPLACEMENT_B);
    }

    /**
     * Moves a turtle based on the string Str
     * @param turtle the turtle to use
     * @param str string to move turtle around
     * @param step the length of each forward movement
     * @param angle the angle to turn the turtle for each turn (Left/Right)
     * @throws IllegalArgumentException if c is not in the curve set or ([,])
     */
    public void follow(Turtle turtle, String str, final int step, final int angle) throws IllegalArgumentException {
        // For each character in the string, find their corresponding actions
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            try {
                // Try to find the character in Curve's set of actions
                super.followCommand(turtle, c, step, angle);
            } catch (IllegalArgumentException ignored) {
                turtle.penColor(super.getColour((float) i / str.length()));
                // c is not in Curve's actions, so try the tree specific actions
                switch (c) {
                    case '[':
                        // Push to stack
                        this.stack.add(new Double[] {turtle.getX(), turtle.getY(), turtle.getDirection()});
                        turtle.left(angle);
                        break;
                    case ']':
                        // Restore from stack
                        Double[] info = this.stack.remove(this.stack.size()-1);
                        turtle.up();
                        turtle.setPosition(info[0], info[1]);
                        turtle.setDirection(info[2]);
                        turtle.right(angle);
                        turtle.down();
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown character: " + c);
                }
            }
        }
    }
}
