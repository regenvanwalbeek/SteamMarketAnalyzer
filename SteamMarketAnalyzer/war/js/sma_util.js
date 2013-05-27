function highlight_link(id){
	$("#" + id).addClass("active");
}

function monitor(){
	$.ajax({
		type:"POST",
		url:"monitor",
	}).done(function(msg){
		$("#monitor_content").html("success <br />" + msg);
		// Repeat after 1 second.
		setTimeout(monitor, 1000);
	})
}