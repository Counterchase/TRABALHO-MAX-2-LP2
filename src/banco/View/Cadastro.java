/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco.View;

import banco.AdminTableModel;
import banco.Banco;
import banco.CadastroClinica;
import banco.CadastroMedico;
import banco.ClienteTableModel;
import banco.ClinicaTableModel;
import banco.MedicoTableModel;
import banco.Model.Admin;
import banco.Model.Cliente;
import banco.Model.Medico;
import banco.Model.Clinica;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author IFMS
 */
public final class Cadastro extends javax.swing.JFrame {

    private Connection conn;

    private List<Admin> Admins;
    private List<Clinica> Clinicas;
    private List<Medico> Medicos;
    private List<Cliente> Clientes;

    private List<Medico> lmedico;
    private List<Clinica> lclinica;
    private List<Admin> ladmin;
    private List<Cliente> lcliente;

    /**
     * ALINHANOD TABELAS
     * =============================================================================================================================*
     */
    public void alinharTbAdmins(JTable tb) {
        AdminTableModel modeloAdmin = new AdminTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void alinharTbClinicas(JTable tb) {
        ClinicaTableModel modeloClinica = new ClinicaTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void alinharTbMedicos(JTable tb) {
        MedicoTableModel modeloMedico = new MedicoTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    public void alinharTbClientes(JTable tb) {
        ClienteTableModel modeloCliente = new ClienteTableModel();

        DefaultTableCellRenderer dtcr = new DefaultTableCellHeaderRenderer();

        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < 4; i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }
    }

    /*========================================================================================================================================*/

 /*LISTANDO TABELAS ==================================================================================*/
    public List<Admin> listarTbAdmin() throws SQLException {
        Admins = new ArrayList<>();
        lclinica = new ArrayList<>();
        ladmin = new ArrayList<>();
        lcliente = new ArrayList<>();
        lmedico = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idadmin, nome, login, senha, adm FROM Admin";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            System.out.print("id: " + res.getInt("idadmin"));
            System.out.print("nome: " + res.getString("nome"));
            System.out.print("login: " + res.getString("login"));
            System.out.print("senha: " + res.getString("senha"));
            System.out.print("adm: " + res.getInt("adm"));

            Admin a = new Admin();
            a.setIdadmin(res.getInt("idadmin"));
            a.setNome(res.getString("nome"));
            a.setLogin(res.getString("login"));
            a.setSenha(res.getString("senha"));
            a.setAdm(res.getInt("adm"));

            Admins.add(a);
        }
        stmt.close();
        conn.close();
        return Admins;

    }

    public List<Clinica> listarTbClinica() throws SQLException {
        Clinicas = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idclinica, nome, cnpj, cidadeclinica, leito FROM Clinica";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            System.out.print("id: " + res.getInt("idclinica"));
            System.out.print("nome: " + res.getString("nome"));
            System.out.print("cnpj: " + res.getString("cnpj"));
            System.out.print("cidade clinica: " + res.getString("cidadeclinica"));

            Clinica a = new Clinica();;
            a.setIdclinica(res.getInt("idclinica"));
            a.setNome(res.getString("nome"));
            a.setCnpj(res.getString("cnpj"));
            a.setCidadeclinica(res.getString("cidadeclinica"));
            a.setLeito(Integer.valueOf(res.getInt("leito")));

            Clinicas.add(a);
        }
        stmt.close();
        conn.close();
        return Clinicas;

    }

