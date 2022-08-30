$(document).ready( function() {
	ucitajProfil();
});

function getUrlVars() {
    let vars = {};
    let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function getFormData($form) {
	let unindexedArray = $form.serializeArray();
	let indexedArray = {};
	
	$.map(unindexedArray, function(n, i){
		indexedArray[n['name']] = n['value'];
    });

    return indexedArray;
}

function ucitajProfil() {
	$.ajax({
        url: "/korisnik/pregled",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function() {
        	alert("Niste ulogovani");
        },
        success: function(data) {
			$("#ime").val(data.ime);
			$("#prezime").val(data.prezime);
			$("#korisnickoIme").html(data.korisnickoIme);
			$("#datumRodjenja").val(data.datumRodjenja);
			$("#pol").val(data.pol);
			$("#uloga").val(data.uloga);
			$("#tip").html(data.tip);
			$("#uloga").html(data.uloga);
        }
	});
	
}

function sacuvaj() {
	let data = getFormData($("#profil"));
    let json = JSON.stringify(data);
        
    $.ajax({
        url: "/korisnik/pregled",
        type: "PUT",
        data: json,
        contentType: "application/json",
        success: function(data) {
            alert("Uspesno sacuvano");
            window.location.reload();
        },
        error: function () {	
			alert("Neispravan unos");
		}
    });
}