package com.graduationproject.backend.backendwebsite.controller;

import com.graduationproject.backend.backendwebsite.entity.VoucherEntity;
import com.graduationproject.backend.backendwebsite.model.VoucherModel;
import com.graduationproject.backend.backendwebsite.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/voucher")
public class VoucherController {

    @Autowired
    VoucherService voucherService;

    @PostMapping(value = "/register")
    public ResponseEntity<VoucherEntity> register(@RequestBody VoucherModel voucherModel)
            throws IOException {
        VoucherEntity voucherEntity = null;
        try {
            voucherEntity = voucherService.insert(voucherModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(voucherEntity, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<VoucherEntity> update(@RequestBody VoucherModel voucherModel)
            throws IOException {
        VoucherEntity voucherEntity = null;
        try {
            voucherEntity = voucherService.update(voucherModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ResponseEntity<>(voucherEntity, HttpStatus.OK);
    }

    @GetMapping("/get")
    public List<VoucherEntity> getAll(){

        return voucherService.getAll();
    }

    @DeleteMapping("/delete/{voucherId}")
    public  ResponseEntity<Map<String, Boolean>>  delete(@PathVariable String voucherId){
        Boolean action = voucherService.deleteByPrimaryKey(Long.parseLong(voucherId));
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", action);
        return ResponseEntity.ok(response);
    }

}
