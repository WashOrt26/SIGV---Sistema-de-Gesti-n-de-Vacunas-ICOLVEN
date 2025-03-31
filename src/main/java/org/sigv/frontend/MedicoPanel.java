package org.sigv.frontend;

import javax.swing.*; // Importa clases de Swing para crear GUIs.
import javax.swing.border.EmptyBorder; // Importa EmptyBorder para bordes vacíos.
import javax.swing.table.DefaultTableCellRenderer; // Importa para renderizar celdas de tabla.
import javax.swing.table.DefaultTableModel; // Importa para manejar el modelo de datos de la tabla.
import javax.swing.table.TableCellRenderer; // Importa para personalizar la renderización de celdas.
import java.awt.*; // Importa clases de AWT para componentes gráficos.
import java.awt.event.ActionEvent; // Importa ActionEvent para eventos de acción.
import java.awt.event.ActionListener; // Importa ActionListener para manejar eventos de acción.
import java.awt.event.FocusAdapter; // Importa para manejar eventos de foco
import java.awt.event.FocusEvent; // Importa para eventos de foco
import java.text.SimpleDateFormat; // Importa para formatear fechas.
import java.util.Calendar; // Importa para manejar el calendario
import java.util.Date; // Importa la clase Date para manejar fechas.
import java.util.HashMap; // Importa HashMap para almacenar pares clave-valor.
import java.util.Map; // Importa la interfaz Map para colecciones de pares clave-valor.

public class MedicoPanel extends JFrame {
    // Colores personalizados
    private static final Color PRIMARY_COLOR = new Color(69, 153, 126); // #45997E
    private static final Color WHITE_COLOR = new Color(255, 255, 255);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color GREEN_LIGHT = new Color(220, 252, 231);
    private static final Color GREEN_TEXT = new Color(22, 163, 74);
    private static final Color RED_LIGHT = new Color(254, 226, 226);
    private static final Color RED_TEXT = new Color(220, 38, 38);

    // Componentes principales
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JPanel registroVacunaPanel;
    private JPanel estudiantesPanel;

    /// Datos de ejemplo
    private String[][] studentData = {
            {"juan.perez@email.com", "Juan Pérez", "1° A", "2024-02-01", "Al día", "Sin observaciones"},
            {"maria.garcia@email.com", "María García", "2° B", "2023-11-15", "Pendiente", "Alergia leve"},
            {"carlos.lopez@email.com", "Carlos López", "3° C", "2024-01-10", "Al día", "Sin observaciones"},
            {"ana.rodriguez@email.com", "Ana Rodríguez", "1° B", "2023-12-05", "Al día", "Fiebre baja post-vacuna"},
            {"pedro.sanchez@email.com", "Pedro Sánchez", "2° A", "2023-10-20", "Pendiente", "Sin observaciones"},
            {"laura.martinez@email.com", "Laura Martínez", "3° B", "2024-02-15", "Al día", "Sin observaciones"},
            {"miguel.torres@email.com", "Miguel Torres", "1° C", "2023-09-30", "Pendiente", "Dolor en el sitio de inyección"},
            {"sofia.ramirez@email.com", "Sofía Ramírez", "2° C", "2024-01-25", "Al día", "Sin observaciones"}
    };
    // Definición de las columnas de la tabla de estudiantes
    private String[] studentColumns = {"Correo", "Nombre", "Grado", "Última Vacuna", "Estado", "Detalles"};

    /// Mapa para almacenar las vacunas de cada estudiante
    private Map<String, String[]> vacunasEstudiantes = new HashMap<>();

    public MedicoPanel() {
        // Inicializar el mapa de vacunas
        inicializarVacunasEstudiantes();

        // Configuración de la ventana principal
        setTitle("Panel Médico - Sistema de Vacunación"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar aplicación al cerrar la ventana

        // Tamaño de la ventana
        setBounds(100, 100, 1600, 900); // Posición y tamaño inicial
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar maximizado
        setMinimumSize(new Dimension(1280, 720)); // Tamaño mínimo de la ventana

        // Panel principal
        contentPane = new JPanel(); // Crear panel principal
        contentPane.setLayout(new BorderLayout(0, 0)); // Usar diseño de borde
        contentPane.setBackground(LIGHT_GRAY); // Establecer color de fondo
        setContentPane(contentPane); // Establecer el panel principal en la ventana

        // Barra superior
        JPanel headerPanel = new JPanel(); // Crear panel para la barra superior
        headerPanel.setBackground(WHITE_COLOR); // Establecer color de fondo
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80)); // Tamaño preferido
        headerPanel.setLayout(new BorderLayout()); // Usar diseño de borde
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Borde inferior

