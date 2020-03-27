package xyz.lannt.redmine.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ConverterSetting {

  private int titleCols;
  private int desCols;
  private int codingCols;
  private int createUTCols;
  private int exeUTCols;
  private int fixbugCols;
  private int assignCols;
  private int startCols;
  private int endCols;
}
