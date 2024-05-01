import java.security.KeyStore;
import java.util.Scanner;

public class Main {

    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static Scanner teclado=new Scanner(System.in);

    public static int [][]crearTablero(){
        int[][]matriz=new int[6][7];
        for (int i=0; i< matriz.length;i++){
            for(int j=0;j< matriz[0].length;j++){
                matriz[i][j]=0;
            }
        }
        return matriz;
    }

    public static void pintarTablero(int[][]matriz,char[]lista_letras){
        for(int i=0;i<= matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                if(i==matriz.length){
                    System.out.print(ANSI_WHITE+" "+ lista_letras[j]+" "+ANSI_RESET+" ");
                }else if (matriz[i][j]==0){
                    System.out.print(ANSI_WHITE_BACKGROUND+"   "+ANSI_RESET+" ");
                }else if(matriz[i][j]==1){
                    System.out.print(ANSI_RED_BACKGROUND+" "+ANSI_BLACK+"1"+" "+ANSI_RESET+" ");
                }else if(matriz[i][j]==2) {
                    System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_BLACK + "2" +" "+ANSI_RESET+ " ") ;
                }
            }System.out.println("\n");
        }
    }

    public static int[][] insertarFicha(int[][]matriz,int jugador,char[]lista_letras) {
        System.out.println("===>JUGADOR " + jugador);
        System.out.println("Introduce una columna: ");
        int columna=0;
        char letra = teclado.next().charAt(0);
        while (letra < 97 || letra > 103) {
            System.out.println("¡¡¡COLUMNA ERRONEA!!! ");
            System.out.println("===>JUGADOR " + jugador);
            System.out.println("Introduce una columna: ");
            letra = teclado.next().charAt(0);
        }
        if (letra == 97) {
            columna = 0;
        } else if (letra == 98) {
            columna = 1;
        } else if (letra == 99) {
            columna = 2;
        } else if (letra == 100) {
            columna = 3;
        } else if (letra == 101) {
            columna = 4;
        } else if (letra == 102) {
            columna = 5;
        } else {
            columna = 6;
            System.out.println(letra);
        }

        for (int j = matriz.length-1; j>=0; j--) {
                    if(matriz[j][columna]==0){
                        matriz[j][columna]=jugador;
                        break;
                    }else{
                        continue;
                    }
                }


        pintarTablero(matriz,lista_letras);
        return matriz;
        }

        public static boolean comprobrarHorizontalVertical(int [][]matriz){
            for(int fila=0;fila< matriz.length;fila++){
                for(int columna=0;columna< matriz[0].length;columna++){
                    if(columna<4){
                        int num=matriz[fila][columna];
                        int num1=matriz[fila][columna+1];
                        int num2=matriz[fila][columna+2];
                        int num3=matriz[fila][columna+3];
                        if(num!=0&&num==num1&&num==num2&&num==num3){
                            return true;
                        }
                    }
                    if(fila<3){
                        int num_v=matriz[fila][columna];
                        int num1_v=matriz[fila+1][columna];
                        int num2_v=matriz[fila+2][columna];
                        int num3_v=matriz[fila+3][columna];
                        if(num_v!=0&&num_v==num1_v&&num_v==num2_v&&num_v==num3_v){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public static boolean comprobarDiagonales(int [][]matriz){

            for(int filas = 3; filas < matriz.length; filas++){
                for(int columnas = 0; columnas < matriz[0].length - 3; columnas++){
                    if (matriz[filas][columnas] != 0 && matriz[filas][columnas] == matriz[filas-1][columnas+1] && matriz[filas-1][columnas+1] == matriz[filas-2][columnas+2] && matriz[filas-2][columnas+2] == matriz[filas-3][columnas+3]) {
                        System.out.println("Diagonal_1");
                        return true;
                    }
                }
            }
            for(int fila = 0; fila < matriz.length - 3; fila++){
                for(int columna = 0; columna < matriz[0].length - 3; columna++){
                    if (matriz[fila][columna] != 0 && matriz[fila][columna] == matriz[fila+1][columna+1] && matriz[fila+1][columna+1] == matriz[fila+2][columna+2] && matriz[fila+2][columna+2] == matriz[fila+3][columna+3]) {
                        System.out.println("Diagonal_2");
                        return true;
                    }
                }
            }
            return false;
        }





        public static void main (String[]args){

            char[] lista_letras ={'A','B','C','D','E','F','G'};
            String continuar="";
            while(true){
                int[][] matriz = crearTablero();//new int[][]{{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{0,0,0,0,0,0,0}};
                System.out.println("========CONECTA4=======");
                System.out.println("\n");
                pintarTablero(matriz,lista_letras);
                while(true){
                        int jugador=1;
                        insertarFicha(matriz,jugador,lista_letras);
                        if(comprobrarHorizontalVertical(matriz)||comprobarDiagonales(matriz)){
                            System.out.println("Gana el jugador "+jugador);
                            break;
                        }
                        jugador=2;
                        insertarFicha(matriz,jugador,lista_letras);
                        if(comprobrarHorizontalVertical(matriz)||comprobarDiagonales(matriz)){
                            System.out.println("Gana el jugador "+jugador);
                            break;
                        }
                }
                System.out.println("Pulse ENTER para continuar: ");
                teclado.nextLine();
                String continuar1= teclado.nextLine();
            }
    }
}