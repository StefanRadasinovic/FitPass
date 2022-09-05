$(document).ready( function() {
	getSportskiObjekti();
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
				red += `<td><button class="btn btn-dark" onclick="ucitajTreningeZaObjekat('${s.id}')">Treninzi</button></td>`
				red += "</tr>";
				
                elem.append(red);
            }
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
				red += `<td><button class="btn btn-dark" onclick="prijava('${t.id}')">Prijava</button></td>`;				
				tableBody.append(red);
			
	    }
	}
});
	
}

function prijava(treningId) {
	$.ajax({
	    url: "/trening/prijava/" + treningId,
	    type: "POST",
	    error: function(error) {

			alert(error.responseText)
	
		},
	    success: function(data) {
			alert("Uspesno sacuvano");
			window.location.href = "/";
		}
	});
}