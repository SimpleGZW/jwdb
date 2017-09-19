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

            preStep();
            nextStep();
            formSubmit();
            showImgs();
        }

        var pages = [
            $('#accountInfo'),
            $('#accountDetail'),
            $('#constructDetail_A'),
            $('#constructDetail_B'),
            $('#constructDetail_C'),
            $('#constructDetail_D'),
            $('#constructDetail_E'),
            $('#cssDetail'),
            $('#materialDetail'),
            $('#registerFinished')
        ];

        var currentPageNum = 0;
        var nextPageNum;
        var prePageNum;

        /*上一步按钮点击事件*/
        function preStep() {
            $('.preStep').on('click',function () {

                //hide prompt image

                $(".promptImg").css({
                    'background': 'none',
                });

                prePageNum = currentPageNum - 1 ;
                pages[currentPageNum].css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-top': '100px'
                });
                pages[currentPageNum].animate({
                    marginTop: '420'
                }, 800, 'linear', function () {
                    pages[currentPageNum].hide();
                    switch (prePageNum) { // 去掉进度导航条高亮
                        case 0:
                            $('#details').addClass('disactive').removeClass('active');            break;
                        case 1:
                            $('#constructinfo').addClass('disactive').removeClass('active');     break;
                        case 6:
                            $('#details_css').addClass('disactive').removeClass('active');       break;
                        case 7:
                            $('#details_materia').addClass('disactive').removeClass('active');  break;
                        case 8:
                            $('#finished').addClass('disactive').removeClass('active');           break;
                    }
                    currentPageNum = prePageNum;

                    pages[prePageNum].show()
                        .css({
                            'z-index': '100',
                            'margin-top': '0px'
                        });
                });
            })
        }

        /*下一步按钮点击事件*/
        function nextStep() {
            $('.nextStep').on('click',function () {

                //hide prompt image

                $(".promptImg").css({
                    'background': 'none',
                });

                nextPageNum = ( currentPageNum + 1 ) % pages.length;
                pages[currentPageNum].css({
                    'position': 'relative',
                    'z-index': '-1',
                    'margin-bottom': '100px'
                });
                pages[currentPageNum].animate({
                    marginTop: '-420'
                }, 1000, 'linear', function () {
                    pages[currentPageNum].hide();

                    switch (currentPageNum) { // 将进度导航条高亮
                        case 0:
                            $('#details').removeClass('disactive').addClass('active');            break;
                        case 1:
                            $('#constructinfo').removeClass('disactive').addClass('active');     break;
                        case 6:
                            $('#details_css').removeClass('disactive').addClass('active');       break;
                        case 7:
                            $('#details_materia').removeClass('disactive').addClass('active');  break;
                        case 8:
                            $('#finished').removeClass('disactive').addClass('active');           break;
                    }

                    currentPageNum = nextPageNum;

                    pages[nextPageNum].show()
                        .css({
                            'z-index': '100',
                            'margin-top': '0px'
                        });
                });

            })
        }

        /*手动提交表单*/
        function formSubmit() {
            $('#submitBtn').on('click',function () {
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


        function showImgs() {
            /*绑定事件：点击输入框显示对应图片*/
            var imgs = {
                'materialnum': 1, // <input>'s name : image's number
                'nodenum': 2
            };

            for (var key in imgs){
                $("input[name='"+key+"']").on('focus', function () {
                    $(".promptImg").css({
                        'background': 'url("/jwdb/assets/images/show/'+imgs[$(this).attr('name')].toString()+'.jpg")',
                        'background-size' : '100% 100%'
                    });
                });
            }

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

    })
});