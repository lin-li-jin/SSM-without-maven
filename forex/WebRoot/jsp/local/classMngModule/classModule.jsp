<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page contentType="text/html;charset=gbk" %>
<!--
ҳ�淢�����ݣ���ǰҳ����ÿҳ��ʾ����
page 1
rows 10

��̨����:json��ʽ:����������������
{
"total":2,
"rows":[
{"classId":"001","classNo":"1415451","className":"����ó��һ��"},
{"classId":"002","classNo":"1415452","className":"����ó�׶���"}
]
}
-->
<html>
<head>
	<title>�����༶ҳ��</title>
	 <meta http-equiv="content-type" content="text/html;charset=gbk" />
	<script src="/forex/resources/js/jquery-1.8.3.js" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.validate.js" type="text/javascript"></script>
	<script src="/forex/resources/ponycss/jquery_validate.css" type="text/javascript"></script>
	<script src="/forex/resources/js/jquery.easyui.min.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="/forex/resources/css/easyui.css">
	<style type="text/css">
		label.error{
			padding-left: 5px;
			font-family:georgia;
			font-size: 13px;
			font-style: normal;
			color: red;
		}
	</style>

	<script type="text/javascript">
        $(function(){
            //�Բ�ѯ�༶�ı����У�����
            $("#searchClassForm").validate({
                rules:{
                    searchClassNo:{
                        //required:true,
                        englishAndNumber:true,
                    }
                },
                messages:{
                    searchClassNo:{
                        //required:"�༶�Ų���Ϊ��!",
                        englishAndNumber:"�༶��ֻ������ĸ���������",
                    }
                },
                errorElement:"label", //��������������ʾ��Ϣ��ǩ
                success: function(label) { //��֤�ɹ����ִ�еĻص�����
                    //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
                    label.text(" ") //��մ�����ʾ��Ϣ
                        .addClass("success"); //�����Զ����success��
                },
                errorPlacement:function(error, element) { //������Ϣλ�����÷���
                    error.appendTo( element.parent() ); //�����element��¼�����ݵĶ���
                },
            });

            //����Ӱ༶�ı����У�����
            $("#addClassForm").validate({
                rules:{
                    classNo:{
                        required:true,
                        minlength:2,
                        englishAndNumber:true,
                    },
                    className:{
                        required:true,
                        minlength:2,
                    }
                },
                messages:{
                    classNo:{
                        required:"�༶�Ų���Ϊ��!",
                        minlength:"�༶�Ų�������2���ַ�!",
                        englishAndNumber:"�༶��ֻ������ĸ���������",
                    },
                    className:{
                        required:"�༶���Ʋ���Ϊ��!",
                        minlength:"�༶���Ʋ�������2���ַ�!",
                    }
                },
                errorElement:"label", //��������������ʾ��Ϣ��ǩ
                success: function(label) { //��֤�ɹ����ִ�еĻص�����
                    //labelָ�������Ǹ�������ʾ��Ϣ��ǩlabel
                    label.text(" ") //��մ�����ʾ��Ϣ
                        .addClass("success"); //�����Զ����success��
                },
                errorPlacement:function(error, element) { //������Ϣλ�����÷���
                    error.appendTo( element.parent() ); //�����element��¼�����ݵĶ���
                },
            });

            //չʾ�༶�б�����ݱ��
            //����ͨ��table��Ϊdatagrid��ʽ
            $("#showClassTable").datagrid({
                fit : true,
                border : true,
                rownumbers : true,
                striped : true,
                singleSelect : true,
                pagination : true,
                onLoadSuccess : function(data){//���ݼ��سɹ���,����ÿһ������,���ϲ鿴�༶��Ϣ
                    for(var i=0;i<data.rows.length;i++){
                        var row = data.rows[i];
                        $("#showClassTable").datagrid('updateRow',{
                            index : i,
                            row : {operation:"<a href=\"/forex/jsp/local/classMngModule/classInfo.jsp?tClass.classNo="+row.classNo+"\">�鿴�༶��Ϣ</a>"}
                        });
                    }
                },
				/* loadFilter : function(data){
				 for(var i=0;i<data.rows.length;i++){
				 var row = data.rows[i];
				 data.rows[i] = data.rows[i]+"\"operation\":\"�鿴�༶��Ϣ\"";
				 }
				 return data;
				 }, */
                //pageList: [30,50,100],
                url : "/forex/queryClass.action",
                idField : 'classId',
                columns : [[
                    {
                        field : 'classId',
                        title : 'ID',
                        width : 100,
                        align : 'center'
                    },{
                        field : 'classNo',
                        title : '�༶��',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'className',
                        title : '�༶����',
                        width : 200,
                        align : 'center'
                    },{
                        field : 'operation',
                        title : '����',
                        width : 170,
                        align : 'center'
                    }
                ]]
            });

            //�����ѯ��ť
            $("#search").click(function(){
                //У���
                var v = $("#searchClassForm").valid();
                if(v){
                    //��ȡ����İ༶�źͰ༶����,��Ϊ��������datagrid���ݱ���reload�������¼�������
                    var classNo = $("#searchClassNo").val();
                    //var className = $("#searchClassName").val();
                    //value = {"tClass.classNo":classNo,"tClass.className":className};
                    value = {"tClass.classNo":classNo};
                    $("#showClassTable").datagrid("reload",value);
                }
            });

            //�����Ӱ�ť
            //{"result":"success","msg":"success","dataList":[{"message":"������ݳɹ�","success":true}]}
            //{"result":"fail","msg":"�Ѵ��ڸ��˺ż�¼��������ٴ����","dataList":"null"}
            $("#add").click(function(){
                //У���
                var v = $("#addClassForm").valid();

                if(v){
                    //��ȡ����İ༶�źͰ༶����
                    var classNo = $("#classNo").val();
                    var className = $("#className").val();
                    //��У��ͨ��
                    $.ajax({
                        url:"/forex/insertClass.action",
                        data:{"tClass.classNo":classNo,"tClass.className":className},
                        dataType:"json",
                        type:"get",
                        async:true,
                        success:function(data){
                            if("success"==(data.result)){
                                console.log(data.dataList[0].message);
                                $.messager.alert(data.dataList[0].message,"info");
                                //����datagrid���ݱ���reload�������¼�������,����Ϊnull����Զ�ʹ���ϴβ�ѯ�Ĳ�����ѯ
                                $("#showClassTable").datagrid("reload",null);
                            }else if("fail"==(data.result)){
                                $.messager.alert("��ʾ","�Ѵ��ڸ��˺ż�¼��������ٴ����","warning");
                            }
                        }

                    });
                }
            });


        });
	</script>

</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="width:1024px;height:150px;">
	<form action="" id="searchClassForm" method="post">
		<br>
		&nbsp;�༶��:<input id="searchClassNo" name="searchClassNo" type="text" placeholder="������...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<!-- &nbsp;�༶����:<input id="searchClassName" name="searchClassName" type="text" placeholder="������...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<input type="button" value="��ѯ" id="search">
	</form>
	&nbsp;��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������<br><br>
	<form action="" id="addClassForm" method="post">
		<br>
		&nbsp;�༶��:<input id="classNo" name="classNo" type="text" placeholder="������...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;�༶����:<input id="className" name="className" type="text" placeholder="������...">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="���" id="add">
	</form>
</div>
<div data-options="region:'center'" style="width:1024px;height:500px;">
	<table id="showClassTable"></table>
</div>
</body>
</html>
