$(document).ready( function() {
	ucitajMenadzerovObjekat();
});

function ucitajMenadzerovObjekat() {
	$.ajax({
        url: "/sportski-objekti/menadzer-objekat",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function (error) {
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
 			
 			$("#nav").append('<li class="nav-item"><a class="nav-link" href="/novi_objekat?objekatId=' + sportskiObjekat.id + '">Novi objekat</a></li>')
 			
        }
    });
}
