package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Model
{
  List<Polyline> polylines = new ArrayList<>();


  public List<Polyline> get_polylines ()
  {
    return Collections.unmodifiableList(polylines);
  }

  public void set_polylines (List<Polyline> polylines)
  {
    this.polylines = polylines;
  }
}
