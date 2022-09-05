$(document).ready(function() {
	getMenadzeri();
});

function getMenadzeri() {
	$.ajax({
		url: "menadzeriBezObjekta",
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		complete: function(data) {
			menadzeriBezObjekta = data.responseJSON;
			let select = $("#menadzer");

			for (let m of menadzeriBezObjekta) {
				select.append("<option value='" + m.id + "' >" + m.ime + " " + m.prezime + "</option>");
			}

			if (menadzeriBezObjekta.length === 0) {
				$("#dugmePotvrda").attr('disabled', true);
			}
		}
	});
}

function sacuvaj() {

	if ($("#logo")[0].files.length === 0) {
		alert("Izaberite sliku!");
		return;
	}

	let formData = new FormData($("#forma")[0]);

	$.ajax({
		url: "/sportski-objekti/novi",
		type: "POST",
		data: formData,
		enctype: "multipart/form-data",
		processData: false,
		contentType: false,
		cache: false,
		error: function(data) {
			alert("Data not valid");
		},
		success: function(data) {
			alert("Uspesno sacuvano");
			window.location.reload();

		}
	});
}