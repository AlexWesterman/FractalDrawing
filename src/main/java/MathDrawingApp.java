package main.java;

import main.java.curves.*;
import main.java.curves.dragon.Dragon;
import main.java.curves.dragon.Heighway;
import main.java.curves.dragon.Twindragon;
import main.java.curves.tree.BinaryTree;
import main.java.curves.tree.FractalPlant;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;

public class MathDrawingApp {

    /**
     * Sets a turtle's position
     * @param turtle the turtle to move
     * @param x the x coordinate (0 is horizontal center)
     * @param y the y coordinate (0 is vertical center)
     */
    public void setTurtleToPos(Turtle turtle, int x, int y) {
        //Move the turtle away from the shape, without drawing a line
        turtle.up();
        turtle.setPosition(x, y);
        turtle.down();
    }

    /**
     * Showcases all fractal drawings implemented in this program
     */
    public void showcaseDrawing() {
        Turtle.setCanvasSize(1200, 600);

        Turtle turtle = new Turtle();
        turtle.speed(1);
        turtle.left(90);
        this.setTurtleToPos(turtle, -600, 0);

        new Gosper().draw(turtle,4, 10, Gosper.DEFAULT_ANGLE);
        this.setTurtleToPos(turtle, -400, 0);

        new Hilbert().draw(turtle,5, 10, Hilbert.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, -100-5000, 0);
        new Sierpinski().draw(turtle,8, 10, Sierpinski.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, 600, 0);
        new Heighway().draw(turtle,10, 10, Heighway.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, 1200, 0);
        new Twindragon().draw(turtle,10, 10, Heighway.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, 2000, 0);
        turtle.right(105);
        new Koch().draw(turtle,5, 10, Koch.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, 5000, 0);
        turtle.left(45);
        new BinaryTree().draw(turtle, 8, 10, BinaryTree.DEFAULT_ANGLE);

        this.setTurtleToPos(turtle, 8000, 0);
        turtle.left(45);
        new FractalPlant().draw(turtle, 6, 10, FractalPlant.DEFAULT_ANGLE);

        // Move turtle away from view
        this.setTurtleToPos(turtle, -10000, 0);
    }

    private HashMap<Character, String> getOptions() {
        String[] options = new String[] {"Heighway", "Twindragon", "Binary Tree", "Fractal Plant", "Gosper Curve",
                "Hilbert Curve", "Koch Curve", "Sierpinski Triangle"};

        HashMap<Character, String> optionMap = new HashMap<Character, String>();

        // Put each option in with their corresponding character
        for (int i = 0; i < options.length; i++)
            optionMap.put((char) ('A' + i), options[i]);

        return optionMap;
    }

    public static void main(String[] args) {
        MathDrawingApp app = new MathDrawingApp();

        System.out.println("Welcome to Fractal Drawings!\n");

        // Display options
        System.out.println("Options (enter the corresponding character to select one):");
        HashMap<Character, String> options = app.getOptions();
        for (int i = 0; i < options.size(); i++) {
            char key = (char) ('A' + i);
            // Start at A and continue through the alphabet
            System.out.println(key + ": " + options.get(key));
        }

        Scanner scanner = new Scanner(System.in);
        char chosen = 0;

        while (chosen == 0) {
            System.out.println("What fractal would you like?");

            String input = scanner.nextLine();

            if (input.length() > 1) {
                System.out.println("Too many characters!");
            } else {
                char option = input.charAt(0);
                // Valid if the option is in the list (ie. between 'A' and the last option)
                if ('A' <= option && option <= ('A' + options.size()-1)) {
                    chosen = option;
                    break;
                } else {
                    System.out.println("Invalid option!");
                }
            }
        }

        String chosenFractal = options.get(chosen);
        System.out.println("You have chosen: " + chosenFractal);

        System.out.println("What level of detail do you want?");

        int detail = 0;
        while (detail == 0) {
            String input = scanner.nextLine();

            try {
                detail = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Must be an integer!");
            }
        }

        // Setup the turtle and the canvas
        Turtle.setCanvasSize(1200, 600);
        Turtle.bgcolor(Color.BLACK);
        Turtle turtle = new Turtle();
        turtle.penColor(Color.WHITE);
        turtle.left(90);
        turtle.speed(1);

        final int step = 10;

        System.out.println("Drawing...");

        // Create an instance and draw it
        switch (chosenFractal) {
            case "Heighway":
                new Heighway().draw(turtle, detail, step, Heighway.DEFAULT_ANGLE);
                break;
            case "Twindragon":
                new Twindragon().draw(turtle, detail, step, Twindragon.DEFAULT_ANGLE);
                break;
            case "Binary Tree":
                new BinaryTree().draw(turtle, detail, step, BinaryTree.DEFAULT_ANGLE);
                break;
            case "Fractal Plant":
                new FractalPlant().draw(turtle, detail, step, FractalPlant.DEFAULT_ANGLE);
                break;
            case "Gosper Curve":
                new Gosper().draw(turtle, detail, step, Gosper.DEFAULT_ANGLE);
                break;
            case "Hilbert Curve":
                new Hilbert().draw(turtle, detail, step, Hilbert.DEFAULT_ANGLE);
                break;
            case "Koch Curve":
                new Koch().draw(turtle, detail, step, Koch.DEFAULT_ANGLE);
                break;
            case "Sierpinski Triangle":
                new Sierpinski().draw(turtle, detail, step, Sierpinski.DEFAULT_ANGLE);
                break;
        }
    }
}
