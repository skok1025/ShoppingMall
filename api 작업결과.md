<API 작업 결과 - API 별>
------------------------

---

회원가입 API (/api/customer/account)
------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

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
    return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원가입 완료", result));
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
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", is("fail")))
                .andExpect(jsonPath("$.message", is("이미 존재하는 아이디입니다.")))
                ;
    }

```

---

회원정보수정 API (/api/customer/account)
----------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

#### ■ 실제동작코드

#### ■ TC코드

---

회원비밀번호 수정 API (/api/customer/account/pw)
------------------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

#### ■ 실제동작코드

#### ■ TC코드

---

회원탈퇴 API (/api/customer/account)
------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

#### ■ 실제동작코드

#### ■ TC코드

---

로그인 API (/api/customer/account)
----------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

#### ■ 실제동작코드

#### ■ TC코드

---

아이디 중복확인 API (/api/customer/account)
-------------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200, data: 1)

-	실패

	-	case1. 중복된 아이디 (code: 200)
	-	case2. 입력 필드 형식오류 (code: 400)

#### ■ 실제동작코드

#### ■ TC코드
