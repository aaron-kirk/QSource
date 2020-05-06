var http = require('http');
var url = require('url');
var fs = require('fs');
const bodyParser = require('body-parser');

const store = require('./store');

http.createServer(function (req, res) {
	var filename = "./public/" + url.parse(req.url, true).pathname + ".html";
	fs.readFile(filename, function(err, data) {
		if(err) {
			res.writeHead(404, {'Content-Type': 'text/html'});
      		return res.end("404 Not Found");
			// fs.readFile('.404.html', function(err, errorpage) {
			// 	res.writeHead(404, {'Content-Type':'text/html'});
			// 	res.write(errorpage);
			// });
			// return res.end();
		} else {
			res.writeHead(200, {'Content-Type':'text/html'});
			res.write(data);
			return res.end();
		}
	});
}).listen(8080);