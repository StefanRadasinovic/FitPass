function getFormData($form) {
	let unindexedArray = $form.serializeArray();
	let indexedArray = {};
	
	$.map(unindexedArray, function(n, i){
		indexedArray[n['name']] = n['value'];
    });

    return indexedArray;
}

function login() {
	var data = getFormData($("#login")); 
	var s = JSON.stringify(data); 
	$.ajax({
		url: "login",
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		complete : function (response) {
			if (response.status === 200)
            	window.location.replace("/");
		}, error: function(e){
		    if (e.status == 400){
		        alert("Neuspesan login");
		        return;
		    } 
		}
	});
}