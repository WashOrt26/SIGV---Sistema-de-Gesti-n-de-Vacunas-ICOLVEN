package org.sigv.frontend;
///  Los comentarios de la base de datos se ven asi verdes :D
import org.sigv.model.Usuario;
import org.sigv.repository.UsuarioRepository;

import javax.swing.*; // Importa componentes gráficos
import javax.swing.border.EmptyBorder; // Para manejar bordes vacíos
import java.awt.*; // Para manejar colores y diseño
import java.awt.event.ActionEvent; // Para manejar eventos de acción
import java.awt.event.ActionListener; // Para manejar eventos de acción
import java.awt.event.FocusAdapter; // Para manejar el comportamiento del placeholder
import java.awt.event.FocusEvent; // Para manejar el comportamiento del placeholder
import java.util.ArrayList; // Para almacenar datos en una lista
import java.util.List; // Para almacenar datos en una lista

public class AuthApp extends JFrame {

    /*
     * Se instancia la clase encargada de las operaciones sobre
     * la base de datos
     */
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    /// ======================================================
    /// Configurar credenciales de la base de datos pues si necesitas tipo un local host o algo asi
    /// ======================================================

    // Colores personalizados
    private static final Color PRIMARY_COLOR = new Color(69, 153, 126); // Color principal
    private static final Color WHITE_COLOR = new Color(255, 255, 255); // Color blanco
    private static final Color LIGHT_GRAY = new Color(245, 245, 245); // Color gris claro
    private static final Color PLACEHOLDER_COLOR = new Color(160, 160, 160); // Color para el placeholder

    // Componentes de la interfaz
    private JPanel contentPane; // Panel principal
    private JPanel leftPanel; // Panel izquierdo (verde)
    private JPanel rightPanel; // Panel derecho (blanco)
    private CardLayout cardLayout; // Para manejar el cambio entre formularios
    private JPanel formPanel; // Contiene los formularios de login y registro

    // Componentes del formulario de login
    private JTextField emailLoginField; // Campo para ingresar el email
    private JPasswordField passwordLoginField; // Campo para ingresar la contraseña
    private JButton loginButton; // Botón para iniciar sesión
    private JButton switchToRegisterButton; // Botón para cambiar a registro

    // Componentes del formulario de registro
    private JTextField nameRegisterField; // Campo para ingresar el nombre
    private JTextField emailRegisterField; // Campo para ingresar el email
    private JPasswordField passwordRegisterField; // Campo para ingresar la contraseña
    private ButtonGroup roleGroup; // Grupo para manejar la selección de rol
    private JRadioButton padreRadio; // Opción de padre
    private JRadioButton directivaRadio; // Opción de directiva
    private JRadioButton medicoRadio; // Opción de médico
    private JComboBox<String> studentComboBox; // ComboBox para seleccionar estudiante
    private JButton registerButton; // Botón para registrar
    private JButton switchToLoginButton; // Botón para cambiar a login

    /// Datos de ejemplo (simulando una base de datos) Tambien deberias borrar la lista porque aja base de dato s
    private List<Estudiante> estudiantes;

    public AuthApp() {
        /// Elimina el setupSampleData porque es el de los datos de los ejemplo
        /// Y pues pudes poner aca  el metodo nuevo para la base de datos de estudiantes

        setupSampleData(); // Configura datos de ejemplo

        // Configuración de la ventana principal
        setTitle("Sistema de Vacunación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setBounds(100, 100, 800, 600); // Tamaño de la ventana
        setMinimumSize(new Dimension(600, 400)); // Tamaño mínimo de la ventana

        // Panel principal
        contentPane = new JPanel(); // Crear el panel principal
        contentPane.setLayout(new BorderLayout(0, 0)); // Establecer el layout
        setContentPane(contentPane); // Asignar el panel principal a la ventana

        // Configuración de paneles
        setupPanels();

        // Configuración de formularios
        setupLoginForm();
        setupRegisterForm();

        // Mostrar formulario de login por defecto
        cardLayout.show(formPanel, "login"); // Mostrar el formulario de login
    }

    /// Borra todo este metodo  setupSampleData porque pues si hay base de datos ya no sirve
    private void setupSampleData() {
        ///Simulación de datos de estudiantes
        estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("1", "Juan Pérez", "1° A"));
        estudiantes.add(new Estudiante("2", "María García", "2° B"));
        estudiantes.add(new Estudiante("3", "Carlos López", "3° C"));
        estudiantes.add(new Estudiante("4", "Ana Rodríguez", "1° B"));
        estudiantes.add(new Estudiante("5", "Pedro Sánchez", "2° A"));
    }

