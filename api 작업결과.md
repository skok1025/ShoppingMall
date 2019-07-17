<API 일정 리스트>
-----------------

---

<API 작업 결과 - API 별>
------------------------

---

회원가입 API (/api/customer/account)
------------------------------------

#### ■ request: post

#### ■ params:

-	memberVo= 회원가입하는 정보 Vo

#### ■ response:

-	성공: 회원가입 완료 (code:200,result: success, data: 1)

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

회원비밀번호 수정 API (/api/customer/account/pw)
------------------------------------------------

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

회원탈퇴 API (/api/customer/account)
------------------------------------

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

로그인 API (/api/customer/auth)
-------------------------------

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

아이디 중복확인 API (/api/customer/checkId)
-------------------------------------------

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
