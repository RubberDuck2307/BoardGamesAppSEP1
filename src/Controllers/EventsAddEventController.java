package Controllers;

import Application.ViewHandler;
import Model.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import Model.ModelManager;
import Model.Event;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventsAddEventController implements Controller
{
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

  @Override public void init(Region region, ModelManager model,
      ViewHandler viewHandler, int ID)
  {
    this.region = region;
    this.model = model;
    this.viewHandler = viewHandler;
    fromDateField.setDisable(false);
    toDateField.setDisable(false);
  }

  @Override public Region getRegion()
  {
    return region;
  }

  @Override public void reset()
  {
  }

  public void addEvent()
      throws ParserConfigurationException, TransformerException
  {

    int intFromHours = Integer.parseInt(fromHoursField.getText());
    int intFromMinutes = Integer.parseInt(fromMinutesField.getText());
    LocalDateTime from = fromDateField.getValue().atTime(intFromHours, intFromMinutes);
    int intToHours = Integer.parseInt(toHoursField.getText());
    int intToMinutes = Integer.parseInt(toMinutesField.getText());
    LocalDateTime to = toDateField.getValue().atTime(intToHours, intToMinutes);

    if(Event.validateData(from, to)){
      model.addEvent(new Event(ID, nameField.getText(), placeField.getText(), fromDateField.getValue(), intFromHours, intFromMinutes, toDateField.getValue(), intToHours, intToMinutes, descriptionField.getText(),
          new ArrayList<>(), commentField.getText(), linkField.getText()));
      model.saveEvent();
      viewHandler.openView(7,model.getEventsList().size() -1);
    }
  }

  public void goBack(){
    viewHandler.openView(7,-1);
  }
}
