var request = require('request'),
	cheerio = require('cheerio'),
	schedule = require('node-schedule');

var url_wb 	= "http://www.whistlerblackcomb.com/local/xml/tom.xml";
var url_seymour = "http://www.mountseymour.com/";
var url_cypress = "http://cypressmountain.com/";
var url_grouse = "https://www.grousemountain.com/";

var Firebase = require('firebase');
var can_ski_ref = new Firebase("https://canski.firebaseio.com/");
var whistler_ref = can_ski_ref.child("whistler");
var blackcomb_ref = can_ski_ref.child("blackcomb");
var seymour_ref = can_ski_ref.child("seymour");
var cypress_ref = can_ski_ref.child("cypress");
var grouse_ref = can_ski_ref.child("grouse");


var num_finished=0;

var whistler_request = 

request(url_wb, function(err, resp, body){

	if (!err && resp.statusCode == 200){
		var parseString = require("xml2js").parseString;

		parseString(body, function(error, result){
			string_json_wb = JSON.stringify(result);
		});
		json_wb = JSON.parse(string_json_wb);

		var runs_open_w = json_wb['Conditions']['Grooming'][0]['RunsOpenWhistler'][0];
		var runs_open_b = json_wb['Conditions']['Grooming'][0]['RunsOpenBlackcomb'][0];
		console.log("runs open whistler: " + runs_open_w);			
		console.log("runs open blackcomb: " + runs_open_b);			

		if (runs_open_w > 0) 
			whistler_ref.set(runs_open_w);
		else
			whistler_ref.set(-1);
		
		if (runs_open_b > 0) 
			blackcomb_ref.set(runs_open_b);
		else
			blackcomb_ref.set(-1);

	}
	num_finished++;
	try_to_exit();
});

var seymour_request = 
request(url_seymour, function(err, resp, body){
	if (!err && resp.statusCode == 200){
		var obj = cheerio.load(body);
		var runs_open_s = obj('.currentConditionsRuns').text();
		console.log("runs open at seymour: " + runs_open_s);
		// semour_ref.set("")

		if (runs_open_s >= 0)
			seymour_ref.set(runs_open_s);
		else
			seymour_ref.set(-1);

	}

	num_finished++;
	try_to_exit();
});

var grouse_request = 
request(url_grouse, function(err, resp, body){
	if (!err && resp.statusCode == 200){
		var obj = cheerio.load(body);
		var warn = obj('span', '#site_wide_alert').text();

		if ((warn.indexOf("stand") > -1) || (warn.indexOf("STAND"))){
			grouse_ref.set("0");
			console.log("grouse is on standby");
		} else {
			grouse_ref.set(">1");
			console.log("grouse is open, latest warnings: " + warn);
		}
	}
	num_finished++;
	try_to_exit();
});

var cypress_request = 
request(url_cypress, function(err, resp, body){
	if (!err && resp.statusCode == 200){
		var obj = cheerio.load(body);
		var warn = obj('#block-views-Conditions-block_1 > div > div > div > div.view-content > div > div.views-field-field-alpine-conditions-desc-value > div > p:nth-child(1) > strong').text();
		
		if ((warn.indexOf("stand") > -1) || (warn.indexOf("STAND"))){
			cypress_ref.set("0")
			console.log("cypress is on standby");
		} else {
			cypress_ref.set(">1");
			console.log("cypress is open, latest warnings: " + warn);
		}


	}
	
	num_finished++;
	try_to_exit();
});

function try_to_exit(){
	if (num_finished === 4)
		process.exit(0);

}


