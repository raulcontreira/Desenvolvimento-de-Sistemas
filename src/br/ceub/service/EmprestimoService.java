package br.ceub.service;
import java.time.LocalDate;
import java.util.List;

import br.ceub.model.Emprestimo;
import br.ceub.model.Livro;
import br.ceub.model.Usuario;
import br.ceub.repository.EmprestimoRepository;
import br.ceub.repository.LivroRepository;
import br.ceub.repository.UsuarioRepository;

public class EmprestimoService {
    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;
    private LivroRepository livroRepository;
    //private static int proximoId = 1;

    public EmprestimoService() {
        this.emprestimoRepository = new EmprestimoRepository();
        this.usuarioRepository = new UsuarioRepository();
        this.livroRepository = new LivroRepository();
    }

    public EmprestimoService(EmprestimoRepository emprestimoRepository, 
                            UsuarioRepository usuarioRepository, 
                            LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public Emprestimo realizarEmprestimo(int usuarioId, int livroId, int diasEmprestimo) {
    	
    	//Valida usuário e livro
    	Usuario usuario = usuarioRepository.buscarPorId(usuarioId);
        Livro livro = livroRepository.buscarPorId(livroId);

        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return null;
        }
         
       
        if (livro == null) {
            System.out.println("Livro não encontrado");
            return null;
        }
        
        //Verifica disponibilidade
        if (livro.getQuantidade() <= 0) {
            System.out.println("Livro indisponível para emprestimo");
            return null;
        }
        
        //Previne mesmo livro emprestado 2x
        List<Emprestimo> ativos = emprestimoRepository.buscarEmprestimosAtivos(usuarioId);

        for (Emprestimo e : ativos) {
            if (e.getLivroId() == livroId) {
                System.out.println("Livro já emprestado para esse usuário");
                return null;
            }
        }
        
      //diminui a quatidade
        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.salvar(livro);
        
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuarioId(usuarioId);
        emprestimo.setLivroId(livroId);
        emprestimo.setAtivo(true);
                
        LocalDate hoje = LocalDate.now();
        emprestimo.setDataEmprestimo(hoje);
        emprestimo.setDataDevolucaoPrevista(hoje.plusDays(diasEmprestimo));
        return emprestimoRepository.salvar(emprestimo);
        
    }

    public void devolverLivro(int emprestimoId) {
    	
    	Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);
    	
        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado");
            return;
        }

        if (!emprestimo.isAtivo()) {
            System.out.println("Livro já foi devolvido");
            return;
        }
        
        //Registra data real de devolução
        emprestimo.setAtivo(false);
        emprestimo.setDataDevoluçaoReal(LocalDate.now());
        
        //Incrementa quantidade de livros
        Livro livro = livroRepository.buscarPorId(emprestimo.getLivroId());
        livro.setQuantidade(livro.getQuantidade() + 1);
        livroRepository.salvar(livro);
    
    }

    public List<Emprestimo> buscarEmprestimosDoUsuario(int usuarioId) {
        return emprestimoRepository.buscarPorUsuario(usuarioId);
    }

    public List<Emprestimo> buscarEmprestimosAtivosDoUsuario(int usuarioId) {
        return emprestimoRepository.buscarEmprestimosAtivos(usuarioId);
    }

    public List<Emprestimo> listarTodosEmprestimos() {
        return emprestimoRepository.listarTodos();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.listarEmprestimosAtivos();
    }

    public Emprestimo buscarPorId(int id) {
        return emprestimoRepository.buscarPorId(id);
    }

    public boolean verificarAtraso(int emprestimoId) {
    	if (emprestimoId <= 0) {
            System.out.println("ID inválido");
            return false;
        }

        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado");
            return false;
        }

        return LocalDate.now().isAfter(emprestimo.getDataDevolucaoPrevista());
    }
}
