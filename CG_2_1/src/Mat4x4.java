public class Mat4x4 {
    public double[][] m = new double[4][4];

    public Mat4x4() {
        // Matriz Identidade por padrão
        for (int i = 0; i < 4; i++) {
            m[i][i] = 1.0;
        }
    }

    // Multiplicação de Matriz 4x4 por outra Matriz 4x4
    public Mat4x4 multiplicacao_mat4x4(Mat4x4 m2) {
        Mat4x4 res = new Mat4x4();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                res.m[r][c] = 0;
                for (int i = 0; i < 4; i++) {
                    res.m[r][c] += this.m[r][i] * m2.m[i][c];
                }
            }
        }
        return res;
    }

    // Translação (tx, ty, tz)
    public static Mat4x4 translacao(double tx, double ty, double tz) {
        Mat4x4 mat = new Mat4x4();
        mat.m[0][3] = tx;
        mat.m[1][3] = ty;
        mat.m[2][3] = tz;
        return mat;
    }

    // Escala (sx, sy, sz)
    public static Mat4x4 scale(double sx, double sy, double sz) {
        Mat4x4 mat = new Mat4x4();
        mat.m[0][0] = sx;
        mat.m[1][1] = sy;
        mat.m[2][2] = sz;
        return mat;
    }

    // Rotação no eixo X
    public static Mat4x4 rotacaoX(double anguloGraus) {
        Mat4x4 mat = new Mat4x4();
        double rad = Math.toRadians(anguloGraus);
        double c = Math.cos(rad);
        double s = Math.sin(rad);

        mat.m[1][1] = c;  mat.m[1][2] = -s;
        mat.m[2][1] = s;  mat.m[2][2] = c;
        return mat;
    }

    // Rotação no eixo Y
    public static Mat4x4 rotacaoY(double anguloGraus) {
        Mat4x4 mat = new Mat4x4();
        double rad = Math.toRadians(anguloGraus);
        double c = Math.cos(rad);
        double s = Math.sin(rad);

        mat.m[0][0] = c;   mat.m[0][2] = s;
        mat.m[2][0] = -s;  mat.m[2][2] = c;
        return mat;
    }

    // Rotação no eixo Z
    public static Mat4x4 rotacaoZ(double anguloGraus) {
        Mat4x4 mat = new Mat4x4();
        double rad = Math.toRadians(anguloGraus);
        double c = Math.cos(rad);
        double s = Math.sin(rad);

        mat.m[0][0] = c;  mat.m[0][1] = -s;
        mat.m[1][0] = s;  mat.m[1][1] = c;
        return mat;
    }
}