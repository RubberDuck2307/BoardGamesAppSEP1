package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class EventsController implements Controller
{
  Region region;
  ModelManager model;
  ViewHandler viewHandler;

  @FXML Button backButton;
  @FXML public TableView<EventsTable> eventsTable;
  @FXML public TableColumn<EventsTable, String> name;
  @FXML public TableColumn<EventsTable, String> notaplace;
  @FXML public TableColumn<EventsTable, String> from;
  @FXML public TableColumn<EventsTable, String> to;
  private ObservableList<EventsTable> eventsTables = FXCollections.observableArrayList();


  public EventsController()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;

    for (int i = 0; i < model.getEventsList().size(); i++)
    {
      Event event = model.getEventsList().getEvent(i);
      String startingDate = event.getFrom().getDayOfMonth() + "/" + event.getFrom().getMonthValue() + "/" + event.getFrom().getYear()  + " - " + event.getFrom().getHour()  + ":" + event.getFrom().getMinute();
      String endingDate = event.getTo().getDayOfMonth() + "/" + event.getTo().getMonthValue() + "/" + event.getTo().getYear()  + " - " + event.getTo().getHour()  + ":" + event.getTo().getMinute();
      eventsTables.add(new EventsTable(event.getName(), event.getPlace(), startingDate, endingDate, event.getID()));

    }
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    notaplace.setCellValueFactory(new PropertyValueFactory<>("place"));
    from.setCellValueFactory(new PropertyValueFactory<>("from"));
    to.setCellValueFactory(new PropertyValueFactory<>("to"));
    eventsTable.setItems(eventsTables);
  }

  @FXML public void backToHomePage()
  {
    viewHandler.openView(1, -1);
  }

  @FXML private void chooseEvent()
  {
    if (eventsTable.getSelectionModel().getSelectedItem() != null)
    {
      System.out.println(
          "YOU CLICKED" + eventsTable.getSelectionModel().getSelectedItem().getID());
      viewHandler.openView(11,
          eventsTable.getSelectionModel().getSelectedItem().getID());
    }
  }

  public void loadAddEventPage(){
    viewHandler.openView(12,-1);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {

  }
}
