package com.example.demo.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.example.demo.Model.CartItem;

@Converter
public class CartItemConverter implements AttributeConverter<List<CartItem>, String> {
  private static final String ITEM_SPLIT_CHAR = ";";
  private static final String DATA_SPLIT_CHAR = ":";

  @Override
  public String convertToDatabaseColumn(List<CartItem> cartItemList) {
    if (cartItemList == null)
      return "";
    List<String> tempList = new ArrayList<String>(cartItemList.size());
    for (CartItem item : cartItemList) {
      tempList.add(
          String.join(DATA_SPLIT_CHAR, String.format("%L", item.getId()), String.format("%d", item.getQuantity())));
    }

    return tempList != null ? String.join(ITEM_SPLIT_CHAR, tempList) : "";
  }

  @Override
  public List<CartItem> convertToEntityAttribute(String string) {
    List<CartItem> outList = new ArrayList<CartItem>();
    List<String> tempList = Arrays.asList(string.split(ITEM_SPLIT_CHAR));
    for (String item : tempList) {
      outList.add(new CartItem(item.split(DATA_SPLIT_CHAR)));
    }
    return outList;
  }
}
