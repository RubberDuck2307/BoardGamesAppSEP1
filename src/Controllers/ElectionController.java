package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class ElectionController implements Controller
{
  public @FXML Button backButton;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  public @FXML Button detailPageButton;
  @FXML public Button addVoteButton;
  @FXML public Button addElectionButton;



  public ElectionController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(1, -1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
