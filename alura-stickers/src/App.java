import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {
        

        // fazer conexão HTTP/HTTPS e buscar top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        
        URI endereco = URI.create(url);
        // HttpClient client = HttpClient.newHttpClient();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        System.out.println(body);

        /* Links alternativos:
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json
        */

        // extrair os dados que interessam (titulo, poster, classificação) - parsear

        // exibir e manipular os dados da maneira que quisermos
    }
}
