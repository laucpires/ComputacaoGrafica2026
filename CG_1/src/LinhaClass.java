public class LineRasterizer {

    /**
     * Desenha uma linha entre (x1, y1) e (x2, y2) no buffer fornecido utilizando o algoritmo de Bresenham.
     * 
     * @param x1 Coordenada X inicial
     * @param y1 Coordenada Y inicial
     * @param x2 Coordenada X final
     * @param y2 Coordenada Y final
     * @param color Cor do pixel (formato ARGB/RGB dependendo da estrutura do seu buffer)
     * @param buffer Array unidimensional representando o buffer de pixels
     * @param width Largura da tela/buffer em pixels
     * @param height Altura da tela/buffer em pixels
     */
    public static void drawLine(int x1, int y1, int x2, int y2, int color, int[] buffer, int width, int height) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        // Define a direção do passo em cada eixo (+1 ou -1)
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;

        int x = x1;
        int y = y1;

        while (true) {
            // Verifica limites da tela (Clipping básico de segurança)
            if (x >= 0 && x < width && y >= 0 && y < height) {
                buffer[y * width + x] = color;
            }

            // Se atingiu o ponto final, encerra o desenho
            if (x == x2 && y == y2) {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }

            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
}
