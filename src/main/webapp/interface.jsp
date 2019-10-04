<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="mainForm">
		<div class="col-lg-12">
			<div class="col-lg-12 " style="border: 1px solid blue; border-radius: 12px;">
				<div><label style="padding-left:40%;  font-size: 20px;">Interface de Comunicação</label></div>
				<div class="col-lg-3">
				</div>
				<div class="col-lg-6" style="padding-top:1.9%; border: 1px solid blue; border-radius: 12px;">
					<label>Interação:</label>
					<button type="submit" class="btn btn-control sm">Consultar</button>
					<textarea class="form-control" name="interaction"></textarea>
					<label>Resultado:</label>
					<textarea class="form-control" name="result"></textarea>
				</div>
				<div class="col-lg-3 ">
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">

$('#mainForm').on('submit', function(e){
	e.preventDefault();
	var datastring = $("#mainForm").serialize();
	$.ajax({
	    type: "POST",
	    url: "rest/interaction/first",
	    data: datastring,
	    dataType: "json",
	    success: function(data) {
	    	debugger;
	    	if(data)
	    	{
	    		$("[name='result']").val(data.returnValue);
	    	}
	    }
	    });
});

</script>
</html>