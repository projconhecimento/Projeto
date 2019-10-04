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
			<div class="col-lg-4">
				<label>Created by: Mateus Valcanaia</label>
			</div>
			<div class="col-lg-4">
				<label>Worlds best Cipher System</label>
			</div>
			<div class="col-lg-4">
				<label>Version 1.0</label>
			</div>
		</div>
		<div class="col-lg-12">
			<div class="col-lg-2 " style="border: 1px solid blue; border-radius: 12px;">
				<label style="padding-left:35%;">LOGIN:</label>
				<label>Usúario:<input type="text" class="form-control form-control-sm"id="usuario"></label>
				<label>Senha:<input type="password" class="form-control form-control-sm"id="password"></label>
			</div>
			<div class="col-lg-10 " style="border: 1px solid blue; border-radius: 12px;">
				<div><label style="padding-left:40%;  font-size: 20px;">Sua senha é forte? Faça o teste!</label></div>
				<div class="col-lg-4">
					<label>Insira a senha para validar:</label>
					<input type="text"  class="form-control form-control-lg"id="senhaTeste">
				</div>
				<div class="col-lg-8" style="padding-top:2.3%;">
					<button type="button" id="validar" class="btn btn-primary">Validar</button>
				</div>
				<div id="chartContainer" class="col-lg-12"style="height: 300px; width: 100%;">
				</div>
			</div>
		</div>
		
	</form>
</body>
<script>

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
			maximum: 100
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
	
}
$('#validar').on('click', function(){
	debugger;
	$.getJSON('rest/login/novodado?senha='+$('#senhaTeste').val()).done(function(data){
		debugger;
		chart.options.data.push({ y: data.quantidade, x:data.nota});
		chart.render();
	});
})

</script>
</html>