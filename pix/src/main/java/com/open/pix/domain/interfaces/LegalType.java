package com.open.pix.domain.interfaces;

import java.util.List;

public interface LegalType {

    boolean supports(String pixType);

    String value();

    LegalType resolve();

    int limit();

    String getLimitErrorMessage();

    String getBlockErrorMessage();

    List<String> blocks();
}
