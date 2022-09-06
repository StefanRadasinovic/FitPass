$(document).ready(function() {
	ucitajMenadzerovObjekat();
});

function ucitajMenadzerovObjekat() {
	$.ajax({
		url: "/sportski-objekti/menadzer-objekat",
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		error: function(error) {
			if (error.status === 403) {
				return;
			} else if (error.status === 404) {
				$("#objekat").html("<h3>Objekat nije pronadjen</h3>");
				return;
			}
		},
		success: function(data) {
			let sportskiObjekat = data;
			$("#objekatId").val(sportskiObjekat.id);
			$("#naziv").html(sportskiObjekat.naziv);
			$("#tip").html(sportskiObjekat.tipObjekta);
			$("#status").html(sportskiObjekat.status);
			$("#lokacija").html(sportskiObjekat.lokacija.adresa);
			$("#logo").attr("src", sportskiObjekat.logo);
			$("#ocena").html(sportskiObjekat.prosecnaOcena);

			$("#nav").append('<li class="nav-item"><a class="nav-link" href="/novi_trening.html?objekatId=' + sportskiObjekat.id + '">Novi trening</a></li>');
			$("#nav").append('<li class="nav-item"><a class="nav-link" href="/korisnici_objekat.html?objekatId=' + sportskiObjekat.id + '">Korisnici</a></li>')

			ucitajTreningeZaObjekat(sportskiObjekat.id);
		}
	});
}

function ucitajTreningeZaObjekat(objekatId) {
	$.ajax({
		url: "/treninzi/objekat/" + objekatId,
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		error: function(error) {
			alert("Treninzi nisu ucitani")
		},
		success: function(data) {
			treninzi = data;
			let tableBody = $("#treninzi");
			tableBody.html("");
			for (let t of treninzi) {
				let red = "<tr>";
				red += "<td>" + t.naziv + "</td>";
				red += "<td>" + t.objekat + "</td>";
				red += "<td>" + t.status + "</td>";
				red += "<td>" + t.tip + "</td>";
				red += "<td>" + t.trajanje + "</td>";
				red += "<td>" + t.opis + "</td>";
				red += "<td>" + t.cena + "</td>";
				red += "<td>" + t.datum + "</td>";
				red += "<td>" + t.trener + "</td>";
				red += "<td>" + t.kupac + "</td>";
				red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
				red += "<td><a class='btn btn-dark' href='/izmena_treninga.html?treningId=" + t.id + "'>Izmena</td>";
				red += `<td><button class='btn btn-danger' onclick='obrisi("${t.id}")'>Obrisi</button></td>`;
				tableBody.append(red);

			}
		}
	});

}

function obrisi(treningId) {
	$.ajax({
		url: "/trening/brisanje/" + treningId,
		type: "DELETE",
		success: function(data) {
			alert("Usepsno obrisan");
			window.location.reload();
		}
	});
}
