$(document).ready( function() {
	getKupci();
});

function getUrlVars() {
    let vars = {};
    let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function preuzmiTrening() {
	let urlVars = getUrlVars();
	
	$("#treningId").val(urlVars.treningId);
	
	$.ajax({
        url: "trening/pregled/" + urlVars.treningId,
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function() {	
			alert("Greska");
		},
        success: function(data) {
            $("#treningId").val(data.id);
            $("#naziv").val(data.naziv);
            $("#opis").val(data.opis);
            $("#trajanje").val(data.trajanje);
            $("#datum").val(data.datum);
            $("#cena").val(data.cena);
            $("#objekatId").val(data.objekat);
            $("#trener").val(data.trener);
            $("#kupac").val(data.kupac);
        }
    });
}

function getKupci() {
	$.ajax({
        url: "kupci",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        error: function() {	
			$("#dugmePotvrda").attr('disabled', true);
		},
        success: function(data) {
            let kupci = data;
            let select = $("#kupac");    
        
            for (let k of kupci) {
                select.append("<option value='" + k.id + "' >" + k.ime + " " + k.prezime + "</option>");
            }
			
			$.ajax({
		        url: "treneri",
		        type: "GET",
		        contentType: "application/json",
		        dataType: "json",
		        error: function() {	
					$("#dugmePotvrda").attr('disabled', true);
				},
		        success: function(data) {
		            let treneri = data;
		            let select = $("#trener");    
		        
		            for (let t of treneri) {
		                select.append("<option value='" + t.id + "' >" + t.ime + " " + t.prezime + "</option>");
		            }
					
					if (treneri.length === 0) {
						$("#dugmePotvrda").attr('disabled', true);
					}
					
					preuzmiTrening();
		        }
		    });
        }
    });
}

function sacuvaj() {
	let formData = new FormData($("#trening")[0]);
	
	let urlVars = getUrlVars();
	
	if ($("#slika")[0].files.length === 0) {
		formData.delete("slika");
	}
	
    $.ajax({
        url: "/trening/azuriranje/" + urlVars.treningId,
        type: "PUT",
        data: formData,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        complete : function (data) {
			if (data.status === 200) {    
		        alert("Uspesno sacuvano");
		        window.location.reload();
            } else {
		        alert("Greska");
			}
        }
    });
}
