package com.example.capital.common;

import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Const {

  public static final String ZERO_TIME_STRING = "0h 0m 0s";

  public static final String SAVE_FILE_NAME = "save.sav";

  public static final ObservableList<String> SHOW_STATS_FOR_LIST = FXCollections.observableArrayList(
      TimePeriod.ALL_TIME.getText(),
      TimePeriod.TODAY.getText(),
      TimePeriod.THIS_WEEK.getText(),
      TimePeriod.THIS_MONTH.getText(),
      TimePeriod.PAST_3_MONTHS.getText(),
      TimePeriod.PAST_6_MONTHS.getText(),
      TimePeriod.THIS_YEAR.getText()
  );

}