        // Título de la aplicación
        JLabel titleLabel = new JLabel("Panel Médico"); // Crear etiqueta para el título
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Establecer fuente
        titleLabel.setBorder(new EmptyBorder(0, 30, 0, 0)); // Espaciado
        headerPanel.add(titleLabel, BorderLayout.WEST); // Agregar título a la izquierda

        // Panel de perfil de usuario
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Crear panel para el usuario
        userPanel.setBackground(WHITE_COLOR); // Establecer color de fondo

        // Icono de usuario (texto)
        JLabel userIcon = new JLabel("DR"); // Crear etiqueta para el icono
        userIcon.setFont(new Font("Arial", Font.BOLD, 24)); // Establecer fuente
        userIcon.setForeground(PRIMARY_COLOR); // Establecer color de texto
        userIcon.setPreferredSize(new Dimension(40, 40)); // Tamaño preferido
        userIcon.setHorizontalAlignment(SwingConstants.CENTER); // Alinear al centro
        userIcon.setOpaque(true); // Hacer opaco
        userIcon.setBackground(LIGHT_GRAY); // Establecer color de fondo
        userIcon.setBorder(BorderFactory.createLineBorder(PRIMARY_COLOR, 1)); // Borde

        // Nombre del usuario no se usa ahora se usa proximamente
        JLabel userName = new JLabel(" "); // Crear etiqueta para el nombre
        userName.setFont(new Font("Arial", Font.BOLD, 16)); // Establecer fuente
        userName.setBorder(new EmptyBorder(0, 10, 0, 30)); // Espaciado

        // Agregar icono y nombre al panel de usuario
        userPanel.add(userIcon);
        userPanel.add(userName);
        headerPanel.add(userPanel, BorderLayout.EAST); // Agregar panel de usuario a la derecha

        contentPane.add(headerPanel, BorderLayout.NORTH); // Agregar barra superior al panel principal

