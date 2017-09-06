<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery.min.js"></script>
<body>
<div style="margin-left: auto;margin-right: auto;">
    <h3> Hello </h3>
    用户名 <input type="text" name="name" id="name" autocomplete="off"/>
    密码 <input type="password" name="password" id="password" autocomplete="off" >
    <input type="button" value="登录" id="login" onclick="return login()" >
    <input type="button" value="取消" id="cancel">
</div>
</body>
<script>
    function login(){

        var name = $("#name").val();
        if( "" ==name || null == name){
            alert("用户名不能为空！");
            return false;
        }
        var password = $("#password").val();
        if( "" ==password || null == password){
            alert("密码不能为空！");
            return false;
        }

        $.ajax({
            type:"POST",
            url: "/user/login",
            dataType : "json",
            data: {
                name: name,
                password: password
            },
            //async:true,
            success: function (obj) {

                if('true'==obj){

                    window.location.href="/user/toSuccessPage";
                }else{

                    alert("账号或密码错误！");
                    return false;
                }
            }
        });
    }

</script>
</html>
