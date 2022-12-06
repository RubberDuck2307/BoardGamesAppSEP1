package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class representing an event
 * @author Anna Andrlova, Christos Artemisios, Alex Bolfa, Jan Metela
 * @version 1.0 - November 2022
 */

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

  /**
   * Thirteen-argument constructor calling the set method
   * @param ID the ID of the event
   * @param name the name of the event
   * @param place the place where is the event going to happen
   * @param fromDate the date and the time of the event start
   * @param intFromHours the hour of the event start
   * @param intFromMinutes the minutes of the event star
   * @param toDate the date and the time of the event end
   * @param intToHours the hour of the event end
   * @param intToMinutes the minutes of the event end
   * @param description the description of the event
   * @param participantsIDs the list with IDs of players attends the event
   * @param comment the custom comment
   * @param link the link to a webpage where more info about the event can be found
   */
  public Event(int ID, String name, String place, LocalDate fromDate, int intFromHours, int intFromMinutes,
      LocalDate toDate, int intToHours, int intToMinutes, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    set(ID, name, place, fromDate, intFromHours, intFromMinutes, toDate, intToHours, intToMinutes, description,
        participantsIDs, comment, link);
  }

  /**
   * Twelve-argument constructor calling the set method. ID is set to -1.
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param fromDate the date and the time of the event start
   * @param intFromHours the hour of the event start
   * @param intFromMinutes the minutes of the event star
   * @param toDate the date and the time of the event end
   * @param intToHours the hour of the event end
   * @param intToMinutes the minutes of the event end
   * @param description the description of the event
   * @param participantsIDs the list with IDs of players attends the event
   * @param comment the custom comment
   * @param link the link to a webpage where more info about the event can be found
   */
  public Event(String name, String place, LocalDate fromDate, int intFromHours, int intFromMinutes,
      LocalDate toDate, int intToHours, int intToMinutes, String description, ArrayList<Integer> participantsIDs,
      String comment, String link)
  {
    int ID = -1;
    set(ID, name, place, fromDate, intFromHours, intFromMinutes, toDate, intToHours, intToMinutes, description,
        participantsIDs, comment, link);
  }

  /**
   * Setter for all attributes
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param fromDate the date and the time of the event start
   * @param intFromHours the hour of the event start
   * @param intFromMinutes the minutes of the event star
   * @param toDate the date and the time of the event end
   * @param intToHours the hour of the event end
   * @param intToMinutes the minutes of the event end
   * @param description the description of the event
   * @param participantsIDs the list with IDs of players attends the event
   * @param comment the custom comment
   * @param link the link to a webpage where more info about the event can be found
   */
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

  /**
   *
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param from the date and time of the event start
   * @param to the date of the event end
   * @throws RuntimeException if from is not in the future or if to is not after from or if name is empty string or if place is empty string
   * @return true, if from is in the future and if to is after from and if name is not empty string and if place is not empty string
   */
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

  /**
   * Getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * Setter for ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * Getter for name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Setter for name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Getter for place
   */
  public String getPlace()
  {
    return place;
  }
  /**
   * Setter for place
   */
  public void setPlace(String place)
  {
    this.place = place;
  }

  /**
   * Getter for fromDate
   */
  public LocalDate getFromDate()
  {
    return fromDate;
  }

  /**
   * Setter for fromDate
   */
  public void setFromDate(LocalDate fromDate)
  {
    this.fromDate = fromDate;
  }

  /**
   * Getter for toDate
   */
  public LocalDate getToDate()
  {
    return toDate;
  }

  /**
   * Setter for toDate
   */
  public void setToDate(LocalDate toDate)
  {
    this.toDate = toDate;
  }

  /**
   * Getter for intFromHours
   */
  public int getIntFromHours()
  {
    return intFromHours;
  }

  /**
   * Setter for intFromHours
   */

  public void setIntFromHours(int intFromHours)
  {
    this.intFromHours = intFromHours;
  }

  /**
   * Getter for intFromMinutes
   */

  public int getIntFromMinutes()
  {
    return intFromMinutes;
  }

  /**
   * Setter for intFromMinutes
   */

  public void setIntFromMinutes(int intFromMinutes)
  {
    this.intFromMinutes = intFromMinutes;
  }

  /**
   * Getter for intToHours
   */
  public int getIntToHours()
  {
    return intToHours;
  }

  /**
   * Setter for intToHours
   */
  public void setIntToHours(int intToHours)
  {
    this.intToHours = intToHours;
  }

  /**
   * Getter for intTomMinutes
   */
  public int getIntToMinutes()
  {
    return intToMinutes;
  }

  /**
   * Setter for intToMinutes
   */
  public void setIntToMinutes(int intToMinutes)
  {
    this.intToMinutes = intToMinutes;
  }

  /**
   * Getter for from
   */

  public LocalDateTime getFrom()
  {
    return from;
  }

  /**
   * Setter for from
   */
  public void setFrom(LocalDateTime from)
  {
    this.from = from;
  }

  /**
   * Getter for to
   */
  public LocalDateTime getTo()
  {
    return to;
  }

  /**
   * Getter for to
   */
  public void setTo(LocalDateTime to)
  {
    this.to = to;
  }

  /**
   * Getter for description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Setter for description
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Getter for participantsIDs
   */
  public ArrayList<Integer> getParticipantsIDs()
  {
    return participantsIDs;
  }

  /**
   * Setter for participantsIDs
   */

  public void setParticipantsIDs(ArrayList<Integer> participantsIDs)
  {
    this.participantsIDs = participantsIDs;
  }

  /**
   * Getter for comment
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * Setter for comment
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * Getter for link
   */
  public String getLink()
  {
    return link;
  }

  /**
   * Setter for link
   */
  public void setLink(String link)
  {
    this.link = link;
  }

  /**
   * Deletes the given ID from participantsID
   * @param ID ID of the player who participates the event
   */
  public void deleteParticipantByID(int ID){
    participantsIDs.remove(ID);
  }

  /**
   *
   * @return values of all the attributes as string
   */

  @Override public String toString()
  {
    return "Model.Event{" + "ID=" + ID + ", name='" + name + '\'' + ", location='"
        + place + '\'' + ", startingDate=" + from + ", endingDate="
        + to + ", description='" + description + '\''
        + ", participantsIDs=" + participantsIDs + ", comment='" + comment
        + '\'' + ", link='" + link + '\'' + '}';
  }

  /**
   *
   * @return intFromHours as string
   */
  public String getFromHours()
  {
    return "" + this.getIntFromHours() + "";
  }

  /**
   *
   * @return intFromMinutes as string
   */
  public String getFromMinutes()
  {
    return "" + this.getIntFromMinutes() + "";
  }

  /**
   *
   * @return intToHours as string
   */
  public String getToHours()
  {
    return "" + this.getIntToHours() + "";
  }

  /**
   *
   * @return intToMinutes as string
   */
  public String getToMinutes()
  {
    return "" + this.getIntToMinutes() + "";
  }
}
