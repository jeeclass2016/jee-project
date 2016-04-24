/**
 * 
 */
$(document).ready(function() {
	console.log("ready!");
	var cb = $('.ft-left');
	cb.removeAttr('checked');
	$('#condition').hide();
	$('#addButton').unbind('click');
	
});
//--- Message to display ---//
msg = "";
arrs = ["valueOfNumberOne", "valueOfNumberTwo", "valueOfNumberThree", "valueOfNumberFour", "valueOfNumberFive", "valueOfNumberSix", "valueOfNumberSeven", "valueOfNumberEigh", "valueOfNumberNine", "valueOfNumberTen", "valueOfNumberEleven"];
function clearCache() {
	for ( var i = 0; i < arrs.length; i++) {
		$('#rule-insert\\:' + arrs[i]).val('');
	}
}

function showInsertPopUp(){
	clearCache();
	PF('dlg').show();
}

function validateGroup() {
	var condition	= $('#addCondition\\:columnGroup').val();
	var arrs 		= condition.split(",");
	for ( var i = 0; i < arrs.length; i++) {
		if (!isNumber(arrs[i])) {
			ShowPopupError();
			return false;
		}
	}
	
	return true;
}
function ShowPopupError() {
    $("#masked_error").show();
    $("#checkValidate").show();
    $(".pop-up-info-warehouse").css({'display':'block'});
}
function HidePopupError() {
    $("#masked_error").hide();
    $("#checkValidate").hide();
    
}
function valiadteRuleInsertion(){
	var form = "rule-insert";
	var isValid = true;
	for ( var i = 0; i < arrs.length; i++) {
		isValid = validateContent(form, i);
		if (!isValid) {
			break;
		}
	}
	if (!isValid) {
		setErrorMsg(msg);
		ShowPopupError();
		return false;
	}
	if (form == "rule-insert") {
		var isChecked = $('#chkPopUpInsert:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlg').hide();
		} 
	}
	if (form == "rule-edit") {
		var isChecked = $('#chkPopUpEdit:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlgPopUp2').hide();
		} 
	}
	
	return isValid;
}
function valiadteRule(form){
	var isValid = true;
	for ( var i = 0; i < arrs.length; i++) {
		isValid = validateContent(form, i);
		if (!isValid) {
			break;
		}
	}
	if (!isValid) {
		setErrorMsg(msg);
		ShowPopupError();
		return false;
	}
	if (form == "rule-insert") {
		var isChecked = $('#chkPopUpInsert:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlg').hide();
		} 
	}
	if (form == "rule-edit") {
		var isChecked = $('#chkPopUpEdit:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlgPopUp2').hide();
		} 
	}
	
	return isValid;
}
function valiadteRule(form, i){
	var isValid = true;
	if (form == "tbview") {
		form = "tbview\\:tb-list\\:" + i;
	}
	for ( var index = 0; index < arrs.length; index++) {
		isValid = validateContent(form, index);
		if (!isValid) break;
	}
	
	if (!isValid) {
		setErrorMsg(msg);
		ShowPopupError();
		return false;
	}
	if (form == "rule-insert") {
		var isChecked = $('#chkPopUpInsert:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlg').hide();
		} 
	}
	if (form == "rule-edit") {
		var isChecked = $('#chkPopUpEdit:checked').val() ? true : false;
		if (!isChecked) {
			PF('dlgPopUp2').hide();
		} 
	}
	
	return isValid;
}
function validateContent(form, i) {
	var id = arrs[i];
	var currentValue 	= $('#' + form + '\\:' + id).val();
	var ruleName 		= $('#' + form + '\\:rules-view').val();
	if (currentValue == '') {
		currentValue = "-1";
	}
	if (isNumber(currentValue)) {
		currentValue = parseInt(currentValue);
	}
	var isValid 		= false;
	if (ruleName == "Number begin") {
		isValid 		= validateNumber(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Number end") {
		isValid 		= validateNumber(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Step") {
		isValid 		= validateNumber(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Extral value") {
		isValid 		= validateNumber(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Ref") {
		isValid 		= validateRefCol(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Reject obt list") {
		isValid 		= validateRejectedNumber(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Greater") {
		isValid 		= validateGreaterSmaller(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	if (ruleName == "Smaller") {
		isValid 		= validateGreaterSmaller(currentValue);
		if (!isValid) {
			buildErrorMessage(i);
			return false;
		}
	}
	return true;
}
function buildErrorMessage(i) {
	msg = "Giá trị cột " + (i + 1) + " không hợp lệ";
}

function validateNumber(n) {
	if (!isNumber(n)) {
		return false;
	}
	return n >= -1 && n < 10; 
}

function validateRefCol(n) {
	if (!isNumber(n)) {
		return false;
	}
	return n >= 1 && n < 12; 
}
function validateRejectedNumber(n) {
	if (n.match("\s*")){return true;}
	if (isNumber(n)){return true;}
	
	if (n.match("\!") && n.match("\;")) {
		var num1 = n.substring(n.indexOf('!') - 1,n.indexOf('!'));
		var num2 = n.substring(n.indexOf(';') - 1,n.indexOf(';'));
		
		return isNumber(num1) && num1 <= 9 && validateRefCol(num2);
	}
	if (n.match("\!")) {
		var num = n.substring(n.indexOf('!') - 1,n.indexOf('!'));
		return isNumber(num) && num <= 9;
	}
	if (n.match("\;")) {
		var num = n.substring(n.indexOf(';') - 1,n.indexOf(';'));
		return validateRefCol(num);
	}
	
	return false;
}
function isNumber(n) {
	return (Object.prototype.toString.call(n) === '[object Number]' || Object.prototype.toString
			.call(n) === '[object String]')
			&& !isNaN(parseFloat(n))
			&& isFinite(n.toString().replace(/^-/, ''));
}
function validateGreaterSmaller(n) {
	if (n.trim().length == 0){return true;}
	if (isNumber(n)){return false;}
	if (n.match("\!") && n.match("\=")) {
		var num1 = n.substring(n.indexOf('!') - 1,n.indexOf('!'));
		var num2 = n.substring(n.indexOf('=') - 1,n.indexOf('='));
		
		return isNumber(num1) && num1 <= 9 && validateRefCol(num2);
	}
	if (n.match("\!")) {
		var num = n.substring(n.indexOf('!') - 1, n.indexOf('!'));
		return isNumber(num) && num <= 9;
	}
	if (n.match("\=")) {
		var num = n.substring(n.indexOf('=') - 1, n.indexOf('='));
		return validateRefCol(num);
	}
	
	return false;
}
function handleClick(cb) {
	if (cb.checked == true) {
		$('#condition').show();
	} else {
		$('#condition').hide();
	}
}
function updateClickAction(){
	btnAdd.enable();
}
function typeValidator() {
	var type 		= $('#ruleSearch-input\\:typeSearchSelect').val();
	if (type == "-1") {
		setErrorMsg('Dữ liệu nhập vào không đúng');
		ShowPopupError();
		return false;
	}
	PF('statusDialog').show();
	return true;
}
function addActionValidator() {
	var type 		= $('#ruleSearch-input\\:typeSearchSelect').val();
	if (type == "-1") {
		setErrorMsg('Bạn phải tìm kiếm trước');
		ShowPopupError();
		return false;
	}
	ShowPopUp1();
	return true;
}
function setErrorMsg(msg){
	$('#errormsg').text(msg);
}

function changeExtraRuleType(){
	var type 		= $('#addCondition\\:status').val();
	if (type == 'NI') {
		 $("#addCondition\\:valueGroup").attr("disabled","disabled");
		 $("#addCondition\\:valueGroup").val("");
	} else {
		 $("#addCondition\\:valueGroup").removeAttr("disabled");
	}
}
function validateExtraRule(){
	
}