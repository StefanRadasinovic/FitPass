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
				red += `<td><button class="btn btn-danger" onclick="brisanje('${s.id}')">Brisanje</button></td>`;
				red += "</tr>";
				
                elem.append(red);
            }
        }
	});
}

function brisanje(objekatId) {
	$.ajax({
        url: "sportski-objekat/brisanje/" + objekatId,
        type: "DELETE",
        success: function(data) {
            alert("Usepsno obrisan");
            window.location.reload();
        }
	});
}