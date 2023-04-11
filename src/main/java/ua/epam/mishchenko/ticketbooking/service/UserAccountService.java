package ua.epam.mishchenko.ticketbooking.service;

import ua.epam.mishchenko.ticketbooking.model.User;

import java.math.BigDecimal;

public interface UserAccountService {

    User refillAccount(String userId, BigDecimal money);
}