        // Panel principal con pestañas
        tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Crear pestañas
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16)); // Establecer fuente
        contentPane.add(tabbedPane, BorderLayout.CENTER); // Agregar pestañas al panel principal

        // Configurar paneles
        setupRegistroVacunaPanel(); // Configurar panel para registrar vacunas
        setupEstudiantesPanel(); // Configurar panel para mostrar estudiantes

        // Agregar paneles a las pestañas
        tabbedPane.addTab("Registrar Vacuna", registroVacunaPanel); // Pestaña para registrar vacunas
        tabbedPane.addTab("Estudiantes", estudiantesPanel); // Pestaña para mostrar estudiantes
    }

    /// Aqui estan los correos y las vacunas de cada estudiante, no se como inicializas eso
    private void inicializarVacunasEstudiantes() {
        // Inicializar el mapa con las vacunas de cada estudiante
        vacunasEstudiantes.put("juan.perez@email.com", new String[]{"Triple Viral", "Influenza"});
        vacunasEstudiantes.put("maria.garcia@email.com", new String[]{"Hepatitis B"});
        vacunasEstudiantes.put("carlos.lopez@email.com", new String[]{"Triple Viral", "DPT", "BCG"});
        vacunasEstudiantes.put("ana.rodriguez@email.com", new String[]{"Influenza", "Hepatitis B"});
        vacunasEstudiantes.put("pedro.sanchez@email.com", new String[]{"Triple Viral"});
        vacunasEstudiantes.put("laura.martinez@email.com", new String[]{"DPT", "BCG"});
        vacunasEstudiantes.put("miguel.torres@email.com", new String[]{"Influenza"});
        vacunasEstudiantes.put("sofia.ramirez@email.com", new String[]{"Triple Viral", "Hepatitis B", "BCG"});
    }

    private void setupRegistroVacunaPanel() {
        registroVacunaPanel = new JPanel();
        registroVacunaPanel.setLayout(new BorderLayout());
        registroVacunaPanel.setBackground(LIGHT_GRAY);
        registroVacunaPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Panel de formulario con fondo blanco
        JPanel formCard = new JPanel();
        formCard.setLayout(new BorderLayout());
        formCard.setBackground(WHITE_COLOR);
        formCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Título del formulario
        JPanel formHeaderPanel = new JPanel(new BorderLayout());
        formHeaderPanel.setBackground(WHITE_COLOR);
        formHeaderPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel formTitle = new JLabel("Registrar Nueva Vacuna");
        formTitle.setFont(new Font("Arial", Font.BOLD, 22));
        formHeaderPanel.add(formTitle, BorderLayout.WEST);

        formCard.add(formHeaderPanel, BorderLayout.NORTH);

        // Contenido del formulario
        JPanel formContentPanel = new JPanel();
        formContentPanel.setLayout(new BoxLayout(formContentPanel, BoxLayout.Y_AXIS));
        formContentPanel.setBackground(WHITE_COLOR);
        formContentPanel.setBorder(new EmptyBorder(20, 30, 30, 30));

        // Fila 1: Estudiante y Tipo de Vacuna
        JPanel row1 = new JPanel(new GridLayout(1, 2, 30, 0));
        row1.setBackground(WHITE_COLOR);
        row1.setMaximumSize(new Dimension(1800, 80));

        // Panel de estudiante
        JPanel estudiantePanel = new JPanel(new BorderLayout(0, 8));
        estudiantePanel.setBackground(WHITE_COLOR);

        JLabel estudianteLabel = new JLabel("Estudiante");
        estudianteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        /// Aca estan los esrudiantes para la selecion en registrar vacuna

        String[] estudiantes = {"Seleccionar estudiante", "Juan Pérez - 1° A", "María García - 2° B",
                "Carlos López - 3° C", "Ana Rodríguez - 1° B", "Pedro Sánchez - 2° A"};
        JComboBox<String> estudianteCombo = new JComboBox<>(estudiantes);
        estudianteCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        estudianteCombo.setPreferredSize(new Dimension(0, 40));

        estudiantePanel.add(estudianteLabel, BorderLayout.NORTH);
        estudiantePanel.add(estudianteCombo, BorderLayout.CENTER);

        // Panel de tipo de vacuna
        JPanel vacunaPanel = new JPanel(new BorderLayout(0, 8));
        vacunaPanel.setBackground(WHITE_COLOR);

        JLabel vacunaLabel = new JLabel("Tipo de Vacuna");
        vacunaLabel.setFont(new Font("Arial", Font.BOLD, 16));

        /// Estas son las vacunas para seleccionar
        String[] vacunas = {"Seleccionar vacuna", "Triple Viral", "Influenza", "Hepatitis B", "DPT", "BCG","VPH","COVID-19"};
        JComboBox<String> vacunaCombo = new JComboBox<>(vacunas);
        vacunaCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        vacunaCombo.setPreferredSize(new Dimension(0, 40));

        vacunaPanel.add(vacunaLabel, BorderLayout.NORTH);
        vacunaPanel.add(vacunaCombo, BorderLayout.CENTER);

        row1.add(estudiantePanel);
        row1.add(vacunaPanel);

        // Fila 1.6: Vacunas del estudiante seleccionado
        JPanel row1_6 = new JPanel(new BorderLayout(0, 8));
        row1_6.setBackground(WHITE_COLOR);
        row1_6.setMaximumSize(new Dimension(1800, 80));

        //Ya funciona por medio de la selección de estudainte
        ///Conectar base de datos
        JLabel vacunasEstudianteLabel = new JLabel("Vacunas ya aplicadas al estudiante");
        vacunasEstudianteLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField vacunasEstudianteField = new JTextField("Seleccione un estudiante para ver sus vacunas");
        vacunasEstudianteField.setFont(new Font("Arial", Font.PLAIN, 16));
        vacunasEstudianteField.setEditable(false);
        vacunasEstudianteField.setBackground(LIGHT_GRAY);

        /// Actualizar el campo de vacunas cuando se selecciona un estudiante
        estudianteCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = estudianteCombo.getSelectedIndex();

                if (selectedIndex > 0) {
                    // Obtener el correo del estudiante seleccionado
                    String correoEstudiante = studentData[selectedIndex - 1][0];
                    String[] vacunasAplicadas = vacunasEstudiantes.get(correoEstudiante);

                    if (vacunasAplicadas != null && vacunasAplicadas.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < vacunasAplicadas.length; i++) {
                            sb.append(vacunasAplicadas[i]);
                            if (i < vacunasAplicadas.length - 1) {
                                sb.append(", ");
                            }
                        }
                        vacunasEstudianteField.setText(sb.toString());
                    } else {
                        vacunasEstudianteField.setText("No hay vacunas aplicadas");
                    }
                } else {
                    vacunasEstudianteField.setText("Seleccione un estudiante para ver sus vacunas");
                }
            }
        });

        row1_6.add(vacunasEstudianteLabel, BorderLayout.NORTH);
        row1_6.add(vacunasEstudianteField, BorderLayout.CENTER);

        // Fila 2: Fecha de Aplicación
        JPanel row2 = new JPanel(new GridLayout(1, 1, 30, 0));
        row2.setBackground(WHITE_COLOR);
        row2.setMaximumSize(new Dimension(1800, 80));

        // Panel de fecha
        //Falta que el panel funcione
        JPanel fechaPanel = new JPanel(new BorderLayout(0, 8));
        fechaPanel.setBackground(WHITE_COLOR);

        JLabel fechaLabel = new JLabel("Fecha de Aplicación");
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel fechaPickerPanel = new JPanel(new BorderLayout());
        fechaPickerPanel.setBackground(WHITE_COLOR);

        JTextField fechaField = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        fechaField.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaField.setEditable(false);
        fechaField.setPreferredSize(new Dimension(0, 40));

        /// Botón de calendario con icono y funcionalidad
        JButton fechaButton = new JButton("📅");
        fechaButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        fechaButton.setFocusPainted(false);
        fechaButton.setPreferredSize(new Dimension(50, 40));

        /// Agregar funcionalidad al botón de calendario
        fechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar un selector de fecha
                JDialog dialog = new JDialog(MedicoPanel.this, "Seleccionar Fecha", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(400, 400); // Tamaño aumentado para mejor visibilidad
                dialog.setLocationRelativeTo(fechaButton);

                // Crear un JCalendar
                JCalendar calendar = new JCalendar();
                calendar.setTodayButtonVisible(true);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton okButton = new JButton("Aceptar");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener la fecha seleccionada y actualizar el campo
                        Date selectedDate = calendar.getDate();
                        fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(selectedDate));
                        dialog.dispose();
                    }
                });

                buttonPanel.add(okButton);

                dialog.add(calendar, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);
                dialog.setVisible(true);
            }
        });

        fechaPickerPanel.add(fechaField, BorderLayout.CENTER);
        fechaPickerPanel.add(fechaButton, BorderLayout.EAST);

        fechaPanel.add(fechaLabel, BorderLayout.NORTH);
        fechaPanel.add(fechaPickerPanel, BorderLayout.CENTER);

        row2.add(fechaPanel);

        // Fila 3: Estado del Estudiante (antes Dosis)
        JPanel row3 = new JPanel(new GridLayout(1, 1, 30, 0));
        row3.setBackground(WHITE_COLOR);
        row3.setMaximumSize(new Dimension(1800, 80));

        // Panel de estado
        JPanel estadoPanel = new JPanel(new BorderLayout(0, 8));
        estadoPanel.setBackground(WHITE_COLOR);

        JLabel estadoLabel = new JLabel("Estado del Estudiante");
        estadoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        /// Selecionar el estado del estudiante
        String[] estados = {"Seleccionar estado", "Al día", "Pendiente"};
        JComboBox<String> estadoCombo = new JComboBox<>(estados);
        estadoCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        estadoCombo.setPreferredSize(new Dimension(0, 40));

        estadoPanel.add(estadoLabel, BorderLayout.NORTH);
        estadoPanel.add(estadoCombo, BorderLayout.CENTER);

        row3.add(estadoPanel);

        /// Observaciones
        // Fila 4: Observaciones
        JPanel row4 = new JPanel(new BorderLayout(0, 8));
        row4.setBackground(WHITE_COLOR);
        row4.setMaximumSize(new Dimension(1800, 150));

        JLabel obsLabel = new JLabel("Observaciones y Reacciones");
        obsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea obsArea = new JTextArea();
        obsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        obsArea.setLineWrap(true);
        obsArea.setWrapStyleWord(true);
        obsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane obsScroll = new JScrollPane(obsArea);
        obsScroll.setPreferredSize(new Dimension(0, 100));

        row4.add(obsLabel, BorderLayout.NORTH);
        row4.add(obsScroll, BorderLayout.CENTER);

        // Fila 6: Botones
        JPanel row6 = new JPanel();
        row6.setLayout(new FlowLayout(FlowLayout.RIGHT));
        row6.setBackground(WHITE_COLOR);
        row6.setMaximumSize(new Dimension(1800, 60));

        JButton limpiarBtn = new JButton("Limpiar Formulario");
        limpiarBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        limpiarBtn.setFocusPainted(false);
        limpiarBtn.setPreferredSize(new Dimension(180, 45));

        // Botón mejorado para registrar vacuna
        JButton registrarBtn = new JButton("Registrar Vacuna");
        registrarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registrarBtn.setBackground(PRIMARY_COLOR);
        registrarBtn.setForeground(WHITE_COLOR);
        registrarBtn.setFocusPainted(false);
        registrarBtn.setBorderPainted(false);
        registrarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registrarBtn.setPreferredSize(new Dimension(200, 45));

        // Efecto hover para el botón
        registrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarBtn.setBackground(new Color(58, 130, 107)); // Un poco más oscuro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrarBtn.setBackground(PRIMARY_COLOR);
            }
        });
        /// Se supone que aca va base de datos para que al registrar una nueva vacuna se inserte en la base de datos y aja,haz tu magia
        // Acción para el botón de registrar
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que se hayan seleccionado los campos obligatorios
                if (estudianteCombo.getSelectedIndex() == 0 ||
                        vacunaCombo.getSelectedIndex() == 0 ||
                        estadoCombo.getSelectedIndex() == 0) {

                    JOptionPane.showMessageDialog(MedicoPanel.this,
                            "Por favor complete todos los campos obligatorios.",
                            "Campos incompletos",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                /// Aquí iría la lógica para registrar la vacuna en la base de datos eso creo
                JOptionPane.showMessageDialog(MedicoPanel.this,
                        "Vacuna registrada exitosamente.",
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Limpiar el formulario funciona
                estudianteCombo.setSelectedIndex(0);
                vacunaCombo.setSelectedIndex(0);
                estadoCombo.setSelectedIndex(0);
                obsArea.setText("");
                fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                vacunasEstudianteField.setText("Seleccione un estudiante para ver sus vacunas");
            }
        });
        //Funciona bien
        // Acción para el botón de limpiar
        limpiarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudianteCombo.setSelectedIndex(0);
                vacunaCombo.setSelectedIndex(0);
                estadoCombo.setSelectedIndex(0);
                obsArea.setText("");
                fechaField.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                vacunasEstudianteField.setText("Seleccione un estudiante para ver sus vacunas");
            }
        });

        row6.add(limpiarBtn);
        row6.add(Box.createRigidArea(new Dimension(15, 0)));
        row6.add(registrarBtn);

        // Agregar filas al panel de contenido con más espacio entre ellas
        formContentPanel.add(row1);
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formContentPanel.add(row1_6);
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formContentPanel.add(row2);
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formContentPanel.add(row3);
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formContentPanel.add(row4);
        formContentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formContentPanel.add(row6);

        // Agregar contenido al formulario
        JScrollPane formScrollPane = new JScrollPane(formContentPanel);
        formScrollPane.setBorder(null);
        formScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        formCard.add(formScrollPane, BorderLayout.CENTER);

        // Agregar formulario al panel principal
        registroVacunaPanel.add(formCard, BorderLayout.CENTER);
    }

    private void setupEstudiantesPanel() {
        estudiantesPanel = new JPanel();
        estudiantesPanel.setLayout(new BorderLayout());
        estudiantesPanel.setBackground(LIGHT_GRAY);
        estudiantesPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Panel de búsqueda y filtros
        JPanel searchCard = new JPanel();
        searchCard.setLayout(new BorderLayout());
        searchCard.setBackground(WHITE_COLOR);
        searchCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Panel para el contenido de búsqueda y filtros
        JPanel searchContent = new JPanel();
        searchContent.setLayout(new BorderLayout());
        searchContent.setBackground(WHITE_COLOR);
        searchContent.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Panel para el campo de búsqueda (izquierda)
        JPanel searchFieldPanel = new JPanel(new BorderLayout());
        searchFieldPanel.setBackground(WHITE_COLOR);
        searchFieldPanel.setPreferredSize(new Dimension(400, 45));

        // Icono de búsqueda
        JLabel searchIcon = new JLabel("  🔍");
        searchIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        searchIcon.setPreferredSize(new Dimension(40, 45));

        //Falta que esto funcione tipo place holder
        JTextField searchField = new JTextField("Buscar estudiante...");
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setForeground(Color.GRAY);

        // Agregar comportamiento de placeholder
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Buscar estudiante...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Buscar estudiante...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        /// Agregar funcionalidad de búsqueda pa que se pueda buscar
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().toLowerCase();
                if (searchText.equals("buscar estudiante...") || searchText.isEmpty()) {
                    return;
                }

                // Filtrar la tabla según el texto de búsqueda
                DefaultTableModel filteredModel = new DefaultTableModel(studentColumns, 0);
                for (String[] row : studentData) {
                    // Buscar en correo, nombre y grado
                    if (row[0].toLowerCase().contains(searchText) ||
                            row[1].toLowerCase().contains(searchText) ||
                            row[2].toLowerCase().contains(searchText)) {
                        filteredModel.addRow(row);
                    }
                }

                JTable studentsTable = (JTable) ((JScrollPane) ((JPanel) estudiantesPanel
                        .getComponent(1)).getComponent(1)).getViewport().getView();
                studentsTable.setModel(filteredModel);

                // Volver a aplicar los renderizadores personalizados
                setupTableRenderers(studentsTable);
            }
        });

        searchFieldPanel.add(searchIcon, BorderLayout.WEST);
        searchFieldPanel.add(searchField, BorderLayout.CENTER);

        // Panel para los botones de filtro (derecha)
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        filterPanel.setBackground(WHITE_COLOR);

        // Botón para registrar vacuna (nuevo)
        JButton registrarVacunaBtn = new JButton("Registrar Vacuna");
        registrarVacunaBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registrarVacunaBtn.setBackground(PRIMARY_COLOR);
        registrarVacunaBtn.setForeground(WHITE_COLOR);
        registrarVacunaBtn.setFocusPainted(false);
        registrarVacunaBtn.setBorderPainted(false);
        registrarVacunaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registrarVacunaBtn.setPreferredSize(new Dimension(200, 45));

        // Efecto hover para el botón
        registrarVacunaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registrarVacunaBtn.setBackground(new Color(58, 130, 107)); // Un poco más oscuro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registrarVacunaBtn.setBackground(PRIMARY_COLOR);
            }
        });

        /// Acción para el botón de registrar vacuna
        registrarVacunaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0); // Cambiar a la pestaña de registro de vacuna
            }
        });

        JButton alDiaBtn = new JButton("✓ Al día");
        alDiaBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        alDiaBtn.setForeground(GREEN_TEXT);
        alDiaBtn.setBackground(WHITE_COLOR);
        alDiaBtn.setFocusPainted(false);
        alDiaBtn.setBorder(BorderFactory.createLineBorder(GREEN_TEXT));
        alDiaBtn.setPreferredSize(new Dimension(120, 45));

        JButton pendientesBtn = new JButton("⚠ Pendientes");
        pendientesBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        pendientesBtn.setForeground(RED_TEXT);
        pendientesBtn.setBackground(WHITE_COLOR);
        pendientesBtn.setFocusPainted(false);
        pendientesBtn.setBorder(BorderFactory.createLineBorder(RED_TEXT));
        pendientesBtn.setPreferredSize(new Dimension(120, 45));

        filterPanel.add(registrarVacunaBtn);
        filterPanel.add(alDiaBtn);
        filterPanel.add(pendientesBtn);

        // Agregar componentes al panel de búsqueda
        searchContent.add(searchFieldPanel, BorderLayout.WEST);
        searchContent.add(filterPanel, BorderLayout.EAST);

        searchCard.add(searchContent, BorderLayout.CENTER);

        // Panel de tabla de estudiantes
        JPanel tableCard = new JPanel();
        tableCard.setLayout(new BorderLayout());
        tableCard.setBackground(WHITE_COLOR);
        tableCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Título de la tabla
        JPanel tableHeaderPanel = new JPanel(new BorderLayout());
        tableHeaderPanel.setBackground(WHITE_COLOR);
        tableHeaderPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel tableTitle = new JLabel("Lista de Estudiantes");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 22));
        tableHeaderPanel.add(tableTitle, BorderLayout.WEST);

        tableCard.add(tableHeaderPanel, BorderLayout.NORTH);

        /// Tabla de estudiantes
        DefaultTableModel tableModel = new DefaultTableModel(studentData, studentColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Ninguna celda es editable
            }
        };
        /// Tabla de estudiante
        JTable studentsTable = new JTable(tableModel);
        studentsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        studentsTable.setRowHeight(40);
        studentsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        studentsTable.getTableHeader().setBackground(LIGHT_GRAY);
        studentsTable.getTableHeader().setPreferredSize(new Dimension(0, 50));
        studentsTable.setSelectionBackground(new Color(93, 131, 143));

        /// Configurar renderizadores de la tabla
        setupTableRenderers(studentsTable);

        /// Agregar listener para el botón "Ver detalle" en la columna de detalles
        studentsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = studentsTable.rowAtPoint(evt.getPoint());
                int col = studentsTable.columnAtPoint(evt.getPoint());

                if (col == 5 && row >= 0) {
                    /// Obtener datos del estudiante
                    String correoEstudiante = (String) tableModel.getValueAt(row, 0);
                    String studentName = (String) tableModel.getValueAt(row, 1);
                    String observaciones = (String) tableModel.getValueAt(row, 5);

                    /// Obtener las vacunas del estudiante
                    String[] vacunas = vacunasEstudiantes.get(correoEstudiante);

                    /// Crear y mostrar el diálogo con las vacunas y observaciones
                    JDialog dialog = new JDialog(MedicoPanel.this, "Detalles de " + studentName, true);
                    dialog.setLayout(new BorderLayout());
                    dialog.setSize(500, 400);
                    dialog.setLocationRelativeTo(MedicoPanel.this);

                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                    contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

                    JLabel titleLabel = new JLabel("Información de " + studentName);
                    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

                    JLabel vacunasTitle = new JLabel("Vacunas aplicadas:");
                    vacunasTitle.setFont(new Font("Arial", Font.BOLD, 16));
                    vacunasTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
                    vacunasTitle.setBorder(new EmptyBorder(15, 0, 5, 0));

                    contentPanel.add(titleLabel);
                    contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    contentPanel.add(vacunasTitle);

                    /// Mostrar vacunas
                    if (vacunas != null && vacunas.length > 0) {
                        for (String vacuna : vacunas) {
                            JLabel vacunaLabel = new JLabel("• " + vacuna);
                            vacunaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                            vacunaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                            contentPanel.add(vacunaLabel);
                            contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                        }
                    } else {
                        JLabel noVacunasLabel = new JLabel("No hay vacunas registradas");
                        noVacunasLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                        noVacunasLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        contentPanel.add(noVacunasLabel);
                    }

                    /// Mostrar observaciones
                    JLabel obsTitle = new JLabel("Observaciones:");
                    obsTitle.setFont(new Font("Arial", Font.BOLD, 16));
                    obsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
                    obsTitle.setBorder(new EmptyBorder(20, 0, 5, 0));
                    contentPanel.add(obsTitle);

                    JTextArea obsArea = new JTextArea(observaciones);
                    obsArea.setFont(new Font("Arial", Font.PLAIN, 14));
                    obsArea.setLineWrap(true);
                    obsArea.setWrapStyleWord(true);
                    obsArea.setEditable(false);
                    obsArea.setBackground(contentPanel.getBackground());
                    obsArea.setAlignmentX(Component.LEFT_ALIGNMENT);
                    contentPanel.add(obsArea);

                    JButton closeButton = new JButton("Cerrar");
                    closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
                    closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                    closeButton.addActionListener(e -> dialog.dispose());

                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    buttonPanel.add(closeButton);

                    JScrollPane scrollPane = new JScrollPane(contentPanel);
                    scrollPane.setBorder(null);

                    dialog.add(scrollPane, BorderLayout.CENTER);
                    dialog.add(buttonPanel, BorderLayout.SOUTH);
                    dialog.setVisible(true);
                }
            }
        });

        // Ajustar el ancho de las columnas
        studentsTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Correo
        studentsTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
        studentsTable.getColumnModel().getColumn(2).setPreferredWidth(80);  // Grado
        studentsTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Última Vacuna
        studentsTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Estado
        studentsTable.getColumnModel().getColumn(5).setPreferredWidth(150); // Detalles

        JScrollPane tableScrollPane = new JScrollPane(studentsTable);
        tableScrollPane.setBorder(null);

        tableCard.add(tableScrollPane, BorderLayout.CENTER);

        /// Agregar componentes al panel de estudiantes
        estudiantesPanel.add(searchCard, BorderLayout.NORTH);
        estudiantesPanel.add(tableCard, BorderLayout.CENTER);

        /// Filtros que funcionan
        alDiaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filtrar la tabla para mostrar solo estudiantes "Al día"
                DefaultTableModel filteredModel = new DefaultTableModel(studentColumns, 0);
                for (String[] row : studentData) {
                    if (row[4].equals("Al día")) {
                        filteredModel.addRow(row);
                    }
                }
                studentsTable.setModel(filteredModel);

                // Volver a aplicar los renderizadores personalizados
                setupTableRenderers(studentsTable);
            }
        });

        pendientesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filtrar la tabla para mostrar solo estudiantes "Pendientes"
                DefaultTableModel filteredModel = new DefaultTableModel(studentColumns, 0);
                for (String[] row : studentData) {
                    if (row[4].equals("Pendiente")) {
                        filteredModel.addRow(row);
                    }
                }
                studentsTable.setModel(filteredModel);

                // Volver a aplicar los renderizadores personalizados
                setupTableRenderers(studentsTable);
            }
        });
    }

    ///Configura los renderizadores personalizados para la tabla de estudiantes///
    private void setupTableRenderers(JTable table) {
        // Renderizador para la columna de estado
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(JLabel.CENTER);

                if (value.equals("Al día")) {
                    label.setBackground(GREEN_LIGHT);
                    label.setForeground(GREEN_TEXT);
                } else {
                    label.setBackground(RED_LIGHT);
                    label.setForeground(RED_TEXT);
                }

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                }

                return label;
            }
        });

        // Renderizador para la columna de detalles
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());

                JButton verBtn = new JButton("Ver detalle");
                verBtn.setFont(new Font("Arial", Font.PLAIN, 12));
                verBtn.setPreferredSize(new Dimension(100, 30));
                verBtn.setFocusPainted(false);
                verBtn.setBackground(PRIMARY_COLOR);
                verBtn.setForeground(WHITE_COLOR);
                verBtn.setBorderPainted(false);

                panel.add(verBtn, BorderLayout.CENTER);

                return panel;
            }
        });
    }

    // Clase interna para implementar un selector de calendario
    private class JCalendar extends JPanel {
        private Calendar calendar;
        private JButton todayButton;
        private JLabel monthLabel;
        private JPanel daysPanel;

        public JCalendar() {
            calendar = Calendar.getInstance();
            setLayout(new BorderLayout());

            // Panel superior con mes y año
            JPanel headerPanel = new JPanel(new BorderLayout());

            JButton prevButton = new JButton("<");
            prevButton.addActionListener(e -> {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar();
            });

            JButton nextButton = new JButton(">");
            nextButton.addActionListener(e -> {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar();
            });

            monthLabel = new JLabel("", JLabel.CENTER);
            monthLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente más grande

            headerPanel.add(prevButton, BorderLayout.WEST);
            headerPanel.add(monthLabel, BorderLayout.CENTER);
            headerPanel.add(nextButton, BorderLayout.EAST);

            // Panel de días
            daysPanel = new JPanel(new GridLayout(7, 7));

            // Panel inferior con botón "Hoy"
            JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            todayButton = new JButton("Hoy");
            todayButton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente más grande
            todayButton.addActionListener(e -> {
                calendar = Calendar.getInstance();
                updateCalendar();
            });
            footerPanel.add(todayButton);

            add(headerPanel, BorderLayout.NORTH);
            add(daysPanel, BorderLayout.CENTER);
            add(footerPanel, BorderLayout.SOUTH);

            updateCalendar();
        }

        private void updateCalendar() {
            daysPanel.removeAll();

            // Nombres de los días de la semana
            String[] dayNames = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
            for (String dayName : dayNames) {
                JLabel label = new JLabel(dayName, JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente más grande
                daysPanel.add(label);
            }

            // Actualizar etiqueta del mes y año
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
            monthLabel.setText(sdf.format(calendar.getTime()));

            // Obtener el primer día del mes
            Calendar temp = (Calendar) calendar.clone();
            temp.set(Calendar.DAY_OF_MONTH, 1);
            int firstDayOfWeek = temp.get(Calendar.DAY_OF_WEEK) - 1;

            // Días del mes anterior
            temp.add(Calendar.DAY_OF_MONTH, -firstDayOfWeek);
            for (int i = 0; i < firstDayOfWeek; i++) {
                JButton dayButton = new JButton(String.valueOf(temp.get(Calendar.DAY_OF_MONTH)));
                dayButton.setEnabled(false);
                dayButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente más grande
                daysPanel.add(dayButton);
                temp.add(Calendar.DAY_OF_MONTH, 1);
            }

            // Días del mes actual
            temp = (Calendar) calendar.clone();
            temp.set(Calendar.DAY_OF_MONTH, 1);
            int daysInMonth = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int i = 1; i <= daysInMonth; i++) {
                final int day = i;
                JButton dayButton = new JButton(String.valueOf(i));
                dayButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente más grande
                dayButton.addActionListener(e -> {
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    updateCalendar();
                });
                daysPanel.add(dayButton);
            }

            // Rellenar el resto de la cuadrícula
            int remainingCells = 7 * 7 - firstDayOfWeek - daysInMonth;
            for (int i = 1; i <= remainingCells; i++) {
                JButton dayButton = new JButton(String.valueOf(i));
                dayButton.setEnabled(false);
                dayButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Fuente más grande
                daysPanel.add(dayButton);
            }

            revalidate();
            repaint();
        }

        public Date getDate() {
            return calendar.getTime();
        }

        public void setTodayButtonVisible(boolean visible) {
            todayButton.setVisible(visible);
        }
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
                    MedicoPanel frame = new MedicoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}