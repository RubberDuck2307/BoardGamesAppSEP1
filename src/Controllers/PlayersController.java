package Controllers;

import Application.FileReader;
import Application.ViewHandler;
import Model.ModelManager;
import Model.Player;
import Model.PlayerTable;
import Model.PlayersList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class PlayersController implements Controller
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



  public PlayersController()
  {
  }

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
    viewHandler.openView(8,
        playersTable.getSelectionModel().getSelectedItem().getID());
  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(1, -1);
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

  public void loadAddPlayerPage(){
    viewHandler.openView(10,-1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
