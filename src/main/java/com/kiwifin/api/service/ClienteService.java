package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.ClienteCreateDTO;
import com.kiwifin.api.DTO.update.ClienteUpdateDTO;
import com.kiwifin.api.entities.Cliente;
import com.kiwifin.api.repositories.ClienteRepository;
import com.kiwifin.api.service.data.GenericDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService extends GenericDataService<Cliente, Long, ClienteRepository> {

    private static final Logger logger = LogManager.getLogger(ClienteService.class);

    public List<Cliente> pesquisarTodosClientes() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os clientes: " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> pesquisarCliente(Long idCliente, String cpf, String nome) {

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            if (idCliente != null) {
                listaCliente.add(repository.findById(idCliente).get());
            }
            if (cpf != null) {
                listaCliente.add(repository.findByCpfEquals(cpf));
            }
            if (nome != null) {
                listaCliente.addAll(repository.findByNomeContains(nome.toUpperCase()));
            }

        } catch (Exception e) {
            logger.error("Erro ao buscar cliente."+ " ERRO -> " + e.getMessage());
            return null;
        }

        return listaCliente;

    }

    public Cliente adicionarCliente(ClienteCreateDTO clienteDTO) {

        Cliente novoCliente = new Cliente();

        try {
            novoCliente.setNome(clienteDTO.getNome().toUpperCase());
            novoCliente.setEmail(clienteDTO.getEmail());
            novoCliente.setCpf(clienteDTO.getCpf());
            novoCliente.setSenha(clienteDTO.getSenha());
            novoCliente.setDataNascimento(clienteDTO.getDataNascimento());
            novoCliente.setCelular(clienteDTO.getCelular());
            novoCliente.setCep(clienteDTO.getCep());
            novoCliente.setCidade(clienteDTO.getCidade().toUpperCase());
            novoCliente.setEndereco(clienteDTO.getEndereco().toUpperCase());
            novoCliente.setUf(clienteDTO.getUf().toUpperCase());
            novoCliente.setComplemento(clienteDTO.getComplemento().toUpperCase());

            repository.save(novoCliente);
            return novoCliente;

        } catch (Exception e) {
            logger.error("Erro ao criar cliente, nome: " + clienteDTO.getNome() + " ERRO -> " + e.getMessage());
            return null;
        }
    }
    
    public Cliente editarCliente(ClienteUpdateDTO clienteDTO) {
        try {
            Cliente atualizaCliente = findById(clienteDTO.getIdCliente()).get();

            if (atualizaCliente != null) {

                if (clienteDTO.getNome() != null) {
                    atualizaCliente.setNome(clienteDTO.getNome().toUpperCase());
                }
                if (clienteDTO.getEmail() != null) {
                    atualizaCliente.setEmail(clienteDTO.getEmail());
                }
                if (clienteDTO.getCpf() != null) {
                    atualizaCliente.setCpf(clienteDTO.getCpf());
                }
                if (clienteDTO.getSenha() != null) {
                    atualizaCliente.setSenha(clienteDTO.getSenha());
                }
                if (clienteDTO.getDataNascimento() != null) {
                    atualizaCliente.setDataNascimento(clienteDTO.getDataNascimento());
                }
                if (clienteDTO.getCelular() != null) {
                    atualizaCliente.setCelular(clienteDTO.getCelular());
                }
                if (clienteDTO.getCep() != null) {
                    atualizaCliente.setCep(clienteDTO.getCep());
                }
                if (clienteDTO.getCidade() != null) {
                    atualizaCliente.setCidade(clienteDTO.getCidade().toUpperCase());
                }
                if (clienteDTO.getEndereco() != null) {
                    atualizaCliente.setEndereco(clienteDTO.getEndereco().toUpperCase());
                }
                if (clienteDTO.getUf() != null) {
                    atualizaCliente.setUf(clienteDTO.getUf().toUpperCase());
                }
                if (clienteDTO.getComplemento() != null) {
                    atualizaCliente.setComplemento(clienteDTO.getComplemento().toUpperCase());
                }

                repository.save(atualizaCliente);
            }

            return atualizaCliente;

        } catch (Exception e) {
            logger.error("Erro ao atualizar o cliente:" + clienteDTO.getIdCliente() + " ERRO -> " + e.getMessage());
            return null;
        }
    }

}
