function createRecord() {
    var reg = /^[1-9]\d*$/;
    var cow_batch = $("#cow_batch").val();
    var vaccine_name = $("#vaccine_name").val();
    var vaccination_date = $("#vaccination_date").val();
    var vaccination_number = $("#vaccination_number").val();
    var total_cow = $("#total_cow").val();

    if (cow_batch == "") {
        layer.alert("请填写批次");
    } else if (vaccine_name == "") {
        layer.alert("请填写疫苗名称");
    } else if (vaccination_date == "") {
        layer.alert("请选择时间");
    } else if (vaccination_number == "") {
        layer.alert("请填写接种数量");
    }else if (total_cow == "") {
        layer.alert("请填总数量");
    } else if (!reg.test(vaccination_number)||!reg.test(total_cow)) {
        layer.alert("数量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "cowVaccination/createRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "cow_batch": cow_batch,
                "vaccine_name": vaccine_name,
                "vaccination_date": vaccination_date,
                "vaccination_number": vaccination_number,
                "total_cow": total_cow
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
        url:"cowVaccination/getRecordByID.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"id":obj},
        success:function (data) {
            $("#id").val(data.id);
            $("#cow_batch").val(data.cow_batch);
            $("#vaccine_name").val(data.vaccine_name);
            $("#vaccination_date").val(data.vaccination_date);
            $("#vaccination_number").val(data.vaccination_number);
            $("#total_cow").val(data.total_cow);
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
        url:"cowVaccination/getRecordList.action",
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
        $("tbody").append("<tr><td>" + object.list[i].cow_batch + "</td><td>" + object.list[i].vaccine_name + "</td><td>" +
            object.list[i].vaccination_date + "</td><td>" + object.list[i].vaccination_number + "</td><td>" +object.list[i].total_cow + "</td><td>" +
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
    var reg = /^[1-9]\d*$/;
    var id = $("#id").val();
    var cow_batch = $("#cow_batch").val();
    var vaccine_name = $("#vaccine_name").val();
    var vaccination_date = $("#vaccination_date").val();
    var vaccination_number = $("#vaccination_number").val();
    var total_cow = $("#total_cow").val();

    if (cow_batch == "") {
        layer.alert("请填写批次");
    } else if (vaccine_name == "") {
        layer.alert("请填写疫苗名称");
    } else if (vaccination_date == "") {
        layer.alert("请选择时间");
    } else if (vaccination_number == "") {
        layer.alert("请填写接种数量");
    }else if (total_cow == "") {
        layer.alert("请填总数量");
    } else if (!reg.test(vaccination_number)||!reg.test(total_cow)) {
        layer.alert("数量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "cowVaccination/updateRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "id":id,
                "cow_batch": cow_batch,
                "vaccine_name": vaccine_name,
                "vaccination_date": vaccination_date,
                "vaccination_number": vaccination_number,
                "total_cow": total_cow
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
                layer.alert("请求出错，请重试", {icon: 5});
            }
        })
    }
}

function jump(obj) {
    xadmin.open("查看","./cow-vaccination-edit.html?purchase_batch=" + obj,600,400);
    /*x_admin_show("编辑","question-edit.html?question_number=" + obj);*/
}