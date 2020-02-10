package projeto;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.Scanner;

public class Interesse {

    private int id;
    private String nome;

    public Interesse(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void loadInteresses(BST_Aed2<Integer, Interesse> interesseST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                Interesse aux_interesse = new Interesse(Integer.parseInt(aux1), aux2);
                interesseST.put(Integer.parseInt(aux1),aux_interesse);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printInteresses(BST_Aed2<Integer, Interesse> interesseST) {
        System.out.println("##################Listagem dos Interesses##################" + "\n");
        for (Integer key : interesseST.emordem()) {
            print_interesse(interesseST, key);
        }
        System.out.println("\n");
    }

    public static void print_interesse(BST_Aed2<Integer, Interesse> interesseST, Integer key) {
        System.out.println("ID: " + interesseST.get(key).getId() + " -" +
                " Nome: " + interesseST.get(key).getNome());
    }

    public static void saveInteresse(BST_Aed2<Integer, Interesse> interesseST, String path) {
        Out out = new Out(path);

        for (Integer key : interesseST.emordem()) {
            out.println(
                    interesseST.get(key).getId() + ";"
                            + interesseST.get(key).getNome() + ";");

        }
    }

    public static void addInteresse(BST_Aed2<Integer, Interesse> interesseST, String nome) {

        Interesse aux_interesse = new Interesse(interesseST.max()+1, nome);
        interesseST.put(interesseST.max()+1, aux_interesse);

        System.out.println("Interesse inserida com sucesso");
    }

    public static void removeInteresse(BST_Aed2<Integer, Interesse> interesseST, int id_comp) {

        interesseST.delete(id_comp);
        System.out.println("Interesse removida com sucesso");
    }

    public static void editInteresse(BST_Aed2<Integer, Interesse> interesseST, int id_comp) {

        if(interesseST.contains(id_comp)) {
            System.out.println("Alterar interesse " + interesseST.get(id_comp).getNome() + " para: ");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            interesseST.get(id_comp).setNome(choice);
            System.out.println("Interesse removida com sucesso");
        } else
            System.out.println("Não existe o interesse com o id: " + id_comp);
    }
}
