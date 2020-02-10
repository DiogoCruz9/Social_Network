package projeto;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.Scanner;

public class Historico {

    private int id;
    private int nempresas;
    private SeparateChainingHashST<Integer, Empresa> empresa_historicoST = new SeparateChainingHashST<>();

    public Historico(int id, int nempresas, SeparateChainingHashST<Integer, Empresa> empresa_historicoST) {
        this.id = id;
        this.nempresas = nempresas;
        this.empresa_historicoST = empresa_historicoST;
    }

    public Historico(int id, int nempresas) {
        this.id = id;
        this.nempresas = nempresas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNempresas() {
        return nempresas;
    }

    public void setNempresas(int nempresas) {
        this.nempresas = nempresas;
    }

    public SeparateChainingHashST<Integer, Empresa> getEmpresa_historicoST() {
        return empresa_historicoST;
    }

    public void setEmpresa_historicoST(SeparateChainingHashST<Integer, Empresa> empresa_historicoST) {
        this.empresa_historicoST = empresa_historicoST;
    }

    public static void loadHistorico(BST_Aed2<Integer, Historico> historicoST, BST_Aed2<Integer, Empresa> empresaST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                SeparateChainingHashST<Integer, Empresa> aux_st = new SeparateChainingHashST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                int i = Integer.parseInt(aux2);
                for(int x = 0; x<i;x++){
                    String aux3 = token[x+2];
                    Empresa aux_empresa = empresaST.get(Integer.parseInt(aux3));
                    aux_st.put(x+1,aux_empresa);
                }
                Historico aux_historico = new Historico(Integer.parseInt(aux1),Integer.parseInt(aux2), aux_st);
                historicoST.put(Integer.parseInt(aux1), aux_historico);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printHistoricos(BST_Aed2<Integer, Historico> historicoST) {
        System.out.println("##################Listagem dos Historicos##################" + "\n");
        for (Integer key : historicoST.emordem()) {
            print_historico(historicoST, key);
        }
        System.out.println("\n");
    }

    public static void print_historico(BST_Aed2<Integer, Historico> historicoST, Integer key) {
        System.out.println("ID: " + historicoST.get(key).getId());
        for(int i=0; i<historicoST.get(key).getNempresas();i++){
            System.out.println("Empresa n. " + historicoST.get(key).getEmpresa_historicoST().get(i+1).getId() + " - " +
                    "Nome: " + historicoST.get(key).getEmpresa_historicoST().get(i+1).getNome());
        }

    }

    public static void saveHistorico(BST_Aed2<Integer, Historico> historicoST, String path) {
        Out out = new Out(path);

        for (Integer key : historicoST.emordem()) {
            out.print(
                    historicoST.get(key).getId() + ";"
                            + historicoST.get(key).getNempresas() + ";");
            for(int i=0;i<historicoST.get(key).getNempresas();i++) {
                out.print(historicoST.get(key).getEmpresa_historicoST().get(i+1).getId() + ";");
            }
            out.println();
        }
    }

    public static void addHistorico(BST_Aed2<Integer, Historico> historicoST, BST_Aed2<Integer, Empresa> empresaST, int numempresas) {

        Historico aux_historico = new Historico(historicoST.max()+1,numempresas);
        SeparateChainingHashST<Integer, Empresa> aux_st = new SeparateChainingHashST<>();

        System.out.println("Indique os ID's das empresas no historico");
        for(int i=0;i<numempresas;i++) {
            Scanner scanIn = new Scanner(System.in);
            String id = scanIn.nextLine();

            Empresa aux_empresa = empresaST.get(Integer.parseInt(id));
            aux_st.put(aux_st.size()+1,aux_empresa);
        }

        aux_historico.setEmpresa_historicoST(aux_st);
        historicoST.put(historicoST.max()+1,aux_historico);

        System.out.println("Historico adicionado com sucesso");
    }

    public static void removeHistorico(BST_Aed2<Integer, Historico> historicoST, BST_Aed2<Integer, Pessoa> pessoaST, int id_historico) {

        for(Integer key : pessoaST.emordem()){
            if(pessoaST.get(key).getMyHistorico().getId()==id_historico){
                pessoaST.get(key).setMyHistorico(null);
            }
        }

        historicoST.delete(id_historico);
        System.out.println("Historico removido com sucesso");
    }

}
