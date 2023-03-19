package com.example.capital.savedata;

import com.example.capital.daos.UserDao;

public class DataSaver {

  public static void saveData() {
    FileWriter.writeToFile(SaveData.builder().users(UserDao.getAllUsers()).build());
  }

}
