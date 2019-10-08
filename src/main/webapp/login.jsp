<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cripto</title>
</head>
<body>
	<form id="login">
		<div class="col-lg-12">
			<div class="col-lg-5">
				<label>Created by: Mateus Valcanaia, Milton Machado e Guilherme Medeiros</label>
			</div>
			<div class="col-lg-4">
				<label>Worlds best Cipher System</label>
			</div>
			<div class="col-lg-3">
				<label>Version 1.0</label>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-12 " style="border: 1px solid blue; border-radius: 12px;">
				<div><label style="padding-left:40%;  font-size: 20px;">Sua senha é forte? Faça o teste!</label></div>
				<div class="col-lg-4">
					<label>Insira a senha para validar:</label>
					<input type="text"  class="form-control form-control-lg"id="senhaTeste">
				</div>
				<div class="col-lg-4" style="padding-top:1.9%;">
					<button type="button" id="validar" class="btn btn-primary">Validar</button>
				</div>
				<div class="col-lg-2 " style="border: 1px solid blue; border-radius: 12px;">
					<a style="padding-left:35%; cursor:pointer;" id="esqueciSenha">Esqueci Senha</a>
				</div>
				<div class="col-lg-1 " style="border: 1px solid blue; border-radius: 12px;">
					<a style="padding-left:35%; cursor:pointer;" id="loginModal">Login</a>
				</div>
				<div class="col-lg-1 " style="border: 1px solid blue; border-radius: 12px;">
					<a style="padding-left:35%; cursor:pointer;" id="cadastroModal">Cadastro</a>
				</div>
				<div id="chartContainer" class="col-lg-12"style="height: 300px; width: 100%;">
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalLogin" role="dialog">
  		  <div class="modal-dialog">
    	  <!-- Modal content-->
    		 <div class="modal-content">
    		 	  <input type="hidden" value="0" id="tentativa" name="tentativa">
      			  <div class="modal-header">
       			   <button type="button" class="close" data-dismiss="modal">&times;</button>
         			<h4 class="modal-title">Faça seu Login</h4>
      			  </div>
     		   <div class="modal-body">
   	 	     	<label>Usúario:<input type="text" class="form-control form-control-sm"name="usuario"id="usuario"/></label>
				<label>Senha:<input type="password" class="form-control form-control-sm"name="senha"id="password"/></label>
     	   	  	<div>
     	   	  	<input type="hidden" name="sinonimo" id="sinonimoHidden">
     	   	  	<label>Favor validar os sinonimos da palavra: </label><input type ="text" value="Sinonimo" class="form-control" disabled  id="sinonimo">
     	   	  	<label>Sinonimos:</label>
     	   	  	<div class="col-lg-12">
     	   	  		<div class="col-lg-3">
     	   	  			<input type="hidden" name="0label" id="0label">
     	   	  			<input type="checkbox" name="input" id="0">&nbsp<label id="label0">Senha</label>&nbsp&nbsp
     	   	  		</div>
     	   	  		<div class="col-lg-3">
     	   	  			<input type="hidden" name="1label" id="1label">
     	   	  			<input type="checkbox" name="input" id="1">&nbsp<label id="label1">Senha</label>&nbsp&nbsp
     	   	  		</div>
     	   	  		<div class="col-lg-3">
     	   	  			<input type="hidden" name="2label" id="2label">
     	   	  			<input type="checkbox" name="input"id="2">&nbsp<label id="label2">Senha</label>&nbsp&nbsp
     	   	  		</div>
     	   	  		<div class="col-lg-3">
     	   	  			<input type="hidden" name="3label" id="3label">
     	   	  			<input type="checkbox" name="input"id="3">&nbsp<label id="label3">Senha</label>
     	   	  		</div>
				</div>     	   	   
     	   	   </div>
     	   	   </div>
     	   	   <div><div class="modal-body"></div></div>
     	   	   <div class="modal-footer" style="padding-top:1%;">
       	  		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       	  		 <button type="button" id="logar" class="btn btn-success">Login</button>
        	   </div>
     		 </div>
     	 </div>
     	</div>
     	<div class="modal fade" id="modalCadastro" role="dialog">
  		  <div class="modal-dialog">
    	  <!-- Modal content-->
    		 <div class="modal-content">
      			  <div class="modal-header">
       			   <button type="button" class="close" data-dismiss="modal">&times;</button>
         			<h4 class="modal-title">Faça seu Cadastro</h4>
      			  </div>
     		   <div class="modal-body">
   	 	     	<label>Nome:<input type="text" class="form-control form-control-sm" name ="nomeUsuario" id="nomeUsuario"></label><br/>
				<label>Email:<input type="text" class="form-control form-control-sm" name="emailUsuario" id="emailUsuario"></label><br/>
				<label>Usúario:<input type="text" class="form-control form-control-sm" name="novoUsuario" id="novoUsuario"></label><br/>
				<label>Senha:<input type="password" class="form-control form-control-sm" name="novaSenha" id="novaSenha"></label><br/>
     	   	   </div>
     	   	   <div class="modal-footer">
       	  		 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       	  		 <button type="button" id="cadastrar"class="btn btn-success">Cadastrar</button>
        	   </div>
     		 </div>
     	 </div>
     	</div>
	</form>
