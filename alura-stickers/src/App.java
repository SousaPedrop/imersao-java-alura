import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão HTTP/HTTPS e buscar top 250 filmes
        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-22&end_date=2023-03-24";
        String url = "http://localhost:8080/linguagens";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        var http = new clienteHttp();
        String json = http.buscaDados(url);
        /*
         * Links alternativos:
         * https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/
         * TopMovies.json
         * https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs
         * .json
         * https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/
         * MostPopularMovies.json
         * https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/
         * MostPopularTVs.json
         */

        // exibir e manipular os dados da maneira que quisermos
        // ExtratorDeConteudo extratorConteudoDaNasa = new ExtratorConteudoDaNasa();
        // List<Conteudo> conteudos = extratorConteudoDaNasa.extraiConteudos(json);

        // ExtratorDeConteudo extratorConteudoDoImdb = new ExtratorConteudoDoImdb();
        // List<Conteudo> conteudos = extratorConteudoDoImdb.extraiConteudos(json);
        
        ExtratorDeConteudo extratorConteudoLinguagens = new ExtratorConteudoDeLinguagens();
        List<Conteudo> conteudos = extratorConteudoLinguagens.extraiConteudos(json);

        String nomeDoArquivo;
        var geradora = new GeradoraDeFigurinhas();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            nomeDoArquivo = "figurinhas/" + conteudo.getTitulo().replace(":", "-") + ".png";

            geradora.criar(inputStream, nomeDoArquivo, conteudo.getRanking());
            System.out.println(
                    "\u001B[31m\u001b[1mTítulo: \u001b[0m\u001b[3m\u001B[34m" + conteudo.getTitulo() + "\u001b[0m");
            System.out.println();
        }
        // for (Map<String, String> filme : listaDeFilmes) {
        // titulo = filme.get("title").replace(":", "-") + ".png";
        // urlImagem = filme.get("image");
        // InputStream inputStream = new URL(urlImagem).openStream();

        // nomeDoArquivo = "figurinhas/" + titulo + ".png";
        // geradora.criar(inputStream, nomeDoArquivo);

        // System.out.println("\u001B[31m\u001b[1mTítulo: \u001b[0m\u001b[3m\u001B[34m"
        // + titulo + "\u001b[0m");
        // System.out.println("\u001b[1mImagem: \u001b[0m\u001b[4m" + urlImagem +
        // "\u001b[0m");
        // System.out.println("\u001B[33m\u001b[1mNota: \u001b[3m" +
        // filme.get("imDbRating") + "\u001b[0m");
        // for (int i = 0; i < Math.floor(Double.parseDouble(filme.get("imDbRating")));
        // i++) {
        // System.out.print("⭐");
        // }
        // System.out.println("\n===================================");
        // }
    }// \u001B[34m
}
