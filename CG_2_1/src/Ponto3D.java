public class Ponto3D {
    public double x, y, z, w;

    public Ponto3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0;
    }

    // Multiplica o Ponto (vetor 4x1) pela Matriz 4x4
    public Ponto3D multiplicacao_mat4x4(Mat4x4 mat) {
        double nx = mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z + mat.m[0][3] * w;
        double ny = mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z + mat.m[1][3] * w;
        double nz = mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z + mat.m[2][3] * w;
        double nw = mat.m[3][0] * x + mat.m[3][1] * y + mat.m[3][2] * z + mat.m[3][3] * w;

        if (nw != 1.0 && nw != 0.0) {
            nx /= nw;
            ny /= nw;
            nz /= nw;
        }

        return new Ponto3D(nx, ny, nz);
    }
}