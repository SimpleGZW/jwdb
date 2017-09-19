/**
 * Created by dushang on 12/04/16.
 */
/*解决由于固定头部带来的锚点位置偏移问题*/
$(document).ready(function(){
    $(window).on('hashchange',function(){
        var target = $(location.hash);
        if(target.length==1){
            var top = target.offset().top-60;
            if(top >=0){
                $('html,body').animate({scrollTop:top}, 1000);
            }
        }
    });
    /*刷新时动态效果*/
    $(function(){
        if(location.hash){
            var target = $(location.hash);
            if(target.length==1){
                var top = target.offset().top-60;
                if(top > 0){
                    $('html,body').animate({scrollTop:top}, 1000);
                }
            }
        }
    });
});