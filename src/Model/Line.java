package Model;


public record Line (Point p1, Point p2)
{
  public static final float COMPARE_TOLERANCE = 0.05f;


  public Line (float x1, float y1, float x2, float y2)
  {
    this(new Point(x1, y1), new Point(x2, y2));
  }


  public double get_length ()
  {
    float a = Math.abs(p1.x() - p2.x());
    float b = Math.abs(p1.y() - p2.y());

    return Math.hypot(a, b);
  }

  public boolean p1_matches (Line other)
  {
    return Line.points_match(this.p1, other.p1()) || Line.points_match(this.p1, other.p2());
  }

  public boolean p2_matches (Line other)
  {
    return Line.points_match(this.p2, other.p1()) || Line.points_match(this.p2, other.p2());
  }

  // a und b, damit keine Verwirrung mit den Instanzvariablen entsteht
  public static boolean points_match (Point a, Point b)
  {
    return (Math.abs(a.x() - b.x()) <= COMPARE_TOLERANCE && Math.abs(a.y() - b.y()) <= COMPARE_TOLERANCE);
  }
}
