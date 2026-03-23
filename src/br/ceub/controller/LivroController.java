package br.ceub.controller;
import java.util.List;

import br.ceub.model.Livro;
import br.ceub.service.LivroService;

public class LivroController {
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    public Livro cadastrar(Livro livro) {
        return livroService.cadastrar(livro);
    }

    public Livro buscarPorId(int id) {
        return livroService.buscarPorId(id);
    }

    public List<Livro> buscarPorNome(String nome) {
        return livroService.buscarPorNome(nome);
    }

    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    public Livro alterar(Livro livro) {
        return livroService.alterar(livro);
    }

    public boolean remover(int id) {
        return livroService.remover(id);
    }
}