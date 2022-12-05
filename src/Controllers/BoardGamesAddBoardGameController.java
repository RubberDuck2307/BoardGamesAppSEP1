package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.util.Optional;

public class BoardGamesAddBoardGameController  implements Controller
{
  public TextField owner;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  public ChoiceBox status;
  public ChoiceBox type;
  public TextField max;
  public TextField min;
  public TextField nameField;
  public TextArea commentField;
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
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.ALLOWED_TYPES);
    type.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.ALLOWED_TYPES);
    status.setItems(items2);
    type.setValue(items.get(0));
    status.setValue(items2.get(0));
    PlayersList playersList= model.getPlayersList();
    if(ID == 0){
      owner.setText("Association");
    }
    else{
      owner.setText(playersList.getNameByID(ID));
    }

  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void addBoardGame()

  {
      int owner = ID;
      String typeOfGame = type.getValue().toString();
      String statusOfGame = status.getValue().toString();

    try
    {
      if(BoardGame.VALIDATE_DATA(nameField.getText(), min.getText(),max.getText()))
      {
        model.addBoardGame(new BoardGame( nameField.getText(), typeOfGame, Integer.parseInt(min.getText()),Integer.parseInt(max.getText()), statusOfGame, commentField.getText(), owner, 0 ));
        model.saveBoardGames();
        System.out.println(model.getBoardGamesList());
        viewHandler.openView(9,model.getBoardGamesList().getBoardGame(model.getBoardGamesList()
            .size() -1).getID());
      }

    }
    catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Invalid data");
      alert.setHeaderText(e.getMessage());
      Optional<ButtonType> result = alert.showAndWait();
    }


  }

  public void backButton()
  {
    viewHandler.openView(132, -1);
  }


}
