package br.eti.brand.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.eti.brand.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
