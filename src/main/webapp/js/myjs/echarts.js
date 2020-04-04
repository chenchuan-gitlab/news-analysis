$(document).ready(function () {
    getMsg();
})

var temperatures = [];
var humiditys = [];
var dates = [];

function showEcharts(t,h,time) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '温湿度监控图'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['温度','湿度']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dates
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'温度',
                type:'line',
                stack: '总量',
                data:temperatures
            },
            {
                name:'湿度',
                type:'line',
                stack: '总量',
                data:humiditys
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getMsg() {
    //setTimeout(getMsg,2000);
    $.ajax({
        url:"getTH/getTHMsg.action",
        type:"GET",
        dataType:"json",
        success:function (data) {
            var temperature = data.temperature;
            var humidity = data.humidity;
            var date = data.date;
            if(temperatures.length==10){
                temperatures.shift();
                temperatures.push(temperature);
            }else{
                temperatures.push(temperature);
            }
            if(humiditys.length==10){
                humiditys.shift();
                humiditys.push(humidity);
            }else{
                humiditys.push(humidity);
            }
            if(dates.length==10){
                dates.shift();
                dates.push(date);
            }else {
                dates.push(date);
            }
            showEcharts(temperatures,humiditys,dates);
        }
    })
}
