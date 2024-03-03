package com.kiwifin.api.service.conversor;

import com.kiwifin.api.DTO.view.ClienteViewDTO;
import com.kiwifin.api.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteConversorService extends GenericConversor<Cliente, ClienteViewDTO>{

    private final PerfilConversorService perfilConversorService;

    public ClienteConversorService(PerfilConversorService perfilConversorService) {
        this.perfilConversorService = perfilConversorService;
    }

    public ClienteViewDTO entity2Dto(Cliente cliente) {

        ClienteViewDTO dto = new ClienteViewDTO();

        dto.setIdCliente(cliente.getIdCliente());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setCelular(cliente.getCelular());
        dto.setCep(cliente.getCep());
        dto.setCidade(cliente.getCidade());
        dto.setEndereco(cliente.getEndereco());
        dto.setUf(cliente.getUf());
        dto.setComplemento(cliente.getComplemento());
        dto.setPerfil(perfilConversorService.entity2Dto(cliente.getPerfil()));

        return dto;
    }
}
