/**
 * Created by dushang on 07/04/16.
 */
$(document).ready(function(){
    /*点击搜索按钮，执行highlight函数*/
    $("#basic-addon").on('click',highlight);
    /*按下enter键时，执行highlight函数*/
    $('#search-text').keydown(function(e){
        var key= e.which;/*给出按键的索引值*/
        if(key==13){
            highlight();
            /*阻止页面刷新*/
            return false;
        }
    });
    $('#search-text').on("click",function(){
        /*如果当前显示为警告信息，则清空警告信息，字体颜色还原*/
        if($(this).css("color")== "rgb(245, 79, 79)"){
            /*注意rgb(255, 0, 0)的写法，缺少空格则条件不能成立*/
            $.ajax({url:"",success:function(){
                $("#search-text").val("").css("color","#999");
            }});
        }
    });

    /*设置当前查找到内容初始index为0*/
    var i=0;
    var lastText="";
    /*将查找到的内容高亮*/
    function  highlight(){
        var flag=0;

        /*清除上一次的查找高亮内容*/
        clearSelection();

        /*获取输入的查找内容*/
        var searchText=$("#search-text").val();

        /*如果输入内容为空*/
        if($.trim(searchText)==""){
            $.ajax({url:"",success:function(){
                $("#search-text").val("输入内容不能为空！").css({"color":"#f54f4f","border":"1px solid #f54f4f","border-right":"none"}).blur();//设置当前元素失去焦点，需要鼠标点击事件获取焦点
            }});
            return;
        }

        searchText = $('#search-text').val();
        /*创建正则表达式，g表示全局的，如果不用g，则查找到第一个就不会继续向下查找了*/
        var regExp = new RegExp(searchText, 'g');

        /*获取main-part部分的所有文字*/
        var content= $(".main-part").text();
        /*判断是否与正则表达式匹配*/
        if(!regExp.test(content)){
            $.ajax({url:"",success:function(){
                $("#search-text").val("未查询到任何有效内容！").css({"color":"#f54f4f","border":"1px solid #f54f4f","border-right":"none"}).blur();
            }});
            return;
        }else{
            if(lastText!=searchText){
                i=0;
                lastText=searchText;
            }
        }
        $('h3,h4,p').each(function(){
            var html=$(this).html();
            /*将找到的关键字替换，加上highlight属性*/
            var newHtml = html.replace(regExp, '<span class="highlight">'+searchText+'</span>');

            $(this).html(newHtml);//更新；
            flag = 1;
        });

        if(flag==1){
            if($(".highlight").size()>1){
                var _top=$(".highlight").eq(i).offset().top+$(".highlight").eq(i).height();
                $(".highlight").eq(i).addClass("currentOne");
                $(".highlight").eq(i-1).removeClass('currentOne');
            }else{
                var _top = $(".highlight").offset().top+$(".highlight").height();
            }
            $("html, body").animate({ scrollTop: _top-90});
            i++;
            if (i > $(".highlight").size() - 1) {
                i = 0;
            }
        }
    }

    /*清除高亮*/
    function clearSelection(){
        $('h3,h4,p').each(function(){
            /*找到所有highlight属性元素*/
            $(this).find('.highlight').each(function () {
                /*去掉它们的属性*/
                $(this).replaceWith($(this).html());
            })
        })
    }
});