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
			<li class="active"><a href="#">製品類</a></li>
			<li><a href="../product/view">製品</a></li>
			<li><a href="#">属性</a></li>
		</ul>
	</div>
	</nav>
	<div class="container">
		<h3>製品類一覧</h3>
		<table id="tbCategory" class="table table-bordered">
			<tr>
				<th>製品類コード</th>
				<th>製品類名</th>
				<th>製品説明</th>
				<th>修正</th>
				<th>削除</th>
			</tr>
		</table>
		<div class="row">
			<div class="col-xs-9">

				<div class="form-group">
					<label for="txtCategoryCode">Category Code:</label> <input
						type="text" class="form-control" id="txtCategoryCode">
				</div>
				<div class="form-group">
					<label for="txtCategoryName">Category Name:</label> <input
						type="text" class="form-control" maxlength="100"
						id="txtCategoryName">
				</div>

				<div class="form-group">
					<label for="txtCategoryDesc">Category Description:</label>
					<textarea class="form-control" rows="2" maxlength="500"
						id="txtCategoryDesc"></textarea>
				</div>
				<table class="table table-bordered" id="tbProduct">
					<tr>
						<th>製品コード</th>
						<th>製品名</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</table>

				<button id="btShowAllProduct" class="btn btn-success">製品追加</button>

				<div id="divProductAdd" hidden>
					<h3>製品一覧</h3>
					<table class="table table-bordered" id="tbProductAdd">
						<tr>
							<th>製品コード</th>
							<th>製品名</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</table>
				</div>


			</div>
			<div class="col-xs-3">
				<div class="radio" style="margin-top: 40px">
					<label><input type="radio" name="categoryMode"
						id="rdCategoryAdd" checked>新規追加</label> <label><input
						type="radio" name="categoryMode" id="rdCategoryUpdate">修正</label>
				</div>
				<button id="btAddCategory" type="button" class="btn btn-success">新規追加</button>
				<button id="btUpdateCategory" class="btn btn-success"
					style="display: none;">修正</button>
			</div>
		</div>

	</div>
</body>

