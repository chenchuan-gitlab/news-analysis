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
                <label for="website" class="layui-form-label">
                    <span class="x-red">*</span>网站名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="website" name="website"
                           autocomplete="off" class="layui-input" required="">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="newsType" class="layui-form-label">
                    <span class="x-red">*</span>新闻类型
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="newsType" name="newsType"
                           autocomplete="off" class="layui-input" required="">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="url" class="layui-form-label">
                    <span class="x-red">*</span>地址
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="url" require="" lay-verify="url"
                           autocomplete="off" class="layui-input">
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
            url: "../website/getDataByID.action" + param,
            type: "GET",
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错", {icon: 2});
                } else {
                    var _data = data.data;
                    if (_data !== null) {
                        $("#id").val(_data.id);
                        $("#website").val(_data.website);
                        $("#newsType").val(_data.newsType);
                        $("#url").val(_data.url);
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
            //监听提交
            form.on('submit(add)',
                function (data) {
                    var field = data.field;
                    $.ajax({
                        url: "../website/update.action",
                        type: "POST",
                        dataType: "json",
                        contentType: "application/json;charset=UTF-8",
                        data: JSON.stringify(field),
                        success: function (data) {
                            if (data.code == 1) {
                                layer.alert("修改成功", {
                                        icon: 6
                                    },
                                    function () {
                                        //关闭当前frame
                                        x_admin_close();

                                        // 可以对父窗口进行刷新
                                        x_admin_father_reload();
                                    });
                            } else {
                                layer.alert("修改失败", {icon: 5});
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
