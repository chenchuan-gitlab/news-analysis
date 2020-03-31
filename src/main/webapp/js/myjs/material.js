function createRecord() {
    var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var material_batch = $("#material_batch").val();
    var cow_batch = $("#cow_batch").val();
    var milk_yield = $("#milk_yield").val();
    var date = $("#date").val();

    if (material_batch == "") {
        layer.alert("请填写批次");
    } else if (cow_batch == "") {
        layer.alert("请填写奶牛批次");
    } else if (milk_yield == "") {
        layer.alert("请填写产奶量");
    } else if (date == "") {
        layer.alert("请选择时间");
    } else if (!reg.test(milk_yield)) {
        layer.alert("产奶量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "material/createRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "material_batch": material_batch,
                "cow_batch": cow_batch,
                "milk_yield": milk_yield,
                "date": date,
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
    var material_batch = $("#material_batch").val();
    var cow_batch = $("#cow_batch").val();
    var start_time = $("#start_time").val();
    var end_time = $("#end_time").val();
    $.ajax({
        url:"material/getRecordList.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"material_batch":material_batch,"cow_batch":cow_batch,"start_time":start_time,"end_time":end_time,"pageNum":pageNum},
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
        $("tbody").append("<tr><td>" + object.list[i].material_batch + "</td><td>" + object.list[i].cow_batch + "</td><td>" +
            object.list[i].milk_yield + "kg</td><td>" + object.list[i].date + "</td><td>" +
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