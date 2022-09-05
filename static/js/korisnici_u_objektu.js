$(document).ready(function() {
	getKorisniciUObjektu();
});

function getUrlVars() {
	let vars = {};
	let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m, key, value) {
		vars[key] = value;
	});
	return vars;
}

function getKorisniciUObjektu() {
	let objekatId = getUrlVars().objekatId;
	$.ajax({
		url: "/korisnici/objekat/" + objekatId,
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			let tableBody = $("#korisnici");
			tableBody.html("");
			for (let korisnik of data) {
				let red = "<tr>";
				red += "<td>" + korisnik.ime + "</td>";
				red += "<td>" + korisnik.prezime + "</td>";
				red += "<td>" + korisnik.korisnickoIme + "</td>";
				red += "<td>" + korisnik.uloga + "</td>";
				red += "<td>" + korisnik.tip + "</td>";
				red += "<td>" + korisnik.pol + "</td>";
				red += "<td>" + korisnik.datumRodjenja + "</td>";
				red += "<td>" + korisnik.sakupljeniBodovi + "</td>";
				tableBody.append(red);
			}
		}
	});
}
