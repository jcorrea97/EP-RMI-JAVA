import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
	
	private static Registry serverCorrente;
	private static PartObj pecaCorrente;
	private static HashMap<PartObj, Integer> subPecasCorrente;
	private static int codigoBuscado;
	
	public static void main(String[] args) {
		try {
			String ip = (args.length < 1) ? null : args[0];
			serverCorrente = LocateRegistry.getRegistry(ip);
			String[] serversOn = serverCorrente.list();
			
			System.out.println("Servers rodando:\n");
			for(int i=0; i < serversOn.length; i++)
				System.out.println("Server "+ serversOn[i]);

			System.out.print("\nEscolha o servidor para entrar: ");
			Scanner scan = new Scanner(System.in);
			String serverEscolhido = scan.next();
			//PartRepository c = (PartRepository) Naming.lookup("rmi://192.168.15.140:1099/" + host);
			
			PartRepository c = (PartRepository) serverCorrente.lookup(serverEscolhido);
			
			int comandoEscolhido = 0;
			System.out.println("\nBem vindo(a) ao servidor " + serverEscolhido + "!!!");
			while(true) {
				System.out.println("\n-------------------MENU------------------------");
				System.out.println("1. Mudar de servidor (bind)....................");
				System.out.println("2. Listar pecas (listp)........................");
				System.out.println("3. Buscar peca por codigo (getp)...............");
				System.out.println("4. Mostrar atributos da peca corrente (showp)..");
				System.out.println("5. Limpar subpecas correntes (clearlist).......");
				System.out.println("6. Adicionar subpeca (addsubpart)..............");
				System.out.println("7. Adicionar peca (addp).......................");
				System.out.println("8. Encerrar sessao (quit)......................");
				System.out.println("-----------------------------------------------");
				
				System.out.print("\nEscolha o numero de acordo com a acao desejada: ");

				if (scan.hasNextInt()) comandoEscolhido = scan.nextInt();
				else System.out.println("Selecione um numero.\n");
				
				switch(comandoEscolhido){
				case 1:
					serversOn = serverCorrente.list();
					
					System.out.println("Outros servidores disponiveis:\n");
					for(int i=0; i < serversOn.length; i++)
						if (serversOn[i]!=serverEscolhido)
							System.out.println("Server "+ serversOn[i]);

					System.out.print("\nEscolha o servidor para entrar: ");
					serverEscolhido = scan.next();
					c.bind(serverEscolhido);
					System.out.println("\nBem vindo(a) ao servidor " + serverEscolhido + "!!!");
					break;
					
				case 2:
		        	c.listp();
		            break;
		            
				case 3:
					System.out.print("Digite o codigo da peca a ser buscada: ");
					codigoBuscado = scan.nextInt();
					pecaCorrente = c.getp(codigoBuscado);
					subPecasCorrente = pecaCorrente.getPecas();
					break;
				
				case 4:
					c.showp(pecaCorrente);
					break;
					
				case 5:
					c.clearList(subPecasCorrente);
		            break;
		        
				case 6:
					System.out.print("Digite a quantidade da peca corrente a ser adicionada na lista de subpecas: ");
					int qtdPecas = scan.nextInt();

					if(subPecasCorrente == null){

						//subPecasCorrente = new HashMap<PartObj, Integer>();
						PartObj newSubPart = new PartObj();
			        	
			        	System.out.print("Codigo da nova subpeca: ");
			    		String codigoEscolhido = scan.next();
			    		int intCodigo = Integer.parseInt(codigoEscolhido);
			    		newSubPart.setCodigoPeca(intCodigo);
			    		
			    		System.out.print("Descricao da nova subpeca: ");
			    		String descricaoEscolhida = scan.next();
			    		newSubPart.setDescricaoPeca(descricaoEscolhida);
			    		
			    		System.out.print("Nome da nova subpeca: ");
			    		String nomeEscolhido = scan.next();
			    		newSubPart.setNomePeca(nomeEscolhido);

						//subPecasCorrente.put(newSubPart,qtdPecas); 

						c.addsubpart(newSubPart, pecaCorrente, qtdPecas);
						System.out.println(qtdPecas + " sub pecas adiconadas a " + pecaCorrente.getNomePeca());

					
						subPecasCorrente = pecaCorrente.getPecas();
					}
					break;
					
		        case 7:
		        	PartObj newPart = new PartObj();
		        	
		        	System.out.print("Codigo da nova peca: ");
		    		String codigoEscolhido = scan.next();
		    		int intCodigo = Integer.parseInt(codigoEscolhido);
		    		newPart.setCodigoPeca(intCodigo);
		    		
		    		System.out.print("Descricao da nova peca: ");
		    		String descricaoEscolhida = scan.next();
		    		newPart.setDescricaoPeca(descricaoEscolhida);
		    		
		    		System.out.print("Nome da nova peca: ");
		    		String nomeEscolhido = scan.next();
		    		newPart.setNomePeca(nomeEscolhido);
		    		
		    		c.addp(newPart);
		    		newPart.setPecas(subPecasCorrente);		    		
		            break;
	
		        case 8:
		        	System.out.print("Qual servidor vc quer encerrar?");
		    		String servidorEscolhido = scan.next();
		            c.quit(servidorEscolhido);
		            scan.close();
		            break;
		            
		        default:
		        	System.out.print("Comando invalido. Insira um numero de 1 a 8.\n");
				}
			
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
