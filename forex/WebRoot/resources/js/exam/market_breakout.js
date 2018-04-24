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
    'item.accTypeNo':'W',
    'item.examNo':6,
    'item.type':MARKETBREAKOUT,
        'item.Direction':buy,
                'item.Acc':CNY,

            'item.mbktStopLossAccMount1':1000,
                'item.mbktStopLossPrice1':'1.234',
                'item.mbktStopLossAccMount2':1000,
                'item.mbktStopLossPrice2':'1.234',
            'item.mbkStopLossAccmonitorPrice':'ASK',
                'item.mbkGoodFrom':'20180909',
        'item.mbkGoodTill':'20190112',

    'item.marketBreakoutScore.DirectionScore':10,
    'item.marketBreakoutScore.AccScore':10,
    'item.marketBreakoutScore.mbktStopLossAccMount1Score':0,
    'item.marketBreakoutScore.mbktStopLossPrice1Score':10,
    'item.marketBreakoutScore.mbktStopLossAccMount2Score':0,
    'item.marketBreakoutScore.mbktStopLossPrice2Score':10,
    'item.marketBreakoutScore.mbkStopLossAccmonitorPriceScore':10,
    'item.marketBreakoutScore.mbkGoodFromScore':20,
    'item.marketBreakoutScore.mbkGoodTillScore':30}







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

    //设置4位小数
    $.validator.addMethod("isdecimal",function (value,element,params) {
        if (/^\d+(\.\d{1,4})?$/.test(value)){
            return true;
        }else {
            return false;
        }
    },$.validator.format("只允许输入4位小数"))



    $("#form1").validate({
        rules: {
            item1_Acc: {
                required:true,
                isLegalAcc:[['USD','EUR','AUD','GBP','JPY','CAD'],"币种转换格式出错",
                    "币种只能是USD、EUR、AUD、GBP、JPY、CAD"]
            },
            item1_AccScore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item1_Acc: {
                required: "交易币种不能为空!"
            },
            item1_AccScore: {
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

    $("#item1_Acc").blur(function(){
        $("#form1").valid();
        $("#item1_Acc").removeClass("error")
        $("#item1_AccScore").removeClass("error")
    });

    $("#form2").validate({
        rules: {
            item2_direction: {
                required:true,
                isLegalAcc: [["买","卖"],"交易方向","交易方向只能是买或者卖"]
            },
            item2_directionscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item2_direction: {
                required: "交易方向不能为空!",
            },
            item2_directionscore: {
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

    $("#item2_direction").blur(function(){
        $("#form2").valid();
        $("#item2_direction").removeClass("error")
        $("#item2_directionscore").removeClass("error")
    })
    
     //交易金额校验规则
    $("#form3").validate({
        rules: {
        		item3_marketsoAccMount: {
                        accMount: "",
                        required: true
                },
                item3_marketsoAccMountScore:{
                    required:true,
                    digits:true
                }
        },
        messages: {
        		item3_marketsoAccMount: {
                    required: "stop loss1交易金额不能为空!"
                },
                item3_marketsoAccMountScore: {
                    required: "stop loss1交易金额分数不能为空",
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

    $("#item3_marketsoAccMount").blur(function(){
        $("#form3").valid();
        $("#item3_marketsoAccMount").removeClass("error")
        $("#item3_marketsoAccMountScore").removeClass("error")
    });

    $("#form4").validate({
        rules: {
        	item4_soPrice: {
                required:true,
                isdecimal:""
            },
            item4_soPricescore:{
                required:true,
                digits:true
            }
        },
        messages: {
        	item4_soPrice: {
                required: "stop loss1交易价格不能为空!",
            },
            item4_soPricescore: {
                required: "stop loss1交易价格分数不能为空",
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

    $("#item4_soPrice").blur(function(){
        $("#form4").valid();
        $("#item4_soPrice").removeClass("error")
        $("#item4_soPricescore").removeClass("error")
    })
    $("#form5").validate({
    	rules: {
    		item5_marketstAccMount: {
    			accMount: "",
    			required: true
    		},
    		item5_marketstAccMountScore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item5_marketstAccMount: {
    			required: "stop loss2交易金额不能为空!"
    		},
    		item5_marketstAccMountScore: {
    			required: "stop loss2交易金额分数不能为空",
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
    
    $("#item5_marketstAccMount").blur(function(){
    	$("#form5").valid();
    	$("#item5_marketstAccMount").removeClass("error")
    	$("#item5_marketstAccMountScore").removeClass("error")
    });
    
    $("#form6").validate({
    	rules: {
    		item6_stPrice: {
    			required:true,
    			isdecimal:""
    		},
    		item6_stPricescore:{
    			required:true,
    			digits:true
    		}
    	},
    	messages: {
    		item6_stPrice: {
    			required: "stop loss2交易价格不能为空!",
    		},
    		item6_stPricescore: {
    			required: "stop loss2交易价格分数不能为空",
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
    
    $("#item6_stPrice").blur(function(){
    	$("#form6").valid();
    	$("#item6_stPrice").removeClass("error")
    	$("#item6_stPricescore").removeClass("error")
    })

    $("#form7").validate({
        rules: {
            item7_accmonitorpricescore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item7_accmonitorpricescore: {
                required: "模拟类型分数不能为空",
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

    $("#item7_accmonitorpricescore").blur(function(){
        $("#form7").valid();
        $("#item7_accmonitorpricescore").removeClass("error")
    })

    $("#form8").validate({
        rules: {
            item8_goodfromscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item8_goodfromscore: {
                required: "开始时间分数不能为空",
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

    $("#item8_goodfromscore").blur(function(){
        $("#form8").valid();
        $("#item8_goodfromscore").removeClass("error")
    })

    $("#form9").validate({
        rules: {
            item9_goodtillscore:{
                required:true,
                digits:true
            }
        },
        messages: {
            item9_goodtillscore: {
                required: "交割时间分数不能为空",
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

    $("#item9_goodtillscore").blur(function(){
        $("#form9").valid();
        $("#item9_goodtillscore").removeClass("error")
    })

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

function enableCheck(obj,v) {
    if ($(obj).prop("checked")){
        $("select[name^='item"+v+"']").removeAttr("disabled")
        $("input[name^='item"+v+"']").removeAttr("readonly")
    }else {
        $("select[name^='item"+v+"']").attr("disabled","disabled")
        $("input[name^='item"+v+"']").attr("readonly","readonly")
    }
}