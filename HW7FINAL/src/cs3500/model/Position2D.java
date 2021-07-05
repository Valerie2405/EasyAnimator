package cs3500.model;


import java.util.Objects;

/**
 * This class represents a 2D position with an x and y coordinate.
 */
public class Position2D {

  protected double x;
  protected double y;

  public Position2D(Position2D v) {
    this(v.x, v.y);
  }

  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Retreives the x coordinate of the shape.
   *
   * @return the x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Retrieves the y coordinate of the shape.
   *
   * @return the y coordinate
   */
  public double getY() {
    return y;
  }

  /**
   * Override the equals method. Determines if two positions are equal.
   *
   * @param a the object being compared against
   * @return whether the two objects are equal
   */
  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  /**
   * Override the hashcode method.
   *
   * @return
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
