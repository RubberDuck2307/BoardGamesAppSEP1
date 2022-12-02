package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

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

    RatingsList ratingsList = model.getRatingsByGame(ID);

    headingLabel.setText(model.getBoardGamesList().getNameByID(ID));

    for (int i = 0; i < ratingsList.size(); i++)
    {
      Rating rating = ratingsList.getRating(i);
      String name = model.getPlayersList().getNameByID(rating.getPlayerID());
      ratingsInTable.add(new RatingTable(name,rating.getValue(), rating.getID()));

    }

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
  @FXML public void addRating(){
    viewHandler.openView(15, ID);
  }

  @FXML public void goBack(){
    viewHandler.openView(9,ID);
  }
}
