package com.open.pix.domain.interfaces;

import java.util.List;

public interface LegalType {

    /**
     * Should check if the legal type supports the string passed
     */
    boolean supports(String pixType);

    /**
     * @return the string representation of the legal type.
     */
    String value();

    /**
     * @return the instance of legal type.
     */
    LegalType resolve();

    /**
     * @return the permitted limit of pix keys to the legal type
     */
    int limit();

    /**
     * @return the legal type error message for limit exceed.
     */
    String getLimitErrorMessage();

    /**
     * @return the legal type error message to blocked legal types
     */
    String getBlockErrorMessage();

    /**
     * @return the {@link List} of string representations of
     * another legal types blocked for that legal type.
     */
    List<String> blocks();
}
