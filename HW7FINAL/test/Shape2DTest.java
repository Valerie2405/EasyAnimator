import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import cs3500.model.Shape2D;
import cs3500.model.ShapeForm;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Testing the methods in the Shape2D class.
 */
public class Shape2DTest {

  Position2D startPosition = new Position2D(100, 100);
  Position2D endPosition = new Position2D(230, 340);
  Shape2D shapey2 = new Shape2D("shapey2", 10,
      25, Color.CYAN, startPosition, ShapeForm.RECTANGLE.toString());

  MotionAnimation motion1 = new MotionAnimator(1, startPosition,
      50, 100, Color.CYAN, 10, endPosition, 100, 6, Color.BLUE);

  //addMotionstoShape with a valid motion
  @Test
  public void addMotionstoShapeTest() {
    List<MotionAnimation> motions = new ArrayList<>();
    motions.add(motion1);
    shapey2.addMotionsToShape(motion1);
    assertEquals(motions, shapey2.getMotionsofShape().subList(0, 1));
  }

  //exception for addMotionsToShape, adding a motion that starts when another motion is already
  //occurring
  @Test(expected = IllegalArgumentException.class)
  public void addMotionstoShapeExceptions() {
    List<MotionAnimation> motions = new ArrayList<>();
    motions.add(motion1);
    shapey2.addMotionsToShape(motion1);
    MotionAnimation motion2 = new MotionAnimator(5, startPosition, 100, 100, Color.CYAN,
        15, endPosition, 100, 100, Color.CYAN);
    shapey2.addMotionsToShape(motion2);
  }

  //exception for addMotionsToShape, adding a motion that already exists
  @Test(expected = IllegalArgumentException.class)
  public void addDuplicateMotion() {
    List<MotionAnimation> motions = new ArrayList<>();
    motions.add(motion1);
    MotionAnimation motion2 = new MotionAnimator(1, startPosition,
        50, 100, Color.CYAN, 10, endPosition, 100, 6, Color.BLUE);
    shapey2.addMotionsToShape(motion1);
    shapey2.addMotionsToShape(motion2);
  }


  //tests the equals method
  @Test
  public void testEqualityofShapeTest() {
    //Examples of shapes
    Position2D position1 = new Position2D(2, 5);
    Shape2D rectangle1 = new Shape2D("Rectangle1",
        6, 7, Color.magenta, position1, ShapeForm.RECTANGLE.toString());
    Shape2D rectangle1a = new Shape2D("Rectangle1", 6, 7,
        Color.magenta, position1, ShapeForm.RECTANGLE.toString());
    Shape2D ellipse = new Shape2D("Circle1", 5, 5, Color.red,
        position1, ShapeForm.ELLIPSE.toString());
    Shape2D ellipse1a = new Shape2D("Circle2a", 5, 5, Color.RED,
        position1, ShapeForm.ELLIPSE.toString());

    assertEquals(rectangle1, rectangle1a);
    assertNotEquals(ellipse, ellipse1a);
  }

  //tests the hashcode method
  @Test
  public void testHashCode() {
    Shape2D shapey3 = new Shape2D("shapey2", 10, 25, Color.CYAN, startPosition,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey4 = new Shape2D("shapey3", 10, 25, Color.CYAN, startPosition,
        ShapeForm.RECTANGLE.toString());

    assertTrue(shapey2.hashCode() == shapey3.hashCode());
    assertFalse(shapey3.hashCode() == shapey4.hashCode());
  }

  //tests getShapeType
  @Test
  public void getShapeTypeTest() {
    Position2D position1 = new Position2D(2, 5);
    Shape2D rectangle1 = new Shape2D("Rectangle1",
        6, 7, Color.magenta, position1, ShapeForm.RECTANGLE.toString());
    assertEquals("rectangle", rectangle1.getForm());
  }

  //tests the toString
  @Test
  public void descriptionTest() {
    Position2D position1 = new Position2D(2, 5);
    Shape2D rectangle1 = new Shape2D("Rectangle1",
        6, 7, Color.magenta, position1, ShapeForm.RECTANGLE.toString());
    assertEquals("2.0 5.0 6 7 255 0 255", rectangle1.description());
  }

  @Test
  public void getStateofShapeTicktest() {
    Position2D position1 = new Position2D(2, 5);
    Position2D position2 = new Position2D(4, 0);

    Shape2D rectangle1 = new Shape2D("Rectangle1",
        6, 7, Color.magenta, position1, ShapeForm.RECTANGLE.toString());
    MotionAnimation motion = new MotionAnimator(20, position1, 20, 30, Color.RED, 27, position2,
        100, 45, Color.CYAN);
    rectangle1.addMotionsToShape(motion);

    assertEquals(rectangle1.getStateofShapeTick(1), null);
    assertEquals(rectangle1.getStateofShapeTick(-23087), null);
    assertNotNull(rectangle1.getStateofShapeTick(20));
    assertNotNull(rectangle1.getStateofShapeTick(27));

  }
}
