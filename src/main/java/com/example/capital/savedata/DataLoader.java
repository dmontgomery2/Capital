package com.example.capital.savedata;

import com.example.capital.models.User;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

  public static List<User> loadUsers() {
    try {
      return FileReader.readFromFile().getUsers();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }

}
