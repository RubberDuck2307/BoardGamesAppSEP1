package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class HomeController implements Controller
{

  @FXML Button playersButton;

  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  public HomeController(){}

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

  }

  @FXML public void openPlayerPage(){
    viewHandler.openView(2);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
