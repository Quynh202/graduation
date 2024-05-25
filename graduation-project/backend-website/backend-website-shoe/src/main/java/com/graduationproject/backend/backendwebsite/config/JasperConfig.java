package com.graduationproject.backend.backendwebsite.config;

import java.io.InputStream;

public class JasperConfig {

  public void export() {
    InputStream orderReportStream = getClass().getResourceAsStream(
        "../resources/META-INF/Coffee_Landscape.jrxml");

  }

}
