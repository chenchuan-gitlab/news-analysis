function createRecord() {
    var product_type = $("#product_type").val();
    var product_name = $("#product_name").val();
    var energy_content = $("#energy_content").val();
    var energy_rate = $("#energy_rate").val();
    var protein_content = $("#protein_content").val();
    var protein_rate = $("#protein_rate").val();
    var fat_conten_content = $("#fat_conten_content").val();
    var fat_conten_rate = $("#fat_conten_rate").val();
    var carbohydrate_content = $("#carbohydrate_content").val();
    var carbohydrate_rate = $("#carbohydrate_rate").val();
    var sodium_content = $("#sodium_content").val();
    var sodium_rate = $("#sodium_rate").val();
    var img_url = $("#img_url").val();


    if (product_type == "" || product_name == "" || energy_content == "" || energy_rate == "" || protein_content == "" || protein_rate == "" ||
        fat_conten_content == "" || fat_conten_rate == "" || carbohydrate_content == "" || carbohydrate_rate == "" || sodium_content == "" || sodium_rate == "") {
        layer.alert("请填写产品完整信息");
    } else if (img_url == "") {
        layer.alert("请上传图片");
    } else {
        $.ajax({
            url: "productInfo/createRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "product_type": product_type,
                "product_name": product_name,
                "energy_content": energy_content,
                "energy_rate": energy_rate,
                "protein_content": protein_content,
                "protein_rate": protein_rate,
                "fat_conten_content": fat_conten_content,
                "fat_conten_rate": fat_conten_rate,
                "carbohydrate_content": carbohydrate_content,
                "carbohydrate_rate": carbohydrate_rate,
                "sodium_content": sodium_content,
                "sodium_rate": sodium_rate,
                "img_url": img_url
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
        url: "cowVaccination/getRecordByID.action",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: {"id": obj},
        success: function (data) {
            $("#id").val(data.id);
            $("#cow_batch").val(data.cow_batch);
            $("#vaccine_name").val(data.vaccine_name);
            $("#vaccination_date").val(data.vaccination_date);
            $("#vaccination_number").val(data.vaccination_number);
            $("#total_cow").val(data.total_cow);
        },
        error: function () {
            layer.alert("请求出错，请重试", {icon: 5});
        }
    })
}

function getRecordList(pageNum) {
    $.ajax({
        url: "productInfo/getRecordList.action",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        data: {"pageNum": pageNum},
        success: function (data) {
            showData(data);
        },
        error: function () {
            layer.alert("请求出错，请重试", {icon: 5});
        }
    })
}


function showData(object) {
    $("#Images").empty();
    layui.config({
        base: "../imgjs/"
    }).use(['flow', 'form', 'layer', 'upload'], function () {
        var flow = layui.flow,
            form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            upload = layui.upload,
            $ = layui.jquery;

        var data = object.list;
        setTimeout(function () {
            for(var i = 0;i<data.length;i++){
                $("#Images").append('<li><img id="'+data[i].id+'" layer-src="' + data[i].img_url + '" src="' + data[i].img_url + '" alt="' + data[i].product_name + '"><div class="operate"><div class="check">' + data[i].product_name + '</div><i class="layui-icon img_del">&#xe640;</i></div></li>');
            }
        },500);
        form.render();

        //设置图片的高度
        $(window).resize(function () {
            $("#Images li img").height($("#Images li img").width());
        })

        //弹出层
        $("body").on("click", "#Images img", function () {
            var id = this.id;
            $.ajax({
                type:"GET",
                url:"productInfo/getRecordByID.action",
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                data:{"id":id},
                success:function (res) {
                    var content="<div style='padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>产品类型："+res.product_type+"<br>" +
                        "产品名称："+res.product_name+"<br><br><table class='layui-table layui-form' lay-skin='line'><thead><tr><th>项目</th><th>每100ml含量</th><th>营养参考价值%</th></tr></thead>"+
                        "<tbody><tr><td>能量</td><td>"+res.energy_content+"</td><td>"+res.energy_rate+"</td></tr>"+
                        "<tbody><tr><td>蛋白质</td><td>"+res.protein_content+"</td><td>"+res.protein_rate+"</td></tr>"+
                        "<tbody><tr><td>脂肪</td><td>"+res.fat_conten_content+"</td><td>"+res.fat_conten_rate+"</td></tr>"+
                        "<tbody><tr><td>碳水化合物</td><td>"+res.carbohydrate_content+"</td><td>"+res.carbohydrate_rate+"</td></tr>"+
                        "<tbody><tr><td>钠</td><td>"+res.sodium_content+"</td><td>"+res.sodium_rate+"</td></tr></tbody></table></div>";
                    layer.open({
                        type: 1
                        ,title: false //不显示标题栏
                        ,closeBtn: false
                        ,area: '500px;'
                        ,shade: 0.8
                        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                        ,btn: [ '确定']
                        ,btnAlign: 'c'
                        ,moveType: 1 //拖拽模式，0或者1
                        ,content: content
                    });
                },
                error:function () {
                    layer.alert("请求出错，请重试", {icon: 5});
                }
            })
        })

        //删除单张图片
        $("body").on("click", ".img_del", function () {
            var _this = $(this);
            layer.confirm('确定删除图片"' + _this.siblings().find("input").attr("title") + '"吗？', {
                icon: 3,
                title: '提示信息'
            }, function (index) {
                _this.parents("li").hide(1000);
                setTimeout(function () {
                    _this.parents("li").remove();
                }, 950);
                layer.close(index);
            });
        })
    })
    $("#recordSpan").text("共有数据：" + object.total + " 条");
    //分页
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page',
            count: object.total,
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
    } else if (total_cow == "") {
        layer.alert("请填总数量");
    } else if (!reg.test(vaccination_number) || !reg.test(total_cow)) {
        layer.alert("数量格式错误，请重新输入");
    } else {
        $.ajax({
            url: "cowVaccination/updateRecord.action",
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "id": id,
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
    xadmin.open("查看", "./cow-vaccination-edit.html?purchase_batch=" + obj, 600, 400);
    /*x_admin_show("编辑","question-edit.html?question_number=" + obj);*/
}