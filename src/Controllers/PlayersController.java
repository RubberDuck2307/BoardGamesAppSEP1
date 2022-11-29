package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.scene.layout.Region;

public class PlayersController implements Controller
{
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  public PlayersController(){}
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler)
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
