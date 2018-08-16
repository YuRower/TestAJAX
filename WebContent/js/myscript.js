function send(){
	console.log(name.inline)
	console.log('33333')
	//let name = document.getElementById("name")
	$.ajax({
		type: 'POST',
		url: "http://localhost:8888/laba5/send.php",
		data: 'name='+name.value
		});
}
