/**
 * Created by dushang on 07/04/16.
 */
define(["jQuery"],function($){
    $(document).ready(function () {
        $('.user-info').hover(function () {
            $(this).find("ul").stop().slideDown();
        },function () {
            $(this).find("ul").stop().slideUp();
        });

        $('.user-info li').on('click',function () {
            // alert('hello');
            window.location.href=$(this).find('a').attr('href');
        })
    });
});
