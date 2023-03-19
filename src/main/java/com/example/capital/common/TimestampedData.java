package com.example.capital.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimestampedData<T> implements Serializable {
  private final T data;
  private final long timestamp;
}
