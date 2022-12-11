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
   * The zero-argument constructor sets eventsList to new empty ArrayList
   */
  public EventsList()
  {
    eventsList = new ArrayList<>();
  }

  /**
   * Adds the given event to the eventsList
   * If the ID of the Event is -1, it is set to the value of the last event's ID
   * in the list, incremented by 1.
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
   * Replace an event having a particular ID with the given event.
   * If an event with the ID is not found in the list, the given event is added
   * at the end of the list instead.
   * @param event the event that will replace the current event.
   * @param ID the ID of the event that will be replaced.
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
   * Removes an event by ID from the list
   * @param ID the ID of the event that is removed
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
   * @param index the index of an event
   * @return the event with the given index
   */
  public Event getEvent(int index)
  {
    return eventsList.get(index);
  }

  /**
   *
   * @param ID the ID of an event
   * @throws RuntimeException if the event with such ID is not in the list
   * @return an event matching the given ID
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
   * @param ID the ID of player participating in the event
   * @return a new EventsLists showing only events matching with the given ID
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
   * @return the size of eventsList
   */
  public int size()
  {
    return eventsList.size();
  }

  /**
   *
   * @return the values of the attributes of all the events in the list
   */
  @Override public String toString()
  {
    return "Model.EventsList{" + "eventsList=" + eventsList + '}';
  }
}
