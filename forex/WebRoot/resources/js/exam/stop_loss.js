//exchange type
var STOPLOSS="stop loss";
var TAKEPROFIT="take profit";
var OCO="oco";
var MARKETBREAKOUT="market breakout";
var ONECLICK="one click";
var SPOT="spot";
var FORWARD="forward";
var SWAP="swap";

//exchange direction
var buy=1;
var sell=0;

var CNY='CNY';
var USD='USD';

(function ($) {
    var subjectType={};

    //getDataBySubjectType(2)   //for test
    //���ݴ�������ͽ��в�ѯ
    function getDataBySubjectType(data) {

        //dynamic to put data
        //ֱ�Ӹ�ֵ����js���������
        subjectType.subjectType=data;
        $.ajax(
            {
                type:'get',
                url:'/forex/queryBySubjectType.action',
                data:subjectType,
                success:function (data) {
                    //����json����
                    $.each(data,function (i,element) {
                        //code
                        //0:error  1:success
                        if (element.code===0)
                            console.log(element.error)
                        else if (element.code===1){
                          $.each(element.message,function (i,ele) {
                              console.log(ele.examContent)
                          })
                        }
                    })
                },
                fail:function (data) {
                    console.log(data)
                    console.log("���ʺ�̨����")
                }
            }
        )
    }

    //��װ��Ŀ����
    var SubjectItem={
    'item.accTypeNo':'W',
    'item.examNo':6,
    'item.type':STOPLOSS,
        'item.Direction':buy,
                'item.Acc':CNY,

            'item.stopLossAccMount':1000,
                'item.stopLossPrice':'1.234',
            'item.stopLossAccmonitorPrice':'ASK',
                'item.stopLossGoodFrom':'20180909',
        'item.stopLossGoodTill':'20190112',

    'item.stopLossScore.DirectionScore':10,
    'item.stopLossScore.AccScore':10,
    'item.stopLossScore.stopLossAccMountScore':0,
    'item.stopLossScore.stopLossAccmonitorPriceScore':10,
    'item.stopLossScore.stopLossGoodFromScore':20,
    'item.stopLossScore.stopLossGoodTillScore':30,
    'item.stopLossScore.stopLossPriceScore':20}







    //��װ����
   // addSubject()

    function addSubject() {
        $.ajax(
            {
                type:'get',
                url:'/forex/addnewExchange.action',
                data:SubjectItem,
                success:function (data) {
                    //code
                    //0:fail  1:success
                    $.each(data,function (i,element) {
                        if (element.code===0)
                          console.log(element.error)
                        else if (element.code===1){
                          console.log(element.error)
                        }
                    })
                }
            }
        )
    }



})

