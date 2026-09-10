import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TesteHeadless {
    public static void main(String[] args) {
        int width = 640;
        int height = 480;
        BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] pixelBuffer = new int[width * height];

        // 1. Instancia Triângulo 3D
        Ponto3D p1 = new Ponto3D(-50, -50, 0);
        Ponto3D p2 = new Ponto3D(50, -50, 0);
        Ponto3D p3 = new Ponto3D(0, 50, 0);
        Triangulo3D tri = new Triangulo3D(p1, p2, p3);

        // 2. Transforma (Escala -> Rotação -> Translação)
        Mat4x4 escala = Mat4x4.scale(1.5, 1.5, 1.0);
        Mat4x4 rotacao = Mat4x4.rotacaoZ(45);
        Mat4x4 translacao = Mat4x4.translacao(320, 240, 0);

        Mat4x4 matComposta = translacao.multiplicacao_mat4x4(rotacao).multiplicacao_mat4x4(escala);
        Triangulo3D triTrans = tri.transform(matComposta);

        // 3. Desenha no buffer
        triTrans.draw(pixelBuffer, width, height, 0xFF00FF00); // Verde

        imageBuffer.setRGB(0, 0, width, height, pixelBuffer, 0, width);

        // 4. Salva o resultado numa imagem PNG para visualizar no Codespaces
        try {
            File outputFile = new File("resultado.png");
            ImageIO.write(imageBuffer, "png", outputFile);
            System.out.println("Imagem gerada com sucesso! Abra o arquivo 'resultado.png'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}