package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Event
{
  private int ID;
  private String name;
  private String place;
  private LocalDate fromDate, toDate;
  private int intFromHours, intFromMinutes, intToHours, intToMinutes;
  private LocalDateTime from;
  private LocalDateTime to;
  private String description;
  private ArrayList<Integer> participantsIDs;
  private String comment;
  private String link;

  public Event(int ID, String name, String place, LocalDate fromDate, int intFromHours, int intFromMinutes,
      LocalDate toDate, int intToHours, int intToMinutes, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    set(ID, name, place, fromDate, intFromHours, intFromMinutes, toDate, intToHours, intToMinutes, description,
        participantsIDs, comment, link);
  }

  public Event(String name, String place, LocalDate fromDate, int intFromHours, int intFromMinutes,
      LocalDate toDate, int intToHours, int intToMinutes, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    int ID = -1;
    set(ID, name, place, fromDate, intFromHours, intFromMinutes, toDate, intToHours, intToMinutes, description,
        participantsIDs, comment, link);
  }

  public void set(int ID, String name, String place, LocalDate fromDate, int intFromHours, int intFromMinutes,
      LocalDate toDate, int intToHours, int intToMinutes, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    this.ID = ID;
    this.name = name;
    this.place = place;
    this.fromDate = fromDate;
    this.intFromHours = intFromHours;
    this.intFromMinutes = intFromMinutes;
    this.toDate = toDate;
    this.intToHours = intToHours;
    this.intToMinutes = intToMinutes;
    this.description = description;
    this.participantsIDs = participantsIDs;
    this.comment = comment;
    this.link = link;
    this.from = fromDate.atTime(intFromHours,intFromMinutes);
    this.to = toDate.atTime(intToHours,intToMinutes);
    System.out.println("Constructor" + toDate);
  }

  static public boolean VALIDATE_DATA(String name, String place, LocalDateTime from,LocalDateTime to)
  {
    if(!from.isAfter(LocalDateTime.now())){
      throw new RuntimeException("The event has to start in the future");
    }
    if (!to.isAfter(from)){
      throw new RuntimeException("The event has to end after it starts");
    }
    name = name.trim();
    if (!(name.length() > 0))
    {
      throw new RuntimeException("The event must have a name");
    }

    place = place.trim();
    if (!(place.length() > 0))
    {
      throw new RuntimeException("The event must have a location");
    }

    return true;
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

  public String getPlace()
  {
    return place;
  }

  public void setPlace(String place)
  {
    this.place = place;
  }

  public LocalDate getFromDate()
  {
    return fromDate;
  }

  public void setFromDate(LocalDate fromDate)
  {
    this.fromDate = fromDate;
  }

  public LocalDate getToDate()
  {
    return toDate;
  }

  public void setToDate(LocalDate toDate)
  {
    this.toDate = toDate;
  }

  public int getIntFromHours()
  {
    return intFromHours;
  }

  public void setIntFromHours(int intFromHours)
  {
    this.intFromHours = intFromHours;
  }

  public int getIntFromMinutes()
  {
    return intFromMinutes;
  }

  public void setIntFromMinutes(int intFromMinutes)
  {
    this.intFromMinutes = intFromMinutes;
  }

  public int getIntToHours()
  {
    return intToHours;
  }

  public void setIntToHours(int intToHours)
  {
    this.intToHours = intToHours;
  }

  public int getIntToMinutes()
  {
    return intToMinutes;
  }

  public void setIntToMinutes(int intToMinutes)
  {
    this.intToMinutes = intToMinutes;
  }

  public LocalDateTime getFrom()
  {
    return from;
  }

  public void setFrom(LocalDateTime from)
  {
    this.from = from;
  }

  public LocalDateTime getTo()
  {
    return to;
  }

  public void setTo(LocalDateTime to)
  {
    this.to = to;
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
        + place + '\'' + ", startingDate=" + from + ", endingDate="
        + to + ", description='" + description + '\''
        + ", participantsIDs=" + participantsIDs + ", comment='" + comment
        + '\'' + ", link='" + link + '\'' + '}';
  }

  public String getFromHours()
  {
    return "" + this.getIntFromHours() + "";
  }
  public String getFromMinutes()
  {
    return "" + this.getIntFromMinutes() + "";
  }
  public String getToHours()
  {
    return "" + this.getIntToHours() + "";
  }
  public String getToMinutes()
  {
    return "" + this.getIntToMinutes() + "";
  }
}
