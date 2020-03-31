function createRecord() {
    var reg = /^[1-9]\d*$/;
    var warehouse_number = $("#warehouse_number").val();
    var production_batch = $("#production_batch").val();
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    var quantity = $("#quantity").val();
    var operator = $("#operator").val();
    var remark = $("#remark").val();

    if(warehouse_number==""){
        layer.alert("请填写仓库编号");
    }else if(production_batch==""){
        layer.alert("请填写生产批次");
    }else if (product_type == "") {
        layer.alert("请填写产品种类");
    } else if (product_name == "") {
        layer.alert("请填写产品名称");
    } else if (quantity == "") {
        layer.alert("请填写出库量");
    } else if (operator == "") {
        layer.alert("请填写操作人员");
    } else if (!reg.test(quantity)) {
        layer.alert("数量填写错误，请重新填写");
    } else {
        $.ajax({
            url: "outRecord/createOutRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "out_number":"",
                "out_date":"",
                "warehouse_number": warehouse_number,
                "production_batch":production_batch,
                "product_type": product_type,
                "product_name": product_name,
                "quantity": quantity,
                "operator": operator,
                "remark":remark
            }),
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
    var out_number = $("#out_number").val();
    var start_time = $("#start_time").val();
    var end_time = $("#end_time").val();
    var warehouse_number = $("#warehouse_number").val();
    var production_batch = $("#production_batch").val();
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    var operator = $("#operator").val();
    $.ajax({
        url:"outRecord/getOutRecords.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"out_number":out_number,"start_time":start_time,"end_time":end_time,"warehouse_number":warehouse_number,
            "production_batch":production_batch,"product_type":product_type,"product_name":product_name,"operator":operator,"pageNum":pageNum},
        success:function (data) {
            showData(data);
        },
        error:function () {
            layer.alert("请求出错，请重试", {icon: 5});
        }
    })
}


function showData(object) {
    $("tbody").empty();
    for(var i = 0;i<object.list.length;i++){
        $("tbody").append("<tr><td>" + object.list[i].out_number + "</td><td>" + object.list[i].out_date + "</td><td>" +
            object.list[i].warehouse_number + "</td><td>" + object.list[i].production_batch + "</td><td>" + object.list[i].product_type + "</td><td>" +
            object.list[i].product_name + "kg</td><td>"+object.list[i].quantity+"</td><td>"+object.list[i].operator+"</td><td>" +object.list[i].remark+"</td><td>" +
            "<button class='layui-btn layui-btn-primary layui-btn-sm' title='查看' onclick='jump("+object.list[i].warehousing_batch+")' type='button'><i class='layui-icon'>&#xe642;</i></button></td></tr>");
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

function jump(obj) {
    xadmin.open("查看","./excipients-view.html?purchase_batch=" + obj);
    /*x_admin_show("编辑","question-edit.html?question_number=" + obj);*/
}