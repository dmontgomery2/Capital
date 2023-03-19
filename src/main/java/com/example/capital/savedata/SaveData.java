package com.example.capital.savedata;

import com.example.capital.models.User;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class SaveData implements Serializable {

  private final List<User> users;
}
