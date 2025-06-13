package com.open.pix.domain.interfaces;

public interface PixType {

    String value();

    void validate(String value);

    int maxLength();
}
