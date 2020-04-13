public class NFABacktracking{
    //Codificação do automato
    static int[] aceitacao = {2};
    static int estadoInicial = 0;
    //estados 0,1,2,....
    //alfabeto 0,1
    
    //TABELA do NFA
    static int[][][] transicao = {{{0,1},{0}},
                                 {{},{2}}, 
                                 {{},{}}};

    public static void main(String args[]){
        /*
        * NFA - Cadeias que terminam em 01.
        */
        String entrada = args[0];

        int[] estados = {estadoInicial};

        int[] estadosFinais = testa(entrada, estados, 0);//0 eh a posicao

        if(aceita(estadosFinais)){
            System.out.println("Aceita!");
        }else{
            System.out.println("Regeita!");
        }
    }   
    private static int[] testa(String entrada, int[] estados, int posicao){
        if(posicao == entrada.length()){//chegou no final
            imprimeCI(entrada, estados[0], posicao);//Só para mostrar a última CI antes do backtrack
            if(aceita(estados)){
                return estados;
            }
            System.out.println("<<Backtrack>> Fim da cadeia!");
            return null;
        }
        int elemento = Integer.parseInt(entrada.substring(posicao, posicao + 1));
        for(int i:estados){//pecorre todos os estados possiveis de serem testados
            imprimeCI(entrada, i, posicao);
            int[] novosEstados = transicao[i][elemento];//retorna todos os estados possiveis(novoEstados)
            if(novosEstados.length == 0){
                // imprimeCI(entrada, novosEstados[0], posicao);
                System.out.println("<<backtrack>> Sem opções de transições!");
                return null;
            }
            int[] transicoes = testa(entrada, novosEstados, posicao + 1);//incremento a possicao
            if(transicoes != null) return transicoes;
        }
        return null;//acabaram as posicoes
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

    private static void imprimeCI(String entrada, int estado, int posicao){
        System.out.print(entrada.substring(0, posicao));
        System.out.print("[q"+estado+"]");
        System.out.println(entrada.substring(posicao));

    }

}