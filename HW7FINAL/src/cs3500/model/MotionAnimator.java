package cs3500.model;

import java.awt.Color;
import java.util.Objects;

/**
 * A class that represents a singular motion on a shape. It has fields to represent all the shape
 * fields that are changed when a shape is animated.
 */
public class MotionAnimator extends Shape2D implements MotionAnimation {

  protected int startTick;
  protected int endTick;
  protected cs3500.model.Position2D startPosition;
  protected cs3500.model.Position2D endPosition;
  protected int startWidth;
  protected int endWidth;
  protected int startHeight;
  protected int endHeight;
  protected Color startColor;
  protected Color endColor;


  /**
   * Constructor to represent an instance of a motion for an animation.
   *
   * @param startTick     starting Tick for the motion
   * @param startPosition the starting position for the shape
   * @param startWidth    the starting width for the shape
   * @param startHeight   the starting height for the shape
   * @param startColor    the starting color for the shape
   * @param endTick       ending Tick for the motion
   * @param endWidth      the ending width for the shape
   * @param endHeight     the ending height for the shape
   * @param endPosition   the ending position for the shape
   * @param endColor      the ending color for the shape
   */
  public MotionAnimator(int startTick, Position2D startPosition, int startWidth, int startHeight,
      Color startColor, int endTick, Position2D endPosition, int endWidth, int endHeight,
      Color endColor) {
    this.startTick = startTick;
    this.startPosition = startPosition;
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.startColor = startColor;
    this.endTick = endTick;
    this.endPosition = endPosition;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.endColor = endColor;
    if (this.startWidth < 0 || this.startHeight < 0 || this.endWidth < 0 || this.endHeight < 0
        || this.startTick < 0 || this.endTick < 0) {
      throw new IllegalArgumentException("Invalid motion");
    }
  }

  /**
   * Default constructor.
   */
  public MotionAnimator() {
    //empty constructor
  }

  /**
   * Getter method to return the startTick of a motion.
   *
   * @return startTick
   */
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Getter method to return the endTick of a motion.
   *
   * @return endTick
   */
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * Getter method to return the starting Color.
   *
   * @return starting Color
   */
  public Color getStartColor() {
    return startColor;
  }

  /**
   * Getter method to return the ending Color.
   *
   * @return ending Color
   */
  public Color getEndColor() {
    return endColor;
  }

  /**
   * Getter method to return starting height of shape at beginning of motion.
   *
   * @return returns the start height
   */
  public int getStartHeight() {
    return startHeight;
  }

  /**
   * Getter method to return end height of shape at the end of motion.
   *
   * @return returns the end Height
   */
  public int getEndHeight() {
    return endHeight;
  }

  /**
   * Getter method to return the starting width of the shape at the beginning of the motion.
   *
   * @return returns the starting Width
   */
  public int getStartWidth() {
    return startWidth;
  }

  /**
   * Getter method to return the ending width of the shape at the end of the motion.
   *
   * @return returns the ending width
   */
  public int getEndWidth() {
    return endWidth;
  }

  /**
   * Returns the starting position of a shape at beginning of motion.
   *
   * @return returns starting position of shape
   */
  public cs3500.model.Position2D getStartPosition() {
    return startPosition;
  }

  /**
   * Returns the ending position of a shape at the end of a motion.
   *
   * @return ending position of shape
   */
  public Position2D getEndPosition() {
    return endPosition;
  }

  /**
   * Outputs a string description of a motion. Displays all the fields of the motion class to
   * represent the state of the shape after a motion.
   *
   * @return a String visualization of a motion
   **/
  @Override
  public String description() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.startTick);
    builder.append(" ");
    builder.append(this.begDescription());
    builder.append(" ");
    builder.append(this.endTick);
    builder.append(" ");
    builder.append(this.endDescription());
    return builder
        .toString();
  }

  /**
   * Creates the beginning of a motion's description.
   */
  public String begDescription() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.startPosition.getX());
    builder.append(" ");
    builder.append(this.startPosition.getY());
    builder.append(" ");
    builder.append(this.startWidth);
    builder.append(" ");
    builder.append(this.startHeight);
    builder.append(" ");
    builder.append(this.startColor.getRed());
    builder.append(" ");
    builder.append(this.startColor.getGreen());
    builder.append(" ");
    builder.append(this.startColor.getBlue());
    return builder.toString();
  }

  /**
   * Creates the end of a motion's description.
   */
  public String endDescription() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.endPosition.getX());
    builder.append(" ");
    builder.append(this.endPosition.getY());
    builder.append(" ");
    builder.append(this.endWidth);
    builder.append(" ");
    builder.append(this.endHeight);
    builder.append(" ");
    builder.append(this.endColor.getRed());
    builder.append(" ");
    builder.append(this.endColor.getGreen());
    builder.append(" ");
    builder.append(this.endColor.getBlue());
    return builder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    MotionAnimator that = (MotionAnimator) o;
    return startTick == that.startTick &&
        endTick == that.endTick &&
        startWidth == that.startWidth &&
        endWidth == that.endWidth &&
        startHeight == that.startHeight &&
        endHeight == that.endHeight &&
        startPosition.equals(that.startPosition) &&
        endPosition.equals(that.endPosition) &&
        startColor.equals(that.startColor) &&
        endColor.equals(that.endColor);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(super.hashCode(), startTick, endTick, startPosition, endPosition, startWidth,
            endWidth,
            startHeight, endHeight, startColor, endColor);
  }
}
