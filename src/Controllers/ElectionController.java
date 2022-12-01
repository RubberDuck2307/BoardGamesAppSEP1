package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

import java.time.LocalDate;

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
    if(model.getElection() == null){
      detailPageButton.setVisible(false);
      addVoteButton.setVisible(false);
    }
    else {
      addElectionButton.setVisible(false);
      if (model.getElection().getEndingDate().isBefore(LocalDate.now()))
      {
        addVoteButton.setDisable(true);
      }
    }

  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(1, -1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @FXML public void loadAddElectionPage(){
    viewHandler.openView(31,-1);
  }

  @FXML public void loadDetailPage(){
    viewHandler.openView(32,-1);
  }

  @FXML public void loadAddVotePage(){
    viewHandler.openView(33,-1);
  }

  @Override public void reset()
  {

  }
}
