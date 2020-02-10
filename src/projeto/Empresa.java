package projeto;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.Date;
import java.util.Scanner;

public class Empresa {

    private int id;
    private String nome;
    private String sigla;
    private float coordenadax;
    private float coordenaday;
    private Interesse myInteresse;
    private SeparateChainingHashST<Integer, Pessoa> pessoa_empresaST = new SeparateChainingHashST<>();
    private RedBlackBST<Date, Meeting> meeting_empresaST = new RedBlackBST<>();

    public Empresa(int id, String nome, String sigla, float coordenadax, float coordenaday, Interesse myInteresse, SeparateChainingHashST<Integer, Pessoa> pessoa_empresaST, RedBlackBST<Date, Meeting> meeting_empresaST) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.coordenadax = coordenadax;
        this.coordenaday = coordenaday;
        this.myInteresse = myInteresse;
        this.pessoa_empresaST = pessoa_empresaST;
        this.meeting_empresaST = meeting_empresaST;
    }

    public Empresa(int id, String nome, String sigla, float coordenadax, float coordenaday, Interesse myInteresse) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.coordenadax = coordenadax;
        this.coordenaday = coordenaday;
        this.myInteresse = myInteresse;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public float getCoordenadax() {
        return coordenadax;
    }

    public void setCoordenadax(float coordenadax) {
        this.coordenadax = coordenadax;
    }

    public float getCoordenaday() {
        return coordenaday;
    }

    public void setCoordenaday(float coordenaday) {
        this.coordenaday = coordenaday;
    }

    public Interesse getMyInteresse() {
        return myInteresse;
    }

    public void setMyInteresse(Interesse myInteresse) {
        this.myInteresse = myInteresse;
    }

    public SeparateChainingHashST<Integer, Pessoa> getPessoa_empresaST() {
        return pessoa_empresaST;
    }

    public void setPessoa_empresaST(SeparateChainingHashST<Integer, Pessoa> pessoa_empresaST) {
        this.pessoa_empresaST = pessoa_empresaST;
    }

    public RedBlackBST<Date, Meeting> getMeeting_empresaST() {
        return meeting_empresaST;
    }

    public void setMeeting_empresaST(RedBlackBST<Date, Meeting> meeting_empresaST) {
        this.meeting_empresaST = meeting_empresaST;
    }

    public static void loadEmpresa(BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Interesse> interesseST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                String aux3 = token[2];
                String aux4 = token[3];
                String aux5 = token[4];
                String aux6 = token[5];

                Interesse aux_interesse = interesseST.get(Integer.parseInt(aux6));
                Empresa aux_empresa = new Empresa(Integer.parseInt(aux1), aux2, aux3, Float.parseFloat(aux4), Float.parseFloat(aux5),aux_interesse);
                empresaST.put(Integer.parseInt(aux1), aux_empresa);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printEmpresas(BST_Aed2<Integer, Empresa> empresaST) {
        System.out.println("################## Listagem das Empresas##################" + "\n");
        for (Integer key : empresaST.emordem()) {
            print_empresa(empresaST, key);
        }
        System.out.println("\n");
    }

    public static void printEmpresas_simple(BST_Aed2<Integer, Empresa> empresaST) {
        System.out.println("################## Listagem das Empresas##################" + "\n");
        for (Integer key : empresaST.emordem()) {
            print_empresa_simple(empresaST, key);
        }
        System.out.println("\n");
    }

    public static void print_empresa(BST_Aed2<Integer, Empresa> empresaST, Integer key) {
        System.out.println("ID: " + empresaST.get(key).getId() + " -" +
                " Nome: " + empresaST.get(key).getNome() + " -" +
                " Sigla: " + empresaST.get(key).getSigla() + " -" +
                " Area de Interesse: " + empresaST.get(key).getMyInteresse().getNome());
        System.out.println("Trabalhadores: ");
        for(int i=0;i<empresaST.get(key).getPessoa_empresaST().size();i++) {
            System.out.println("- " + empresaST.get(key).getPessoa_empresaST().get(i+1).getNome());
        }
        System.out.println("Meetings Criados: ");
        RedBlackBST<Date, Meeting> aux_meetings = empresaST.get(key).getMeeting_empresaST();
        for(Date date : aux_meetings.keys()) {
            System.out.println("- " + aux_meetings.get(date).getNome());
        }
    }

    public static void print_empresa_simple(BST_Aed2<Integer, Empresa> empresaST, Integer key) {
        System.out.println("ID: " + empresaST.get(key).getId() + " -" +
                " Nome: " + empresaST.get(key).getNome());
    }

    public static void meetings_empresa(BST_Aed2<Integer, Empresa> empresaST, Integer id_empresa) {
        System.out.println("################## Meetings Realizados pela Empresa: " + empresaST.get(id_empresa).getNome() + " ##################");
        Empresa aux_empresa = empresaST.get(id_empresa);
        if(aux_empresa.getMeeting_empresaST().size()==0) {
            System.out.println("Sem meetings realizados");
        } else {
            RedBlackBST<Date, Meeting> aux_meetings = empresaST.get(id_empresa).getMeeting_empresaST();
            for(Date date : aux_meetings.keys()) {
                System.out.println("- " + aux_meetings.get(date).getNome());
            }
        }
        System.out.println();
    }

    public static void saveEmpresa(BST_Aed2<Integer, Empresa> empresaST, String path) {
        Out out = new Out(path);

        for (Integer key : empresaST.emordem()) {
            out.println(
                    empresaST.get(key).getId() + ";"
                            + empresaST.get(key).getNome() + ";"
                            + empresaST.get(key).getSigla() + ";"
                            + empresaST.get(key).getCoordenadax() + ";"
                            + empresaST.get(key).getCoordenaday() + ";"
                            + empresaST.get(key).getMyInteresse().getId() +";");

        }
    }

    public static void addEmpresa(BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Interesse> interesseST, String nome, String sigla, float coordenadax, float coordenaday, int id_interesse) {

        Interesse aux_interesse = interesseST.get(id_interesse);
        Empresa aux_empresa = new Empresa(empresaST.max()+1,nome,sigla,coordenadax,coordenaday,aux_interesse);
        empresaST.put(empresaST.max()+1, aux_empresa);

        System.out.println("Empresa inserida com sucesso");
    }

    public static void removeEmpresa(BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Pessoa> pessoaST, int id_empresa) {

        for(Integer key: pessoaST.emordem()) {
            if(pessoaST.get(key).getMyEmpresa()==null) {

            } else if(pessoaST.get(key).getMyEmpresa().getId() == id_empresa) {
               pessoaST.get(key).setMyEmpresa(null);
           }
        }

        empresaST.delete(id_empresa);

        System.out.println("Empresa removida com sucesso");
    }

    public static void editEmpresa(BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Interesse> interesseST, int id_empresa) {

        if (empresaST.contains(id_empresa)) {

            System.out.println("O que pretende alterar?");
            System.out.println("1- Nome");
            System.out.println("2- Sigla");
            System.out.println("3- Coordenada X");
            System.out.println("4- Coordenada Y");
            System.out.println("5- Area de Interesse");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {

                case "1":
                    String nome = scanIn.nextLine();
                    empresaST.get(id_empresa).setNome(nome);
                    break;
                case "2":
                    String sigla = scanIn.nextLine();
                    empresaST.get(id_empresa).setSigla(sigla);
                    break;
                case "3":
                    String coordenadax = scanIn.nextLine();
                    empresaST.get(id_empresa).setCoordenadax(Float.parseFloat(coordenadax));
                    break;
                case "4":
                    String coordenaday = scanIn.nextLine();
                    empresaST.get(id_empresa).setCoordenaday(Float.parseFloat(coordenaday));
                    break;
                case "5":
                    System.out.println("Que area de interesse deseja?");
                    Interesse.printInteresses(interesseST);
                    String interesse = scanIn.nextLine();
                    Interesse aux_interesse = interesseST.get(Integer.parseInt(interesse));
                    empresaST.get(id_empresa).setMyInteresse(aux_interesse);
                    break;
            }
            System.out.println("Empresa editada com sucesso");
        } else {
            System.out.println("Esta Empresa nao existe");
        }

    }

    public static void empresa_interesse(BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Interesse> interesseST, int id_interesse) {

        BST_Aed2<Integer, Empresa> aux_empresaST = new BST_Aed2<>();
        Interesse aux_interesse = interesseST.get(id_interesse);

        for(Integer key : empresaST.keys()) {
            if(empresaST.get(key).getMyInteresse()==aux_interesse) {
                aux_empresaST.put(key,empresaST.get(key));
            }
        }

        System.out.println("Empresas ligadas a area de: " + aux_interesse.getNome());
        printEmpresas_simple(aux_empresaST);

    }
}