<script type="text/javascript">
	var recentEdit ="";
	var categoryData = "";
	var productList = [];
	var submit = function(){
        $.ajax({
            url:'saveOrUpdate',
            type:'POST',
            data:{user_id:$("#user_id").val(),user_name:$('#name').val(),email:$('#email').val()},
            success: function(response){
                    alert(response.message);
                    load();    
            }              
        });        
	}
 
	var deleteCategory = function(index1){     
		console.log(categoryData[index1].categoryCode);
     	$.ajax({
        	url:'delete',
       	 	type:'POST',
        	data:{categoryCode: categoryData[index1].categoryCode},
        	success: function(response){
                		alert(response.Message);
                		load();
        	}              
    	});
	}
 

	var edit = function (index){
		recentEdit = index;
		$("frCategoryUpdate").show();
    	$("#txtCategoryCode").val(categoryData[index].categoryCode);
    	$("#txtCategoryName").val(categoryData[index].categoryName);
    	$("#txtCategoryDesc").val(categoryData[index].categoryDesc);
    	$('#rdCategoryUpdate').attr('checked','checked');
    	radioChange("rdCategoryUpdate");
		$.ajax({
            url:'productlist',
            type:'GET',
            data:{categoryCode:$("#txtCategoryCode").val(), categoryMode: "Update"},
            success: function(response){
            	var productData = response.Data;
            	console.log(productData);
            	//add product list to category selected
            	$('.trProduct').remove();
                for(i=0; i< productData.length; i++){
                	var row = "<tr class='trProduct' id='trDeleteProductOutOfCategory_" +i+"' name='"+ productData[i].productCode +"''> <td>"+ productData[i].productCode+" </td> <td> "+productData[i].productName+" </td> "
                			+ "<td> <a href='#' onclick='deleteProductOutOfCategory("+ i +")'> Delete </a> </td> "
        					+ "<td> </td>"
                			+ "<td> </td> </tr>";
                    $("#tbProduct").append(row);
                }        
            }              
        });  
	}
	
	var deleteProductOutOfCategory = function(rowIdS){
		console.log('#trDeleteProductOutOfCategory_' + rowIdS);
		$('#trDeleteProductOutOfCategory_' + rowIdS).remove();
	}
	
	var submitEdit = function(){
        $.ajax({
            url:'update',
            type:'POST',
            data:{categoryCode:$("#txtCategoryCode").val(),categoryName:$('#txtCategoryName').val(),categoryDesc:$('#txtCategoryDesc').val()},
            success: function(response){
                    alert(response.message);
                    load();    
         
            }              
        });        
	}
	
	var submitAdd = function(){
		productList = [];
		$('.cbProduct').each(function(i, obj) {
			if($('#' + this.id).is(':checked')){
				var x = { productCode : this.id};
			    productList.push(x);	
			}
		});
		var category = {categoryCode:$('#txtCategoryCode').val(),categoryName:$('#txtCategoryName').val(),categoryDesc:$('#txtCategoryDesc').val()};
		var categoryProductModel = {category: category, productList : productList};
		console.log(categoryProductModel);
        $.ajax({
            url:'add',
            type:'POST',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(categoryProductModel),
            success: function(response){
					alert(response.Message);
                    load();  
                    $('.cbProduct').prop('checked', false);
                    $("#txtCategoryCode").val('');
        	    	$("#txtCategoryName").val('');
        	    	$("#txtCategoryDesc").val('');
            }              
        });        
	}
 
 
	var load = function(){
		$.ajax({
        	url:'categorylist',
        	type:'GET',
        	success: function(response){
        		console.log(response.Data);
        		categoryData = response.Data;
                $('.tbCategoryTr').remove();
                for(var i=0; i< categoryData.length; i++){
                	var row = "<tr class='tbCategoryTr'> <td> "+ categoryData[i].categoryCode +" </td> <td> "+ categoryData[i].categoryName +" </td> "
                			+ "<td>" + categoryData[i].categoryDesc + "</td>"
                			+ "<td> <a href='#' onclick='edit("+ i +")'> Edit </a>  </td> "
                			+ "<td> <a href='#' onclick='deleteCategory("+ i +")'> Delete </a>  </td> </tr>";
                    $("#tbCategory").append(row);
                }          
        	}	              
    	});	
	}
	
	$("#btShowAllProduct").click(function(){
		if($('#divProductAdd').css('display') == 'none'){
			$('#divProductAdd').show();
		}else{
			$('#divProductAdd').hide();
			return;
		}
		$.ajax({
            url:'productlist',
            type:'GET',
            data:{categoryCode:"all", categoryMode:"Add"},
            success: function(response){
        
            	var productData = response.Data;
            	//add product list to category selected
            	$('.trProductAdd').remove();
                for(var i=0; i< productData.length; i++){
                	var row = "<tr class='trProductAdd'> <td> "+ productData[i].productCode+" </td> <td> "+productData[i].productName+" </td> "
                			
                			+ "<td> <input class='cbProduct' id='"+ productData[i].productCode +"' type='checkbox' value='"+ productData[i].productCode +"'></td> "
                			+ "<td></td> "
        					+ "<td></td></tr>";
                    $("#tbProductAdd").append(row);
                }        
            },
            error: function(){
        
            }
        });  
	});
	
	$("#btUpdateCategory").click(function(){
		var productList = [];
		$('.trProduct').each(function(i, obj) {
			var x = { productCode : $('#'+this.id).attr('name')};
		    productList.push(x);
		});
		$('.cbProduct').each(function(i, obj) {
			if($('#' + this.id).is(':checked')){
				var exist = false;
				var x = { productCode : this.id};
				for(var i = 0; i < productList.length; i++){
					if(productList[i].productCode == this.id){
						exist = true;	
					}
				}
				if(!exist){
					productList.push(x);	
				}
			    	
			}
		});
		var category = {categoryCode:$('#txtCategoryCode').val(),categoryName:$('#txtCategoryName').val(),categoryDesc:$('#txtCategoryDesc').val()};
		var categoryProductModel = {category: category, productList : productList};
		console.log(categoryProductModel);
        $.ajax({
            url:'update',
            type:'POST',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(categoryProductModel),
            success: function(response){
					alert(response.Message);
                    load();   
                    edit(recentEdit);
                    $('.cbProduct').prop('checked', false);
            }              
        });  
	});
	
	
	$("#btAddCategory").click(function(){
		submitAdd();
	});
	
	var radioChange = function(id){
		console.log(id);
		if(id == 'rdCategoryAdd'){
			$("#txtCategoryCode").val('');
	    	$("#txtCategoryName").val('');
	    	$("#txtCategoryDesc").val('');
	    	$('.trProduct').remove();
			$("#btAddCategory").show();
			$("#btUpdateCategory").hide();
			 $('#txtCategoryCode').prop("disabled", false); 
		} else {
			$("#btAddCategory").hide();
			$("#btUpdateCategory").show();
			$('#txtCategoryCode').prop("disabled", true);
		}
	}
	
	$("input[name=categoryMode]").change(function(){
		radioChange(this.id);
		
	});
	
</script>
</html>