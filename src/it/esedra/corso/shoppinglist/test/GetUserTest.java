package it.esedra.corso.shoppinglist.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class GetUserTest {

	public static void main(String[] args) {
		execute();
	}

	public static void execute() {
		try {
			// server client
			HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(80)).build();
					
			//creo una request
			HttpRequest request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create("http://localhost:3000/get-user"))
					.timeout(Duration.ofMinutes(2)).GET().build();

			HttpResponse<String> response;

			response = client.send(request, BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());

		


		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}