$(document).ready(function(){


    $.validator.addMethod("accMount",function (value, element, params) {
        console.log(value)
        return value>0&&value!==0&&value%100===0        //������ֵ��trueʱ������ʾ��ʾ���ݣ�������ֵ��falseʱ������ʾ��ʾ����
    },$.validator.format("����д100������"))

    //����������֤
    $.validator.addMethod("isLegalAcc",function (value,element,params) {
        console.log(value)
        //����Ϸ��ı���['USD','EUR','AUD','GBP','JPY','CAD']
        //"����ֻ����USD��EUR��AUD��GBP��JPY��CAD"
        var acc=params[0];
        var isExit=$.inArray(value,acc)
        if (value!="" && isExit<0) {
            console.log("����ֻ����USD��EUR��AUD��GBP��JPY��CAD")
            $.messager.alert(params[1],params[2])
            return false;
        }
        else
            return true;
    })

    //����4λС��
    $.validator.addMethod("isdecimal",function (value,element,params) {
        if (/^\d+(\.\d{1,4})?$/.test(value)){
            return true;
        }else {
            return false;
        }
    },$.validator.format("ֻ��������4λС��"))




    //���׽��У�����
    $("#form1").validate({
        rules: {
                item1_stopLossAccMount: {
                        accMount: "",
                        required: true
                },
                item1_stopLossAccMountScore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
                item1_stopLossAccMount: {
                    required: "���׽���Ϊ��!"
                },
                item1_stopLossAccMountScore: {
                    required: "���׽���������Ϊ��",
                    digits: "����ֻ��������"
                }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item1_stopLossAccMount").blur(function(){
        $("#form1").valid();
        $("#item1_stopLossAccMount").removeClass("error")
        $("#item1_stopLossAccMountScore").removeClass("error")
    });


    $("#form2").validate({
        rules: {
            item2_Acc: {
                required:true,
                isLegalAcc:[['USD','EUR','AUD','GBP','JPY','CAD'],"����ת����ʽ����",
                    "����ֻ����USD��EUR��AUD��GBP��JPY��CAD"]
            },
            item2_AccScore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item2_Acc: {
                required: "���ױ��ֲ���Ϊ��!"
            },
            item2_AccScore: {
                required: "���ױ��ַ�������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item2_Acc").blur(function(){
        $("#form2").valid();
        $("#item2_Acc").removeClass("error")
        $("#item2_AccScore").removeClass("error")
    });

    $("#form3").validate({
        rules: {
            item3_direction: {
                required:true,
                isLegalAcc: [["��","��"],"���׷���","���׷���ֻ�����������"]
            },
            item3_directionscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item3_direction: {
                required: "���׷�����Ϊ��!",
            },
            item3_directionscore: {
                required: "���׷����������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item3_direction").blur(function(){
        $("#form3").valid();
        $("#item3_direction").removeClass("error")
        $("#item3_directionscore").removeClass("error")
    })

    $("#form4").validate({
        rules: {
            item4_price: {
                required:true,
                isdecimal:""
            },
            item4_pricescore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item4_price: {
                required: "���׼۸���Ϊ��!",
            },
            item4_pricescore: {
                required: "���׼۸��������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item4_price").blur(function(){
        $("#form4").valid();
        $("#item4_price").removeClass("error")
        $("#item4_pricescore").removeClass("error")
    })

    $("#form5").validate({
        rules: {
            item5_accmonitorpricescore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item5_accmonitorpricescore: {
                required: "ģ�����ͷ�������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item5_accmonitorpricescore").blur(function(){
        $("#form5").valid();
        $("#item5_accmonitorpricescore").removeClass("error")
    })

    $("#form6").validate({
        rules: {
            item6_goodfromscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item6_goodfromscore: {
                required: "��ʼʱ���������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item6_goodfromscore").blur(function(){
        $("#form6").valid();
        $("#item6_goodfromscore").removeClass("error")
    })

    $("#form7").validate({
        rules: {
            item7_goodtillscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item7_goodtillscore: {
                required: "����ʱ���������Ϊ��",
                digits: "����ֻ��������"
            }
        },
        errorElement: "label", //��������������ʾ��Ϣ��ǩ
        success: function (label) { //��֤�ɹ����ִ�еĻص�����
            //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
            label.text(" ") //��մ�����ʾ��Ϣ
                .addClass("success"); //�����Զ����success��
        },
        errorPlacement: function (error, element) { //������Ϣλ�����÷���
            error.addClass("error")
            error.appendTo(element.parent()); //�����element��¼�����ݵĶ���
        },
    });

    $("#item7_goodtillscore").blur(function(){
        $("#form7").valid();
        $("#item7_goodtillscore").removeClass("error")
    })

});

//ȫ�ַ���,domԪ�ز����ҵ���Ӧ�ķ���
//�����ѡ��ʱ������ķ����򡢴𰸿��״̬
function haha(obj,v) {
    if ($(obj).prop("checked")) {
        //�޸�������item+v��ͷ�Ĳ��ɱ༭״̬
        $("input[name^='item"+v+"']").removeAttr("readonly")
    } else {
        $("input[name^='item"+v+"']").attr("readonly","readonly")
    }

}

function enableCheck(obj,v) {
    if ($(obj).prop("checked")){
        $("select[name^='item"+v+"']").removeAttr("disabled")
        $("input[name^='item"+v+"']").removeAttr("readonly")
    }else {
        $("select[name^='item"+v+"']").attr("disabled","disabled")
        $("input[name^='item"+v+"']").attr("readonly","readonly")
    }
}








