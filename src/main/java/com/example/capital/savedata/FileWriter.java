package com.example.capital.savedata;

import static com.example.capital.common.Const.SAVE_FILE_NAME;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class FileWriter {


  public static void writeToFile(SaveData saveData) {
    try {
      new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME)).writeObject(saveData);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
