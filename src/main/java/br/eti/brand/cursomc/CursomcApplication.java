package br.eti.brand.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.eti.brand.cursomc.domain.Categoria;
import br.eti.brand.cursomc.domain.Cidade;
import br.eti.brand.cursomc.domain.Cliente;
import br.eti.brand.cursomc.domain.Endereco;
import br.eti.brand.cursomc.domain.Estado;
import br.eti.brand.cursomc.domain.Pagamento;
import br.eti.brand.cursomc.domain.PagamentoComBoleto;
import br.eti.brand.cursomc.domain.PagamentoComCartao;
import br.eti.brand.cursomc.domain.Pedido;
import br.eti.brand.cursomc.domain.Produto;
import br.eti.brand.cursomc.domain.enums.EstadoPagamento;
import br.eti.brand.cursomc.domain.enums.TipoCliente;
import br.eti.brand.cursomc.repositories.CategoriaRepository;
import br.eti.brand.cursomc.repositories.CidadeRepository;
import br.eti.brand.cursomc.repositories.ClienteRepository;
import br.eti.brand.cursomc.repositories.EnderecoRepository;
import br.eti.brand.cursomc.repositories.EstadoRepository;
import br.eti.brand.cursomc.repositories.PagamentoRepository;
import br.eti.brand.cursomc.repositories.PedidoRepository;
import br.eti.brand.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));	
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "67747477742", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("22332233","23332456"));
		
		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apto 301", "Jardim", "23222222", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "102", "Apto 101", "Centro", "34433334", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("15/11/2018 18:49"),  cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("16/11/2018 18:52"),  cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("06/11/2018 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
		
		
		
		
		
		
		
	}
}
