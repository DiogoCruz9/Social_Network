package projeto;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static BST_Aed2<Integer, Pessoa> pessoaST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Empresa> empresaST = new BST_Aed2<>();
    public static RedBlackBST<Date, Meeting> meetingST = new RedBlackBST<>();
    public static BST_Aed2<Integer, Historico> historicoST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Competencia> competenciaST = new BST_Aed2<>();
    public static BST_Aed2<Integer, Interesse> interesseST = new BST_Aed2<>();

    public static void main(String[] args) throws ParseException, FileNotFoundException {

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String path1 = ".//data//pessoas.txt";
        String path2 = ".//data//empresas.txt";
        String path3 = ".//data//meetings.txt";
        String path4 = ".//data//competencias.txt";
        String path5 = ".//data//interesses.txt";
        String path6 = ".//data//historicos.txt";

        String savepath1 = ".//data//savepessoas.txt";
        String savepath2 = ".//data//saveempresas.txt";
        String savepath3 = ".//data//savemeetings.txt";
        String savepath4 = ".//data//savecompetencias.txt";
        String savepath5 = ".//data//saveinteresses.txt";
        String savepath6 = ".//data//savehistoricos.txt";

        Competencia.loadCompetencia(competenciaST, path4);
        Interesse.loadInteresses(interesseST, path5);
        Empresa.loadEmpresa(empresaST, interesseST, path2);
        Historico.loadHistorico(historicoST, empresaST, path6);
        Pessoa.loadPessoa(pessoaST, empresaST, competenciaST, interesseST, historicoST, path1);
        Meeting.loadMeeting(meetingST, interesseST, empresaST, pessoaST, path3);

        // ST'S
        // COMPETENCIAS

        //Competencia.addCompetencia(competenciaST, "JAVA");
        //Competencia.removeCompetencia(competenciaST, 4);
        //Competencia.editCompetencia(competenciaST,4);
        //Competencia.printCompetencias(competenciaST);
        //Competencia.saveCompetencia(competenciaST, savepath4);

        // INTERESSES

        //Interesse.addInteresse(interesseST, "Economia");
        //Interesse.removeInteresse(interesseST, 2);
        //Interesse.editInteresse(interesseST, 4);
        //Interesse.printInteresses(interesseST);
        //Interesse.saveInteresse(interesseST, savepath5);

        // EMPRESAS

        //Empresa.addEmpresa(empresaST,interesseST,"Teste","TST", Float.parseFloat("1.23232"), Float.parseFloat("1.23232"), 2);
        //Empresa.removeEmpresa(empresaST, pessoaST, 3);
        //Empresa.editEmpresa(empresaST,interesseST,2);
        //Empresa.printEmpresas(empresaST);
        //Empresa.saveEmpresa(empresaST, savepath2);

        // HISTORICOS

        //Historico.addHistorico(historicoST,empresaST,1);
        //Historico.removeHistorico(historicoST,pessoaST,2);
        //Historico.printHistoricos(historicoST);
        //Historico.saveHistorico(historicoST, savepath6);

        // PESSOAS

        //Pessoa.addPessoa(pessoaST,empresaST,competenciaST,interesseST,historicoST,"Teste",30, "Lisboa");
        //Pessoa.removePessoa(pessoaST,empresaST,2);
        //Pessoa.printPessoas(pessoaST);
        //Pessoa.savePessoa(pessoaST, savepath1);

        // MEETINGS

        //Meeting.addMeeting(meetingST,interesseST,empresaST,pessoaST,"Encontro de Teste 222",Float.parseFloat("2.11111"),Float.parseFloat("1.212312321"), ft.parse("2019-06-20 22:30:00"));
        //Meeting.addPessoaToMeeting(meetingST,pessoaST,1);
        //Meeting.removeMeeting(meetingST,empresaST,1);
        //Meeting.printMeetings(meetingST);
        //Meeting.saveMeeting(meetingST, savepath3);

        // PESQUISAS

        //Pessoa.competencia_pessoa(pessoaST,competenciaST,5);
        //Pessoa.desempregrado(pessoaST);
        //Empresa.meetings_empresa(empresaST,3);
        //Meeting.meeting_npessoas(meetingST,1);
        //Meeting.meeting_between(meetingST, ft.parse("2019-05-20 22:30:00"), ft.parse("2019-07-20 22:30:00"));
        //Empresa.empresa_interesse(empresaST,interesseST,5);
        //Pessoa.pessoa_interesse(pessoaST,interesseST,3);
        //Meeting.meeting_interesse(meetingST,interesseST,2);
        //Pessoa.pessoa_semempresa_maiscompetencias(pessoaST);
    }
}
