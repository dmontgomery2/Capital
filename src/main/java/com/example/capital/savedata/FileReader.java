package com.example.capital.savedata;

import static com.example.capital.common.Const.SAVE_FILE_NAME;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

class FileReader {

  public static SaveData readFromFile() throws Exception {
    return (SaveData) new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME)).readObject();
  }

}
