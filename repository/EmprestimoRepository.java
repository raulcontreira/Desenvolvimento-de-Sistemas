package br.ceub.repository;
import java.util.List;

import br.ceub.model.Emprestimo;

public class EmprestimoRepository {
	
	private static  list<emprestimo> banco = new Arraylist<emprestimo>();
	private static  int nextId = 1;
	
    public Emprestimo salvar(Emprestimo emprestimo) {
    	if (emprestimo.getId() == 0) {
    		emprestimo.setId(nextId++);
    	}
    	banco.add(emprestimo.getId(), emprestimo)
        return esmprestimo;
    }

    public Emprestimo buscarPorId(int id) {
        return banco.get(id);
    }

    public List<Emprestimo> buscarPorUsuario(int usuarioId) {
        return ;
    }

    public List<Emprestimo> buscarEmprestimosAtivos(int usuarioId) {
        return banco.values().stream()
                .filter(emprestimo -> emprestimo.getUsuarioId() == usuarioId && emprestimo.isAtivo())
                .collect(Collectors.toList());
    }

    public List<Emprestimo> listarTodos() {
        return new ArrayList<>(banco.values());
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return banco.values().stream()
                .filter(Emprestimo::isAtivo)
                .collect(Collectors.toList());
    }

    public void atualizar(Emprestimo emprestimo) {
    	if(banco (emprestimo.getId()))
    		banco.add(emprestimo.getId(), emprestimo);
    		return emprestimo;
    }
    	return null; // caso não ache o emprestimo
    
    public boolean deletar(int id) {
            return banco.remove(id) != null;
        }
}
