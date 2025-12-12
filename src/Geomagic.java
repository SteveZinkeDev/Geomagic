import Controller.Controller;
import Model.Model;
import View.View;

import java.io.File;


public class Geomagic
{
  public static void main (String[] args)
  {
    Model model = new Model();
    View view = new View(model);
    Controller contr = new Controller(model, view);

    // direkt beim Start oeffnen, danach koennen anderen Dateien ueber das MenuItem geladen werden
    contr.open_file(new File("resources/input.txt"));

    view.setVisible(true);
  }
}
