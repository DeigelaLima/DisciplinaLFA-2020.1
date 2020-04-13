public class DFASimples {// STATIC -> variaveis static pq vou acessar dentro de um contexto static(void main) 
    static int[] aceitacao = {1};

    //estados = 0,1,2,....
    //alfabeto = 0,1

    static int[][] trasicao == {{0,1},{1,0}};
    static int estadoInicial = 0;

	public static void main(String[] args) {
		
	/*
	 * DFA - cadeias com número ímpar de 1s
	 */
		String entrada = args[0];
		int estado = estadoInicial;
		int posicao = 0;
		//entrada = args[0];

		// caharAt -> consiste em retornar apenas um caractere
		//em determinada posição de nossa String.
		//System.out.println("Ola" + args[0]);

		while(posicao < entrada.length()) {
            imprimeCI(entrada, estado, posicao);

			char elemento = entrada.charAt(posicao);

            if(estado == 0 && elemento == '0'){
                estado = 0;
            }else if(estado == 0 && elemento == '1'){
                estado = 1;
            }else if(estado == 1 && elemento == '0'){
                estado = 1;
            }else if(estado == 1 && elemento == '1'){
                estado = 0;
            }
			
	        //System.out.println(posicao+": " +elemento);										
			posicao ++;
		}
        imprimeCI(entrada, estado, posicao);//imprime a ultima CI

        if(estado == 1){
            System.out.println("Aceita!");
        }else if(estado == 0){
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
