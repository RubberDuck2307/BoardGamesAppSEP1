package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.scene.layout.Region;

public interface Controller
{

  public void init(Region region, ModelManager model, ViewHandler viewHandler, int ID);
  public Region getRegion();
  public void reset();
}
