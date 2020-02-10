package projeto;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.security.SecurityPermission;
import java.util.Scanner;

public class Pessoa {

    private int id;
    private String nome;
    private int idade;
    private String localizacao;
    private Empresa myEmpresa;
    private SeparateChainingHashST<Integer, Competencia> competencia_pessoaST = new SeparateChainingHashST<>();
    private SeparateChainingHashST<Integer, Interesse> interesse_pessoaST = new SeparateChainingHashST<>();
    private Historico myHistorico;

    public Pessoa(int id, String nome, int idade, String localizacao, Empresa myEmpresa, SeparateChainingHashST<Integer, Competencia> competencia_pessoaST, SeparateChainingHashST<Integer, Interesse> interesse_pessoaST, Historico myHistorico) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.localizacao = localizacao;
        this.myEmpresa = myEmpresa;
        this.competencia_pessoaST = competencia_pessoaST;
        this.interesse_pessoaST = interesse_pessoaST;
        this.myHistorico = myHistorico;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Empresa getMyEmpresa() {
        return myEmpresa;
    }

    public void setMyEmpresa(Empresa myEmpresa) {
        this.myEmpresa = myEmpresa;
    }

    public SeparateChainingHashST<Integer, Competencia> getCompetencia_pessoaST() {
        return competencia_pessoaST;
    }

    public void setCompetencia_pessoaST(SeparateChainingHashST<Integer, Competencia> competencia_pessoaST) {
        this.competencia_pessoaST = competencia_pessoaST;
    }

    public SeparateChainingHashST<Integer, Interesse> getInteresse_pessoaST() {
        return interesse_pessoaST;
    }

    public void setInteresse_pessoaST(SeparateChainingHashST<Integer, Interesse> interesse_pessoaST) {
        this.interesse_pessoaST = interesse_pessoaST;
    }

    public Historico getMyHistorico() {
        return myHistorico;
    }

    public void setMyHistorico(Historico myHistorico) {
        this.myHistorico = myHistorico;
    }

