import org.omg.CORBA.MARSHAL;

import java.util.Scanner;

/**
 * Created by oriol on 20/10/15.
 */
public class Tester {

    public int matrix[][] = {{8,9,10,11,12,17},{7,5,4,13,16,18},{6,31,3,14,15,19},
            {30,32,2,35,36,20},{29,33,34,1,22,21},{28,27,26,25,24,23}};

    public int matrix_buida[][] = {{0,0,0,11,12,17},{7,5,4,0,0,18},{6,0,3,14,0,0},
            {30,0,0,35,0,20},{29,33,34,0,0,21},{28,27,26,25,0,0}};

    public int matrix_omplir[][] = {{0,0,0,11,12,17},{7,5,4,0,0,18},{6,0,3,14,0,0},
            {30,0,0,35,0,20},{29,33,34,0,0,21},{28,27,26,25,0,0}};

    public int vec_pos[] = {0,0,1,1,1, 1,1,0,0,0, 1,1,0,1,0, 0,1,1,0,1, 1,0,0,0,1, 1,1,1,1,1,  0,0,1,1,1};

    public int vec_col[] = {0,0,1,1,1, 1,1,0,0,0, 1,1,0,1,0, 0,1,1,0,1, 1,0,0,0,1, 1,1,1,1,1,  0,0,1,1,1};

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BARRA = "\u2014";


    public void pintar_hidato(int m){
        for(int i = 0; i < 19; ++i) System.out.print("_");
        System.out.print("\n");
        for (int i = 0; i < 6; ++i){
            System.out.print("|");
            for (int j = 0; j < 6; ++j) {
                if(m == 1){
                    if (matrix[i][j] - 10 < 0) System.out.print(" ");
                    if(matrix_buida[i][j] != 0)System.out.print(ANSI_RED + matrix[i][j] + ANSI_RESET);
                    else System.out.print(matrix[i][j]);
                }
                else{
                    if (matrix_omplir[i][j] - 10 < 0) System.out.print(" ");
                    if(matrix_buida[i][j] != 0)System.out.print(ANSI_RED+ matrix[i][j] + ANSI_RESET);
                    else{
                        if(matrix_omplir[i][j] != 0) System.out.print(matrix_omplir[i][j]);
                        else System.out.print(" ");
                    }
                }
                System.out.print("|");
            }
            System.out.print("\n");

        }
        System.out.print("+");
        for(int i = 1; i < 18; ++i) System.out.print(ANSI_BARRA);
        System.out.print("+\n");
    }

    public void matrix_clear(){
        for (int i = 0; i < matrix_omplir.length; ++i){
            for (int j = 0; j < matrix_omplir[0].length; ++j){
                matrix_omplir[i][j] = matrix_buida[i][j];
            }
        }
    }

    public void vector_clear(){
        for(int i = 0; i < vec_col.length; ++i){
            vec_col[i] = vec_pos[i];
        }
    }

    public void afegir_casella(int x, int y, int n){
        matrix_omplir[x-1][y-1] = n;
    }

    public void reset_casella(int x, int y){
        matrix_omplir[x-1][y-1] = 0;
    }

    public boolean valor_posat(int n){
        for(int i = 0; i < 6; ++i){
            for(int j = 0; j < 6; ++j){
                if (matrix_omplir[i][j] == n) return true;
            }
        }
        return false;
    }

    public void help(){
        for (int i = 0; i < vec_col.length; ++i){
            if (vec_col[i] == 0){
                for (int j = 0; j < matrix.length; ++j){
                    for (int k = 0; k < matrix[0].length; ++k) {
                        if (matrix[j][k] == i+1) {
                            System.out.println("Jo de tu posaria el numero " + (i+1) + " a les coordenades:\nX: " + (j+1)
                                    + "\n" + "Y: " + (k+1));
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Tester t = new Tester();

        System.out.print("Benvinguts al Tester\n");
        while(true) {
            System.out.print("Introdueix una comanda:");
            Scanner capt = new Scanner(System.in);
            String s = capt.nextLine();
            if (s.equals("x")){
                break;
            }
            if (s.equals("man")) {
                System.out.print("Menu de ajuda:\n");
                System.out.print("p: Imprimeix Hidato\n");
                System.out.print("x: Sortir del Tester\n");
            }
            if (s.equals("psol")){
                System.out.print("Hidato complert:\n");
                t.pintar_hidato(1);
            }

            if (s.equals("p")){
                System.out.print("Hidato actual:\n");
                t.pintar_hidato(2);
            }

            if (s.equals("hidato play")){
                System.out.println("ha comensat una nova partida:");
                t.matrix_clear();
                t.vector_clear();
                t.pintar_hidato(2);

                while (true){
                    s = capt.nextLine();
                    if (s.equals("add")){
                        System.out.println("introdueix X:");
                        int x = capt.nextInt();
                        while(x < 1 | x > 6){
                            System.out.println("X no valida, introdueix una altra");
                            x = capt.nextInt();
                        }

                        System.out.println("introdueix Y:");
                        int y = capt.nextInt();
                        while(y < 1 | y > 6){
                            System.out.println("Y no valida, introdueix una altra");
                            y = capt.nextInt();
                        }
                        System.out.println("introdueix valor:");
                        int n = capt.nextInt();
                        while(t.valor_posat(n)){
                            System.out.println("Valor ja assignat");
                            n = capt.nextInt();
                        }
                        t.afegir_casella(x, y, n);
                        t.pintar_hidato(2);
                        t.vec_col[n-1] = 1;
                    }

                    if (s.equals("rm")){
                        System.out.println("introdueix X:");
                        int x = capt.nextInt();
                        while(x < 1 | x > 6){
                            System.out.println("X no valida, introdueix una altra");
                            x = capt.nextInt();
                        }

                        System.out.println("introdueix Y:");
                        int y = capt.nextInt();
                        while(y < 1 | y > 6){
                            System.out.println("Y no valida, introdueix una altra");
                            y = capt.nextInt();
                        }
                        System.out.println("Eliminat");
                        t.vec_col[t.matrix[x-1][y-1] -1] = 0;
                        t.reset_casella(x, y);
                        t.pintar_hidato(2);
                    }

                    if (s.equals("help")){
                        t.help();
                    }
                    if (s.equals("stop")) {
                        System.out.println("Partida abandonada");
                        break;
                    }
                }
            }



            else {
                System.out.print("Comanda no valida\n");
            }

        }
    }


}
