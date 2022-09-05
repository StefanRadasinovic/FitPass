function getFormData($form) {
	let unindexedArray = $form.serializeArray();
	let indexedArray = {};

	$.map(unindexedArray, function(n, i) {
		indexedArray[n['name']] = n['value'];
	});

	return indexedArray;
}

function registruj() {
	var data = getFormData($("#registracija"));
	var s = JSON.stringify(data);
	var myURL = "";
	myURL = "registracija";

	$.ajax({
		url: myURL,
		type: "POST",
		data: s,
		contentType: "application/json",
		dataType: "json",
		complete: function(data) {
			if (data.status == 400)
				alert("Neuspesna registracija");
			else {
				alert("Uspesno ste registrovali korisnika.");
				window.location.replace("/");
			}
		}
	});
}