import java.util.Scanner;
import java.lang.Math;

class Matrix {
    int n, m;
    double[][] array;

    Matrix(int n, int m) {
        array = new double[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                array[i][j] = 0;
            }
        }
        this.n = n;
        this.m = m;
    }

    public void SetElement(int i, int j, double value) {
        array[i][j] = value;
    }

    public double GetElement(int i, int j) {
        return array[i][j];
    }

    public Matrix Addition(Matrix other) {
        if (this.n != other.n || this.m != other.m) {
            throw new NullPointerException("The operation cannot be performed.");
        }
        Matrix result = new Matrix(this.n, this.m);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(i, j) + other.GetElement(i, j));
            }
        }
        return result;
    }

    public Matrix Multiplication(Matrix other) {
        if (this.m != other.n) {
            throw new NullPointerException("The operation cannot be performed.");
        }

        Matrix result = new Matrix(this.n, other.m);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < other.m; ++j) {
                double sum = 0;
                for (int k = 0; k < this.m; ++k) {
                    sum += this.GetElement(i, k) * other.GetElement(k, j);
                }
                result.SetElement(i, j, sum);
            }
        }
        return result;
    }

    public Matrix MultiplicationByConstant(double c) {
        Matrix result = new Matrix(this.n, this.m);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(i, j) * c);
            }
        }
        return result;
    }

    public Matrix TransposeMain() {
        Matrix result = new Matrix(this.m, this.n);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(j, i));
            }
        }
        return result;
    }

    public Matrix TransposeSide() {
        Matrix result = new Matrix(this.m, this.n);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(this.m - j - 1, this.n - 1 - i));
            }
        }
        return result;
    }

    public Matrix TransposeVertical() {
        Matrix result = new Matrix(this.m, this.n);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(i, this.m - 1 - j));
            }
        }
        return result;
    }

    public Matrix TransposeHorizontal() {
        Matrix result = new Matrix(this.m, this.n);
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                result.SetElement(i, j, this.GetElement(this.n - i - 1, j));
            }
        }
        return result;
    }

    private Matrix CreateMinor(int l, int k) {
        Matrix res = new Matrix(n - 1, m - 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i < l && j < k) {
                    res.SetElement(i, j, this.array[i][j]);
                }
                if (i < l && j > k) {
                    res.SetElement(i, j - 1, this.array[i][j]);
                }
                if (i > l && j < k) {
                    res.SetElement(i - 1, j, this.array[i][j]);
                }
                if (i > l && j > k) {
                    res.SetElement(i - 1, j - 1, this.array[i][j]);
                }
            }
        }
        return res;
    }

    public double Determinant() {
        if (n == 2 && m == 2) return array[0][0] * array[1][1] - array[0][1] * array[1][0];
        double res = 0;
        this.Print();
        for (int i = 0; i < n; ++i) {
            Matrix minor = this.CreateMinor(0, i);
            System.out.println(i);
            minor.Print();
            res += minor.Determinant() * array[0][i] * Math.pow(-1, i);
        }
        return res;
    }

    public Matrix FindInverse() {
        double det = this.Determinant();
        if (det == 0) {
            throw new NullPointerException("This matrix doesn't have an inverse.");
        }
        Matrix inverse = new Matrix(n, m);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                inverse.SetElement(i, j, CreateMinor(i, j).Determinant() * Math.pow(-1, i + j));
            }
        }

        inverse = inverse.MultiplicationByConstant(1 / det);
        inverse = inverse.TransposeMain();

        return inverse;
    }

    public void Print() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void EnterMatrix(Scanner s) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                this.array[i][j] = s.nextDouble();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    1. Add matrices
                    2. Multiply matrix by a constant
                    3. Multiply matrices
                    4. Transpose matrix
                    5. Calculate a determinant
                    6. Inverse matrix
                    0. Exit
                    Your choice:""");
            int choice = s.nextInt();
            if (choice == 0) break;
            if (choice == 1) {
                System.out.print("Enter size of first matrix:");
                int n1 = s.nextInt(), m1 = s.nextInt();
                Matrix ma1 = new Matrix(n1, m1);
                System.out.print("Enter first matrix:");
                ma1.EnterMatrix(s);

                System.out.print("Enter size of second matrix:");
                int n2 = s.nextInt(), m2 = s.nextInt();
                Matrix ma2 = new Matrix(n2, m2);
                System.out.print("Enter second matrix:");
                ma2.EnterMatrix(s);

                try {
                    System.out.print("The result is:\n");
                    Matrix sum = ma1.Addition(ma2);
                    sum.Print();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choice == 2) {
                System.out.print("Enter size of matrix:");
                int n = s.nextInt(), m = s.nextInt();
                Matrix ma = new Matrix(n, m);
                System.out.print("Enter matrix:");
                ma.EnterMatrix(s);

                System.out.print("Enter constant:");
                double constant = s.nextDouble();
                System.out.print("The result is:\n");

                Matrix mult = ma.MultiplicationByConstant(constant);
                mult.Print();
            }

            if (choice == 3) {
                System.out.print("Enter size of first matrix:");
                int n1 = s.nextInt(), m1 = s.nextInt();
                Matrix ma1 = new Matrix(n1, m1);
                System.out.print("Enter first matrix:");
                ma1.EnterMatrix(s);

                System.out.print("Enter size of second matrix:");
                int n2 = s.nextInt(), m2 = s.nextInt();
                Matrix ma2 = new Matrix(n2, m2);
                System.out.print("Enter second matrix:");
                ma2.EnterMatrix(s);

                try {
                    System.out.print("The result is:\n");
                    Matrix mult = ma1.Multiplication(ma2);
                    mult.Print();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (choice == 4) {
                System.out.print("""
                        1. Main diagonal
                        2. Side diagonal
                        3. Vertical line
                        4. Horizontal line
                        Your choice:""");
                int choice2 = s.nextInt();

                System.out.print("Enter matrix size:\n");
                int n = s.nextInt(), m = s.nextInt();
                Matrix ma = new Matrix(n, m);
                System.out.print("Enter matrix:\n");
                ma.EnterMatrix(s);

                System.out.print("The result is:\n");
                Matrix result = null;
                switch (choice2) {
                    case(1):
                        result = ma.TransposeMain();
                        break;
                    case(2):
                        result = ma.TransposeSide();
                        break;
                    case(3):
                        result = ma.TransposeVertical();
                        break;
                    case(4):
                        result = ma.TransposeHorizontal();
                        break;
                }
                result.Print();
            }

            if (choice == 5) {
                System.out.print("Enter matrix size:\n");
                int n = s.nextInt(), m = s.nextInt();
                Matrix ma = new Matrix(n, m);
                System.out.print("Enter matrix:\n");
                ma.EnterMatrix(s);
                System.out.print("The result is:\n");
                System.out.println(ma.Determinant());
            }

            if (choice == 6) {
                System.out.print("Enter matrix size:\n");
                int n = s.nextInt(), m = s.nextInt();
                System.out.print("Enter matrix:\n");
                Matrix ma = new Matrix(n, m);
                ma.EnterMatrix(s);
                try {
                    Matrix inverse = ma.FindInverse();
                    System.out.print("The result is:\n");
                    inverse.Print();
                } catch (NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}