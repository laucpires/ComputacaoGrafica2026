public class Triangulo3D {
    public Ponto3D p1, p2, p3;

    public Triangulo3D(Ponto3D p1, Ponto3D p2, Ponto3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Método transform que aplica a matriz aos 3 pontos 3D
    public Triangulo3D transform(Mat4x4 mat) {
        Ponto3D tp1 = p1.multiplicacao_mat4x4(mat);
        Ponto3D tp2 = p2.multiplicacao_mat4x4(mat);
        Ponto3D tp3 = p3.multiplicacao_mat4x4(mat);
        return new Triangulo3D(tp1, tp2, tp3);
    }

    // Rasteriza o triângulo no buffer usando Bresenham com Clipping de tela
    public void draw(int[] buffer, int width, int height, int color) {
        drawLineBresenham((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y, color, buffer, width, height);
        drawLineBresenham((int)p2.x, (int)p2.y, (int)p3.x, (int)p3.y, color, buffer, width, height);
        drawLineBresenham((int)p3.x, (int)p3.y, (int)p1.x, (int)p1.y, color, buffer, width, height);
    }

    private void drawLineBresenham(int x1, int y1, int x2, int y2, int color, int[] buffer, int width, int height) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int x = x1;
        int y = y1;

        while (true) {
            // Clipping para evitar estouro de buffer (ArrayIndexOutOfBounds)
            if (x >= 0 && x < width && y >= 0 && y < height) {
                buffer[y * width + x] = color;
            }
            if (x == x2 && y == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) { err -= dy; x += sx; }
            if (e2 < dx) { err += dx; y += sy; }
        }
    }
}