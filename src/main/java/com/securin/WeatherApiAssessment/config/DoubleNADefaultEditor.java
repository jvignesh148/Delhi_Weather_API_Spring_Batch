// src/main/java/com/securin/WeatherApiAssessment/config/DoubleNADefaultEditor.java
package com.securin.WeatherApiAssessment.config;

import java.beans.PropertyEditorSupport;

public class DoubleNADefaultEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        if (text == null || text.trim().isEmpty() ||
                "N/A".equalsIgnoreCase(text.trim()) ||
                "NA".equalsIgnoreCase(text.trim()) ||
                "-".equals(text.trim())) {
            setValue(null);  // or 0.0 if you prefer
        } else {
            try {
                setValue(Double.valueOf(text.trim()));
            } catch (NumberFormatException e) {
                setValue(null);
            }
        }
    }
}