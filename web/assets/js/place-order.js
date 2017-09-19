/**
 * Created by dushang on 2016/8/17.
 */


define(["jQuery", "bootstrapJs", "accordion"],function($){

    $(function () {

        /*初始化页面*/
        initPage();

        /*订单基本信息*/
        var basicData={
            name:'',
            email:'',
            telephone:'',
            responsibleparty:'',
            submitdate:'',
            invoicetitle:'',
            accepttype:'',
            remark:'',
            type:'',
            status:0
        };
        /*样品表所有数据*/
        var sampleData=[];
        /*单个样品数据格式*/
        var sample={
            samplename:'',
            sampletype:'',
            carriername:'',
            resistance:'',
            fragmentlength:'',
            primertype:'',
            seqrequirements:'',
            remark:'',
            samplestatus:0
        };
        /*单个样品dom结构*/
        var sampleContent=
            "<tr>"+
            "<td><input type='text' class='sample-id' name='sampleId'  disabled></td>"+
       "<td><input type='text' name=''></td>"+
            "<td><input type='text' name=''></td>"+
            "<td><i class='fa fa-trash' aria-hidden='true'></i></td>"+
            "</tr>";

        /*初始化页面*/
        function initPage() {

            /*初始化风琴菜单*/
            initAccordion();

            /*绑定页面删除按钮点击事件*/
            bindDeleteEvent();

            /*添加样品按钮点击事件*/
            $('#addSample').on('click',addSample);

            /*重置按钮点击事件*/
            $('#resetButton').on('click',function () {
                $('#sampleTable tbody').html(sampleContent);
                $("#realReset").click();
                changeSampleId();
                bindDeleteEvent();
            });

            /*保存按钮点击事件*/
            $('#saveButton').on('click',function () {
                transferData();
            });

            /*保存并下单按钮点击事件*/
            $('#saveSubmitButton').on('click',function () {
                basicData.status=1;
                transferData();
            })
        }

        function initAccordion() {
            $("#orderCategory").accordion({
                'title':'订购单类型',
                'menus':[
                    ['DNA测序','#/1']
                ],
                'width':'220px',
                'hasSubmenus':false,
                'foldIcon':'fa-chevron-right'
            });

            /*默认选中第一个*/
            setTimeout(function () {
                $(".accordion-menu li:nth-child(1)").find('div').click();
            },10);

        }

        /*添加样品*/
        function addSample() {

            $('#sampleTable tbody').append(sampleContent);   //添加样品

            bindDeleteEvent();
            changeSampleId();
        }

        /*修改样品编号*/
        function changeSampleId() {
            var i=1;
            $('.sample-id').each(function () {
                $(this).val(i);
                i++;
            })
        }

        /*绑定删除按钮点击事件*/
        function bindDeleteEvent() {
            $('.fa-trash').each(function () {
                $(this).on('click',deleteSample);
            });
        }

        /*删除样品*/
        function deleteSample() {
            $(this).parent().parent().remove();

            changeSampleId();
        }


        //提示信息
        var msg = "";

        /*获取基本信息数据*/
        function getBasicData() {

            var name = $('#name').val();
            var email = $('#email').val();
            var tel = $('#telephone').val();
            var responsibleparty = $('#responsibleParty').val();
            var submitdate = $('#submitDate').val();
            var invoicetitle = $('#invoiceTitle').val();
            var accepttype = $('.accept-type[name="acceptType"]:checked').val();
            var remark = $('#remark').val();

            if(name === ""){
                msg = "姓名不能为空!";
            }else if(email === ""){
                msg = "邮箱不能为空!";
            }else if(!/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email)){
                msg = "邮箱格式不正确!";
            }else if(tel === ""){
                msg = "手机不能为空!";
            }else if(!/[0-9]{11}/.test(tel)){
                msg = "手机格式不正确!";
            }else if(responsibleparty === ""){
                msg = "科室负责人不能为空!";
            }else if(submitdate === ""){
                msg = "送达日期不能为空!";
            }else if(invoicetitle === ""){
                msg = "发票抬头不能为空!";
            }else if(remark.length > 350){
                msg = "备注字数超出限制!";
            }else{
                basicData.name = name;
                basicData.email = email;
                basicData.telephone = tel;
                basicData.responsibleparty = responsibleparty;
                basicData.submitdate = submitdate;
                basicData.invoicetitle = invoicetitle;
                basicData.accepttype = accepttype;
                basicData.remark = remark;
                $('#orderCategory').find("li").each(function () {
                    if($(this).find('div').hasClass('chosen')){
                        basicData.type = $(this).find('a').attr('href').split("#/")[1];
                    }
                });
                return true;
            }
            return false;
        }

        /*获取样品表数据*/
        function getSampleData() {
            sampleData = [];
            var flag=true;
            $('#sampleTable tbody').find('tr').each(function () {

                var samplename = $(this).find('.sample-name').val();
                var sampletype = $(this).find('.sample-type').val();
                var carriername = $(this).find('.carrier-name').val();
                var resistance = $(this).find('.resistance').val();
                var fragmentlength = $(this).find('.fragment-length').val();
                var primertype = $(this).find('.primer-type').val();
                var seqrequirements = $(this).find('.seq-requirements').val();
                var remark = $(this).find('.remark').val();

                if(samplename === ""){
                    msg = "样品名称不能为空!";
                }else if(carriername === ""){
                    msg = "载体名称不能为空!";
                }else if(resistance === ""){
                    msg = "抗性不能为空!";
                }else if(fragmentlength === ""){
                    msg = "片段长度不能为空!";
                }else if (!(/^\d+$/.test(fragmentlength))){
                    msg = "片段长度只能为数字!";
                }else if(remark.length > 350){
                    msg = "样品备注字数超出限制!";
                }else{
                    sample.samplename = samplename;
                    sample.sampletype = sampletype;
                    sample.carriername = carriername;
                    sample.resistance = resistance;
                    sample.fragmentlength = parseInt(fragmentlength);
                    sample.primertype = primertype;
                    sample.seqrequirements = seqrequirements;
                    sample.remark = remark;
                    sampleData.push($.extend(true,{},sample));
                    return true;    //跳出当前循环
                }

                flag=false;

                return false;   //跳出所有循环
            });

            return flag;
        }

        /*通过ajax请求将数据传递到服务器*/
        function transferData() {

            if(getBasicData() && getSampleData()){
                $(".modal-body").html("正在提交数据...");
                $('#modalBtn').click();

                $.ajax({
                    url:webRoot + '/addOrder',
                    type:'POST',
                    dataType:'json',
                    data:{
                        basicData:JSON.stringify(basicData),
                        sampleData:JSON.stringify(sampleData)
                    },
                    cache:false,
                    /*数据传送成功*/
                    success:function (data) {
                        if(data.statusCode == 200){

                            $(".modal-body").html(data.message);

                            if(basicData.status == 0){
                                /*如果保存，跳转到我的订单-未提交订单*/
                                document.location.href = webRoot+"/submitOrderSuccess#/1";
                            }else{
                                /*如果保存并提交，跳转到我的订单-已提交订单*/
                                document.location.href = webRoot+"/submitOrderSuccess#/2";
                            }
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
            }else{
                //弹出警告信息
                $(".modal-body").html(msg);
                $('#modalBtn').click();
            }
        }
    });
});