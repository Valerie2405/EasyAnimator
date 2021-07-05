package cs3500.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class that represents a shape. Has fields for form, width, height, color, position,
 * name, and the motions associated with the shape. Form represents the type of the shape, like
 * rectangle or ellipse.
 */
public class Shape2D implements Shape {

  private String shapeForm;
  private int shapeWidth;
  private int shapeHeight;
  Color shapeColor;
  Position2D shapePos;
  String name;
  List<MotionAnimation> listOfMotions = new ArrayList<>();

  /**
   * Constructor to represent an instance of a shape.
   *
   * @param name        the shapes name
   * @param shapeWidth  the width of the shape
   * @param shapeHeight the height of the shape
   * @param shapeColor  the color of the shape
   * @param shapePos    the position of the shape
   * @param shapeForm   the form of the shape
   */
  public Shape2D(String name, int shapeWidth, int shapeHeight, Color shapeColor,
      Position2D shapePos, String shapeForm) {
    this.name = name;
    this.shapeWidth = shapeWidth;
    this.shapeHeight = shapeHeight;
    this.shapeColor = shapeColor;
    this.shapePos = shapePos;
    this.shapeForm = shapeForm;
    this.listOfMotions = listOfMotions;

  }

  /**
   * Constructor to create an instance of a shape with just its name and form.
   *
   * @param name      the name of the shape
   * @param shapeForm the form of the shape
   */
  public Shape2D(String name, String shapeForm) {
    this.name = name;
    this.shapeForm = shapeForm;
  }

  /**
   * Empty constructor.
   */
  public Shape2D() {
    //default
  }

  /**
   * State of a shape at a given tick, and returns the state of the shape at a given moment
   * throughout the animation.
   *
   * @param tick given teick
   * @return a state of a shape at a given tick
   */
  public StateofShape getStateofShapeTick(int tick) {
    StateofShape state = null;
    //iterate through list of the motions at a specific tick from the last motion to the beginnning
    // since the last motion will have the greatest end tick
    for (int i = this.listOfMotions.size() - 1; i >= 0; i--) {
      MotionAnimation motion = listOfMotions.get(i);
      double multA = ((double) (motion.getEndTick() - tick) / (double) (motion.getEndTick()
          - motion.getStartTick()));
      double multB = ((double) (tick - motion.getStartTick()) / (double) (motion.getEndTick()
          - motion.getStartTick()));
      //checks if the provided tick is greater then the end Tick of the motion, thus return a shape
      // at ending state
      if (tick >= motion.getEndTick()) {
        state = new StateofShape(this.shapeForm,
            (int) motion.getEndPosition().getX(),
            (int) motion.getEndPosition().getY(),
            motion.getEndWidth(),
            motion.getEndHeight(),
            motion.getEndColor());
        break;
        // checks if the provided tick is equal to the starting tick of the motion,
        // thus return a shape at starting state
      } else if (tick == motion.getStartTick()) {
        state = new StateofShape(this.shapeForm,
            (int) motion.getStartPosition().getX(),
            (int) motion.getStartPosition().getY(),
            motion.getStartWidth(),
            motion.getStartHeight(),
            motion.getStartColor());
        break;
        //checks if the tick provided is in between the end and start tick
        // of the motion, thus returns
        //the state of the shape in between the start and end motion
      } else if (tick >= motion.getEndTick() &&
          tick == motion.getStartTick()) {
        state = new StateofShape(this.shapeForm,
            (int) (motion.getStartPosition().getX() * multA
                + motion.getEndPosition().getX() * multB),
            (int) (motion.getStartPosition().getY() * multA
                + motion.getEndPosition().getY() * multB),
            (int) (motion.getStartWidth() * multA
                + motion.getEndWidth() * multB),
            (int) (motion.getStartHeight() * multA
                + motion.getEndHeight() * multB),
            new Color((int) (motion.getStartColor().getRed() * multA
                + motion.getEndColor().getRed() * multB),
                (int) ((motion.getStartColor().getGreen()) * multA + (
                    motion.getEndColor().getGreen()
                        * multB)),
                (int) (motion.getStartColor().getBlue() * multA
                    + motion.getEndColor().getBlue() * multB)));
      }
    }
    return state;
  }

