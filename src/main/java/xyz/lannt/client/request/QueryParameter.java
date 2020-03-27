package xyz.lannt.client.request;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.ObjectUtils;

import xyz.lannt.client.exception.MarketClientException;

public interface QueryParameter {

  default String toQueryParam() throws IllegalAccessException {
    return getFields(getClass()).stream()
        .filter(e -> !ObjectUtils.isEmpty(getFieldValue(e))).map(e -> getParam(e))
        .reduce((v1, v2) -> String.join("&", v1, v2)).orElse("");
  }

  default String getParam(Field field) {
    if (field == null) {
      return "";
    }

    return LOWER_CAMEL.to(LOWER_UNDERSCORE, field.getName()) + "=" + getFieldValue(field);
  }

  default List<Field> getFields(Class<?> clazz) {
    Class<?> superClass = clazz.getSuperclass();
    if (superClass == null) {
      return Collections.emptyList();
    }

    List<Field> fields = new ArrayList<Field>();
    for (Field field : clazz.getDeclaredFields()) {
      fields.add(field);
    }

    fields.addAll(getFields(superClass));

    return fields;
  }

  default String getFieldValue(Field field) {

    field.setAccessible(true);
    Class<?> targetType = field.getType();
    Object fieldData;
    try {
      fieldData = field.get(this);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new MarketClientException(e);
    }
    if (fieldData == null) {
      return null;
    }

    if (targetType.isPrimitive()) {
      return String.valueOf(fieldData);
    }

    return fieldData.toString();
  }
}