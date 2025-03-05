package Frontend;

import javax.swing.*; // Importa componentes gráficos
import javax.swing.border.EmptyBorder; // Para manejar bordes vacíos
import javax.swing.table.DefaultTableModel; // Para manejar modelos de tabla
import java.awt.*; // Para manejar colores y diseño
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // Para formatear fechas
import java.util.Date; // Para manejar fechas

public class MedicoPanel extends JFrame {
    // Colores personalizados
    private static final Color PRIMARY_COLOR = new Color(69, 153, 126); // Color principal
    private static final Color WHITE_COLOR = new Color(255, 255, 255); // Color blanco
    private static final Color LIGHT_GRAY = new Color(245, 245, 245); // Color gris claro
    private static final Color GREEN_LIGHT = new Color(220, 252, 231); // Color verde claro
    private static final Color GREEN_TEXT = new Color(22, 163, 74); // Color de texto verde
    private static final Color RED_LIGHT = new Color(254, 226, 226); // Color rojo claro
    private static final Color RED_TEXT = new Color(220, 38, 38); // Color de texto rojo

    // Componentes principales
    private JPanel contentPane; // Panel principal
    private JTabbedPane tabbedPane; // Pestañas para diferentes secciones
    private JPanel registroVacunaPanel; // Panel para registrar vacunas
    private JPanel estudiantesPanel; // Panel para mostrar estudiantes

    // Datos de ejemplo
    private String[][] studentData = {
            {"EST001", "Juan Pérez", "1° A", "2024-02-01", "2024-08-01", "Al día"},
            {"EST002", "María García", "2° B", "2023-11-15", "2024-03-15", "Pendiente"},
            {"EST003", "Carlos López", "3° C", "2024-01-10", "2024-07-10", "Al día"},
            {"EST004", "Ana Rodríguez", "1° B", "2023-12-05", "2024-06-05", "Al día"},
            {"EST005", "Pedro Sánchez", "2° A", "2023-10-20", "2024-04-20", "Pendiente"},
            {"EST006", "Laura Martínez", "3° B", "2024-02-15", "2024-08-15", "Al día"},
            {"EST007", "Miguel Torres", "1° C", "2023-09-30", "2024-03-30", "Pendiente"},
            {"EST008", "Sofía Ramírez", "2° C", "2024-01-25", "2024-07-25", "Al día"}
    };
    private String[] studentColumns = {"ID", "Nombre", "Grado", "Última Vacuna", "Próxima Vacuna", "Estado"};

    public MedicoPanel() {
        // Configuración de la ventana principal para PC
        setTitle("Panel Médico - Sistema de Vacunación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tamaño optimizado para PC
        setBounds(100, 100, 1600, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar maximizado
        setMinimumSize(new Dimension(1280, 720)); // Tamaño mínimo

        // Panel principal
        contentPane = new JPanel(); // Crear el panel principal
        contentPane.setLayout(new BorderLayout(0, 0)); // Establecer el layout
        contentPane.setBackground(LIGHT_GRAY); // Establecer el color de fondo
        setContentPane(contentPane); // Asignar el panel principal a la ventana

        // Barra superior
        JPanel headerPanel = new JPanel(); // Crear el panel de encabezado
        headerPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80)); // Más alto para PC
        headerPanel.setLayout(new BorderLayout()); // Establecer el layout
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Borde inferior

        JLabel titleLabel = new JLabel("Panel Médico"); // Título del panel
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Fuente más grande
        titleLabel.setBorder(new EmptyBorder(0, 30, 0, 0)); // Espaciado interno
        headerPanel.add(titleLabel, BorderLayout.WEST); // Agregar título al panel de encabezado

        // Perfil de usuario
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel para el perfil de usuario
        userPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel userIcon = new JLabel("👨‍⚕️"); // Icono del médico
        userIcon.setFont(new Font("Arial", Font.PLAIN, 28)); // Icono más grande

        JLabel userName = new JLabel("Dr. González"); // Nombre del médico
        userName.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande
        userName.setBorder(new EmptyBorder(0, 5, 0, 30)); // Más padding

        userPanel.add(userIcon); // Agregar icono al panel de usuario
        userPanel.add(userName); // Agregar nombre al panel de usuario
        headerPanel.add(userPanel, BorderLayout.EAST); // Agregar panel de usuario al panel de encabezado

        contentPane.add(headerPanel, BorderLayout.NORTH); // Agregar el panel de encabezado al panel principal

        // Panel principal con pestañas
        tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Crear pestañas
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        contentPane.add(tabbedPane, BorderLayout.CENTER); // Agregar pestañas al panel principal

        // Configurar paneles
        setupRegistroVacunaPanel(); // Configurar el panel de registro de vacunas
        setupEstudiantesPanel(); // Configurar el panel de estudiantes

        // Agregar paneles a las pestañas
        tabbedPane.addTab("Registrar Vacuna", registroVacunaPanel); // Agregar pestaña de registro de vacuna
        tabbedPane.addTab("Estudiantes", estudiantesPanel); // Agregar pestaña de estudiantes
    }

