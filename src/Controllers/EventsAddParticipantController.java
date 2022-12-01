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

public class EventsAddParticipantController implements Controller
{
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;

  @FXML public TableView<PlayerTable> participantsTable;
  @FXML public TableColumn<PlayerTable, String> name;
  @FXML public TableColumn<PlayerTable, String> email;
  @FXML public TableColumn<PlayerTable, String> phone;
  @FXML public TableColumn<PlayerTable, String> membership;
  private ObservableList<PlayerTable> participantsInTable = FXCollections.observableArrayList();
  int IDOfEvent;

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    System.out.println("I am opened with ID" + ID);
    IDOfEvent = ID;

    fillTable();

    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    membership.setCellValueFactory(new PropertyValueFactory<>("membership"));
    participantsTable.setItems(participantsInTable);
  }

  public void fillTable()
  {
    participantsInTable.clear();
    ArrayList<Integer> participantsID = model.getEventsList()
        .getEventByID(IDOfEvent).getParticipantsIDs();

    System.out.println( model.getEventsList());
    PlayersList playersList = model.getPlayersList();
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

      if (participantsID.contains(player.getID()))
      {
        participantsInTable.add(
            new PlayerTable(player.getName(), player.getPhoneNumber(),
                player.getEmail(), membership, player.getID()));
      }
    }
  }

  @FXML public void goBack()
  {
    viewHandler.openView(11, IDOfEvent);
  }

  @FXML public void addParticipant(){
    viewHandler.openView(14,IDOfEvent);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {
  }
}
