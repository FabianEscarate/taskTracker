package utils;

public class jsonExtension {

  public  String toJson() {
    StringBuilder json = new StringBuilder();
    json.append("{");
    java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      try {
        json.append("\"").append(fields[i].getName()).append("\":");
        Object value = fields[i].get(this);
        if (value instanceof String) {
          json.append("\"").append(value).append("\"");
        } else {
          json.append(value);
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