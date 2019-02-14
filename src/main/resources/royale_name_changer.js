function initialize(contestants){
//Contestant list. [Name, Nickname, Avatar URL, Gender(1=M, 0=F)]
	%s
	return contestants;
}

//Stolen shuffle function
function shuffle(array){
	var currentIndex = array.length, temporaryValue, randomIndex;
	
	 // While there remain elements to shuffle...
	while (0 !== currentIndex) {

		// Pick a remaining element...
		randomIndex = Math.floor(Math.random() * currentIndex);
		currentIndex -= 1;

		// And swap it with the current element.
		temporaryValue = array[currentIndex];
		array[currentIndex] = array[randomIndex];
		array[randomIndex] = temporaryValue;
  }

  return array;
}

//Change the elements on the page
function post(array){
	for (var i = 0; i < array.length; i++) {
		var num = i + 1
		
		var numString = "00"
		if (num < 10){
			numString = "0" + num;
		} else {
			numString = num;
		}
		
		document.getElementById("cusTribute" + numString).value = array[i][0];
		document.getElementsByName("cusTribute" + numString + "nickname")[0].value = array[i][1];
		document.getElementsByName("cusTribute" + numString + "img")[0].value = array[i][2];
		document.getElementsByName("cusTribute" + numString + "imgBW")[0].value = array[i][2];
		document.getElementsByName("cusTribute" + numString + "gender")[0].value = array[i][3];
		console.log("All good!");
	}
}

var contestants = [];
contestants = initialize(contestants);
contestants = shuffle(contestants);
post(contestants);
