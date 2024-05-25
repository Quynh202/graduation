package com.graduationproject.backend.backendwebsite.controller;

import com.graduationproject.backend.backendwebsite.forms.LoginForm;
import com.graduationproject.backend.backendwebsite.forms.SignUpForm;
import com.graduationproject.backend.backendwebsite.helper.AuthHelper;
import com.graduationproject.backend.backendwebsite.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthHelper authHelper;

  /**
   * Login with information from UI.
   *
   * @param loginRequest loginRequest
   * @return response entity.
   */
  @PostMapping("/sign-in")
  public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginForm loginRequest) {

    // $2a$10$TQwKVRH05PCXNdVZqByT/OU.QWsdOa1aFMYOvauZRrcvH2Zr9PK1y
    // 12345678
    JwtResponse jwtResponse = authHelper.login(loginRequest);

    return ResponseEntity.ok(jwtResponse);
  }

  /**
   * Register account.
   *
   * @param signUpForm signUpForm
   * @return response entity.
   */
  @PostMapping("/sign-up")
  public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpForm) {
    return authHelper.registerUser(signUpForm);
  }

}
