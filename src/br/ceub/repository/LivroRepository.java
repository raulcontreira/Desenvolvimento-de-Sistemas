package br.ceub.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.ceub.model.Livro;

public class LivroRepository {
    private static Map<Integer, Livro> banco = new HashMap<>();
    private static int nextId = 1;

    public Livro salvar(Livro livro) {
        if (livro.getId() == 0) {
            livro.setId(nextId++);
        }
        banco.put(livro.getId(), livro);
        return livro;
    }

    public Livro buscarPorId(int id) {
        return banco.get(id);
    }

    public List<Livro> buscarPorNome(String nome) {
        return banco.values().stream()
                .filter(livro -> livro.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(banco.values());
    }

    public Livro atualizar(Livro livro) {
        if (banco.containsKey(livro.getId())) {
            banco.put(livro.getId(), livro);
            return livro;
        }
        return null; 
    }

    public boolean deletar(int id) {
        return banco.remove(id) != null;
    }
}
