<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/templates/headerNav.jsp"%>
<main class="container">
    <%--  파일을 파라미터로 전송하려면 어떻게 해야하나요??
       default : form 은 문자열 파라미터만 쿼리스트링(x-www-form-urlencoded) 전송 (파일은 무시하고 파일 이름만 전송)
       multipart/form-data : form의 모든 데이터를 BLOB 바이너리 형식으로 전송 (file == BLOB)
       톰캣 서블릿 :  multipart/form-data 를 처리할 수 없어서 cos.jar 라이브러리를 사용했어야했다...
                   톰캣(7)서블릿 3.0 이상부터는 @MultipartConfig 를 사용
    --%>
    <form name="signupForm" action="./signup.do" method="post" enctype="multipart/form-data" style="width: 500px;margin: 0 auto;">
        <h1 class="my-4">회원가입</h1>
        <p class="form-floating">
            <input id="uIdInput" type="text" name="u_id" value="test_test1" class="form-control" placeholder="??">
            <label class="">아이디</label>
        </p>
        <p class="form-floating">
            <input type="text" name="name" value="테스트유저" class="form-control" placeholder="??">
            <label>이름</label>
        </p>
        <p class="form-floating">
            <input type="password" name="pw" value="1234" class="form-control" placeholder="??">
            <label>패스워드</label>
        </p>
        <p class="form-floating">
            <input type="password" name="check_pw" value="1234" class="form-control" placeholder="??">
            <label>패스워드 체크</label>
        </p>
        <p class="input-group">
            <label class="input-group-text">프로필</label>
            <input type="file" name="img_path" value="" class="form-control" placeholder="??">
        </p>
        <p class="form-floating">
            <input type="text" name="phone" value="91099991111" class="form-control" placeholder="??">
            <label>핸드폰</label>
        </p>
        <p class="form-floating">
            <input type="text" name="email" value="test_test@gamil.com" class="form-control" placeholder="??">
            <label>이메일</label>
        </p>
        <p class="form-floating">
            <input type="date" name="birth" value="1986-05-25" class="form-control" placeholder="??">
            <label>생일</label>
        </p>
        <p class="form-floating">
            <select name="gender" class="form-control">
                <option value="MALE" >남자</option>
                <option value="FEMALE" >여자</option>
                <option value="NONE" selected>없음</option>
            </select>
            <label>성별</label>
        </p>
        <p class="form-floating">
            <input type="text" name="address" value="서울시" class="form-control" placeholder="??">
            <label>주소</label>
        </p>
        <p class="form-floating">
            <input type="text" name="detail_address" value="압구정" class="form-control" placeholder="??">
            <label>주소상세</label>
        </p>
        <p class="text-end">
            <button class="btn btn-outline-warning" type="reset">초기화</button>
            <button class="btn btn-outline-primary">회원가입</button>
        </p>
    </form>
    <script>
        signupForm.u_id.addEventListener("change",uIdHandler);
        signupForm.onsubmit=async function (e){
            e.preventDefault();
            let uIdCheck=await uIdHandler();
            if (!uIdCheck){
                window.location.href=(window.location.href).split("#")[0]+"#uIdInput";
            }else{
                signupForm.submit();
            }
        } //22분까지 쉬었다가 자습하세요~

        async function uIdHandler(){
            let uId=signupForm.u_id.value;
            let url="./idCheck.do?u_id="+uId;
            const res=await fetch(url);
            if(res.status==200){
                const obj=await res.json(); //{idCheck:true/false}
                if(obj.idCheck){//이미사용중인 아이디
                    signupForm.u_id.classList.add("is-invalid");
                    signupForm.u_id.nextElementSibling.innerText="사용 중인 아이디 입니다.";
                    signupForm.u_id.nextElementSibling.classList.add("invalid-feedback");
                    signupForm.u_id.classList.remove("is-valid");
                    signupForm.u_id.nextElementSibling.classList.remove("valid-feedback");
                }else{//사용 가능한 아이디
                    signupForm.u_id.classList.add("is-valid");
                    signupForm.u_id.nextElementSibling.innerText="사용 가능한 아이디 입니다.";
                    signupForm.u_id.nextElementSibling.classList.add("valid-feedback")
                    signupForm.u_id.classList.remove("is-invalid");
                    signupForm.u_id.nextElementSibling.classList.remove("invalid-feedback");
                    return true;
                }
            }else{
                alert("오류 : "+res.status)
            }
        }
    </script>
</main>
</body>
</html>