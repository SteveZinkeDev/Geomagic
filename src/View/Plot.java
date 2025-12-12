package View;

import Model.Model;
import Model.Line;
import Model.Polyline;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;


public class Plot extends JComponent
{
  private final Model model;

  private static final int BORDER = 20;
  private static final int BORDER_HALF = BORDER >> 1;


  public Plot (Model model)
  {
    this.model = model;
  }


  @Override
  protected void paintComponent (Graphics g)
  {
    super.paintComponent(g);

    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//    paint_axes(g);
    paint_polylines(g);
    paint_legend(g);
  }

  private void paint_axes (Graphics g)
  {
    int height = getHeight();
    int width  = getWidth();

    ((Graphics2D) g).setStroke(new BasicStroke(3));
    g.setColor(Color.BLACK);

    // X Achse unten
    g.drawLine(BORDER, height - BORDER, width - BORDER - BORDER_HALF, height - BORDER);
    g.fillPolygon(new int[] { width - BORDER, width - BORDER - BORDER_HALF, width - BORDER - BORDER_HALF },
                  new int[] { height - BORDER, height - BORDER - BORDER_HALF, height - BORDER_HALF },
                  3);

    // Y Achse links
    g.drawLine(BORDER, height - BORDER, BORDER, BORDER + BORDER_HALF);
    g.fillPolygon(new int[] { BORDER, BORDER_HALF, BORDER + BORDER_HALF },
                  new int[] { BORDER, BORDER + BORDER_HALF, BORDER + BORDER_HALF },
                  3);
  }

  public void paint_polylines (Graphics g)
  {
    ((Graphics2D) g).setStroke(new BasicStroke(4));

    int point_radius = BORDER >> 2;

    for (Polyline pl : model.get_polylines())
    {
      for (Line l : pl.get_lines())
      {
        g.setColor(pl.get_color());

        // Direkt auf int gecastet fuer diese Aufgabe, da hier Nachkommastellen vernachlaessigbar waeren (wenn vorhanden)
        // Fuer andere Anwendungsfaelle gegebenfalls runden (Math.round()) oder beibehalten
        // -> zB bei SVGs
        int x1 = (int) l.p1().x();
        int y1 = (int) l.p1().y();
        int x2 = (int) l.p2().x();
        int y2 = (int) l.p2().y();

        g.drawLine(x1, y1, x2, y2);

        g.setColor(Color.BLACK);

        g.fillOval(x1 - point_radius, y1 - point_radius, BORDER_HALF,BORDER_HALF);
        g.fillOval(x2 - point_radius, y2 - point_radius, BORDER_HALF,BORDER_HALF);

        g.drawString(String.format("%.0f, %.0f", l.p1().x(), l.p1().y()), x1 - BORDER, y1 - BORDER);
        g.drawString(String.format("%.0f, %.0f", l.p2().x(), l.p2().y()), x2 - BORDER, y2 - BORDER);
      }
    }
  }

  // Legende oben links
  public void paint_legend (Graphics g)
  {
    Font font = g.getFont().deriveFont(Font.BOLD, 15.f);
    g.setFont(font);

    for (int i = 0; i < model.get_polylines().size(); i++)
    {
      Polyline pl = model.get_polylines().get(i);

      String text = String.format("Linienzug %d: %d Linien - Länge: %.2f", i+1, pl.get_lines().size(), pl.get_length());

      g.setColor(pl.get_color());
      g.drawString(text, BORDER, (i * BORDER) + BORDER);
    }
  }
}
