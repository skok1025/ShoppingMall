##응답 코드
-----------

<a href='https://developer.mozilla.org/ko/docs/Web/HTTP/Status'>참고문헌</a>

###성공

-	**200 OK** : 요청이 성공적으로 되었습니다.
-	**201 Created** : 요청이 성공적이었으며 그 결과로 새로운 리소스가 생성되었습니다. 이 응답은 일반적으로 POST 요청 또는 일부 PUT 요청 이후에 따라옵니다.<API 일정 리스트>

###실패

-	**400 Bad Request**: 이 응답은 잘못된 문법으로 인하여 서버가 요청을 이해할 수 없음을 의미합니다.
-	**403 Forbidden** : 클라이언트는 콘텐츠에 접근할 권리를 가지고 있지 않습니다.
-	**404 Not Found** : 서버는 요청받은 리소스를 찾을 수 없습니다.

<hr>

<API List 일정표>
=================

| ﻿시나리오             | 분류                                   | API                                        | 방식                       | URL                           | 예상일정 | 예상소요시간       | 개발일정     | 개발시간               | 코드 링크                                 | 비고                                                               |
|----------------------|----------------------------------------|--------------------------------------------|----------------------------|-------------------------------|----------|--------------------|--------------|------------------------|-------------------------------------------|--------------------------------------------------------------------|
| 사용자 시나리오      | 회원계정(Customer-Controller)          | 회원가입 API (완)                          | POST                       | /api/customer/account         | 7월 16일 | 2H                 | 7월 16일     | 1.5H                   | [링크](#1-회원가입-API)                   |                                                                    |
|                      |                                        | 회원정보수정 API (완)                      | PUT                        | /api/customer/account         | 7월 16일 | 2H                 | 7월 16일     | 1H                     | [링크](#2-회원정보수정-API)               |                                                                    |
|                      |                                        | 회원비밀번호 수정 API (완)                 | PUT                        | /api/customer/account/pw      | 7월 16일 | 1H                 | 7월 16일     | 1H                     | [링크](#3-회원비밀번호-수정-API)          |                                                                    |
|                      |                                        | 회원탈퇴 API (완)                          | DELETE                     | /api/customer/account         | 7월 16일 | 2H                 | 7월 16일     | 1H                     | [링크](#4-회원탈퇴-API)                   |                                                                    |
|                      |                                        | 로그인 API -(완)                           | POST                       | /api/customer/auth            | 7월 16일 | 2H                 | 7월 16일     | 1H                     | [링크](#5-로그인-API)                     |                                                                    |
|                      |                                        | 아이디 중복확인 API (완)                   | GET                        | /api/customer/checkid         | 7월 16일 | 1H                 | 7월 16일     | 0.5H                   | [링크](#6-아이디-중복확인-API)            |                                                                    |
|                      |                                        | 회원 이용약관 등록 API                     | POST                       | /api/customer/terms           | !        | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 회원 이용약관 조회 API                     | GET                        | /api/customer/terms           | !        | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 회원 이용약관 삭제 API                     | DELETE                     | /api/customer/terms           | !        | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        |                                            |                            |                               |          | 2H                 |              |                        |                                           |                                                                    |
|                      | 장바구니(Basket-Controller)            | 장바구니추가 API -                         | POST                       | /api/basket/add/              | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 장바구니조회 API -                         | GET                        | /api/basket/list              | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 장바구니수정 API -                         | PUT                        | /api/basket/modify            | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 장바구니상품삭제 API -                     | DELETE                     | /api/basket/remove/{basketNo} | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 상품(Goods-Controller)                 | 상품검색 API -                             | GET                        | /api/goods/search             | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 상품상세조회API -                          | GET                        | /api/goods/view               | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 주문(Order-Controller)                 | 주문 API                                   | POST                       | /api/order/                   | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 주문조회 API                               | GET                        | /api/order/                   | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 주문취소 API                               | DELETE                     | /api/order/                   | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 주문 교환신청 API                          | POST                       | /api/order/change             | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 주문 교환신청내역조회 API                  | GET                        | /api/order/change             | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 주문 교환신청 취소 API                     | DELETE                     | /api/order/change             | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
| 관리자 시나리오      | 관리자 상품 관리(Admin-Controller)     | 관리자 상품등록 API -                      | POST                       | /api/admin/goods              | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품조회 API -                      | GET                        | /api/admin/goodslist          | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품수정 API -                      | PUT                        | /api/admin/goods              | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품삭제 API -                      | DELETE                     | /api/admin/goods              | 7월 19일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 보류- 상품등록시 함께?                 | 관리자 상품이미지등록 API                  | POST                       | /api/admin/goods/image        | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품이미지수정 API                  | PUT                        | /api/admin/goods/image        | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품이미지삭제 API                  | DELETE                     | /api/admin/goods/image        | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품상세옵션 등록 API               | POST                       | /api/admin/goods/option       | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품상세옵션 수정 API               | PUT                        | /api/admin/goods/option       | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품상세옵션 삭제 API               | DELETE                     | /api/admin/goods/option       | 7월 22일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 관리자 카테고리 관리(Admin-Controller) | 관리자 1차 카테고리 등록 API (완)          | POST                       | /api/admin/bigcategory        | 7월 17일 | 1H                 | 7월 17일     | 1.5H                   | [링크](#7-관리자-1차-카테고리-등록-API)   | mapper id duplicate 문제 해결                                      |
|                      |                                        | 관리자 1차 카테고리 수정 API (완)          | PUT                        | /api/admin/bigcategory        | 7월 17일 | 0.5H               | 7월 17일     | 0.5H                   | [링크](#8-관리자-1차-카테고리-수정-API)   |                                                                    |
|                      |                                        | 관리자 1차 카테고리 삭제 API (완)\_        | DELETE                     | /api/admin/bigcategory        | 7월 17일 | 1H                 | 7월 17일     | 2H                     | [링크](#9-관리자-1차-카테고리-삭제-API)   | 2차카테고리 등 외래키 제거에 따른 프로시저 고민                    |
|                      |                                        | 관리자 2차 카테고리 등록 API (완)          | POST                       | /api/admin/smallcategory      | 7월 17일 | 0.5H               | 7월 17일     | 0.5H                   | [링크](#10-관리자-2차-카테고리-등록-API)  | 최대 길이 10 Valid                                                 |
|                      |                                        | 관리자 2차 카테고리 수정 API (완)          | PUT                        | /api/admin/smallcategory      | 7월 17일 | 0.5H               | 7월 17일     | 0.5H                   | [링크](#11-관리자-2차-카테고리-수정-API)  |                                                                    |
|                      |                                        | 관리자 2차 카테고리 삭제 API (완)          | DELETE                     | /api/admin/smallcategory      | 7월 18일 | 0.6H               | 7월 18일     | 0.5H                   | [링크](#12-관리자-2차-카테고리-삭제-API)  |                                                                    |
|                      |                                        | 관리자 카테고리 목록 조회 API (완)         | GET                        | /api/admin/category/list      | 7월 18일 | 2H                 | 7월 18일     | 1H                     | [링크](#13-관리자-카테고리-목록-조회-API) |                                                                    |
|                      | 관리자 주문 관리(Admin-Controller)     | 관리자 주문 내역조회 API                   | GET                        | /api/admin/orderlist          | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 주문취소 내역조회 API               | GET                        | /api/admin/ordercancellist    | 7월 23일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 괸리자 회원 관리(Admin-Controller)     | 관리자 회원 정보 조회 API                  | GET                        | /api/admin/member             | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 회원 정보 삭제 API                  | DELETE                     | /api/admin/member             | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 관리자 진열 관리(Admin-Controller)     | 관리자 진열 카테고리(기본정보) 등록 API    | POST                       | /api/admin/displaycategory    | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 진열 카테고리(기본정보) 조회 API    | GET                        | /api/admin/displaycategory    | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 진열 카테고리(기본정보) 수정 API    | PUT                        | /api/admin/displaycategory    | 7월 24일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 진열 카테고리(기본정보) 삭제 API    | DELETE                     | /api/admin/displaycategory    | 7월 25일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품 메인진열 등록 API              | POST                       | /api/admin/maindisplay        | 7월 25일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품 메인진열 조회 API              | GET                        | /api/admin/maindisplay        | 7월 25일 | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 관리자 상품 메인진열 삭제 API              | DELETE                     | /api/admin/maindisplay        | 7월 25일 | 2H                 |              |                        |                                           |                                                                    |
|                      | 관리자 이용약관 관리                   | 이용약관 등록 API                          | POST                       | /api/admin/terms              | !        | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 이용약관 조회 API                          | GET                        | /api/admin/terms              | !        | 2H                 |              |                        |                                           |                                                                    |
|                      |                                        | 이용약관 삭제 API                          | DELETE                     | /api/admin/terms              | !        | 2H                 |              |                        |                                           |                                                                    |
| \*\*\*\*\*\*\*\*\*\* | \*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*     | \*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\* | \*\*\*\*\*\*\*\*\*\*\*\*\* | \*\*\*\*\*\*\*\*              | \*\*\*\* | \*\*\*\*\*\*\*\*\* | \*\*\*\*\*\* | \*\*\*\*\*\*\*\*\*\*\* | \*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*          | \*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\* |

---

<API 작업 결과 - API 별>
========================

---

회원계정 (Customer-Controller) API List
=======================================

1 회원가입 API
--------------

### (/api/customer/account)

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:201,result: success, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200, result: fail, message: 이미 존재하는 아이디입니다.)
	-	case2. 입력 필드 형식오류 (code: 400, result: fail, message:(입력 필드),... 오류발생)

#### ■ 실제동작코드

```java
 @ApiOperation(value = "회원가입 (계정 정보 생성)") @ApiImplicitParams({ @ApiImplicitParam(name = "memberVo", value = "회원가입하는 정보 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") }) @PostMapping("/account") public ResponseEntity<JSONResult> join(@RequestBody @Valid MemberVo memberVo, BindingResult bindresult) {


    // 아이디 중복 체크
    if (customerService.getIdCount(memberVo.getId()) != 0) {
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("이미 존재하는 아이디입니다."));
    }

    // 유효성 검사 실패시
    if (bindresult.hasErrors()) {
        List<FieldError> list = bindresult.getFieldErrors();
        String errMsg = "";
        for (FieldError err : list) {
            errMsg += err.getField() + "/";
        }
        errMsg += "오류발생";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
    }

    int result = customerService.memberJoin(memberVo);
    return ResponseEntity.status(HttpStatus.Created).body(JSONResult.success("회원가입 완료", result));
}
```

#### ■ TC CODE

```java

  /**
     * 회원가입을 테스트 하는 메소드 (실패케이스 - 이름 2자 이상 , 10자 이하)
     * @throws Exception 예외
     */
    @Test
    public void a_testJoinFail_InputName() throws Exception{
        MemberVo vo = new MemberVo();
        vo.setName("김");
        vo.setAddress("서울시 성동구");
        vo.setBirthDate("1993-10-25");
        vo.setGender("m");
        vo.setId("skok10");
        vo.setPassword("1234");
        vo.setEmail("skok1025@naver.com");
        vo.setTel("01068669202");
        vo.setRegdate("2019-07-11");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


        resultActions
        .andExpect(status().isBadRequest())
        .andDo(print())
        ;
    }

    /**
     * 회원가입을 테스트 하는 메소드 (성공케이스)
     * @throws Exception 예외
     */
    @Test
    public void a_testJoinSuccess() throws Exception{
        MemberVo vo = new MemberVo();
        vo.setName("김석현");
        vo.setAddress("서울시 성동구");
        vo.setBirthDate("1993-10-25");
        vo.setGender("m");
        vo.setId("skok1025");
        vo.setPassword("1234");
        vo.setEmail("skok1025@naver.com");
        vo.setTel("01068669202");
        vo.setRegdate("2019-07-11");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.data", is(1)))
                ;
    }

    /**
     * 회원가입을 테스트 하는 메소드 (실패케이스 - 중복된 아이디)
     * @throws Exception 예외
     */
    @Test
    public void b_testJoinFail_Id() throws Exception{
        MemberVo vo = new MemberVo();
        vo.setName("김석현");
        vo.setAddress("서울시 성동구");
        vo.setBirthDate("1993-10-25");
        vo.setGender("m");
        vo.setId("skok1025");
        vo.setPassword("1234");
        vo.setEmail("skok1025@naver.com");
        vo.setTel("01068669202");
        vo.setRegdate("2019-07-11");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("fail")))
                .andExpect(jsonPath("$.message", is("이미 존재하는 아이디입니다.")))
                ;
    }

```

---

2 회원정보수정 API
------------------

### (/api/customer/account)

#### ■ request: put

#### ■ params:

-	memberVo= 회원정보 수정한 Vo

#### ■ response:

-	성공: 회원수정 완료 (code:200,result:success ,data: 1)

-	실패

	-	case1. 수정한 이메일형식오류 (code: 400, result:fail)
	-	case2. 수정한 이름형식오류, 이름 2자~10자 이내 (code: 400)
	-	case3. 없는 아이디 정보수정 시도 (message: '회원정보수정 실패',code: 200)

#### ■ 실제동작코드

```java
/**
     * 회원정보수정하는 메소드
     *
     * @param memberVo 회원정보수정 vo
     * @return 응답
     */
    @ApiOperation(value = "회원정보수정 (계정 정보 수정)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberVo", value = "회원정보 수정한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") })
    @PutMapping("/account")
    public ResponseEntity<JSONResult> modifyAccount(@RequestBody MemberVo memberVo) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(memberVo, "name");

        if (validatorResults.isEmpty() == false) {
            for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
                String message = validatorResult.getMessage();
                //String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
                JSONResult result = JSONResult.fail(message);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

        }

        validatorResults = validator.validateProperty(memberVo, "email");

        if (validatorResults.isEmpty() == false) {
            for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
                String message = validatorResult.getMessage();
                //String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
                JSONResult result = JSONResult.fail(message);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

        }

        int result = customerService.modifyAccount(memberVo);
        return result == 1 ?
                ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원정보수정 완료", result))
                : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("회원정보수정 실패"));
    }
```

#### ■ TC코드

```java
/**
     * 회원정보 수정을 테스트 하는 메소드 (실패케이스1- 이름이 2자 이하)
     * @throws Exception 예외
     */
    @Test
    public void g_testModifyAccount_Fail_Name() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        // 수정할 회원번호
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        vo.setNo(1L);
        // 수정내용
        vo.setName("김");
        vo.setAddress("서울시 성동구 성수동 2가");
        vo.setBirthDate("1993-10-25");
        vo.setGender("f");
        vo.setPassword("1234");
        vo.setEmail("skok1025@naver.com");
        vo.setTel("01068669202");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("fail")))
                ;   
    }
    /**
     * 회원정보 수정을 테스트 하는 메소드 (실패케이스2- 이메일 형식 오류)
     * @throws Exception 예외
     */
    @Test
    public void h_testModifyAccount_Fail_Email() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        // 수정할 회원번호
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        vo.setNo(1L);
        // 수정내용
        vo.setName("김석현");
        vo.setAddress("서울시 성동구 성수동 2가");
        vo.setBirthDate("1993-10-25");
        vo.setGender("f");
        vo.setPassword("1234");
        vo.setEmail("skok102naver.com");
        vo.setTel("01068669202");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


        resultActions
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        ;   
    }
    /**
     * 회원정보 수정을 테스트 하는 메소드 (성공케이스)
     * @throws Exception 예외
     */
    @Test
    public void i_testModifyAccount_Success() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();

        // 수정할 회원번호
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        //vo.setNo(1L); // 회원가입한 회원번호를 가져올 필요성 !!!
        // 수정내용
        vo.setName("김석현");
        vo.setAddress("서울시 성동구 성수동 2가");
        vo.setBirthDate("1993-10-25");
        vo.setGender("f");
        vo.setPassword("1234");
        vo.setEmail("skok1025@naver.com");
        vo.setTel("01068669202");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.message", is("회원정보수정 완료")))
                .andExpect(jsonPath("$.data", is(1)))
                ;   
    }

```

---

3 회원비밀번호 수정 API
-----------------------

### (/api/customer/account/pw)

#### ■ request: put

#### ■ params:

-	memberVo= 회원번호,회원 기존 비밀번호,회원 새로운 비밀번호, 회원 새로운 비밀번호 확인 포함 Vo

#### ■ response:

-	성공: 회원비밀번호 수정 완료 (code:200,result: success, data: 1)

-	실패

	-	case1. 기존 비밀번호가 틀린경우 (code: 200, result: fail, nmessage: 회원비번수정 실패)
	-	case2. 입력 필드 형식오류 (code: 200, result: fail, message: 회원비번수정 실패)

#### ■ 실제동작코드

```java
/**
     * 회원비밀번호 수정하는 메소드
     *
     * @param memberVo 회원번호,회원 기존 비밀번호,회원 새로운 비밀번호, 회원 새로운 비밀번호 확인 포함 MemberVo
     * @return 응답
     */
    @ApiOperation(value = "회원정보수정 (계정 정보 수정)")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "memberVo", value = "회원번호,회원 기존 비밀번호,회원 새로운 비밀번호, 회원 새로운 비밀번호 확인 포함 MemberVo", required = true, dataType = "MemberVo", defaultValue = "")})
    @PutMapping("/account/pw")
    public ResponseEntity<JSONResult> modifyAccountPassword(
            @RequestBody MemberVo memberVo) {


        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(memberVo, "password");

        if (validatorResults.isEmpty() == false) {
            for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
                String message = validatorResult.getMessage();
                //String message = messageSource.getMessage("Email.userVo.email", null, LocaleContextHolder.getLocale());
                JSONResult result = JSONResult.fail(message);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }

        }

        int result = customerService.modifyAccountPw(memberVo);
        return result == 1 ?
                ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원비번수정 완료", result))
                : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("회원비번수정 실패"));
    }

```

#### ■ TC코드

```java
/**
     * 회원정보 수정(pw)을 테스트 하는 메소드 (실패케이스 - 기존 비밀번호가 아닌경우)
     * @throws Exception 예외
     */
    @Test
    public void j_testModifyAccountPw_Fail()  throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        // 수정할 회원번호
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        vo.setNo(1L);

        vo.setPassword("1233424"); // 비번 : 1234
        vo.setNewPw("12345");
        vo.setConfirmPw("12345");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/customer/account/pw").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("fail")))
                .andExpect(jsonPath("$.message", is("회원비번수정 실패")))
                .andExpect(jsonPath("$.data", not(is(1))))
                ;

    }

    /**
     * 회원정보 수정(pw)을 테스트 하는 메소드 (성공케이스)
     * @throws Exception 예외
     */
    @Test
    public void k_testModifyAccountPw_Success()  throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();


        // 수정할 회원번호
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        //vo.setNo(1L);

        vo.setPassword("1234");
        vo.setNewPw("12345");
        vo.setConfirmPw("12345");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/customer/account/pw").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.message", is("회원비번수정 완료")))
                .andExpect(jsonPath("$.data", is(1)))
                ;

    }
```

---

4 회원탈퇴 API
--------------

### (/api/customer/account)

#### ■ request: delete

#### ■ params:

-	memberVo= 비밀번호를 포함한 Vo

#### ■ response:

-	성공: 회원탈퇴 성공 (code:200, result:success ,data: 1)

-	실패

	-	case1. 입력된 비밀번호가 사용자 비밀번호와 다른경우 (code: 200, result: fail, message:회원탈퇴 실패)

#### ■ 실제동작코드

```java
/**
     * 계정을 삭제하는 메소드
     *
     * @param memberVo 삭제할 회원번호, 비밀번호를 담은 VO
     * @return 응답
     */
    @ApiOperation(value = "회원탈퇴(계정정보 삭제)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberVo", value = "비밀번호를 포함한 MemberVo", required = true, dataType = "MemberVo", defaultValue = "") })
    @DeleteMapping("/account")
    public JSONResult removeAccount(@RequestBody MemberVo memberVo) {
        int result = customerService.removeAccount(memberVo);
        return result != 0 ? JSONResult.success("회원탈퇴 성공", result) : JSONResult.fail("회원탈퇴 실패");
    }
```

#### ■ TC코드

```java
/**
     * 계정삭제 실패 테스트 (실패케이스)
     * @throws Exception 예외
     */
    @Test
    public void l_testRemoveAccountFail() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        //vo.setNo(1L);
        vo.setPassword("124"); // 사용자입력
        // 실제 비밀번호 : 1234

        ResultActions resultActions =
                mockMvc
                .perform(delete("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        .andExpect(jsonPath("$.data", not(is(1))))
        ;       
    }

    /**
     * 계정삭제 성공 테스트 (성공케이스)
     * @throws Exception 예외
     */
    @Test

    public void m_testRemoveAccountSuccess() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        vo.setNo(customerDao.getCurrentInsertNo()); // 회원가입한 회원번호를 가져올 필요성 !!!
        //vo.setNo(1L);
        vo.setPassword("1234"); // 사용자입력
                                // 실제 비밀번호 : 1234

        ResultActions resultActions =
                mockMvc
                .perform(delete("/api/customer/account").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.data", is(1)))
                ;       
    }

```

---

5 로그인 API
------------

### (/api/customer/auth)

#### ■ request: post

#### ■ params:

-	memberVo= 로그인 정보를 담은 정보 Vo

#### ■ response:

-	성공: 로그인 성공 (code:200,result:success ,data: authUser(유저정보))

-	실패

	-	case1. 로그인 아이디의 비밀번호가 틀린경우 (code: 200, result:fail, message: 로그인 실패)

	-	case2. 존재하지 않는 아이디를 입력한 경우 (code: 200, result:fail, message: 로그인 실패)

#### ■ 실제동작코드

```java

    /**
     * 로그인하는 메소드
     *
     * @param membervo 로그인 정보를 담은 membervo (id, password)
     * @return 응답
     */
    @ApiOperation(value = "로그인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "membervo", value = "로그인 정보를 담은 membervo", required = true, dataType = "MemberVo", defaultValue = "") })
    @PostMapping("/auth")
    public ResponseEntity<JSONResult> checkAuth(@RequestBody MemberVo membervo) {
        MemberVo authUser = customerService.getAuthUser(membervo);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<MemberVo>> validatorResults = validator.validateProperty(membervo, "id");

        if (!validatorResults.isEmpty()) {
            for (ConstraintViolation<MemberVo> validatorResult : validatorResults) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(JSONResult.fail(validatorResult.getMessage()));
            }
        }

        return authUser != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 성공", authUser))
                : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("로그인 실패"));
    }

```

#### ■ TC코드

```java

    /**
     * 로그인 인증처리 테스트 (실패케이스1- 비밀번호 오류)
     * @throws Exception 예외
     */
    @Test
    public void c_testCheckAuthFailPw() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();

        vo.setId("skok1025");  // 사용자 입력
        vo.setPassword("123");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        ;       
    }

    /**
     * 로그인 인증처리 테스트 (실패케이스2- 존재하지 않는 아이디)
     * @throws Exception 예외
     */
    @Test
    public void c_testCheckAuthFailId() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        vo.setId("skok102");  // 사용자 입력
        vo.setPassword("123");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        ;       
    }

    /**
     * 로그인 인증처리 테스트 (성공케이스)
     * @throws Exception 예외
     */
    @Test
    public void d_testCheckAuthSuccess() throws Exception{
        a_testJoinSuccess(); // 선행작업: 회원가입

        MemberVo vo = new MemberVo();
        vo.setId("skok1025");  // 사용자 입력
        vo.setPassword("1234");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/customer/auth").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));


                resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("success")))
                .andExpect(jsonPath("$.data.id", is(vo.getId())))
                ;       
    }
```

---

6 아이디 중복확인 API
---------------------

### (/api/customer/checkId)

---

#### ■ request: get

#### ■ params:

-	id : 중복체크를 할 아이디

#### ■ response:

-	성공: 사용가능한 아이디 (code:200, result:success,data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200, result:fail, message: 중복된 아이디입니다.)

#### ■ 실제동작코드

```java
/**
 * 아이디 중복체크하는 메소드
 *
 * @param id 중복체크를 할 아이디
 * @return 응답
 */
@ApiOperation(value = "아이디 중복 체크")
@ApiImplicitParams({
    @ApiImplicitParam(name = "id", value = "중복체크를 할 아이디", required = true, dataType = "String", defaultValue = "") })
@GetMapping("/checkId")
public ResponseEntity<JSONResult> CheckId(String id) {
  int count = customerService.getIdCount(id);
  return count == 0 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("사용가능한 아이디입니다.", count))
      : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("중복된 아이디입니다."));
}

```

#### ■ TC코드

```java
/**
     * 존재하는 아이디('skok1025')의 아이디(사용불가)를 체크하는 테스트
     * @throws Exception 예외
     */
    @Test
    public void e_testCheckExistingId() throws Exception {
        a_testJoinSuccess(); // 선행작업: 회원가입

        ResultActions resultActions =
        mockMvc
        .perform(get("/api/customer/checkId?id={id}","skok1025")).andExpect(status().isOk());

        resultActions
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        .andExpect(jsonPath("$.data", not(is(0))));
    }

    /**
     * 존재하지 않는 아이디('test')의 아이디(사용가능)를 체크하는 테스트
     * @throws Exception 예외
     */
    @Test
    public void f_testCheckNotExistingId() throws Exception {
        a_testJoinSuccess(); // 선행작업: 회원가입 skok1025

        ResultActions resultActions =
        mockMvc
        .perform(get("/api/customer/checkId?id={id}","test")).andExpect(status().isOk());

        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("success")))
        .andExpect(jsonPath("$.data", is(0)));
    }

```

---

관리자 카테고리 관리 (Admin-Controller) API List
================================================

7 관리자 1차 카테고리 등록 API
------------------------------

### (/api/admin/bigcategory)

---

#### ■ request: post

#### ■ params:

-	BigCategoryVo : 관리자가 등록할 1차케테고리 vo

#### ■ response:

-	성공: 관리자 1차 카테고리 등록 성공 (code:201, result:success,data: 등록된 카테고리 vo)

-	실패

	-	case1. 카테고리명 10자 이상 (code: 400, result:fail, message: name 에러)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 1차 카테고리 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "관리자가 등록할 1차카테고리 vo", required = true, dataType = "BigCategoryVo", defaultValue = "")
    })
    @PostMapping("/bigcategory")
    public ResponseEntity<JSONResult> addBigCategory(
            @RequestBody @Valid BigCategoryVo vo,
            BindingResult bindresult) {

        if(bindresult.hasErrors()) {
            List<FieldError> list = bindresult.getFieldErrors();
            String errMsg = "";
            for(FieldError err: list) {
                errMsg += err.getField()+"/";
            }
            errMsg += "오류발생";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
        }

        int result = adminService.addBigCatergory(vo);
        return result==1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 1차 카테고리 등록 성공", vo))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 1차 카테고리등록 실패"));
    }

```

#### ■ TC코드

```java
/**
     * 관리자가 1차 카테고리 (상의)를 등록하는 테스트메소드 (성공케이스)
     * @throws Exception 예외
     */
    @Test
    public void testAddBigCategory_Success() throws Exception{

        BigCategoryVo vo = new BigCategoryVo();
        vo.setName("지금넣은거");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("success")))
        //.andExpect(jsonPath("$.data", is(1)))
        ;
    }

    /**
     * 관리자가 1차 카테고리 (아름다운상의꼼데가르송)를 등록하는 테스트메소드 (실패케이스 - 카테고리명 10자 이상)
     * @throws Exception 예외
     */
    @Test
    public void testAddBigCategory_Fail() throws Exception{
        BigCategoryVo vo = new BigCategoryVo();
        vo.setName("아름다운상의꼼데가르송");

        ResultActions resultActions =
                mockMvc
                .perform(post("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        //.andExpect(jsonPath("$.data", is(1)))
        ;
    }
```

---

8 관리자 1차 카테고리 수정 API
------------------------------

### (/api/admin/bigcategory)

---

#### ■ request: put

#### ■ params:

-	BigCategoryVo : 관리자가 수정할 1차카테고리 vo

#### ■ response:

-	성공: 관리자 1차 카테고리 수정 성공 (code:200, result:success,data: 관리자가 수정한 1차 카테고리 vo)

-	실패

	-	case1. 카테고리명 10자 이상 (code: 400, result:fail, message: name 에러)
	-	case2. 없는 카테고리번호fmf (x-> 윗옷) 으로 수정하는 경우(code:200, result:fail, message: 관리자 1차 카테고리수정 실패)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 1차 카테고리 수정")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "vo", value = "관리자가 수정할 1차카테고리 vo", required = true, dataType = "Long", defaultValue = "")
    })
    @PutMapping("/bigcategory")
    public ResponseEntity<JSONResult> modifyBigCategory(@RequestBody @Valid BigCategoryVo vo, BindingResult bindresult) {
        if(bindresult.hasErrors()) {
            List<FieldError> list = bindresult.getFieldErrors();
            String errMsg = "";
            for(FieldError err: list) {
                errMsg += err.getField()+"/";
            }
            errMsg += "오류발생";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
        }
        int result = adminService.modifyBigCatergory(vo);
        return result==1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 1차 카테고리 수정 성공", vo)) : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 1차 카테고리수정 실패"));
    }

```

#### ■ TC코드

```java
/**
     * 관리자가 1차 카테고리 (상의 -> 윗옷) 으로 수정하는 테스트 메소드 (성공케이스)
     * @throws Exception
     */
    @Test
    public void testModifyBigCategory_Success() throws Exception{
        testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)

        BigCategoryVo vo = new BigCategoryVo();
        vo.setNo(adminDao.getCurrentInsertBigCategoryNo());
        vo.setName("윗옷");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("success")))
        //.andExpect(jsonPath("$.data", is(1)))
        ;
    }

    /**
     * 관리자가 1차 카테고리 (상의 -> 아름다운상의꼼데가르송) 으로 수정하는 테스트 메소드
     * (실패케이스1 - 카테고리명 10자 이상)
     * @throws Exception 예외
     */
    @Test
    public void testModifyBigCategory_Fail1() throws Exception{
        testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)

        BigCategoryVo vo = new BigCategoryVo();
        vo.setNo(adminDao.getCurrentInsertBigCategoryNo());
        vo.setName("아름다운상의꼼데가르송");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        //.andExpect(jsonPath("$.data", is(1)))
        ;
    }

    /**
     * 관리자가 없는 1차 카테고리 번호를 (x -> 윗옷) 으로 수정하는 테스트 메소드
     *  (실패케이스2)
     * @throws Exception
     */
    @Test
    public void testModifyBigCategory_Fail2() throws Exception{
        testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)

        BigCategoryVo vo = new BigCategoryVo();
        vo.setNo(1000L);// 1000번의 상품번호는 없음
        vo.setName("윗옷");

        ResultActions resultActions =
                mockMvc
                .perform(put("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.result", is("fail")))
        //.andExpect(jsonPath("$.data", is(1)))
        ;
    }
```

---

9 관리자 1차 카테고리 삭제 API
------------------------------

### (/api/admin/bigcategory)

---

#### ■ request: delete

#### ■ params:

-	BigCategoryVo : 관리자가 삭제할 1차카테고리 vo(번호포함)

#### ■ response:

-	성공: 관리자 1차 카테고리 삭제 성공 (code:200, result:success,data: 1)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 1차 카테고리 삭제")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "vo", value = "관리자가 삭제할 1차카테고리 vo(번호)", required = true, dataType = "Long", defaultValue = "")
    })
    @DeleteMapping("/bigcategory")
    public JSONResult removeBigCategory(@RequestBody BigCategoryVo vo) {
        int result = adminService.removeBigCatergory(vo);
        return result==1 ? JSONResult.success("관리자 1차 카테고리 삭제 성공", result) : JSONResult.fail("관리자 1차 카테고리 삭제 실패");
    }

```

#### ■ TC코드

```java
   /**
     * 관리자가 1차 카테고리를 삭제하는 테스트 메소드
     * @throws Exception 예외
     */
    @Test
    public void testRemoveBigCategory() throws Exception{
        testAddBigCategory("상의"); // 선행작업 : 1차 카테고리 등록 (상의)

        BigCategoryVo vo = new BigCategoryVo();
        vo.setNo(adminDao.getCurrentInsertBigCategoryNo());

        ResultActions resultActions =
                mockMvc
                .perform(delete("/api/admin/bigcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

        resultActions
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result", is("success")))
        .andExpect(jsonPath("$.data", is(1)))
        ;
    }

```

---

10 관리자 2차 카테고리 등록 API
-------------------------------

### (/api/admin/smallcategory)

---

#### ■ request: post

#### ■ params:

-	SmallCategoryVo : 관리자가 등록할 2차카테고리 vo

#### ■ response:

-	성공: 관리자 2차 카테고리 등록 성공 (code:201, result:success,data: 관리자가 등록한 2차카테고리 vo)

-	실패

	-	case1. 카테고리명 10자 이상 (code: 400, result:fail, message: name 에러)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 2차 카테고리 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "관리자가 등록할 2차카테고리 vo", required = true, dataType = "SmallCategoryVo", defaultValue = "")
    })
    @PostMapping("/smallcategory")
    public ResponseEntity<JSONResult> addSmallCategory(@RequestBody @Valid SmallCategoryVo vo, BindingResult bindresult) {
        if(bindresult.hasErrors()) {
            List<FieldError> list = bindresult.getFieldErrors();
            String errMsg = "";
            for(FieldError err: list) {
                errMsg += err.getField()+"/";
            }
            errMsg += "오류발생";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
        }

        int result = adminService.addSmallCatergory(vo);
        return result==1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 2차 카테고리 등록 성공", vo))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 2차 카테고리등록 실패"));
    }


```

#### ■ TC코드

```java
/**
 * 관리자가 2차 카테고리 (반팔)를 등록하는 테스트메소드 (성공케이스)
 * @throws Exception 예외
 */
@Test
public void testAddSmallCategory_Success() throws Exception{
    testAddBigCategory("상의");

    SmallCategoryVo vo = new SmallCategoryVo();
    vo.setName("반팔");
    vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());

    ResultActions resultActions =
            mockMvc
            .perform(post("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

    resultActions
    .andExpect(status().isCreated())
    .andDo(print())
    .andExpect(jsonPath("$.result", is("success")))
    //.andExpect(jsonPath("$.data", is(1)))
    ;
}
/**
 * 관리자가 2차 카테고리 (아름다운반팔꼼데가르송)를 등록하는 테스트메소드 (실패케이스- 카테고리명 10자 이상)
 * @throws Exception 예외
 */
@Test
public void testAddSmallCategory_Fail() throws Exception{
    testAddBigCategory("상의");

    SmallCategoryVo vo = new SmallCategoryVo();
    vo.setName("아름다운반팔꼼데가르송");
    vo.setBigcategoryNo(adminDao.getCurrentInsertBigCategoryNo());

    ResultActions resultActions =
            mockMvc
            .perform(post("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

    resultActions
    .andExpect(status().isBadRequest())
    .andDo(print())
    .andExpect(jsonPath("$.result", is("fail")))
    //.andExpect(jsonPath("$.data", is(1)))
    ;
}

```

---

11 관리자 2차 카테고리 수정 API
-------------------------------

### (/api/admin/smallcategory)

---

#### ■ request: put

#### ■ params:

-	SmallCategoryVo : 관리자가 수정할 2차 카테고리 vo

#### ■ response:

-	성공: 관리자 2차 카테고리 수정 성공 (code:200, result:success,data: 수정한 2차 카테고리 vo)

-	실패

	-	case1. 카테고리명 10자 이상 (code: 400, result:fail, message: name 에러)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 2차 카테고리 수정")
@ApiImplicitParams({
    @ApiImplicitParam(name = "vo", value = "관리자가 수정할 2차카테고리 vo", required = true, dataType = "SmallCategoryVo", defaultValue = "")
})
@PutMapping("/smallcategory")
public ResponseEntity<JSONResult> modifySmallCategory(@RequestBody @Valid SmallCategoryVo vo,BindingResult bindresult) {
    if(bindresult.hasErrors()) {
        List<FieldError> list = bindresult.getFieldErrors();
        String errMsg = "";
        for(FieldError err: list) {
            errMsg += err.getDefaultMessage()+"/";
        }
        errMsg += "오류발생";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
    }
    int result = adminService.modifySmallCatergory(vo);
    return result==1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 2차 카테고리 수정 성공", vo))
            : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 2차 카테고리수정 실패"));
}


```

#### ■ TC코드

```java

/**
 * 관리자가 2차 카테고리 (반팔 -> 칠부팔) 으로 수정하는 테스트 메소드 (성공케이스)
 * @throws Exception 예외
 */
@Test
public void testModifySmallCategory_Success() throws Exception{
    testAddSmallCategory("상의",Arrays.asList("반팔")); // 선행작업 : 2차 카테고리 등록 (반팔)

    SmallCategoryVo vo = new SmallCategoryVo();
    vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());
    vo.setName("칠부팔");

    ResultActions resultActions =
            mockMvc
            .perform(put("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

    resultActions
    .andExpect(status().isOk())
    .andDo(print())
    .andExpect(jsonPath("$.result", is("success")))
    //.andExpect(jsonPath("$.data", is(1)))
    ;
}

/**
 * 관리자가 2차 카테고리 (반팔 -> 아름다운꼼데가르송칠부팔) 으로 수정하는 테스트 메소드 (실패케이스 - 카테고리명 10자 이상 )
 * @throws Exception 예외
 */
@Test
public void testModifySmallCategory_Fail() throws Exception{
    testAddSmallCategory("상의",Arrays.asList("반팔")); // 선행작업 : 2차 카테고리 등록 (반팔)

    SmallCategoryVo vo = new SmallCategoryVo();
    vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());
    vo.setName("아름다운꼼데가르송칠부팔");

    ResultActions resultActions =
            mockMvc
            .perform(put("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

    resultActions
    .andExpect(status().isBadRequest())
    .andDo(print())
    .andExpect(jsonPath("$.result", is("fail")))
    //.andExpect(jsonPath("$.data", is(1)))
    ;
}

```

---

12 관리자 2차 카테고리 삭제 API
-------------------------------

### (/api/admin/smallcategory)

---

#### ■ request: delete

#### ■ params:

-	SmallCategoryVo : 관리자가 삭제할 2차카테고리 vo(번호)

#### ■ response:

-	성공: 관리자 2차 카테고리 삭제 성공 (code:200, result:success,data: 삭제된 2차 카테고리 vo)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 2차 카테고리 삭제")
@ApiImplicitParams({
  @ApiImplicitParam(name = "vo", value = "관리자가 삭제할 2차카테고리 vo(번호)", required = true, dataType = "Long", defaultValue = "")
})
@DeleteMapping("/smallcategory")
public JSONResult removeSmallCategory(@RequestBody SmallCategoryVo vo) {
  int result = adminService.removeSmallCatergory(vo);
  return result==1 ? JSONResult.success("c", vo) : JSONResult.fail("관리자 2차 카테고리 삭제 실패");
}

```

#### ■ TC코드

```java
/**
 * 관리자가 2차 카테고리를  삭제하는 테스트 메소드
 * @throws Exception 예외
 */
@Test
public void testRemoveSmallCategory() throws Exception{
    testAddSmallCategory("상의",Arrays.asList("반팔","카라티")); // 선행작업 : 2차 카테고리 등록

    SmallCategoryVo vo = new SmallCategoryVo();
    vo.setNo(adminDao.getCurrentInsertSmallCategoryNo());

    ResultActions resultActions =
            mockMvc
            .perform(delete("/api/admin/smallcategory").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(vo)));

    resultActions
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.result", is("success")))
    .andExpect(jsonPath("$.data", is(1)))
    ;
}

```

---

13 관리자 카테고리 목록조회 API
-------------------------------

### (/api/admin/category/list)

---

#### ■ request: get

#### ■ params:

#### ■ response:

-	성공: 관리자 카테고리 목록 조회 성공 (code:200, result:success,data: List<BigCategoryVo>(카테고리 리스트)

#### ■ 실제동작코드

```java
@ApiOperation(value = "관리자 카테고리 목록조회")

@GetMapping("/category/list")
public ResponseEntity<JSONResult> getCategorylist() {

    List<BigCategoryVo> categoryList = adminService.getCategoryList();

    return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 카테고리 목록 조회 성공", categoryList)) ;

}

```

#### ■ TC코드

```java
/**
 * 관리자 카테고리 리스트를 반환하는 테스트 메소드
 * @throws Exception
 */
@Test
public void testGetCategorylist() throws Exception{

    ResultActions resultActions =
            mockMvc
            .perform(get("/api/admin/category/list").contentType(MediaType.APPLICATION_JSON));

    resultActions
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.result", is("success")))
    ;
}
```

---
