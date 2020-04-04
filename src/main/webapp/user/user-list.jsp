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
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input" autocomplete="off" placeholder="开始日" name="start" id="startTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input" autocomplete="off" placeholder="截止日" name="end" id="endTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button onclick="search(1)" class="layui-btn" lay-submit="" lay-filter="sreach"><i
                                    class="layui-icon">
                                &#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除
                    </button>
                    <button class="layui-btn" onclick="xadmin.open('添加用户','./user-add.jsp',600,400)"><i
                            class="layui-icon"></i>添加
                    </button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" name="" lay-skin="primary">
                            </th>
                            <th>用户名</th>
                            <th>手机</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>类型</th>
                            <th>状态</th>
                            <th>加入时间</th>
                            <th>操作</th>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
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
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？' + data, function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }


    function search(pageNum) {
        var userName = $("#userName").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        $.ajax({
            url: "/user/list.action",
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: {"userName": userName, "startTime": startTime, "endTime": endTime, "pageNum": pageNum},
            success: function (data) {
                var code = data.code;
                if (code === 0) {
                    layer.alert("查询出错");
                } else {
                    var data = data.data;
                    showData(data);
                }
            },
            error: function () {
                layer.alert("请求出错，请重试", {icon: 5});
            }
        })
    }

    function showData(object) {
        $("tbody").empty();
        for (var i = 0; i < object.rows.length; i++) {
            var title = "禁用";
            if (object.rows[i].statusCode == 0) {
                title = "启用"
            }
            var html = "<tr><td><input type='checkbox' name='' lay-skin='primary' value='" + object.rows[i].id + "'></td><td>" +
                object.rows[i].userName + "</td><td>" + object.rows[i].phone + "</td><td>" +
                object.rows[i].sex + "</td><td>" + object.rows[i].age + "</td><td>" + object.rows[i].type + "</td><td>" +
                object.rows[i].status + "</td><td>" + object.rows[i].timeStr + "</td>";
            html += "<td class='td-manage'>" +
                "<a onclick='member_stop(this," + object.rows[i].id + ")' href='javascript:;' title=" + title + "><i class='layui-icon' style='font-size: 23px'>&#xe601;</i></a>" +
                "<a title='编辑' onclick=xadmin.open('编辑','./user-edit.jsp',600,400) href='javascript:;'><i class='layui-icon' style='font-size: 23px'>&#xe642;</i></a>" +
                "<a title='删除' onclick=member_del(this," + object.rows[i].id + ") href='javascript:;'><i class='layui-icon' style='font-size: 23px'>&#xe640;</i> </a></td></tr>";
            $("tbody").append(html);
        }
        $("#recordSpan").text("共有数据：" + object.totalCount + " 条");
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
</script>
<script>var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</html>