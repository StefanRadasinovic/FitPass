$(document).ready( function() {
	ucitajPodatkeZaObjekat();
});

function getUrlVars() {
    let vars = {};
    let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function ucitajPodatkeZaObjekat() {
	let objekatId = getUrlVars().objekatId;
	$.ajax({
        url: "/sportski-objekti/" + objekatId,
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        complete: function(data) {
            let sportskiObjekat = data.responseJSON;
 			$("#naziv").html(sportskiObjekat.naziv);
 			$("#tip").html(sportskiObjekat.tipObjekta);
 			$("#status").html(sportskiObjekat.status);
 			$("#lokacija").html(sportskiObjekat.lokacija.adresa);
 			$("#logo").attr("src", sportskiObjekat.logo);
 			$("#ocena").html(sportskiObjekat.prosecnaOcena);
 			
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
					
				tableBody.append(red);
			
	    }
	}
	});
}