</body>
<script>
$('#logar').on('click', function(e){
	e.preventDefault();
	var datastring = $("#login").serialize();
	$.ajax({
	    type: "POST",
	    url: "rest/login/login",
	    data: datastring,
	    dataType: "json",
	    success: function(data) {
	    	if(data.success){
	    		if(data.mensagem)
	    		{
	    			alert(data.mensagem);
	    		}
	    		else
	    		{
	    			alert('Login efetuado com Sucesso!');
	    		}
	    		$('#modalLogin').modal('hide');
	    	}
	    	else{
	    		$('#tentativa').val(data.tentativa);
	    		if(data.mensagem)
	    		{
	    			alert(data.mensagem);
		    		$('#modalLogin').modal('hide');
	    		}
	    		else
	    		{
	    			alert('Login ou senha invalidas!');
	    		}
	    		
	    	}
	    }
	});
});
// $("input:checkbox[name=input]:checked").each(function(){
//     yourArray.push($(this).val());
// });
$('[name="input"]').on('click', function(e){
	var id = e.currentTarget.id;
	if(e.currentTarget.checked)
	{
		$('#'+id+'label').val($('#label'+id)[0].innerHTML);
	}
	else
	{
		$('#'+id+'label').val("");
	}
	
});
$('#cadastrar').on('click', function(e){
	e.preventDefault();
	var datastring = $("#login").serialize();
	$.ajax({
	    type: "POST",
	    url: "rest/login/novo",
	    data: datastring,
	    dataType: "json",
	    success: function(data) {
	    	alert(data.mensagem)
	    	$('#modalCadastro').modal('hide');
	    }
	});
// 	$.post('rest/login/novo?senha='+$('#senhaTeste').val()).done(function(data){
// 		chart.options.data[0].dataPoints.push({ y: data.quantidade, x:data.nota});
// 		chart.render();
// 	});
});
$('#loginModal').on('click', function(e){
	e.preventDefault();
	$.getJSON('rest/login/sinonimos').done(function(data){
		if(data.palavraChave)
		{	
			limpar();
			$('#sinonimo').val(data.palavraChave);
			$('#sinonimoHidden').val(data.palavraChave);
			for(i=0; i<data.sinonimos.length; i++)
			{
				$("#label"+i)[0].innerHTML = data.sinonimos[i].palavra;
			}
		}
	});
	$('#modalLogin').modal('show');
});
function limpar()
{
	$('#0')[0].checked = false;
	$('#1')[0].checked = false;
	$('#2')[0].checked = false;
	$('#3')[0].checked = false;
	$('#usuario').val("");
	$('#password').val("");
	$('#0label').val("");
	$('#1label').val("");
	$('#2label').val("");
	$('#3label').val("");
}
$('#cadastroModal').on('click', function(e){
	e.preventDefault();
	$('#modalCadastro').modal('show');
});
var chart;
$(document).ready(function() {
	initializeGraphic();
	loadValues();
});
function initializeGraphic(){
	 chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		zoomEnabled: true,
		title:{
			text: "Ranking das Senhas"
		},
		axisX: {
			title:"Nota da Senha",
			minimum: 0,
			maximum: 10
		},
		axisY:{
			title: "Quantidade Caracteres",
			minimum: 0,
			maximum: 20
		},
		data: [{
			type: "scatter",
			toolTipContent: "<b>Nota: </b>{x} <br/><b>Caracteres: </b>{y}",
			dataPoints: [{ x: 1, y: 20}]
		}]
	});
	chart.render();
}
function loadValues()
{
	$.getJSON('rest/login/loaddados').done(function(data){
		for(i=0; i<data.ranking.length;i++)
		{
			chart.options.data[0].dataPoints.push({ y: data.ranking[i].quantidade, x:data.ranking[i].nota});
		}
		chart.render();
	});
}
$('#validar').on('click', function(){
	$.getJSON('rest/login/novodado?senha='+$('#senhaTeste').val()).done(function(data){
		chart.options.data[0].dataPoints.push({ y: data.quantidade, x:data.nota});
		chart.render();
	});
})

</script>
</html>