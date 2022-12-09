package Controllers;

import Application.ViewHandler;
import Model.ModelManager;
import Model.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class EventsDetailPageController implements Controller
{
  public Label heading;
  public TextField nameField;
  public TextField placeField;
  public TextField linkField;
  public DatePicker fromDateField;
  public TextField fromHoursField;
  public TextField fromMinutesField;
  public DatePicker toDateField;
  public TextField toHoursField;
  public TextField toMinutesField;
  public TextArea descriptionField;
  public TextArea commentField;
  public Button editButton;
  public Button deleteButton;
  public Button seeParticipantsButton;
  private Region region;
  private ModelManager model;
  private ViewHandler viewHandler;
  private int ID;
  private EventHandler save;

  private void save()
  {
  }

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    this.ID = ID;
    setData();
  }

  public void setData()
  {
    Event event = model.getEventsList().getEventByID(ID);
    heading.setText(event.getName());
    nameField.setText(event.getName());
    placeField.setText(event.getPlace());
    fromDateField.setValue(event.getFromDate());
    fromHoursField.setText(event.getFromHours());
    fromMinutesField.setText(event.getFromMinutes());
    toDateField.setValue(event.getToDate());
    toHoursField.setText(event.getToHours());
    toMinutesField.setText(event.getToMinutes());
    linkField.setText(event.getLink());
    descriptionField.setText(event.getDescription());
    commentField.setText(event.getComment());

    nameField.setMouseTransparent(true);
    placeField.setMouseTransparent(true);
    fromDateField.setMouseTransparent(true);
    fromHoursField.setMouseTransparent(true);
    fromMinutesField.setMouseTransparent(true);
    toDateField.setMouseTransparent(true);
    toHoursField.setMouseTransparent(true);
    toMinutesField.setMouseTransparent(true);
    linkField.setMouseTransparent(true);
    descriptionField.setMouseTransparent(true);
    commentField.setMouseTransparent(true);

    nameField.setFocusTraversable(false);
    placeField.setFocusTraversable(false);
    fromDateField.setFocusTraversable(false);
    fromHoursField.setFocusTraversable(false);
    fromMinutesField.setFocusTraversable(false);
    toDateField.setFocusTraversable(false);
    toHoursField.setFocusTraversable(false);
    toMinutesField.setFocusTraversable(false);
    linkField.setFocusTraversable(false);
    descriptionField.setFocusTraversable(false);
    commentField.setFocusTraversable(false);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {
  }

  public void edit(ActionEvent actionEvent)
  {
    nameField.setMouseTransparent(false);
    placeField.setMouseTransparent(false);
    fromDateField.setMouseTransparent(false);
    fromHoursField.setMouseTransparent(false);
    fromMinutesField.setMouseTransparent(false);
    toDateField.setMouseTransparent(false);
    toHoursField.setMouseTransparent(false);
    toMinutesField.setMouseTransparent(false);
    linkField.setMouseTransparent(false);
    descriptionField.setMouseTransparent(false);
    commentField.setMouseTransparent(false);

    nameField.setFocusTraversable(true);
    placeField.setFocusTraversable(true);
    fromDateField.setFocusTraversable(true);
    fromHoursField.setFocusTraversable(true);
    fromMinutesField.setFocusTraversable(true);
    toDateField.setFocusTraversable(true);
    toHoursField.setFocusTraversable(true);
    toMinutesField.setFocusTraversable(true);
    linkField.setFocusTraversable(true);
    descriptionField.setFocusTraversable(true);
    commentField.setFocusTraversable(true);
    editButton.setText("Save Changes");

    save = event -> {
      String name = nameField.getText();
      String place = placeField.getText();
      String link = linkField.getText();
      LocalDate fromDate = fromDateField.getValue();
      String fromHours = fromHoursField.getText();
      int intFromHours = Integer.parseInt(fromHours);
      String fromMinutes = fromMinutesField.getText();
      int intFromMinutes = Integer.parseInt(fromMinutes);
      LocalDate toDate = toDateField.getValue();
      String toHours = toHoursField.getText();
      int intToHours = Integer.parseInt(toHours);
      String toMinutes = toMinutesField.getText();
      int intToMinutes = Integer.parseInt(toMinutes);
      String comment = commentField.getText();
      String description = descriptionField.getText();

      try
      {
      LocalDateTime from = fromDate.atTime(intFromHours, intFromMinutes);
      LocalDateTime to = toDate.atTime(intToHours, intToMinutes);

        if (Event.VALIDATE_DATA(name, place, from, to))
        {
          Event event1 = new Event(ID, name, place, fromDate, intFromHours,
              intFromMinutes, toDate, intToHours, intToMinutes, description,
              new ArrayList<>(), comment, link);
          model.setEvent(event1, ID);
          try
          {
            model.saveEvent();
          }
          catch (ParserConfigurationException e)
          {
            throw new RuntimeException(e);
          }
          catch (TransformerException e)
          {
            throw new RuntimeException(e);
          }
          setData();
          editButton.setOnAction(this::edit);
          editButton.setText("Edit");
        }
      }
      catch (Exception e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid data");
        alert.setHeaderText(e.getMessage());
        Optional<ButtonType> result = alert.showAndWait();
      }
    }; editButton.setOnAction(save);
  }

  public void delete() throws ParserConfigurationException, TransformerException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to delete the event ");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK)
    {
      model.getEventsList().deleteByID(ID);
      model.saveEvent();
      viewHandler.openView(7, -1);
    }
  }

  public void seeParticipants()
  {
    viewHandler.openView(13, ID);
  }

  public void goBack()
  {
    viewHandler.openView(7, -1);
  }
}
