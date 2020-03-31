function createRecord() {
    var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var purchase_batch = $("#batch").val();
    var excipients_name = $("#excipients_name").val();
    var purchase_date = $("#time").val();
    var purchase_place = $("#place").val().replace(/\s*/g, "");
    var unit_price = $("#unit_price").val();
    var weight = $("#weight").val();

    if(purchase_batch==""){
        layer.alert("请填写批次");
    }else if(excipients_name==""){
        layer.alert("请填写辅料名称");
    }else if (purchase_date == "") {
        layer.alert("请选择时间");
    } else if (purchase_place == "") {
        layer.alert("请填写地点");
    } else if (unit_price == "") {
        layer.alert("请填写单价");
    } else if (weight == "") {
        layer.alert("请填写重量");
    } else if (!reg.test(unit_price) || !reg.test(weight)) {
        layer.alert("价格或重量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "excipients/createRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "purchase_batch": purchase_batch,
                "excipients_name":excipients_name,
                "purchase_date": purchase_date,
                "purchase_place": purchase_place,
                "unit_price": unit_price,
                "weight": weight,
                "total_price":"",
                "purchase_person":"张三"
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
    var purchase_batch = $("#purchase_batch").val();
    var excipients_name = $("#excipients_name").val();
    var start_time = $("#start_time").val();
    var end_time = $("#end_time").val();
    var purchase_place = $("#purchase_place").val();
    var purchase_person = $("#purchase_person").val();
    $.ajax({
        url:"excipients/getRecordList.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"purchase_batch":purchase_batch,"excipients_name":excipients_name,"start_time":start_time,"end_time":end_time,"purchase_place":purchase_place,"purchase_person":purchase_person,"pageNum":pageNum},
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
        $("tbody").append("<tr><td>" + object.list[i].purchase_batch + "</td><td>" + object.list[i].excipients_name + "</td><td>" +
            object.list[i].purchase_date + "</td><td>" + object.list[i].purchase_place + "</td><td>" + object.list[i].unit_price + "元/kg</td><td>" +
            object.list[i].weight + "kg</td><td>"+object.list[i].total_price+"</td><td>"+object.list[i].purchase_person+"</td><td>" +
            "<button class='layui-btn layui-btn-primary layui-btn-sm' title='查看' onclick='jump("+object.list[i].purchase_batch+")' type='button'><i class='layui-icon'>&#xe642;</i></button></td></tr>");
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