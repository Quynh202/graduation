package com.graduationproject.backend.backendwebsite.service;

import com.graduationproject.backend.backendwebsite.entity.VoucherEntity;
import com.graduationproject.backend.backendwebsite.model.VoucherModel;
import com.graduationproject.backend.backendwebsite.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VoucherService {

    @Autowired
    VoucherRepository voucherRepository;


    public List<VoucherEntity> getAll(){

        return voucherRepository.findAll();
    }

    public VoucherEntity insert(VoucherModel voucherModel) {
        VoucherEntity voucherEntity = toBuildVoucherInsert(voucherModel);
        return voucherRepository.save(voucherEntity);
    }

    public VoucherEntity update(VoucherModel voucherModel) {
        VoucherEntity voucherEntity = voucherRepository.findById(voucherModel.getVoucherId())
               .orElseThrow(() -> new IllegalArgumentException("Value is null"));
        voucherEntity.setQuantity(voucherModel.getQuantity());
        voucherEntity.setDiscount(voucherModel.getDiscount() );
        voucherEntity.setEndDate(voucherModel.getEndDate());
        voucherEntity.setStartDate(voucherModel.getStartDate());
        voucherEntity.setIdCode(voucherModel.getIdCode());
        voucherEntity.setMaxPriceOrder(voucherModel.getMaxPriceOrder());
        voucherEntity.setStatus(voucherModel.getStatus());
        return voucherRepository.save(voucherEntity);
    }


    public Boolean deleteByPrimaryKey(Long voucherId) {

        Optional<VoucherEntity> voucher =  voucherRepository.findById(voucherId);
        if (voucher.isPresent()) {
            voucherRepository.deleteById(voucherId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    private VoucherEntity toBuildVoucherInsert(VoucherModel voucherModel) {
        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setQuantity(voucherModel.getQuantity());
        voucherEntity.setDiscount(voucherModel.getDiscount() );
        voucherEntity.setEndDate(voucherModel.getEndDate());
        voucherEntity.setStartDate(voucherModel.getStartDate());
        voucherEntity.setIdCode(voucherModel.getIdCode());
        voucherEntity.setMaxPriceOrder(voucherModel.getMaxPriceOrder());
        voucherEntity.setStatus(voucherModel.getStatus());
        return voucherEntity;
    }


}
