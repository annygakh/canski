var scraping = require('./scraping');

var hours = 4, the_interval = hours * 60 * 1000;
setInterval(function() {
	scraping.start();

}, the_interval);
