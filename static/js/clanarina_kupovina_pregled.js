$(document).ready(function() {

	pregledClanarine();
});

function getUrlVars() {
	let vars = {};
	let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m, key, value) {
		vars[key] = value;
	});
	return vars;
}

function pregledClanarine() {
	let urlVars = getUrlVars();

	$.ajax({
		url: "/clanarina/pregled/" + urlVars.clanarinaId,
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		error: function() {
			alert("Greska");
		},
		success: function(data) {
			$("#naziv").html(data.naziv);
			$("#tip").html(data.tip);
			$("#cena").html(data.cena);
			$("#brojTermina").html(data.brojTermina);
			$("#daniVazenja").html(data.kolikoDanaVazi);
		}
	});
}

function kupovina() {
	let urlVars = getUrlVars();

	if (!confirm("Da li potvrdjujete kupovinu clanarine?")) {
		return;
	}

	$.ajax({
		url: "/clanarina/kupovina/" + urlVars.clanarinaId,
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		error: function() {
			alert("Greska");
		},
		success: function(data) {
			alert("Uspesna kupovina");
			window.location.href = "/";
		}
	});
}