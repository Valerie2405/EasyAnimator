import static org.junit.Assert.assertEquals;

import cs3500.animator.view.SVGView;
import cs3500.model.AnimatorModel;
import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import cs3500.model.Shape2D;
import cs3500.model.ShapeForm;
import java.awt.Color;
import java.io.IOException;
import org.junit.Test;

/**
 * Testing the methods in the SVGView class.
 */
public class SVGViewTest {

  AnimatorModel scene1 = new AnimatorModel();
  Appendable ap = new StringBuilder();

  SVGView view = new SVGView(scene1, ap, 1);

  Position2D startPosition = new Position2D(100, 100);
  Position2D endPosition = new Position2D(230, 340);
  Shape2D shapey = new Shape2D("shapey", 10,
      25, Color.CYAN, startPosition, ShapeForm.RECTANGLE.toString());

  MotionAnimation motion1 = new MotionAnimator(1, startPosition,
      10, 25, Color.CYAN, 10, endPosition, 100, 6, Color.BLUE);

  //tests the SVG description method
  @Test
  public void SVGTest() throws IOException {
    shapey.addMotionsToShape(motion1);
    scene1.startAnimation(500, 500, shapey);
    view.display();
    assertEquals(
        "<svg width=\"500\" height=\"500\" xmlns=\"http://www.w3.org/2000/svg\" "
            + "xmlns:xlink= \"http://www.w3.org/1999/xlink\" >\n"
            + "\n"
            + "<rect id=\"shapey\" x=\"100.0\" y=\"100.0\" width=\"10\" height=\"25\" fill=\"rgb(0,"
            + "255,255)\" visibility=\"visible\" >\n"
            + "     <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"x\" from=\"100.0\" to=\"230.0\" fill=\"freeze\" />\n"
            + "     <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"y\" from=\"100.0\" to=\"340.0\" fill=\"freeze\" />\n"
            + "     <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"width\" from=\"10.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "     <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"height\" from=\"25.0\" to=\"6.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "</svg>", view.buildSVG());
  }

  //tests illegal argument exception for passing in a null model
  @Test(expected = IllegalArgumentException.class)
  public void SVGNullModel() {
    SVGView svg = new SVGView(null, ap, 1);
    view.buildSVG();
  }

  //tests illegal argument exception for passing in invalid tempo
  @Test(expected = IllegalArgumentException.class)
  public void SVGBadTempo() {
    SVGView svg = new SVGView(scene1, ap, -1);
    view.buildSVG();
  }

  //tests illegal argument exception for passing in a null appendable
  @Test(expected = IllegalArgumentException.class)
  public void SVGNullAp() {
    SVGView svg = new SVGView(scene1, null, 1);
    svg.buildSVG();
  }

  //tests unsupported argument exception for the interactive methods
  @Test(expected = UnsupportedOperationException.class)
  public void SVGStop() {
    SVGView svg = new SVGView(scene1, ap, 1);
    svg.stop();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void SVGGo() {
    SVGView svg = new SVGView(scene1, ap, 1);
    svg.goAnimation();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void SVGStartButton() {
    SVGView svg = new SVGView(scene1, ap, 1);
    svg.getStartButton();
  }

}