    private void setupRegistroVacunaPanel() {
        registroVacunaPanel = new JPanel(); // Crear el panel de registro de vacunas
        registroVacunaPanel.setLayout(new BorderLayout()); // Establecer el layout
        registroVacunaPanel.setBackground(LIGHT_GRAY); // Establecer el color de fondo
        registroVacunaPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Más padding

        // Panel de formulario con fondo blanco
        JPanel formCard = new JPanel(); // Crear el panel del formulario
        formCard.setLayout(new BorderLayout()); // Establecer el layout
        formCard.setBackground(WHITE_COLOR); // Establecer el color de fondo
        formCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Borde del formulario

        // Título del formulario
        JPanel formHeaderPanel = new JPanel(new BorderLayout()); // Panel para el encabezado del formulario
        formHeaderPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        formHeaderPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Más padding

        JLabel formTitle = new JLabel("Registrar Nueva Vacuna"); // Título del formulario
        formTitle.setFont(new Font("Arial", Font.BOLD, 22)); // Fuente más grande
        formHeaderPanel.add(formTitle, BorderLayout.WEST); // Agregar título al panel de encabezado

        formCard.add(formHeaderPanel, BorderLayout.NORTH); // Agregar encabezado al panel del formulario

        // Contenido del formulario
        JPanel formContentPanel = new JPanel(); // Crear el panel de contenido del formulario
        formContentPanel.setLayout(new BoxLayout(formContentPanel, BoxLayout.Y_AXIS)); // Orientación vertical
        formContentPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        formContentPanel.setBorder(new EmptyBorder(20, 30, 30, 30)); // Más padding

        // Fila 1: Estudiante y Tipo de Vacuna
        JPanel row1 = new JPanel(new GridLayout(1, 2, 30, 0)); // Más espacio entre columnas
        row1.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row1.setMaximumSize(new Dimension(1800, 80)); // Más alto

        // Panel de estudiante
        JPanel estudiantePanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        estudiantePanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel estudianteLabel = new JLabel("Estudiante"); // Etiqueta para el campo de estudiante
        estudianteLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        String[] estudiantes = {"Seleccionar estudiante", "Juan Pérez - 1° A", "María García - 2° B",
                "Carlos López - 3° C", "Ana Rodríguez - 1° B", "Pedro Sánchez - 2° A"};
        JComboBox<String> estudianteCombo = new JComboBox<>(estudiantes); // ComboBox para seleccionar estudiante
        estudianteCombo.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        estudianteCombo.setPreferredSize(new Dimension(0, 40)); // Más alto

        estudiantePanel.add(estudianteLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        estudiantePanel.add(estudianteCombo, BorderLayout.CENTER); // Agregar ComboBox al panel

        // Panel de tipo de vacuna
        JPanel vacunaPanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        vacunaPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel vacunaLabel = new JLabel("Tipo de Vacuna"); // Etiqueta para el campo de tipo de vacuna
        vacunaLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        String[] vacunas = {"Seleccionar vacuna", "Triple Viral", "Influenza", "Hepatitis B", "DPT", "BCG"};
        JComboBox<String> vacunaCombo = new JComboBox<>(vacunas); // ComboBox para seleccionar tipo de vacuna
        vacunaCombo.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        vacunaCombo.setPreferredSize(new Dimension(0, 40)); // Más alto

        vacunaPanel.add(vacunaLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        vacunaPanel.add(vacunaCombo, BorderLayout.CENTER); // Agregar ComboBox al panel

        row1.add(estudiantePanel); // Agregar panel de estudiante a la fila
        row1.add(vacunaPanel); // Agregar panel de vacuna a la fila

        // Fila 2: Fecha de Aplicación y Número de Lote
        JPanel row2 = new JPanel(new GridLayout(1, 2, 30, 0)); // Más espacio entre columnas
        row2.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row2.setMaximumSize(new Dimension(1800, 80)); // Más alto

        // Panel de fecha
        JPanel fechaPanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        fechaPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel fechaLabel = new JLabel("Fecha de Aplicación"); // Etiqueta para la fecha
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        JPanel fechaPickerPanel = new JPanel(new BorderLayout()); // Panel para el selector de fecha
        fechaPickerPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JTextField fechaField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date())); // Campo de texto para la fecha
        fechaField.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        fechaField.setEditable(false); // Campo no editable
        fechaField.setPreferredSize(new Dimension(0, 40)); // Más alto

        JButton fechaButton = new JButton("📅"); // Botón para seleccionar fecha
        fechaButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        fechaButton.setFocusPainted(false); // Sin borde al hacer clic
        fechaButton.setPreferredSize(new Dimension(50, 40)); // Más ancho y alto

        fechaPickerPanel.add(fechaField, BorderLayout.CENTER); // Agregar campo de texto al panel
        fechaPickerPanel.add(fechaButton, BorderLayout.EAST); // Agregar botón al panel

        fechaPanel.add(fechaLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        fechaPanel.add(fechaPickerPanel, BorderLayout.CENTER); // Agregar selector de fecha al panel

        // Panel de lote
        JPanel lotePanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        lotePanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel loteLabel = new JLabel("Número de Lote"); // Etiqueta para el número de lote
        loteLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        JTextField loteField = new JTextField(); // Campo de texto para el número de lote
        loteField.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        loteField.setPreferredSize(new Dimension(0, 40)); // Más alto

        lotePanel.add(loteLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        lotePanel.add(loteField, BorderLayout.CENTER); // Agregar campo de texto al panel

        row2.add(fechaPanel); // Agregar panel de fecha a la fila
        row2.add(lotePanel); // Agregar panel de lote a la fila

        // Fila 3: Dosis y Vía de Administración
        JPanel row3 = new JPanel(new GridLayout(1, 2, 30, 0)); // Más espacio entre columnas
        row3.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row3.setMaximumSize(new Dimension(1800, 80)); // Más alto

        // Panel de dosis
        JPanel dosisPanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        dosisPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel dosisLabel = new JLabel("Dosis"); // Etiqueta para la dosis
        dosisLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        String[] dosis = {"Seleccionar dosis", "Primera dosis", "Segunda dosis", "Tercera dosis", "Refuerzo"};
        JComboBox<String> dosisCombo = new JComboBox<>(dosis); // ComboBox para seleccionar dosis
        dosisCombo.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        dosisCombo.setPreferredSize(new Dimension(0, 40)); // Más alto

        dosisPanel.add(dosisLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        dosisPanel.add(dosisCombo, BorderLayout.CENTER); // Agregar ComboBox al panel

        // Panel de vía
        JPanel viaPanel = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        viaPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JLabel viaLabel = new JLabel("Vía de Administración"); // Etiqueta para la vía
        viaLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        String[] vias = {"Seleccionar vía", "Intramuscular", "Subcutánea", "Oral"};
        JComboBox<String> viaCombo = new JComboBox<>(vias); // ComboBox para seleccionar vía
        viaCombo.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        viaCombo.setPreferredSize(new Dimension(0, 40)); // Más alto

        viaPanel.add(viaLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        viaPanel.add(viaCombo, BorderLayout.CENTER); // Agregar ComboBox al panel

        row3.add(dosisPanel); // Agregar panel de dosis a la fila
        row3.add(viaPanel); // Agregar panel de vía a la fila

        // Fila 4: Observaciones
        JPanel row4 = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        row4.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row4.setMaximumSize(new Dimension(1800, 150)); // Más alto

        JLabel obsLabel = new JLabel("Observaciones y Reacciones"); // Etiqueta para observaciones
        obsLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        JTextArea obsArea = new JTextArea(); // Área de texto para observaciones
        obsArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        obsArea.setLineWrap(true); // Ajustar línea
        obsArea.setWrapStyleWord(true); // Ajustar palabra
        obsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Borde ligero

        JScrollPane obsScroll = new JScrollPane(obsArea); // Panel de desplazamiento para el área de texto
        obsScroll.setPreferredSize(new Dimension(0, 100)); // Más alto

        row4.add(obsLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        row4.add(obsScroll, BorderLayout.CENTER); // Agregar área de texto al panel

        // Fila 5: Próxima Dosis
        JPanel row5 = new JPanel(new BorderLayout(0, 8)); // Más espacio vertical
        row5.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row5.setMaximumSize(new Dimension(1800, 80)); // Más alto

        JLabel proxLabel = new JLabel("Próxima Dosis"); // Etiqueta para próxima dosis
        proxLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

        JPanel proxPickerPanel = new JPanel(new BorderLayout()); // Panel para seleccionar próxima dosis
        proxPickerPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JTextField proxField = new JTextField("Programar próxima dosis"); // Campo de texto para próxima dosis
        proxField.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        proxField.setEditable(false); // Campo no editable
        proxField.setForeground(Color.GRAY); // Color del texto
        proxField.setPreferredSize(new Dimension(0, 40)); // Más alto

        JButton proxButton = new JButton("📅"); // Botón para seleccionar fecha
        proxButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        proxButton.setFocusPainted(false); // Sin borde al hacer clic
        proxButton.setPreferredSize(new Dimension(50, 40)); // Más ancho y alto

        proxPickerPanel.add(proxField, BorderLayout.CENTER); // Agregar campo de texto al panel
        proxPickerPanel.add(proxButton, BorderLayout.EAST); // Agregar botón al panel

        row5.add(proxLabel, BorderLayout.NORTH); // Agregar etiqueta al panel
        row5.add(proxPickerPanel, BorderLayout.CENTER); // Agregar selector de próxima dosis al panel

        // Fila 6: Botones
        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel para botones
        row6.setBackground(WHITE_COLOR); // Establecer el color de fondo
        row6.setMaximumSize(new Dimension(1800, 60)); // Más alto

        JButton limpiarBtn = new JButton("Limpiar Formulario"); // Botón para limpiar el formulario
        limpiarBtn.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        limpiarBtn.setFocusPainted(false); // Sin borde al hacer clic
        limpiarBtn.setPreferredSize(new Dimension(180, 45)); // Más ancho y alto

        JButton registrarBtn = new JButton("Registrar Vacuna"); // Botón para registrar vacuna
        registrarBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande
        registrarBtn.setBackground(PRIMARY_COLOR); // Color de fondo
        registrarBtn.setForeground(WHITE_COLOR); // Color del texto
        registrarBtn.setFocusPainted(false); // Sin borde al hacer clic
        registrarBtn.setPreferredSize(new Dimension(180, 45)); // Más ancho y alto

        // Acción para registrar vacuna
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se puede agregar la lógica para registrar la vacuna en la base de datos
                // Ejemplo:
                // String estudianteSeleccionado = (String) estudianteCombo.getSelectedItem();
                // String tipoVacuna = (String) vacunaCombo.getSelectedItem();
                // String fechaAplicacion = fechaField.getText();
                // String numeroLote = loteField.getText();
                // String dosis = (String) dosisCombo.getSelectedItem();
                // String viaAdministracion = (String) viaCombo.getSelectedItem();
                // String observaciones = obsArea.getText();
                // Aquí se puede llamar a un método para insertar estos datos en la base de datos

                JOptionPane.showMessageDialog(MedicoPanel.this, "Vacuna registrada exitosamente.");
            }
        });

        row6.add(limpiarBtn); // Agregar botón de limpiar
        row6.add(Box.createRigidArea(new Dimension(15, 0))); // Más espacio
        row6.add(registrarBtn); // Agregar botón de registrar

        // Agregar filas al panel de contenido con más espacio entre ellas
        formContentPanel.add(row1); // Agregar fila 1
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Más espacio
        formContentPanel.add(row2); // Agregar fila 2
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Más espacio
        formContentPanel.add(row3); // Agregar fila 3
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Más espacio
        formContentPanel.add(row4); // Agregar fila 4
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Más espacio
        formContentPanel.add(row5); // Agregar fila 5
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Más espacio
        formContentPanel.add(row6); // Agregar fila 6

        // Agregar contenido al formulario
        JScrollPane formScrollPane = new JScrollPane(formContentPanel); // Panel de desplazamiento para el formulario
        formScrollPane.setBorder(null); // Sin borde
        formScrollPane.getVerticalScrollBar().setUnitIncrement(16); // Incremento de desplazamiento
        formCard.add(formScrollPane, BorderLayout.CENTER); // Agregar formulario al panel

        // Agregar formulario al panel principal
        registroVacunaPanel.add(formCard, BorderLayout.CENTER); // Agregar el panel de registro de vacunas
    }

    private void setupEstudiantesPanel() {
        estudiantesPanel = new JPanel(); // Crear el panel de estudiantes
        estudiantesPanel.setLayout(new BorderLayout()); // Establecer el layout
        estudiantesPanel.setBackground(LIGHT_GRAY); // Establecer el color de fondo
        estudiantesPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Más padding

        // Panel de búsqueda
        JPanel searchCard = new JPanel(); // Crear el panel de búsqueda
        searchCard.setLayout(new BorderLayout()); // Establecer el layout
        searchCard.setBackground(WHITE_COLOR); // Establecer el color de fondo
        searchCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Borde del panel
        searchCard.setPreferredSize(new Dimension(0, 100)); // Más alto

        JPanel searchContent = new JPanel(); // Crear el contenido del panel de búsqueda
        searchContent.setLayout(new BoxLayout(searchContent, BoxLayout.X_AXIS)); // Orientación horizontal
        searchContent.setBackground(WHITE_COLOR); // Establecer el color de fondo
        searchContent.setBorder(new EmptyBorder(25, 30, 25, 30)); // Más padding

        // Campo de búsqueda
        JPanel searchFieldPanel = new JPanel(new BorderLayout()); // Panel para el campo de búsqueda
        searchFieldPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        searchFieldPanel.setPreferredSize(new Dimension(400, 45)); // Más ancho y alto

        JLabel searchIcon = new JLabel("🔍"); // Icono de búsqueda
        searchIcon.setFont(new Font("Arial", Font.PLAIN, 18)); // Fuente más grande
        searchIcon.setBorder(new EmptyBorder(0, 10, 0, 10)); // Más padding

        JTextField searchField = new JTextField("Buscar estudiante..."); // Campo de texto para búsqueda
        searchField.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        searchField.setForeground(Color.GRAY); // Color del texto

        searchFieldPanel.add(searchIcon, BorderLayout.WEST); // Agregar icono al panel
        searchFieldPanel.add(searchField, BorderLayout.CENTER); // Agregar campo de texto al panel

        // Botones de filtro
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel para botones de filtro
        filterPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo

        JButton alDiaBtn = new JButton("✓ Al día"); // Botón para filtrar estudiantes al día
        alDiaBtn.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        alDiaBtn.setForeground(GREEN_TEXT); // Color del texto
        alDiaBtn.setBackground(WHITE_COLOR); // Color de fondo
        alDiaBtn.setFocusPainted(false); // Sin borde al hacer clic
        alDiaBtn.setBorder(BorderFactory.createLineBorder(GREEN_TEXT)); // Borde verde
        alDiaBtn.setPreferredSize(new Dimension(120, 45)); // Más ancho y alto

        JButton pendientesBtn = new JButton("⚠ Pendientes"); // Botón para filtrar estudiantes pendientes
        pendientesBtn.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        pendientesBtn.setForeground(RED_TEXT); // Color del texto
        pendientesBtn.setBackground(WHITE_COLOR); // Color de fondo
        pendientesBtn.setFocusPainted(false); // Sin borde al hacer clic
        pendientesBtn.setBorder(BorderFactory.createLineBorder(RED_TEXT)); // Borde rojo
        pendientesBtn.setPreferredSize(new Dimension(150, 45)); // Más ancho y alto

        // Agregar acción a los botones de filtro
        alDiaBtn.addActionListener(e -> {
            // Aquí puedes agregar la lógica para filtrar la tabla de estudiantes
            // Por ejemplo, mostrar solo los estudiantes "Al día"
        });

        pendientesBtn.addActionListener(e -> {
            // Aquí puedes agregar la lógica para filtrar la tabla de estudiantes
            // Por ejemplo, mostrar solo los estudiantes "Pendientes"
        });

        filterPanel.add(alDiaBtn); // Agregar botón de al día
        filterPanel.add(Box.createRigidArea(new Dimension(15, 0))); // Más espacio
        filterPanel.add(pendientesBtn); // Agregar botón de pendientes

        // Agregar componentes al panel de búsqueda
        searchContent.add(searchFieldPanel); // Agregar campo de búsqueda
        searchContent.add(Box.createHorizontalGlue()); // Espacio flexible
        searchContent.add(filterPanel); // Agregar panel de filtros

        searchCard.add(searchContent, BorderLayout.CENTER); // Agregar contenido al panel de búsqueda

        // Panel de tabla de estudiantes
        JPanel tableCard = new JPanel(); // Crear el panel de tabla
        tableCard.setLayout(new BorderLayout()); // Establecer el layout
        tableCard.setBackground(WHITE_COLOR); // Establecer el color de fondo
        tableCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Borde del panel

        // Título de la tabla
        JPanel tableHeaderPanel = new JPanel(new BorderLayout()); // Panel para el encabezado de la tabla
        tableHeaderPanel.setBackground(WHITE_COLOR); // Establecer el color de fondo
        tableHeaderPanel.setBorder(new EmptyBorder(20, 30, 20, 30)); // Más padding

        JLabel tableTitle = new JLabel("Lista de Estudiantes"); // Título de la tabla
        tableTitle.setFont(new Font("Arial", Font.BOLD, 22)); // Fuente más grande
        tableHeaderPanel.add(tableTitle, BorderLayout.WEST); // Agregar título al panel de encabezado

        tableCard.add(tableHeaderPanel, BorderLayout.NORTH); // Agregar encabezado al panel de tabla

        // Tabla de estudiantes
        DefaultTableModel tableModel = new DefaultTableModel(studentData, studentColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Las celdas no son editables
            }
        };

        JTable studentsTable = new JTable(tableModel); // Crear la tabla
        studentsTable.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente más grande
        studentsTable.setRowHeight(40); // Filas más altas
        studentsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande
        studentsTable.getTableHeader().setBackground(LIGHT_GRAY); // Color de fondo del encabezado
        studentsTable.getTableHeader().setPreferredSize(new Dimension(0, 50)); // Encabezado más alto
        studentsTable.setSelectionBackground(new Color(232, 240, 254)); // Color de fondo al seleccionar

        // Renderizador personalizado para la columna de estado
        studentsTable.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente más grande

            // Cambiar el color de fondo y texto según el estado
            if (value.equals("Al día")) {
                label.setBackground(GREEN_LIGHT);
                label.setForeground(GREEN_TEXT);
            } else {
                label.setBackground(RED_LIGHT);
                label.setForeground(RED_TEXT);
            }

            label.setBorder(new EmptyBorder(8, 15, 8, 15)); // Más padding

            if (isSelected) {
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            }

            return label; // Retornar el label personalizado
        });

        JScrollPane tableScrollPane = new JScrollPane(studentsTable); // Panel de desplazamiento para la tabla
        tableScrollPane.setBorder(null); // Sin borde

        tableCard.add(tableScrollPane, BorderLayout.CENTER); // Agregar tabla al panel de tabla

        // Agregar componentes al panel de estudiantes
        estudiantesPanel.add(searchCard, BorderLayout.NORTH); // Agregar panel de búsqueda
        estudiantesPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.CENTER); // Más espacio
        estudiantesPanel.add(tableCard, BorderLayout.CENTER); // Agregar panel de tabla
    }

    public static void main(String[] args) {
        try {
            // Establecer look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MedicoPanel frame = new MedicoPanel(); // Crear instancia del panel médico
                    frame.setVisible(true); // Hacer visible el panel médico
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo de errores
                }
            }
        });
    }
}