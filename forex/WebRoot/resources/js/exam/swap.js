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
    'item.accTypeNo':'C',
    'item.examNo':6,
    'item.type':SWAP,
        'item.Direction':buy,
                'item.Acc':CNY,

            'item.swapAccAmount':1000,
            'item.swapSpot':'Y', 
            'item.swapStartDate':20170921,  
            'item.swapMaturityDate':20170930, //���׶��ַ��� 
            'item.swapFixedType':'receive',
            'item.swapFixedRate':6.1,
            'item.swapCbasis':10,
            'item.swapFbasis':15,
            'item.swapFrequency':2,
            'item.swapLibor':2,
            'item.swapProviderNo':201401059,
            'item.swapPoint':-5,

    'item.swapScore.directionScore':10,
    'item.swapScore.accScore':5,
    'item.swapScore.swapAccAmountScore':5,
    'item.swapScore.swapSpotScore':10,
    'item.swapScore.swapStartDateScore':5,  
    'item.swapScore.swapMaturityDateScore':10,
    'item.swapScore.swapFixedTypeScore':5,
    'item.swapScore.swapFixedRateScore':10,
    'item.swapScore.swapCbasisScore':5,
    'item.swapScore.swapFbasisScore':5,
    'item.swapScore.swapFrequencyScore':10,
    'item.swapScore.swapLiborScore':5,
    'item.swapScore.swapProviderNoScore':10,
    'item.swapScore.swapPointScore':5
    }


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


    
    //������ ���� + 0
    $.validator.addMethod("basis", function (value, element,params) {
	    var tel = /^-?\d+$/g;  //������ ���� + 0
	    return this.optional(element) || (tel.test(value));
	}, "����������"); 




    //����У�����
    $("#form1").validate({
        rules: {
        		item1_basis: {
                        basis: "",
                        required: true
                },
                item1_basisScore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
        		item1_basis: {
                    required: "���׻��㲻��Ϊ��!"
                },
                item1_basisScore: {
                    required: "���׻����������Ϊ��",
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

    $("#item1_basis").blur(function(){
        $("#form1").valid();
        $("#item1_basis").removeClass("error")
        $("#item1_basisScore").removeClass("error")
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
        		item4_swapAccMount: {
                        accMount: "",
                        required: true
                },
                item4_swapAccMountScore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
        		item4_swapAccMount: {
                    required: "���׽���Ϊ��!"
                },
                item4_swapAccMountScore: {
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

    $("#item4_swapAccMount").blur(function(){
        $("#form4").valid();
        $("#item4_swapAccMount").removeClass("error")
        $("#item4_swapAccMountScore").removeClass("error")
    });
    
    $("#form5").validate({
    	rules: {
    		item5_provider: {
    			required: true
    		},
    		item5_providerscore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item5_provider: {
    			required: "���׶��ֺŲ���Ϊ��!"
    		},
    		item5_providerscore: {
    			required: "���׶��ֺŷ�������Ϊ��",
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
    
    $("#item5_provider").blur(function(){
    	$("#form5").valid();
    	$("#item5_provider").removeClass("error")
    	$("#item5_providerscore").removeClass("error")
    });
    
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
    
    
    $("#form8").validate({
    	rules: {
    		item8_isSpotscore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item8_isSpotscore: {
    			required: "��������Ϊ��",
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
    
    $("#item8_isSpotscore").blur(function(){
    	$("#form8").valid();
    	$("#item8_isSpotscore").removeClass("error")
    })
    
    
    $("#form9").validate({
    	rules: {
    		item9_fixedTypescore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item9_fixedTypescore: {
    			required: "�̶��������ͷ�������Ϊ��",
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
    
    $("#item9_fixedTypescore").blur(function(){
    	$("#form9").valid();
    	$("#item9_fixedTypescore").removeClass("error")
    })
    
    $("#form10").validate({
        rules: {
        	item10_fixedRate: {
                required:true,
                isdecimal:""
            },
            item10_fixedRatescore:{
                required:true,
                digits:true
            }
        },
        messages: {
        	item10_fixedRate: {
                required: "�̶����ʲ���Ϊ��!",
            },
            item10_fixedRatescore: {
                required: "�̶����ʷ�������Ϊ��",
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

    $("#item10_fixedRate").blur(function(){
        $("#form10").valid();
        $("#item10_fixedRate").removeClass("error")
        $("#item10_fixedRatescore").removeClass("error")
    })
    
    $("#form11").validate({
        rules: {
        		item11_cBasic: {
                        basis: "",
                        required: true
                },
                item11_cBasicscore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
        		item11_cBasic: {
                    required: "���˵��ڵ㲻��Ϊ��!"
                },
                item11_cBasicscore: {
                    required: "���˵��ڵ��������Ϊ��",
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

    $("#item11_cBasic").blur(function(){
        $("#form11").valid();
        $("#item11_cBasic").removeClass("error")
        $("#item11_cBasicscore").removeClass("error")
    });
    $("#form12").validate({
    	rules: {
    		item12_fBasic: {
    			basis: "",
    			required: true
    		},
    		item12_fBasicscore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item12_fBasic: {
    			required: "Զ�˵��ڵ㲻��Ϊ��!"
    		},
    		item12_fBasicscore: {
    			required: "Զ�˵��ڵ��������Ϊ��",
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
    
    $("#item12_fBasic").blur(function(){
    	$("#form12").valid();
    	$("#item12_fBasic").removeClass("error")
    	$("#item12_fBasicscore").removeClass("error")
    });
    
    $("#form13").validate({
    	rules: {
    		item13_Frequencyscore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item13_Frequencyscore: {
    			required: "���˸�Ϣ���ͷ�������Ϊ��",
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
    
    $("#item13_Frequencyscore").blur(function(){
    	$("#form13").valid();
    	$("#item13_Frequencyscore").removeClass("error")
    })
    $("#form14").validate({
    	rules: {
    		item14_Liborscore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item14_Liborscore: {
    			required: "���˸�Ϣ���ͷ�������Ϊ��",
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
    
    $("#item14_Liborscore").blur(function(){
    	$("#form14").valid();
    	$("#item14_Liborscore").removeClass("error")
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