    public List<Medico> listarTbMedico() throws SQLException {
        Medicos = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }
        String sql = "SELECT idmedico, nome, cpf, crm, datanascimento, idclinica, idadmin FROM Medico";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {

            Medico a = new Medico();;
            a.setIdmedico(res.getInt("idmedico"));
            a.setNome(res.getString("nome"));
            a.setCpf(res.getString("cpf").toString());
            a.setCrm(res.getString("crm"));

            Calendar c = Calendar.getInstance();
            c.setTime(res.getDate("datanascimento"));
            a.setDatanascimento(c);

            a.setIdclinica(res.getInt("idclinica"));
            a.setIdadmin(res.getInt("idadmin"));
            Medicos.add(a);
        }
        stmt.close();
        conn.close();
        return Medicos;

    }

    public List<Cliente> listarTbCliente() throws SQLException {
        Clientes = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }
        String sql = "SELECT idcliente, nome, cpf, rg, datanascimento, idclinica, idmedico FROM Cliente";
        System.out.println("sql: " + sql);

        //atravez desse objeto usamos comandos sql
        Statement stmt = conn.createStatement();

        //select
        ResultSet res = stmt.executeQuery(sql);
        int countClientes = 0;

        while (res.next()) {

            Cliente a = new Cliente();;
            a.setIdcliente(res.getInt("idcliente"));
            a.setNome(res.getString("nome"));
            a.setCpf(res.getString("cpf").toString());
            a.setRg(res.getString("rg"));

            Calendar c = Calendar.getInstance();
            c.setTime(res.getDate("datanascimento"));
            a.setDatanascimento(c);

            a.setIdclinica(res.getInt("idclinica"));
            a.setIdmedico(res.getInt("idmedico"));

            Clientes.add(a);
            countClientes++;
        }
        stmt.close();
        conn.close();
        return Clientes;

    }
    
     

    /*==========================================================================================================================================*/
 /*LISTAR CLINICA E ADMIN COMBO BOX =============================================================================================================*/
    public List<Admin> listarAdmins() throws SQLException {

        List<Admin> Admins = new ArrayList<>();

        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idadmin, nome,login, senha, adm FROM admin";

        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println("Id:  " + res.getInt("idadmin"));
            System.out.println("Nome:  " + res.getString("nome"));
            System.out.println("Login:  " + res.getString("login"));
            System.out.println("Senha:  " + res.getString("senha"));
            System.out.println("Adm:  " + res.getInt("adm"));

            Admin b = new Admin();
            b.setIdadmin(res.getInt("idadmin"));
            b.setNome(res.getString("nome"));
            b.setLogin(res.getString("login"));
            b.setSenha(res.getString("senha"));
            b.setAdm(res.getInt("adm"));

            Admins.add(b);

        }
        return Admins;

    }

    public List<Clinica> listarClinicas() throws SQLException {

        List<Clinica> Clinicas = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idclinica, nome,cnpj, cidadeclinica, leito FROM Clinica";

        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {

            Clinica a = new Clinica();
            a.setIdclinica(res.getInt("idclinica"));
            a.setNome(res.getString("nome"));
            a.setCnpj(res.getString("cnpj"));
            a.setCidadeclinica(res.getString("cidadeclinica"));

            Clinicas.add(a);

        }
        return Clinicas;

    }

    public List<Medico> listarMedicos() throws SQLException {

        List<Medico> medicos = new ArrayList<>();
        conn = Banco.conecta();
        if (conn == null || conn.isClosed()) {
            System.out.println("erro ao conectar ao banco de dados");
            System.exit(-1);
        }

        String sql = "SELECT idmedico, nome, cpf, crm, datanascimento, idclinica, idadmin FROM Medico";

        System.out.println("sql: " + sql);
        Statement stmt = conn.createStatement();

        //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {

            Medico a = new Medico();
            a.setIdmedico(res.getInt("idmedico"));
            a.setNome(res.getString("nome"));
            a.setCpf(res.getString("cpf"));
            a.setCrm(res.getString("crm"));

            Calendar c = Calendar.getInstance();
            c.setTime(res.getDate("datanascimento"));
            a.setDatanascimento(c);

            a.setIdclinica(res.getInt("idclinica"));
            a.setIdadmin(res.getInt("idadmin"));
            medicos.add(a);

        }
        return medicos;

    }

    /*==========================================================================================================================*/
 /*INICIALIZANDO COMPONENTESS====================================================================================================================*/
    public Cadastro() {
        initComponents();
        setLocationRelativeTo(null);
        lmedico = new ArrayList<>();
        lclinica = new ArrayList<>();
        ladmin = new ArrayList<>();

        try {
            Admins = listarTbAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Clinicas = listarTbClinica();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Medicos = listarTbMedico();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Clientes = listarTbCliente();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            lclinica = listarClinicas();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ladmin = listarAdmins();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            lmedico = listarMedicos();
        } catch (SQLException ex) {
            Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
        }

        AdminTableModel modeloAdmin = new AdminTableModel();
        modeloAdmin.setListaAdmins(Admins);

        tbAdmin.setModel(modeloAdmin);

        ClinicaTableModel modeloClinica = new ClinicaTableModel();
        modeloClinica.setListaClinicas(Clinicas);

        tbClinica.setModel(modeloClinica);
        alinharTbClinicas(tbClinica);

        //  alinharTbAdmins(tbAdmin);
        MedicoTableModel modeloMedico = new MedicoTableModel();
        modeloMedico.setListamedicos(Medicos);

        tbMedico.setModel(modeloMedico);

        //  alinharTbAdmins(tbAdmin);
        ClienteTableModel modeloCliente = new ClienteTableModel();
        modeloCliente.setListaClientes(Clientes);

        tbCliente.setModel(modeloCliente);

        for (int i = 0; i < lclinica.size(); i++) {
            combClinicaMedico.addItem(lclinica.get(i));
            combClinicaCliente.addItem(lclinica.get(i));

        }

        for (int i = 0; i < ladmin.size(); i++) {
            combAdminMedico.addItem(ladmin.get(i));

        }

        for (int i = 0; i < lmedico.size(); i++) {
            combMedicoCliente.addItem(lmedico.get(i));

        }

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            //encerrou a conexão
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(CadastroClinica.class.getName()).log(Level.SEVERE, null, ex);
        }

        //txtDataNascimento.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        txtDataNascimentoMedico.setFormats("dd/MM/yyyy");
        txtDataNascimentoMedico.setDate(Calendar.getInstance().getTime());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        admin = new javax.swing.JPanel();
        txtNomeAdmin = new javax.swing.JTextField();
        txtLoginAdmin = new javax.swing.JTextField();
        btnCadastrarAdmin = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAdmin = new javax.swing.JTable();
        btnExcluirAdmin = new javax.swing.JButton();
        btnEditarAdmin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkAdm = new javax.swing.JCheckBox();
        txtSenhaAdmin = new javax.swing.JPasswordField();
        txtSenhaAdmin2 = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Menu = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        Cliente = new javax.swing.JPanel();
        btnCadastrarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        txtCpfCliente = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        combClinicaCliente = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtDataNascimentoCliente = new org.jdesktop.swingx.JXDatePicker();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCliente = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        btnExcluirCliente = new javax.swing.JButton();
        txtNomeCliente = new javax.swing.JTextField();
        txtRgCliente = new javax.swing.JFormattedTextField();
        combMedicoCliente = new javax.swing.JComboBox<>();
        clinica = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbClinica = new javax.swing.JTable();
        btnExcluirClinica = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnEditarClinica = new javax.swing.JButton();
        txtNomeClinica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCnpjClinica = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCidadeClinica = new javax.swing.JTextField();
        btnCadastrarClinica = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        txtLeitoClinica = new javax.swing.JFormattedTextField();
        medico = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtDataNascimentoMedico = new org.jdesktop.swingx.JXDatePicker();
        combAdminMedico = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnExcluirMedico = new javax.swing.JButton();
        txtCrmMedico = new javax.swing.JTextField();
        btnEditarMedico = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        combClinicaMedico = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedico = new javax.swing.JTable();
        txtNomeMedico = new javax.swing.JTextField();
        jButton1Medico = new javax.swing.JButton();
        txtCpfMedico = new javax.swing.JFormattedTextField();

        admin.setBackground(new java.awt.Color(255, 255, 255));

        txtNomeAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeAdminActionPerformed(evt);
            }
        });

        txtLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginAdminActionPerformed(evt);
            }
        });

        btnCadastrarAdmin.setText("Cadastrar");
        btnCadastrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarAdminActionPerformed(evt);
            }
        });

        tbAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbAdmin);

        btnExcluirAdmin.setText("Excluir");
        btnExcluirAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAdminActionPerformed(evt);
            }
        });

        btnEditarAdmin.setText("Editar");
        btnEditarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarAdminActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        jLabel2.setText("Login:");

        jLabel3.setText("Senha:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Cadastro de Admins");

        chkAdm.setText("ADMINISTRADOR?");
        chkAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAdmActionPerformed(evt);
            }
        });

        txtSenhaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaAdminActionPerformed(evt);
            }
        });

        txtSenhaAdmin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaAdmin2ActionPerformed(evt);
            }
        });

        jLabel16.setText("Confirma senha:");

        javax.swing.GroupLayout adminLayout = new javax.swing.GroupLayout(admin);
        admin.setLayout(adminLayout);
        adminLayout.setHorizontalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adminLayout.createSequentialGroup()
                        .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeAdmin))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(btnExcluirAdmin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditarAdmin))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(adminLayout.createSequentialGroup()
                                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLoginAdmin)
                                    .addGroup(adminLayout.createSequentialGroup()
                                        .addComponent(txtSenhaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSenhaAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(adminLayout.createSequentialGroup()
                        .addComponent(btnCadastrarAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkAdm)
                        .addGap(85, 85, 85))))
        );
        adminLayout.setVerticalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(21, 21, 21)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoginAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSenhaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenhaAdmin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirAdmin)
                    .addComponent(btnEditarAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarAdmin)
                    .addComponent(chkAdm))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/NQZSGBCO_400x400.png"))); // NOI18N

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/icons8-sair-24.png"))); // NOI18N
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel17))
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        btnLogout.getAccessibleContext().setAccessibleDescription("\n");

        jTabbedPane1.addTab("<html><b>Menu", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/icons8-mais-filled-24.png")), Menu); // NOI18N

        Cliente.setBackground(new java.awt.Color(255, 255, 255));

        btnCadastrarCliente.setText("Cadastrar");
        btnCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("Editar");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        try {
            txtCpfCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfClienteActionPerformed(evt);
            }
        });

        jLabel18.setText("CPF:");

        jLabel19.setText("Clinica:");

        combClinicaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combClinicaClienteActionPerformed(evt);
            }
        });

        jLabel20.setText("Nome:");

        jLabel21.setText("Medico:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Cadastro de Pacientes");

        txtDataNascimentoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoClienteActionPerformed(evt);
            }
        });

        jLabel23.setText("Data de Nascimento:");

        tbCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tbCliente);

        jLabel24.setText("RG:");

        btnExcluirCliente.setText("Excluir");
        btnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClienteActionPerformed(evt);
            }
        });

        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });

        try {
            txtRgCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        combMedicoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combMedicoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClienteLayout = new javax.swing.GroupLayout(Cliente);
        Cliente.setLayout(ClienteLayout);
        ClienteLayout.setHorizontalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClienteLayout.createSequentialGroup()
                        .addComponent(btnExcluirCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarCliente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClienteLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(2, 2, 2)
                        .addComponent(txtNomeCliente))
                    .addGroup(ClienteLayout.createSequentialGroup()
                        .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRgCliente)
                            .addComponent(txtCpfCliente)))
                    .addGroup(ClienteLayout.createSequentialGroup()
                        .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCadastrarCliente)
                                    .addGroup(ClienteLayout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(ClienteLayout.createSequentialGroup()
                                    .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel19))
                                    .addGap(76, 76, 76)
                                    .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(combClinicaCliente, 0, 250, Short.MAX_VALUE)
                                        .addComponent(combMedicoCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ClienteLayout.setVerticalGroup(
            ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtRgCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtCpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimentoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(combClinicaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(combMedicoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(ClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirCliente)
                    .addComponent(btnEditarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><b>Paciente", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/icons8-gestão-de-cliente-filled-24.png")), Cliente, "Cadastre um Paciente"); // NOI18N

        clinica.setBackground(new java.awt.Color(255, 255, 255));
        clinica.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Cadastro de Clínicas");

        tbClinica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbClinica);

        btnExcluirClinica.setText("Excluir");
        btnExcluirClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClinicaActionPerformed(evt);
            }
        });

        jLabel5.setText("Nome:");

        btnEditarClinica.setText("Editar");
        btnEditarClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClinicaActionPerformed(evt);
            }
        });

        txtNomeClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClinicaActionPerformed(evt);
            }
        });

        jLabel6.setText("CNPJ:");

        txtCnpjClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjClinicaActionPerformed(evt);
            }
        });

        jLabel7.setText("Cidade:");

        btnCadastrarClinica.setText("Cadastrar");
        btnCadastrarClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarClinicaActionPerformed(evt);
            }
        });

        jLabel25.setText("Leitos Disponiveis:");

        txtLeitoClinica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##"))));
        txtLeitoClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLeitoClinicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clinicaLayout = new javax.swing.GroupLayout(clinica);
        clinica.setLayout(clinicaLayout);
        clinicaLayout.setHorizontalGroup(
            clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clinicaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeClinica))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(btnCadastrarClinica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLeitoClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(btnExcluirClinica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 517, Short.MAX_VALUE)
                        .addComponent(btnEditarClinica))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeClinica))
                    .addGroup(clinicaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCnpjClinica)))
                .addContainerGap())
        );
        clinicaLayout.setVerticalGroup(
            clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clinicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(19, 19, 19)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNomeClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCnpjClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCidadeClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirClinica)
                    .addComponent(btnEditarClinica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clinicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarClinica)
                    .addComponent(jLabel25)
                    .addComponent(txtLeitoClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html> <b>Clínica</b> </html>", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/iconfinder_hospital-o_1608931 (1).png")), clinica, "Cadastre Clinicas\n"); // NOI18N

        medico.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Admin:");

        txtDataNascimentoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoMedicoActionPerformed(evt);
            }
        });

        combAdminMedico.setToolTipText("");
        combAdminMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combAdminMedicoActionPerformed(evt);
            }
        });

        jLabel10.setText("CRM:");

        btnExcluirMedico.setText("Excluir");
        btnExcluirMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMedicoActionPerformed(evt);
            }
        });

        btnEditarMedico.setText("Editar");
        btnEditarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMedicoActionPerformed(evt);
            }
        });

        jLabel11.setText("CPF:");

        jLabel12.setText("Clinica:");

        combClinicaMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combClinicaMedicoActionPerformed(evt);
            }
        });

        jLabel13.setText("Nome:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Cadastro de Médicos");

        jLabel15.setText("Data de Nascimento:");

        tbMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbMedico);

        txtNomeMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeMedicoActionPerformed(evt);
            }
        });

        jButton1Medico.setText("Cadastrar");
        jButton1Medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1MedicoActionPerformed(evt);
            }
        });

        try {
            txtCpfMedico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout medicoLayout = new javax.swing.GroupLayout(medico);
        medico.setLayout(medicoLayout);
        medicoLayout.setHorizontalGroup(
            medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicoLayout.createSequentialGroup()
                        .addComponent(btnExcluirMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditarMedico))
                    .addGroup(medicoLayout.createSequentialGroup()
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(medicoLayout.createSequentialGroup()
                                    .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel12))
                                    .addGap(76, 76, 76)
                                    .addComponent(combAdminMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(combClinicaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1Medico)
                                    .addGroup(medicoLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDataNascimentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicoLayout.createSequentialGroup()
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13))
                        .addGap(2, 2, 2)
                        .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCrmMedico)
                            .addComponent(txtNomeMedico)))
                    .addGroup(medicoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCpfMedico)))
                .addContainerGap())
        );
        medicoLayout.setVerticalGroup(
            medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNomeMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCrmMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCpfMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimentoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(combClinicaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combAdminMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(27, 27, 27)
                .addGroup(medicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirMedico)
                    .addComponent(btnEditarMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1Medico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html><b>Médico</b>", new javax.swing.ImageIcon(getClass().getResource("/banco/IMG/iconfinder_49_3678412.png")), medico, "Cadastre um Médico\n"); // NOI18N

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1MedicoActionPerformed
        conn = Banco.conecta();
        String Mostrar = null;
        String sql = "";

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoMedico.getDate();

            String nome = "'" + txtNomeMedico.getText() + "'";
            String crm = "'" + txtCrmMedico.getText() + "'";
            String cpf = "'" + txtCpfMedico.getText() + "'";

            String dataNasc = "'" + sdf.format(aux) + "'";
            if (txtNomeMedico.getText().isEmpty() || txtCrmMedico.getText().isEmpty() || txtCpfMedico.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!!");
            } else {

                Statement stmt = conn.createStatement();

                sql = "INSERT INTO Medico (nome,crm, cpf, datanascimento, idclinica, idadmin ) VALUES ("
                        + "" + nome + "," + crm + "," + cpf + "," + dataNasc + "," + ((Clinica) combClinicaMedico.getSelectedItem()).getIdclinica() + ","
                        + " " + ((Admin) combAdminMedico.getSelectedItem()).getIdadmin() + ")";
                System.out.println("sql: " + sql);

                //atravez desse objeto usamos comandos sql
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                //select
                //stmt.executeQuery(sql);
                //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete

                //encerrou a conexão
                stmt.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Médico(a) cadastrado!");

                txtNomeMedico.setText(Mostrar);
                txtCpfMedico.setText(Mostrar);
                txtCrmMedico.setText(Mostrar);

                Medicos = listarTbMedico();

                MedicoTableModel modelo = new MedicoTableModel();
                modelo.setListamedicos(Medicos);

                tbMedico.setModel(modelo);

                combMedicoCliente.removeAllItems();

                lmedico = listarMedicos();

                for (int i = 0; i < lmedico.size(); i++) {
                    combMedicoCliente.addItem(lmedico.get(i));
                }

                //   alinharTbMedicos(tbMedico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MedicoActionPerformed

    private void txtNomeMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeMedicoActionPerformed

    }//GEN-LAST:event_txtNomeMedicoActionPerformed

    private void combClinicaMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combClinicaMedicoActionPerformed

    }//GEN-LAST:event_combClinicaMedicoActionPerformed

    private void btnEditarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMedicoActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoMedico.getDate();

            String nome = "'" + txtNomeMedico.getText() + "'";
            String crm = "'" + txtCrmMedico.getText() + "'";
            String cpf = "'" + txtCpfMedico.getText() + "'";
            String dataNasc = "'" + sdf.format(aux) + "'";

            int k = tbMedico.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Porfavor Complete os Campos e Selecione a Linha na Tabela a ser editada e em Seguida clicke no botao Editar");
            } else {
                // System.out.println(k);
                int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();

                Statement stmt = conn.createStatement();
                String sql = "UPDATE medico SET nome = " + nome + ",crm = " + crm + ",cpf = " + cpf + ",datanascimento = " + dataNasc + "WHERE idmedico = " + i + "";
                stmt.executeUpdate(sql);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Médico(a) Editado!");

                String Mostrar = " ";
                txtNomeMedico.setText(Mostrar);
                txtCpfMedico.setText(Mostrar);
                txtCrmMedico.setText(Mostrar);

                Medicos = listarTbMedico();
                MedicoTableModel modelo = new MedicoTableModel();
                modelo.setListamedicos(Medicos);
                tbMedico.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarMedicoActionPerformed

    private void btnExcluirMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMedicoActionPerformed

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbMedico.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            } else {
                // System.out.println(k);
                int i = ((MedicoTableModel) tbMedico.getModel()).getListamedicos().get(k).getIdmedico();

                Statement stmt = conn.createStatement();
                String sql = "Delete from medico where idmedico = " + i + " ";
                stmt.executeUpdate(sql);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Médico(a) Apagado!");

                String Mostrar = " ";
                txtNomeMedico.setText(Mostrar);
                txtCpfMedico.setText(Mostrar);
                txtCrmMedico.setText(Mostrar);

                Medicos = listarTbMedico();
                MedicoTableModel modelo = new MedicoTableModel();
                modelo.setListamedicos(Medicos);
                tbMedico.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirMedicoActionPerformed

    private void txtDataNascimentoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoMedicoActionPerformed

    @SuppressWarnings("empty-statement")
    private void btnEditarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarAdminActionPerformed
        String Mostrar = null;
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            String nome = "'" + txtNomeAdmin.getText() + "'";
            String login = "'" + txtLoginAdmin.getText() + "'";
            String senha = "'" + txtSenhaAdmin.getText() + "'";
            Integer adm = 0;
            if (chkAdm.isSelected()) {
                adm = 1;
            } else {
                adm = 0;
            };

            int k = tbAdmin.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Porfavor Complete os Campos e Selecione a Linha na Tabela a ser editada e em Seguida clicke no botao Editar");
                txtSenhaAdmin.setText(Mostrar);
                txtSenhaAdmin2.setText(Mostrar);
            } else {
                // System.out.println(k);

                int i = ((AdminTableModel) tbAdmin.getModel()).getListaAdmins().get(k).getIdadmin();

                try (Statement stmt = conn.createStatement()) {
                    String sql = "UPDATE admin SET nome = " + nome + ",login = " + login + ",senha = " + senha + ",adm = " + adm + " WHERE idadmin = " + i + "";
                    stmt.executeUpdate(sql);
                }
                conn.close();

                JOptionPane.showMessageDialog(null, "Admin Editado!");

                txtNomeAdmin.setText(Mostrar);
                txtLoginAdmin.setText(Mostrar);
                txtSenhaAdmin.setText(Mostrar);
                txtSenhaAdmin2.setText(Mostrar);

                Admins = listarTbAdmin();
                AdminTableModel modelo = new AdminTableModel();
                modelo.setListaAdmins(Admins);
                tbAdmin.setModel(modelo);

                combAdminMedico.removeAllItems();

                ladmin = listarAdmins();

                for (int i2 = 0; i2 < ladmin.size(); i2++) {
                    combAdminMedico.addItem(ladmin.get(i2));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarAdminActionPerformed

    private void btnExcluirAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAdminActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbAdmin.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            } else {

                // System.out.println(k);
                int i = ((AdminTableModel) tbAdmin.getModel()).getListaAdmins().get(k).getIdadmin();

                Statement stmt = conn.createStatement();
                if (i == 1) {
                    JOptionPane.showMessageDialog(null, "Nao é Possivel apagar o Admin Nativo!");
                } else {

                    String sql = "Delete from admin where idadmin = " + i + " ";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Admin Apagado!");

                }

                stmt.close();
                conn.close();

                String Mostrar = " ";
                txtNomeAdmin.setText(Mostrar);
                txtLoginAdmin.setText(Mostrar);
                txtSenhaAdmin.setText(Mostrar);

                Admins = listarTbAdmin();
                AdminTableModel modelo = new AdminTableModel();
                modelo.setListaAdmins(Admins);
                tbAdmin.setModel(modelo);

                combAdminMedico.removeAllItems();
                ladmin = listarAdmins();

                for (int i3 = 0; i3 < ladmin.size(); i3++) {
                    combAdminMedico.addItem(ladmin.get(i3));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirAdminActionPerformed

    @SuppressWarnings("empty-statement")
    private void btnCadastrarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarAdminActionPerformed
        String Mostrar = null;
        conn = Banco.conecta();
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            String nome = "'" + txtNomeAdmin.getText() + "'";
            String login = "'" + txtLoginAdmin.getText() + "'";
            String senha = "'" + txtSenhaAdmin.getText() + "'";
            String senha2 = "'" + txtSenhaAdmin2.getText() + "'";

            if (senha.equals(senha2)) {

                Integer adm = null;
                if (chkAdm.isSelected()) {
                    adm = 1;
                } else {
                    adm = 0;
                };

                if (txtNomeAdmin.getText().isEmpty() || txtLoginAdmin.getText().isEmpty() || txtSenhaAdmin.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!!");
                } else {

                    String sql = "INSERT INTO admin(nome, login, senha, adm) VALUES ("
                            + "" + nome + "," + login + "," + senha + "," + adm + ")";
                    System.out.println("sql: " + sql);

                    //atravez desse objeto usamos comandos sql
                    Statement stmt = conn.createStatement();

                    //select
                    //stmt.executeQuery(sql);
                    //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Admin" + nome + "cadastrado!!");

                    //encerrou a conexão
                    stmt.close();
                    conn.close();

                    txtNomeAdmin.setText(Mostrar);
                    txtLoginAdmin.setText(Mostrar);
                    txtSenhaAdmin.setText(Mostrar);
                    txtSenhaAdmin2.setText(Mostrar);

                    Admins = listarTbAdmin();

                    AdminTableModel modelo = new AdminTableModel();
                    modelo.setListaAdmins(Admins);

                    tbAdmin.setModel(modelo);

                    for (int i = 0; i < ladmin.size(); i++) {
                        combAdminMedico.addItem(ladmin.get(i));

                    }
                    combAdminMedico.removeAllItems();
                    ladmin = listarAdmins();

                    for (int i = 0; i < ladmin.size(); i++) {
                        combAdminMedico.addItem(ladmin.get(i));

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senhas Não São Iguais!");
                txtSenhaAdmin.setText(Mostrar);
                txtSenhaAdmin2.setText(Mostrar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarAdminActionPerformed

    private void txtLoginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginAdminActionPerformed

    private void txtNomeAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeAdminActionPerformed

    private void btnCadastrarClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClinicaActionPerformed
        conn = Banco.conecta();
        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            String nome = "'" + txtNomeClinica.getText() + "'";
            String cnpj = "'" + txtCnpjClinica.getText() + "'";
            String cidade = "'" + txtCidadeClinica.getText() + "'";
            Integer leito = Integer.parseInt(txtLeitoClinica.getText());

            if (txtNomeClinica.getText().isEmpty() || txtCnpjClinica.getText().isEmpty() || txtCidadeClinica.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!!");
            } else {

                String sql = "INSERT INTO Clinica (nome, cnpj, cidadeclinica, leito) VALUES ("
                        + "" + nome + "," + cnpj + "," + cidade + "," + leito + ")";
                System.out.println("sql: " + sql);

                //atravez desse objeto usamos comandos sql
                Statement stmt = conn.createStatement();

                //select
                //stmt.executeQuery(sql);
                //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Clínica  " + nome + "  cadastrada!!");

                //encerrou a conexão
                stmt.close();
                conn.close();

                String Mostrar = " ";
                txtNomeClinica.setText(Mostrar);
                txtCnpjClinica.setText(Mostrar);
                txtCidadeClinica.setText(Mostrar);
                txtLeitoClinica.setText(Mostrar);

                Clinicas = listarTbClinica();

                ClinicaTableModel modelo = new ClinicaTableModel();
                modelo.setListaClinicas(Clinicas);

                tbClinica.setModel(modelo);

                combClinicaMedico.removeAllItems();
                combClinicaCliente.removeAllItems();

                lclinica = listarClinicas();

                for (int i = 0; i < lclinica.size(); i++) {
                    combClinicaMedico.addItem(lclinica.get(i));
                    combClinicaCliente.addItem(lclinica.get(i));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarClinicaActionPerformed

    private void txtCnpjClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjClinicaActionPerformed

    private void txtNomeClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClinicaActionPerformed

    private void btnEditarClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClinicaActionPerformed

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            String nome = "'" + txtNomeClinica.getText() + "'";
            String cnpj = "'" + txtCnpjClinica.getText() + "'";
            String cidade = "'" + txtCidadeClinica.getText() + "'";

            int k = tbClinica.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Porfavor Complete os Campos e Selecione a Linha na Tabela a ser editada e em Seguida clicke no botao Editar");
            } else {
                // System.out.println(k);

                int i = ((ClinicaTableModel) tbClinica.getModel()).getListaclinicas().get(k).getIdclinica();

                Statement stmt = conn.createStatement();
                String sql = "UPDATE clinica SET nome = " + nome + ",cnpj = " + cnpj + ",cidadeclinica = " + cidade + " WHERE idclinica = " + i + "";
                stmt.executeUpdate(sql);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Clinica Editada!");

                String Mostrar = " ";
                txtNomeClinica.setText(Mostrar);
                txtCnpjClinica.setText(Mostrar);
                txtCidadeClinica.setText(Mostrar);

                Clinicas = listarTbClinica();
                ClinicaTableModel modelo = new ClinicaTableModel();
                modelo.setListaClinicas(Clinicas);
                tbClinica.setModel(modelo);

                combClinicaMedico.removeAllItems();

                lclinica = listarClinicas();

                for (int i2 = 0; i2 < lclinica.size(); i2++) {
                    combClinicaMedico.addItem(lclinica.get(i2));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarClinicaActionPerformed

    private void btnExcluirClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClinicaActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbClinica.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um Admin para deletar");
            } else {
                // System.out.println(k);
                int i = ((ClinicaTableModel) tbClinica.getModel()).getListaclinicas().get(k).getIdclinica();

                Statement stmt = conn.createStatement();
                String sql = "Delete from clinica where idclinica = " + i + " ";
                stmt.executeUpdate(sql);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Clinica Apagada!");

                String Mostrar = " ";
                txtNomeAdmin.setText(Mostrar);
                txtCnpjClinica.setText(Mostrar);
                txtCidadeClinica.setText(Mostrar);

                Clinicas = listarTbClinica();
                ClinicaTableModel modelo = new ClinicaTableModel();
                modelo.setListaClinicas(Clinicas);
                tbClinica.setModel(modelo);

                combClinicaMedico.removeAllItems();

                lclinica = listarClinicas();

                for (int i2 = 0; i2 < lclinica.size(); i2++) {
                    combClinicaMedico.addItem(lclinica.get(i2));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirClinicaActionPerformed

    private void chkAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAdmActionPerformed

    private void txtCpfMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfMedicoActionPerformed

    private void txtSenhaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaAdminActionPerformed

    private void txtSenhaAdmin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaAdmin2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaAdmin2ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        Login init = new Login();
        init.setVisible(true); //torna visivel frame de cadastro
        setVisible(false);//tira a tela de login
        dispose(); //fecha o form de login (quem chamou)

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarClienteActionPerformed
        conn = Banco.conecta();
        String Mostrar = null;
        String sql = "";
        String sql2= "";

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);
            }

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoCliente.getDate();

            String nome = "'" + txtNomeCliente.getText() + "'";
            String rg = "'" + txtRgCliente.getText() + "'";
            String cpf = "'" + txtCpfCliente.getText() + "'";

            String dataNasc = "'" + sdf1.format(aux) + "'";
            Integer idclinica = ((Clinica)combClinicaCliente.getSelectedItem()).getIdclinica();
            Integer idmedico = ((Medico) combMedicoCliente.getSelectedItem()).getIdmedico();
            
            
            if (txtNomeCliente.getText().isEmpty() || txtRgCliente.getText().isEmpty() || txtCpfCliente.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!!");
            } else {

                Statement stmt = conn.createStatement();

                sql = "INSERT INTO Cliente (nome,rg, cpf, datanascimento, idclinica, idmedico ) VALUES ("
                        + "" + nome + "," + rg + "," + cpf + "," + dataNasc + "," + idclinica + ","
                        + " " + idmedico + ")";
                // subtraindo quantidade de leitos da clinica onde o clinete foi cadastrado
                sql2 = "UPDATE clinica SET leito = leito -1 WHERE idclinica = " + idclinica + "";
                
                System.out.println("sql: " + sql);

                //atravez desse objeto usamos comandos sql
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                //select
                //stmt.executeQuery(sql);
                //retorna um conjunto de dados , sempre q for fazer insert usar o executeUpdate : inset, update, delete

                //encerrou a conexão
                stmt.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Cliente cadastrado!");

                txtNomeCliente.setText(Mostrar);
                txtCpfCliente.setText(Mostrar);
                txtRgCliente.setText(Mostrar);

                Clientes = listarTbCliente();
                
               
                

                ClienteTableModel modelo = new ClienteTableModel();
                modelo.setListaClientes(Clientes);

                tbCliente.setModel(modelo);
                
                Clinicas = listarTbClinica();

                ClinicaTableModel modelo2 = new ClinicaTableModel();
                modelo2.setListaClinicas(Clinicas);

                tbClinica.setModel(modelo2);
                
                              //   alinharTbMedicos(tbMedico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date aux = txtDataNascimentoCliente.getDate();

            String nome = "'" + txtNomeCliente.getText() + "'";
            String rg = "'" + txtRgCliente.getText() + "'";
            String cpf = "'" + txtCpfCliente.getText() + "'";
            String dataNasc = "'" + sdf.format(aux) + "'";

            int k = tbCliente.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Porfavor Complete os Campos e Selecione a Linha na Tabela a ser editada e em Seguida clicke no botao Editar");
            } else {
                // System.out.println(k);
                int i = ((ClienteTableModel) tbCliente.getModel()).getListaClientes().get(k).getIdcliente();

                Statement stmt = conn.createStatement();
                String sql = "UPDATE cliente SET nome = " + nome + ",rg = " + rg + ",cpf = " + cpf + ",datanascimento = " + dataNasc + "WHERE idcliente = " + i + "";
                stmt.executeUpdate(sql);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Cliente Editado!");

                String Mostrar = null;
                txtNomeCliente.setText(Mostrar);
                txtCpfCliente.setText(Mostrar);
                txtRgCliente.setText(Mostrar);

                Clientes = listarTbCliente();
                ClienteTableModel modelo = new ClienteTableModel();
                modelo.setListaClientes(Clientes);
                tbCliente.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void txtCpfClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfClienteActionPerformed

    private void combClinicaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combClinicaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combClinicaClienteActionPerformed

    private void txtDataNascimentoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoClienteActionPerformed

    private void btnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClienteActionPerformed

        conn = Banco.conecta();

        try {
            if (conn == null || conn.isClosed()) {
                System.out.println("erro ao conectar ao banco de dados");
                System.exit(-1);

            }

            int k = tbCliente.getSelectedRow();
            if (k == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um Cliente para deletar");
            } else {
                // System.out.println(k);
                int i = ((ClienteTableModel) tbCliente.getModel()).getListaClientes().get(k).getIdcliente();
                int j = ((ClienteTableModel) tbCliente.getModel()).getListaClientes().get(k).getIdclinica();

                Statement stmt = conn.createStatement();
                String sql = "Delete from cliente where idcliente = " + i + " ";
                String sql2 = "UPDATE clinica SET leito = leito +1 WHERE idclinica = " + j + "";
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);

                stmt.close();
                conn.close();

                JOptionPane.showMessageDialog(null, "Cliente(a) Apagado!");

                String Mostrar = null;
                txtNomeCliente.setText(Mostrar);
                txtCpfCliente.setText(Mostrar);
                txtRgCliente.setText(Mostrar);

                Clientes = listarTbCliente();
                ClienteTableModel modelo = new ClienteTableModel();
                modelo.setListaClientes(Clientes);
                tbCliente.setModel(modelo);
                
                Clinicas = listarTbClinica();
                ClinicaTableModel modelo2 = new ClinicaTableModel();
                modelo2.setListaClinicas(Clinicas);
                tbClinica.setModel(modelo2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirClienteActionPerformed

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void combAdminMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combAdminMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combAdminMedicoActionPerformed

    private void combMedicoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combMedicoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combMedicoClienteActionPerformed

    private void txtLeitoClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLeitoClinicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLeitoClinicaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cliente;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel admin;
    private javax.swing.JButton btnCadastrarAdmin;
    private javax.swing.JButton btnCadastrarCliente;
    private javax.swing.JButton btnCadastrarClinica;
    private javax.swing.JButton btnEditarAdmin;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarClinica;
    private javax.swing.JButton btnEditarMedico;
    private javax.swing.JButton btnExcluirAdmin;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnExcluirClinica;
    private javax.swing.JButton btnExcluirMedico;
    private javax.swing.JButton btnLogout;
    private javax.swing.JCheckBox chkAdm;
    private javax.swing.JPanel clinica;
    private javax.swing.JComboBox<Admin> combAdminMedico;
    private javax.swing.JComboBox<Clinica> combClinicaCliente;
    private javax.swing.JComboBox<Clinica> combClinicaMedico;
    private javax.swing.JComboBox<Medico> combMedicoCliente;
    private javax.swing.JButton jButton1Medico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel medico;
    private javax.swing.JTable tbAdmin;
    private javax.swing.JTable tbCliente;
    private javax.swing.JTable tbClinica;
    private javax.swing.JTable tbMedico;
    private javax.swing.JTextField txtCidadeClinica;
    private javax.swing.JTextField txtCnpjClinica;
    private javax.swing.JFormattedTextField txtCpfCliente;
    private javax.swing.JFormattedTextField txtCpfMedico;
    private javax.swing.JTextField txtCrmMedico;
    private org.jdesktop.swingx.JXDatePicker txtDataNascimentoCliente;
    private org.jdesktop.swingx.JXDatePicker txtDataNascimentoMedico;
    private javax.swing.JFormattedTextField txtLeitoClinica;
    private javax.swing.JTextField txtLoginAdmin;
    private javax.swing.JTextField txtNomeAdmin;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeClinica;
    private javax.swing.JTextField txtNomeMedico;
    private javax.swing.JFormattedTextField txtRgCliente;
    private javax.swing.JPasswordField txtSenhaAdmin;
    private javax.swing.JPasswordField txtSenhaAdmin2;
    // End of variables declaration//GEN-END:variables

}
