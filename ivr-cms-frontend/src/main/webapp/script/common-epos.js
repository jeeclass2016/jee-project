/**
 * 
 */
/*-- start pop-up */
function ShowPopUp2() {
	$("#masked_2").show();
	$("#popUp2").show();
}
function ShowPopUp3() {
	$("#masked_3").show();
	$("#popUp3").show();
}

function ShowPopUp8() {
	$("#masked_8").show();
	$("#popUp8").show();
}

function HidePopup1() {
	$("#masked_1").hide();
	$("#popUp1").hide();
}

function HidePopup2() {
	$("#masked_2").hide();
	$("#popUp2").hide();
}

function HidePopup3() {
	$("#masked_3").hide();
	$("#popUp3").hide();
}

function HidePopup8() {
	$("#masked_8").hide();
	$("#popUp8").hide();
}

/* căn pop-up giữa màn hình */
function center() {// hàm xử lý
	$('.middle').css({
		left : ($(window).width() - $('div').width()) / 2,
		top : ($(window).height() - $('div').height()) / 2
	});
	$('.middle2').css({
		left : ($(window).width() - $('#popUp2').width()) / 2,
		top : ($(window).height() - $('div').height()) / 2
	});
}

/** -- --* */
