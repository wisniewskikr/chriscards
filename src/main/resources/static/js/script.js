$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

function redirect(target) {
	location.href=target;
}

function submitAction(formId, action) {
	$('#' + formId).attr('action', action).submit();
}