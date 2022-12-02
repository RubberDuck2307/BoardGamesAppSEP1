package Controllers;

import Application.ViewHandler;
import Model.BoardGame;
import Model.ModelManager;
import Model.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class BoardGamesInsertRatingController implements ExtendedController
{
  @FXML public TextField nameBoardField;
  @FXML public TextField nameMemberField;
  @FXML public ChoiceBox ratingBox;
  private ViewHandler viewHandler;
  private Region region;
  private ModelManager model;
  private int IDOfPlayer;
  private int IDOfBoardGame;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID, int ID2)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    IDOfPlayer = ID;
    IDOfBoardGame = ID2;
    ArrayList<Integer> values = new ArrayList<>();
    values.add(1);
    values.add(2);
    values.add(3);
    values.add(4);
    values.add(5);
    ObservableList<Integer> items = FXCollections.observableArrayList(
        values);
    ratingBox.setItems(items);
    ratingBox.setValue(5);

    nameBoardField.setText(model.getBoardGamesList().getNameByID(IDOfBoardGame));
    String player = model.getPlayersList().getPlayerByID(IDOfPlayer).getName() + " - " + model.getPlayersList().getPlayerByID(IDOfPlayer).getPhoneNumber();
    nameMemberField.setText(player);




  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  @FXML public void goBack(){
    viewHandler.openView(15,IDOfBoardGame);

  }

  @FXML public void confirmRating()
      throws ParserConfigurationException, TransformerException
  {
    Integer value = (Integer) ratingBox.getValue();
    model.addRating(new Rating(value, IDOfPlayer, IDOfBoardGame));
    model.saveRatings();
    viewHandler.openView(16, IDOfBoardGame);
  }
}
