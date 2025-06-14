package com.open.pix.domain.interfaces;

public interface PixType {

    String value();

    String type();

    void validate(String value);

    int maxLength();
}
