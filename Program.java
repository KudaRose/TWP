import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Program {
    public static void main(String[] args) {
        int TotalEntradas = 11; // Número de entradas externas del perceptrón
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
        // Lee los datos de un archivo plano
        int MaximosRegistros = 1013;
        double[][] entrada = new double[MaximosRegistros + 1][];
        double[][] salidas = new double[MaximosRegistros + 1][];
        final String urlArchivo = "informacionNutricional.csv";
        double[][][] EntradaSalidasCantRegistros = LeeDatosArchivo(urlArchivo, entrada, salidas);
        entrada = EntradaSalidasCantRegistros[1];
        salidas = EntradaSalidasCantRegistros[2];
        int ConjuntoEntradas = (int) EntradaSalidasCantRegistros[0][0][0];
        // Normaliza los valores entre 0 y 1 que es lo que requiere el perceptrón
        double minimoSodio = entrada[1][1], maximoSodio = entrada[1][1];
        double minimoProteina = entrada[1][2], maximoProteina = entrada[1][2];
        double minimoCarbohidratos = entrada[1][3], maximoCarbohidratos = entrada[1][3];
        double minimoFibra = entrada[1][4], maximoFibra = entrada[1][4];
        double minimoAzucar = entrada[1][5], maximoAzucar = entrada[1][5];
        double minimoSaturadas = entrada[1][6], maximoSaturadas = entrada[1][6];
        double minimoMonoinsaturadas = entrada[1][7], maximoMonoinsaturadas = entrada[1][7];
        double minimoPolinsaturadas = entrada[1][8], maximoPolinsaturadas = entrada[1][8];
        double minimoTrans = entrada[1][9], maximoTrans = entrada[1][9];
        double minimoCalorias = entrada[1][10], maximoCalorias = entrada[1][10];
        double minimoNatural = entrada[1][11], maximoNatural = entrada[1][11];
        double minimoY = salidas[1][1], maximoY = salidas[1][1];
        for (int cont = 1; cont <= ConjuntoEntradas; cont++) {
            if (entrada[cont][1] > maximoSodio) {
                maximoSodio = entrada[cont][1];
            }
            if (entrada[cont][2] > maximoProteina) {
                maximoProteina = entrada[cont][2];
            }
            if (entrada[cont][3] > maximoCarbohidratos) {
                maximoCarbohidratos = entrada[cont][3];
            }
            if (entrada[cont][4] > maximoFibra) {
                maximoFibra = entrada[cont][4];
            }
            if (entrada[cont][5] > maximoAzucar) {
                maximoAzucar = entrada[cont][5];
            }
            if (entrada[cont][6] > maximoSaturadas) {
                maximoSaturadas = entrada[cont][6];
            }
            if (entrada[cont][7] > maximoMonoinsaturadas) {
                maximoMonoinsaturadas = entrada[cont][7];
            }
            if (entrada[cont][8] > maximoPolinsaturadas) {
                maximoPolinsaturadas = entrada[cont][8];
            }
            if (entrada[cont][9] > maximoTrans) {
                maximoTrans = entrada[cont][9];
            }
            if (entrada[cont][10] > maximoCalorias) {
                maximoCalorias = entrada[cont][10];
            }
            if (entrada[cont][11] > maximoNatural) {
                maximoNatural = entrada[cont][11];
            }
            if (salidas[cont][1] > maximoY) {
                maximoY = salidas[cont][1];
            }
            if (entrada[cont][1] < minimoSodio) {
                minimoSodio = entrada[cont][1];
            }
            if (entrada[cont][2] < minimoProteina) {
                minimoProteina = entrada[cont][2];
            }
            if (entrada[cont][3] < minimoCarbohidratos) {
                minimoCarbohidratos = entrada[cont][3];
            }
            if (entrada[cont][4] < minimoFibra) {
                minimoFibra = entrada[cont][4];
            }
            if (entrada[cont][5] < minimoAzucar) {
                minimoAzucar = entrada[cont][5];
            }
            if (entrada[cont][6] < minimoSaturadas) {
                minimoSaturadas = entrada[cont][6];
            }
            if (entrada[cont][7] < minimoMonoinsaturadas) {
                minimoMonoinsaturadas = entrada[cont][7];
            }
            if (entrada[cont][8] < minimoPolinsaturadas) {
                minimoPolinsaturadas = entrada[cont][8];
            }
            if (entrada[cont][9] < minimoTrans) {
                minimoTrans = entrada[cont][9];
            }
            if (entrada[cont][10] < minimoCalorias) {
                minimoCalorias = entrada[cont][10];
            }
            if (entrada[cont][11] < minimoNatural) {
                minimoNatural = entrada[cont][11];
            }
            if (salidas[cont][1] < minimoY) {
                minimoY = salidas[cont][1];
            }
        }

        Ventana ventana1 = new Ventana(objP, minimoSodio, maximoSodio, minimoProteina, maximoProteina,
                minimoCarbohidratos, maximoCarbohidratos, minimoFibra, maximoFibra, minimoAzucar, maximoAzucar,
                minimoSaturadas, maximoSaturadas, minimoMonoinsaturadas, maximoMonoinsaturadas, minimoPolinsaturadas,
                maximoPolinsaturadas, minimoTrans, maximoTrans, minimoCalorias, maximoCalorias, minimoNatural,
                maximoNatural, minimoY, maximoY);
        ventana1.setVisible(true);
        /*
         * for (int cont = 1; cont <= ConjuntoEntradas; cont++) {
         * entrada[cont][1] = (entrada[cont][1] - minimoSodio) / (maximoSodio -
         * minimoSodio);
         * entrada[cont][2] = (entrada[cont][2] - minimoProteina) / (maximoProteina -
         * minimoProteina);
         * entrada[cont][3] = (entrada[cont][3] - minimoCarbohidratos) /
         * (maximoCarbohidratos - minimoCarbohidratos);
         * entrada[cont][4] = (entrada[cont][4] - minimoFibra) / (maximoFibra -
         * minimoFibra);
         * entrada[cont][5] = (entrada[cont][5] - minimoAzucar) / (maximoAzucar -
         * minimoAzucar);
         * entrada[cont][6] = (entrada[cont][6] - minimoSaturadas) / (maximoSaturadas -
         * minimoSaturadas);
         * entrada[cont][7] = (entrada[cont][7] - minimoMonoinsaturadas) /
         * (maximoMonoinsaturadas - minimoMonoinsaturadas);
         * entrada[cont][8] = (entrada[cont][8] - minimoPolinsaturadas) /
         * (maximoPolinsaturadas - minimoPolinsaturadas);
         * entrada[cont][9] = (entrada[cont][9] - minimoTrans) / (maximoTrans -
         * minimoTrans);
         * entrada[cont][10] = (entrada[cont][10] - minimoCalorias) / (maximoCalorias -
         * minimoCalorias);
         * entrada[cont][11] = (entrada[cont][11] - minimoNatural) / (maximoNatural -
         * minimoNatural);
         * salidas[cont][1] = (salidas[cont][1] - minimoY) / (maximoY - minimoY);
         * }
         * 
         * //Inicia el proceso de la red neuronal
         * double alpha = 0.2; //Factor de aprendizaje
         * for (int epoca = 1; epoca <= 8000; epoca++) {
         * if (epoca % 4000 == 0){
         * System.out.println("Iteracion: " + epoca);
         * }
         * //Importante: Se envía el primer conjunto de entradas-salidas, luego el
         * segundo, tercero y cuarto
         * //por cada ciclo de entrenamiento.
         * for (int entra = 1; entra <= ConjuntoEntradas; entra++) {
         * objP.Procesa(entrada[entra]);
         * objP.Entrena(alpha, entrada[entra], salidas[entra]);
         * }
         * }
         * //Muestra el resultado
         * for (int entra = 1; entra <= ConjuntoEntradas; entra++) {
         * System.out.print(entra);
         * objP.Procesa(entrada[entra]);
         * objP.Muestra(entrada[entra], salidas[entra],minimoSodio,maximoSodio,minimoY,
         * maximoY);
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
            String fila = null;
            String[] campos;
            double limValores = 0.0;
            lector.readLine();
            while ((fila = lector.readLine()) != null && limValores < 1013) {
                campos = fila.split(",");
                limValores += 1;
                if (campos[14].equals("Natural")) {
                    campos[14] = "1.0";
                } else {
                    campos[14] = "0.0";
                }
                entrada[(int) limValores] = new double[] { 0, Double.parseDouble(campos[5]),
                        Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), Double.parseDouble(campos[8]),
                        Double.parseDouble(campos[9]), Double.parseDouble(campos[10]), Double.parseDouble(campos[11]),
                        Double.parseDouble(campos[12]), Double.parseDouble(campos[13]), Double.parseDouble(campos[4]),
                        Double.parseDouble(campos[14]) };
                if (campos[15].equals("Saludable")) {
                    salida[(int) limValores] = new double[] { 0, 1.0 };
                } else {
                    salida[(int) limValores] = new double[] { 0, 0.0 };
                }
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
            escritor.close();
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

class Ventana extends JFrame {
    public Ventana(Perceptron objP, double minimoSodio, double maximoSodio, double minimoProteina,
            double maximoProteina, double minimoCarbohidratos, double maximoCarbohidratos, double minimoFibra,
            double maximoFibra, double minimoAzucar, double maximoAzucar, double minimoSaturadas,
            double maximoSaturadas, double minimoMonoinsaturadas, double maximoMonoinsaturadas,
            double minimoPolinsaturadas, double maximoPolinsaturadas, double minimoTrans, double maximoTrans,
            double minimoCalorias, double maximoCalorias, double minimoNatural, double maximoNatural, double minimoY,
            double maximoY) {
        // Configuraciones de ventana
        setTitle("Health-Care");
        setExtendedState(JFrame.MAXIMIZED_BOTH);// 1350X700
        Color verde = new Color(0, 135, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel Pprincipal = new JPanel();
        Pprincipal.setLayout(null);
        Pprincipal.setBackground(Color.WHITE);

        // Creamos Menu
        Font f1 = new Font("Arial", Font.BOLD, 60);
        Font f2 = new Font("Arial", Font.PLAIN, 30);
        Font f3 = new Font("Arial", Font.PLAIN, 20);
        Font f4 = new Font("Arial", Font.PLAIN, 18);
        JLabel Llogo = new JLabel();
        ImageIcon ImagenLogo = new ImageIcon(getClass().getResource("logoHealthcare.png"));
        Icon IconoLogo = new ImageIcon(ImagenLogo.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        Llogo.setIcon(IconoLogo);
        Llogo.setBounds(0, 0, 75, 75);
        Pprincipal.add(Llogo);
        JLabel HealthCare = new JLabel("Health-Care");
        HealthCare.setForeground(Color.WHITE);
        HealthCare.setOpaque(true);
        HealthCare.setBackground(verde);
        HealthCare.setFont(f1);
        HealthCare.setBounds(75, 0, 1300, 75);
        Pprincipal.add(HealthCare);

        // Panel de clasificacion
        JPanel Pclasificacion = new JPanel();
        Pclasificacion.setLayout(null);
        Pclasificacion.setBackground(Color.WHITE);
        Pclasificacion.setBounds(0, 0, 1400, 800);
        JLabel LinfoNutri = new JLabel("INFORMACIÓN NUTRICIONAL");
        LinfoNutri.setFont(f2);
        LinfoNutri.setBounds(100, 100, 450, 30);
        Pclasificacion.add(LinfoNutri);
        JButton Bclasificar = new JButton("Clasificar");
        Bclasificar.setBackground(Color.BLACK);
        Bclasificar.setForeground(Color.WHITE);
        Bclasificar.setFont(f4);
        Bclasificar.setBounds(550, 555, 150, 30);
        Pclasificacion.add(Bclasificar);
        JPanel PIndicadores = new JPanel();
        PIndicadores.setLayout(new GridBagLayout());
        GridBagConstraints cPIndicadores = new GridBagConstraints();
        PIndicadores.setBackground(verde);
        PIndicadores.setBounds(100, 150, 700, 500);
        JLabel LCalorias = new JLabel("Calorías");
        cPIndicadores.weightx = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 0;
        cPIndicadores.anchor = GridBagConstraints.NORTHWEST;
        cPIndicadores.insets = new Insets(70, 30, 0, 0);
        LCalorias.setFont(f3);
        LCalorias.setForeground(Color.WHITE);
        PIndicadores.add(LCalorias, cPIndicadores);
        JTextField TFcalorias = new JTextField();
        TFcalorias.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(70, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 0;
        PIndicadores.add(TFcalorias, cPIndicadores);
        JButton Bcalorias = new JButton();
        Bcalorias.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBcalorias = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBcalorias = new ImageIcon(ImagenBcalorias.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Bcalorias.setIcon(IconoBcalorias);
        Bcalorias.setBackground(verde);
        cPIndicadores.insets = new Insets(70, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 0;
        PIndicadores.add(Bcalorias, cPIndicadores);
        JLabel LProteinas = new JLabel("Proteínas");
        cPIndicadores.gridx = 3;
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridy = 0;
        cPIndicadores.insets = new Insets(70, 20, 0, 0);
        LProteinas.setFont(f3);
        LProteinas.setForeground(Color.WHITE);
        PIndicadores.add(LProteinas, cPIndicadores);
        JTextField TFProteinas = new JTextField();
        TFProteinas.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(70, 0, 0, 0);
        cPIndicadores.gridx = 4;
        cPIndicadores.gridy = 0;
        PIndicadores.add(TFProteinas, cPIndicadores);
        JButton BProteinas = new JButton();
        BProteinas.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBProteinas = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBProteinas = new ImageIcon(
                ImagenBProteinas.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BProteinas.setIcon(IconoBProteinas);
        BProteinas.setBackground(verde);
        cPIndicadores.insets = new Insets(70, 0, 0, 0);
        cPIndicadores.gridx = 5;
        cPIndicadores.gridy = 0;
        cPIndicadores.weightx = 1.0;
        PIndicadores.add(BProteinas, cPIndicadores);
        JLabel Lcarbohidratos = new JLabel("Carbohidratos");
        cPIndicadores.ipadx = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 1;
        cPIndicadores.insets = new Insets(40, 30, 0, 0);
        Lcarbohidratos.setFont(f3);
        Lcarbohidratos.setForeground(Color.WHITE);
        PIndicadores.add(Lcarbohidratos, cPIndicadores);
        JTextField TFCarbohidratos = new JTextField();
        TFCarbohidratos.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 1;
        PIndicadores.add(TFCarbohidratos, cPIndicadores);
        JButton BCarbohidratos = new JButton();
        BCarbohidratos.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBCarbohidratos = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBCarbohidratos = new ImageIcon(
                ImagenBCarbohidratos.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BCarbohidratos.setIcon(IconoBCarbohidratos);
        BCarbohidratos.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 1;
        PIndicadores.add(BCarbohidratos, cPIndicadores);
        JLabel LFibra = new JLabel("Fibra");
        cPIndicadores.gridx = 3;
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridy = 1;
        cPIndicadores.insets = new Insets(40, 20, 0, 0);
        LFibra.setFont(f3);
        LFibra.setForeground(Color.WHITE);
        PIndicadores.add(LFibra, cPIndicadores);
        JTextField TFFibra = new JTextField();
        TFFibra.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 4;
        cPIndicadores.gridy = 1;
        PIndicadores.add(TFFibra, cPIndicadores);
        JButton BFibra = new JButton();
        BFibra.setFont(f4);
        cPIndicadores.weightx = 1.0;
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBFibra = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBFibra = new ImageIcon(ImagenBFibra.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BFibra.setIcon(IconoBFibra);
        BFibra.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 5;
        cPIndicadores.gridy = 1;
        PIndicadores.add(BFibra, cPIndicadores);
        JLabel Lazucar = new JLabel("Azúcar");
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 2;
        cPIndicadores.insets = new Insets(40, 30, 0, 0);
        Lazucar.setFont(f3);
        Lazucar.setForeground(Color.WHITE);
        PIndicadores.add(Lazucar, cPIndicadores);
        JTextField TFAzucar = new JTextField();
        TFAzucar.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 2;
        PIndicadores.add(TFAzucar, cPIndicadores);
        JButton BAzucar = new JButton();
        BAzucar.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBAzucar = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBAzucar = new ImageIcon(ImagenBAzucar.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BAzucar.setIcon(IconoBAzucar);
        BAzucar.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 2;
        PIndicadores.add(BAzucar, cPIndicadores);
        JLabel LSodio = new JLabel("Sodio");
        cPIndicadores.gridx = 3;
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridy = 2;
        cPIndicadores.insets = new Insets(40, 20, 0, 0);
        LSodio.setFont(f3);
        LSodio.setForeground(Color.WHITE);
        PIndicadores.add(LSodio, cPIndicadores);
        JTextField TFSodio = new JTextField();
        TFSodio.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 4;
        cPIndicadores.gridy = 2;
        PIndicadores.add(TFSodio, cPIndicadores);
        JButton BSodio = new JButton();
        cPIndicadores.weightx = 1.0;
        BSodio.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBSodio = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBSodio = new ImageIcon(ImagenBSodio.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BSodio.setIcon(IconoBSodio);
        BSodio.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 5;
        cPIndicadores.gridy = 2;
        PIndicadores.add(BSodio, cPIndicadores);
        JLabel LgrasaSaturada = new JLabel("G.Saturadas");
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 3;
        cPIndicadores.insets = new Insets(40, 30, 0, 0);
        LgrasaSaturada.setFont(f3);
        LgrasaSaturada.setForeground(Color.WHITE);
        PIndicadores.add(LgrasaSaturada, cPIndicadores);
        JTextField TFgrasasSaturadas = new JTextField();
        TFgrasasSaturadas.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 3;
        PIndicadores.add(TFgrasasSaturadas, cPIndicadores);
        JButton BgrasasSaturadas = new JButton();
        BgrasasSaturadas.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBgrasasSaturadas = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBgrasasSaturadas = new ImageIcon(
                ImagenBgrasasSaturadas.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BgrasasSaturadas.setIcon(IconoBgrasasSaturadas);
        BgrasasSaturadas.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 3;
        PIndicadores.add(BgrasasSaturadas, cPIndicadores);
        JLabel LGrasasTrasn = new JLabel("G.Trans");
        cPIndicadores.gridx = 3;
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridy = 3;
        cPIndicadores.insets = new Insets(40, 20, 0, 0);
        LGrasasTrasn.setFont(f3);
        LGrasasTrasn.setForeground(Color.WHITE);
        PIndicadores.add(LGrasasTrasn, cPIndicadores);
        JTextField TFGrasasTrasn = new JTextField();
        TFGrasasTrasn.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 4;
        cPIndicadores.gridy = 3;
        PIndicadores.add(TFGrasasTrasn, cPIndicadores);
        JButton BGrasasTrasn = new JButton();
        cPIndicadores.weightx = 1.0;
        BGrasasTrasn.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBGrasasTrasn = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBGrasasTrasn = new ImageIcon(
                ImagenBGrasasTrasn.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BGrasasTrasn.setIcon(IconoBGrasasTrasn);
        BGrasasTrasn.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 5;
        cPIndicadores.gridy = 3;
        PIndicadores.add(BGrasasTrasn, cPIndicadores);
        JLabel LgrasaMonoinsaturada = new JLabel("G.Monoinsat.");
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 4;
        cPIndicadores.insets = new Insets(40, 30, 0, 0);
        LgrasaMonoinsaturada.setFont(f3);
        LgrasaMonoinsaturada.setForeground(Color.WHITE);
        PIndicadores.add(LgrasaMonoinsaturada, cPIndicadores);
        JTextField TFGrasasMonoinsaturadas = new JTextField();
        TFGrasasMonoinsaturadas.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 4;
        PIndicadores.add(TFGrasasMonoinsaturadas, cPIndicadores);
        JButton BGrasasMonoinsaturadas = new JButton();
        BGrasasMonoinsaturadas.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBGrasasMonoinsaturadas = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBGrasasMonoinsaturadas = new ImageIcon(
                ImagenBGrasasMonoinsaturadas.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BGrasasMonoinsaturadas.setIcon(IconoBGrasasMonoinsaturadas);
        BGrasasMonoinsaturadas.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 4;
        PIndicadores.add(BGrasasMonoinsaturadas, cPIndicadores);
        JLabel LgrasaPoliinsaturada = new JLabel("G.Poliinsat.");
        cPIndicadores.gridx = 3;
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridy = 4;
        cPIndicadores.insets = new Insets(40, 20, 0, 0);
        LgrasaPoliinsaturada.setFont(f3);
        LgrasaPoliinsaturada.setForeground(Color.WHITE);
        PIndicadores.add(LgrasaPoliinsaturada, cPIndicadores);
        JTextField TFgrasaPoliinsaturada = new JTextField();
        TFgrasaPoliinsaturada.setFont(f4);
        cPIndicadores.ipadx = 110;
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 4;
        cPIndicadores.gridy = 4;
        PIndicadores.add(TFgrasaPoliinsaturada, cPIndicadores);
        JButton BgrasaPoliinsaturada = new JButton();
        cPIndicadores.weightx = 1.0;
        BgrasaPoliinsaturada.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBgrasaPoliinsaturada = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBgrasaPoliinsaturada = new ImageIcon(
                ImagenBgrasaPoliinsaturada.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BgrasaPoliinsaturada.setIcon(IconoBgrasaPoliinsaturada);
        BgrasaPoliinsaturada.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 5;
        cPIndicadores.gridy = 4;
        PIndicadores.add(BgrasaPoliinsaturada, cPIndicadores);
        JLabel LtipoAlimento = new JLabel("Tipo Alimento");
        cPIndicadores.ipadx = 0;
        cPIndicadores.ipady = 0;
        cPIndicadores.gridx = 0;
        cPIndicadores.gridy = 5;
        cPIndicadores.insets = new Insets(40, 30, 0, 0);
        LtipoAlimento.setFont(f3);
        LtipoAlimento.setForeground(Color.WHITE);
        PIndicadores.add(LtipoAlimento, cPIndicadores);
        JComboBox<String> TFtipoAlimento = new JComboBox<String>();
        TFtipoAlimento.addItem("Procesado");
        TFtipoAlimento.addItem("Natural");
        TFtipoAlimento.setEditable(false);
        Pclasificacion.add(PIndicadores);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 1;
        cPIndicadores.gridy = 5;
        cPIndicadores.ipady = -5;
        TFtipoAlimento.setFont(f4);
        cPIndicadores.weightx = 1.0;
        cPIndicadores.weighty = 1.0;
        PIndicadores.add(TFtipoAlimento, cPIndicadores);
        JButton BTipoAlimento = new JButton();
        cPIndicadores.weightx = 1.0;
        BTipoAlimento.setFont(f4);
        cPIndicadores.ipadx = -25;
        cPIndicadores.ipady = -3;
        ImageIcon ImagenBTipoAlimento = new ImageIcon(getClass().getResource("botonInfoemacion.png"));
        Icon IconoBTipoAlimento = new ImageIcon(
                ImagenBTipoAlimento.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        BTipoAlimento.setIcon(IconoBTipoAlimento);
        BTipoAlimento.setBackground(verde);
        cPIndicadores.insets = new Insets(40, 0, 0, 0);
        cPIndicadores.gridx = 2;
        cPIndicadores.gridy = 5;
        PIndicadores.add(BTipoAlimento, cPIndicadores);
        JLabel LinfoNutrifoto = new JLabel();
        ImageIcon ImagenNutri = new ImageIcon(getClass().getResource("informacionNutricionalFoto1.jpg"));
        Icon IconoNutri = new ImageIcon(ImagenNutri.getImage().getScaledInstance(425, 375, Image.SCALE_DEFAULT));
        LinfoNutrifoto.setIcon(IconoNutri);
        LinfoNutrifoto.setBounds(850, 150, 425, 375);
        Pclasificacion.add(LinfoNutrifoto);
        JLabel Lclasificacion = new JLabel("CLASIFICACIÓN:");
        Lclasificacion.setFont(f2);
        Lclasificacion.setBounds(850, 550, 425, 30);
        Pclasificacion.add(Lclasificacion);
        JLabel Lsaludable_no = new JLabel();
        Lsaludable_no.setFont(f2);
        Lsaludable_no.setForeground(Color.WHITE);
        Lsaludable_no.setOpaque(true);
        Lsaludable_no.setBackground(verde);
        Lsaludable_no.setBounds(850, 585, 425, 65);
        Pclasificacion.add(Lsaludable_no);

        ActionListener EBclasificar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double minimoSodio1 = minimoSodio, maximoSodio1 = maximoSodio;
                double minimoProteina1 = minimoProteina, maximoProteina1 = maximoProteina;
                double minimoCarbohidratos1 = minimoCarbohidratos, maximoCarbohidratos1 = maximoCarbohidratos;
                double minimoFibra1 = minimoFibra, maximoFibra1 = maximoFibra;
                double minimoAzucar1 = minimoAzucar, maximoAzucar1 = maximoAzucar;
                double minimoSaturadas1 = minimoSaturadas, maximoSaturadas1 = maximoSaturadas;
                double minimoMonoinsaturadas1 = minimoMonoinsaturadas, maximoMonoinsaturadas1 = maximoMonoinsaturadas;
                double minimoPolinsaturadas1 = minimoPolinsaturadas, maximoPolinsaturadas1 = maximoPolinsaturadas;
                double minimoTrans1 = minimoTrans, maximoTrans1 = maximoTrans;
                double minimoCalorias1 = minimoCalorias, maximoCalorias1 = maximoCalorias;
                double minimoNatural1 = minimoNatural, maximoNatural1 = maximoNatural;
                System.out.println((double) TFtipoAlimento.getSelectedIndex());
                try {
                    Double calorias = Double.parseDouble(TFcalorias.getText());
                    try {
                        Double proteinas = Double.parseDouble(TFProteinas.getText());
                        try {
                            Double carbohidratos = Double.parseDouble(TFCarbohidratos.getText());
                            try {
                                Double azucar = Double.parseDouble(TFAzucar.getText());
                                try {
                                    Double grasasSaturadas = Double.parseDouble(TFgrasasSaturadas.getText());
                                    try {
                                        Double grasasTrans = Double.parseDouble(TFGrasasTrasn.getText());
                                        try {
                                            Double grasasMonoinsaturadas = Double
                                                    .parseDouble(TFGrasasMonoinsaturadas.getText());
                                            try {
                                                Double grasasPoliinsaturadas = Double
                                                        .parseDouble(TFgrasaPoliinsaturada.getText());
                                                if (TFtipoAlimento.getSelectedIndex() == 0
                                                        || TFtipoAlimento.getSelectedIndex() == 1) {
                                                    if (Double.parseDouble(TFSodio.getText()) > maximoSodio1) {
                                                        maximoSodio1 = Double.parseDouble(TFSodio.getText());
                                                    }
                                                    if (Double.parseDouble(TFProteinas.getText()) > maximoProteina1) {
                                                        maximoProteina1 = Double.parseDouble(TFProteinas.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFCarbohidratos.getText()) > maximoCarbohidratos1) {
                                                        maximoCarbohidratos1 = Double
                                                                .parseDouble(TFCarbohidratos.getText());
                                                    }
                                                    if (Double.parseDouble(TFFibra.getText()) > maximoFibra1) {
                                                        maximoFibra1 = Double.parseDouble(TFFibra.getText());
                                                    }
                                                    if (Double.parseDouble(TFAzucar.getText()) > maximoAzucar1) {
                                                        maximoAzucar1 = Double.parseDouble(TFAzucar.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFgrasasSaturadas.getText()) > maximoSaturadas1) {
                                                        maximoSaturadas1 = Double
                                                                .parseDouble(TFgrasasSaturadas.getText());
                                                    }
                                                    if (Double.parseDouble(TFGrasasMonoinsaturadas
                                                            .getText()) > maximoMonoinsaturadas1) {
                                                        maximoMonoinsaturadas1 = Double
                                                                .parseDouble(TFGrasasMonoinsaturadas.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFgrasaPoliinsaturada.getText()) > maximoPolinsaturadas1) {
                                                        maximoPolinsaturadas1 = Double
                                                                .parseDouble(TFgrasaPoliinsaturada.getText());
                                                    }
                                                    if (Double.parseDouble(TFGrasasTrasn.getText()) > maximoTrans1) {
                                                        maximoTrans1 = Double.parseDouble(TFGrasasTrasn.getText());
                                                    }
                                                    if (Double.parseDouble(TFcalorias.getText()) > maximoCalorias1) {
                                                        maximoCalorias1 = Double.parseDouble(TFcalorias.getText());
                                                    }
                                                    if ((double) TFtipoAlimento.getSelectedIndex() > maximoNatural1) {
                                                        maximoNatural1 = (double) TFtipoAlimento.getSelectedIndex();
                                                    }
                                                    if (Double.parseDouble(TFSodio.getText()) < minimoSodio1) {
                                                        minimoSodio1 = Double.parseDouble(TFSodio.getText());
                                                    }
                                                    if (Double.parseDouble(TFProteinas.getText()) < minimoProteina1) {
                                                        minimoProteina1 = Double.parseDouble(TFProteinas.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFCarbohidratos.getText()) < minimoCarbohidratos1) {
                                                        minimoCarbohidratos1 = Double
                                                                .parseDouble(TFCarbohidratos.getText());
                                                    }
                                                    if (Double.parseDouble(TFFibra.getText()) < minimoFibra1) {
                                                        minimoFibra1 = Double.parseDouble(TFFibra.getText());
                                                    }
                                                    if (Double.parseDouble(TFAzucar.getText()) < minimoAzucar1) {
                                                        minimoAzucar1 = Double.parseDouble(TFAzucar.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFgrasasSaturadas.getText()) < minimoSaturadas1) {
                                                        minimoSaturadas1 = Double
                                                                .parseDouble(TFgrasasSaturadas.getText());
                                                    }
                                                    if (Double.parseDouble(TFGrasasMonoinsaturadas
                                                            .getText()) < minimoMonoinsaturadas1) {
                                                        minimoMonoinsaturadas1 = Double
                                                                .parseDouble(TFGrasasMonoinsaturadas.getText());
                                                    }
                                                    if (Double.parseDouble(
                                                            TFgrasaPoliinsaturada.getText()) < minimoPolinsaturadas1) {
                                                        minimoPolinsaturadas1 = Double
                                                                .parseDouble(TFgrasaPoliinsaturada.getText());
                                                    }
                                                    if (Double.parseDouble(TFGrasasTrasn.getText()) < minimoTrans1) {
                                                        minimoTrans1 = Double.parseDouble(TFGrasasTrasn.getText());
                                                    }
                                                    if (Double.parseDouble(TFcalorias.getText()) < minimoCalorias1) {
                                                        minimoCalorias1 = Double.parseDouble(TFcalorias.getText());
                                                    }
                                                    if ((double) TFtipoAlimento.getSelectedIndex() < minimoNatural1) {
                                                        minimoNatural1 = (double) TFtipoAlimento.getSelectedIndex();
                                                    }
                                                    double[] entradaUsr = { 0, Double.parseDouble(TFSodio.getText()),
                                                            Double.parseDouble(TFProteinas.getText()),
                                                            Double.parseDouble(TFCarbohidratos.getText()),
                                                            Double.parseDouble(TFFibra.getText()),
                                                            Double.parseDouble(TFAzucar.getText()),
                                                            Double.parseDouble(TFgrasasSaturadas.getText()),
                                                            Double.parseDouble(TFGrasasMonoinsaturadas.getText()),
                                                            Double.parseDouble(TFgrasaPoliinsaturada.getText()),
                                                            Double.parseDouble(TFGrasasTrasn.getText()),
                                                            Double.parseDouble(TFcalorias.getText()),
                                                            (double) TFtipoAlimento.getSelectedIndex() };
                                                    entradaUsr[1] = (entradaUsr[1] - minimoSodio1)
                                                            / (maximoSodio1 - minimoSodio1);
                                                    entradaUsr[2] = (entradaUsr[2] - minimoProteina1)
                                                            / (maximoProteina1 - minimoProteina1);
                                                    entradaUsr[3] = (entradaUsr[3] - minimoCarbohidratos1)
                                                            / (maximoCarbohidratos1 - minimoCarbohidratos1);
                                                    entradaUsr[4] = (entradaUsr[4] - minimoFibra1)
                                                            / (maximoFibra1 - minimoFibra1);
                                                    entradaUsr[5] = (entradaUsr[5] - minimoAzucar1)
                                                            / (maximoAzucar1 - minimoAzucar1);
                                                    entradaUsr[6] = (entradaUsr[6] - minimoSaturadas1)
                                                            / (maximoSaturadas1 - minimoSaturadas1);
                                                    entradaUsr[7] = (entradaUsr[7] - minimoMonoinsaturadas1)
                                                            / (maximoMonoinsaturadas1 - minimoMonoinsaturadas1);
                                                    entradaUsr[8] = (entradaUsr[8] - minimoPolinsaturadas1)
                                                            / (maximoPolinsaturadas1 - minimoPolinsaturadas1);
                                                    entradaUsr[9] = (entradaUsr[9] - minimoTrans1)
                                                            / (maximoTrans1 - minimoTrans1);
                                                    entradaUsr[10] = (entradaUsr[10] - minimoCalorias1)
                                                            / (maximoCalorias1 - minimoCalorias1);
                                                    entradaUsr[11] = (entradaUsr[11] - minimoNatural1)
                                                            / (maximoNatural1 - minimoNatural1);
                                                    objP.Procesa(entradaUsr);
                                                    int salida = objP.clasificacion(minimoY, maximoY);
                                                    if (salida == 1) {
                                                        Lsaludable_no.setText("Saludable");
                                                    } else {
                                                        Lsaludable_no.setText("No Saludable");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Debe completar el tipo de alimento con una de las opciones provistas",
                                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    TFtipoAlimento.requestFocus();
                                                }
                                            } catch (java.lang.NumberFormatException event) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Debe completar las grasas poliiinsaturadas con un número",
                                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                                                TFgrasaPoliinsaturada.requestFocus();
                                            }
                                        } catch (java.lang.NumberFormatException event) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Debe completar las grasas monoinsaturadas con un número", "ERROR",
                                                    JOptionPane.ERROR_MESSAGE);
                                            TFGrasasMonoinsaturadas.requestFocus();
                                        }
                                    } catch (java.lang.NumberFormatException event) {
                                        JOptionPane.showMessageDialog(null,
                                                "Debe completar las grasas trans con un número", "ERROR",
                                                JOptionPane.ERROR_MESSAGE);
                                        TFGrasasTrasn.requestFocus();
                                    }
                                } catch (java.lang.NumberFormatException event) {
                                    JOptionPane.showMessageDialog(null,
                                            "Debe completar las grasas saturadas con un número", "ERROR",
                                            JOptionPane.ERROR_MESSAGE);
                                    TFgrasasSaturadas.requestFocus();
                                }
                            } catch (java.lang.NumberFormatException event) {
                                JOptionPane.showMessageDialog(null, "Debe completar los azúcares con un número",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                                TFAzucar.requestFocus();
                            }
                        } catch (java.lang.NumberFormatException event) {
                            JOptionPane.showMessageDialog(null, "Debe completar los carbohidratos con un número",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                            TFCarbohidratos.requestFocus();
                        }
                    } catch (java.lang.NumberFormatException event) {
                        JOptionPane.showMessageDialog(null, "Debe completar las proteínas con un número", "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                        TFProteinas.requestFocus();
                    }
                } catch (java.lang.NumberFormatException event) {
                    JOptionPane.showMessageDialog(null, "Debe completar las calorías con un número", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    TFcalorias.requestFocus();
                }
            }
        };
        ActionListener EBSodio = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "SODIO: Es la cantidad de sal en la porción de alimentos.Valores altos son perjudiciales.Se mide en miligramos",
                        "SODIO", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BSodio.addActionListener(EBSodio);
        ActionListener EBProteinas = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "PROTEÍNA: Este nutriente se usa para desarrollar músculo y combatir infecciones.Se mide en gramos.",
                        "PROTEÍNA", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BProteinas.addActionListener(EBProteinas);
        ActionListener EBCarbohidratos = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "CARBOHIDRATOS: Proporcionan energía a los músculos y al cerebro.Se mide en gramos.",
                        "CARBOHIDRATOS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BCarbohidratos.addActionListener(EBCarbohidratos);
        ActionListener EBFibra = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "FIBRA: Te ayuda con la digestión y te mantiene saciada entre las comidas.Se mide en gramos.",
                        "FIBRA", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BFibra.addActionListener(EBFibra);
        ActionListener EBAzucar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "AZÚCAR: Son importantes para la energía instantánea, pero comer demasiado azúcar agregada puede ser poco saludable y hacerte sentir cansada.Se mide en gramos.",
                        "AZÚCAR", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BAzucar.addActionListener(EBAzucar);
        ActionListener EBgrasasSaturadas = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "GRASAS SATURADAS: Es una de las grasas dañinas.Estas grasas son frecuentemente sólidas a temperatura ambiente.Se mide en gramos.",
                        "GRASAS SATURADAS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BgrasasSaturadas.addActionListener(EBgrasasSaturadas);
        ActionListener EBgrasasMonoinsaturadas = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "GRASAS MONOINSATURADAS: Es una de las grasas saludables.Pueden ayudar a reducir su nivel de colesterol LDL (malo).Se mide en gramos.",
                        "GRASAS MONOINSATURADAS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BGrasasMonoinsaturadas.addActionListener(EBgrasasMonoinsaturadas);
        ActionListener EBgrasasPoliinsaturadas = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "GRASAS POLIINSATURADAS: Es una de las grasas saludables.Pueden ayudar a disminuir el colesterol LDL (malo).Se mide en gramos.",
                        "GRASAS POLIINSATURADAS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BgrasaPoliinsaturada.addActionListener(EBgrasasPoliinsaturadas);
        ActionListener EBgrasasTrans = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "GRASAS TRANS: Estas no son saludables para tu corazón y debes evitarlas.Se mide en miligramos.",
                        "GRASAS TRANS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BGrasasTrasn.addActionListener(EBgrasasTrans);
        ActionListener EBTipoAlimento = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "TIPO ALIMENTO: Puede ser natural o procesado.\nLos alimentos procesados son aquellos que han soportado cambios o han pasado por algun grado de procesamiento industrial antes de llegar a nuestra mesa para que los podamos consumir.\nAlimento natural, se le pone a aquellos a los cuáles no se les ha añadido ningún componente, es decir, no se les ha adicionado industrialmente sal, azúcar, grasas u otros componentes y tampoco han recibido un tratamiento industrial.",
                        "TIPO ALIMENTO", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        BTipoAlimento.addActionListener(EBTipoAlimento);
        ActionListener EBCalorias = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "CALORIAS: Son una unidad de energía que proviene de los carbohidratos, las proteínas y las grasas. Las calorías nos dan energía para que podamos pensar y estar activos.",
                        "CALORIAS", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        Bcalorias.addActionListener(EBCalorias);
        Bclasificar.addActionListener(EBclasificar);
        Pprincipal.add(Pclasificacion);
        add(Pprincipal);
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
    public void Muestra(double[] E, double[] S, double minimoX, double maximoX, double minimoY, double maximoY) {
        System.out.print(E[1] * (maximoX - minimoX) + minimoX);
        System.out.print(" ===> ");
        System.out.print(S[1] * (maximoY - minimoY) + minimoY);
        System.out.print(" <vs> ");
        System.out.print(A[TotalCapas][1] * (maximoY - minimoY) + minimoY); // Salidas reales del perceptrón

        int salidaEntera;
        if ((A[TotalCapas][1] * (maximoY - minimoY) + minimoY) < 0.5) {
            salidaEntera = 0;
        } else {
            salidaEntera = 1;
        }
        if (salidaEntera != S[1]) {
            System.out.print("     MAL");
        }
        System.out.println();
    }

    public int clasificacion(double minimoY, double maximoY) {
        if ((A[TotalCapas][1] * (maximoY - minimoY) + minimoY) < 0.5) {
            return 0;
        }
        return 1;
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