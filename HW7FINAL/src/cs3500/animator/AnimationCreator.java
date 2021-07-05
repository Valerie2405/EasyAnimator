package cs3500.animator;

import cs3500.model.AnimatorModel;
import cs3500.model.Model;
import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import cs3500.model.Shape2D;
import cs3500.model.ShapeForm;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that creates an animation algorithmically and writes it to a text file. This file can
 * then be called by Excellence to visually display the animation.
 */
public class AnimationCreator {

  static FileWriter ap;

  /**
   * Default no argument constructor.
   */
  public AnimationCreator() {
    try {
      ap = new FileWriter("animation.txt", true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Main method to generate the file.
   */
  public static void main(String[] args) {
    Model model = new AnimatorModel(500, 500, new ArrayList<>());
    addShapes(model);
    addMotionstoShape(model);
    AnimationCreator creator = new AnimationCreator();
    try {
      appendString(model, (FileWriter) getAp());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Append the output of the string method to the appendable.
   */
  private static void appendString(Model model, FileWriter ap) throws IOException {
    try {
      String s = buildFile(model);
      ap.append(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
    ap.close();
  }

  /**
   * Adds shapes to the model.
   */
  private static void addShapes(Model model) {
    for (int i = 0; i < 10; i++) {
      Shape2D shape = new Shape2D(String.valueOf(i), ShapeForm.ELLIPSE.toString());
      model.addShapes(shape);
    }
  }

  /**
   * Method to add motions to the shapes.
   */
  private static void addMotionstoShape(Model model) {
    List<Shape2D> modelShapes = model.getShapes();
    List<Position2D> endPosList = new ArrayList<>();
    Random random = new Random();
    Position2D firstPos = new Position2D(random.nextInt(model.getWidthBoard()),
        random.nextInt(model.getHeightBoard()));
    endPosList.add(firstPos);
    for (Shape2D shape : modelShapes) {
      int tick = 0;
      for (int i = 0; i < 10; i++) {
        Position2D newPos = new Position2D(random.nextInt(model.getWidthBoard()),
            random.nextInt(model.getHeightBoard()));
        endPosList.add(newPos);
        Color color = new Color(random.nextInt(255), random.nextInt(255),
            random.nextInt(255));
        MotionAnimation motion = new MotionAnimator(tick, endPosList.get(i), 50,
            50, color, tick + 30, endPosList.get(i + 1),
            50, 50, color);
        shape.addMotionsToShape(motion);
        tick = tick + 30;
      }
    }
  }

  /**
   * Method to convert the motions of the shape into a string that can be appended to the text
   * file.
   */
  private static String buildFile(Model model) {
    StringBuilder builder = new StringBuilder();
    builder.append("canvas 0 0 " + model.getWidthBoard() + " " + model.getHeightBoard() + "\n");

    //creates the string to represent a shape in the form "shape name form"
    for (Shape2D shape : model.getShapes()) {
      StringBuilder s = new StringBuilder();
      s.append("shape " + shape.getName() + " " + shape.getForm());
      s.append("\n");
      builder.append(s);
    }

    //creates the string to represent a motion of a shape
    for (Shape2D shape : model.getShapes()) {
      for (MotionAnimation motion : shape.getMotionsofShape()) {
        StringBuilder m = new StringBuilder();
        m.append("motion ");
        m.append(shape.getName() + " ");
        m.append(motion.getStartTick() + " ");
        m.append((int) motion.getStartPosition().getX() + " ");
        m.append((int) motion.getStartPosition().getY() + " ");
        m.append(motion.getStartWidth() + " ");
        m.append(motion.getStartHeight() + " ");
        m.append(motion.getStartColor().getRed() + " ");
        m.append(motion.getStartColor().getGreen() + " ");
        m.append(motion.getStartColor().getBlue() + " ");
        m.append(motion.getEndTick() + " ");
        m.append((int) motion.getEndPosition().getX() + " ");
        m.append((int) motion.getEndPosition().getY() + " ");
        m.append(motion.getEndWidth() + " ");
        m.append(motion.getEndHeight() + " ");
        m.append(motion.getEndColor().getRed() + " ");
        m.append(motion.getEndColor().getGreen() + " ");
        m.append(motion.getEndColor().getBlue() + " ");
        m.append("\n");
        builder.append(m);
      }
    }
    return builder.toString();
  }

  /**
   * Getter method to return the value of the appendable.
   */
  private static Appendable getAp() {
    return ap;
  }
}



