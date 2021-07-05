package cs3500.model;

/**
 * Represents the different forms a shape can take. As of now, our implementation
 * only supports ellipse and rectangle, but other shapes can be easily added.
 */
public enum ShapeForm {
  ELLIPSE("ellipse"),
  RECTANGLE("rectangle");

  String shapeForm;

  ShapeForm(String str) {
    shapeForm = str;
  }


  @Override
  public String toString() {
    return shapeForm;
  }
}
