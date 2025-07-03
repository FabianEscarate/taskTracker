package utils;

import java.util.Date;

public class jsonExtension {

  private static String makeStringValue(Object value) {
    return String.format("\"%s\"", value);
  }

  public String toJson() {
    StringBuilder json = new StringBuilder();
    json.append("{");
    java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      try {
        json.append("\"").append(fields[i].getName()).append("\":");
        Object value = fields[i].get(this);
        if (value instanceof String || value instanceof Enum) {
          json.append(makeStringValue(value));
        }
        if (value instanceof Number) {
          json.append(value);
        }
        if (value instanceof Date) {
          json.append(makeStringValue(value.toString()));
        }
      } catch (IllegalAccessException e) {
        json.append("null");
      }
      if (i < fields.length - 1) {
        json.append(",");
      }
    }
    json.append("}");
    return json.toString();
  }
}