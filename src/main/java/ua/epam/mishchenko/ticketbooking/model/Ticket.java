package ua.epam.mishchenko.ticketbooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * The type Ticket.
 */
@Document("tickets")
public class Ticket {

    /**
     * The Id.
     */
    @Id
    private String id;

    /**
     * The User entity.
     */
    private User user;

    private Event event;

    /**
     * The Place.
     */
    private Integer place;

    /**
     * The Category.
     */
    private Category category;

    /**
     * Instantiates a new Ticket.
     */
    public Ticket() {
    }

    /**
     * Instantiates a new Ticket.
     *
     * @param id       the id
     * @param user     the user entity
     * @param event    the event entity
     * @param place    the place
     * @param category the category
     */
    public Ticket(String id, User user, Event event, int place, Category category) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.place = place;
        this.category = category;
    }

    /**
     * Instantiates a new Ticket.
     *
     * @param user     the user entity
     * @param event    the event entity
     * @param place    the place
     * @param category the category
     */
    public Ticket(User user, Event event, int place, Category category) {
        this.user = user;
        this.event = event;
        this.place = place;
        this.category = category;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets event entity.
     *
     * @return the event entity
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets event entity.
     *
     * @param event the event id
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gets user entity.
     *
     * @return the user entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user entity.
     *
     * @param user the user entity
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets place.
     *
     * @return the place
     */
    public int getPlace() {
        return place;
    }

    /**
     * Sets place.
     *
     * @param place the place
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(user, ticket.user) && Objects.equals(event, ticket.event) && Objects.equals(place, ticket.place) && category == ticket.category;
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    public int hashCode() {
        return Objects.hash(id, user, event, place, category);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString() {
        return "{" +
                "'id' : " + id +
                ", 'userId' : " + user.getId() +
                ", 'eventId' : " + event.getId() +
                ", 'place' : " + place +
                ", 'category' : '" + category +
                "'}";
    }
}
