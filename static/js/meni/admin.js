$(document).ready( function() {
	ucitajKorisnike();
});

function ucitajKorisnike() {
	$.ajax({
        url: "/korisnici/svi",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function (error) {
			
		},
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

function pretraga() {
	let pretragaTekst = $("#pretraga").val();
	let pretragaPo = $("#pretragaPo").val();
	let tip = $('#tip').val();
	let uloga = $('#uloga').val();
	let rastuceSortiranje = $("#sortRastuce").is(":checked");
	let sortiranjePo = $("#sortiranjePo").val();
	
	let queryParams = jQuery.param({
		pretragaTekst,
		pretragaPo,
		tip,
		uloga,
		rastuceSortiranje,
		sortiranjePo
	})
	
	$.ajax({
        url: "/korisnici/pretraga?" + queryParams,
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