package at.fhkaernten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Ex0903 {

	// Da seit einigen Jahren REST vermehrt eingesetzt wird da dieses einige
	// Vorteile gegenüber RPC hat ist eine JSON/RPC Anwendung kaum noch zu
	// finden(public im internet). Es gibt jedoch noch Anwendungen z.B.
	// XBMC(MediaCenter) welche mittels RPC angesteuert werden können
	// http://wiki.xbmc.org/index.php?title=JSON-RPC_API
	// Dazu wird ein HTTP POST mit dem JSON/RPC Objekt an
	// http://<your-ip>:<your-port>/jsonrpc gesendet z.B:
	// {"jsonrpc": "2.0","method": "Player.GetItem", "params": { "properties":
	// ["title", "album","artist", "season", "episode", "duration", "showtitle",
	// "tvshowid","thumbnail", "file", "fanart", "streamdetails"], "playerid": 1
	// }, "id":"VideoGetItem"}
	// oder mittels HTTP GET Methode an
	// http://<your-ip>:<your-port>/jsonrpc?request=<url-encoded-request>.
	// Weitere Anwendungen sind Bitcoins und Lotus Office, es sind fast nur noch
	// Anwendungen welche in einem separaten Programm angesprochen werden im
	// Umlauf.
	// Für Webanwendungen wird fast ausschließlich nur noch JS + AJAX mit JSON
	// oder XML am Client verwendet.
	// Es werden so gut wie alle Anwendugen von RPC auf REST umgestellt.
	// Bugzilla z.B. hat noch das RPC-Service aktiviert
	// bei Aufruf von
	// https://bugzilla.mozilla.org/jsonrpc.cgi?method=Bugzilla.time bekommt man
	// ein JSON Objekt zurück
	// {"error":null,"id":"https://bugzilla.mozilla.org/","result":{"db_time":"2014-02-13T17:00:44Z","tz_short_name":"UTC","web_time":"2014-02-13T17:00:44Z","tz_name":"UTC","tz_offset":"+0000","web_time_utc":"2014-02-13T17:00:44Z"}}
	// eine Dokumentation für Benutzer des Bugzilla Dienstes über JSON/RPC
	// findet sich unter
	// http://www.bugzilla.org/docs/tip/en/html/api/Bugzilla/WebService/Server/JSONRPC.html

	// Da eine öffentliche JSON/RPC Anwendung mittels Browser nicht zu finden
	// waren habe ich das XBMC MediaCenter installiert und dieses verwendet.
	//
	//
	// Request URL:http://192.168.1.3:8080/jsonrpc?Playlist.GetItems
	// Request Method:POST
	// Status Code:200 OK
	// POST /jsonrpc?Playlist.GetItems HTTP/1.1
	// Host: 192.168.1.3:8080
	// Connection: keep-alive
	// Content-Length: 162
	// Accept: application/json, text/javascript, */*; q=0.01
	// Origin: http://192.168.1.3:8080
	// X-Requested-With: XMLHttpRequest
	// User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML,
	// like Gecko) Chrome/31.0.1650.63 Safari/537.36
	// Content-Type: application/json
	// Referer: http://192.168.1.3:8080/
	// Accept-Encoding: gzip,deflate,sdch
	// Accept-Language: en-US,en;q=0.8,de;q=0.6
	// Request Payload:
	// {"jsonrpc":"2.0","method":"Playlist.GetItems","id":1,"params":{"playlistid":1,"properties":["title","season","episode","plot","runtime","showtitle","thumbnail"]}}

	// Response:
	// HTTP/1.1 200 OK
	// Content-Length: 182
	// Content-Type: application/json
	// Date: Thu, 13 Feb 2014 17:42:06 GMT
	//
	// Response:
	// {"id":1,"jsonrpc":"2.0","result":{"items":[{"label":"Dream Theater - On The Backs of Angels.mp4","thumbnail":"","title":"","type":"unknown"}],"limits":{"end":1,"start":0,"total":1}}}

	// Die Request URL ist die Adresse des Dienstes mit Port und Methodennamen.
	// Die Methode ist ein HTTP POST mit welcher die JSON Daten im Body
	// transportiert werden.
	// Der StatusCode zeigt an, dass der Aufruf und Antword erfolgreich waren.
	// Die Content-Length zeigt an wie groß die Anfrage ist,
	// der Content-Type, von welchem Typ.
	// Das Feld Request Payload zeigt das Objekt welches im JSON Format an den
	// Server gesendet wurde.

	// Die Antwort ist ebenfalls im JSON Format unter Response zu sehen

	public static void main(String[] args) throws IOException {
		System.out
				.println("XBMC JSON/RPC CLIENT \n\nPlease activate your HTTP access in XBMC \nSystem->Settings->Services->Remote control");
		Scanner sc = new Scanner(System.in);
		String ok = "n";
		while (!ok.equals("y")) {
			System.out.println("Activated HTTP Remote control ? [y/n]");
			ok = sc.nextLine();
		}
		System.out
				.println("Please enter IP address and port you want to connect (10.0.0.1:8080)");
		String targetURL = sc.nextLine();
		int action = 0;
		while (action == 0) {
			System.out.println("What activity do you want to perform?");
			System.out
					.println("[1] Retrieve Playlists(retrieve playlist id) \n[2] List items in Playlist(id required)");
			action = sc.nextInt();
		}
		String body = "";
		if (action == 1) {
			body = "{\"jsonrpc\": \"2.0\", \"method\": \"Playlist.GetPlaylists\", \"id\": 1}";
		} else if (action == 2) {
			System.out.println("Which playlist should be listed?");
			int playlist = sc.nextInt();
			body = "{\"jsonrpc\": \"2.0\", \"method\": \"Playlist.GetItems\", \"id\": 1, \"params\":{\"playlistid\":"
					+ playlist + "}}";
		}

		URL url = new URL("http://" + targetURL + "/jsonrpc");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length",
				String.valueOf(body.length()));

		OutputStreamWriter writer = new OutputStreamWriter(
				connection.getOutputStream());
		writer.write(body);
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		for (String line; (line = reader.readLine()) != null;) {
			System.out.println(line);
		}
		writer.close();
		reader.close();
		sc.close();
	}

	// Output
	
	
	// XBMC JSON/RPC CLIENT
	//
	// Please activate your HTTP access in XBMC
	// System->Settings->Services->Remote control
	// Activated HTTP Remote control ? [y/n]
	// y
	// Please enter IP address and port you want to connect (10.0.0.1:8080)
	// 192.168.1.3:8080
	// What activity do you want to perform?
	// [1] Retrieve Playlists(retrieve playlist id)
	// [2] List items in Playlist(id required)
	// 1
	// {"id":1,"jsonrpc":"2.0","result":[{"playlistid":0,"type":"audio"},{"playlistid":1,"type":"video"},{"playlistid":2,"type":"picture"}]}

	// XBMC JSON/RPC CLIENT
	//
	// Please activate your HTTP access in XBMC
	// System->Settings->Services->Remote control
	// Activated HTTP Remote control ? [y/n]
	// y
	// Please enter IP address and port you want to connect (10.0.0.1:8080)
	// 192.168.1.3:8080
	// What activity do you want to perform?
	// [1] Retrieve Playlists(retrieve playlist id)
	// [2] List items in Playlist(id required)
	// 2
	// Which playlist should be listed?
	// 1
	// {"id":1,"jsonrpc":"2.0","result":{"items":[{"label":"Dream Theater - On The Backs of Angels.mp4","type":"unknown"}],"limits":{"end":1,"start":0,"total":1}}}
}