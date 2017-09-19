/**
 * Created by dushang on 06/04/16.
 */

/*设置生成报告中导航栏菜单点击动态效果*/
$(document).ready(function(){
    var navbar=$('.navigation-bar');
    navbar.on('click','li',function(){
        $(this).addClass('chosen');
        $(this).siblings().removeClass('chosen');
    });
});
/*修改表格中氨基酸的样式*/
$(document).ready(function(){
   var aminoAcid=$(".amino-acid");
    $.each(aminoAcid,function(name,value){
        var aminoAcidStr= $.trim(value.innerHTML);
        value.innerHTML="<a>"+aminoAcidStr.substr(0,3)+"</a>"+aminoAcidStr.substr(3,aminoAcidStr.length-6)+"<a>"+aminoAcidStr.substr(aminoAcidStr.length-3,3)+"</a>";
    });

    showtooltip();
    setmodal();
});

/*设置生成报告中表格氨基酸的鼠标悬停提示信息效果*/
function showtooltip(){
    $('.table-bordered td a').each(function (name,value){
        $(this).on('mouseover',function(){
            $(this).css("cursor","pointer");
        });
        $(this).tooltip({
            items:$(this),
            position:{ my: "top+10px", at: "bottom" },
            content:function(){
                var returnName="";
                switch (value.innerHTML){
                    case "Ala":returnName="丙氨酸（A/Ala）" ; break;
                    case "Arg":returnName="精氨酸（R/Arg）" ; break;
                    case "Asn":returnName="天冬酰胺（N/Asn）" ; break;
                    case "Asp":returnName="天冬氨酸（D/Asp）" ; break;
                    case "Cys":returnName="半胱氨酸（C/Cys）" ; break;
                    case "Gln":returnName="谷氨酰胺（Q/Gln）" ; break;
                    case "Glu":returnName="谷氨酸（E/Glu）" ; break;
                    case "Gly":returnName="甘氨酸（G/Gly）" ; break;
                    case "His":returnName="组氨酸（H/his）" ; break;
                    case "Ile":returnName="异亮氨酸（I/Ile）" ; break;
                    case "Leu":returnName="亮氨酸（L/Leu）" ; break;
                    case "Lys":returnName="赖氨酸（K/Lys）" ; break;
                    case "Met":returnName="蛋氨酸（M/Met）" ; break;
                    case "Phe":returnName="苯丙氨酸（F/Phe）" ; break;
                    case "Pro":returnName="脯氨酸（P/Pro）" ; break;
                    case "Ser":returnName="丝氨酸（S/Ser）" ; break;
                    case "Thr":returnName="苏氨酸（T/Thr）" ; break;
                    case "Trp":returnName="色氨酸（W/Trp）" ; break;
                    case "Tyr":returnName="酪氨酸（Y/Tyr）" ; break;
                    case "Val":returnName="缬氨酸（V/Val）" ; break;
                    default : return;
                }
                return returnName;
            }
        });
    });
}

/*设置table模态框*/
function setmodal(){
    $('.gene-table table').each(function(name,value){
        /*获取表格的高度*/
        var tableHeight=value.clientHeight;
        /*获取当前文档的宽度*/
        var screenWidth=$(document).width();
        if(value.clientHeight>340){
            var tableHTML=value.parentNode.parentNode.parentNode.innerHTML;
            $(this).parent().css({"height":"360px","width":"780px","overflow-y":"hidden"})
                .parent().append("<div style='float: right;margin-top: 20px' data-toggle='modal' data-target='#table-modal'><span style='color:#666;'>详情</span><i class='ar ar-triangle-down' style='margin-left: 5px;color:#3a6bc5'></i></div>")
                .on('mouseover',function(){
                    $(this).css("cursor","pointer")
                });
            $(this).parent().parent().append("<div class='modal fade' id='table-modal' tabindex='-1' role='dialog' aria-hidden='true'> " +
                "<div class='modal-dialog'> " +
                "<div class='modal-content' style='width: 920px; right: "+(screenWidth-920)/2+"px'> " +/*由于插件无法将模态框居中，需要手动设置居中*/
                "<div class='modal-body'>"+
                "<div class='system-report'  style='height: "+(tableHeight+180)+"px'>" + tableHTML+ "</div>"+/*修改原div的高度*/
                "</div> " +
                "</div></div></div>");
            /*调用显示tooltip函数*/
            showtooltip();
        }
    });
}
