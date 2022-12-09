package Controllers;

import Application.ViewHandler;
import Model.*;
import TableClasses.RatingTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Optional;

public class BoardGamesRatingsController implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;
  @FXML  public Label headingLabel;
  @FXML private TableView<RatingTable> ratingsTable;
  @FXML public TableColumn<RatingTable, String> player;
  @FXML public TableColumn<RatingTable, String> rating;

  private ObservableList<RatingTable> ratingsInTable = FXCollections.observableArrayList();

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;


    headingLabel.setText(model.getBoardGamesList().getNameByID(ID));

    fillTable();

    player.setCellValueFactory(new PropertyValueFactory<>("player"));
    rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
    ratingsTable.setItems(ratingsInTable);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {
  }

  @FXML public void delete()
      throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure that you want to delete the rating ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK){
      model.getRatings().deleteByID(ratingsTable.getSelectionModel().getSelectedItem().getID());
      model.saveRatings();
      fillTable();
    }
  }

  public void fillTable(){
    ratingsInTable.clear();
    RatingsList ratingsList = model.getRatingsByGame(ID);
    for (int i = 0; i < ratingsList.size(); i++)
    {
      Rating rating = ratingsList.getRating(i);
      String name = model.getPlayersList().getNameByID(rating.getPlayerID());
      ratingsInTable.add(new RatingTable(name,rating.getValue(), rating.getID()));

    }
  }
  @FXML public void addRating(){
    viewHandler.openView(15, ID);
  }

  @FXML public void goBack(){
    viewHandler.openView(9,ID);
  }
}
