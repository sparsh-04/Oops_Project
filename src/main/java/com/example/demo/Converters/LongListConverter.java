package com.example.demo.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LongListConverter implements AttributeConverter<List<Long>, String> {
  private static final String SPLIT_CHAR = ";";

  @Override
  public String convertToDatabaseColumn(List<Long> longList) {
    List<String> tempList = new ArrayList<String>(longList.size());
    for (Long item : longList) {
      tempList.add(item.toString());
    }
    return String.join(SPLIT_CHAR, tempList);
  }

  @Override
  public List<Long> convertToEntityAttribute(String string) {
    List<String> inList = Arrays.asList(string.split(SPLIT_CHAR));
    List<Long> outList = new ArrayList<Long>(inList.size());
    for (String item : inList) {
      if(item.equals("")){
        return outList;
      }
      outList.add(Long.valueOf(item));
    }
    return outList;
  }
}