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
    //根据传入的类型进行查询
    function getDataBySubjectType(data) {

        //dynamic to put data
        //直接赋值增加js对象的属性
        subjectType.subjectType=data;
        $.ajax(
            {
                type:'get',
                url:'/forex/queryBySubjectType.action',
                data:subjectType,
                success:function (data) {
                    //遍历json数据
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
                    console.log("访问后台出错")
                }
            }
        )
    }

    //组装题目对象
    var SubjectItem={
    'item.accTypeNo':'C',
    'item.examNo':6,
    'item.type':SPOT,
        'item.Direction':buy,
                'item.Acc':CNY,

            'item.spotAccMount':1000,
            'item.spotPrice':10,  //spot 交易基点
            'item.spotProviderNo':201401059, //交易对手方号 

    'item.spotScore.DirectionScore':10,
    'item.spotScore.AccScore':20,
    'item.spotScore.spotAccMountScore':20,
    'item.spotScore.spotPriceScore':30,  //交易基点
    'item.spotScore.spotProviderNoScore':20
    
    }


    //组装数据
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
        return value>0&&value!==0&&value%100===0        //当返回值是true时，则不显示提示内容；当返回值是false时，则显示提示内容
    },$.validator.format("请填写100的整数"))

    //币种类型验证
    $.validator.addMethod("isLegalAcc",function (value,element,params) {
        console.log(value)
        //定义合法的币种['USD','EUR','AUD','GBP','JPY','CAD']
        //"币种只能是USD、EUR、AUD、GBP、JPY、CAD"
        var acc=params[0];
        var isExit=$.inArray(value,acc)
        if (value!="" && isExit<0) {
            console.log("币种只能是USD、EUR、AUD、GBP、JPY、CAD")
            $.messager.alert(params[1],params[2])
            return false;
        }
        else
            return true;
    })

    
    //正、负 整数 + 0
    $.validator.addMethod("basis", function (value, element,params) {
	    var tel = /^-?\d+$/g;  //正、负 整数 + 0
	    return this.optional(element) || (tel.test(value));
	}, "请输入整数"); 




    //交易金额校验规则
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
                    required: "交易基点不能为空!"
                },
                item1_basisScore: {
                    required: "交易基点分数不能为空",
                    digits: "分数只能是整数"
                }
        },
        errorElement: "label", //用来创建错误提示信息标签
        success: function (label) { //验证成功后的执行的回调函数
            //label指向上面那个错误提示信息标签label
            label.text(" ") //清空错误提示消息
                .addClass("success"); //加上自定义的success类
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.addClass("error")
            error.appendTo(element.parent()); //这里的element是录入数据的对象
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
                isLegalAcc:[['USD','EUR','AUD','GBP','JPY','CAD'],"币种转换格式出错",
                    "币种只能是USD、EUR、AUD、GBP、JPY、CAD"]
            },
            item2_AccScore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item2_Acc: {
                required: "交易币种不能为空!"
            },
            item2_AccScore: {
                required: "交易币种分数不能为空",
                digits: "分数只能是整数"
            }
        },
        errorElement: "label", //用来创建错误提示信息标签
        success: function (label) { //验证成功后的执行的回调函数
            //label指向上面那个错误提示信息标签label
            label.text(" ") //清空错误提示消息
                .addClass("success"); //加上自定义的success类
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.addClass("error")
            error.appendTo(element.parent()); //这里的element是录入数据的对象
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
                isLegalAcc: [["买","卖"],"交易方向","交易方向只能是买或者卖"]
            },
            item3_directionscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item3_direction: {
                required: "交易方向不能为空!",
            },
            item3_directionscore: {
                required: "交易方向分数不能为空",
                digits: "分数只能是整数"
            }
        },
        errorElement: "label", //用来创建错误提示信息标签
        success: function (label) { //验证成功后的执行的回调函数
            //label指向上面那个错误提示信息标签label
            label.text(" ") //清空错误提示消息
                .addClass("success"); //加上自定义的success类
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.addClass("error")
            error.appendTo(element.parent()); //这里的element是录入数据的对象
        },
    });

    $("#item3_direction").blur(function(){
        $("#form3").valid();
        $("#item3_direction").removeClass("error")
        $("#item3_directionscore").removeClass("error")
    })

    $("#form4").validate({
        rules: {
        		item4_spotAccMount: {
                        accMount: "",
                        required: true
                },
                item4_spotAccMountScore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
        		item4_spotAccMount: {
                    required: "交易金额不能为空!"
                },
                item4_spotAccMountScore: {
                    required: "交易金额分数不能为空",
                    digits: "分数只能是整数"
                }
        },
        errorElement: "label", //用来创建错误提示信息标签
        success: function (label) { //验证成功后的执行的回调函数
            //label指向上面那个错误提示信息标签label
            label.text(" ") //清空错误提示消息
                .addClass("success"); //加上自定义的success类
        },
        errorPlacement: function (error, element) { //错误信息位置设置方法
            error.addClass("error")
            error.appendTo(element.parent()); //这里的element是录入数据的对象
        },
    });

    $("#item4_spotAccMount").blur(function(){
        $("#form4").valid();
        $("#item4_spotAccMount").removeClass("error")
        $("#item4_spotAccMountScore").removeClass("error")
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
    			required: "交易对手号不能为空!"
    		},
    		item5_providerscore: {
    			required: "交易对手号分数不能为空",
    			digits: "分数只能是整数"
    		}
    	},
    	errorElement: "label", //用来创建错误提示信息标签
    	success: function (label) { //验证成功后的执行的回调函数
    		//label指向上面那个错误提示信息标签label
    		label.text(" ") //清空错误提示消息
    		.addClass("success"); //加上自定义的success类
    	},
    	errorPlacement: function (error, element) { //错误信息位置设置方法
    		error.addClass("error")
    		error.appendTo(element.parent()); //这里的element是录入数据的对象
    	},
    });
    
    $("#item5_provider").blur(function(){
    	$("#form5").valid();
    	$("#item5_provider").removeClass("error")
    	$("#item5_providerscore").removeClass("error")
    });


});

//全局方法,dom元素才能找到相应的方法
//点击多选框时，则更改分数框、答案框的状态
function haha(obj,v) {
    if ($(obj).prop("checked")) {
        //修改所有以item+v开头的不可编辑状态
        $("input[name^='item"+v+"']").removeAttr("readonly")
    } else {
        $("input[name^='item"+v+"']").attr("readonly","readonly")
    }

}
