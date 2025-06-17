package com.open.pix.domain.factory;

import com.open.pix.domain.exceptions.PixTypeException;
import com.open.pix.domain.interfaces.PixType;

import java.util.Map;
import java.util.function.Function;

public final class PixTypeFactory {

    private final Map<String, Function<String, PixType>> PIX_TYPE_MAP;

    public PixTypeFactory(Map<String, Function<String, PixType>> PIX_TYPE_MAP) {
        this.PIX_TYPE_MAP = PIX_TYPE_MAP;
    }

    public PixType newPixType(String type, String value) {
        if (type == null) {
            throw new PixTypeException("Pix type cannot be null");
        }
        String key = type.trim().toLowerCase();
        Function<String, PixType> constructor = PIX_TYPE_MAP.get(key);
        if (constructor == null) {
            throw new PixTypeException("Invalid type of pix key: "
                    + type + ". Permitted values : " + PIX_TYPE_MAP.keySet());
        }
        return constructor.apply(value);
    }
}
