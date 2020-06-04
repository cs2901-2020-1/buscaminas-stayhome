import java.util.*;
import java.lang.Math;
import java.util.Scanner;
import java.io.*;

public class Busca_Minas {
    public static final int N = 5;
    static int r;
    static char[][] tablero_imprimir = new char[N][N];
    static char[][] tablero_contenido = new char[N][N];
    static int minas=N;

    static boolean valido(int i,int j){
        if(i>=0 && j>=0 && N>i && N>j)
            return true;
        return false;
    }

    static void poner_numero(int i,int j){
        for(int k=i-1;k<=i+1;k++){
            for(int l=j-1;l<=j+1;l++){
                if(valido(k,l)){
                    if(tablero_contenido[k][l]!='*'){

                        int sum=(int)(tablero_contenido[k][l])-48;
                        sum++;
                        char a=(char)(sum+48);
                        tablero_contenido[k][l]=a;
                    }
                }
            }
        }

    }

    static void poner_minas(){
        int minas_puestas=0;

        while(minas_puestas<minas){
            int x = (int)Math.floor((Math.random()*N+0));
            int y = (int)Math.floor((Math.random()*N+0));
            if(tablero_contenido[x][y]!='*'){
                tablero_contenido[x][y]='*';
                poner_numero(x,y);
                minas_puestas++;
            }
        }
    }
    static void print_game() {
        System.out.print("CONTENIDO \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero_imprimir[i][j]);
                System.out.print(" ");

            }
            System.out.print("\n");
        }
    }


    static void print_tablero() {
        System.out.print("CONTENIDO \n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero_contenido[i][j]);
                System.out.print(" ");

            }
            System.out.print("\n");
        }

        System.out.print("JUGAR \n");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero_imprimir[i][j]);
                System.out.print(" ");

            }
            System.out.print("\n");
        }
    }

    static void iniciar(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero_contenido[i][j]='0';
            }

        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero_imprimir[i][j]='-';
            }

        }
        poner_minas();
    }

    static void jugar(){
        Scanner s = new Scanner(System.in);
        boolean win=true;
        int x;
        int y;
        while(r<N*N-N){
            System.out.print("Game start \n");
            print_game();
            System.out.print("Inserte cordenada x \n");
            x = s.nextInt();
            System.out.print("Inserte cordenada y \n");
            y = s.nextInt();

            if(tablero_contenido[x][y]=='*'){
                win=false;
                System.out.print("Perdio \n");
                break;

            }
            revelar(x,y);
        }
        if(win)
            System.out.print("Game win \n");
    }

    static void revelar(int i,int j){
        if(tablero_contenido[i][j]=='0'){

            for(int k=i-1;k<=i+1;k++){
                for(int l=j-1;l<=j+1;l++){
                    if(valido(k,l)){
                        if(k==i && l==j){
                            r++;
                            tablero_imprimir[k][l]=tablero_contenido[k][l];
                        }
                        else{
                            if(tablero_imprimir[k][l]=='-'){
                                tablero_imprimir[k][l]=tablero_contenido[k][l];
                                revelar(k,l);
                            }
                        }
                    }
                }
            }
        }else if(tablero_contenido[i][j]!='*'){
            r++;
            tablero_imprimir[i][j]=tablero_contenido[i][j];
        }

    }

    public static void main(String[] args) {
        r=0;
        iniciar();
        jugar();
    }
}
