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
                    <span class="x-red">*</span>用户名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="userName"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>评论标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="title"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="content" class="layui-form-label">
                    <span class="x-red">*</span>评论内容</label>
                <div class="layui-input-inline">
                    <input type="text" id="content"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="time" class="layui-form-label">
                    <span class="x-red">*</span>评论时间</label>
                <div class="layui-input-inline">
                    <input type="text" id="time"
                           autocomplete="off" class="layui-input" readonly>
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
            url: "../comment/getDataByID.action" + param,
            type: "GET",
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错", {icon: 2});
                } else {
                    var _data = data.data;
                    if (_data !== null) {
                        $("#id").val(_data.id);
                        $("#userName").val(_data.userName);
                        $("#title").val(_data.title);
                        $("#content").val(_data.content);
                        $("#time").val(_data.time);
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
</script>
</body>

</html>