    private void setupPanels() {
        // Panel izquierdo (verde)
        leftPanel = new JPanel(); // Crear el panel izquierdo
        leftPanel.setBackground(PRIMARY_COLOR); // Establecer el color de fondo
        leftPanel.setLayout(new BorderLayout()); // Establecer el layout
        contentPane.add(leftPanel, BorderLayout.WEST); // Agregar el panel izquierdo al panel principal
        leftPanel.setPreferredSize(new Dimension(400, getHeight())); // Establecer el tamaño preferido

        // Panel derecho (blanco)
        rightPanel = new JPanel(); // Crear el panel derecho
        rightPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        rightPanel.setLayout(new BorderLayout()); // Establecer el layout
        contentPane.add(rightPanel, BorderLayout.CENTER); // Agregar el panel derecho al panel principal

        // Panel para los formularios con CardLayout
        cardLayout = new CardLayout(); // Crear el CardLayout
        formPanel = new JPanel(cardLayout); // Crear el panel de formularios
        formPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        rightPanel.add(formPanel, BorderLayout.CENTER); // Agregar el panel de formularios al panel derecho
    }

    private void setupLoginForm() {
        // Panel de bienvenida para login
        JPanel welcomeLoginPanel = new JPanel();
        welcomeLoginPanel.setLayout(new BoxLayout(welcomeLoginPanel, BoxLayout.Y_AXIS)); // Orientación vertical
        welcomeLoginPanel.setBackground(PRIMARY_COLOR); // Establecer el color de fondo
        welcomeLoginPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Espaciado interno

        JLabel welcomeTitle = new JLabel("¡Bienvenido de nuevo!"); // Título de bienvenida
        welcomeTitle.setForeground(WHITE_COLOR); // Establecer el color del texto
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de fuente ajustado
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Para mantenerte conectado con nosotros, inicia sesión con tu información personal</div></html>");
        welcomeText.setForeground(WHITE_COLOR); // Establecer el color del texto
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        welcomeLoginPanel.add(Box.createVerticalGlue()); // Espacio flexible
        welcomeLoginPanel.add(welcomeTitle); // Agregar título
        welcomeLoginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        welcomeLoginPanel.add(welcomeText); // Agregar texto de bienvenida
        welcomeLoginPanel.add(Box.createVerticalGlue()); // Espacio flexible

        leftPanel.add(welcomeLoginPanel, BorderLayout.CENTER); // Agregar el panel de bienvenida al panel izquierdo

        // Panel de formulario de login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS)); // Orientación vertical
        loginPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        loginPanel.setBorder(new EmptyBorder(30, 50, 30, 50)); // Espaciado interno

        // Título del formulario
        JLabel loginTitle = new JLabel("Iniciar Sesión");
        loginTitle.setForeground(PRIMARY_COLOR); // Establecer el color del texto
        loginTitle.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño de fuente ajustado
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        // Campo de email
        JPanel emailPanel = new JPanel(new BorderLayout()); // Panel para el campo de email
        emailPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        emailPanel.setMaximumSize(new Dimension(400, 50)); // Tamaño máximo

        JLabel emailIcon = new JLabel("✉"); // Icono de email
        emailIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18)); // Tamaño de fuente
        emailIcon.setBorder(new EmptyBorder(0, 5, 0, 10)); // Espaciado interno
        emailIcon.setForeground(Color.GRAY); // Color del icono

        emailLoginField = new JTextField("Correo electrónico"); // Campo de texto para email
        emailLoginField.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente
        emailLoginField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY), // Borde gris
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Espaciado interno
        emailLoginField.setBackground(LIGHT_GRAY); // Color de fondo
        emailLoginField.setForeground(PLACEHOLDER_COLOR); // Color del texto

        // Agregar comportamiento de placeholder
        emailLoginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailLoginField.getText().equals("Correo electrónico")) {
                    emailLoginField.setText(""); // Limpiar el campo
                    emailLoginField.setForeground(Color.BLACK); // Cambiar color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailLoginField.getText().isEmpty()) {
                    emailLoginField.setText("Correo electrónico"); // Restablecer el placeholder
                    emailLoginField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                }
            }
        });

        emailPanel.add(emailIcon, BorderLayout.WEST); // Agregar icono al panel
        emailPanel.add(emailLoginField, BorderLayout.CENTER); // Agregar campo de texto al panel

        // Campo de contraseña
        JPanel passwordPanel = new JPanel(new BorderLayout()); // Panel para el campo de contraseña
        passwordPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        passwordPanel.setMaximumSize(new Dimension(400, 50)); // Tamaño máximo

        JLabel passwordIcon = new JLabel("🔒"); // Icono de contraseña
        passwordIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18)); // Tamaño de fuente
        passwordIcon.setBorder(new EmptyBorder(0, 5, 0, 10)); // Espaciado interno
        passwordIcon.setForeground(Color.GRAY); // Color del icono

        passwordLoginField = new JPasswordField("Contraseña"); // Campo de texto para contraseña
        passwordLoginField.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente
        passwordLoginField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY), // Borde gris
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Espaciado interno
        passwordLoginField.setBackground(LIGHT_GRAY); // Color de fondo
        passwordLoginField.setForeground(PLACEHOLDER_COLOR); // Color del texto
        passwordLoginField.setEchoChar((char) 0); // Mostrar texto para el placeholder

        // Agregar comportamiento de placeholder
        passwordLoginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordLoginField.getPassword()).equals("Contraseña")) {
                    passwordLoginField.setText(""); // Limpiar el campo
                    passwordLoginField.setEchoChar('•'); // Activar caracteres de contraseña
                    passwordLoginField.setForeground(Color.BLACK); // Cambiar color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordLoginField.getPassword().length == 0) {
                    passwordLoginField.setText("Contraseña"); // Restablecer el placeholder
                    passwordLoginField.setEchoChar((char) 0); // Desactivar caracteres de contraseña
                    passwordLoginField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                }
            }
        });

        passwordPanel.add(passwordIcon, BorderLayout.WEST); // Agregar icono al panel
        passwordPanel.add(passwordLoginField, BorderLayout.CENTER); // Agregar campo de texto al panel

        /// Botón de login
        loginButton = new JButton("INICIAR SESIÓN"); // Botón para iniciar sesión
        loginButton.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño de fuente ajustado
        loginButton.setBackground(PRIMARY_COLOR); // Color de fondo
        loginButton.setForeground(WHITE_COLOR); // Color del texto
        loginButton.setFocusPainted(false); // Sin borde al hacer clic
        loginButton.setBorderPainted(false); // Sin borde
        loginButton.setMaximumSize(new Dimension(400, 50)); // Más grande para PC
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón

        /// ======================================================
        ///AQUÍ VA LA CONEXIÓN CON LA BASE DE DATOS PARA LOGIN
        /// PASOS:
        /// 1. Validar campos no estén vacíos
        /// 2. Obtener email y password
        /// 3. Ejecutar consulta SQL para validar credenciales
        /// 4. Si es válido, abrir siguiente ventana
        /// 5. Si no, mostrar mensaje de error
        /// ======================================================
        loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               boolean pasoValidacion = true;
               String email = emailLoginField.getText();
               String password = new String(passwordLoginField.getPassword());
               if (email.isEmpty()) {
                   emailLoginField.setText("Correo electrónico"); // Restablecer el placeholder
                   emailLoginField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                   pasoValidacion = false;
               }
               if (password.isEmpty()) {
                   passwordLoginField.setText("Contraseña"); // Restablecer el placeholder
                   passwordLoginField.setEchoChar((char) 0); // Desactivar caracteres de contraseña
                   passwordLoginField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                   pasoValidacion = false;
               }
               if (pasoValidacion) {
                   Usuario usuarioConsultado = usuarioRepository.buscarUsuario(email, password);
                   if (usuarioConsultado == null) {
                       System.out.println("Muestra mensaje de error");
                   } else {
                       System.out.println("pasa a la siguiente ventana");
                   }

               }

           }
        });

        // Enlace para cambiar a registro
        switchToRegisterButton = new JButton("¿No tienes cuenta? Regístrate"); // Botón para cambiar a registro
        switchToRegisterButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        switchToRegisterButton.setForeground(PRIMARY_COLOR); // Color del texto
        switchToRegisterButton.setBorderPainted(false); // Sin borde
        switchToRegisterButton.setContentAreaFilled(false); // Sin fondo
        switchToRegisterButton.setFocusPainted(false); // Sin borde al hacer clic
        switchToRegisterButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar
        switchToRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón

        // Acción para cambiar al formulario de registro
        switchToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWelcomePanel(false); // Actualizar el panel de bienvenida
                cardLayout.show(formPanel, "register"); // Mostrar el formulario de registro
            }
        });

        // Agregar componentes al panel de login
        loginPanel.add(Box.createVerticalGlue()); // Espacio flexible
        loginPanel.add(loginTitle); // Agregar título
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        loginPanel.add(emailPanel); // Agregar campo de email
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        loginPanel.add(passwordPanel); // Agregar campo de contraseña
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espacio rígido
        loginPanel.add(loginButton); // Agregar botón de login
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        loginPanel.add(switchToRegisterButton); // Agregar enlace a registro
        loginPanel.add(Box.createVerticalGlue()); // Espacio flexible

        // Agregar panel de login al panel de formularios
        formPanel.add(loginPanel, "login"); // Agregar el panel de login al CardLayout
    }

    private void setupRegisterForm() {
        // Panel de formulario de registro
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS)); // Orientación vertical
        registerPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        registerPanel.setBorder(new EmptyBorder(30, 50, 30, 50)); // Más padding

        // Título del formulario
        JLabel registerTitle = new JLabel("Crear Cuenta");
        registerTitle.setForeground(PRIMARY_COLOR); // Establecer el color del texto
        registerTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño de fuente ajustado
        registerTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        // Campo de nombre
        JPanel namePanel = new JPanel(new BorderLayout()); // Panel para el campo de nombre
        namePanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        namePanel.setMaximumSize(new Dimension(400, 50)); // Tamaño máximo

        JLabel nameIcon = new JLabel("👤"); // Icono de nombre
        nameIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));// Tamaño de fuente
        nameIcon.setBorder(new EmptyBorder(0, 5, 0, 10)); // Espaciado interno
        nameIcon.setForeground(Color.GRAY); // Color del icono

        nameRegisterField = new JTextField("Nombre y apellidos"); // Campo de texto para nombre
        nameRegisterField.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente
        nameRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY), // Borde gris
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Espaciado interno
        nameRegisterField.setBackground(LIGHT_GRAY); // Color de fondo
        nameRegisterField.setForeground(PLACEHOLDER_COLOR); // Color del texto

        // Agregar comportamiento de placeholder
        nameRegisterField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameRegisterField.getText().equals("Nombre y apellidos")) {
                    nameRegisterField.setText(""); // Limpiar el campo
                    nameRegisterField.setForeground(Color.BLACK); // Cambiar color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameRegisterField.getText().isEmpty()) {
                    nameRegisterField.setText("Nombre y apellidos"); // Restablecer el placeholder
                    nameRegisterField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                }
            }
        });

        namePanel.add(nameIcon, BorderLayout.WEST); // Agregar icono al panel
        namePanel.add(nameRegisterField, BorderLayout.CENTER); // Agregar campo de texto al panel

        // Campo de email
        JPanel emailRegisterPanel = new JPanel(new BorderLayout()); // Panel para el campo de email
        emailRegisterPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        emailRegisterPanel.setMaximumSize(new Dimension(400, 50)); // Tamaño máximo

        JLabel emailRegisterIcon = new JLabel("✉"); // Icono de email
        emailRegisterIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));// Tamaño de fuente
        emailRegisterIcon.setBorder(new EmptyBorder(0, 5, 0, 10)); // Espaciado interno
        emailRegisterIcon.setForeground(Color.GRAY); // Color del icono

        emailRegisterField = new JTextField("Correo electrónico"); // Campo de texto para email
        emailRegisterField.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente
        emailRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY), // Borde gris
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Espaciado interno
        emailRegisterField.setBackground(LIGHT_GRAY); // Color de fondo
        emailRegisterField.setForeground(PLACEHOLDER_COLOR); // Color del texto

        // Agregar comportamiento de placeholder
        emailRegisterField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailRegisterField.getText().equals("Correo electrónico")) {
                    emailRegisterField.setText(""); // Limpiar el campo
                    emailRegisterField.setForeground(Color.BLACK); // Cambiar color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailRegisterField.getText().isEmpty()) {
                    emailRegisterField.setText("Correo electrónico"); // Restablecer el placeholder
                    emailRegisterField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                }
            }
        });

        emailRegisterPanel.add(emailRegisterIcon, BorderLayout.WEST); // Agregar icono al panel
        emailRegisterPanel.add(emailRegisterField, BorderLayout.CENTER); // Agregar campo de texto al panel

        // Campo de contraseña
        JPanel passwordRegisterPanel = new JPanel(new BorderLayout()); // Panel para el campo de contraseña
        passwordRegisterPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        passwordRegisterPanel.setMaximumSize(new Dimension(400, 50)); // Tamaño máximo

        JLabel passwordRegisterIcon = new JLabel("🔒"); // Icono de contraseña
        passwordRegisterIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));//Tamaño de fuente
        passwordRegisterIcon.setBorder(new EmptyBorder(0, 5, 0, 10)); // Espaciado interno
        passwordRegisterIcon.setForeground(Color.GRAY); // Color del icono

        passwordRegisterField = new JPasswordField("Contraseña"); // Campo de texto para contraseña
        passwordRegisterField.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente
        passwordRegisterField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LIGHT_GRAY), // Borde gris
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Espaciado interno
        passwordRegisterField.setBackground(LIGHT_GRAY); // Color de fondo
        passwordRegisterField.setForeground(PLACEHOLDER_COLOR); // Color del texto
        passwordRegisterField.setEchoChar((char) 0); // Mostrar texto para el placeholder

        // Agregar comportamiento de placeholder
        passwordRegisterField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordRegisterField.getPassword()).equals("Contraseña")) {
                    passwordRegisterField.setText(""); // Limpiar el campo
                    passwordRegisterField.setEchoChar('•'); // Activar caracteres de contraseña
                    passwordRegisterField.setForeground(Color.BLACK); // Cambiar color del texto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordRegisterField.getPassword().length == 0) {
                    passwordRegisterField.setText("Contraseña"); // Restablecer el placeholder
                    passwordRegisterField.setEchoChar((char) 0); // Desactivar caracteres de contraseña
                    passwordRegisterField.setForeground(PLACEHOLDER_COLOR); // Cambiar color del texto
                }
            }
        });

        passwordRegisterPanel.add(passwordRegisterIcon, BorderLayout.WEST); // Agregar icono al panel
        passwordRegisterPanel.add(passwordRegisterField, BorderLayout.CENTER); // Agregar campo de texto al panel

        // Panel de selección de rol
        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS)); // Orientación vertical
        rolePanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        rolePanel.setMaximumSize(new Dimension(400, 150)); // Más grande para PC
        rolePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro

        JLabel roleLabel = new JLabel("Seleccione su rol:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Tamaño de fuente ajustado
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro

        roleGroup = new ButtonGroup(); // Grupo para los botones de radio

        padreRadio = new JRadioButton("Padre");
        padreRadio.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        padreRadio.setBackground(WHITE_COLOR); // Establecer el color de fondo
        padreRadio.setSelected(true); // Seleccionar por defecto

        directivaRadio = new JRadioButton("Directiva");
        directivaRadio.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        directivaRadio.setBackground(WHITE_COLOR); // Establecer el color de fondo

        medicoRadio = new JRadioButton("Médico");
        medicoRadio.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        medicoRadio.setBackground(WHITE_COLOR); // Establecer el color de fondo

        // Agregar los botones de radio al grupo
        roleGroup.add(padreRadio);
        roleGroup.add(directivaRadio);
        roleGroup.add(medicoRadio);

        // Agregar componentes al panel de selección de rol
        rolePanel.add(roleLabel);
        rolePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre el label y los botones
        rolePanel.add(padreRadio);
        rolePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los botones
        rolePanel.add(directivaRadio);
        rolePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los botones
        rolePanel.add(medicoRadio);

        // Panel de selección de estudiante
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS)); // Orientación vertical
        studentPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        studentPanel.setMaximumSize(new Dimension(400, 80)); // Más grande para PC
        studentPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro

        JLabel studentLabel = new JLabel("Seleccione su estudiante:");
        studentLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Tamaño de fuente ajustado
        studentLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro

        /// Combo Box
        studentComboBox = new JComboBox<>(); // ComboBox para seleccionar estudiante
        studentComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        studentComboBox.setMaximumSize(new Dimension(400, 40)); // Más grande para PC
        studentComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro
        /// ======================================================
        /// AQUÍ DEBES CARGAR LOS ESTUDIANTES DESDE LA BD
        /// (Esto reemplaza los datos de ejemplo)
        /// ======================================================

        /// Agregar estudiantes al combo box
        for (Estudiante estudiante : estudiantes) {
            studentComboBox.addItem(estudiante.getNombre() + " - " + estudiante.getGrado());
        }

        // Agregar componentes al panel de selección de estudiante
        studentPanel.add(studentLabel);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre el label y el combo box
        studentPanel.add(studentComboBox);

        // Botón de registro
        registerButton = new JButton("REGISTRARSE"); // Botón para registrar
        registerButton.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño de fuente ajustado
        registerButton.setBackground(PRIMARY_COLOR); // Color de fondo
        registerButton.setForeground(WHITE_COLOR); // Color del texto
        registerButton.setFocusPainted(false); // Sin borde al hacer clic
        registerButton.setBorderPainted(false); // Sin borde
        registerButton.setMaximumSize(new Dimension(400, 50)); // Más grande para PC
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        /// ======================================================
        /// AQUÍ VA LA CONEXIÓN CON LA BASE DE DATOS PARA REGISTRO
        /// PASOS:
        /// 1. Validar campos obligatorios
        /// 2. Obtener nombre, email, password y rol
        /// 3. Si es padre, obtener ID del estudiante seleccionado
        /// 4. Ejecutar INSERT en la tabla usuarios
        /// 5. Mostrar confirmación o error
        /// ======================================================
        /// registerButton.addActionListener(new ActionListener() {
        ///     @Override
        ///     public void actionPerformed(ActionEvent e) {
        ///         // Lógica de inserción en BD aquí <--
        ///     }
        /// });Puedes hacerlo asi no se :D
        // Enlace para cambiar a login
        switchToLoginButton = new JButton("¿Ya tienes cuenta? Inicia sesión"); // Botón para cambiar a login
        switchToLoginButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        switchToLoginButton.setForeground(PRIMARY_COLOR); // Color del texto
        switchToLoginButton.setBorderPainted(false); // Sin borde
        switchToLoginButton.setContentAreaFilled(false); // Sin fondo
        switchToLoginButton.setFocusPainted(false); // Sin borde al hacer clic
        switchToLoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar
        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón

        // Acción para cambiar al formulario de login
        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWelcomePanel(true); // Actualizar el panel de bienvenida
                cardLayout.show(formPanel, "login"); // Mostrar el formulario de login
            }
        });

        // Mostrar/ocultar panel de estudiante según el rol seleccionado
        ActionListener roleListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentPanel.setVisible(padreRadio.isSelected()); // Mostrar el panel de estudiante solo si se selecciona "Padre"
                registerPanel.revalidate(); // Revalidar el panel de registro
                registerPanel.repaint(); // Repaint para actualizar la interfaz
            }
        };

        padreRadio.addActionListener(roleListener);
        directivaRadio.addActionListener(roleListener);
        medicoRadio.addActionListener(roleListener);

        // Agregar componentes al panel de registro
        registerPanel.add(Box.createVerticalGlue()); // Espacio flexible
        registerPanel.add(registerTitle); // Agregar título
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espacio rígido
        registerPanel.add(namePanel); // Agregar campo de nombre
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio rígido
        registerPanel.add(emailRegisterPanel); // Agregar campo de email
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio rígido
        registerPanel.add(passwordRegisterPanel); // Agregar campo de contraseña
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        registerPanel.add(Box.createHorizontalGlue()); // Espacio flexible a la izquierda
        registerPanel.add(rolePanel); // Agregar panel de selección de rol
        registerPanel.add(Box.createHorizontalGlue()); // Espacio flexible a la derecha
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio rígido
        registerPanel.add(studentPanel); // Agregar panel de selección de estudiante
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        registerPanel.add(registerButton); // Agregar botón de registro
        registerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio rígido
        registerPanel.add(switchToLoginButton); // Agregar enlace a login
        registerPanel.add(Box.createVerticalGlue()); // Espacio flexible

        // Agregar panel de registro al panel de formularios
        formPanel.add(registerPanel, "register"); // Agregar el panel de registro al CardLayout
    }

    private void updateWelcomePanel(boolean isLogin) {
        // Limpiar panel izquierdo
        leftPanel.removeAll();

        // Panel de bienvenida
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS)); // Orientación vertical
        welcomePanel.setBackground(PRIMARY_COLOR); // Establecer el color de fondo
        welcomePanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Más padding

        JLabel welcomeTitle = new JLabel(isLogin ? "¡Bienvenido de nuevo!" : "¡Bienvenido!"); // Título de bienvenida
        welcomeTitle.setForeground(WHITE_COLOR); // Establecer el color del texto
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Tamaño de fuente ajustado
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        String welcomeMessage = isLogin
                ? "Para mantenerte conectado con nosotros, inicia sesión con tu información personal"
                : "Regístrate para formar parte de nuestro sistema de vacunación escolar";

        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>" + welcomeMessage + "</div></html>"); // Mensaje de bienvenida
        welcomeText.setForeground(WHITE_COLOR); // Establecer el color del texto
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14)); // Tamaño de fuente ajustado
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el texto

        welcomePanel.add(Box.createVerticalGlue()); // Espacio flexible
        welcomePanel.add(welcomeTitle); // Agregar título
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio rígido
        welcomePanel.add(welcomeText); // Agregar texto de bienvenida
        welcomePanel.add(Box.createVerticalGlue()); // Espacio flexible

        leftPanel.add(welcomePanel, BorderLayout.CENTER); // Agregar el panel de bienvenida al panel izquierdo
        leftPanel.revalidate(); // Revalidar el panel
        leftPanel.repaint(); // Repaint para actualizar la interfaz
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

    /// Clase para representar a un estudiante,
    public class Estudiante {
        private String id; // ID del estudiante
        private String nombre; // Nombre del estudiante
        private String grado; // Grado del estudiante

        public Estudiante(String id, String nombre, String grado) {
            this.id = id; // Inicializa el ID
            this.nombre = nombre; // Inicializa el nombre
            this.grado = grado; // Inicializa el grado
        }

        public String getId() {
            return id; // Retorna el ID
        }

        public String getNombre() {
            return nombre; // Retorna el nombre
        }

        public String getGrado() {
            return grado; // Retorna el grado
        }
        /// ======================================================
        /// AQUÍ VAN LOS MÉTODOS DE CONEXIÓN A LA BASE DE DATOS
        /// 1. validarCredencialesBD() - Para login
        /// 2. insertarUsuarioEnBD() - Para registro
        /// ======================================================
        ///private boolean validarCredencialesBD(String email, String password) {
        ///     // Tu conexión JDBC aquí
        ///     return false;
        /// }
        ///
        /// private boolean insertarUsuarioEnBD(String nombre, String email, String password, String rol, String estudianteId) {
        ///     // Tu INSERT SQL aquí
        ///     return false;
        /// } Puedes hacerlo asi no se
    }
}