import static org.junit.Assert.assertEquals;

import cs3500.model.StateofShape;
import java.awt.Color;
import org.junit.Test;

/**
 * Class that tests the methods in the state of shape class.
 */
public class StateofShapeTest {


  //tests stateofShape
  @Test
  public void testStateofShape() {
    StateofShape state = new StateofShape("Ellipse", 5, 2, 3, 4, Color.BLUE);
    assertEquals(state.getForm(), "Ellipse");
    assertEquals(state.getX(), 5);
    assertEquals(state.getY(), 2);
    assertEquals(state.getW(), 3);
    assertEquals(state.getH(), 4);
    assertEquals(state.getC(), Color.BLUE);

    StateofShape state2 = new StateofShape("Rectangle", 5, 2, 3, -4, Color.RED);
    assertEquals(state.getForm(), "Rectangle");
    assertEquals(state.getX(), 5);
    assertEquals(state.getY(), 2);
    assertEquals(state.getW(), 3);
    assertEquals(state.getH(), -4);
    assertEquals(state.getC(), Color.RED);


  }


}
