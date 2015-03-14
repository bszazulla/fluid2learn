package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.impl.DirecaoMaze;

public class EnquirerMaze implements IEnquirer 
{

	IResponder responder;
	
	public void connect(IResponder responder) 
	{
		this.responder = responder;
	}
	
	public boolean discover() 
	{
		// inicia com pergunta
		String acao = "P";
		// de onde veio
		String dirUltimo;
		
		// pilha 
		Stack<DirecaoMaze> pilha = new Stack<DirecaoMaze>();
		
		while (!acao.equalsIgnoreCase("F")) 
		{
			// objeto que guarda as informacoes do estado atual
			DirecaoMaze atual = new DirecaoMaze("null");
			
			// ateh encontrar a saida
			String resposta;
			do
			{
				resposta = responder.ask(atual.getDirecao()); // pergunta ao responder
				
				if (resposta == "passagem" || resposta == "saida") 
				{
					responder.move(atual.getDirecao());
					
					if (!pilha.empty())
					{
						DirecaoMaze aux = pilha.peek(); // ultimo local passado
						dirUltimo = aux.getDirecao(); // captura a direcao do ultimo local passado para usar em veioDe						
					}
					else
						dirUltimo = "null"; // estah na entrada do labirinto e pilha vazia
					
					pilha.push(atual); // coloca na pilha
					System.out.println("Movimento para " + atual.getDirecao());
			
					atual = new DirecaoMaze(dirUltimo); // novo objeto apontado por atual = nova tentativa de movimento
				} 
				else
				{
					atual.mudaDirecao(); // muda a tentativa de movimento
					
					// se só sobra voltar de onde veio agora (caminho fechado) - aqui deve estar o problema
					if(atual.voltando()) // atual voltando eh um metodo que testa o veioDe, se eh igual a pra onde vai (direção)
					{
						
						pilha.pop(); // retira da pilha			
						
						// volta a apontar atual para o que estah no topo da pilha (ele eh capaz de fazer isso em java?)
						atual = pilha.peek(); //toma a ultima direção, apontando para o que estah no topo da pilha
						atual.mudaDirecao(); //e jah muda, pq pela direção atual sabe-se que não dah
					}	
				}
			} while (resposta != "saida");
			
			
			// pergunta se estah na saida e pode terminar o programa
			if (responder.ask("aqui") == "saida") 
			{
				acao = "F";
			}
			
		}
		
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		
		return true;
	}
	
}
