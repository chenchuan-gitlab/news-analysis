function createRecord() {
    var reg1 = /^[1-9]\d*$/;
    var reg2 = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    var material_batch = $("#material_batch").val();
    var feed_quantity = $("#feed_quantity").val();
    var plan_output = $("#plan_output").val();
    var operator = $("#operator").val();
    var jsonArr = [];

    if (product_type == "") {
        layer.alert("请填写产品种类");
    } else if (product_name == "") {
        layer.alert("请填写产品名称");
    } else if (material_batch == "") {
        layer.alert("请填写产品名称");
    } else if (feed_quantity == "") {
        layer.alert("请填写投料量");
    } else if (plan_output == "") {
        layer.alert("请填计划产量");
    } else if (operator == "") {
        layer.alert("请填写操作人员");
    } else if (!reg2.test(feed_quantity)) {
        layer.alert("投料量数据错误，请重新填写");
    } else if (!reg1.test(plan_output)) {
        layer.alert("计划产量数据错误，请重新填写");
    } else {
        var trList = $("tbody").children("tr");
        for (var i = 0; i < trList.length; i++) {
            var tdArr = trList.eq(i).find("td");
            var excipients_name = tdArr.eq(0).find("input").val();
            var feed_quantity = tdArr.eq(1).find("input").val();
            if (excipients_name == "") {
                layer.alert("请输入辅料名称");
                return;
            } else if (!reg2.test(feed_quantity)) {
                layer.alert("投料量数据错误，请重新填写");
                return;
            } else {
                jsonArr.push("{\"excipients_name\":\"" + excipients_name + "\",\"feed_quantity\":\"" + feed_quantity + "\"}");
            }
        }
        var recordJson = "{\"production_batch\":\"\",\"product_type\":\"" + product_type + "\",\"product_name\":\"" + product_name + "\"," +
            "\"material_batch\":\"" + material_batch + "\",\"feed_quantity\":\"" + feed_quantity + "\",\"plan_output\":\"" + plan_output + "\"," +
            "\"production_date\":\"\",\"operator\":\"" + operator + "\"}";
        if(jsonArr.length==0){
            jsonArr = null;
        }
        $.ajax({
            url: "production/createProductionRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({"record":recordJson,"exPutRecord":jsonArr}),
            success: function (data) {
                layer.alert(data.msg, function () {
                    //关闭当前frame
                    x_admin_close();
                    // 可以对父窗口进行刷新
                    x_admin_father_reload();
                });
            },
            error: function () {
                layer.alert("增加出错，请重试", {icon: 5});
            }
        })
    }
}

function getRecordList(pageNum) {
    var production_batch = $("#production_batch").val();
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    var start_time = $("#start_time").val();
    var end_time = $("#end_time").val();
    var operator = $("#operator").val();
    $.ajax({
        url: "production/getProductionRecords.action",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: {
            "production_batch": production_batch,
            "product_type": product_type,
            "product_name": product_name,
            "start_time": start_time,
            "end_time": end_time,
            "operator": operator,
            "pageNum": pageNum
        },
        success: function (data) {
            showData(data);
        },
        error: function () {
            layer.alert("请求出错，请重试", {icon: 5});
        }
    })
}


function showData(object) {
    $("tbody").empty();
    for (var i = 0; i < object.list.length; i++) {
        $("tbody").append("<tr><td>" + object.list[i].production_batch + "</td><td>" + object.list[i].product_type + "</td><td>" +
            object.list[i].product_name + "</td><td>" + object.list[i].material_batch + "</td><td>" + object.list[i].feed_quantity + "kg</td><td>" +
            object.list[i].plan_output + "瓶</td><td>" + object.list[i].production_date + "</td><td>" + object.list[i].operator + "</td><td>" +
            "<button class='layui-btn layui-btn-primary layui-btn-sm' title='查看' onclick='jump(" + object.list[i].production_batch + ")' type='button'><i class='layui-icon'>&#xe642;</i></button></td></tr>");
    }
    $("#recordSpan").text("共有数据：" + object.size + " 条");
    //分页
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page',
            count: object.size,
            curr: object.pageNum,
            jump: function (obj, first) {
                if (!first) {
                    getRecordList(obj.curr);
                }
            }
        });
    });
}

function getExcipientsPutList(pageNum) {
    var production_batch = $("#production_batch").val();
    $.ajax({
        type:"GET",
        url:"production/getExcipientsPutRecordList.action",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"production_batch":production_batch},
        success:function (data) {
            $("tbody").empty();
            for(var i=0;i<data.list.length;i++){
                $("tbody").append("<tr><td>"+data.list[i].production_batch+"</td><td>"+data.list[i].excipients_name+"</td><td>"+data.list[i].feed_quantity+"</td></tr>");
            }
            $("#recordSpan").text("共有数据：" + data.size + " 条");
            //分页
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                //执行一个laypage实例
                laypage.render({
                    elem: 'page',
                    count: data.size,
                    curr: data.pageNum,
                    jump: function (obj, first) {
                        if (!first) {
                            getExcipientsPutList(obj.curr);
                        }
                    }
                });
            });
        }
    })
}

function jump(obj) {
    xadmin.open("查看", "./excipients-view.html?purchase_batch=" + obj);
    /*x_admin_show("编辑","question-edit.html?question_number=" + obj);*/
}