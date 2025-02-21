package app;

import dao.DataAcessObject;
import dao.UserDAO;
import models.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static UserDAO dao = new UserDAO();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            writeMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    handleCreateUser();
                    break;
                case 2:
                    handleDeleteUser();
                    break;
                case 3:
                    handleFindUser();
                    break;
                case 4:
                    handleListUsers();
                    break;
                case 5:
                    handleUpdateUser();
                    break;
                case 6:
                default:
                    isRunning = false;
            }
        }
    }

    private static void writeMenu() {
        System.out.println("=======================================");
        System.out.println("1. Criar usuário;");
        System.out.println("2. Deletar usuário pelo ID;");
        System.out.println("3. Procurar usuário pelo ID;");
        System.out.println("4. Listar usuários;");
        System.out.println("5. Atualizar dados de usuário pelo ID;");
        System.out.println("6. Sair do sistema.");
        System.out.println("=======================================");
        System.out.print("> Escolha uma opção: ");
    }

    private static void handleCreateUser() {
        System.out.println("Digite o id do usuario: ");
        int id = scanner.nextInt();

        System.out.print("Digite o nome do usuário: ");
        String name = scanner.next();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.next();

        System.out.print("Digite a idade do usuário: ");
        int age = scanner.nextInt();

        User user = new User(id, name, email, age);
        boolean result = dao.insert(user);
        if (result)
            System.out.println("Usuário criado com sucesso");
        else
            System.err.println("Erro ao criar o usuário.");
    }

    private static void handleDeleteUser() {
        System.out.println("Digite o id do usuario: ");
        int id = scanner.nextInt();

        boolean result = dao.delete(id);
        if (result)
            System.out.println("Usuário deletado com sucesso");
        else
            System.err.println("Erro ao deletar o usuário.");
    }

    private static void handleFindUser() {
        System.out.println("Digite o id do usuario: ");
        int id = scanner.nextInt();

        User user = dao.get(id);
        if (user != null) {
            System.out.printf("> ID: %d\n> Nome: %s\n> Email: %s\n> Idade: %d%n",
                    user.getId(), user.getName(), user.getEmail(), user.getAge());
        } else
            System.err.println("O usuário não foi encontrado.");
    }

    private static void handleListUsers() {
        List<User> users = dao.read();

        System.out.println("=======================================");
        for (User user : users) {
            System.out.printf("> ID: %d\n> Nome: %s\n> Email: %s\n> Idade: %d%n\n",
                    user.getId(), user.getName(), user.getEmail(), user.getAge());
        }
    }

    private static void handleUpdateUser() {
        System.out.println("Digite o id do usuario: ");
        int id = scanner.nextInt();

        System.out.print("Digite o nome do usuário: ");
        String name = scanner.next();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.next();

        System.out.print("Digite a idade do usuário: ");
        int age = scanner.nextInt();

        User user = new User(id, name, email, age);
        boolean result = dao.update(user);
        if (result)
            System.out.println("Usuário atualizado com sucesso");
        else
            System.err.println("Erro ao atualizar o usuário.");
    }
}