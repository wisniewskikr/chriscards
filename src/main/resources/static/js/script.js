$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

function redirect(target) {
	location.href=target;
}

function submitAction(formId, action) {
	$('#' + formId).attr('action', action).submit();
}

function showLearnigRepeat(learningMode) {

	if ("MANUAL" == learningMode) {
		$(".manualCheckboxes").show();
		$(".authomaticCheckboxes").hide();
	}	
	
	if ("AUTHOMATIC" == learningMode) {
		$(".manualCheckboxes").hide();
		$(".authomaticCheckboxes").show();
	}
	
}

function translateViaGoogle() {
	
	var json = {
			polishWord: $('#polishWord').val(),
			polishSentence: $('#polishSentence').val()
	};
			    
   $.ajax({
        type: "POST",
        contentType : 'application/json',
        url: "/cards/translate",
        data: JSON.stringify(json),
        success: function(response){
        	
        	if(response.status == "SUCCESS") {
        		$("#englishWord").val(response.englishWord);
		    	$("#englishSentence").val(response.englishSentence);
        	}
        	
        	if(response.status == "FAIL") {
        		alert("Fail");
        	}
	    	
        }
   });
	
}