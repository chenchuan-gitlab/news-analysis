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
                <label for="siteName" class="layui-form-label">
                    <span class="x-red">*</span>站点名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="siteName"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="newsType" class="layui-form-label">
                    <span class="x-red">*</span>新闻类型</label>
                <div class="layui-input-inline">
                    <input type="text" id="newsType"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="title"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="newsUrl" class="layui-form-label">
                    <span class="x-red">*</span>新闻地址</label>
                <div class="layui-input-inline">
                    <input type="text" id="newsUrl"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="time" class="layui-form-label">
                    <span class="x-red">*</span>时间</label>
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
            url: "../newsInfo/getDataByID.action" + param,
            type: "GET",
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错", {icon: 2});
                } else {
                    var _data = data.data;
                    if (_data !== null) {
                        $("#siteName").val(_data.siteName);
                        $("#newsType").val(_data.newsType);
                        $("#title").val(_data.title);
                        $("#newsUrl").val(_data.newsUrl);
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
