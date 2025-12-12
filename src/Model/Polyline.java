package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Polyline
{
  private final List<Line> lines;
  private Color color;


  public Polyline (Line line)
  {
    this.lines = new ArrayList<>();
    this.lines.add(line);
  }

  public Polyline (List<Line> lines)
  {
    this.lines = lines;
  }


  public void set_color (Color color)
  {
    this.color = color;
  }

  public void set_color (int r, int g, int b)
  {
    this.color = new Color(r, g, b);
  }

  // dachte erst, das waere eine lustige Idee, aber oft war das unlesbar...
  public void set_random_color ()
  {
    Random rand = new Random();

    int r = rand.nextInt(128, 256);
    int g = rand.nextInt(128, 256);
    int b = rand.nextInt(128, 256);

    set_color(r, g, b);
  }

  public void add (Line line)
  {
    lines.add(line);
  }

  public boolean contains (Line line)
  {
    return lines.contains(line);
  }

  public List<Line> get_lines ()
  {
    return lines;
  }

  public double get_length ()
  {
    double length = 0;
    for (Line line : lines)
    {
      length += line.get_length();
    }

    return length;
  }

  public Color get_color ()
  {
    return color;
  }
}
