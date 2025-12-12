package Controller;

import Model.Model;
import View.View;
import Model.Point;
import Model.Line;
import Model.Polyline;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;


public class Controller implements ActionListener
{
  public static final String OPEN_ACTION = "open";

  private static final Color[] COLORS = new Color[] {
    new Color(0, 117, 133),
    new Color(255, 0, 0),
    new Color(0, 0, 255)
  };


  private final Model model;
  private final View view;


  public Controller (Model model, View view)
  {
    this.model = model;
    this.view = view;

    view.set_actions(this);
  }


  @Override
  public void actionPerformed (ActionEvent e)
  {
    // Switch fuer eventuell mehr Actions
    switch (e.getActionCommand())
    {
      case OPEN_ACTION -> open_file();
    }
  }

  private void open_file ()
  {
    JFileChooser fc = new JFileChooser("resources");

    if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION)
    {
      File selected_file = fc.getSelectedFile();

      open_file(selected_file);
    }
  }

  public void open_file (File selected_file)
  {
    List<String> file_lines;
    try
    {
      file_lines = Files.readAllLines(selected_file.toPath());
    }
    catch (IOException e)
    {
      System.err.println("Could not read file " + selected_file);
      return;
    }

    List<Line> lines = parse_lines(file_lines);
    List<Polyline> polylines = create_polylines(lines);

    model.set_polylines(polylines);

    view.repaint();
  }


  private List<Line> parse_lines (List<String> file_lines)
  {
    List<Line> lines = new ArrayList<>();

    for (String file_line : file_lines)
    {
      String[] coords = file_line.split("\\s+"); // just in case
      if (coords.length != 4) continue;

      float x1 = Float.parseFloat(coords[0]);
      float y1 = Float.parseFloat(coords[1]);
      float x2 = Float.parseFloat(coords[2]);
      float y2 = Float.parseFloat(coords[3]);

      Point a = new Point(x1, y1);
      Point b = new Point(x2, y2);

      lines.add(new Line(a, b));
    }

    return lines;
  }

  public List<Polyline> create_polylines (List<Line> lines)
  {
    List<Polyline> polylines = new ArrayList<>();

    for (Line line : lines)
    {
      if (polylines.stream().anyMatch(pl -> pl.contains(line))) continue;

      List<Line> connected_lines = new ArrayList<>();
      connected_lines.add(line);
      connected_lines = get_connected_lines(lines, connected_lines);

      if (connected_lines.size() > 1)
      {
        polylines.add(new Polyline(connected_lines));
      }
    }

    polylines.sort((pl1, pl2) -> Double.compare(pl2.get_length(), pl1.get_length()));

    for (int i = 0; i < polylines.size(); i++)
    {
      // weil ich nur 3 Farben definiert habe -> durch-cyclen
      polylines.get(i).set_color(COLORS[i % COLORS.length]);
    }

    return polylines;
  }

  // Rekursion noch optimierbar, dass p1/p2 nicht getestet wird, wenn die Linie dadurch hier rein geriet
  public List<Line> get_connected_lines (List<Line> lines, List<Line> connected_lines)
  {
    Line start_line = connected_lines.getLast();

    int matches = 0;
    Line first_match = null;

    for (Line line : lines)
    {
      if (connected_lines.contains(line)) continue;

      if (start_line.p1_matches(line))
      {
        matches++;
        first_match = line;
      }
    }

    if (matches == 1)
    {
      connected_lines.add(first_match);
      connected_lines = get_connected_lines(lines, connected_lines);
    }

    matches = 0;
    first_match = null;

    for (Line line : lines)
    {
      if (connected_lines.contains(line)) continue;

      if (start_line.p2_matches(line))
      {
        matches++;
        first_match = line;
      }
    }

    if (matches == 1)
    {
      connected_lines.add(first_match);
      connected_lines = get_connected_lines(lines, connected_lines);
    }

    return connected_lines;
  }
}
