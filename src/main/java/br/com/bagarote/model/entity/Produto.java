package br.com.bagarote.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "SequenceProduto", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceProduto")
	@EqualsAndHashCode.Include
	private Long idProduto;
	private String produto;
	private String descricao;
	private BigDecimal valorBase;
	private byte[] imagemProduto;
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA" ,referencedColumnName="id_empresa")
	private Empresa empresa;

	@Transient
	public String getImagemProdutoBase64() {
		if(Objects.nonNull(this.imagemProduto)) {
			return Base64.getEncoder().encodeToString(this.imagemProduto);
		}
		return "";
	}
}
