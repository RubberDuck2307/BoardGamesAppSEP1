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
   * The thirteen-argument constructor calling the set method
   * @param ID the ID of the event
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param fromDate the date and time when the event starts
   * @param intFromHours the hour when the event starts
   * @param intFromMinutes the minutes when the event stars
   * @param toDate the date and the time when the event ends
   * @param intToHours the hour when the event ends
   * @param intToMinutes the minutes when the event ends
   * @param description the description of the event
   * @param participantsIDs the list with the IDs of the players attending the event
   * @param comment a custom comment
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
   * The twelve-argument constructor calling the set method. ID is set to -1.
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param fromDate the date and time when the event starts
   * @param intFromHours the hour when the event starts
   * @param intFromMinutes the minutes when the event stars
   * @param toDate the date and the time when the event ends
   * @param intToHours the hour when the event ends
   * @param intToMinutes the minutes when the event ends
   * @param description the description of the event
   * @param participantsIDs the list with the IDs of the players attending the event
   * @param comment a custom comment
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
   * The setter for all attributes
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param fromDate the date and time when the event starts
   * @param intFromHours the hour when the event starts
   * @param intFromMinutes the minutes when the event stars
   * @param toDate the date and the time when the event ends
   * @param intToHours the hour when the event ends
   * @param intToMinutes the minutes when the event ends
   * @param description the description of the event
   * @param participantsIDs the list with the IDs of the players attending the event
   * @param comment a custom comment
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
  }

  /**
   *
   * @param name the name of the event
   * @param place the place where the event takes place
   * @param from the date and time when the event starts
   * @param to the date when the event ends
   * @throws RuntimeException if from is not in the future or if to is not after from or if name is an empty string or if place is an empty string
   * @return true, if from is in the future and if to is after from and if name is not an empty string and if place is not an empty string
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
   * The getter for ID
   */
  public int getID()
  {
    return ID;
  }

  /**
   * The setter for ID
   */
  public void setID(int ID)
  {
    this.ID = ID;
  }

  /**
   * The getter for name
   */
  public String getName()
  {
    return name;
  }

  /**
   * The setter for name
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * The getter for place
   */
  public String getPlace()
  {
    return place;
  }
  /**
   * The setter for place
   */
  public void setPlace(String place)
  {
    this.place = place;
  }

  /**
   * The getter for fromDate
   */
  public LocalDate getFromDate()
  {
    return fromDate;
  }

  /**
   * The setter for fromDate
   */
  public void setFromDate(LocalDate fromDate)
  {
    this.fromDate = fromDate;
  }

  /**
   * The getter for toDate
   */
  public LocalDate getToDate()
  {
    return toDate;
  }

  /**
   * The setter for toDate
   */
  public void setToDate(LocalDate toDate)
  {
    this.toDate = toDate;
  }

  /**
   * The getter for intFromHours
   */
  public int getIntFromHours()
  {
    return intFromHours;
  }

  /**
   * The setter for intFromHours
   */

  public void setIntFromHours(int intFromHours)
  {
    this.intFromHours = intFromHours;
  }

  /**
   * The getter for intFromMinutes
   */

  public int getIntFromMinutes()
  {
    return intFromMinutes;
  }

  /**
   * The setter for intFromMinutes
   */

  public void setIntFromMinutes(int intFromMinutes)
  {
    this.intFromMinutes = intFromMinutes;
  }

  /**
   * The getter for intToHours
   */
  public int getIntToHours()
  {
    return intToHours;
  }

  /**
   * The setter for intToHours
   */
  public void setIntToHours(int intToHours)
  {
    this.intToHours = intToHours;
  }

  /**
   * The getter for intTomMinutes
   */
  public int getIntToMinutes()
  {
    return intToMinutes;
  }

  /**
   * The setter for intToMinutes
   */
  public void setIntToMinutes(int intToMinutes)
  {
    this.intToMinutes = intToMinutes;
  }

  /**
   * The getter for from
   */

  public LocalDateTime getFrom()
  {
    return from;
  }

  /**
   * The setter for from
   */
  public void setFrom(LocalDateTime from)
  {
    this.from = from;
  }

  /**
   * The getter for to
   */
  public LocalDateTime getTo()
  {
    return to;
  }

  /**
   * The getter for to
   */
  public void setTo(LocalDateTime to)
  {
    this.to = to;
  }

  /**
   * The getter for description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * The setter for description
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * The getter for participantsIDs
   */
  public ArrayList<Integer> getParticipantsIDs()
  {
    return participantsIDs;
  }

  /**
   * The setter for participantsIDs
   */

  public void setParticipantsIDs(ArrayList<Integer> participantsIDs)
  {
    this.participantsIDs = participantsIDs;
  }

  /**
   * The getter for comment
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * The setter for comment
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  /**
   * The getter for link
   */
  public String getLink()
  {
    return link;
  }

  /**
   * The setter for link
   */
  public void setLink(String link)
  {
    this.link = link;
  }

  /**
   * Deletes the given ID from the array list participantsID
   * @param ID the ID of the player participating in the event
   */
  public void deleteParticipantByID(int ID){
    participantsIDs.remove(Integer.valueOf(ID));
  }

  /**
   *
   * @return the values of all the attributes as string
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
