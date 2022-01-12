package br.com.bagarote.model.entity;

import java.io.Serializable;
import java.util.List;

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
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SequenceEmpresa", sequenceName = "SEQ_EMPRESA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceEmpresa")
	@EqualsAndHashCode.Include
	@Column(name="id_empresa")
	private Long idEmpresa;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "razao_social")
	private String razaoSocial;

	private String cnpj;

	private String telefone;

	@Column(name = "responsavel_legal")
	private String responsavelLegal;

	@OneToMany(mappedBy = "empresa")
	private List<Cliente> clientes;

	@OneToMany(mappedBy = "empresa")
	private List<Produto> produtos;

	@OneToMany(mappedBy = "empresa")
	private List<Venda> vendas;
}
