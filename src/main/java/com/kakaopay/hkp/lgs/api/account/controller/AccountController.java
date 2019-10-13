package com.kakaopay.hkp.lgs.api.account.controller;

import com.kakaopay.hkp.lgs.api.account.domain.dto.JWTToken;
import com.kakaopay.hkp.lgs.api.account.domain.dto.UserDto;
import com.kakaopay.hkp.lgs.api.account.service.AccountService;
import com.kakaopay.hkp.lgs.base.controller.BaseApiController;
import com.kakaopay.hkp.lgs.security.jwt.JWTFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "AccountController : 계정생성, 로그인, 토큰 재발급 API")
@RestController
public class AccountController extends BaseApiController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("${url.api.account.signup}")
    @ApiOperation(value="계정 등록 API", notes="입력으로 ID, PW 받아 내부 DB에 계정 저장하고 토큰 생성하여 출력하는 API")
    public ResponseEntity<JWTToken> signUp(
            @ApiParam(name = "user", value = "계정생성 아이디와 비밀번호", required = true)
            @RequestBody UserDto userDto) {

        String jwt = accountService.signUp(userDto);

        return new ResponseEntity<>(new JWTToken(jwt), createAuthHeader(jwt), HttpStatus.CREATED);
    }

    @PostMapping("${url.api.account.signin}")
    @ApiOperation(value="로그인 API", notes="입력으로 생성된 계정으로 로그인 요청하면 토큰을 발급하는 API")
    public ResponseEntity<JWTToken> signIn(
            @ApiParam(name = "user", value = "로그인 아이디와 비밀번호", required = true)
            @RequestBody UserDto userDto) {

        String jwt = accountService.signIn(userDto);
        return new ResponseEntity<>(new JWTToken(jwt), createAuthHeader(jwt), HttpStatus.OK);
    }

    @PostMapping("${url.api.account.refresh}")
    @ApiOperation(value="토큰 갱신 API", notes="기존에 발급받은 토큰을 Authorization 헤더에 입력 요청하면 토큰을 재발급하는 API.")
    public ResponseEntity<JWTToken> refreshToken(
            @RequestHeader("Authorization") String token ) {

        String jwt = accountService.refresh(token);
        return new ResponseEntity<>(new JWTToken(jwt), createAuthHeader(jwt), HttpStatus.CREATED);
    }

    private HttpHeaders createAuthHeader(String jwt) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, JWTFilter.TOKEN_BEARER + jwt);
        return httpHeaders;
    }


}
