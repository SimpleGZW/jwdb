define(["jQuery", "bootstrapJs"],function ($) {
    $(document).ready(function () {

        inputStatus('username', 'input1', 'delete1');
        inputStatus('password', 'input2', 'delete2');

        $('.btn-block').on('click', illegalInput);

        if(msg !== ""){

            $('#warning-info').html(msg);
            $(".alert-danger").show();
            setTimeout(function(){$('.alert-danger').stop().fadeOut();},3000);
        }

        function illegalInput() {
            var username = $('#username').val();
            var password = $('#password').val();
            var warningInfo = "";

            //前台验证
            if($.trim(username) === ""){
                warningInfo = "账号不能为空";
            }else if($.trim(password) === ""){
                warningInfo = "密码不能为空";
            }else if(!($('input:radio[id="user"]:checked').val()||$('input:radio[id="manager"]:checked').val())){
                warningInfo = "请选择角色";
            }

            if(warningInfo !== ""){
                //不提交表单
                $('#warning-info').html(warningInfo);
                $(".alert-danger").show();
                setTimeout(function(){$('.alert-danger').stop().fadeOut();},3000);
                return false;
            }else{
                //提交表单
                return true;   //阻止事件冒泡
            }
        }

        //输入框操作
        function inputStatus(inputId, Class, delId) {
            $('#'+inputId).on('focus', deleteText);
            function deleteText() {
                $('.' + Class).css({'border': '1px solid #386cca'});
                var user = $('#'+inputId).val();
                if($.trim(user) !== ''){
                    $('#' + delId).css('display','block');
                }
                $('#'+inputId).on('input', function () {
                    var user = $('#'+inputId).val();
                    if($.trim(user) !== ''){
                        $('#' + delId).css('display','block');
                    }else{
                        $('#' + delId).css('display','none');
                    }
                });
            }
            $('#'+inputId).on('blur', loseFocus);
            function loseFocus() {
                $('.' + Class).css({'border':'1px solid #ccc'});
                $('#' + delId).css('display','none');
            }
            $('#' + delId).on('mousedown', close);
            function close() {
                $('#'+inputId).val(function () {
                    return "";
                });
                $(this).css('display', 'none');
            }
        }


        /*选择登录角色*/
        $('.login-role').on('click',function () {
            $(this).addClass('role-active').siblings().removeClass('role-active');
            if($(this).attr('data-role')=='user'){
                $('.role input[id="user"]').click();
            }else{
                $('.role input[id="manager"]').click();
            }
        })
    });
});