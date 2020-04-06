<%@ page language="java" contentType="text/html;utf-8" pageEncoding="utf-8" %>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/login.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">管理登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form">
        <input name="username" placeholder="用户名" type="text" lay-verify="username" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="password" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>

<script>
    $(function () {
        layui.use('form', function () {
            var form = layui.form;
            form.on('submit(login)', function (data) {
                var field = data.field;
                // 密码md5加密
                field.password = hexMD5(field.password);
                $.ajax({
                    url: "login.action",
                    type: "GET",
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    data: {"username": field.username, "password": field.password},
                    success: function (data) {
                        if (data.code == 1) {
                            location.href = 'index.jsp'
                        } else {
                            layer.alert(data.message, {icon: 5})
                        }
                    },
                    error: function () {
                        layer.alert("请求失败", {icon: 5})
                    }
                });
                return false;
            });

            //自定义验证规则
            form.verify({
                username: function (value) {
                    if (value.length == 0) {
                        return '请输入用户名';
                    }
                },
                password: function (value) {
                    if (value.length == 0) {
                        return '请输入密码';
                    }
                }
            });
        });


    })
</script>
</body>
</html>