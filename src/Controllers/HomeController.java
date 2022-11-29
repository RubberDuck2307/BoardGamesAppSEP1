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
  @FXML Button boardGamesButton;
  @FXML Button electionButton;
  @FXML Button reservationsButton;
  @FXML Button borrowingsButton;
  @FXML Button eventsButton;

  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  public HomeController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

  }

  @FXML public void openPlayerPage()
  {
    viewHandler.openView(2, -1);
  }
  @FXML public void openBoardGamesPage()
  {
    viewHandler.openView(3, -1);
  }
  @FXML public void openElectionPage()
  {
    viewHandler.openView(4, -1);
  }
  @FXML public void openReservationPage()
  {
    viewHandler.openView(5, -1);
  }
  @FXML public void openBorrowingsPage()
  {
    viewHandler.openView(6, -1);
  }
  @FXML public void openEventsPage()
  {
    viewHandler.openView(7, -1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
