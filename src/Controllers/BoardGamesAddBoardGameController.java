package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.BoardGamesList;
import Model.ModelManager;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class BoardGamesAddBoardGameController  implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  public ChoiceBox status;
  public ChoiceBox type;
  public TextField max;
  public TextField min;
  public TextField nameField;
  public TextArea commentField;
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    ObservableList<String> items = FXCollections.observableArrayList(
        BoardGame.getAllowedTypes());
    type.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
    status.setItems(items2);
    type.setValue(items.get(0));
    status.setValue(items2.get(0));
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void addBoardGame()  throws
      ParserConfigurationException, TransformerException
  {
    int minimum = Integer.parseInt(min.getText());
    int maximum = Integer.parseInt(max.getText());
    String typeOfGame = type.getValue().toString();
    String statusOfGame = status.getValue().toString();
    if(BoardGame.validateData(nameField.getText(), minimum, maximum)){
      model.addBoardGame(new BoardGame( nameField.getText(), typeOfGame, minimum,maximum, statusOfGame, commentField.getText(), 0, 0 ));
      model.saveBoardGames();
      viewHandler.openView(9,model.getBoardGamesList().size() -1);
    }

  }

  public void backButton()
  {
    viewHandler.openView(3, -1);
  }

  public void selectOwner(ActionEvent actionEvent)
  {
  }
}
