function createRecord() {
    var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var cow_batch = $("#cow_batch").val();
    var date = $("#date").val();
    var food_name = $("#food_name").val();
    var food_weight = $("#food_weight").val();

    if (cow_batch == "") {
        layer.alert("请填写批次");
    } else if (date == "") {
        layer.alert("选择时间");
    } else if (food_name == "") {
        layer.alert("请填写食物");
    } else if (food_weight == "") {
        layer.alert("请填写质量");
    } else if (!reg.test(food_weight)) {
        layer.alert("质量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "cowFood/createRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "cow_batch": cow_batch,
                "date": date,
                "food_name": food_name,
                "food_weight": food_weight,
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

function getRecordByID(obj) {
    $.ajax({
        url:"cowFood/getRecordByID.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"id":obj},
        success:function (data) {
            $("#id").val(data.id);
            $("#cow_batch").val(data.cow_batch);
            $("#date").val(data.date);
            $("#food_name").val(data.food_name);
            $("#food_weight").val(data.food_weight);
        },
        error:function () {
            layer.alert("请求出错，请重试", {icon: 5});
        }
    })
}

function getRecordList(pageNum) {
    var cow_batch = $("#cow_batch").val();
    var start_time = $("#start_time").val();
    var end_time = $("#end_time").val();
    $.ajax({
        url:"cowFood/getRecordList.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"cow_batch":cow_batch,"start_time":start_time,"end_time":end_time,"pageNum":pageNum},
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
        $("tbody").append("<tr><td>" + object.list[i].cow_batch + "</td><td>" + object.list[i].date + "</td><td>" +
            object.list[i].food_name + "</td><td>" + object.list[i].food_weight + "kg</td><td>" +
            "<button class='layui-btn layui-btn-primary layui-btn-sm' title='查看' onclick='jump("+object.list[i].id+")' type='button'><i class='layui-icon'>&#xe642;</i></button></td></tr>");
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

function updateRecord() {
    var reg = /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/;
    var id = $("#id").val();
    var cow_batch = $("#cow_batch").val();
    var date = $("#date").val();
    var food_name = $("#food_name").val();
    var food_weight = $("#food_weight").val();

    if (cow_batch == "") {
        layer.alert("请填写批次");
    } else if (date == "") {
        layer.alert("选择时间");
    } else if (food_name == "") {
        layer.alert("请填写食物");
    } else if (food_weight == "") {
        layer.alert("请填写质量");
    } else if (!reg.test(food_weight)) {
        layer.alert("质量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "cowFood/updateRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "id":id,
                "cow_batch": cow_batch,
                "date": date,
                "food_name": food_name,
                "food_weight": food_weight,
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

function jump(obj) {
    xadmin.open("查看","./cow-food-edit.html?purchase_batch=" + obj,600,400);
    /*x_admin_show("编辑","question-edit.html?question_number=" + obj);*/
}