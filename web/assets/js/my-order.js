/**
 * Created by dushang on 2016/8/19.
 */
define(["jQuery","accordion","jPages"],function ($) {
    $(function () {


        var listId=0;
        if(document.URL.split('#/')[1]){
            listId=document.URL.split('#/')[1]-1;
        }
        initPagers();

        initAccordion();

        /*初始化分页*/
        function initPagers() {
            $(".holder1").jPages({
                containerID : "itemContainer1",
                previous:"Pre",
                next:"Next",
                perPage:10
            });
            $(".holder2").jPages({
                containerID : "itemContainer2",
                previous:"Pre",
                next:"Next",
                perPage:10
            });
            $(".holder3").jPages({
                containerID : "itemContainer3",
                previous:"Pre",
                next:"Next",
                perPage:10
            });

            $('.holder1 a,.holder2 a,.holder3 a').addClass('btn btn-default');
        }

        function initAccordion() {
            $("#myOrder").accordion({
                'title':'My Projects',
                'menus':[
                    ['Proccessing','#/1'],
                    ['Finished','#/2']
                ],
                'width':'220px',
                'hasSubmenus':false,
                'foldIcon':'fa-chevron-right'
            });

            /*根据listId值选中*/
            $(".accordion-menu li").eq(listId).find('div').click();
            $('.order-list>div').eq(listId).show().siblings().hide();

        }

        /*根据点击左侧菜单显示对应列表*/
        function showList() {

            $('#myOrder').find("li").each(function () {

                if($(this).find('div').hasClass('chosen')){
                    listId=$(this).find('a').attr('href').split("/")[1]-1;
                }

            });
            $('.order-list>div').eq(listId).show().siblings().hide();

        }

        /*绑定左侧菜单点击事件*/
        $('.accordion-menu li').each(function () {
            $(this).on('click',showList);
        })
    });
});