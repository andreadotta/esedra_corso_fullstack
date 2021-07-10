package it.esedra.corso.shoppinglist.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import it.esedra.corso.shoppinglist.helper.GetFileResource;

public class ServerTest {

	public static void main(String args[]) {
		try {
			//server client
			HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(80)).build();
					
			//creao una request
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/add-shopping-list"))
					.timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json")
					.POST(BodyPublishers.ofFile(GetFileResource.get("item.json", "test").toPath())).build();

			HttpResponse<String> response;

			response = client.send(request, BodyHandlers.ofString());

			System.out.println(response.statusCode());
			System.out.println(response.body());

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
