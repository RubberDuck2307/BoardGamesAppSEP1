package Controllers;

import Application.ViewHandler;
import Model.*;
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
        BoardGame.getAllowedTypes());
    type.setItems(items);
    ObservableList<String> items2 = FXCollections.observableArrayList(
        BoardGame.getAllowedStatuses());
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

  public void addBoardGame()  throws
      ParserConfigurationException, TransformerException
  {
    int minimum = Integer.parseInt(min.getText());
    int maximum = Integer.parseInt(max.getText());
    System.out.println("this is id " +ID);
    int owner = ID;
    String typeOfGame = type.getValue().toString();
    String statusOfGame = status.getValue().toString();
    if(BoardGame.validateData(nameField.getText(), minimum, maximum)){
      model.addBoardGame(new BoardGame( nameField.getText(), typeOfGame, minimum,maximum, statusOfGame, commentField.getText(), owner, 0 ));
      model.saveBoardGames();
      System.out.println(model.getBoardGamesList());
      viewHandler.openView(9,model.getBoardGamesList().getBoardGame(model.getBoardGamesList()
          .size() -1).getID());
    }

  }

  public void backButton()
  {
    viewHandler.openView(132, -1);
  }


}
