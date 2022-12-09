package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import TableClasses.PlayerTable;
import Model.PlayersList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class BoardGamesAddBoardGameControllerSelectOwner implements Controller
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
  @FXML public TableColumn<PlayerTable, String> email;
  @FXML public TableColumn<PlayerTable, String> phone;
  @FXML public TableColumn<PlayerTable, String> membership;
  private ObservableList<PlayerTable> playersTables = FXCollections.observableArrayList();
  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;


    fillTable();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    membership.setCellValueFactory(new PropertyValueFactory<>("membership"));
    playersTable.setItems(playersTables);

  }

  @FXML private void choosePlayer()
  {
    if (playersTable.getSelectionModel().getSelectedItem() != null)
    {
      viewHandler.openView(130,
          playersTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(3, -1);
  }

  public void fillTable(){
    playersTables.clear();
    PlayersList playersList = model.getPlayersList();
    playersList = playersList.filterPlayerList(searchField.getText());
    if (guestsRadio.isSelected()){
      playersList = playersList.getGuests();
    }

    else if (membersRadio.isSelected())
    {
      playersList = playersList.getMembers();
    }

    for (int i = 0; i < playersList.size(); i++)
    {
      Player player = playersList.getPlayer(i);
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



  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }

  public void ownerAssociation()
  {
    viewHandler.openView(130,0);
  }
}
