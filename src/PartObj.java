import java.io.Serializable;
import java.util.HashMap;
import java.util.Arrays;

public class PartObj implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer codigoPeca;
	private String nomePeca;
	private String descricaoPeca;
	public HashMap<PartObj, Integer> pecas;
	private int contador = 0;
	
	public Integer getCodigoPeca() {
		return codigoPeca;
	}
	public void setCodigoPeca(Integer codigoPeca) {
		this.codigoPeca = codigoPeca;
	}
	public String getNomePeca() {
		return nomePeca;
	}
	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}
	public String getDescricaoPeca() {
		return descricaoPeca;
	}
	public void setDescricaoPeca(String descricaoPeca) {
		this.descricaoPeca = descricaoPeca;
	}
	public HashMap<PartObj, Integer> getPecas() {
		return pecas;
	}
	public void setPecas(HashMap<PartObj, Integer> pecas) {
		this.pecas = pecas;
	}
	public void addSubPecas(PartObj newSubPart, Integer codigoSubPart) {
		if(contador == 0){
			pecas = new HashMap<PartObj, Integer>();
			contador = 1;
		}

		pecas.put(newSubPart, codigoSubPart);

		//System.out.println(newSubPart.getNomePeca());
		//pecas.forEach((key, value) -> System.out.println(key.getNomePeca() + " " + value));

	}

		

	// public static void main(String[] args){


	// 	PartObj newSubPart = new PartObj();
			        	
	// 	int intCodigo = 123;
	// 	newSubPart.setCodigoPeca(intCodigo);
		

	// 	String descricaoEscolhida = "teste";
	// 	newSubPart.setDescricaoPeca(descricaoEscolhida);
		
	
	// 	String nomeEscolhido = "teste";
	// 	newSubPart.setNomePeca(nomeEscolhido);


	// //	System.out.println(newSubPart.getNomePeca());


	// 	addSubPecas(newSubPart, 3);
	// }

}