  /**
   * Adds a motion to a shape. Checks to see whether the motion conflicts with any other motions.
   *
   * @param newMotion the motion to be added
   * @throws IllegalArgumentException if the motion conflicts with any of the other motions that a
   *                                  shape has (if it overlaps, starts or ends at the same time as
   *                                  another motion, etc)
   */
  public void addMotionsToShape(MotionAnimation newMotion) {
    for (MotionAnimation motion : listOfMotions) {
      if (newMotion.getStartTick() >= motion.getStartTick()
          && newMotion.getStartTick() < motion.getEndTick()) {
        throw new IllegalArgumentException("Motion overlap");
      }
      //if the motion ends in the middle of another motion
      else if (newMotion.getEndTick() > motion.getStartTick()
          && newMotion.getEndTick() < motion.getEndTick()) {
        throw new IllegalArgumentException("Motion overlap.");
      }
      //if either tick is less than 0
      else if (newMotion.getStartTick() < 0 || newMotion.getEndTick() < 0) {
        throw new IllegalArgumentException("Invalid start or end ticks.");
      }
      //if the end tick comes before the start tick
      else if (newMotion.getEndTick() <= newMotion.getStartTick()) {
        throw new IllegalArgumentException("End tick comes before start tick.");
      }
    }
    if (newMotion.getStartTick() == 0) {
      this.shapePos = newMotion.getStartPosition();
    }

    listOfMotions.add(newMotion);
  }

  /**
   * Getter method to return the color of a shape.
   *
   * @return color of shape
   */
  public Color getShapeColor() {
    return this.shapeColor;
  }

  /**
   * Getter method to return the height of a shape.
   *
   * @return height of shape
   */
  public int getShapeHeight() {
    return this.shapeHeight;
  }

  /**
   * Getter method to return the width if a shape.
   *
   * @return width of shape
   */
  public int getShapeWidth() {
    return this.shapeWidth;
  }

  /**
   * Getter method to return the position of a shape.
   *
   * @return
   */
  public Position2D getShapePos() {
    return this.shapePos;
  }

  /**
   * Getter method to return the list of all of a shapes motions.
   *
   * @return a list of a shapes motions
   */
  public List<MotionAnimation> getMotionsofShape() {
    return this.listOfMotions;
  }

  /**
   * Overrides the equals method. Determines equality of two shape objects.
   *
   * @param o the shape being compared against
   * @return whether the two shapes are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shape2D shape2D = (Shape2D) o;
    return shapeWidth == shape2D.shapeWidth &&
        shapeHeight == shape2D.shapeHeight &&
        Objects.equals(shapeColor, shape2D.shapeColor) &&
        Objects.equals(shapePos, shape2D.shapePos) &&
        Objects.equals(name, shape2D.name) &&
        Objects.equals(listOfMotions, shape2D.listOfMotions);
  }

  /**
   * Override hashcode.
   *
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(shapeWidth, shapeHeight, shapeColor, shapePos, name, listOfMotions);
  }

  /**
   * Getter method to return the name of a shape.
   *
   * @return the shape's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Determines the type of the shape.
   *
   * @return the type of the shape
   */
  public String getForm() {
    return this.shapeForm;
  }

  /**
   * Returns a string visualization of a shape. Displays values for all the fields of a shape except
   * for its motions.
   *
   * @return a string visualization of a shape
   */
  public String description() {
    return shapePos.getX() + " " + shapePos.getY() + " " + shapeWidth + " " + shapeHeight + " "
        + shapeColor.getRed() + " " + shapeColor.getGreen() + " " + shapeColor.getBlue();
  }
}
