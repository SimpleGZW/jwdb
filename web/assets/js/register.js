/**
 * Created by dushang on 2016/8/22.
 */
define(["jQuery"],function ($) {

    $(function () {

        initPages();

        /*初始化页面*/
        function initPages() {

            /*隐藏所有的提示信息*/
            $('.register-form').find('.prompt').hide();

            setBasicStyle();

            /*验证用户名是否合法*/
            $('#userName').on('blur',function () {
                validateUsername();
            });

            /*用户名合法时，验证密码是否合法*/
            $('#password1').on('blur',function () {
                if(validateUsername()){
                    validatePassword();
                }
            });
            /*用户名密码都合法时，验证密码是否一致*/
            $('#password2').on('blur',function () {
                if(validateUsername()&&validatePassword()){
                    validatePwdConsistency();
                }
            });

            nextStep();


            /*手动提交表单*/
            $('#saveSubmit').on('click',function () {
                var url=$('#registerForm').attr('action');
                var param={};
                param['username']=$('#registerForm input[name=username]').val();
                param['password']=$('#registerForm input[name=password1]').val();
                param['name']=$('#registerForm input[name=name]').val();
                param['company']=$('#registerForm input[name=company]').val();
                param['phone']=$('#registerForm input[name=phone]').val();
                param['address']=$('#registerForm input[name=address]').val();
                param['agent']=$('#registerForm input[name=agent]').val();
                param['invoiceTitle']=$('#registerForm input[name=invoiceTitle]').val();
                param['responsibleParty']=$('#registerForm input[name=responsibleParty]').val();
                param['email']=$('#registerForm input[name=email]').val();

                if(param['name']==""){
                    $('#name').parent().parent().parent().find('.warning-info').show();
                    return false;
                }else if(param['company']==""){
                    $('#company').parent().parent().parent().find('.warning-info').show();
                    return false;
                }else if(param['phone']==""){
                    $('#phone').parent().parent().parent().find('.warning-info').show();
                    return false;
                }else if(param['address']==""){
                    $('#address').parent().parent().parent().find('.warning-info').show();
                    return false;
                }else{
                    //msg为返回完成注册的用户名
                    $.post(url,param,function (msg) {
                        // msg="zhangsan";     /*连接后台后注释该行代码*/
                        $('#accountDetail').fadeOut(1000,function () {
                            $('#registerFinished').fadeIn();
                            $('#finished').addClass('active');

                            $('#registerFinished').find('.user-name a').text(msg);
                        })

                    })
                }

            });
        }


        function setBasicStyle(){

            /*为所有的输入框添加focus样式*/
            $('.register-form form').find('input').on('focus',function () {
                $(this).parent().css({
                    border:'1px solid #5C8CE6',
                    outline:'thin solid #5C8CE6'
                });

                /*移除当前的成功和失败样式*/
                if($(this).parent().parent().find('i').hasClass('input-failed')){
                    $(this).parent().parent().find('i').removeClass('fa-info-circle input-failed').addClass('fa-info-circle');
                }else if($(this).parent().parent().find('i').hasClass('input-success')){
                    $(this).parent().parent().find('i').removeClass('fa-check-circle input-success').addClass('fa-info-circle');
                }
                /*隐藏警告信息*/
                $(this).parent().parent().parent().find('.warning-info').hide();

                /*显示提示信息*/
                $(this).parent().parent().find('.prompt').show();

            });
            /*为所有的输入框添加blur样式*/
            $('.register-form form').find('input').on('blur',function () {
                $(this).parent().css({
                    border:'1px solid #ccc',
                    outline:'none'
                });

                /*隐藏提示信息*/
                $(this).parent().parent().find('.prompt').hide();

            });

            /*显示密码图标点击样式*/
            $('.password-toggle').on('click',function () {
                $(this).hide().siblings().show();
                if($(this).parent().find('input').attr('type')=='text'){
                    $(this).parent().find('input').attr('type','password');
                }else{
                    $(this).parent().find('input').attr('type','text');
                }
            })

        }



        /*正则表达式用于验证用户名*/
        function validateUsername() {

            var regExp=new RegExp("^[A-Za-z0-9]{6,20}$");     //用于验证用户名
            var username=$('#userName').val();

            if(!regExp.test(username)){
                $('#userName').parent().parent().find('i').removeClass('fa-info-circle fa-check-circle').addClass('fa-times-circle input-failed').parent().find('.prompt').show();
                $('#userName').parent().css({
                    border:'1px solid #f67e7e',
                    outline:'thin solid #f67e7e'
                });

                return false;
            }else{
                $('#userName').parent().parent().find('i').removeClass('fa-info-circle input-failed').addClass('fa-check-circle input-success');

                return true;
            }
        }

        /*正则表达式用于验证密码*/
        function validatePassword() {
            var regExp1=new RegExp("^[0-9]{6,20}$");            //用于验证密码，安全等级低，不符合要求
            var regExp2=new RegExp("^[a-zA-Z]{6,20}$");            //用于验证密码，安全等级低，不符合要求
            var regExp3=new RegExp("(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{6,20}$");            //用于验证密码，匹配字母和数字的6-20位组合，同时包含字母和数字
            var regExp4=new RegExp("^(?![^A-Za-z]+$)(?![^0-9]+$)[\x21-x7e]{6,20}$");            //用于验证密码，匹配字母、数字或特殊字符的6-20位组合，至少包含字母和数字

            var password1=$('#password1').val();

            if(regExp4.test(password1)){
                $('#password1').parent().parent().find('i').last().removeClass('fa-info-circle input-failed').addClass('fa-check-circle input-success');
                if(regExp3.test(password1)){
                    /*密码强度为中*/
                    $('#password1').parent().parent().parent().find('.warning-info').eq(1).show();
                }else{
                    /*密码强度为高*/
                    $('#password1').parent().parent().parent().find('.warning-info').eq(2).show().css('color','#29cc96');
                }
                return true;
            }else{
                if(regExp1.test(password1)||regExp2.test(password1)){
                    /*密码强度为低*/
                    $('#password1').parent().parent().find('i').last().removeClass('fa-info-circle fa-check-circle').addClass('fa-times-circle input-failed');
                    $('#password1').parent().css({
                        border:'1px solid #f67e7e',
                        outline:'thin solid #f67e7e'
                    });
                    $('#password1').parent().parent().parent().find('.warning-info').eq(0).show();

                }else{
                    /*密码不符合要求*/
                    $('#password1').parent().parent().find('i').last().removeClass('fa-info-circle fa-check-circle').addClass('fa-times-circle input-failed').parent().find('.prompt').show();
                    $('#password1').parent().css({
                        border:'1px solid #f67e7e',
                        outline:'thin solid #f67e7e'
                    });
                }
                return false;
            }
        }

        /*验证密码一致性*/
        function validatePwdConsistency() {
            var password1=$('#password1').val();
            var password2=$('#password2').val();

            if(password1==password2){
                $('#password2').parent().parent().find('i').last().removeClass('fa-info-circle input-failed').addClass('fa-check-circle input-success');
                return true;
            }else{
                $('#password2').parent().parent().find('i').last().removeClass('fa-info-circle fa-check-circle').addClass('fa-times-circle input-failed').parent().find('.prompt').show();
                return false;
            }
        }

        /*下一步按钮点击事件*/
        function nextStep() {
            $('#nexStep').on('click',function () {
                //获取checkbox值用于判断用户是否同意注册协议
                var agree=$('#checkbox').is(':checked');
                /*如果没有勾选同意协议，给出提示信息*/
                if(validateUsername()&&validatePassword()&&validatePwdConsistency()){
                    if(!agree){
                        $('.agreement').find('.warning-info').show();
                    }else{
                        //获取用户名用于判断用户名是否重复
                        var username=$('#userName').val();

                        $.ajax({
                            url:webRoot+'/checkAccount',
                            type:'POST',
                            data:{username:username},
                            cache:false ,
                            /*数据传送成功*/

                            //返回的data为布尔值，用户名合法，返回true，不合法返回false
                            success:function (data) {
                                // data=true;      //连接后台时注释该行代码

                                /*如果用户名合法*/
                                if(data){
                                    $('#accountDetail').show();
                                    $('#accountInfo').css({
                                        'position':'relative',
                                        'z-index':'-1',
                                        'margin-bottom':'100px'
                                    });
                                    $('#accountInfo').animate({
                                        marginTop:'-490'
                                    },1000,'linear',function () {
                                        $('#accountInfo').hide();
                                        $('#details').addClass('active');

                                    });
                                }else{
                                    /*用户名已经注册，显示出错信息*/
                                    $('#userName').parent().parent().parent().find('.warning-info').show();
                                }


                            },
                            /*数据传送失败*/
                            error:function () {
                                alert("failed");
                            }
                        });
                    }
                }

            });
            
            $('#nexStep').on('blur',function () {
                $('.agreement').find('.warning-info').hide();
            })
        }

    })
});