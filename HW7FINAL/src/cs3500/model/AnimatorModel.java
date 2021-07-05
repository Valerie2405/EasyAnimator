package cs3500.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a model to represent an animated scene. Has fields for the dimensions of
 * the board (width and height), the list of shapes in the animation ( each shape has its own list
 * of motions). With this class, one can retrieve the information necessary for a simple animation.
 * This class contains a static builder class that constructs an AnimatorModel before the creation
 * of an animatorModel. The builder creates an AnimatorModel that has all the information needed by
 * the view to make a simple animation.
 */
public class AnimatorModel implements cs3500.model.Model {

  private int x;
  private int y;
  private int widthBoard;
  private int heightBoard;
  List<Shape2D> listOfShapes = new ArrayList<>();


  /**
   * Class builds an AnimatorModel by building it with the help of the methods inside the builder
   * class such as build, setBounds, declareShapes and addMotion. It creates an AnimatorModel with
   * all the components needed by the view.
   *
   * @return
   */
  public static final class Builder implements AnimationBuilder<cs3500.model.Model> {

    private int x;
    private int y;
    private int widthBoard;
    private int heightBoard;
    List<Shape2D> listOfShapes = new ArrayList<>();
    private final cs3500.model.Model model;

    /**
     * Constructor that builds an empty AnimatorModel that will be used to build a functional
     * AnimatorMode that will be used by the view.
     */
    public Builder() {
      model = new AnimatorModel();
    }

    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public cs3500.model.Model build() {
      //take all the information that you built up and create model with that info
      return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<cs3500.model.Model> setBounds(int x, int y, int width, int height) {
      if (width < 1 || height < 1) {
        throw new IllegalArgumentException("Out bounds for the screen");
      }
      this.x = x;
      this.y = y;
      this.widthBoard = width;
      this.heightBoard = height;
      model.setBounds(x, y, width, height);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<cs3500.model.Model> declareShape(String name, String type) {
      Shape2D shape = new Shape2D(name, type);
      model.addShapes(shape);
      this.listOfShapes.add(shape);
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      if (t1 > t2) {
        throw new IllegalArgumentException("This time is invalid");
      }
      for (Shape2D shape : model.getShapes()) {
        cs3500.model.MotionAnimation motion = new MotionAnimator(t1,
            new cs3500.model.Position2D(x1, y1), w1, h1,
            new Color(r1, g1, b1), t2, new Position2D(x2, y2), w2, h2, new Color(r2, g2, b2));
        if (shape.getName().equals(name)) {
          model.addMotion(motion, shape);
        }
      }
      return this;
    }
  }

  /**
   * Constructor to represent an instance of an AnimatorModel.
   *
   * @param widthBoard   the width of the canvas
   * @param heightBoard  the height of the canvas
   * @param listOfShapes the list of shapes in the animation
   */
  public AnimatorModel(int widthBoard, int heightBoard, List<Shape2D> listOfShapes) {
    this.widthBoard = widthBoard;
    this.heightBoard = heightBoard;
    if (listOfShapes == null) {
      throw new IllegalArgumentException("list of shapes is null");
    }
    this.listOfShapes = listOfShapes;
    this.x = x;
    this.y = y;
  }

  /**
   * Default no argument constructor. Instantiates the list of shapes, the width and height of the
   * board.
   */
  public AnimatorModel() {
    this.x = x;
    this.y = y;
    this.widthBoard = widthBoard;
    this.heightBoard = heightBoard;
    this.listOfShapes = listOfShapes;
  }

  /**
   * Begins a new animation. Initializes the values of the tick, canvas dimensions, and shapes.
   *
   * @param heightBoard the height of the canvas
   * @param widthBoard  the width of the canvas
   * @param shapes      the list of shapes in the animation
   * @throws IllegalArgumentException if any parameters are null or invalid
   */
  public void startAnimation(int heightBoard, int widthBoard, Shape2D... shapes)
      throws IllegalArgumentException {
    //if the width or height of the board is invalid
    if (heightBoard <= 0 || widthBoard <= 0) {
      throw new IllegalArgumentException("Invalid board dimensions.");
    }
    //if shapes is null
    else if (shapes == null) {
      throw new IllegalArgumentException("Null shapes.");
    }
    this.heightBoard = heightBoard;
    this.widthBoard = widthBoard;
    this.listOfShapes = new ArrayList<>(Arrays.asList(shapes));
  }

  /**
   * Replaces the values of the the fields of a model with the given inputs.
   *
   * @param x      the new x value
   * @param y      the new y value
   * @param width  the new width
   * @param height the new height
   */
  public void setBounds(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.widthBoard = width;
    this.heightBoard = height;
  }

  /**
   * Adds a shape to the animation.
   *
   * @param shape the shape to be added
   * @throws IllegalArgumentException if the shape already exist in the list of shapes
   */
  public void addShapes(Shape2D shape) {
    if (listOfShapes.contains(shape)) {
      throw new IllegalArgumentException("Shape is already apart of this animation");
    }
    this.listOfShapes.add(shape);
  }

  /**
   * Removes a shape from the animation.
   *
   * @param shape the shape to be removed
   * @throws IllegalArgumentException if the shape does not exist in the model
   */
  public void removeShapes(Shape2D shape) {
    if (!this.listOfShapes.contains(shape)) {
      throw new IllegalArgumentException("Shape does not exist.");
    } else {
      this.listOfShapes.remove(shape);
    }
  }

  /**
   * Returns the list of all the shapes in the model.
   *
   * @return a list of shapes
   */
  public List<Shape2D> getShapes() {
    return this.listOfShapes;
  }

  /**
   * Returns the last tick from the list of motions of a shape.
   *
   * @return a tick that is the last tick of the list of motions for a specific shape
   */
  public int getLastTick() {
    int lastTick = 0;
    for (Shape2D shape : listOfShapes) {
      //gets the end tick for the last motion of that shape
      int tick = shape.listOfMotions.get(shape.listOfMotions.size() - 1).getEndTick();
      if (lastTick < tick) {
        lastTick = tick;
      }
    }
    return lastTick;
  }

  /**
   * Gets the leftMost x.
   *
   * @return leftMost x
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the leftmost y.
   *
   * @return leftmost y
   */
  public int getY() {
    return y;
  }

  /**
   * Returns the height of the board.
   *
   * @return value of height of the Board
   */
  public int getHeightBoard() {
    return this.heightBoard;
  }

  /**
   * Returns the width of the board.
   *
   * @return value of the width of the Board
   */
  public int getWidthBoard() {
    return this.widthBoard;
  }

  /**
   * Add a motion to a certain shape's list of motions.
   *
   * @param motion the motion to be added
   * @param shape  the shape that the motion is being added to
   */
  public void addMotion(cs3500.model.MotionAnimation motion, Shape2D shape) {
    if (shape.getMotionsofShape().contains(motion)) {
      throw new IllegalArgumentException("duplicate motions");
    }
    shape.addMotionsToShape(motion);
  }

  /**
   * Removes a motion from a certain shape's list of motions.
   *
   * @param motion the motion to be removed
   * @param shape  the index of the motion to be removed
   */
  public void removeMotion(MotionAnimation motion, Shape2D shape) {
    if (!shape.getMotionsofShape().contains(motion)) {
      throw new IllegalArgumentException(
          "motion does not exist in the list of motions of this shape");
    }
    shape.listOfMotions.remove(motion);
  }


  /**
   * Returns a state of a shape at specific Tick.
   *
   * @return returns the state of a shape
   */
  @Override
  public List<StateofShape> stateofShapesTick(int tick) {
    List<StateofShape> states = new ArrayList<>();
    for (Shape2D shape : getShapes()) {
      states.add(shape.getStateofShapeTick(tick));
    }
    return states;
  }


  /**
   * Returns a string visualization of an animation. For each motion, it displays the start and end
   * tick of that motion, along with the values for the shape's fields before and after the motion
   * occurs.
   *
   * @return a string representing an animation
   * @throws IllegalStateException if the animation has not been started
   */
  public String description() {
    String finalString = "";
    for (Shape2D shape : listOfShapes) {
      finalString =
          finalString + "shape " + shape.getName() + " " + shape.getForm() + "\n";
      for (int i = 0; i < shape.getMotionsofShape().size(); i++) {
        finalString = finalString + "motion " + shape.getName() + " " +
            shape.getMotionsofShape().get(i).description() + "\n";
      }
    }
    return finalString;
  }

}