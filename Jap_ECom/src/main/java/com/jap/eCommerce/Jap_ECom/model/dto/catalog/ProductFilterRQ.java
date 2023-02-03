package com.jap.eCommerce.Jap_ECom.model.dto.catalog;

import com.jap.eCommerce.Jap_ECom.constants.AppsConstants;

public class ProductFilterRQ {

    private String code;

    private String name;

    private AppsConstants.YesNo isEnabled;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppsConstants.YesNo getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(AppsConstants.YesNo isEnabled) {
        this.isEnabled = isEnabled;
    }
}
