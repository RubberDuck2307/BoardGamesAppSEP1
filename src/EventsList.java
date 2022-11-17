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

  public Event getEvent(int index)
  {
    return eventsList.get(index);
  }

  public int size()
  {
    return eventsList.size();
  }

  @Override public String toString()
  {
    return "EventsList{" + "eventsList=" + eventsList + '}';
  }
}
