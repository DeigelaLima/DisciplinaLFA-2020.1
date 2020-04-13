public class DFAGenerico {// STATIC -> variaveis static pq vou acessar dentro de um contexto static(void main) 
    static int[] aceitacao = {1};

    //estados = 0,1,2,....
    //alfabeto = 0,1

    static int[][] transicao = {{0,1},{1,0}};
    static int estadoInicial = 0;

	public static void main(String[] args) {
		
	/*
	 * DFA - cadeias com número ímpar de 1s
	 */
		String entrada = args[0];
		int estado = estadoInicial;
		int posicao = 0;

		// caharAt -> consiste em retornar apenas um caractere
		//em determinada posição de nossa String.

		while(posicao < entrada.length()) {
            imprimeCI(entrada, estado, posicao);

			char elemento = entrada.charAt(posicao);

            int elementoInt = Integer.parseInt(elemento+"");
			
            estado = transicao[estado][elementoInt];
										
			posicao ++;
		}
        imprimeCI(entrada, estado, posicao);//imprime a ultima CI

        boolean aceita = false;
        for(int i:aceitacao){
            if(estado == i) aceita = true;
        }
        if(aceita){
            System.out.println("Aceita!");
        }else{
            System.out.println("Rejeita!");
        }
	}
    //Configuração Instantanea - CI
    public static void imprimeCI(String entrada, int estado, int posicao){

         //[q0]00101
         //0[q0]0101
         //00[q0]101
         //001[q1]01

        //substring pega uma subcadeia
         System.out.print(entrada.substring(0, posicao));
         System.out.print("[q"+estado+"]");
         System.out.println(entrada.substring(posicao));
    }
}
