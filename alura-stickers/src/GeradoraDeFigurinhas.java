import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    public void criar(InputStream inputStream, String nomeDoArquivo) throws Exception
    {
        // Leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("C:/Users/p/Desktop/imersao-java-alura/alura-stickers/bin/images/filme.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparencia e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a img original p/ nova imagem em memoria
        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);
        
        // escrever na nova imagem
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;

        graphics.drawString(texto, posicaoTextoX, novaAltura - 100);

        //escrever a nova imagem num arquivo
        ImageIO.write(novaImagem, "png", new File(nomeDoArquivo));
    }
}
