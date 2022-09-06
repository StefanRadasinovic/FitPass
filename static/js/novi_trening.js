$(document).ready( function() {
	
	let urlVars = getUrlVars();
	if (!urlVars.objekatId) {
		alert("Id objekta nije prolsedjen");
		window.location.reload();
	}
	
	$("#objekatId").val(urlVars.objekatId);
	
	getKupci();
});

function getUrlVars() {
    let vars = {};
    let parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function getKupci() {
	$.ajax({
        url: "kupci",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        complete: function(data) {
            let kupci = data.responseJSON;
            let select = $("#kupac");    
        
            for (let k of kupci) {
                select.append("<option value='" + k.id + "' >" + k.ime + " " + k.prezime + "</option>");
            }
			
			if (kupci.length === 0) {
				$("#dugmePotvrda").attr('disabled', true);
			}
			
			$.ajax({
		        url: "treneri",
		        type: "GET",
		        contentType: "application/json",
		        dataType: "json",
		        complete: function(data) {
		            let treneri = data.responseJSON;
		            let selectTrener = $("#trener");    
		        
		            for (let t of treneri) {
		                selectTrener.append("<option value='" + t.id + "' >" + t.ime + " " + t.prezime + "</option>");
		            }
					
					if (treneri.length === 0) {
						$("#dugmePotvrda").attr('disabled', true);
					}
		        }
		    });
        }
    });
}

function sacuvaj() {
	if ($("#slika")[0].files.length === 0) {
		alert("Izaberite sliku");
		return;
	}
	
    let formData = new FormData($("#trening")[0]);
        
    $.ajax({
        url: "/treninzi/novi",
        type: "POST",
        data: formData,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        error: function () {
			alert("Neispravni podaci")
		},
        success : function (data) {    
            alert("Uspesno sacuvano");
			window.location.reload();
        }
    });
}