package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Event
{
  private int ID;
  private String name;
  private String location;
  private LocalDateTime startingDate;
  private LocalDateTime endingDate;
  private String description;
  private ArrayList<Integer> participantsIDs;
  private String comment;
  private String link;

  public Event(int ID, String name, String location, LocalDateTime startingDate,
      LocalDateTime endingDate, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    set(ID, name, location, startingDate, endingDate, description,
        participantsIDs, comment, link);
  }

  Event(String name, String location, LocalDateTime startingDate,
      LocalDateTime endingDate, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    int ID = -1;
    set(ID, name, location, startingDate, endingDate, description,
        participantsIDs, comment, link);
  }

  public void set(int ID, String name, String location, LocalDateTime startingDate,
      LocalDateTime endingDate, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    this.ID = ID;
    this.name = name;
    this.location = location;
    this.startingDate = startingDate;
    this.endingDate = endingDate;
    this.description = description;
    this.participantsIDs = participantsIDs;
    this.comment = comment;
    this.link = link;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public LocalDateTime getStartingDate()
  {
    return startingDate;
  }

  public void setStartingDate(LocalDateTime startingDate)
  {
    this.startingDate = startingDate;
  }

  public LocalDateTime getEndingDate()
  {
    return endingDate;
  }

  public void setEndingDate(LocalDateTime endingDate)
  {
    this.endingDate = endingDate;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public ArrayList<Integer> getParticipantsIDs()
  {
    return participantsIDs;
  }

  public void setParticipantsIDs(ArrayList<Integer> participantsIDs)
  {
    this.participantsIDs = participantsIDs;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public String getLink()
  {
    return link;
  }

  public void setLink(String link)
  {
    this.link = link;
  }

  @Override public String toString()
  {
    return "Model.Event{" + "ID=" + ID + ", name='" + name + '\'' + ", location='"
        + location + '\'' + ", startingDate=" + startingDate + ", endingDate="
        + endingDate + ", description='" + description + '\''
        + ", participantsIDs=" + participantsIDs + ", comment='" + comment
        + '\'' + ", link='" + link + '\'' + '}';
  }
}
