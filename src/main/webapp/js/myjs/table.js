// table全选
layui.use(['form', 'element', 'table', 'jquery'], function () {
    var form = layui.form,
        element = layui.element,
        table = layui.table,
        $ = layui.jquery;
    //全选功能
    form.on('checkbox(allChoose)', function (data) {
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
        child.each(function (index, item) {
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
    //全选和部分选中时候,表头全选按钮的样式变化
    form.on('checkbox(itemChoose)', function (data) {
        var sib = $(data.elem).parents('table').find('tbody input[type="checkbox"]:checked').length;
        var total = $(data.elem).parents('table').find('tbody input[type="checkbox"]').length;
        if (sib == total) {
            $(data.elem).parents('table').find('thead input[type="checkbox"]').prop("checked", true);
            form.render();
        } else {
            $(data.elem).parents('table').find('thead input[type="checkbox"]').prop("checked",
                false);
            form.render();
        }
    });
});

function tableChecked(){
    var checkedData = '';
    $("input:checkbox:checked").each(function(i){
        var itemData =  $(this).val();
        if(this.name != 'allCheck'){
            checkedData += itemData + ",";
        }
    });
    if(checkedData.length != 0){
        checkedData = checkedData.substr(0,checkedData.length - 1);
    }
    return checkedData;
}