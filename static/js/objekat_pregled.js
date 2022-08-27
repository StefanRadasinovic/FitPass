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
        }
    });
	
}