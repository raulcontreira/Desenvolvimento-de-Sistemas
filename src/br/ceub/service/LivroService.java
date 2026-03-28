package br.ceub.service;
import java.util.List;


import br.ceub.model.Livro;
import br.ceub.repository.LivroRepository;

public class LivroService {
    private LivroRepository livroRepository;

    public LivroService() {
        this.livroRepository = new LivroRepository();
    }

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrar(Livro livro) {
    	
    	if (livro.getNome() == null || livro.getNome().isEmpty()) {
            System.out.println("Nome do livro não pode ser null");
            return null;
        }
    	
        livro.setId(livroRepository.listarTodos().size()+1);
    	
        return livroRepository.salvar(livro);
    }

    public Livro buscarPorId(int id) {
        return livroRepository.buscarPorId(id);
    }

    public List<Livro> buscarPorNome(String nome) {
    	if (nome == null || nome.isEmpty()) {
            System.out.println("Digite um nome válido");
            return null;
        }
        return livroRepository.buscarPorNome(nome);
    }

    public List<Livro> listarTodos() {
        return livroRepository.listarTodos();
    }

    public void alterar(Livro livro) {
    	 Livro livroExiste = livroRepository.buscarPorId(livro.getId());

    	    if (livroExiste == null) {
    	        System.out.println("Livro não encontrado");
    	        return;
    	    }

    	    livroRepository.atualizar(livro);
  
    }

    public void remover(int id) {
    	Livro livro = livroRepository.buscarPorId(id);

        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        livroRepository.deletar(id);
    	
    }
}
