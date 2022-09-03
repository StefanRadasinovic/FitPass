$(document).ready( function() {
	getClanarine();
});


function getClanarine() {
	$.ajax({
        url: "/clanarine",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function (error) {
			
		},
        success: function(data) {
			let tableBody = $("#clanarine");
            tableBody.html("");
            for (let clanarina of data) {
                let red = "<tr>";
				red += "<td>" + clanarina.naziv + "</td>";
				red += "<td>" + clanarina.tip + "</td>";
				red += "<td>" + clanarina.cena + "</td>";

				if (clanarina.brojTermina === 0) {
					red += "<td>" + clanarina.brojTermina + "</td>";		
				} else {
					red += "<td>neograniceno</td>";
				}
				
				red += "<td>" + clanarina.kolikoDanaVazi + "</td>";
				red += "<td><a class='btn btn-dark' href='/clanarina_kupovina.html?clanarinaId=" + clanarina.id + "'>Kupovina</a></td>";
				red += "</tr>";
				tableBody.append(red);
            }
        }
    });
}
