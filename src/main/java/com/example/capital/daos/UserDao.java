package com.example.capital.daos;

import static com.example.capital.savedata.DataLoader.loadUsers;

import com.example.capital.models.User;
import java.util.List;
import java.util.Optional;

public class UserDao {

  private static final List<User> USERS = loadUsers();

  private static User currentUser;

  public static void addUser(User user) {
    USERS.add(user);
  }

  public static Optional<User> getByUsernameAndPassword(String username, String password) {
    return USERS.stream()
        .filter(user -> user.matchesUsernameAndPassword(username, password))
        .findFirst();
  }

  public static Optional<User> getByUsername(String username) {
    return USERS.stream()
        .filter(user -> user.matchesUsername(username))
        .findFirst();
  }

  public static User getCurrentUser() {
    return currentUser;
  }

  public static void setCurrentUser(User user) {
    currentUser = user;
  }

  public static List<User> getAllUsers() {
    return USERS;
  }


}
