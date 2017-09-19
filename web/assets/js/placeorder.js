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
            secondStep();
            thirdStep();
            fourthStep();
            fifthStep();
            sixthStep();
            seventhStep();
            eighthStep();
            formSubmit();
        }

    /*手动提交表单*/
        function formSubmit() {
            $('#ninthStep').on('click',function () {
                var basicData={
                    projectname:'',
                    projecttype:'',
                    loadstepnum:'',
                    temperature:'',
                    materialnum:'',
                    nodenum:'',
                    temExamplePath:'',
                    outputPPath:'',
                    matdataPath:'',
                    picturenum:''
                };

                basicData.projectname=$('#registerForm input[name=projectname]').val();
                basicData.projecttype=$('#registerForm input[name=projecttype]').val();
                basicData.loadstepnum=$('#registerForm input[name=loadstepnum]').val();
                basicData.temperature=$('#registerForm input[name=temperature]').val();
                basicData.materialnum=$('#registerForm input[name=materialnum]').val();
                basicData.nodenum=$('#registerForm input[name=nodenum]').val();
                basicData.temExamplePath=$('#registerForm input[name=temExamplePath]').val();
                basicData.outputPPath=$('#registerForm input[name=outputPPath]').val();
                basicData.matdataPath=$('#registerForm input[name=matdataPath]').val();
                basicData.picturenum =$('#registerForm input[name=picturenum]').val();
                 $(".modal-body").html("正在提交数据...");
                $('#modalBtn').click();
                //msg为返回完成注册的用户名
                $.ajax({
                    url:webRoot + '/addOrder',
                    type:'POST',
                    dataType:'json',
                    data:{
                        orderinfo:JSON.stringify(basicData)
                    },
                    cache:false,
                    /*数据传送成功*/
                    success:function (data) {
                        if(data.statusCode == 200){
                            $('#materialDetail').fadeOut(1000, function () {
                                $('#registerFinished').fadeIn();
                                $('#finished').addClass('active');
                                $('#registerFinished').find('.user-name a').text(data.message);
                            })
                        }
                        else{
                            //弹出出错信息
                            $(".modal-body").html("数据保存失败，请稍后重试！");
                            $('#modalBtn').click();
                        }
                    },
                    /*数据传送失败*/
                    error:function () {
                        $(".modal-body").html("数据传送失败，请稍后重试");
                        $('#modalBtn').click();
                    }
                });
            })
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
            $('#nextStep').on('click',function () {
                $('#accountDetail').show();
                $('#accountInfo').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#accountInfo').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#accountInfo').hide();
                    $('#details').addClass('active');
                });
            })
        }

        //下一步按钮点击事件
         function secondStep() {
             $('#secondStep').on('click',function () {
                 $('#constructDetail_A').show();
                 $('#accountDetail').css({
                     'position': 'relative',
                     'z-index': '-1',
                     'margin-bottom': '100px'
                 });
                 $('#accountDetail').animate({
                     marginTop: '-420'
                 }, 1000, 'linear', function () {
                     $('#accountDetail').hide();
                     $('#constructinfo').addClass('active');
                 });
             })
         }

        function thirdStep() {
            $('#thirdStep').on('click',function () {
                $('#constructDetail_B').show();
                $('#constructDetail_A').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#constructDetail_A').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#constructDetail_A').hide();
                });
            })
        }

        function fourthStep() {
            $('#fourthStep').on('click',function () {
                $('#constructDetail_C').show();
                $('#constructDetail_B').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#constructDetail_B').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#constructDetail_B').hide();
                });
            })
        }


        function fifthStep() {
            $('#fifthStep').on('click',function () {
                $('#constructDetail_D').show();
                $('#constructDetail_C').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#constructDetail_C').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#constructDetail_C').hide();
                });
            })
        }

        function sixthStep() {
            $('#sixthStep').on('click',function () {
                $('#constructDetail_E').show();
                $('#constructDetail_D').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#constructDetail_D').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#constructDetail_D').hide();
                });
            })
        }

        function seventhStep() {
            $('#seventhStep').on('click',function () {
                $('#cssDetail').show();
                $('#constructDetail_E').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#constructDetail_E').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#constructDetail_E').hide();
                    $('#details_css').addClass('active');
                });
            })
        }

        function eighthStep() {
            $('#eighthStep').on('click',function () {
                $('#materialDetail').show();
                $('#cssDetail').css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                $('#cssDetail').animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    $('#cssDetail').hide();
                    $('#details_materia').addClass('active');
                });
            })
        }

    })
});