/**
 * Created by dushang on 22/03/16.
 */
$(document).ready(function(){
    var $submenu=$('.sub-menu');
    var $mainmenu=$('.main-menu');
    $('.sub-menu li').on('click',function(){
        $(this)[0].firstChild.click();
    });
    /*默认二级菜单隐藏*/
    $submenu.hide();
    /*第一次打开页面展开第一个一级菜单的子菜单*/
    $submenu.first().delay(400).slideDown(700);
    $mainmenu.find('i').first().addClass('ar-angle-up');
    $mainmenu.children().eq(1).find('div').css("border-top","1px solid #ddd");
    /*二级菜单的第一个元素距离一级菜单高度为10px*/
    $submenu.find('li').first().css("margin-top","10px");
    /*给选中的二级菜单添加样式，同时移除其他二级菜单的样式*/
    $submenu.on('click','li', function() {
        //$submenu.parent().siblings().find('li').removeClass('chosen');
        $mainmenu.find('li').removeClass('chosen');
        $(this).addClass('chosen');
    });
    $mainmenu.on('click','div', function() {
        /*点击某个一级菜单时，其子菜单下拉*/
        $(this).next('.sub-menu').slideToggle(700);
        /*修改子菜单的第一个元素属性*/
        $(this).next('.sub-menu').find('li').first().css("margin-top","10px");
        /*根据用户点击修改主菜单显示图标*/
        /*将点击菜单的同级菜单图标全部改为收起状态*/
        $(this).parent().siblings().find('i').removeClass('ar-angle-up').addClass('ar-angle-down');
        $mainmenu.find('div').css("border-top","0px solid #ddd");
        /*如果当前点击菜单的图标为收起状态则替换为展开状态，否则替换为收起状态*/
        if($(this).find('i').hasClass('ar-angle-up')){
            $(this).find('i').removeClass('ar-angle-up').addClass('ar-angle-down');
        }else{
            $(this).parent().next().find('div').css("border-top","1px solid #ddd");
            $(this).find('i').removeClass('ar-angle-down').addClass('ar-angle-up');
        }
        /**/

        /*同时，其同级的一级菜单的子菜单收起*/
        $(this).parent().siblings().find('.sub-menu').slideUp(700);

    });

});