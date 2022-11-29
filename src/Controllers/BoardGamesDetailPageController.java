package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.ModelManager;
import Model.Player;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class BoardGamesDetailPageController implements Controller
{
  public Label heading;
  public TextArea comment;
  public TextField nameField;
  public Button edit;
  public TextField min;
  public TextField max;
  public ChoiceBox type;
  public ChoiceBox status;
  public TextField numberOfVotes;
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
    setData();
  }
  public void setData(){
    BoardGame boardGame = model.getBoardGamesList().getBoardGame(ID);
    heading.setText("Boardgame name "+ boardGame.getName());
    comment.setText(boardGame.getComment());
    nameField.setText(boardGame.getName());
    min.setText(boardGame.getNumberOfPlayersMin() +"");
    max.setText(boardGame.getNumberOfPlayersMax() + "");
    type.setValue(boardGame.getType());
    status.setValue(boardGame.getAvailabilityStatus());
    numberOfVotes.setText(boardGame.getNumberOfVotes() + "");
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
