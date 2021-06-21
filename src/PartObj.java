import java.io.Serializable;
import java.util.HashMap;

public class PartObj implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer codigoPeca;
	private String nomePeca;
	private String descricaoPeca;
	private HashMap<PartObj, Integer> pecas;
	
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

		

}
