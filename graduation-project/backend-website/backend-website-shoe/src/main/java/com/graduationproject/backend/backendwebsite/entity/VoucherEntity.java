package com.graduationproject.backend.backendwebsite.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_voucher")
public class VoucherEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id", nullable = false)
    private Long voucherId;

    @Column(name = "id_code")
    private String idCode;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "quantity")
    private Long quantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    @Column(name = "endDate")
    private Date endDate;

   @Column(name = "max_price_order")
    private BigDecimal maxPriceOrder;

    @Column(name = "status")
    private Long status;

}
