package br.ceub.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.ceub.model.Livro;

public class LivroRepository {
    private static List< Livro> banco = new ArrayList<Livro>();
    private static int nextId = 1;

    public Livro salvar(Livro livro) {
        if (livro.getId() == 0) {
            livro.setId(nextId++);
        }
        banco.add(livro);
        return livro;
    }

    public Livro buscarPorId(int id) {
        return banco.get(id);
    }

    public List<Livro> buscarPorNome(String nome) {
        return banco .stream()
                .filter(livro -> livro.getNome().equalsIgnoreCase(nome))
                .toList();
    }

    public List<Livro> listarTodos() {
        return new ArrayList<Livro>(banco);
    }

    public Livro atualizar(Livro livro) {
        	banco.add(livro.getId(), livro);
            return livro; 
    }

    public boolean deletar(int id) {
        return banco.remove(id) != null;
    }
}
