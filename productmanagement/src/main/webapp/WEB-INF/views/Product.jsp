<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./libs/css/bootstrap/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./libs/js/boostrap/bootstrap.min.js"></script>
<title>製品管理</title>
</head>
<body onload=load()>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">製品管理</a>
		</div>
		<ul class="nav navbar-nav">
			<li class=""><a href="../home/category">製品類</a></li>
			<li><a href="../product/view">製品</a></li>
			<li><a href="#">属性</a></li>
		</ul>
	</div>
	</nav>
	<div class="container">
		<table id="tbProduct" class="table">
			<tr>
				<th>製品コード</th>
				<th>製品名</th>
				<th>製品説明</th>
				<th>修正</th>
				<th>削除</th>
			</tr>
		</table>
		<div class="row">
			<div class="col-xs-9">
				<form id="frProductCreateUpdate">
					<div class="form-group">
						<label for="txtProductCode">Product Code:</label> <input
							type="text" class="form-control" id="txtProductCode">
					</div>
					<div class="form-group">
						<label for="txtProductName">Product Name:</label> <input
							type="text" class="form-control" maxlength="100"
							id="txtProductName">
					</div>

					<div class="form-group">
						<label for="txtProductDesc">Product Description:</label>
						<textarea class="form-control" rows="2" maxlength="500"
							id="txtProductDesc"></textarea>
					</div>
					
				</form>
			</div>
			<div class="col-xs-3">
				<div class="radio" style="margin-top: 40px">
					<label><input type="radio" name="productMode"
						id="rdProductAdd" checked>新規追加</label> 
					<label><input
						type="radio" name="productMode" id="rdProductUpdate">修正</label>
				</div>
				<button id="btAddProduct" type="button" class="btn btn-success">新規追加</button>
				<button id="btUpdateProduct" class="btn btn-success"
					style="display: none;">修正</button>
			</div>
		</div>

	</div>
</body>

<script type="text/javascript">
	productData = "";
	var attrList = [];
	
 
	var deleteProduct = function(index1){     
		console.log(productData[index1].product);
     	$.ajax({
        	url:'delete',
       	 	type:'POST',
        	data:{productCode: productData[index1].productCode},
        	success: function(response){
                		alert(response.Message);
                		load();
        	}              
    	});
	}
 

	edit = function (index){
		$("frProductUpdate").show();
    	$("#txtProductCode").val(productData[index].productCode);
    	$("#txtProductName").val(productData[index].productName);
    	$("#txtProductDesc").val(productData[index].productDesc);
    	$('#rdProductUpdate').attr('checked','checked');
    	radioChange("rdProductUpdate");
		$.ajax({
            url:'attrlist',
            type:'GET',
            data:{productCode:$("#txtProductCode").val()},
            success: function(response){
            	var attrData = response.Data;
            	//add attr list to product selected
            	$('#tbAttr.tr').remove();
                for(i=0; i< attrData.length; i++){
                	var row = "<tr class='tr'> <td> "+ attrData[i].productCode+" </td> <td> "+attrData[i].productName+" </td> "
                			+ "<td> <a href='#' onclick= edit("+ i +");'> Edit </a>  </td> "
                			+ "<td> <a href='#' onclick='delete("+i+");'> Delete </a>  </td> </tr>";
                    $("#tbAttr").append(row);
                }        
            }              
        });  
	}
	
	submitEdit = function(){
        $.ajax({
            url:'update',
            type:'POST',
            data:{productCode:$("#txtProductCode").val(),productName:$('#txtProductName').val(),productDesc:$('#txtProductDesc').val()},
            success: function(response){
                    alert(response.Message);
                    load();    
            }              
        });        
	}
	
	submitAdd = function(){
        $.ajax({
            url:'add',
            type:'POST',
            data:{productCode:$("#txtProductCode").val(),productName:$('#txtProductName').val(),productDesc:$('#txtProductDesc').val()},
            success: function(response){
                    alert(response.Message);
                    load();    
                    $("#txtProductCode").val('');
        	    	$("#txtProductName").val('');
        	    	$("#txtProductDesc").val('');
            }              
        });        
	}
 
 
	load = function(){
		$.ajax({
        	url:'productlist',
        	type:'GET',
        	success: function(response){
        		console.log(response.Data);
        		productData = response.Data;
                $('.tbProductTr').remove();
                for(var i=0; i< productData.length; i++){
                	var row = "<tr class='tbProductTr'> <td> "+ productData[i].productCode +" </td> <td> "+ productData[i].productName +" </td> "
                			+ "<td>" + productData[i].productDesc + "</td>"
                			+ "<td> <a href='#' onclick='edit("+ i +")'> Edit </a>  </td> "
                			+ "<td> <a href='#' onclick='deleteProduct("+ i +")'> Delete </a></td></tr>";
                    $("#tbProduct").append(row);
                }          
        	}	              
    	});	
	}
	
	$("#btUpdateProduct").click(function(){
		submitEdit();
	});
	
	$("#btAddProduct").click(function(){
		submitAdd();
	});
	
	var radioChange = function(id){
		if(id == 'rdProductAdd'){
			$("#btAddProduct").show();
			$("#btUpdateProduct").hide();
			$("#txtProductCode").val('');
	    	$("#txtProductName").val('');
	    	$("#txtProductDesc").val('');
			 $('#txtProductCode').prop("disabled", false); 
		} else {
			$("#btAddProduct").hide();
			$("#btUpdateProduct").show();
			$('#txtProductCode').prop("disabled", true);
		}
		
	}
	
	$("input[name=productMode]").change(function(){
		radioChange(this.id);
		
	});
    
	
</script>
</html>