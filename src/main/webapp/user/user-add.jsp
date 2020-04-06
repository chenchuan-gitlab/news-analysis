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
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>登录名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" lay-verify="nikename"
                           autocomplete="off" class="layui-input" required="">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>将会成为您唯一的登入名
                </div>
            </div>
            <div class="layui-form-item">
                <label for="password" class="layui-form-label">
                    <span class="x-red">*</span>密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="password" name="password" lay-verify="pass"
                           autocomplete="off" class="layui-input" required="">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    6到16个字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码
                </label>
                <div class="layui-input-inline">
                    <input type="password" id="repass" require="" lay-verify="repass"
                           autocomplete="off" class="layui-input">
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
                           onkeyup="this.value=this.value.replace(/\D/g,'')"
                           onafterpaste="this.value=this.value.replace(/\D/g,'')">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>性别</label>
                <div class="layui-input-block">
                    <input type="radio" name="sex" value="男" title="男" checked>
                    <input type="radio" name="sex" value="女" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><span class="x-red">*</span>角色</label>
                <div class="layui-input-block">
                    <input type="radio" name="type" value="1" title="管理员" checked>
                    <input type="radio" name="type" value="0" title="普通人员">
                </div>
            </div>

            <div class="layui-form-item">
                </label>
                <button class="layui-btn" lay-filter="add" lay-submit="">
                    增加
                </button>
            </div>
        </form>
    </div>
</div>
<script>
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
                    if (value == 'admin') {
                        return 'admin为超级管理员账号，无法添加';
                    }
                },
                pass: [/(.+){6,12}$/, '密码必须6到12位'],
                repass: function (value) {
                    if ($('#repass').val() != $('#password').val()) {
                        return '两次密码不一致';
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
                        url: "../user/add.action",
                        type: "POST",
                        dataType: "json",
                        contentType: "application/json;charset=UTF-8",
                        data: JSON.stringify(field),
                        success: function (data) {
                            if (data.code == 1) {
                                layer.alert("增加成功", {
                                        icon: 6
                                    },
                                    function () {
                                        //关闭当前frame
                                        x_admin_close();

                                        // 可以对父窗口进行刷新
                                        x_admin_father_reload();
                                    });
                            } else {
                                layer.alert("增加失败", {icon: 5});
                            }
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
