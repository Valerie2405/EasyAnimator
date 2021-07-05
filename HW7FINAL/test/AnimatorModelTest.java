import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.animator.util.AnimationReader;
import cs3500.model.Model;
import cs3500.model.MotionAnimation;
import cs3500.model.MotionAnimator;
import cs3500.model.Position2D;
import cs3500.model.AnimatorModel;
import cs3500.model.Shape2D;
import cs3500.model.ShapeForm;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Test animations, equalities of shapes.
 */
public class AnimatorModelTest {

  AnimatorModel scene1 = new AnimatorModel();
  Position2D pos1 = new Position2D(100, 100);
  Position2D pos2 = new Position2D(200, 200);
  Shape2D shapey = new Shape2D("shapey", 5, 5, Color.CYAN, pos1, ShapeForm.RECTANGLE.toString());
  Shape2D shapey2 = new Shape2D("shapey2", 10, 25, Color.CYAN, pos1,
      ShapeForm.RECTANGLE.toString());

  //test startAnimation
  @Test
  public void testStartAnimation() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);
    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey4 = new Shape2D("shapey4", 25, 30, Color.PINK, pos3,
        ShapeForm.RECTANGLE.toString());
    scene2.startAnimation(500, 500, shapey3, shapey4);
    List<Shape2D> shapes = new ArrayList<>();
    shapes.add(shapey3);
    shapes.add(shapey4);

    //tests that shapes are in scene
    assertEquals(shapes, scene2.getShapes());

    //tests that for each shape in this scene their description is outputted correctly
    assertEquals("150.0 90.0 11 2 255 0 0", scene2.getShapes().get(0).description());
    assertEquals("20.0 200.0 25 30 255 175 175", scene2.getShapes().get(1).description());

    //tests that the tick is set to 0 for the beginning of animation
    //assertEquals(0, scene2.getTick());

    //tests that that the values of the height and Board are set properly
    assertEquals(500, scene2.getHeightBoard());
    assertEquals(500, scene2.getWidthBoard());
  }

  //Builder test
  @Test
  public void testBuilder() throws FileNotFoundException {
    Readable f = new FileReader("/Users/emilygringorten/Downloads/hw6-4/" + "buildings.txt");
    Model model = AnimationReader.parseFile(f, new AnimatorModel.Builder());
    assertEquals(800, model.getHeightBoard());
    assertEquals(800, model.getWidthBoard());
    assertEquals(model.getX(), 0);
    assertEquals(model.getY(), 0);
    assertEquals(model.getShapes().size(), 109);
    //wrote this to ensure that none of the shapes are null
    for (Shape2D shape : model.getShapes()) {
      System.out.println(shape.getName() + " " + shape.getMotionsofShape());
    }
  }

  //test startAnimation exception will null shapes
  @Test(expected = IllegalArgumentException.class)
  public void testStartAnimationNullShapes() {
    AnimatorModel scene2 = new AnimatorModel();
    scene2.startAnimation(500, 500, null);
  }

  //tests startAnimation exception with invalid board height
  @Test(expected = IllegalArgumentException.class)
  public void testStartAnimationInvalidHeight() {
    AnimatorModel scene2 = new AnimatorModel();
    scene2.startAnimation(0, 500, null);
  }

  //tests startAnimation exception with invalid board height
  @Test(expected = IllegalArgumentException.class)
  public void testStartAnimationInvalidWidth() {
    AnimatorModel scene2 = new AnimatorModel();
    scene2.startAnimation(500, -5, null);
  }

  //tests adding a shape to the model
  @Test
  public void testaddShape() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);
    Position2D pos4 = new Position2D(2, 3);
    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey4 = new Shape2D("shapey4", 25, 30, Color.PINK, pos3,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey5 = new Shape2D("shapey5", 5, 6, Color.DARK_GRAY, pos4,
        ShapeForm.ELLIPSE.toString());
    scene2.startAnimation(500, 500, shapey3, shapey4);

    //add shapey5 to list of shapes in scene2
    scene2.addShapes(shapey5);
    //test if shapey5 is in the list of shapes for scene2
    assertTrue(scene2.getShapes().contains(shapey5));
  }

  //test removing a shape from the model
  @Test
  public void testRemoveShapes() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);
    Position2D pos4 = new Position2D(2, 3);
    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey4 = new Shape2D("shapey4", 25, 30, Color.PINK, pos3,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey5 = new Shape2D("shapey5", 5, 6, Color.DARK_GRAY, pos4,
        ShapeForm.ELLIPSE.toString());
    scene2.startAnimation(500, 500, shapey3, shapey4, shapey5);
    List<Shape2D> shapes = new ArrayList<>();
    shapes.add(shapey3);
    shapes.add(shapey4);

    scene2.removeShapes(shapey5);
    //test if shapey5 is not in the list of shapes for scene2
    assertEquals(false, scene2.getShapes().contains(shapey5));
    assertEquals(shapes, scene2.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapesException() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);
    Position2D pos4 = new Position2D(2, 3);
    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey4 = new Shape2D("shapey4", 25, 30, Color.PINK, pos3,
        ShapeForm.RECTANGLE.toString());
    Shape2D shapey5 = new Shape2D("shapey5", 5, 6, Color.DARK_GRAY, pos4,
        ShapeForm.ELLIPSE.toString());
    scene2.startAnimation(500, 500, shapey3, shapey4);

    //remove shapye5 that does not exist in this scene
    scene2.removeShapes(shapey5);

  }

  //tests the getShapes method
  @Test
  public void testGetShapes() {
    scene1.startAnimation(500, 500, shapey, shapey2);
    List<Shape2D> shapes = new ArrayList<>();
    shapes.add(shapey);
    shapes.add(shapey2);
    assertEquals(shapes, scene1.getShapes());
    AnimatorModel scene2 = new AnimatorModel();
    scene2.startAnimation(500, 500);
    assertEquals(new ArrayList<Shape2D>(), scene2.getShapes());
  }

  //test the removeMotion method
  @Test
  public void testremoveMotion() {
    scene1.startAnimation(500, 500, shapey, shapey2);
    MotionAnimation motion1 = new MotionAnimator(1, pos2, 50, 100, Color.CYAN, 10, pos2, 100, 100,
        Color.CYAN);
    MotionAnimation motion2 = new MotionAnimator(10, pos2, 100, 7890, Color.CYAN, 20, pos2, 100,
        100, Color.CYAN);
    List<MotionAnimation> listofMotions = new ArrayList<>();
    listofMotions.add(motion2);
    shapey.addMotionsToShape(motion1);
    shapey.addMotionsToShape(motion2);
    assertEquals(2, shapey.getMotionsofShape().size());
    scene1.removeMotion(motion1, shapey);
    assertEquals(1, shapey.getMotionsofShape().size());
  }

  //tests the description method in the model
  @Test
  public void testdescription() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);

    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());

    MotionAnimation motion1 = new MotionAnimator(1, pos2, 50, 100, Color.CYAN, 10, pos2, 100, 100,
        Color.CYAN);
    MotionAnimation motion2 = new MotionAnimator(10, pos2, 100, 100, Color.CYAN, 20, pos3, 100, 100,
        Color.CYAN);
    MotionAnimation motion3 = new MotionAnimator(20, pos2, 100, 100, Color.CYAN, 30, pos3, 100, 100,
        Color.red);
    MotionAnimation motion4 = new MotionAnimator(30, pos2, 100, 100, Color.CYAN, 40, pos3, 200, 200,
        Color.PINK);

    shapey3.addMotionsToShape(motion1);
    shapey3.addMotionsToShape(motion2);
    shapey3.addMotionsToShape(motion3);
    shapey3.addMotionsToShape(motion4);

    scene2.startAnimation(500, 500, shapey3);

    assertEquals("shape shapey3 rectangle\n"
            + "motion shapey3 1 150.0 90.0 50 100 0 255 255 10 150.0 90.0 100 100 0 255 255\n"
            + "motion shapey3 10 150.0 90.0 100 100 0 255 255 20 20.0 200.0 100 100 0 255 255\n"
            + "motion shapey3 20 150.0 90.0 100 100 0 255 255 30 20.0 200.0 100 100 255 0 0\n"
            + "motion shapey3 30 150.0 90.0 100 100 0 255 255 40 20.0 200.0 200 200 255 175 175\n",
        scene2.description());
  }

  //tests add motion
  @Test
  public void testAddMotion() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);

    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());

    MotionAnimation motion1 = new MotionAnimator(1, pos2, 50, 100, Color.CYAN, 10, pos2, 100, 100,
        Color.CYAN);

    scene2.addMotion(motion1, shapey3);
    assertEquals(1, shapey3.getMotionsofShape().size());
  }

  //tests the illegal argument exception for add motion
  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionDuplicate() {
    AnimatorModel scene2 = new AnimatorModel();
    Position2D pos2 = new Position2D(150, 90);
    Position2D pos3 = new Position2D(20, 200);

    Shape2D shapey3 = new Shape2D("shapey3", 11, 2, Color.RED, pos2,
        ShapeForm.RECTANGLE.toString());

    MotionAnimation motion1 = new MotionAnimator(1, pos2, 50, 100, Color.CYAN, 10, pos2, 100, 100,
        Color.CYAN);
    MotionAnimation motion2 = new MotionAnimator(1, pos2, 50, 100, Color.CYAN, 10, pos2, 100, 100,
        Color.CYAN);
    scene2.addMotion(motion1, shapey3);
    scene2.addMotion(motion2, shapey3);

  }
}