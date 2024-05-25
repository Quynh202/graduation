package com.graduationproject.backend.backendwebsite.dto;

import java.util.Date;

public interface ICategory {
    Long getCategoryId();

    String getCategoryDescription();

    String getCategoryName();

    String getCategorySeo();

    Date getCreatedDate();

    Date getUpdatedDate();

    String getCategoryStatus();
}