    public static void loadPessoa(BST_Aed2<Integer, Pessoa> pessoaST, BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Competencia> competenciaST, BST_Aed2<Integer, Interesse> interesseST, BST_Aed2<Integer, Historico> historicoST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                int j = 5;
                SeparateChainingHashST<Integer, Competencia> aux_competenciast = new SeparateChainingHashST<>();
                SeparateChainingHashST<Integer, Interesse> aux_interessest = new SeparateChainingHashST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                String aux3 = token[2];
                String aux4 = token[3];
                String aux5 = token[4];

                Empresa aux_empresa = empresaST.get(Integer.parseInt(aux5));
                String aux6 = token[5];
                int i = Integer.parseInt(aux6);
                j = j+i+1; //8
                for(int x = 0; x<i;x++){
                    String aux7 = token[x+6];
                    Competencia aux_competencia = competenciaST.get(Integer.parseInt(aux7));
                    aux_competenciast.put(x+1,aux_competencia);
                }
                String aux8 = token[j];
                int z = Integer.parseInt(aux8);
                for(int p = 0; p<z; p++) {
                    String aux9 = token[p+j+1];
                    Interesse aux_interesse = interesseST.get(Integer.parseInt(aux9));
                    aux_interessest.put(p+1, aux_interesse);
                }
                j = j + z + 1;
                String aux10 = token[j];
                Historico aux_historico = historicoST.get(Integer.parseInt(aux10));
                if(Integer.parseInt(aux5)==0){
                    Pessoa aux_pessoa = new Pessoa(Integer.parseInt(aux1),aux2,Integer.parseInt(aux3),aux4,null,aux_competenciast,aux_interessest,aux_historico);
                    pessoaST.put(Integer.parseInt(aux1),aux_pessoa);
                }
                Pessoa aux_pessoa = new Pessoa(Integer.parseInt(aux1),aux2,Integer.parseInt(aux3),aux4,aux_empresa,aux_competenciast,aux_interessest,aux_historico);
                pessoaST.put(Integer.parseInt(aux1),aux_pessoa);
                SeparateChainingHashST<Integer, Pessoa> aux_pessoaempresaST = aux_empresa.getPessoa_empresaST();
                aux_pessoaempresaST.put(Integer.parseInt(aux1),aux_pessoa);
                aux_empresa.setPessoa_empresaST(aux_pessoaempresaST);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printPessoas(BST_Aed2<Integer, Pessoa> pessoaST) {
        System.out.println("##################Listagem das Pessoas##################" + "\n");
        for (Integer key : pessoaST.emordem()) {
            print_pessoa(pessoaST, key);
        }
        System.out.println("\n");
    }

    public static void print_pessoa(BST_Aed2<Integer, Pessoa> pessoaST, Integer key) {
        System.out.println("ID: " + pessoaST.get(key).getId());
        System.out.println("Nome: " + pessoaST.get(key).getNome());
        System.out.println("Idade: " + pessoaST.get(key).getIdade());
        System.out.println("Localizacao: " + pessoaST.get(key).getLocalizacao());
        if(pessoaST.get(key).getMyEmpresa()==null){
            System.out.println("Empresa: Desempregado");
        } else {
            System.out.println("Empresa: " + pessoaST.get(key).getMyEmpresa().getNome());
        }
        System.out.println("Competencias: ");
        for(int i=0;i<pessoaST.get(key).getCompetencia_pessoaST().size();i++) {
            System.out.println("- " + pessoaST.get(key).getCompetencia_pessoaST().get(i+1).getNome());
        }
        System.out.println("Areas de Interesse: ");
        for(int x=0;x<pessoaST.get(key).getInteresse_pessoaST().size();x++) {
            System.out.println("- " + pessoaST.get(key).getInteresse_pessoaST().get(x+1).getNome());
        }
        System.out.println("Historico de Empresas: ");
        if(pessoaST.get(key).getMyHistorico()==null) {
            System.out.println("- Sem Historico");
        } else {
            for(int j=0;j<pessoaST.get(key).getMyHistorico().getEmpresa_historicoST().size();j++) {
                System.out.println("- " + pessoaST.get(key).getMyHistorico().getEmpresa_historicoST().get(j+1).getNome());
            }
        }
        System.out.println();
    }

    public static void competencia_pessoa(BST_Aed2<Integer, Pessoa> pessoaST, BST_Aed2<Integer, Competencia> competenciaST, Integer id_comp) {

        System.out.println("##################Pessoa com Competencia: " + competenciaST.get(id_comp).getNome() + " ##################");
        for(Integer key : pessoaST.emordem()) {
            Pessoa aux_pessoa = pessoaST.get(key);
            for(int x=0;x<aux_pessoa.getCompetencia_pessoaST().size();x++) {
                if(aux_pessoa.getCompetencia_pessoaST().get(x+1).getId()==id_comp) {
                    print_pessoa(pessoaST,key);
                }
            }
        }
    }

    public static void desempregrado(BST_Aed2<Integer, Pessoa> pessoaST) {
        System.out.println("################## Pessoas sem Emprego##################" + "\n");
        for(Integer key : pessoaST.emordem()) {
            Pessoa aux_pessoa = pessoaST.get(key);
            if(aux_pessoa.getMyEmpresa()==null) {
                print_pessoa(pessoaST,key);
            }
        }
    }

    public static void savePessoa(BST_Aed2<Integer, Pessoa> pessoaST, String path) {
        Out out = new Out(path);

        for (Integer key : pessoaST.emordem()) {
            out.print(
                    pessoaST.get(key).getId() + ";"
                            + pessoaST.get(key).getNome() + ";"
                            + pessoaST.get(key).getIdade() + ";"
                            + pessoaST.get(key).getLocalizacao() + ";");
            if(pessoaST.get(key).getMyEmpresa()==null) {
                out.print(0 + ";");
            } else out.print(pessoaST.get(key).getMyEmpresa().getId() + ";");
                            out.print(pessoaST.get(key).getCompetencia_pessoaST().size() + ";");
            for(int i=0;i<pessoaST.get(key).getCompetencia_pessoaST().size();i++) {
                out.print(
                        pessoaST.get(key).getCompetencia_pessoaST().get(i+1).getId() + ";"
                );
            }
            out.print(pessoaST.get(key).getInteresse_pessoaST().size() + ";");
            for(int x=0;x<pessoaST.get(key).getInteresse_pessoaST().size();x++) {
                out.print(
                        pessoaST.get(key).getInteresse_pessoaST().get(x+1).getId() + ";"
                );
            }
            out.println(pessoaST.get(key).getMyHistorico().getId() + ";");
        }
    }

    public static void addPessoa(BST_Aed2<Integer, Pessoa> pessoaST, BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Competencia> competenciasST, BST_Aed2<Integer, Interesse> interesseST, BST_Aed2<Integer, Historico> historicoST, String nome, int idade, String localizacao) {

        System.out.println("Indique o Id da Empresa da nova Pessoa: (0 = Desempregrado)");
        Empresa.printEmpresas_simple(empresaST);

        Scanner scanIn = new Scanner(System.in);
        String choice = scanIn.nextLine();

        if(Integer.parseInt(choice)==0) {
            Empresa aux_empresa = null;
        }
        Empresa aux_empresa = empresaST.get(Integer.parseInt(choice));

        System.out.println("Indique o numero de competencias da nova pessoa: ");
        Scanner scanIn2 = new Scanner(System.in);
        String choice2 = scanIn2.nextLine();
        System.out.println("Indique agora o id das " + Integer.parseInt(choice2) + " competencias.");
        Competencia.printCompetencias(competenciasST);
        SeparateChainingHashST<Integer, Competencia> aux_competenciaST = new SeparateChainingHashST<>();
        for(int i=0;i<Integer.parseInt(choice2);i++) {
            Scanner scanIn3 = new Scanner(System.in);
            String choice3 = scanIn3.nextLine();
            Competencia aux_competencia = competenciasST.get(Integer.parseInt(choice3));
            aux_competenciaST.put(i+1, aux_competencia);
        }

        System.out.println("Indique o numero de interesses da nova pessoa: ");
        Scanner scanIn4 = new Scanner(System.in);
        String choice4 = scanIn4.nextLine();
        System.out.println("Indique agora o id dos " + Integer.parseInt(choice4) + " interesses.");
        Interesse.printInteresses(interesseST);
        SeparateChainingHashST<Integer, Interesse> aux_interesseST = new SeparateChainingHashST<>();
        for(int x=0; x<Integer.parseInt(choice4);x++) {
            Scanner scanIn5 = new Scanner(System.in);
            String choice5 = scanIn5.nextLine();
            Interesse aux_interesse = interesseST.get(Integer.parseInt(choice5));
            aux_interesseST.put(x+1, aux_interesse);
        }

        System.out.println("Indique o numero do Historico da nova pessoa: ");
        Historico.printHistoricos(historicoST);
        Scanner scanIn6 = new Scanner(System.in);
        String choice6 = scanIn6.nextLine();
        if(Integer.parseInt(choice6)==0) {
            Historico aux_historico = null;
        }
        Historico aux_historico = historicoST.get(Integer.parseInt(choice6));

        Pessoa aux_pessoa = new Pessoa(pessoaST.max()+1,nome,idade,localizacao,aux_empresa,aux_competenciaST,aux_interesseST,aux_historico);
        pessoaST.put(pessoaST.max()+1,aux_pessoa);

        if(aux_empresa!=null) {
            SeparateChainingHashST<Integer, Pessoa> aux_pessoas_empresast = aux_empresa.getPessoa_empresaST();
            aux_pessoas_empresast.put(aux_pessoas_empresast.size()+1,aux_pessoa);
        }

        System.out.println("Pessoa inserida com sucesso");
    }

    public static void removePessoa(BST_Aed2<Integer, Pessoa> pessoaST, BST_Aed2<Integer, Empresa> empresaST, int id) {

        Pessoa aux_pessoa = pessoaST.get(id);
        if(aux_pessoa.getMyEmpresa()!=null) {
            SeparateChainingHashST<Integer, Pessoa> aux_pessoaempresaST = aux_pessoa.getMyEmpresa().getPessoa_empresaST();
            aux_pessoaempresaST.delete(aux_pessoa.getId());
            Empresa aux_empresa = aux_pessoa.getMyEmpresa();
            aux_empresa.setPessoa_empresaST(aux_pessoaempresaST);
        }

        pessoaST.delete(id);

        System.out.println("Pessoa removida com sucesso");
    }

    public static void pessoa_interesse(BST_Aed2<Integer, Pessoa> pessoaST, BST_Aed2<Integer, Interesse> interesseST, int id_interesse) {

        BST_Aed2<Integer, Pessoa> aux_pessoaST = new BST_Aed2<>();
        Interesse aux_interesse = interesseST.get(id_interesse);

        for(Integer key : pessoaST.keys()) {
            SeparateChainingHashST<Integer, Interesse> aux_interesseST = pessoaST.get(key).getInteresse_pessoaST();
            for(Integer key2 : aux_interesseST.keys()) {
                if(aux_interesse==aux_interesseST.get(key2)) {
                    Pessoa aux_pessoa = pessoaST.get(key);
                    aux_pessoaST.put(key, aux_pessoa);
                }
            }
        }

        System.out.println("Pessoas com interesse em: " + aux_interesse.getNome());
        printPessoas(aux_pessoaST);
    }

    public static void pessoa_semempresa_maiscompetencias(BST_Aed2<Integer,Pessoa> pessoaST) {

        int max = 0;
        int aux_key = 0;
        for (Integer keys: pessoaST.keys()) {
            if(pessoaST.get(keys).getMyEmpresa()==null) {
                if(pessoaST.get(keys).getCompetencia_pessoaST().size()>max) {
                    max = pessoaST.get(keys).getCompetencia_pessoaST().size();
                    aux_key = keys;
                }
            }
        }

        System.out.println("Pessoa desempregagada com mais competencias: ");
        print_pessoa(pessoaST, aux_key);
    }
}
