package projeto;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.ArrayList;
import java.util.Scanner;

public class Competencia {

    private int id;
    private String nome;

    public Competencia(int id, String nome) {
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

    public static void loadCompetencia(BST_Aed2<Integer, Competencia> competenciaST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                Competencia aux_competencia = new Competencia(Integer.parseInt(aux1), aux2);
                competenciaST.put(Integer.parseInt(aux1),aux_competencia);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printCompetencias(BST_Aed2<Integer, Competencia> competenciaST) {
        System.out.println("##################Listagem das Competencias##################" + "\n");
        for (Integer key : competenciaST.emordem()) {
            print_competencia(competenciaST, key);
        }
        System.out.println("\n");
    }

    public static void print_competencia(BST_Aed2<Integer, Competencia> competenciaST, Integer key) {
        System.out.println("ID: " + competenciaST.get(key).getId() + " -" +
                " Nome: " + competenciaST.get(key).getNome());
    }

    public static void saveCompetencia(BST_Aed2<Integer, Competencia> competenciaST, String path) {
        Out out = new Out(path);

        for (Integer key : competenciaST.emordem()) {
            out.println(
                    competenciaST.get(key).getId() + ";"
                            + competenciaST.get(key).getNome() + ";");

        }
    }

    public static void addCompetencia(BST_Aed2<Integer, Competencia> competenciaST, String nome) {

        Competencia aux_competencia = new Competencia(competenciaST.max()+1, nome);
        competenciaST.put(competenciaST.max()+1, aux_competencia);

        System.out.println("Competencia inserida com sucesso");
    }

    public static void removeCompetencia(BST_Aed2<Integer, Competencia> competenciaST, int id_comp) {

        competenciaST.delete(id_comp);
        System.out.println("Competencia removida com sucesso");
    }

    public static void editCompetencia(BST_Aed2<Integer, Competencia> competenciaST, int id_comp) {

        if(competenciaST.contains(id_comp)) {
            System.out.println("Alterar competencia " + competenciaST.get(id_comp).getNome() + " para: ");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            competenciaST.get(id_comp).setNome(choice);
            System.out.println("Competencia removida com sucesso");
        } else
            System.out.println("Não existe a competencia com o id: " + id_comp);
    }
}
