import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Programa {
    public static void main(String[] args) throws IOException {
        int TotalEntradas = 9; // Número de entradas externas del perceptrón
        int TotalSalidas = 1; // Número de salidas externas del perceptrón
        int TotalCapas = 4; // Total capas que tendrá el perceptrón
        int[] neuronasporcapa = new int[TotalCapas + 1]; // Los índices iniciarán en 1 en esta implementación
        neuronasporcapa[1] = TotalEntradas; // Entradas externas del perceptrón
        neuronasporcapa[2] = 7; // Capa oculta con 8 neuronas
        neuronasporcapa[3] = 7; // Capa oculta con 8 neuronas
        neuronasporcapa[4] = TotalSalidas; // Capa de salida con 1 neurona
        Perceptron objP = new Perceptron(TotalEntradas, TotalSalidas, TotalCapas, neuronasporcapa);
        // Lee los pesos
        double[][][] pesos = LeePesos("pesos.csv", objP.getW());
        objP.setW(pesos);
        // Lee los umbrales
        double[][] umbrales = LeeUmbrales("umbrales.csv", objP.getU());
        objP.setU(umbrales);
        // Lee los datos del csv
        int MaximosRegistros = 1253;
        double[][] entrada = new double[MaximosRegistros + 1][];
        double[][] salidas = new double[MaximosRegistros + 1][];
        final String urlArchivo = "THEWILDPROJECT.csv";
        double[][][] retorno = LeeDatosArchivo(urlArchivo, entrada, salidas);
        entrada = retorno[1];
        salidas = retorno[2];
        int ConjuntoEntradas = (int) retorno[0][0][0];
        // Normaliza los valores entre 0 y 1 que es lo que requiere el perceptrón
        double minimoLatitud = entrada[1][1], maximoLatitud = entrada[1][1];
        double minimoLongitud = entrada[1][2], maximoLongitud = entrada[1][2];
        double minimoElevacion = entrada[1][3], maximoElevacion = entrada[1][3];
        double minimoVegetacion = entrada[1][4], maximoVegetacion = entrada[1][4];
        double minimoVividez = entrada[1][5], maximoVividez = entrada[1][5];
        double minimoClima = entrada[1][6], maximoClima = entrada[1][6];
        double minimoSuelo = entrada[1][7], maximoSuelo = entrada[1][7];
        double minimoTemperatura = entrada[1][8], maximoTemperatura = entrada[1][8];
        double minimoPrecip = entrada[1][9], maximoPrecip = entrada[1][9];

        for (int cont = 1; cont <= ConjuntoEntradas; cont++) {
            if (entrada[cont][1] > maximoLatitud) {
                maximoLatitud = entrada[cont][1];
            }
            if (entrada[cont][2] > maximoLongitud) {
                maximoLongitud = entrada[cont][2];
            }
            if (entrada[cont][3] > maximoElevacion) {
                maximoElevacion = entrada[cont][3];
            }
            if (entrada[cont][4] > maximoVegetacion) {
                maximoVegetacion = entrada[cont][4];
            }
            if (entrada[cont][5] > maximoVividez) {
                maximoVividez = entrada[cont][5];
            }
            if (entrada[cont][6] > maximoClima) {
                maximoClima = entrada[cont][6];
            }
            if (entrada[cont][7] > maximoSuelo) {
                maximoSuelo = entrada[cont][7];
            }
            if (entrada[cont][8] > maximoTemperatura) {
                maximoTemperatura = entrada[cont][8];
            }
            if (entrada[cont][9] > maximoPrecip) {
                maximoPrecip = entrada[cont][9];
            }
            if (entrada[cont][1] < minimoLatitud) {
                minimoLatitud = entrada[cont][1];
            }
            if (entrada[cont][2] < minimoLongitud) {
                minimoLongitud = entrada[cont][2];
            }
            if (entrada[cont][3] < minimoElevacion) {
                minimoElevacion = entrada[cont][3];
            }
            if (entrada[cont][4] < minimoVegetacion) {
                minimoVegetacion = entrada[cont][4];
            }
            if (entrada[cont][5] < minimoVividez) {
                minimoVividez = entrada[cont][5];
            }
            if (entrada[cont][6] < minimoClima) {
                minimoClima = entrada[cont][6];
            }
            if (entrada[cont][7] < minimoSuelo) {
                minimoSuelo = entrada[cont][7];
            }
            if (entrada[cont][8] < minimoTemperatura) {
                minimoTemperatura = entrada[cont][8];
            }
            if (entrada[cont][9] < minimoPrecip) {
                minimoPrecip = entrada[cont][9];
            }
        }
        Window window1 = new Window(objP, minimoLatitud, maximoLatitud, minimoLongitud, maximoLongitud, minimoElevacion,
                maximoElevacion, minimoVegetacion, maximoVegetacion, minimoVividez, maximoVividez, minimoClima,
                maximoClima, minimoSuelo, maximoSuelo, minimoTemperatura, maximoTemperatura, minimoPrecip,
                maximoPrecip);
        window1.setVisible(true);

        /*
         * for (int cont = 1; cont <= ConjuntoEntradas; cont++) {
         * entrada[cont][1] = (entrada[cont][1] - minimoLatitud) / (maximoLatitud -
         * minimoLatitud);
         * entrada[cont][2] = (entrada[cont][2] - minimoLongitud) / (maximoLongitud -
         * minimoLongitud);
         * entrada[cont][3] = (entrada[cont][3] - minimoElevacion) / (maximoElevacion -
         * minimoElevacion);
         * entrada[cont][4] = (entrada[cont][4] - minimoVegetacion) / (maximoVegetacion
         * - minimoVegetacion);
         * entrada[cont][5] = (entrada[cont][5] - minimoVividez) / (maximoVividez -
         * minimoVividez);
         * entrada[cont][6] = (entrada[cont][6] - minimoClima) / (maximoClima -
         * minimoClima);
         * entrada[cont][7] = (entrada[cont][7] - minimoSuelo) / (maximoSuelo -
         * minimoSuelo);
         * entrada[cont][8] = (entrada[cont][8] - minimoTemperatura) /
         * (maximoTemperatura - minimoTemperatura);
         * entrada[cont][9] = (entrada[cont][9] - minimoPrecip) / (maximoPrecip -
         * minimoPrecip);
         * }
         * // Inicia el proceso de la red neuronal
         * double alpha = 0.2; // Factor de aprendizaje
         * for (int epoca = 1; epoca <= 120000; epoca++) {
         * if (epoca % 4000 == 0) {
         * System.out.println("Iteracion: " + epoca);
         * }
         * // Importante: Se envía el primer conjunto de entradas-salidas, luego el
         * // segundo, tercero y cuarto
         * // por cada ciclo de entrenamiento.
         * for (int entra = 1; entra <= ConjuntoEntradas; entra++) {
         * objP.Procesa(entrada[entra]);
         * objP.Entrena(alpha, entrada[entra], salidas[entra]);
         * }
         * }
         * // Muestra el resultado
         * for (int entra = 1; entra <= ConjuntoEntradas; entra++) {
         * System.out.print(entra);
         * objP.Procesa(entrada[entra]);
         * objP.Muestra(entrada[entra], salidas[entra], minimoLatitud, maximoLatitud);
         * }
         * escribirDatosArchivo(objP.getW());
         * escribirDatosUmbral(objP.getU());
         */
    }

    private static double[][][] LeeDatosArchivo(String urlArchivo, double[][] entrada, double[][] salida) {
        double[][][] retornar = new double[3][][];
        String archCSV = urlArchivo;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archCSV));
            lector.readLine();
            String fila = null;
            String[] campos;
            double limValores = 0.0;
            while ((fila = lector.readLine()) != null && limValores < 1253) {
                campos = fila.split(",");
                limValores += 1;
                entrada[(int) limValores] = new double[] { 0, Double.parseDouble(campos[0]),
                        Double.parseDouble(campos[1]), Double.parseDouble(campos[2]), Double.parseDouble(campos[3]),
                        Double.parseDouble(campos[4]), Double.parseDouble(campos[5]), Double.parseDouble(campos[6]),
                        Double.parseDouble(campos[7]), Double.parseDouble(campos[8]) };
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat formato = new DecimalFormat("#.#", simbolos);
                salida[(int) limValores] = new double[] { 0,
                        Double.parseDouble(formato.format(Double.parseDouble(campos[9]) * (0.2) - 0.1)) };
            }
            retornar[0] = new double[][] { { limValores } };
            retornar[1] = entrada;
            retornar[2] = salida;
            lector.close();
            return retornar;
        } catch (Exception e) {
            System.out.println(e);
        }
        return retornar;
    }

    private static void escribirDatosArchivo(double[][][] pesos) {
        File archivo = new File("pesos.csv");
        String archCSV = "pesos.csv";
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archCSV));
            for (int i = 1; i < pesos.length; i++) {
                for (int j = 1; j < pesos[i].length; j++) {
                    for (int k = 1; k < pesos[i][j].length; k++) {
                        escritor.write(pesos[i][j][k] + ",");
                        escritor.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static double[][][] LeePesos(String urlArchivo, double[][][] pesos) {
        String archCSV = urlArchivo;
        try {
            BufferedReader lector1 = new BufferedReader(new FileReader(archCSV));
            String fila = null;
            String[] campos;
            fila = lector1.readLine();
            campos = fila.split(",");
            int l = 0;
            for (int i = 1; i < pesos.length; i++) {
                for (int j = 1; j < pesos[i].length; j++) {
                    for (int k = 1; k < pesos[i][j].length; k++) {
                        pesos[i][j][k] = Double.parseDouble(campos[l]);
                        l++;
                    }
                }
            }
            lector1.close();
            return pesos;
        } catch (Exception e) {
            System.out.println(e);
        }
        return pesos;
    }

    private static void escribirDatosUmbral(double[][] umbrales) {
        File archivo = new File("umbrales.csv");
        String archCSV = "umbrales.csv";
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archCSV));
            for (int i = 1; i < umbrales.length; i++) {
                for (int j = 1; j < umbrales[i].length; j++) {
                    escritor.write(umbrales[i][j] + ",");
                    escritor.flush();
                }
            }
            escritor.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static double[][] LeeUmbrales(String urlArchivo, double[][] umbrales) {
        String archCSV = urlArchivo;
        try {
            BufferedReader lector1 = new BufferedReader(new FileReader(archCSV));
            String fila = null;
            String[] campos;
            fila = lector1.readLine();
            campos = fila.split(",");
            int l = 0;
            for (int i = 1; i < umbrales.length; i++) {
                for (int j = 1; j < umbrales[i].length; j++) {
                    umbrales[i][j] = Double.parseDouble(campos[l]);
                    l++;
                }
            }
            lector1.close();
            return umbrales;
        } catch (Exception e) {
            System.out.println(e);
        }
        return umbrales;
    }
}

class Perceptron {
    private double[][][] W; // Los pesos serán arreglos multidimensionales. Así: W[capa, neurona inicial,
                            // neurona final]
    private double[][] U; // Los umbrales de cada neurona serán arreglos bidimensionales. Así: U[capa,
                          // neurona que produce la salida]
    private double[][] A; // Las salidas de cada neurona serán arreglos bidimensionales. Así: A[capa,
                          // neurona que produce la salida]
    private double[][][] WN; // Los nuevos pesos serán arreglos multidimensionales. Así: W[capa, neurona
                             // inicial, neurona final]
    private double[][] UN; // Los nuevos umbrales de cada neurona serán arreglos bidimensionales. Así:
                           // U[capa, neurona que produce la salida]
    private int TotalCapas; // El total de capas que tendrá el perceptrón incluyendo la capa de entrada
    private int[] neuronasporcapa; // Cuantas neuronas habrá en cada capa
    private int TotalEntradas; // Total de entradas externas del perceptrón
    private int TotalSalidas; // Total salidas externas del perceptrón

    public Perceptron(int TotalEntradas, int TotalSalidas, int TotalCapas, int[] neuronasporcapa) {
        this.TotalEntradas = TotalEntradas;
        this.TotalSalidas = TotalSalidas;
        this.TotalCapas = TotalCapas;
        int maxNeuronas = 0; // Detecta el máximo número de neuronas por capa para dimensionar los arreglos
        this.neuronasporcapa = new int[TotalCapas + 1];
        for (int capa = 1; capa <= TotalCapas; capa++) {
            this.neuronasporcapa[capa] = neuronasporcapa[capa];
            if (neuronasporcapa[capa] > maxNeuronas) {
                maxNeuronas = neuronasporcapa[capa];
            }
        }
        // Dimensiona con el máximo valor
        W = new double[TotalCapas + 1][maxNeuronas + 1][maxNeuronas + 1];
        U = new double[TotalCapas + 1][maxNeuronas + 1];
        WN = new double[TotalCapas + 1][maxNeuronas + 1][maxNeuronas + 1];
        UN = new double[TotalCapas + 1][maxNeuronas + 1];
        A = new double[TotalCapas + 1][maxNeuronas + 1];
        // Da valores aleatorios a pesos y umbrales
        for (int capa = 2; capa <= TotalCapas; capa++) {
            for (int i = 1; i <= neuronasporcapa[capa]; i++) {
                U[capa][i] = Math.random();
            }
        }
        for (int capa = 1; capa < TotalCapas; capa++) {
            for (int i = 1; i <= neuronasporcapa[capa]; i++) {
                for (int j = 1; j <= neuronasporcapa[capa + 1]; j++) {
                    W[capa][i][j] = Math.random();
                }
            }
        }
    }

    public double[][][] getW() {
        return W;
    }

    public void setW(double[][][] W) {
        this.W = W;
    }

    public double[][] getU() {
        return U;
    }

    public void setU(double[][] U) {
        this.U = U;
    }

    public void Procesa(double[] E) {
        // Entradas externas del perceptrón pasan a la salida de la primera capa
        for (int copia = 1; copia <= TotalEntradas; copia++) {
            A[1][copia] = E[copia];
        }
        // Proceso del perceptrón
        for (int capa = 2; capa <= TotalCapas; capa++) {
            for (int neurona = 1; neurona <= neuronasporcapa[capa]; neurona++) {
                A[capa][neurona] = 0;
                for (int entra = 1; entra <= neuronasporcapa[capa - 1]; entra++) {
                    A[capa][neurona] += A[capa - 1][entra] * W[capa - 1][entra][neurona];
                }
                A[capa][neurona] += U[capa][neurona];
                A[capa][neurona] = 1 / (1 + Math.exp(-A[capa][neurona]));
            }
        }
    }

    // Muestra las entradas externas del perceptrón, las salidas esperadas y las
    // salidas reales
    // me falta saber como hacer aca para que salga el ave adecuada
    public void Muestra(double[] E, double[] S, double minimoX, double maximoX) {
        System.out.print(E[1] * (maximoX - minimoX) + minimoX);
        System.out.print(" ===> ");
        System.out.print(S[1]);
        System.out.print(" <vs> ");
        System.out.print(A[TotalCapas][1]); // Salidas reales del perceptrón
        int salidaEntera = 0;
        if ((A[TotalCapas][1] > 0.0) && (A[TotalCapas][1] < 0.2)) {
            salidaEntera = 1;
        }
        if ((A[TotalCapas][1] > 0.2) && (A[TotalCapas][1] < 0.4)) {
            salidaEntera = 2;
        }
        if ((A[TotalCapas][1] > 0.4) && (A[TotalCapas][1] < 0.6)) {
            salidaEntera = 3;
        }
        if ((A[TotalCapas][1] > 0.6) && (A[TotalCapas][1] < 0.8)) {
            salidaEntera = 4;
        }
        if ((A[TotalCapas][1] > 0.8) && (A[TotalCapas][1] < 1.0)) {
            salidaEntera = 5;
        }
        if (S[1] == 0.1) {
            S[1] = 1;
        }
        if (S[1] == 0.3) {
            S[1] = 2;
        }
        if (S[1] == 0.5) {
            S[1] = 3;
        }
        if (S[1] == 0.7) {
            S[1] = 4;
        }
        if (S[1] == 0.9) {
            System.out.println(S[1]);
            S[1] = 5;
        }
        if (salidaEntera != S[1]) {
            System.out.print("     MAL");
            System.out.println(S[1]);
            System.out.println(salidaEntera);
        }
        System.out.println();
    }

    public int Resultado(double[] E, double minimoX, double maximoX) {
        int salidaEntera = 0;
        if ((A[TotalCapas][1] > 0.0) && (A[TotalCapas][1] < 0.2)) {
            salidaEntera = 1;
        }
        if ((A[TotalCapas][1] > 0.2) && (A[TotalCapas][1] < 0.4)) {
            salidaEntera = 2;
        }
        if ((A[TotalCapas][1] > 0.4) && (A[TotalCapas][1] < 0.6)) {
            salidaEntera = 3;
        }
        if ((A[TotalCapas][1] > 0.6) && (A[TotalCapas][1] < 0.8)) {
            salidaEntera = 4;
        }
        if ((A[TotalCapas][1] > 0.8) && (A[TotalCapas][1] < 1.0)) {
            salidaEntera = 5;
        }
        return salidaEntera;
    }

    // El entrenamiento es ajustar los pesos y umbrales
    public void Entrena(double alpha, double[] E, double[] S) {
        // Ajusta pesos capa3 ==> capa4
        for (int j = 1; j <= neuronasporcapa[3]; j++) {
            for (int i = 1; i <= neuronasporcapa[4]; i++) {
                double Yi = A[4][i];
                double dE3 = A[3][j] * (Yi - S[i]) * Yi * (1 - Yi);
                WN[3][j][i] = W[3][j][i] - alpha * dE3; // Nuevo peso se guarda temporalmente
            }
        }
        // Ajusta pesos capa2 ==> capa3
        for (int j = 1; j <= neuronasporcapa[2]; j++) {
            for (int k = 1; k <= neuronasporcapa[3]; k++) {
                double acum = 0;
                for (int i = 1; i <= neuronasporcapa[4]; i++) {
                    double Yi = A[4][i];
                    acum += W[3][k][i] * (Yi - S[i]) * Yi * (1 - Yi);
                }
                double dE2 = A[2][j] * A[3][k] * (1 - A[3][k]) * acum;
                WN[2][j][k] = W[2][j][k] - alpha * dE2; // Nuevo peso se guarda temporalmente
            }
        }
        // Ajusta pesos capa1 ==> capa2
        for (int j = 1; j <= neuronasporcapa[1]; j++) {
            for (int k = 1; k <= neuronasporcapa[2]; k++) {
                double acumular = 0;
                for (int p = 1; p <= neuronasporcapa[3]; p++) {
                    double acum = 0;
                    for (int i = 1; i <= neuronasporcapa[4]; i++) {
                        double Yi = A[4][i];
                        acum += W[3][p][i] * (Yi - S[i]) * Yi * (1 - Yi);
                    }
                    acumular += W[2][k][p] * A[3][p] * (1 - A[3][p]) * acum;
                }
                double dE1 = E[j] * A[2][k] * (1 - A[2][k]) * acumular;
                WN[1][j][k] = W[1][j][k] - alpha * dE1; // Nuevo peso se guarda temporalmente
            }
        }
        // Ajusta umbrales de neuronas de la capa 4
        for (int i = 1; i <= neuronasporcapa[4]; i++) {
            double Yi = A[4][i];
            double dE4 = (Yi - S[i]) * Yi * (1 - Yi);
            UN[4][i] = U[4][i] - alpha * dE4; // Nuevo umbral se guarda temporalmente
        }
        // Ajusta umbrales de neuronas de la capa 3
        for (int k = 1; k <= neuronasporcapa[3]; k++) {
            double acum = 0;
            for (int i = 1; i <= neuronasporcapa[4]; i++) {
                double Yi = A[4][i];
                acum += W[3][k][i] * (Yi - S[i]) * Yi * (1 - Yi);
            }
            double dE3 = A[3][k] * (1 - A[3][k]) * acum;
            UN[3][k] = U[3][k] - alpha * dE3; // Nuevo umbral se guarda temporalmente
        }
        // Ajusta umbrales de neuronas de la capa 2
        for (int k = 1; k <= neuronasporcapa[2]; k++) {
            double acumular = 0;
            for (int p = 1; p <= neuronasporcapa[3]; p++) {
                double acum = 0;
                for (int i = 1; i <= neuronasporcapa[4]; i++) {
                    double Yi = A[4][i];
                    acum += W[3][p][i] * (Yi - S[i]) * Yi * (1 - Yi);
                }
                acumular += W[2][k][p] * A[3][p] * (1 - A[3][p]) * acum;
            }
            double dE2 = A[2][k] * (1 - A[2][k]) * acumular;
            UN[2][k] = U[2][k] - alpha * dE2; // Nuevo umbral se guarda temporalmente
        }
        // Copia los nuevos pesos y umbrales a los pesos y umbrales respectivos del
        // perceptrón
        for (int capa = 2; capa <= TotalCapas; capa++) {
            for (int i = 1; i <= neuronasporcapa[capa]; i++) {
                U[capa][i] = UN[capa][i];
            }
        }
        for (int capa = 1; capa < TotalCapas; capa++) {
            for (int i = 1; i <= neuronasporcapa[capa]; i++) {
                for (int j = 1; j <= neuronasporcapa[capa + 1]; j++) {
                    W[capa][i][j] = WN[capa][i][j];
                }
            }
        }
    }
}

class Window extends JFrame {

    public Window(Perceptron objP, double minimoLatitud, double maximoLatitud, double minimoLongitud,
            double maximoLongitud, double minimoElevacion, double maximoElevacion, double minimoVegetacion,
            double maximoVegetacion, double minimoVividez, double maximoVividez, double minimoClima,
            double maximoClima, double minimoSuelo, double maximoSuelo,
            double minimoTemperatura, double maximoTemperatura, double minimoPrecip, double maximoPrecip)
            throws IOException {
        // Creacion de la ventana del programa
        setTitle("The Wild Project");
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1400, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("images/TWPlogo.png");
        ImageIcon info = new ImageIcon("images/info.png");
        String varfilename = "images/TWPlogo.png";
        BufferedImage bufferedImage = ImageIO.read(new File(varfilename));
        // ImageIcon varImg = new ImageIcon(filename);
        Icon icon = new ImageIcon(info.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        setIconImage(img.getImage());
        // Color cfondo = new Color(253, 180, 1, 255);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        Font f1 = new Font("Sans", Font.PLAIN, 20);
        Font f2 = new Font("Sans", Font.BOLD, 35);
        Font f3 = new Font("Sans", Font.BOLD, 45);

        // Panel para clasificacion
        JPanel Clasif = new JPanel();
        Clasif.setLayout(null);
        Clasif.setBounds(0, 0, 1400, 800);
        Clasif.setBackground(Color.decode("#fdb401"));
        // Clasif.setOpaque(true);
        JLabel title = new JLabel("Clasificador de Biomas");
        title.setFont(f3);
        title.setBounds(400, 10, 550, 60);
        Clasif.add(title);
        JButton bton = new JButton("Clasificar");
        bton.setBackground(Color.BLACK);
        bton.setForeground(Color.WHITE);
        bton.setFont(f1);
        bton.setBounds(500, 480, 150, 50);
        Clasif.add(bton);

        JLabel Latitud = new JLabel("Latitud");
        Latitud.setFont(f1);
        Latitud.setBounds(200, 150, 100, 25);
        Clasif.add(Latitud);
        JTextField Latitudtext = new JTextField();
        Latitudtext.setBounds(200, 180, 150, 30);
        Clasif.add(Latitudtext);
        JButton bLatitud = new JButton();
        bLatitud.setIcon(icon);
        bLatitud.setBounds(360, 185, 20, 20);
        Clasif.add(bLatitud);

        JLabel longitude = new JLabel("Longitud");
        longitude.setFont(f1);
        longitude.setBounds(200, 230, 125, 25);
        Clasif.add(longitude);
        JTextField longitudetext = new JTextField();
        longitudetext.setBounds(200, 260, 150, 30);
        Clasif.add(longitudetext);
        JButton blongitude = new JButton();
        blongitude.setIcon(icon);
        blongitude.setBounds(360, 265, 20, 20);
        Clasif.add(blongitude);

        JLabel elevation = new JLabel("Elevacion");
        elevation.setFont(f1);
        elevation.setBounds(200, 310, 125, 25);
        Clasif.add(elevation);
        JTextField elevationtext = new JTextField();
        elevationtext.setBounds(200, 340, 150, 30);
        Clasif.add(elevationtext);
        JButton belevacion = new JButton();
        belevacion.setIcon(icon);
        belevacion.setBounds(360, 345, 20, 20);
        Clasif.add(belevacion);

        JLabel vegetation = new JLabel("Vegetacion");
        vegetation.setFont(f1);
        vegetation.setBounds(200, 390, 125, 25);
        Clasif.add(vegetation);
        JComboBox<String> vegetationcombo = new JComboBox<String>();
        vegetationcombo.addItem("Arborea");
        vegetationcombo.addItem("Arbustacea");
        vegetationcombo.setSelectedIndex(-1);
        vegetationcombo.setBounds(200, 420, 150, 30);
        Clasif.add(vegetationcombo);
        JButton bvegetation = new JButton();
        bvegetation.setIcon(icon);
        bvegetation.setBounds(360, 425, 20, 20);
        Clasif.add(bvegetation);

        JLabel vividez = new JLabel("Vividez");
        vividez.setFont(f1);
        vividez.setBounds(200, 470, 125, 25);
        Clasif.add(vividez);
        JComboBox<String> vividezcombo = new JComboBox<String>();
        vividezcombo.addItem("Vivida");
        vividezcombo.addItem("Moribunda");
        vividezcombo.setSelectedIndex(-1);
        vividezcombo.setBounds(200, 500, 150, 30);
        Clasif.add(vividezcombo);
        JButton bvividez = new JButton();
        bvividez.setIcon(icon);
        bvividez.setBounds(360, 505, 20, 20);
        Clasif.add(bvividez);

        JLabel clima = new JLabel("Clima");
        clima.setFont(f1);
        clima.setBounds(500, 150, 125, 25);
        Clasif.add(clima);
        JComboBox<String> climacombo = new JComboBox<String>();
        climacombo.addItem("Humedo");
        climacombo.addItem("Lluvioso");
        climacombo.addItem("Seco");
        climacombo.setSelectedIndex(-1);
        climacombo.setBounds(500, 180, 150, 30);
        Clasif.add(climacombo);
        JButton bclima = new JButton();
        bclima.setIcon(icon);
        bclima.setBounds(660, 185, 20, 20);
        Clasif.add(bclima);

        JLabel suelo = new JLabel("Suelo");
        suelo.setFont(f1);
        suelo.setBounds(500, 230, 125, 25);
        Clasif.add(suelo);
        JComboBox<String> suelocombo = new JComboBox<String>();
        suelocombo.addItem("Rojizo");
        suelocombo.addItem("Pulverizo");
        suelocombo.addItem("Solido");
        suelocombo.setSelectedIndex(-1);
        suelocombo.setEditable(false);
        suelocombo.setBounds(500, 260, 150, 30);
        Clasif.add(suelocombo);
        JButton bsuelo = new JButton();
        bsuelo.setIcon(icon);
        bsuelo.setBounds(660, 265, 20, 20);
        Clasif.add(bsuelo);

        JLabel temp = new JLabel("Temperatura");
        temp.setFont(f1);
        temp.setBounds(500, 310, 125, 25);
        Clasif.add(temp);
        JComboBox<String> tempcombo = new JComboBox<String>();
        tempcombo.addItem("Fria");
        tempcombo.addItem("Templada");
        tempcombo.addItem("Calida");
        tempcombo.setSelectedIndex(-1);
        tempcombo.setBounds(500, 340, 150, 30);
        Clasif.add(tempcombo);
        JButton btemp = new JButton();
        btemp.setIcon(icon);
        btemp.setBounds(660, 345, 20, 20);
        Clasif.add(btemp);

        JLabel precip = new JLabel("Precipitaciones");
        precip.setFont(f1);
        precip.setBounds(500, 390, 150, 25);
        Clasif.add(precip);
        JComboBox<String> precipcombo = new JComboBox<String>();
        precipcombo.addItem("Bajas");
        precipcombo.addItem("Moderadas");
        precipcombo.addItem("Altas");
        precipcombo.setSelectedIndex(-1);
        precipcombo.setBounds(500, 420, 150, 30);
        Clasif.add(precipcombo);
        JButton bprecip = new JButton();
        bprecip.setIcon(icon);
        bprecip.setBounds(660, 425, 20, 20);
        Clasif.add(bprecip);

        // Frame de imagen variable con eventos del programa
        JLabel imagenframe = new JLabel();
        imagenframe.setSize(500, 500);
        imagenframe.setBounds(800, 100, 500, 500);
        Image image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon varimage = new ImageIcon(image);
        imagenframe.setIcon(varimage);
        Clasif.add(imagenframe);

        JLabel resultado = new JLabel("Resultado de la clasificacion", SwingConstants.CENTER);
        resultado.setFont(f2);
        resultado.setBounds(800, 615, 500, 40);
        resultado.setBackground(Color.BLACK);
        Clasif.add(resultado);

        ActionListener ebLatitud = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String varfilename = "images/latitud.jpg";
                File imgfile = new File(varfilename);
                System.out.println(imgfile.exists());
                System.out.println(imgfile.canRead());
                // BufferedImage bufferedImage = ImageIO.read(new File(varfilename));
                // Image image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                Image image = new ImageIcon(getClass().getResource("images/latitud.jpg")).getImage();
                ImageIcon varimage = new ImageIcon(image);
                imagenframe.setIcon(varimage);
                Clasif.add(imagenframe);
                // InputStream is = new URL(varfilename).openStream();
                JOptionPane.showMessageDialog(null,
                        "Latitud: La latitud es la distancia angular entre la linea ecuatorial y un punto determinado de la Tierra, medida a lo largo del meridiano en el que se encuentra dicho punto",
                        "LATITUD", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bLatitud.addActionListener(ebLatitud);

        ActionListener ebLongitude = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Longitud: medida que en cartografia expresa la distancia angular entre un punto dado de la superficie terrestre y el meridiano que se toma como 0°",
                        "LONGITUD", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        blongitude.addActionListener(ebLongitude);

        ActionListener ebelevacion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Elevacion: Elevacion de un punto en concreto en la superficie terrestre respecto del nivel del mar",
                        "ELEVACION", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        belevacion.addActionListener(ebelevacion);

        ActionListener ebvegetacion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Vegetacion: La vegetacion del bioma, puede ser de tipo arbustacea o de tipo arborea",
                        "VEGETACION", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bvegetation.addActionListener(ebvegetacion);

        ActionListener ebvividez = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Vividez: El aspecto de la vegetacion en el bioma, si es mas vivida o moribunda, es decir, de colores mas vivos o mas apagados",
                        "VIVIDEZ", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bvividez.addActionListener(ebvividez);

        ActionListener ebclima = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Clima: Como es el clima en el bioma, si es lluvioso, humedo o mas bien seco",
                        "CLIMA", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bclima.addActionListener(ebclima);

        ActionListener ebsuelo = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Suelo: Como es la estructura del suelo en el bioma, si es un suelo rojizo, mas pulverizo o es un suelo solido",
                        "SUELO", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bsuelo.addActionListener(ebsuelo);

        ActionListener ebtemp = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Temperatura: Como es la temperatura del bioma, si es de temperaturas frias, templadas o calidas/calientes",
                        "TEMPERATURA", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        btemp.addActionListener(ebtemp);

        ActionListener ebprecip = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Precipitaciones: Cuan cuantiosas son las precipitaciones en el bioma; bajas, moderadas o altas",
                        "PRECIPITACIONES", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        bprecip.addActionListener(ebprecip);

        ActionListener Bprediccion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double minLatitud = minimoLatitud, maxLatitud = maximoLatitud;
                double minLongitud = minimoLongitud, maxLongitud = maximoLongitud;
                double minElevacion = minimoElevacion, maxElevacion = maximoElevacion;
                double minVegetacion = minimoVegetacion, maxVegetacion = maximoVegetacion;
                double minVividez = minimoVividez, maxVividez = maximoVividez;
                double minClima = minimoClima, maxClima = maximoClima;
                double minSuelo = minimoSuelo, maxSuelo = maximoSuelo;
                double minTemp = minimoTemperatura, maxTemp = maximoTemperatura;
                double minPrec = minimoPrecip, maxPrec = maximoPrecip;

                try {
                    Double latitud_num = Double.parseDouble(Latitudtext.getText());
                    try {
                        Double longitud_num = Double.parseDouble(longitudetext.getText());
                        try {
                            Double elevation_num = Double.parseDouble(elevationtext.getText());
                            try {
                                Double veg_num = ((double) vegetationcombo.getSelectedIndex()) + 1;
                                Double vivid_num = ((double) vividezcombo.getSelectedIndex()) + 1;
                                Double clima_num = ((double) climacombo.getSelectedIndex()) + 1;
                                Double suelo_num = ((double) suelocombo.getSelectedIndex()) + 1;
                                Double temp_num = ((double) tempcombo.getSelectedIndex()) + 1;
                                Double prec_num = ((double) precipcombo.getSelectedIndex()) + 1;

                                if (latitud_num > maxLatitud) {
                                    maxLatitud = latitud_num;
                                }
                                if (longitud_num > maxLongitud) {
                                    maxLongitud = longitud_num;
                                }
                                if (elevation_num > maxElevacion) {
                                    maxElevacion = elevation_num;
                                }
                                if (veg_num > maxVegetacion) {
                                    maxVegetacion = veg_num;
                                }
                                if (vivid_num > maxVividez) {
                                    maxVividez = vivid_num;
                                }
                                if (clima_num > maxClima) {
                                    maxClima = clima_num;
                                }
                                if (suelo_num > maxSuelo) {
                                    maxSuelo = suelo_num;
                                }
                                if (temp_num > maxTemp) {
                                    maxTemp = temp_num;
                                }
                                if (prec_num > maxPrec) {
                                    maxPrec = prec_num;
                                }
                                if (latitud_num < minLatitud) {
                                    minLatitud = latitud_num;
                                }
                                if (longitud_num < maxLongitud) {
                                    minLongitud = longitud_num;
                                }
                                if (elevation_num < minElevacion) {
                                    minElevacion = elevation_num;
                                }
                                if (veg_num < minVegetacion) {
                                    minVegetacion = veg_num;
                                }
                                if (vivid_num < minVividez) {
                                    minVividez = vivid_num;
                                }
                                if (clima_num < minClima) {
                                    minClima = clima_num;
                                }
                                if (suelo_num < minSuelo) {
                                    minSuelo = suelo_num;
                                }
                                if (temp_num < minTemp) {
                                    minTemp = temp_num;
                                }
                                if (prec_num < minPrec) {
                                    minPrec = prec_num;
                                }

                                double[] ingreso = new double[] { 0, latitud_num, longitud_num, elevation_num,
                                        veg_num,
                                        vivid_num, clima_num, suelo_num, temp_num, prec_num };
                                ingreso[1] = (ingreso[1] - minLatitud) / (maxLatitud -
                                        minLatitud);
                                ingreso[2] = (ingreso[2] - minLongitud) / (maxLongitud -
                                        minLongitud);
                                ingreso[3] = (ingreso[3] - minElevacion) / (maxElevacion -
                                        minElevacion);
                                ingreso[4] = (ingreso[4] - minVegetacion) / (maxVegetacion
                                        - minVegetacion);
                                ingreso[5] = (ingreso[5] - minVividez) / (maxVividez -
                                        minVividez);
                                ingreso[6] = (ingreso[6] - minClima) / (maxClima -
                                        minClima);
                                ingreso[7] = (ingreso[7] - minSuelo) / (maxSuelo -
                                        minSuelo);
                                ingreso[8] = (ingreso[8] - minTemp) /
                                        (maxTemp - minTemp);
                                ingreso[9] = (ingreso[9] - minPrec) / (maxPrec -
                                        minPrec);

                                objP.Procesa(ingreso);
                                int result = objP.Resultado(ingreso, minimoLatitud, maximoLatitud);
                                {
                                    if (result == 1) {
                                        resultado.setText("Selva Misionera");
                                    } else if (result == 2) {
                                        resultado.setText("Bosque Chaquenio");
                                    } else if (result == 3) {
                                        resultado.setText("Desierto Andino");
                                    } else if (result == 4) {
                                        resultado.setText("Pastizal Pampeano");
                                    } else if (result == 5) {
                                        resultado.setText("Estepa Patagonica");
                                    }
                                }
                                ;

                            } catch (java.lang.NullPointerException event) {
                                JOptionPane.showMessageDialog(null,
                                        "Debe completar las opciones en las cajas de seleccion", "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (java.lang.NumberFormatException event) {
                            JOptionPane.showMessageDialog(null,
                                    "Se deben que ingresar los valores de altitud en datos umericos", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            elevationtext.requestFocus();
                        }
                    } catch (java.lang.NumberFormatException event) {
                        JOptionPane.showMessageDialog(null,
                                "Se deben que ingresar las coordenadas de longitud en valores numericos",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        longitudetext.requestFocus();

                    }

                } catch (java.lang.NumberFormatException event) {
                    JOptionPane.showMessageDialog(null,
                            "Se deben que ingresar las coordenadas de latitud en valores numericos", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    Latitudtext.requestFocus();
                }
            }
        };

        bton.addActionListener(Bprediccion);
        mainPanel.add(Clasif);
        add(mainPanel);
    }
}