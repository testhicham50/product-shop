package com.alten.shop.utils;

import com.alten.shop.entities.Product;
import com.alten.shop.exceptions.FieldNotFoundException;

import java.lang.reflect.Field;
import java.util.Map;

public class ProductUpdateUtil {

    /**
     * Updates non-null fields of an existing object with the values from the provided map.
     *
     * @param existingObj The existing object to be updated.
     * @param fields      A map containing field names and their corresponding values for update.
     * @throws IllegalAccessException If there is an issue accessing the fields during the update process.
     */
    public static void updateNonNullFields(Object existingObj, Map<String, Object> fields) throws IllegalAccessException {
        Class<?> existingObjClass = existingObj.getClass();

        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            Object newValue = entry.getValue();

            try {
                // Check if the field exists in the entity
                Field field = existingObjClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                // Update the field if the new value is not null
                if (newValue != null) {
                    field.set(existingObj, newValue);
                }
            } catch (NoSuchFieldException e) {
                // Handle the case where the field does not exist in the entity
                throw new FieldNotFoundException("Field '" + fieldName + "' not found");
            }
        }
    }

    /**
     * Validates that the provided fields are valid for the Product entity.
     *
     * @param fields A map containing field names and their corresponding values for update.
     * @throws FieldNotFoundException If any of the provided fields are not valid for the Product entity.
     */
    public static void validateFields(Map<String, Object> fields) {
        for (String fieldName : fields.keySet()) {
            try {
                // Check if the field exists in the Product entity
                Product.class.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Throw an exception if the field is not valid
                throw new FieldNotFoundException("Field '" + fieldName + "' not found");
            }
        }
    }
}
