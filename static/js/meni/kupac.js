$(document).ready( function() {
	ucitajIstoriju();
});

function ucitajIstoriju() {
	$.ajax({
	    url: "/trening/istorija",
	    type: "GET",
	    contentType: "application/json",
	    dataType: "json",
	    success: function(data) {
			
			let tableBody = $("#istorija-treninga");
			
	        tableBody.html("");
	        for (let istorija of data) {
	            let red = "<tr>";
					red += "<td>" + istorija.datum + "</td>";
					red += "<td>" + istorija.trening + "</td>";
					red += "<td>" + istorija.trener + "</td>";
					red += "<td>" + istorija.sportskiObjekat + "</td>";
					red += "</tr>"
					tableBody.append(red);
	        }
			
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
        url: "/trening/istorija-pretraga?" + queryParams,
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            
            let tableBody = $("#istorija-treninga");
			
	        tableBody.html("");
	        for (let istorija of data) {
	            let red = "<tr>";
					red += "<td>" + istorija.datum + "</td>";
					red += "<td>" + istorija.trening + "</td>";
					red += "<td>" + istorija.trener + "</td>";
					red += "<td>" + istorija.sportskiObjekat + "</td>";
					red += "</tr>"
					tableBody.append(red);
	        }
        }
	});
}