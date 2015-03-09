package pt.c01interfaces.s01knowledge.s02app.actors;

import java.util.ArrayList;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.impl.Pergunta;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer 
{
        IObjetoConhecimento obj;

        @Override
        public void connect(IResponder responder) 
        {

                IBaseConhecimento bc = new BaseConhecimento();
                String listaAnimais[] = bc.listaNomes();
                String atual = null;

                // Cria teste para as perguntas e respostas j� feitas
                ArrayList<Pergunta> perguntasFeitas = new ArrayList<Pergunta>();
                
                // executa as perguntas para todos os animais
                for (int animal = 0; animal < listaAnimais.length; animal++) 
                {

                        atual = listaAnimais[animal];
                        obj = bc.recuperaObjeto(atual);
                        IDeclaracao decl = obj.primeira();

                        boolean animalEsperado = true;

                        // pergunta enquanto h� perguntas e afirma��es corretas
                        while (decl != null && animalEsperado) 
                        {
                            boolean repetida = false;
                            
                            String pergunta = decl.getPropriedade();
                            String respostaEsperada = decl.getValor();
                            
                            // verifica se a pergunta j� existe no array (.size pega o tamanho do array)
                            for(int i = 0; i < perguntasFeitas.size(); i++)
                            {
                            	Pergunta aux = perguntasFeitas.get(i);
                            	
                            	// perguntas feitas na posi��o i, no campo pergunta (getPergunta retorna string)
                            	if(pergunta.equalsIgnoreCase(aux.getPergunta()))
                            	{
                            		repetida = true;
                            		
                            		// se a resposta n�o for a esperada, eu j� sei que n�o � esse animal
                            		if (!(aux.getResposta().equalsIgnoreCase(respostaEsperada)))
                            		{
                                        animalEsperado = false;
                            		}
                            		
                            		break;
                            	}
                            		
                            }
                            
                            // compara��o das pergunta e resposta caso a pergunta n�o seja repetida                               
                            if (!repetida) 
                            {
                                // pergunta ao responder
                                String resposta = responder.ask(pergunta);
                                
                                // cria��o de perguntaAtual para adicion�-la ao arraylist
                                Pergunta perguntaAtual = new Pergunta(pergunta, resposta);
                                
                                // adicionando perguntaAtual ao array de perguntas feitas
                                perguntasFeitas.add(perguntaAtual);
                                
                                // testando se a resposta � igual a esperada
                                if (resposta.equalsIgnoreCase(respostaEsperada))
                                    decl = obj.proxima();
                                else
                                    animalEsperado = false;
                                
                            } 
                            else
                                decl = obj.proxima();
                    }

                    // para se achou o animal
                    if (animalEsperado)
                    	break;
                }
                               
                boolean acertei = responder.finalAnswer(atual);

                if (acertei)
                    System.out.println("Oba! Acertei!");
                else
                    System.out.println("fuem! fuem! fuem!");

        }

}