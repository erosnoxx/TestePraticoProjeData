package com.projeto.Projedata;

import com.projeto.Projedata.presenters.FuncionarioPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjedataApplication  implements CommandLineRunner {
	@Autowired
	private FuncionarioPresenter funcionarioPresenter;

	public static void main(String[] args) {
		SpringApplication.run(ProjedataApplication.class, args);
	}

	@Override
	public void run(String... args) {
		funcionarioPresenter.run();
	}
}
