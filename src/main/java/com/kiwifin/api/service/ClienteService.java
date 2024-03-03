package com.kiwifin.api.service;

import com.kiwifin.api.DTO.create.ClienteCreateDTO;
import com.kiwifin.api.DTO.update.ClienteUpdateDTO;
import com.kiwifin.api.DTO.view.ClienteViewDTO;
import com.kiwifin.api.entities.Cliente;
import com.kiwifin.api.repositories.ClienteRepository;
import com.kiwifin.api.service.conversor.ClienteConversorService;
import com.kiwifin.api.service.data.GenericBusinessService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService extends GenericBusinessService<ClienteConversorService, ClienteRepository> {


    private static final Logger logger = LogManager.getLogger(ClienteService.class);


    public ClienteViewDTO incluirCliente(ClienteCreateDTO clienteCreateDTO) {
        return conversorService.entity2Dto(adicionarCliente(clienteCreateDTO));
    }

    public ClienteViewDTO atualizarCliente(ClienteUpdateDTO clienteUpdateDTO) {
        return conversorService.entity2Dto(editarCliente(clienteUpdateDTO));
    }

    public List<ClienteViewDTO> buscarClientes(Long idCliente, String cpf, String nome) {
        return conversorService.entityList2DtoList(pesquisarClientes(idCliente, cpf, nome));
    }

    public List<ClienteViewDTO> buscarTodosClientes() {
        return conversorService.entityList2DtoList(pesquisarTodosClientes());
    }




    public List<Cliente> pesquisarTodosClientes() {
        try {
            return dataService.findAll();
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os clientes: " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> pesquisarClientes(Long idCliente, String cpf, String nome) {

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            if (idCliente != null) {
                Optional<Cliente> cliente = dataService.findById(idCliente);
                if (cliente.isPresent()) {
                    listaCliente.add(cliente.get());
                }
            }
            if (cpf != null) {
                listaCliente.addAll(dataService.findByCpfEquals(cpf));
            }
            if (nome != null) {
                listaCliente.addAll(dataService.findByNomeContains(nome.toUpperCase()));
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
            novoCliente.setSenha(new BCryptPasswordEncoder().encode(clienteDTO.getSenha()));
            novoCliente.setDataNascimento(clienteDTO.getDataNascimento());
            novoCliente.setCelular(clienteDTO.getCelular());
            novoCliente.setCep(clienteDTO.getCep());
            novoCliente.setCidade(clienteDTO.getCidade().toUpperCase());
            novoCliente.setEndereco(clienteDTO.getEndereco().toUpperCase());
            novoCliente.setUf(clienteDTO.getUf().toUpperCase());
            novoCliente.setComplemento(clienteDTO.getComplemento().toUpperCase());

            dataService.save(novoCliente);
            return novoCliente;

        } catch (Exception e) {
            logger.error("Erro ao criar cliente, nome: " + clienteDTO.getNome() + " ERRO -> " + e.getMessage());
            return null;
        }
    }
    
    public Cliente editarCliente(ClienteUpdateDTO clienteDTO) {
        try {
            Optional<Cliente> atualizaCliente = dataService.findById(clienteDTO.getIdCliente());

            if (atualizaCliente.isPresent()) {

                if (clienteDTO.getNome() != null) {
                    atualizaCliente.get().setNome(clienteDTO.getNome().toUpperCase());
                }
                if (clienteDTO.getEmail() != null) {
                    atualizaCliente.get().setEmail(clienteDTO.getEmail());
                }
                if (clienteDTO.getCpf() != null) {
                    atualizaCliente.get().setCpf(clienteDTO.getCpf());
                }
                if (clienteDTO.getSenha() != null) {
                    atualizaCliente.get().setSenha(new BCryptPasswordEncoder().encode(clienteDTO.getSenha()));
                }
                if (clienteDTO.getDataNascimento() != null) {
                    atualizaCliente.get().setDataNascimento(clienteDTO.getDataNascimento());
                }
                if (clienteDTO.getCelular() != null) {
                    atualizaCliente.get().setCelular(clienteDTO.getCelular());
                }
                if (clienteDTO.getCep() != null) {
                    atualizaCliente.get().setCep(clienteDTO.getCep());
                }
                if (clienteDTO.getCidade() != null) {
                    atualizaCliente.get().setCidade(clienteDTO.getCidade().toUpperCase());
                }
                if (clienteDTO.getEndereco() != null) {
                    atualizaCliente.get().setEndereco(clienteDTO.getEndereco().toUpperCase());
                }
                if (clienteDTO.getUf() != null) {
                    atualizaCliente.get().setUf(clienteDTO.getUf().toUpperCase());
                }
                if (clienteDTO.getComplemento() != null) {
                    atualizaCliente.get().setComplemento(clienteDTO.getComplemento().toUpperCase());
                }

                dataService.save(atualizaCliente.get());
            }

            return atualizaCliente.get();

        } catch (Exception e) {
            logger.error("Erro ao atualizar o cliente:" + clienteDTO.getIdCliente() + " ERRO -> " + e.getMessage());
            return null;
        }
    }

}
