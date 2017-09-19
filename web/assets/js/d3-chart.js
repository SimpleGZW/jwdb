/**
 * Created by dushang on 01/04/16.
 */

$(document).ready(function() {


    /*绘制雷达图部分*/
    var  mapdata=new Array();
    /*从后台获取绑定数据*/
    //获取项目名
    var pathName=window.document.location.pathname;
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

    $.get(projectName+"/radarChart",function(serverData,status){
        mapdata[0]=serverData.eatNum;
        mapdata[1]=serverData.exerciseNum;
        mapdata[2]=serverData.sleepingNum;
        mapdata[3]=serverData.drinkingNum;
        mapdata[4]=serverData.smokingNum;

        var ctx = $("#radar-life").get(0).getContext("2d");

        var data = {
            labels: ["饮食状况", "锻炼状况", "睡眠状况", "饮酒状况", "吸烟状况"],
            datasets: [{
                fillColor: "rgba(173,197,242,0.7)",
                strokeColor: "rgba(173,197,242,0.7)",
                pointStrokeColor: "rgba(173,197,242,0.7)",
                pointHighlightStroke: "#f45f4f",
                data:mapdata
                //data:[60,60,60,60,60]
            }]
        };

        Chart.Tooltip = Chart.Element.extend({
            draw : function(){

                var ctx = this.chart.ctx;

                ctx.font = Chart.helpers.fontString(this.fontSize,this.fontStyle,this.fontFamily);

                this.xAlign = "center";
                this.yAlign = "above";

                //Distance between the actual element.y position and the start of the tooltip caret
                var caretPadding = this.caretPadding = 2;

                var tooltipWidth = ctx.measureText(this.text).width + 2*this.xPadding,
                    tooltipRectHeight = this.fontSize + 2*this.yPadding,
                    tooltipHeight = tooltipRectHeight + this.caretHeight + caretPadding;

                if (this.x + tooltipWidth/2 >this.chart.width){
                    this.xAlign = "left";
                } else if (this.x - tooltipWidth/2 < 0){
                    this.xAlign = "right";
                }

                if (this.y - tooltipHeight < 0){
                    this.yAlign = "below";
                }


                var tooltipX = this.x - tooltipWidth/2,
                    tooltipY = this.y - tooltipHeight;


                if (this.text == '优') ctx.fillStyle = '#28cb94';
                if (this.text == '良') ctx.fillStyle = '#f69e4f';
                if (this.text == '差') ctx.fillStyle = '#f47c79';
                ctx.fillStyle = this.fillColor;

                // Custom Tooltips
                if(this.custom){
                    this.custom(this);
                }
                else{
                    switch(this.yAlign)
                    {
                        case "above":
                            //Draw a caret above the x/y
                            ctx.beginPath();
                            ctx.moveTo(this.x,this.y - caretPadding);
                            ctx.lineTo(this.x + this.caretHeight, this.y - (caretPadding + this.caretHeight));
                            ctx.lineTo(this.x - this.caretHeight, this.y - (caretPadding + this.caretHeight));
                            ctx.closePath();

                            if (this.text == '优') ctx.fillStyle = '#28cb94';
                            if (this.text == '良') ctx.fillStyle = '#f69e4f';
                            if (this.text == '差') ctx.fillStyle = '#f47c79';
                            ctx.fill();
                            break;
                        case "below":
                            tooltipY = this.y + caretPadding + this.caretHeight;
                            //Draw a caret below the x/y
                            ctx.beginPath();
                            ctx.moveTo(this.x, this.y + caretPadding);
                            ctx.lineTo(this.x + this.caretHeight, this.y + caretPadding + this.caretHeight);
                            ctx.lineTo(this.x - this.caretHeight, this.y + caretPadding + this.caretHeight);
                            ctx.closePath();
                            ctx.fill();
                            break;
                    }

                    switch(this.xAlign)
                    {
                        case "left":
                            tooltipX = this.x - tooltipWidth + (this.cornerRadius + this.caretHeight);
                            break;
                        case "right":
                            tooltipX = this.x - (this.cornerRadius + this.caretHeight);
                            break;
                    }

                    Chart.helpers.drawRoundedRectangle(ctx,tooltipX,tooltipY,tooltipWidth,tooltipRectHeight,this.cornerRadius);

                    if (this.text == '优') ctx.fillStyle = '#28cb94';
                    if (this.text == '良') ctx.fillStyle = '#f69e4f';
                    if (this.text == '差') ctx.fillStyle = '#f47c79';
                    ctx.fill();

                    ctx.fillStyle = this.textColor;
                    ctx.textAlign = "center";
                    ctx.textBaseline = "middle";
                    ctx.fillText(this.text, tooltipX + tooltipWidth/2, tooltipY + tooltipRectHeight/2);
                }
            }
        });
        var options = {
            scaleOverride: true,
            pointLabelFontSize : 14,
            pointLabelFontColor : "#333",
            pointLabelFontFamily : "Microsoft YaHei",
            scaleSteps: 5,
            scaleStepWidth: 20,
            pointDotRadius:1,
            tooltipTemplate:"<%if(value>80){%><%='优'%><%}else if(value>40){%><%='良'%><%}else{%><%='差'%><%}%>",
            tooltipFontFamily:"Microsoft YaHei",
            tooltipFillColor:'#386cca',
            tooltipXPadding:'12',
            tooltipYPadding:'3',
        };
        var life = new Chart(ctx).Radar(data, options);

    });


    /*绘制健康评估结果统计图部分*/
    var dataset1=new Array();
    var dataset2=new Array();

    $.get(projectName+"/resultDesign",function(serverData,status){
        // 绘制“全基因组健康评估结果统计”图
        var labels1 = ["生殖和肾及泌尿系统","免疫和内分泌系统","肿瘤类系统","肥胖和糖尿病、运动、消化以及代谢系统","心血管系统","体表系统","呼吸系统","神经系统"];
        dataset1[0]=serverData.SSMSystemSSG;
        dataset1[1]=serverData.MNSystemSSG;
        dataset1[2]=serverData.ZSystemSSG;
        dataset1[3]=serverData.FTYXDSystemSSG;
        dataset1[4]=serverData.XSystemSSG;
        dataset1[5]=serverData.TSystemSSG;
        dataset1[6]=serverData.HXSystemSSG;
        dataset1[7] = serverData.SJSystemBYG;
        dataset2[0]=serverData.SSMSystemBYG;
        dataset2[1]=serverData.MNSystemBYG;
        dataset2[2]=serverData.ZSystemBYG;
        dataset2[3]=serverData.FTYXDSystemBYG;
        dataset2[4]=serverData.XSystemBYG;
        dataset2[5]=serverData.TSystemBYG;
        dataset2[6]=serverData.HXSystemBYG;
        dataset2[7] = serverData.SJSystemBYG;
        // 矩形的高度和间距
        var rectHeight = 20;
        var rectPadding = 10;
        var widthScale = d3.scale.linear().domain([0, 40, 400]).range([10, 50, 400]);
        // 添加组
        var group1 = d3.select("#gene").selectAll("g").data(labels1).enter().append("g").attr("transform","translate(300 0)");


        // 添加矩形（颜色：#29cc96）
        group1.append("rect").attr("width", function(d, i) {
            return widthScale(dataset1[i]);
        }).attr("height", 20).style("x", "0").attr("y",function(d, i) {
            return 30 * i;
        }).style("fill","#29cc96");

        // 添加矩形中的文字
        group1.append("text").attr("dx",function(d, i) {
            return widthScale(dataset1[i]) / 2;
        }).attr("dy", function(d, i) {
            return 30 * i + 16;
        }).style("text-anchor", "middle").style("fill", "#ffffff").text(function(d, i) {return dataset1[i];});


        // 添加矩形（颜色：#f45f4f）
        group1.append("rect").attr("width", function(d, i) {
            return widthScale(dataset2[i]);
        }).attr("height", rectHeight).attr("x", function(d,i) {
            return widthScale(dataset1[i]);
        }).attr("y",function(d, i) {
            return (rectHeight + rectPadding) * i;
        }).style("fill","#f45f4f");

        // 添加矩形中的文字
        group1.append("text").attr("dx", function(d,i) {
            return widthScale(dataset1[i]) + widthScale(dataset2[i]) / 2;
        }).attr("dy", function(d, i) {
            return 30 * i + 16;
        }).style("text-anchor", "middle").style("fill", "#ffffff").text(function(d, i) {return dataset2[i];});



        // 添加坐标标签
        group1.append("text").attr("class", "label1").attr("dx", -10)
            .attr("dy", function(d, i) {
                return (rectHeight + rectPadding) * i + 16;
            }).style("text-anchor", "end").style("font-size","14px").text(function(d, i) {
            return labels1[i];
        });
    });



    // 绘制“健康状况风险评估”图
    var dataset3=new Array()
    $.get(projectName+'/eightSystemRickAccess',function(serverData,status){
        dataset3[0]=serverData.TZZS;
        dataset3[1]=serverData.XHSystemRA;
        dataset3[2]=serverData.XXGSystemRA;
        dataset3[3]=serverData.NDSystemRA;
        dataset3[4]=serverData.SJSystemRA;
        dataset3[5]=serverData.MYSystemRA;
        dataset3[6]=serverData.ZLSytemRA;
        dataset3[7]=serverData.HXSystemRA;
        dataset3[8]=serverData.YDSystemRA;
        dataset3[9]=serverData.TBSystemRA;
        dataset3[10]=serverData.MNSystemRA;
        dataset3[11]=serverData.SZSystemRA;

        var rectPadding = 10;

        var labels2 = ["（BMI）体质指数","消化系统","心血管循环系统","内分泌与代谢系统","神经系统","免疫系统","肿瘤类系统","呼吸系统","运动系统","体表系统","泌尿系统","生殖系统"];
        // 添加组
        var group2 = d3.select("#health").selectAll("g").data(labels2).enter().append("g").attr("transform", "translate(160 0)");
        // 添加矩形
        group2.append("rect").attr("width", function(d, i) {
            return dataset3[i] * 100;
        }).attr("height", 24).attr("x", 0).attr("y", function(d, i) {
            return (24 + rectPadding) * i;
        }).style("fill", function(d, i) {
            if (dataset3[i] == 1) return "#29cc96";
            else if (dataset3[i] == 2 || dataset3[i] == 3) return "#f59d4f";
            else return "#f54f4f";
        });
        // 添加文字
        group2.append("text").attr("class", "label2").attr("dx", -30).attr("dy", function(d, i) {
            return (24 + rectPadding) * i + 16;
        }).style("text-anchor", "end").style("font-size","16px").text(function(d, i) {
            return labels2[i];
        });
    });
});

