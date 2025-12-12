package View;

import Controller.Controller;
import Model.Model;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;


public class View extends JFrame
{
  public View (Model model)
  {
    setup_frame(model);
  }


  private void setup_frame (Model model)
  {
    this.setTitle("Geomagic");
    this.setMinimumSize(new Dimension(1024, 1024));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(true);

    this.add(new Plot(model));
  }

  public void set_actions (Controller contr)
  {
    JMenuItem open = new JMenuItem("Datei öffnen");
    open.addActionListener(contr);
    open.setActionCommand(Controller.OPEN_ACTION);

    JMenuBar menu_bar = new JMenuBar();
    menu_bar.add(open);
    setJMenuBar(menu_bar);
  }
}
