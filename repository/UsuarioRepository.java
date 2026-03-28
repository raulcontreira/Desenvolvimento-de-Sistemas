package br.ceub.repository;
import java.util.ArrayList;
import java.util.List;

import br.ceub.model.Livro;
import br.ceub.model.Usuario;

public class UsuarioRepository {

	private static List< Usuario> banco = new ArrayList<Usuario>();
    private static int nextId = 1;
	
    public Usuario salvar(Usuario u){
        if (u.getId() == 0) {
        	u.setId(nextId++);
        }
        banco.add(u.getId(), u);
        return u;
    }

    public Usuario buscarPorId(int id){
        return banco.get(id);
    }

    public Usuario buscarPorCpf(String cpf){
        for(Usuario u : banco ){
            if(u.getCpf().equals(cpf)) return u;
        }
        return null;
    }

    public Usuario buscarPorEmail(String email) {
    	for(Usuario u : banco ){
            if(u.getEmail().equals(email)) return u;
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
    	for(Usuario u : banco ){
            if(u.getNome().equals(nome)) return u;
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        return new Arraylist<>(banco);
    }

    public void atualizar(Usuario usuario) {
    	if(banco(usuario.getId())) {
    		bando.add(usuario.getId(), usuario);
    		return usuario;
    	}
    	
    	return null;
    }

    public void deletar(int id) {
    	return banco.remove(id) != null;
    }
}
