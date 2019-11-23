package main.java.curves;

import main.java.Turtle;

import java.awt.*;
import java.util.EnumMap;

public class Curve {
    private final String PROD_RULE_A, PROD_RULE_B, REPLACEMENT_A, REPLACEMENT_B;
    private final String INIT_STRING;

    public Curve(final String INIT_STRING, String PROD_RULE_A, String PROD_RULE_B, String REPLACEMENT_A, String REPLACEMENT_B) {
        this.PROD_RULE_A = PROD_RULE_A;
        this.PROD_RULE_B = PROD_RULE_B;
        this.REPLACEMENT_A = REPLACEMENT_A;
        this.REPLACEMENT_B = REPLACEMENT_B;
        this.INIT_STRING = INIT_STRING;
    }

    /**
     * Move a turtle based on a single action command
     * @param turtle the turtle to use
     * @param c the character command
     * @param step the length of each forward movement
     * @param angle the angle to turn the turtle for each turn (Left/Right)
     * @throws IllegalArgumentException if c is not in the set (F,L,R)
     */
    public void followCommand(Turtle turtle, char c, final int step, final int angle) throws IllegalArgumentException {
        switch (c) {
            case 'F':
                turtle.forward(step);
                break;
            case 'L':
                turtle.left(angle);
                break;
            case 'R':
                turtle.right(angle);
                break;
            default:
                throw new IllegalArgumentException("Unknown character: " + c);
        }
    }

    public Color getColour(float percentage) {
        if      (percentage < 0.25) return Color.yellow;
        else if (percentage < 0.5 ) return Color.green;
        else if (percentage < 0.75) return Color.yellow;
        else                        return Color.green;
    }

    /**
     * Moves a turtle based on the string Str
     * @param turtle the turtle to use
     * @param str string to move turtle around
     * @param step the length of each forward movement
     * @param angle the angle to turn the turtle for each turn (Left/Right)
     */
    public void follow(Turtle turtle, String str, final int step, final int angle) {
        int len = str.length();
        // For each character in the string, find their corresponding actions
        for (int i = 0; i < len; i++){
            turtle.penColor(this.getColour((float) i / len));

            char c = str.charAt(i);
            this.followCommand(turtle, c, step, angle);
        }
    }

    /**
     * Creates a curve specific string
     * @param str the axiom to be rewritten
     * @param depth the depth of the recursion
     * @return the rewritten string S
     * @throws IllegalArgumentException if the string contains any characters outside of (L,F,R,E)
     */
    private String getString(String str, int depth) throws IllegalArgumentException {
        // Base Case
        if (depth == 0)
            return str;

        // General Case
        StringBuilder newStr = new StringBuilder();
        int upperDepth = depth-1;

        // For each character in the string, find their corresponding actions
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            // Each string returned calls their own recursive call
            switch (c) {
                case 'A':
                    newStr.append(this.getString(this.PROD_RULE_A, upperDepth));
                    break;
                case 'B':
                    newStr.append(this.getString(this.PROD_RULE_B, upperDepth));
                    break;
                case 'L': case 'R': case 'F': case '[': case ']':
                    // These characters are just their self
                    newStr.append(c);
                    break;
                default:
                    throw new IllegalArgumentException("String must only contain {A,B,L,R,F,[,]} characters | c = " + c);
            }
        }

        return newStr.toString().replaceAll("A", this.REPLACEMENT_A).replaceAll("B", this.REPLACEMENT_B);
    }

    /**
     * Draws a curve via a turtle
     * @param turtle the turtle to use
     * @param depth the recursion depth
     * @param step the distance the turtle moves per forward instruction
     * @param angle the angle to rotate the turtle left or right per instruction
     */
    public void draw(Turtle turtle, int depth, final int step, final int angle) {
        String str = this.getString(this.INIT_STRING, depth);
        this.follow(turtle, str, step, angle);
    }
}
