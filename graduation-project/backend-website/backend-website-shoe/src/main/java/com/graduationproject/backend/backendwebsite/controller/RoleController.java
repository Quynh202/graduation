package com.graduationproject.backend.backendwebsite.controller;

import com.graduationproject.backend.backendwebsite.entity.RoleEntity;
import com.graduationproject.backend.backendwebsite.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {

  @Autowired
  UserService userService;

  @GetMapping("/get")
  public List<RoleEntity> getUserById() {
    return userService.getAllRole();
  }

}
