package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.scene.layout.Region;

public interface ExtendedController
{

  public void init(Region region, ModelManager model, ViewHandler viewHandler, int ID, int ID2);

  public Region getRegion();
  public void reset();
}
