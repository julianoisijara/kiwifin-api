package com.kiwifin.api;

import com.kiwifin.api.service.ColaboradorService;
import com.kiwifin.api.service.DepartamentoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KiwifinApiApplication {
		static DepartamentoService departamentoService;
		static ColaboradorService colaboradorService;

	public KiwifinApiApplication(DepartamentoService departamentoService, ColaboradorService colaboradorService) {
		this.departamentoService = departamentoService;
		this.colaboradorService = colaboradorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(KiwifinApiApplication.class, args);

		departamentoService.criarDepartamentoAdm();
		colaboradorService.criarNovoAdm();

	}
}
