package projeto;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Meeting {

    private int id;
    private String nome;
    private Date date;
    private float coordenadax;
    private float coordenaday;
    private Interesse myInteresse;
    private Empresa myEmpresa;
    private SeparateChainingHashST<Integer, Pessoa> pessoa_meetingST = new SeparateChainingHashST<>();

    public Meeting(int id, String nome, Date date, float coordenadax, float coordenaday, Interesse myInteresse, Empresa myEmpresa, SeparateChainingHashST<Integer, Pessoa> pessoa_meetingST) {
        this.id = id;
        this.nome = nome;
        this.date = date;
        this.coordenadax = coordenadax;
        this.coordenaday = coordenaday;
        this.myInteresse = myInteresse;
        this.myEmpresa = myEmpresa;
        this.pessoa_meetingST = pessoa_meetingST;
    }

    public Meeting(int id, String nome, Date date, float coordenadax, float coordenaday, Interesse myInteresse, Empresa myEmpresa) {
        this.id = id;
        this.nome = nome;
        this.date = date;
        this.coordenadax = coordenadax;
        this.coordenaday = coordenaday;
        this.myInteresse = myInteresse;
        this.myEmpresa = myEmpresa;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Empresa getMyEmpresa() {
        return myEmpresa;
    }

    public void setMyEmpresa(Empresa myEmpresa) {
        this.myEmpresa = myEmpresa;
    }

    public SeparateChainingHashST<Integer, Pessoa> getPessoa_meetingST() {
        return pessoa_meetingST;
    }

    public void setPessoa_meetingST(SeparateChainingHashST<Integer, Pessoa> pessoa_meetingST) {
        this.pessoa_meetingST = pessoa_meetingST;
    }

    public static void loadMeeting(RedBlackBST<Date, Meeting> meetingST, BST_Aed2<Integer, Interesse> interesseST, BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Pessoa> pessoaST, String path) {
        In in = new In(path);

        try {
            while (!in.isEmpty()) {

                int x = 7;
                SeparateChainingHashST<Integer, Pessoa> aux_pessoaST = new SeparateChainingHashST<>();
                RedBlackBST<Date, Meeting> aux_meetingST = new RedBlackBST<>();

                String[] token = in.readLine().split(";");
                String aux1 = token[0];
                String aux2 = token[1];
                String aux3 = token[2];
                String aux4 = token[3];
                String aux5 = token[4];
                String aux6 = token[5];
                Interesse aux_interesse = interesseST.get(Integer.parseInt(aux6));
                String aux7 = token[6];
                Empresa aux_empresa = empresaST.get(Integer.parseInt(aux7));
                String aux8 = token[7];
                int j = Integer.parseInt(aux8);
                for(int i=0;i<j;i++){
                    String aux9 = token[x+i+1];
                    Pessoa aux_pessoa = pessoaST.get(Integer.parseInt(aux9));
                    aux_pessoaST.put(i+1, aux_pessoa);
                }

                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Meeting aux_meeting = new Meeting(Integer.parseInt(aux1),aux2,ft.parse(aux3),Float.parseFloat(aux4),Float.parseFloat(aux5),aux_interesse,aux_empresa,aux_pessoaST);
                meetingST.put(ft.parse(aux3), aux_meeting);
                aux_meetingST = aux_empresa.getMeeting_empresaST();
                aux_meetingST.put(ft.parse(aux3), aux_meeting);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void printMeetings(RedBlackBST<Date, Meeting> meetingST) {
        System.out.println("################## Listagem dos Meetings##################" + "\n");
        for (Date key : meetingST.keys()) {
            print_meeting(meetingST, key);
        }
        System.out.println("\n");
    }

    public static void print_meeting(RedBlackBST<Date, Meeting> meetingST, Date key) {
        System.out.println("ID: " + meetingST.get(key).getId());
        System.out.println("Nome: " + meetingST.get(key).getNome());
        System.out.println("Organizado por: " + meetingST.get(key).getMyEmpresa().getNome());
        System.out.println("Data: " + meetingST.get(key).getDate());
        System.out.println("Area de Interesse: " + meetingST.get(key).getMyInteresse().getNome());
        System.out.println("Participantes: ");
        for(int i=0;i<meetingST.get(key).getPessoa_meetingST().size();i++) {
            System.out.println("- " + meetingST.get(key).getPessoa_meetingST().get(i+1).getNome());
        }
        System.out.println();
    }

    public static void meeting_npessoas(RedBlackBST<Date, Meeting> meetingST, Integer npessoas) {
        System.out.println("################## Meetings com mais de " + npessoas + " pessoa/s ##################" + "\n");
        for(Date key : meetingST.keys()) {
            if(meetingST.get(key).pessoa_meetingST.size()>npessoas) {
                print_meeting(meetingST, key);
            }
        }
    }

    public static void saveMeeting(RedBlackBST<Date, Meeting> meetingST, String path) {
        Out out = new Out(path);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Date key : meetingST.keys()) {
            out.print(
                    meetingST.get(key).getId() + ";"
                            + meetingST.get(key).getNome() + ";"
                            + formatter.format(meetingST.get(key).getDate()) + ";"
                            + meetingST.get(key).getCoordenadax() + ";"
                            + meetingST.get(key).getCoordenaday() + ";"
                            + meetingST.get(key).getMyInteresse().getId() +";"
                            + meetingST.get(key).getMyEmpresa().getId() + ";"
                            + meetingST.get(key).getPessoa_meetingST().size() + ";");
            for (int i = 0; i<meetingST.get(key).getPessoa_meetingST().size(); i++) {
                out.print(meetingST.get(key).getPessoa_meetingST().get(i+1).getId() + ";");
            }
            out.println();
        }
    }

    public static void addMeeting(RedBlackBST<Date, Meeting> meetingST, BST_Aed2<Integer, Interesse> interesseST, BST_Aed2<Integer, Empresa> empresaST, BST_Aed2<Integer, Pessoa> pessoaST, String nome, float coordenadax, float coordenaday, Date date) {

        System.out.println("Indique o ID do Interesse do evento:");
        Interesse.printInteresses(interesseST);

        Scanner scanIn = new Scanner(System.in);
        String choice = scanIn.nextLine();

        Interesse aux_interesse = interesseST.get(Integer.parseInt(choice));

        System.out.println("Indique o ID da Empresa do evento:");
        Empresa.printEmpresas_simple(empresaST);

        Scanner scanIn2 = new Scanner(System.in);
        String choice2 = scanIn2.nextLine();

        Empresa aux_empresa = empresaST.get(Integer.parseInt(choice2));

        Meeting aux_meeting = new Meeting(meetingST.size()+1,nome,date,coordenadax,coordenaday,aux_interesse,aux_empresa);
        RedBlackBST<Date, Meeting> aux_meetings = aux_empresa.getMeeting_empresaST();
        aux_meetings.put(date, aux_meeting);
        aux_empresa.setMeeting_empresaST(aux_meetings);
        meetingST.put(date,aux_meeting);

        System.out.println("Meeting adicionado com sucesso. ");

    }

    public static void addPessoaToMeeting(RedBlackBST<Date, Meeting> meetingST, BST_Aed2<Integer, Pessoa> pessoaST, int id_pessoa) {

        System.out.println("Indique o evento ao qual quer adicionar o participante " + pessoaST.get(id_pessoa).getNome());
        printMeetings(meetingST);

        Scanner scanIn = new Scanner(System.in);
        String choice = scanIn.nextLine();

        for (Date date : meetingST.keys()) {
            if(meetingST.get(date).getId()==Integer.parseInt(choice)) {
                Pessoa aux_pessoa = pessoaST.get(id_pessoa);
                Meeting aux_meeting = meetingST.get(date);
                SeparateChainingHashST<Integer, Pessoa> aux_pessoasmeetingST = aux_meeting.getPessoa_meetingST();
                if(aux_pessoasmeetingST.contains(id_pessoa)) {
                    System.out.println("Esta pessoa ja esta neste Meeting.");
                    break;
                }
                aux_pessoasmeetingST.put(id_pessoa, aux_pessoa);
                aux_meeting.setPessoa_meetingST(aux_pessoasmeetingST);

                System.out.println("Participante adicionado com sucesso!");
            }
        }
    }

    public static void removeMeeting(RedBlackBST<Date, Meeting> meetingST, BST_Aed2<Integer, Empresa> empresaST, int id_meeting) {

        for(Date date : meetingST.keys()) {
            if(meetingST.get(date).getId()==id_meeting) {
                Empresa aux_empresa = meetingST.get(date).getMyEmpresa();
                RedBlackBST<Date, Meeting> aux_meetings = aux_empresa.getMeeting_empresaST();
                aux_meetings.delete(date);
                meetingST.delete(date);
                System.out.println("Meeting Apagado com Sucesso");
            }

        }
    }

    public static void meeting_between(RedBlackBST<Date, Meeting> meetingST, Date date1, Date date2) {

        RedBlackBST<Date, Meeting> aux_meetings = new RedBlackBST<>();

        for(Date date : meetingST.keys()) {
            if(date.after(date1) && date.before(date2)) {
                Meeting aux_meeting = meetingST.get(date);
                aux_meetings.put(date, aux_meeting);
            }
        }

        System.out.println("Meetings entre " + date1 + " e " + date2);
        printMeetings(aux_meetings);
    }

    public static void meeting_interesse(RedBlackBST<Date, Meeting> meetingST, BST_Aed2<Integer, Interesse> interesseST, int id_interesse) {

        RedBlackBST<Date, Meeting> aux_meetingST = new RedBlackBST<>();
        Interesse aux_interesse = interesseST.get(id_interesse);

        for(Date key : meetingST.keys()) {
                if(aux_interesse==meetingST.get(key).getMyInteresse()) {
                    Meeting aux_meeting = meetingST.get(key);
                    aux_meetingST.put(key, aux_meeting);
                }
        }

        System.out.println("Meetings com interesse em: " + aux_interesse.getNome());
        printMeetings(aux_meetingST);

    }
}
