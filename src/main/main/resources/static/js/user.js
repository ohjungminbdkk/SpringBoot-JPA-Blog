let index={

	// 여기 this값
	init: function(){
		$("#btn-save").on("click", ()=>{ // function(){}., ()=>{} this를 바인딩하기 위해서!!
			this.save();
			// 여기 this값이 현재 ()=>{}로 같지만 fuction으로 해버리면, 윈도우에 있는 값을 가르킨다.
		});
		

		},
		
	save: function(){
	//alert('user의 save함수 호출됨');		
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
//		console.log(data);

//ajax 호출시 defalut가 비동기 호출
//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
//ajax가 통신을 성공하고 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해주네요.
	$.ajax({
		type : "POST",
		url: "/auth/joinProc",
		data:JSON.stringify(data), //http body 데이터
		contentType:"application/json; charset=utf-8",//body데이터가 어떤 타입인지(MIME)
		dataType:"json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생신게 json이라면) => javascript오브젝트로 변경
		// 회원가입 수행 요청(100초 과정)하면  아래 펑크션마다 끝나고 다음걸 실행하는게 아니라 계속적으로 비동기처리하는것
	}).done(function(resp){
		alert("회원가입이 완료되었습니다.");
		//console.log(resp);
		location.href="/";
	
	}).fail(function(error ){
	
		alert("a123232");
		alert(JSON.stringify(error));
	}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
		}
}


index.init();