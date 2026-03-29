package br.ceub;

import java.time.LocalDate;
import java.util.List;

import br.ceub.controller.LivroController;
import br.ceub.controller.UsuarioController;
import br.ceub.model.Emprestimo;
import br.ceub.model.Livro;
import br.ceub.model.Usuario;
import br.ceub.service.EmprestimoService;

public class Main {

    public static void main(String[] args) {

        try {
            
            LivroController livroController = new LivroController();
            UsuarioController usuarioController = new UsuarioController();
            EmprestimoService emprestimoService = new EmprestimoService();

            
            livroController.cadastrar(new Livro(1, "One Piece", "Oda", 1, 5));
            livroController.cadastrar(new Livro(2, "Turma da Mônica", "Maurício de Souza", 1, 3));

            
            
            usuarioController.cadastrar(new Usuario(1, "Raul", "Raul@email.com", "123", "9999"));
            usuarioController.cadastrar(new Usuario(2, "Giovana", "Giovana@email.com", "333", "8888"));

            
            System.out.println(" Lista de livros:");
            for (Livro l : livroController.listarTodos()) {
                System.out.println(l.getNome() + " - Estoque: " + l.getQuantidade());
            }

           
            System.out.println("\n Realizando empréstimo...");
            Emprestimo emp = emprestimoService.realizarEmprestimo(1, 1, 7);

            System.out.println("Empréstimo realizado com ID: " + emp.getId());

            
            Livro livro = livroController.buscarPorId(1);
            System.out.println("Estoque após empréstimo: " + livro.getQuantidade());

            
            boolean atraso = emprestimoService.verificarAtraso(emp.getId());
            System.out.println("Está atrasado? " + atraso);

            
            System.out.println("\n Devolvendo livro...");
            emprestimoService.devolverLivro(emp.getId());

            
            livro = livroController.buscarPorId(1);
            System.out.println("Estoque após devolução: " + livro.getQuantidade());

            
            System.out.println("\n Empréstimos do usuário 1:");
            List<Emprestimo> lista = emprestimoService.buscarEmprestimosDoUsuario(1);

            for (Emprestimo e : lista) {
                System.out.println("ID: " + e.getId() +
                        " | Livro: " + e.getLivroId() +
                        " | Ativo: " + e.isAtivo());
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

