$(document).ready( function() {
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
			console.log(data)
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
						if (t.kupac) {
							red += "<td>" + t.kupac + "</td>";		
						} else {
							red += "<td>/</td>";
						}
						red += "<td><img width=50 height=50 src='" + t.slikaURL + "'</td>";
						red += "<td><a href='' class='btn btn-danger'>Otkazivanje</a></td>";				
						tableBody.append(red);
			        }
					
			    }
			});
	    }
	});
}
