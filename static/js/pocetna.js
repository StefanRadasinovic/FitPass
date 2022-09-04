$(document).ready( function() {
	getSportskiObjekti();
	getUloga();
});


function getSportskiObjekti() {
	$.ajax({
        url: "sportski-objekti",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        complete: function(data) {
            const sportskiObjekti = data.responseJSON;
            
            let elem = $("#objekti");
            elem.html("");
            for (let s of sportskiObjekti) {
				let red = "<tr>"
				red += "<td>" + s.naziv + "</td>";
				red += "<td>" + s.tipObjekta + "</td>";
				red += "<td>" + s.status + "</td>";
				red += "<td>" + s.sadrzaj + "</td>";
				red += "<td>" + s.lokacija.adresa + "</td>";
				red += "<td>" + s.radnoVreme + "</td>";
				red += "<td>" + s.prosecnaOcena + "</td>";
				red += "<td><img width=50 height=50 src='" + s.logo + "'</td>";
				red += "<td><a href='/objekat_pregled.html?objekatId=" + s.id + "' class='btn btn-dark'>" + "Pregled" + "</a></td>";
				red += "</tr>";
				
                elem.append(red);
            }
        }
	});
}

function pretraga() {
	let pretragaTekst = $("#pretraga").val();
	let pretragaPo = $("#pretragaPo").val();
	let tipObjekta = $('input[name="tip"]:checked').val();
	let otvoreni = $('#otvoreni').is(':checked');
	let rastuceSortiranje = $("#sortRastuce").is(":checked");
	let sortiranjePo = $("#sortiranjePo").val();
	
	let queryParams = jQuery.param({
		pretragaTekst,
		pretragaPo,
		tipObjekta,
		otvoreni,
		rastuceSortiranje,
		sortiranjePo
	})
	
	$.ajax({
        url: "sportski-objekti/pretraga?" + queryParams,
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        complete: function(data) {
            const sportskiObjekti = data.responseJSON;
            
            let elem = $("#objekti");
            elem.html("");
            for (let s of sportskiObjekti) {
				let red = "<tr>"
				red += "<td>" + s.naziv + "</td>";
				red += "<td>" + s.tipObjekta + "</td>";
				red += "<td>" + s.status + "</td>";
				red += "<td>" + s.sadrzaj + "</td>";
				red += "<td>" + s.lokacija.adresa + "</td>";
				red += "<td>" + s.radnoVreme + "</td>";
				red += "<td>" + s.prosecnaOcena + "</td>";
				red += "<td><img width=50 height=50 src='" + s.logo + "'</td>";
				red += "</tr>";
				
                elem.append(red);
            }
        }
	});
}

function getUloga() {
	$.ajax({
        url: "korisnik/uloga",
        type: "GET",
        success: function(data) {
			console.log(data);
            if (data === 'KUPAC') {
				$("#navbar").append("<li class='nav-item'><a class='nav-link' href='/meni/meni_kupac.html'>Kupac</a></li>")
        	} else if (data === "MENADZER") {
				$("#navbar").append("<li class='nav-item'><a class='nav-link' href='/meni/meni_menadzer.html'>Menadzer</a></li>")
			} else if (data === "ADMINISTRATOR") {
				$("#navbar").append("<li class='nav-item'><a class='nav-link' href='/meni/meni_admin.html'>Admin</a></li>")
			} else if (data === "TRENER") {
				$("#navbar").append("<li class='nav-item'><a class='nav-link' href='/meni/meni_trener.html'>Trener</a></li>")
			}
		}
	});
}
	