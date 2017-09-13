<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<%--%>

<%--request.setCharacterEncoding("utf-8");--%>

<%--%>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--<title>Add User From</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="save" method="post">--%>
<%--<fieldset>--%>
<%--<legend>创建用户</legend>--%>
<%--<p>--%>
<%--<label>姓名：</label> <input type="text"  name="name"--%>
<%--tabindex="1">--%>
<%--</p>--%>

<%--<p id="buttons">--%>
<%--<input id="reset" type="reset" tabindex="4" value="取消"> <input--%>
<%--id="submit" type="submit" tabindex="5" value="创建">--%>
<%--</p>--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add User From</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <title>My JSP 'index.jsp' starting page</title>

    <script>
        function requestJson() {

            var jsonData = {
                "userId": "1",
                "pwd": "2"
            };
            $.ajax({
                type: 'post',
                url: 'http://localhost:8080/adminlogin',
                contentType: 'application/json;charset=utf-8',//指定为json类
                //数据格式是json串，商品信息
                data: JSON.stringify(jsonData),
                success: function (data) {//返回json结果
                    alert(data.strnickname);
                }
            });
        }
    </script>
</head>

<body>
<h1>title</h1>
<input type="button" onclick="requestJson()" value="确定" id="btn"/>

</body>
</html>




