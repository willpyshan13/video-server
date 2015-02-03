<%--
  Created by IntelliJ IDEA.
  User: Landy
  Date: 13-10-9
  Time: 下午2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <base href="<%=basePath%>" />
    <title>东娱微电影客户端后台管理系统</title>
    <link rel="stylesheet" href="css/reset.css" />
    <link rel="stylesheet" href="css/text.css" />
    <link rel="stylesheet" href="css/960_12_col.css" />
    <style type="text/css">
        #center{
            position: absolute;
            background-color: #efefef;
            width:500px;
            height:360px;
            left:50%;
            margin-left:-250px;
            top:50%;
            margin-top: -200px;
            text-align: center;
            padding-top: 25px;
        }
        #center img{
            width:30%;
        }
        #form{
            padding-top: 15px;
        }
        .item{
            padding: 4px 0;
        }
        .item input{
            height: 25px;
            width: 250px;
        }

        label{
            font-size: 15px;
        }
        #btn{
            padding-top: 10px;
        }
        #btn input{
            width:110px;
            background-color: rgb(0,144,218);
            line-height: 30px;
            border: 0px;
            height: 30px;
            cursor: pointer;
        }
    </style>
</head>
<body class="container_12">
<div  id="center">
    <img src="img/logo.png"/>
    <div id="form">
        <form method="post" action="login">
            <div class="item"><label for="">用户名:
                <div><input type="text" name="username" ></div>
            </label></div>
            <div class="item"><label for="">密码:
                <div><input type="password" name="password"></div>
            </label></div>
            <div class="item" id="btn">
                <input type="submit" value="登陆">
                <input style="margin-left:30px;" type="reset" value="重置">
            </div>
        </form>
    </div>
</div>
</body>
</html>