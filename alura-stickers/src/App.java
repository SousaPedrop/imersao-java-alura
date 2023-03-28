import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        

        // fazer conexão HTTP/HTTPS e buscar top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        URI endereco = URI.create(url);
        // HttpClient client = HttpClient.newHttpClient();
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        /* Links alternativos:
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
            https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json
        */

        // extrair os dados que interessam (titulo, poster, classificação) - parsear
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados da maneira que quisermos
        String urlImagem;
        String titulo;
        var geradora = new GeradoraDeFigurinhas();

        for (Map<String,String> filme : listaDeFilmes) {
            titulo = filme.get("title") + ".png";
            urlImagem = filme.get("image");
            InputStream inputStream = new URL(urlImagem).openStream();

            geradora.criar(inputStream, titulo);

            System.out.println("\u001B[31m\u001b[1mTítulo: \u001b[0m\u001b[3m\u001B[34m" + titulo + "\u001b[0m");
            // System.out.println("\u001b[1mImagem: \u001b[0m\u001b[4m" + urlImagem + "\u001b[0m");
            // System.out.println("\u001B[33m\u001b[1mNota: \u001b[3m" + filme.get("imDbRating") + "\u001b[0m");
            // for (int i = 0; i < Math.floor(Double.parseDouble(filme.get("imDbRating"))); i++) {
            //     System.out.print("⭐");
            // }
            System.out.println("\n===================================");
        }
    }//	\u001B[34m
}
