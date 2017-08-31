<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery.min.js"></script>
<body>
<h2>Hello World!</h2>
<div style="margin-left: auto;margin-right: auto;">
    <h3> Hello </h3>
    用户名 <input type="text" name="name" id="name" autocomplete="off"/>
    密码 <input type="password" name="password" id="password" autocomplete="off" >
    <input type="button" value="登录" id="login" onclick="login()" >
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
            type:"post",
            url: "/user/login",
            dataType : "json",
            data: {
                name: name,
                password: password
            },
            //async:true,
            success: function (obj) {

                debugger;
            }
        });
    }

</script>
</html>
