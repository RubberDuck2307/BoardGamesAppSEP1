package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class BoardgamesAddRatingController implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  @FXML public TextField searchField;
  @FXML public Button backButton;
  @FXML public RadioButton allRadio;
  @FXML public RadioButton membersRadio;
  @FXML public RadioButton guestsRadio;
  @FXML public TableView<PlayerTable> playersTable;
  @FXML public TableColumn<PlayerTable, String> name;
  @FXML public TableColumn<PlayerTable, String> email2;
  @FXML public TableColumn<PlayerTable, String> phone2;
  @FXML public TableColumn<PlayerTable, String> membership2;
  @FXML public Label headingLabel;
  private ObservableList<PlayerTable> playersTables = FXCollections.observableArrayList();
  private int ID;

  public BoardgamesAddRatingController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;

    fillTable();

    headingLabel.setText(model.getBoardGamesList().getNameByID(ID));

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    email2.setCellValueFactory(new PropertyValueFactory<>("email"));
    phone2.setCellValueFactory(new PropertyValueFactory<>("phone"));
    membership2.setCellValueFactory(new PropertyValueFactory<>("membership"));
    playersTable.setItems(playersTables);
  }

  @FXML private void choosePlayer()
  {
    viewHandler.openView(8,
        playersTable.getSelectionModel().getSelectedItem().getID());
  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(16, ID);
  }

  public void fillTable()
  {
    playersTables.clear();
    PlayersList playersList = model.getPlayersList();
    playersList = playersList.filterPlayerList(searchField.getText());
    if (guestsRadio.isSelected())
    {
      playersList = playersList.getGuests();
    }

    else if (membersRadio.isSelected())
    {
      playersList = playersList.getMembers();
    }

    RatingsList ratingsList = model.getRatingsByGame(ID);
    ArrayList<Integer> rated = new ArrayList<>();
    for (int i = 0; i < ratingsList.size(); i++)
    {
      rated.add(ratingsList.getRating(i).getPlayerID());
    }

    for (int i = 0; i < playersList.size(); i++)
    {
      Player player = playersList.getPlayer(i);
      if (!rated.contains(player.getID())){
      String membership = "";
      if (player.isMembership())
      {
        membership = "Member";
      }
      else
      {
        membership = "Guest";
      }
      playersTables.add(
          new PlayerTable(player.getName(), player.getPhoneNumber(),
              player.getEmail(), membership, player.getID()));
    }
    }
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @FXML public void confirmPlayer()
  {
    if (playersTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(1,
          playersTable.getSelectionModel().getSelectedItem().getID(), ID);
    }
  }

  public void goBack()
  {
    viewHandler.openView(16, ID);
  }

  @Override public void reset()
  {

  }
}
