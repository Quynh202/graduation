package com.graduationproject.backend.backendwebsite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class VoucherModel {

    private Long voucherId;

    private String idCode;

    private BigDecimal discount;

    private Long quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private BigDecimal maxPriceOrder;

    private Long status;

}
