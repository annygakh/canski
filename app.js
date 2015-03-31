var main = function(){
	var ERROR = 'ERROR';

	var can_ski_ref = new Firebase("https://canski.firebaseio.com/");
	var whistler_ref = can_ski_ref.child("whistler");
	var blackcomb_ref = can_ski_ref.child("blackcomb");
	var seymour_ref = can_ski_ref.child("seymour");
	var cypress_ref = can_ski_ref.child("cypress");
	var grouse_ref = can_ski_ref.child("grouse");

	var $whistler_status = $('#whistler_status');
	var $blackcomb_status = $('#blackcomb_status');
	var $seymour_status = $('#seymour_status');
	var $cypress_status = $('#cypress_status');
	var $grouse_status = $('#grouse_status');



	// Firebase async listeners for any changes
	whistler_ref.on("value", function(snapshot) {
		var value = snapshot.val();
		$whistler_status.text(value);
	}, function (error_obj) {
		$whistler_status.text(ERROR);
	  // console.log("The read failed: " + error_obj.code);
	});

	blackcomb_ref.on("value", function(snapshot) {
		var value = snapshot.val();
		$blackcomb_status.text(value);
	}, function (error_obj) {
		$blackcomb_status.text(ERROR);
	  // console.log("The read failed: " + error_obj.code);
	});

	seymour_ref.on("value", function(snapshot) {
		var value = snapshot.val();
		$seymour_status.text(value);
	}, function (error_obj) {
		$seymour_status.text(ERROR);
	  // console.log("The read failed: " + error_obj.code);
	});

	cypress_ref.on("value", function(snapshot) {
		var value = snapshot.val();
		$cypress_status.text(value);
	}, function (error_obj) {
		$cypress_status.text(ERROR);
	  // console.log("The read failed: " + error_obj.code);
	});

	grouse_ref.on("value", function(snapshot) {
		var value = snapshot.val();
		$grouse_status.text(value);
	}, function (error_obj) {
		$grouse_status.text(ERROR);
	  // console.log("The read failed: " + error_obj.code);
	});
	// async listeners

}


$(document).ready(main);