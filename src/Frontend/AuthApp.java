package Frontend;

import javax.swing.*; //Importa componentes graficos
import javax.swing.border.EmptyBorder; //Son los espacios entre las cosas
import java.awt.*; // Pal color la letra pues diseño
import java.awt.event.ActionEvent; //Es el de manejo de eventos
import java.awt.event.ActionListener;/// Falta
import java.util.ArrayList; //Almacena
import java.util.List; //Almacena

public class AuthApp extends JFrame {
    //Colores de la pagina
    private static final Color PRIMARY_COLOR = new Color(69, 153, 126); // #45997E
    private static final Color WHITE_COLOR = new Color(255, 255, 255);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);

    // Componentes de la interfaz
    private JPanel contentPane;// Es la interfaz como tal
    private JPanel leftPanel; //Panel
    private JPanel rightPanel;//Panel
    private CardLayout cardLayout; // Es pa que se pueda cambiar entre los paneles
    private JPanel formPanel; //Contiene los campos de entrada y botones para la autenticación.

    // Componentes del formulario de login
    private JTextField emailLoginField; //Pa ingresar el Email
    private JPasswordField passwordLoginField; //Contrasea tipo *******
    private JButton loginButton; //Button pal inicio de sección
    private JButton switchToRegisterButton; // Button pa cambiar a regristo

    // Componentes del formulario de registro
    private JTextField nameRegisterField; //Name pa que se registre
    private JTextField emailRegisterField; //Email
    private JPasswordField passwordRegisterField;//Contraseña *** en
    private ButtonGroup roleGroup; //Permite la selección del rol
    private JRadioButton padreRadio; //Opcion de padre
    private JRadioButton directivaRadio; //Opcion directiva
    private JRadioButton medicoRadio;  //Opcion Medico
    private JComboBox<String> studentComboBox; //Desplegable
    private JButton registerButton; //Button de registro
    private JButton switchToLoginButton; // Pa cambiar al login

    /// Datos del ejemplo porque no hay base de datos
    private List<Estudiante> estudiantes;

    public AuthApp() {
        setupSampleData(); ///Metodo ficticio porque no hay base de datos

        // Configuración de la ventana principal
        setTitle("Sistema de Vacunación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Es pa scerra la ventana
        setBounds(100, 100, 1000, 600); //Tamaño de pantalla maximo
        setMinimumSize(new Dimension(800, 500)); //Minimo tamaño

        // Panel principal
        contentPane = new JPanel(); //Contenedor principal
        contentPane.setLayout(new BorderLayout(0, 0));//Organiza los elementos en Norte, Sur, Este, Oeste y Centro.
        setContentPane(contentPane); //Asigna este panel como la base de la ventana.

        // Configuración de paneles pues el de izquierda y derecha uno verde y otro y los otros :D
        setupPanels();

        // Configuración de formularios
        setupLoginForm();
        setupRegisterForm();

        // Mostrar formulario de login por defecto
        cardLayout.show(formPanel, "login"); //Frompanel es el panel de los formularios  y pues primero va salir es de login
    }
    /// Haber esto es pal ejemplo porque pues como no hay base de datos pues pa cuando la tengan
    private void setupSampleData() {
        estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("1", "Juan Pérez", "1° A"));
        estudiantes.add(new Estudiante("2", "María García", "2° B"));
        estudiantes.add(new Estudiante("3", "Carlos López", "3° C"));
    }
    /// ..........................................................................................
    private void setupPanels() {
        // Panel izquierdo (verde)
        leftPanel = new JPanel(); //Creamos panel
        leftPanel.setBackground(PRIMARY_COLOR);
        leftPanel.setLayout(new BorderLayout());//Orden pal panel
        contentPane.add(leftPanel, BorderLayout.WEST); //West Izquierda
        leftPanel.setPreferredSize(new Dimension(400, getHeight()));

        // Panel derecho (blanco)
        rightPanel = new JPanel();
        rightPanel.setBackground(WHITE_COLOR);
        rightPanel.setLayout(new BorderLayout());
        contentPane.add(rightPanel, BorderLayout.CENTER);

        // Panel para los formularios con CardLayout
        cardLayout = new CardLayout();
        formPanel = new JPanel(cardLayout);
        formPanel.setBackground(WHITE_COLOR);
        rightPanel.add(formPanel, BorderLayout.CENTER);
    }

    private void setupLoginForm() {
        // Panel de bienvenida para login
        JPanel welcomeLoginPanel = new JPanel();
        welcomeLoginPanel.setLayout(new BoxLayout(welcomeLoginPanel, BoxLayout.Y_AXIS));//Orientación vertical
        welcomeLoginPanel.setBackground(PRIMARY_COLOR);
        welcomeLoginPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel welcomeTitle = new JLabel("¡Bienvenido de nuevo!");
        welcomeTitle.setForeground(WHITE_COLOR);
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Para mantenerte conectado con nosotros, inicia sesión con tu información personal</div></html>");
        welcomeText.setForeground(WHITE_COLOR);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeLoginPanel.add(Box.createVerticalGlue());
        welcomeLoginPanel.add(welcomeTitle);
        welcomeLoginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomeLoginPanel.add(welcomeText);
        welcomeLoginPanel.add(Box.createVerticalGlue());

        leftPanel.add(welcomeLoginPanel, BorderLayout.CENTER);

        // Panel de formulario de login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(WHITE_COLOR);
        loginPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        // Título del formulario
        JLabel loginTitle = new JLabel("Iniciar Sesión");
        loginTitle.setForeground(PRIMARY_COLOR);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 24));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de email
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.setBackground(WHITE_COLOR);
        emailPanel.setMaximumSize(new Dimension(400, 50));

        JLabel emailIcon = new JLabel("✉");
        emailIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        emailIcon.setBorder(new EmptyBorder(0, 5, 0, 10));
        emailIcon.setForeground(Color.GRAY);

        emailLoginField = new JTextField();
        emailLoginField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLoginField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        emailLoginField.setBackground(LIGHT_GRAY);

        emailPanel.add(emailIcon, BorderLayout.WEST);
        emailPanel.add(emailLoginField, BorderLayout.CENTER);

        // Campo de contraseña
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(WHITE_COLOR);
        passwordPanel.setMaximumSize(new Dimension(400, 50));

        JLabel passwordIcon = new JLabel("🔒");
        passwordIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordIcon.setBorder(new EmptyBorder(0, 5, 0, 10));
        passwordIcon.setForeground(Color.GRAY);

        passwordLoginField = new JPasswordField();
        passwordLoginField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLoginField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        passwordLoginField.setBackground(LIGHT_GRAY);

        passwordPanel.add(passwordIcon, BorderLayout.WEST);
        passwordPanel.add(passwordLoginField, BorderLayout.CENTER);

        // Botón de login
        loginButton = new JButton("INICIAR SESIÓN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(PRIMARY_COLOR);
        loginButton.setForeground(WHITE_COLOR);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setMaximumSize(new Dimension(400, 50));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Enlace para cambiar a registro
        switchToRegisterButton = new JButton("¿No tienes cuenta? Regístrate");
        switchToRegisterButton.setFont(new Font("Arial", Font.PLAIN, 14));
        switchToRegisterButton.setForeground(PRIMARY_COLOR);
        switchToRegisterButton.setBorderPainted(false);
        switchToRegisterButton.setContentAreaFilled(false);
        switchToRegisterButton.setFocusPainted(false);
        switchToRegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acción para cambiar al formulario de registro
        switchToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWelcomePanel(false);
                cardLayout.show(formPanel, "register");
            }
        });

        // Agregar componentes al panel de login
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(loginTitle);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        loginPanel.add(emailPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(loginButton);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(switchToRegisterButton);
        loginPanel.add(Box.createVerticalGlue());

        // Agregar panel de login al panel de formularios
        formPanel.add(loginPanel, "login");
    }

    private void setupRegisterForm() {
        // Panel de formulario de registro
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBackground(WHITE_COLOR);
        registerPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        // Título del formulario
        JLabel registerTitle = new JLabel("Crear Cuenta");
        registerTitle.setForeground(PRIMARY_COLOR);
        registerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        registerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de nombre
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(WHITE_COLOR);
        namePanel.setMaximumSize(new Dimension(400, 50));

        JLabel nameIcon = new JLabel("👤");
        nameIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        nameIcon.setBorder(new EmptyBorder(0, 5, 0, 10));
        nameIcon.setForeground(Color.GRAY);

        nameRegisterField = new JTextField();
        nameRegisterField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        nameRegisterField.setBackground(LIGHT_GRAY);

        namePanel.add(nameIcon, BorderLayout.WEST);
        namePanel.add(nameRegisterField, BorderLayout.CENTER);

        // Campo de email
        JPanel emailRegisterPanel = new JPanel(new BorderLayout());
        emailRegisterPanel.setBackground(WHITE_COLOR);
        emailRegisterPanel.setMaximumSize(new Dimension(400, 50));

        JLabel emailRegisterIcon = new JLabel("✉");
        emailRegisterIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        emailRegisterIcon.setBorder(new EmptyBorder(0, 5, 0, 10));
        emailRegisterIcon.setForeground(Color.GRAY);

        emailRegisterField = new JTextField();
        emailRegisterField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        emailRegisterField.setBackground(LIGHT_GRAY);

        emailRegisterPanel.add(emailRegisterIcon, BorderLayout.WEST);
        emailRegisterPanel.add(emailRegisterField, BorderLayout.CENTER);

        // Campo de contraseña
        JPanel passwordRegisterPanel = new JPanel(new BorderLayout());
        passwordRegisterPanel.setBackground(WHITE_COLOR);
        passwordRegisterPanel.setMaximumSize(new Dimension(400, 50));

        JLabel passwordRegisterIcon = new JLabel("🔒");
        passwordRegisterIcon.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordRegisterIcon.setBorder(new EmptyBorder(0, 5, 0, 10));
        passwordRegisterIcon.setForeground(Color.GRAY);

        passwordRegisterField = new JPasswordField();
        passwordRegisterField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        passwordRegisterField.setBackground(LIGHT_GRAY);

        passwordRegisterPanel.add(passwordRegisterIcon, BorderLayout.WEST);
        passwordRegisterPanel.add(passwordRegisterField, BorderLayout.CENTER);

        // Panel de selección de rol
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setBackground(WHITE_COLOR);
        rolePanel.setMaximumSize(new Dimension(400, 120));
        rolePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel = new JLabel("Seleccione su rol:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        roleGroup = new ButtonGroup();

        padreRadio = new JRadioButton("Padre");
        padreRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        padreRadio.setBackground(WHITE_COLOR);
        padreRadio.setSelected(true);

        directivaRadio = new JRadioButton("Directiva");
        directivaRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        directivaRadio.setBackground(WHITE_COLOR);

        medicoRadio = new JRadioButton("Médico");
        medicoRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        medicoRadio.setBackground(WHITE_COLOR);

        roleGroup.add(padreRadio);
        roleGroup.add(directivaRadio);
        roleGroup.add(medicoRadio);

        rolePanel.add(roleLabel);
        rolePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rolePanel.add(padreRadio);
        rolePanel.add(directivaRadio);
        rolePanel.add(medicoRadio);

        // Panel de selección de estudiante
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBackground(WHITE_COLOR);
        studentPanel.setMaximumSize(new Dimension(400, 80));
        studentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel studentLabel = new JLabel("Seleccione su estudiante:");
        studentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        studentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        studentComboBox = new JComboBox<>();
        studentComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        studentComboBox.setMaximumSize(new Dimension(400, 40));

        // Agregar estudiantes al combo box
        for (Estudiante estudiante : estudiantes) {
            studentComboBox.addItem(estudiante.getNombre() + " - " + estudiante.getGrado());
        }

        studentPanel.add(studentLabel);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        studentPanel.add(studentComboBox);

        // Botón de registro
        registerButton = new JButton("REGISTRARSE");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(PRIMARY_COLOR);
        registerButton.setForeground(WHITE_COLOR);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setMaximumSize(new Dimension(400, 50));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Enlace para cambiar a login
        switchToLoginButton = new JButton("¿Ya tienes cuenta? Inicia sesión");
        switchToLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        switchToLoginButton.setForeground(PRIMARY_COLOR);
        switchToLoginButton.setBorderPainted(false);
        switchToLoginButton.setContentAreaFilled(false);
        switchToLoginButton.setFocusPainted(false);
        switchToLoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Acción para cambiar al formulario de login
        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWelcomePanel(true);
                cardLayout.show(formPanel, "login");
            }
        });

        // Mostrar/ocultar panel de estudiante según el rol seleccionado
        ActionListener roleListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.setVisible(padreRadio.isSelected());
                registerPanel.revalidate();
                registerPanel.repaint();
            }
        };

        padreRadio.addActionListener(roleListener);
        directivaRadio.addActionListener(roleListener);
        medicoRadio.addActionListener(roleListener);

        // Agregar componentes al panel de registro
        registerPanel.add(Box.createVerticalGlue());
        registerPanel.add(registerTitle);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(namePanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(emailRegisterPanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(passwordRegisterPanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(rolePanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(studentPanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(registerButton);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        registerPanel.add(switchToLoginButton);
        registerPanel.add(Box.createVerticalGlue());

        // Agregar panel de registro al panel de formularios
        formPanel.add(registerPanel, "register");
    }

    private void updateWelcomePanel(boolean isLogin) {
        // Limpiar panel izquierdo
        leftPanel.removeAll();

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(PRIMARY_COLOR);
        welcomePanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel welcomeTitle = new JLabel(isLogin ? "¡Bienvenido de nuevo!" : "¡Bienvenido!");
        welcomeTitle.setForeground(WHITE_COLOR);
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        String welcomeMessage = isLogin
                ? "Para mantenerte conectado con nosotros, inicia sesión con tu información personal"
                : "Regístrate para formar parte de nuestro sistema de vacunación escolar";

        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>" + welcomeMessage + "</div></html>");
        welcomeText.setForeground(WHITE_COLOR);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeTitle);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomePanel.add(welcomeText);
        welcomePanel.add(Box.createVerticalGlue());

        leftPanel.add(welcomePanel, BorderLayout.CENTER);
        leftPanel.revalidate();
        leftPanel.repaint();
    }

    public static void main(String[] args) {
        try {
            // Establecer look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AuthApp frame = new AuthApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Clase para representar a un estudiante
    private class Estudiante {
        private String id;
        private String nombre;
        private String grado;

        public Estudiante(String id, String nombre, String grado) {
            this.id = id;
            this.nombre = nombre;
            this.grado = grado;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getGrado() {
            return grado;
        }
    }
}
