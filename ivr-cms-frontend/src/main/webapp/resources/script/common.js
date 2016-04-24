var ctrlDown = false;
var ctrlKey = 17, f5Key = 116, rKey = 82;

$(document).keydown(function(e) {
	if (e.keyCode == f5Key) {
		window.location.replace(window.location);
		// It will avoid if-modified-since requests.
		e.preventDefault();
	}

	if (ctrlDown && (e.keyCode == rKey)) {
		// Ctrl + R pressed. Do whatever you want
		// or copy the same code here that you did above
		e.preventDefault();
	}

}).keyup(function(e) {
	if (e.keyCode == ctrlKey)
		ctrlDown = false;
});

// this is another way
function itemHasFocus(id) {
	var output = false;
	console.log($(this).is(":focus")); // check item has focus
	if ($(this).is(":focus")) {
		output = true;
		return false; // return false skips out of the loop
	}
	return output;
}

function bindF9(e, id) {
	if (e.keyCode == 120) {
		$(id).click();
	}
}
function bindF9AndNotEnter(e, id) {
	if (e.keyCode == 120) {
		$(id).click();
	}else if(e.keyCode == 13){
		e.preventDefault();
		return false;
	}
}

function bindEnter(e) {
	if (e.keyCode == 13) {
		e.preventDefault();
		return false;
	}
}

function bindEnterId(e, id) {
	if (e.keyCode == 13) {
		$(id).click();
	}
}
