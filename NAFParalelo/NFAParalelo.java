import java.util.Set;
import java.util.TreeSet;


public class NFAParalelo{
    //Codificação do automato
    static int[] aceitacao = {2};
    static int estadoInicial = 0;
    //estados 0,1,2,....
    //alfabeto 0,1
    
    /*Busca por Largura/Paralelo
     *{q0}101
     *1{q0}01
     *10{q0,q1}1
     *101{q0,q2}
    */

    //TABELA do NFA
    static int[][][] transicao = {{{0,1},{0}},
                                 {{},{2}}, 
                                 {{},{}}};

    public static void main(String args[]){
        /*
        * NFA - Cadeias que terminam em 01.
        */
        String entrada = args[0];
        int posicao = 0;
        int[] estados = {estadoInicial};

        while(posicao < entrada.length()){
            imprimeCI(entrada, estados, posicao);
            int elemento = Integer.parseInt(entrada.substring(posicao, posicao + 1));

            int[] novosEstados = new int[] {};

            for(int i: estados){
                int[] destinoTransicao = transicao[i][elemento];//para cada estado atual pego a trasicao retorna um conjuntos possiveis de transicoes
                novosEstados = uniao(novosEstados, destinoTransicao); //vai acumulando os estados
            }
            estados = novosEstados;
            if(estados.length == 0){
                break;
            }

            posicao++;
        }

        imprimeCI(entrada, estados, posicao);

        if(aceita(estados)){
            System.out.println("Aceita!");
        }else{
            System.out.println("Regeita!");
        }
    }   
    
    private static int[] uniao(int[] estados, int[] novosEstados){
        Set<Integer> uniao = new TreeSet<>();//SET-> implementa uniao sem repeticao
        for(int i:estados) uniao.add(i);
        for(int i:novosEstados) uniao.add(i);

        //Convertendo para Array
        int[] ret= new int[uniao.size()];
        int j = 0;
        for(int i:uniao) ret[j++] = i;
        return ret;
    }

    private static boolean aceita(int[] estados){
        if(estados == null) return false;// ausencia de estados
        for (int i:estados){
            for(int j:aceitacao){
                if(i == j){
                    return true;
                }
            }
        }
        return false;
    }

    private static void imprimeCI(String entrada, int[] estados, int posicao){
        System.out.print(entrada.substring(0, posicao)+"{");

        for(int i = 0; i < estados.length; i++){
            System.out.print("q"+estados[i]);
            if(i < estados.length - 1){//nao imprime a virgula depois do ultimo elemento
                System.out.print(",");

            }
        }

        System.out.println("}"+entrada.substring(posicao));

    }

}