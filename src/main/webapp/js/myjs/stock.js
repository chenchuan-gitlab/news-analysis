function getRecordList(pageNum) {
    var production_batch = $("#production_batch").val();
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    $.ajax({
        url:"stock/getInfoList.action",
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"production_batch":production_batch,"product_type":product_type,"product_name":product_name,"pageNum":pageNum},
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
        $("tbody").append("<tr><td>" + object.list[i].warehouse_number + "</td><td>" + object.list[i].production_batch + "</td><td>" + object.list[i].product_type + "</td><td>" +
            object.list[i].product_name + "</td><td>" + object.list[i].inventory_quantity + "</td><td>" + object.list[i].product_date + "</td><td>" +
            object.list[i].warehousing_date + "</td></tr>");
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