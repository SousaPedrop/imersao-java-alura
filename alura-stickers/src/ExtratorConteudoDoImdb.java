import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoDoImdb implements ExtratorDeConteudo{
    public List<Conteudo> extraiConteudos(String json) {
        // extrair os dados que interessam (titulo, poster, classificação) - parsear
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        //popular a lista de conteudos
        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image");

            Conteudo conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
