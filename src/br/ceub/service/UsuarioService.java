package br.ceub.service;
import java.util.List;

import br.ceub.model.Usuario;
import br.ceub.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
    	
    	if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Nome do usuário não pode ser null");
            return null;
          }
    	
    	if (buscarPorCpf(usuario.getCpf()) != null) {
    	    System.out.println("CPF já cadastrado");
    	    return null;
    	}

    	if (buscarPorEmail(usuario.getEmail()) != null) {
    	   System.out.println("Email já cadastrado");
    	   return null;
    	}
    	usuario.setId(usuarioRepository.listarTodos().size() + 1);

    	return usuarioRepository.salvar(usuario);
    }

    public Usuario buscarPorId(int id) {
    	if (id <= 0) {
            System.out.println("ID inválido");
            return null;
        }
    	
    	return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarPorCpf(String cpf) {
    	if (cpf == null || cpf.isEmpty()) {
            System.out.println("CPF inválido");
            return null;
        }
        return usuarioRepository.buscarPorCpf(cpf);
    }

    public Usuario buscarPorEmail(String email) {
    	if (email == null || email.isEmpty()) {
            System.out.println("Email inválido");
            return null;
        }
        return usuarioRepository.buscarPorEmail(email);
    }

    public List<Usuario> buscarPorNome(String nome) {
    	if (nome == null || nome.isEmpty()) {
            System.out.println("Nome inválido");
            return null;
        }
    	return usuarioRepository.buscarPorNome(nome);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public void alterar(Usuario usuario) {
    	Usuario usuarioExiste = usuarioRepository.buscarPorId(usuario.getId());

        if (usuarioExiste == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        usuarioRepository.atualizar(usuario);
    
    }

    public void remover(int id) {
    	Usuario usuarioExiste = usuarioRepository.buscarPorId(id);
    	
    	if (usuarioExiste == null) {
    		System.out.println("Usuário não existe");
    		return;
    	}
    	
    	usuarioRepository.deletar(id);
    }
}
