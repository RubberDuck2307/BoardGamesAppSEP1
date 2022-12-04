package Model;

import java.util.ArrayList;

public class EventsList
{
  private ArrayList<Event> eventsList;

  public EventsList()
  {
    eventsList = new ArrayList<>();
  }

  public EventsList(ArrayList<Event> events)
  {
    this.eventsList = events;
  }

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

  public Event getEvent(int index)
  {
    return eventsList.get(index);
  }

  public Event getEventByID(int ID){
    for (int i = 0; i < size(); i++){
      System.out.println(ID);
      System.out.println("dd" + eventsList.get(i).getID());
      if (eventsList.get(i).getID() == ID){
        return eventsList.get(i);
      }
    }
    return eventsList.get(0);
  }

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

  public int size()
  {
    return eventsList.size();
  }

  @Override public String toString()
  {
    return "Model.EventsList{" + "eventsList=" + eventsList + '}';
  }
}
