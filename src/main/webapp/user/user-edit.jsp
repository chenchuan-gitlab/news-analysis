<%@ page language="java" contentType="text/html;utf-8" pageEncoding="utf-8" %>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" name="id" id="id">
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>登录名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" lay-verify="required"
                           autocomplete="off" class="layui-input" required="">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>将会成为您唯一的登入名
                </div>
            </div>
            <div class="layui-form-item">
                <label for="phone" class="layui-form-label"><span class="x-red">*</span>手机</label>
                <div class="layui-input-inline">
                    <input type="text" id="phone" name="phone" required="" lay-verify="phone"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="age" class="layui-form-label">年龄</label>
                <div class="layui-input-inline">
                    <input type="number" id="age" name="age" autocomplete="off" class="layui-input" min="1"
                           onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男">
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>角色</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" value="1" title="管理员">
                    <input type="radio" name="type" value="0" title="普通人员">
                </div>
            </div>

            <div class="layui-form-item">
                </label>
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    确定
                </button>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function () {
        var form = layui.form;
        // 获取上一个页面传送过来的参数
        var param = window.location.search;
        $.ajax({
            url: "../user/getUserByID.action" + param,
            type: "GET",
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错", {icon: 2});
                } else {
                    var userData = data.data;
                    if (userData !== null) {
                        $("#id").val(userData.id);
                        $("#userName").val(userData.userName);
                        $("#phone").val(userData.phone);
                        $("#age").val(userData.age);
                        // 给性别赋值

                        $("input[name='sex']").each(function (index, element) {
                            //判断当前按钮的值与input的值是否一致，一致则赋值
                            if ($(this).val() == userData.sex) {
                                $(this).prop("checked", true);
                            }

                        });
                        // 给角色赋值
                        $("input[name='type']").each(function (index, element) {
                            //判断当前按钮的值与input的值是否一致，一致则赋值
                            if ($(this).val() == userData.type) {
                                $(this).prop("checked", true);
                            }

                        });
                        form.render();
                    } else {
                        layer.alert("数据获取失败", {
                                icon: 5
                            },
                            function () {
                                //关闭当前frame
                                x_admin_close();
                            });
                    }
                }
            },
            error: function () {
                layer.alert("请求出错，请重试", {icon: 2});
            }
        });

    });
    layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;

            //自定义验证规则
            form.verify({
                nikename: function (value) {
                    if (value.length < 5) {
                        return '昵称至少得5个字符啊';
                    }
                }
            });

            //监听提交
            form.on('submit(add)',
                function (data) {
                    var field = data.field;
                    // 密码md5加密
                    field.password = hexMD5(field.password);
                    $.ajax({
                        url: "../user/updateUser.action",
                        type: "POST",
                        dataType: "json",
                        contentType: "application/json;charset=UTF-8",
                        data: JSON.stringify(field),
                        success: function (data) {
                            //发异步，把数据提交给php
                            layer.alert("修改成功", {
                                    icon: 6
                                },
                                function () {
                                    //关闭当前frame
                                    x_admin_close();

                                    // 可以对父窗口进行刷新
                                    x_admin_father_reload();
                                });
                        },
                        error: function () {
                            layer.alert("请求失败", {icon: 5})
                        }
                    })

                    return false;
                });

        });
</script>
</body>

</html>
