import static org.junit.Assert.assertEquals;

import cs3500.animator.view.TextualView;
import cs3500.model.AnimatorModel;
import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import cs3500.model.Shape2D;
import cs3500.model.ShapeForm;
import java.awt.Color;
import org.junit.Test;

/**
 * Class to test the methods in the textual view class.
 */
public class TextualViewTest {

  AnimatorModel scene1 = new AnimatorModel();
  Appendable ap = new StringBuilder();

  TextualView view = new TextualView(scene1, ap, 1);


  Position2D startPosition = new Position2D(100, 100);
  Position2D endPosition = new Position2D(230, 340);
  Shape2D shapey = new Shape2D("shapey", 10,
      25, Color.CYAN, startPosition, ShapeForm.RECTANGLE.toString());

  MotionAnimation motion1 = new MotionAnimator(1, startPosition,
      10, 25, Color.CYAN, 10, endPosition, 100, 6, Color.BLUE);

  //tests the output of the textual view with correct inputs
  @Test
  public void testTextualView() {
    TextualView text = new TextualView(scene1, ap, 1);
    shapey.addMotionsToShape(motion1);
    scene1.startAnimation(500, 500, shapey);
    assertEquals("canvas 0 0 500 500\n"
            + "shape shapey rectangle\n"
            + "motion shapey 1.0 100.0 100.0 10 25 0 255 255 10.0 230.0 340.0 100 6 0 0 255\n",
        text.description());
  }

  //tests illegal argument exception when null model is passed to the view
  @Test(expected = IllegalArgumentException.class)
  public void SVGNullModel() {
    TextualView text = new TextualView(null, ap, 1);
    text.display();
  }

  //tests illegal argument exception when null appendable is passed
  @Test(expected = IllegalArgumentException.class)
  public void SVGNullAp() {
    TextualView text = new TextualView(scene1, null, 1);
    text.display();
  }

  //tests illegal argument exception when invalid tempo is passed
  @Test(expected = IllegalArgumentException.class)
  public void SVGInvalidTempo() {
    TextualView text = new TextualView(scene1, ap, 0);
    text.display();
  }

  //tests unsupported argument exception for the interactive methods
  @Test(expected = UnsupportedOperationException.class)
  public void txtGoAnimation() {
    TextualView txt = new TextualView(scene1, ap, 1);
    txt.goAnimation();
  }

  //tests unsupported argument exception for the interactive methods
  @Test(expected = UnsupportedOperationException.class)
  public void txtStop() {
    TextualView txt = new TextualView(scene1, ap, 1);
    txt.stop();
  }

  //tests unsupported argument exception for the interactive methods
  @Test(expected = UnsupportedOperationException.class)
  public void txtUnsupported() {
    TextualView txt = new TextualView(scene1, ap, 1);
    txt.getPauseButton();
  }

}
