package ua.epam.mishchenko.ticketbooking.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.mishchenko.ticketbooking.facade.impl.BookingFacadeImpl;
import ua.epam.mishchenko.ticketbooking.model.Category;
import ua.epam.mishchenko.ticketbooking.model.Event;
import ua.epam.mishchenko.ticketbooking.model.Ticket;
import ua.epam.mishchenko.ticketbooking.model.User;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TicketsControllerTest {

    private TicketsController ticketsController;

    @Mock
    private BookingFacadeImpl bookingFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        ticketsController = new TicketsController(bookingFacade);
    }

    @Test
    public void bookTicketWithCorrectParametersShouldReturnModelAndViewWithBookedTicket() {
        Ticket ticket = new Ticket();

        when(bookingFacade.bookTicket(anyString(), anyString(), anyInt(), any())).thenReturn(ticket);

        ModelAndView actualModelAndView = ticketsController.bookTicket("1L", "1L", 1, Category.BAR);

        verify(bookingFacade, times(1)).bookTicket(anyString(), anyString(), anyInt(), any());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("ticket", actualModelAndView.getViewName());
        assertTrue(actualModelMap.containsAttribute("ticket"));
        assertFalse(actualModelMap.containsAttribute("message"));
    }

    @Test
    public void bookTicketWithCorrectParametersShouldReturnModelAndViewWithMessage() {
        when(bookingFacade.bookTicket(anyString(), anyString(), anyInt(), any())).thenReturn(null);

        ModelAndView actualModelAndView = ticketsController.bookTicket("1L", "1L", 1, Category.BAR);

        verify(bookingFacade, times(1)).bookTicket(anyString(), anyString(), anyInt(), any());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("ticket", actualModelAndView.getViewName());
        assertFalse(actualModelMap.containsAttribute("ticket"));
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("Can not to book a ticket", actualModelMap.getAttribute("message"));
    }

    @Test
    public void showTicketsByUserWithNotExistingUserIdShouldReturnModelAndViewWithMessage() {
        when(bookingFacade.getUserById(anyString())).thenReturn(null);

        ModelAndView actualModelAndView = ticketsController.showTicketsByUser("1L", 1, 1);

        verify(bookingFacade, times(1)).getUserById(anyString());
        verify(bookingFacade, times(0)).getBookedTickets(any(User.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertFalse(actualModelMap.containsAttribute("tickets"));
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("Can not to find a user by id: 1L", actualModelMap.getAttribute("message"));
    }

    @Test
    public void showTicketsByUserWithExistingUserIdShouldReturnModelAndViewWithMessage() {
        when(bookingFacade.getUserById(anyString())).thenReturn(new User());
        when(bookingFacade.getBookedTickets(any(User.class), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        ModelAndView actualModelAndView = ticketsController.showTicketsByUser("1L", 1, 1);

        verify(bookingFacade, times(1)).getUserById(anyString());
        verify(bookingFacade, times(1)).getBookedTickets(any(User.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertFalse(actualModelMap.containsAttribute("tickets"));
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("Can not to find the tickets by user with id: 1L", actualModelMap.getAttribute("message"));
    }

    @Test
    public void showTicketsByUserWithExistingUserIdShouldReturnModelAndViewWithListOfBookedTickets() {
        when(bookingFacade.getUserById(anyString())).thenReturn(new User());
        when(bookingFacade.getBookedTickets(any(User.class), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(new Ticket()));

        ModelAndView actualModelAndView = ticketsController.showTicketsByUser("1L", 1, 1);

        verify(bookingFacade, times(1)).getUserById(anyString());
        verify(bookingFacade, times(1)).getBookedTickets(any(User.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertTrue(actualModelMap.containsAttribute("tickets"));
        assertFalse(actualModelMap.containsAttribute("message"));
    }

    @Test
    public void showTicketsByEventWithNotExistingEventIdShouldReturnModelAndViewWithMessage() {
        when(bookingFacade.getEventById(anyString())).thenReturn(null);

        ModelAndView actualModelAndView = ticketsController.showTicketsByEvent("1L", 1, 1);

        verify(bookingFacade, times(1)).getEventById(anyString());
        verify(bookingFacade, times(0)).getBookedTickets(any(Event.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertFalse(actualModelMap.containsAttribute("tickets"));
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("Can not to find an event by id: 1L", actualModelMap.getAttribute("message"));
    }

    @Test
    public void showTicketsByEventWithExistingEventIdShouldReturnModelAndViewWithMessage() {
        when(bookingFacade.getEventById(anyString())).thenReturn(new Event());
        when(bookingFacade.getBookedTickets(any(Event.class), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        ModelAndView actualModelAndView = ticketsController.showTicketsByEvent("1L", 1, 1);

        verify(bookingFacade, times(1)).getEventById(anyString());
        verify(bookingFacade, times(1)).getBookedTickets(any(Event.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertFalse(actualModelMap.containsAttribute("tickets"));
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("Can not to find the tickets by event with id: 1L", actualModelMap.getAttribute("message"));
    }

    @Test
    public void showTicketsByEventWithExistingEventIdShouldReturnModelAndViewWithListOfBookedTickets() {
        when(bookingFacade.getEventById(anyString())).thenReturn(new Event());
        when(bookingFacade.getBookedTickets(any(Event.class), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(new Ticket()));

        ModelAndView actualModelAndView = ticketsController.showTicketsByEvent("1L", 1, 1);

        verify(bookingFacade, times(1)).getEventById(anyString());
        verify(bookingFacade, times(1)).getBookedTickets(any(Event.class), anyInt(), anyInt());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("tickets", actualModelAndView.getViewName());
        assertTrue(actualModelMap.containsAttribute("tickets"));
        assertFalse(actualModelMap.containsAttribute("message"));
    }

    @Test
    public void cancelTicketWithExistingIdShouldReturnModelAndViewWithPositiveMessage() {
        when(bookingFacade.cancelTicket(anyString())).thenReturn(true);

        ModelAndView actualModelAndView = ticketsController.cancelTicket("1L");

        verify(bookingFacade, times(1)).cancelTicket(anyString());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("ticket", actualModelAndView.getViewName());
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("The ticket with id: 1L successfully canceled", actualModelMap.getAttribute("message"));
    }

    @Test
    public void cancelTicketWithNotExistingIdShouldReturnModelAndViewWithNegativeMessage() {
        when(bookingFacade.cancelTicket(anyString())).thenReturn(false);

        ModelAndView actualModelAndView = ticketsController.cancelTicket("1L");

        verify(bookingFacade, times(1)).cancelTicket(anyString());

        ModelMap actualModelMap = actualModelAndView.getModelMap();

        assertEquals("ticket", actualModelAndView.getViewName());
        assertTrue(actualModelMap.containsAttribute("message"));
        assertEquals("The ticket with id: 1L not canceled", actualModelMap.getAttribute("message"));
    }
}
