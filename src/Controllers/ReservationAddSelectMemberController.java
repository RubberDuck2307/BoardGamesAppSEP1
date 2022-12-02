package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Reservation;
import javafx.scene.layout.Region;

public class ReservationAddSelectMemberController implements Controller
{

  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
