$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	{
		url : "DoctorAPI",
		type : type,
		data : $("#formDOC").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onDOCSaveComplete(response.responseText, status);
			 //console.log(onPatientSaveComplete(response.responseText, status));
		}
	});
});


function onDOCSaveComplete(response, status)
{
	
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
 
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid1").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formDOC")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#firstname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#lastname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#password").val($(this).closest("tr").find('td:eq(3)').text());
	$("#phonenumber").val($(this).closest("tr").find('td:eq(4)').text());
	$("#specalization").val($(this).closest("tr").find('td:eq(5)').text());
	$("#charges").val($(this).closest("tr").find('td:eq(6)').text());
	$("#type").val($(this).closest("tr").find('td:eq(7)').text());

	
}); 

//REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "DoctorAPI",
		 type : "DELETE",
		 data : "doctorid=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 onDOCDeleteComplete(response.responseText, status);
		 }
		 
		 });
		});

function onDOCDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid1").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//CLIENT-MODEL================================================================
function validateDoctorForm()
{
	//Firstname
	if ($("#firstname").val().trim() == "")
	{
		return "firstname cannot be null";
	}
	

	var f_name = $("#firstname").val().trim();
	if ($.isNumeric(f_name)) {
		return "firstname cannot bet be Numeric";
	}
	

	
//Lastname-----------------------------------------------
	
	if ($("#lastname").val().trim() == "")
	{
		return " Lastname cannot be null";
	}

	var l_name = $("#lastname").val().trim();
	if ($.isNumeric(l_name)) {
		return "Lastname cannot bet be Numeric";
	}
	
	
	
//Email-----------------------------------
	
	if ($("#email").val().trim() == "")
	{
		return " email cannot be null";
	}
	
	//valid or not
	var em = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;	
	var email = $("#email").val().trim();
	if (em.test(email) == false) 
	{
	return "Please enter valid email";
	}
	
	
//Password--------------------------------	
	if ($("#password").val().trim() == "")
	{
		return " password cannot be null";
	}
	

//Phone--------------------------------	
	if ($("#phonenumber").val().trim() == "")
	{
		return "phone no cannot be null.";
	}
	
//should be numeric
	var pno = $("#phonenumber").val().trim();
	if (!$.isNumeric(pno)) {
		return "Insert a correct Phone No";
	}
	
	var p = /^\d{10}$/;
	if (!p.test(pno)) {
		return "Phone number should have 10 numbers";
	}
	
	
	
//specalization--------------------------
	if ($("#specalization").val().trim() == "")
	{
		return "Please enter specialization.";
	}
	
	var spec = $("#specalization").val().trim();
	if ($.isNumeric(spec)) {
		return "specalization cannot have numeric values";
	}
	
	
// charge-------------------------------
	if ($("#charges").val().trim() == "")
	{
		return "Doctor Charge cannot be null.";
	}
	// is numerical value
	var tmpcharge = $("#charges").val().trim();
	if (!$.isNumeric(tmpcharge))
	{
		return "Charge should be numeric.";
	}
	// convert to decimal charge
	$("#charges").val(parseFloat(tmpcharge).toFixed(2));
	
	

	
//------------------Doctor Type------------------
	
	if ($("#type").val().trim() == "")
	{
		return "Please enter Doctor type.";
	}
	var t = $("#type").val().trim();
	if ($.isNumeric(t)) {
		return "Type cannot have numeric values";
	}
	
	
	return true;
}















