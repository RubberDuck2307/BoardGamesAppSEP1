package Model;

import java.util.ArrayList;

/**
 * A class representing a list of Event objects
 * @author Anna Andrlova, Alex Bolfa, Christos Artemisios, Jan Metela
 */

public class EventsList
{
  private ArrayList<Event> eventsList;

  /**
   * Zero-argument constructor. Set eventsList to new empty ArrayList
   */
  public EventsList()
  {
    eventsList = new ArrayList<>();
  }

  /**
   * Add the given event to the List
   * If the ID of the Event is -1 it is set to the value of the ID of the last Event in the list incremented by 1.
   * If the list is empty the ID is set to 0
   */
  public void addEvent(Event event)
  {
    if (event.getID() == -1)
    {
      if (eventsList.size() != 0)
      {
        event.setID(eventsList.get(eventsList.size() - 1).getID() + 1);
      }
      else
      {
        event.setID(0);
      }
    }
    eventsList.add(event);
  }


  /**
   * Replace an event with particular ID with the given event.
   * If an event with the ID is not found in the list, the given event is added on the end of the list instead.
   * @param event event by which is the other event replaced
   * @param ID ID of event that is replaced
   */
  public void setEvent(Event event, int ID)
  {
    boolean found = false;
    for (int i = 0; i < eventsList.size(); i++)
    {
      if (eventsList.get(i).getID() == ID)
      {
        eventsList.set(i, event);
        found = true;
        break;
      }
    }
    if(!found)
    {
      eventsList.add(event);
    }
  }

  /**
   * Removes an event from the list by given ID
   * @param ID ID of the event that is removed
   */
  public void deleteByID(int ID)
  {
    for (int i = 0; i < eventsList.size(); i++)
    {
      if (eventsList.get(i).getID() == ID)
      {
        eventsList.remove(i);
        break;
      }
    }
  }

  /**
   *
   * @param index index of an event
   * @return an event on given index
   */
  public Event getEvent(int index)
  {
    return eventsList.get(index);
  }

  /**
   *
   * @param ID ID of an event
   * @throws RuntimeException if event with such ID is not in the list
   * @return an event whose ID equals to the given ID
   */
  public Event getEventByID(int ID){
    for (int i = 0; i < size(); i++){
      if (eventsList.get(i).getID() == ID){
        return eventsList.get(i);
      }
    }
    throw new RuntimeException("There is no event with such ID");
  }

  /**
   *
   * @param ID the ID of player who participates the event
   * @return a new EventsLists with only events from the original list which participantsIDs list contains the given ID
   */
  public EventsList getEventsByParticipantID(int ID){
    EventsList eventsList1 = new EventsList();
    for (int i = 0; i < size(); i++)
    {
      if (getEvent(i).getParticipantsIDs().contains(ID)){
        eventsList1.addEvent(getEvent(i));
      }
    }
    return eventsList1;
  }

  /**
   *
   * @return size of eventsList
   */
  public int size()
  {
    return eventsList.size();
  }

  /**
   *
   * @return values of all attributes of all the events in the list
   */
  @Override public String toString()
  {
    return "Model.EventsList{" + "eventsList=" + eventsList + '}';
  }
}
