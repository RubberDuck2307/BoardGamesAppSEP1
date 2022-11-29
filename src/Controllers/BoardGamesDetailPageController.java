package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.scene.layout.Region;

public class BoardGamesDetailPageController implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
