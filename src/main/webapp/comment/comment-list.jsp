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
    <script src="../lib/layui/layui.js" charset="utf-8"></script>
    <script src="../js/myjs/table.js"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <div class="layui-inline layui-show-xs-block">
                        <input class="layui-input" autocomplete="off" placeholder="开始时间" name="startTime"
                               id="startTime">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <input class="layui-input" autocomplete="off" placeholder="结束时间" name="startTime"
                               id="endTime">
                    </div>
                    <div class="layui-inline layui-show-xs-block">
                        <button onclick="search(1)" class="layui-btn"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form" lay-filter="table">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" name="allCheck" lay-skin="primary" lay-filter="allChoose">
                            </th>
                            <th>用户名</th>
                            <th>评论标题</th>
                            <th>评论内容</th>
                            <th>评论时间</th>
                            <th>状态</th>
                            <th>评论时间</th>
                            <th>操作</th>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div id="noData" align="center">

                </div>
                <div class="layui-card-body ">
                    <div id="page" align="center">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        search(1);
    });

    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var form = layui.form;

        //执行一个laydate实例
        laydate.render({
            elem: '#startTime' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
        });
    });

    /*用户-停用*/
    function updateStatus(id, status) {
        layer.confirm('确认要更新状态吗？', function (index) {
            $.ajax({
                url: "../comment/updateStatus.action?id=" + id + "&status=" + status,
                success: function (data) {
                    var code = data.code;
                    if (code === 0) {
                        layer.alert("更新状态失败", {icon: 2});
                    } else {
                        layer.alert("更新状态成功", {icon: 1}, function (index) {
                            layer.close(index);
                            search(1);
                        });
                    }
                },
                error: function () {
                    layer.alert("请求出错，请重试", {icon: 5});
                }
            })
        });
    }


    // 查询列表数据
    function search(pageNum) {
        var userName = $("#userName").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        $.ajax({
            url: "../comment/list.action",
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: {"startTime": startTime, "endTime": endTime, "pageNum": pageNum},
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错", {icon: 2});
                } else {
                    var data = data.data;
                    showData(data);
                }
            },
            error: function () {
                layer.alert("请求出错，请重试", {icon: 2});
            }
        })
    }

    // 渲染列表数据
    function showData(object) {
        var form = layui.form;
        var html = "";
        for (var i = 0; i < object.rows.length; i++) {
            var title = "禁用";
            if (object.rows[i].statusCode == 0) {
                title = "启用"
            }
            html += "<tr><td><input type='checkbox' name='' lay-skin='primary' value='" + object.rows[i].id + "' lay-filter='itemChoose'></td><td>" +
                object.rows[i].userName + "</td><td>" +
                object.rows[i].title + "</td><td>" +
                object.rows[i].content + "</td><td>" +
                object.rows[i].status + "</td><td>" +
                object.rows[i].time + "</td>";
            html += "<td class='td-manage'>" +
                "<a title='查看' onclick=xadmin.open('查看','./comment-view.jsp?id=" + object.rows[i].id + "',600,400) href='javascript:;'><i class='layui-icon' style='font-size: 23px'>&#xe633;</i></a></td></tr>";
        }
        if (html.length == 0) {
            $("#noData").html("<span>暂无数据</span>");
            $("#page").empty();
        } else {
            $("#noData").empty();
        }
        $("tbody").html(html);
        // 渲染checkbox
        form.render("checkbox");
        if (object.rows.length != 0) {
            //分页
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'page',
                    count: object.totalCount,
                    curr: object.pageNum,
                    jump: function (obj, first) {
                        if (!first) {
                            search(obj.curr);
                        }
                    }
                });
            });
        }
    }
</script>
</html>