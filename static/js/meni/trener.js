$(document).ready(function() {
	ucitajGrupneTreninge();
});

function ucitajGrupneTreninge() {
	$.ajax({
		url: "/treninzi/trener/grupni",
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		error: function(error) {

		},
		success: function(data) {
			treninzi = data;
			let tableBody = $("#grupni-treninzi");
			tableBody.html("");
			for (let t of treninzi) {
				let red = "<tr>";
				red += "<td>" + t.naziv + "</td>";
				red += "<td>" + t.objekat + "</td>";
				red += "<td>" + t.tip + "</td>";
				red += "<td>" + t.trajanje + "</td>";
				red += "<td>" + t.opis + "</td>";
				red += "<td>" + t.cena + "</td>";
				red += "<td>" + t.datum + "</td>";
				red += "<td>" + t.trener + "</td>";
				if (t.kupac) {
					red += "<td>" + t.kupac + "</td>";
				} else {
					red += "<td>/</td>";
				}
				red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
				tableBody.append(red);
			}

			$.ajax({
				url: "/treninzi/trener/individualni",
				type: "GET",
				contentType: "application/json",
				dataType: "json",
				error: function(error) {

				},
				success: function(data) {

					treninzi = data;
					let tableBody = $("#individualni-treninzi");
					tableBody.html("");
					for (let t of treninzi) {
						let red = "<tr>";
						red += "<td>" + t.naziv + "</td>";
						red += "<td>" + t.objekat + "</td>";
						red += "<td>" + t.tip + "</td>";
						red += "<td>" + t.trajanje + "</td>";
						red += "<td>" + t.opis + "</td>";
						red += "<td>" + t.cena + "</td>";
						red += "<td>" + t.datum + "</td>";
						red += "<td>" + t.trener + "</td>";
						red += "<td>" + t.otkazan + "</td>";
						if (t.kupac) {
							red += "<td>" + t.kupac + "</td>";
						} else {
							red += "<td>/</td>";
						}
						red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
						red += `<td><button class='btn btn-danger' onclick='otkazi("${t.id}")'>Otkazivanje</button></td>`;
						tableBody.append(red);
					}

				}
			});
		}
	});
}

function pretraga() {
	let pretragaTekst = $("#pretraga").val();
	let cenaOd = $('#cenaOd').val();
	let cenaDo = $('#cenaDo').val();
	let datumOd = $('#datumOd').val();
	let datumDo = $('#datumDo').val();
	let tipTreninga = $('#tip').val();
	let bezDoplate = $('#bezDoplate').is(':checked');
	let rastuceSortiranje = $("#sortRastuce").is(":checked");
	let sortiranjePo = $("#sortiranjePo").val();

	let queryParams = jQuery.param({
		pretragaTekst,
		cenaOd,
		cenaDo,
		datumOd,
		datumDo,
		tipTreninga,
		bezDoplate,
		rastuceSortiranje,
		sortiranjePo
	})

	$.ajax({
		url: "/trening/pretraga?" + queryParams,
		type: "GET",
		contentType: "application/json",
		dataType: "json",
		success: function(data) {

			let tableBodyIndividualni = $("#individualni-treninzi");
			let tableBodyGrupni = $("#grupni-treninzi");
			tableBodyIndividualni.html("");
			tableBodyGrupni.html("");
			for (let treningProsireno of data) {
				let t = treningProsireno.trening;

				if (t.tip === "INDIVIDUALNI") {
					let red = "<tr>";
					red += "<td>" + t.naziv + "</td>";
					red += "<td>" + treningProsireno.sportskiObjekat.naziv + "</td>";
					red += "<td>" + t.tip + "</td>";
					red += "<td>" + t.trajanje + "</td>";
					red += "<td>" + t.opis + "</td>";
					red += "<td>" + t.cena + "</td>";
					red += "<td>" + t.datum + "</td>";
					red += "<td>" + treningProsireno.trener + "</td>";
					if (t.kupac) {
						red += "<td>" + t.kupac + "</td>";
					} else {
						red += "<td>/</td>";
					}
					red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
					red += "<td><button class='btn btn-danger' onclick='otkazi(" + t.id + ")'>Otkazivanje</button></td>";
					tableBodyIndividualni.append(red);
				} else {
					let red = "<tr>";
					red += "<td>" + t.naziv + "</td>";
					red += "<td>" + treningProsireno.sportskiObjekat.naziv + "</td>";
					red += "<td>" + t.tip + "</td>";
					red += "<td>" + t.trajanje + "</td>";
					red += "<td>" + t.opis + "</td>";
					red += "<td>" + t.cena + "</td>";
					red += "<td>" + t.datum + "</td>";
					red += "<td>" + treningProsireno.trener + "</td>";
					if (t.kupac) {
						red += "<td>" + t.kupac + "</td>";
					} else {
						red += "<td>/</td>";
					}
					red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
					red += "<td><button class='btn btn-danger' onclick='otkazi(" + t.id + ")'>Otkazivanje</button></td>";
					tableBodyGrupni.append(red);
				}

			}
		}
	});
}

function otkazi(treningId) {
	$.ajax({
		url: "/trening/otkazivanje/" + treningId,
		type: "PUT",
		dataType: "json",
		error: function(error) {
			alert(error.responseText)
		},
		success: function(data) {
			alert("Sacuvano");
			window.location.reload();
		}
	});
}