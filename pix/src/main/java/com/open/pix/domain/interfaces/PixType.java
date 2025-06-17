package com.open.pix.domain.interfaces;

public interface PixType {

    /**
     * @return the pix key.
     */
    String value();

    /**
     * @return the pix type string representation
     */
    String type();

    /**
     * Should validate the pix type business rules
     */
    void validate(String value);

    /**
     * @return the int value that represents the max length of that type
     */
    int maxLength();
}
