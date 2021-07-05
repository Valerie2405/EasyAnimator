import static org.junit.Assert.assertEquals;

import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import java.awt.Color;
import org.junit.Test;

/**
 * A test class to test the MotionAnimator class.
 */
public class MotionAnimatorTest {

  Position2D startPosition = new Position2D(100, 150);
  Position2D endPosition = new Position2D(150, 200);

  MotionAnimation motion1 = new MotionAnimator(1, startPosition, 100, 50,
      Color.BLUE, 5, endPosition, 250, 100, Color.RED);


  @Test
  public void testGetTicks() {
    assertEquals(1, motion1.getStartTick());
    assertEquals(5, motion1.getEndTick());
  }

  @Test
  public void testGetWidthHeight() {
    //WIDTH
    assertEquals(100, motion1.getStartWidth());
    assertEquals(250, motion1.getEndWidth());
    //HEIGHT
    assertEquals(50, motion1.getStartHeight());
    assertEquals(50, motion1.getStartHeight());

  }

  @Test
  public void testgetPosition() {
    assertEquals(100, motion1.getStartPosition().getX(), 0);
    assertEquals(150, motion1.getStartPosition().getY(), 0);

    assertEquals(150, motion1.getEndPosition().getX(), 0);
    assertEquals(200, motion1.getEndPosition().getY(), 0);
  }

  @Test
  public void testgetColor() {
    assertEquals(Color.BLUE, motion1.getStartColor());
    assertEquals(Color.RED, motion1.getEndColor());
  }

  //Decription
  @Test
  public void testDescription() {
    assertEquals("1 100.0 150.0 100 50 0 0 255 5 150.0 200.0 250 100 255 0 0",
        motion1.description());
  }

}
