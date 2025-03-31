package org.sigv.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstudiantePanel extends JFrame {
    // Colores personalizados
    //Imcompleto
    private static final Color PRIMARY_COLOR = new Color(69, 153, 126); // #45997E
    private static final Color WHITE_COLOR = new Color(255, 255, 255);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color GREEN_LIGHT = new Color(220, 252, 231);
    private static final Color GREEN_TEXT = new Color(22, 163, 74);
    private static final Color RED_LIGHT = new Color(254, 226, 226);
    private static final Color RED_TEXT = new Color(220, 38, 38);
    private static final Color YELLOW_LIGHT = new Color(254, 249, 195);
    private static final Color YELLOW_TEXT = new Color(202, 138, 4);

    // Componentes principales
    private JPanel contentPane;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel studentInfoPanel;
    private JPanel alertsPanel;
    private JPanel historyPanel;
    private JPanel footerPanel;

    // Datos de ejemplo
    private Estudiante estudiante;
    private Vacuna[] historialVacunas;

    public EstudiantePanel() {
        // Inicializar datos de ejemplo
        setupSampleData();

        // Configuración de la ventana principal para PC
        setTitle("Panel de Estudiante - Sistema de Vacunación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tamaño optimizado para PC
        setBounds(100, 100, 1600, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar maximizado
        setMinimumSize(new Dimension(1280, 720));

        // Panel principal
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(LIGHT_GRAY);
        setContentPane(contentPane);

        // Configurar paneles
        setupHeaderPanel();
        setupMainPanel();
        setupFooterPanel();

        // Agregar paneles al contenedor principal
        contentPane.add(headerPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(footerPanel, BorderLayout.SOUTH);
    }

    private void setupSampleData() {
        /// Conecatddo con base de datos
        estudiante = new Estudiante("EST001", "Juan Pérez", "1° A", "Al día");

        /// Deberia conectarse con aja las vacuans
        // Historial de vacunas
        historialVacunas = new Vacuna[] {
                new Vacuna("2024-02-01", "Triple Viral", "Dr. González", "Centro Médico Escolar", "Completada"),
                new Vacuna("2023-11-15", "Hepatitis B", "Dra. Rodríguez", "Hospital Central", "Completada"),
                new Vacuna("2023-08-20", "Influenza", "Dr. Martínez", "Centro de Salud", "Completada"),
                new Vacuna("2024-08-01", "Influenza", "Pendiente", "Por definir", "Pendiente")
        };
    }

    private void setupHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setBackground(WHITE_COLOR);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80)); // Más alto para PC
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel titleLabel = new JLabel("Carnet de Vacunación");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Fuente más grande
        titleLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Botón para descargar carnet
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(WHITE_COLOR);

        JButton downloadBtn = new JButton("Descargar Carnet Digital");
        downloadBtn.setFont(new Font("Arial", Font.BOLD, 14));
        downloadBtn.setBackground(PRIMARY_COLOR);
        downloadBtn.setForeground(WHITE_COLOR);
        downloadBtn.setFocusPainted(false);
        downloadBtn.setBorderPainted(false);
        downloadBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        downloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(EstudiantePanel.this,
                        "Carnet digital descargado exitosamente.",
                        "Descarga exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        actionPanel.add(downloadBtn);
        headerPanel.add(actionPanel, BorderLayout.EAST);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(LIGHT_GRAY);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Configurar paneles de información
        setupStudentInfoPanel();
        setupAlertsPanel();
        setupHistoryPanel();

        // Agregar paneles al panel principal
        mainPanel.add(studentInfoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(alertsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(historyPanel);
    }

    private void setupStudentInfoPanel() {
        studentInfoPanel = new JPanel();
        studentInfoPanel.setLayout(new BorderLayout());
        studentInfoPanel.setBackground(WHITE_COLOR);
        studentInfoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JPanel infoContent = new JPanel(new GridLayout(1, 4, 20, 0));
        infoContent.setBackground(WHITE_COLOR);
        infoContent.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Información del estudiante
        JPanel namePanel = createInfoField("Nombre", estudiante.getNombre());
        JPanel idPanel = createInfoField("ID Estudiante", estudiante.getId());

        // Estado de vacunación
        JPanel statusPanel = new JPanel(new BorderLayout(0, 5));
        statusPanel.setBackground(WHITE_COLOR);

        JLabel statusLabel = new JLabel("Estado de Vacunación");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.GRAY);

        JPanel statusValuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusValuePanel.setBackground(WHITE_COLOR);

        JLabel statusValue = new JLabel(estudiante.getEstado());
        statusValue.setFont(new Font("Arial", Font.BOLD, 16));
        statusValue.setOpaque(true);

        if (estudiante.getEstado().equals("Al día")) {
            statusValue.setBackground(GREEN_LIGHT);
            statusValue.setForeground(GREEN_TEXT);
        } else {
            statusValue.setBackground(YELLOW_LIGHT);
            statusValue.setForeground(YELLOW_TEXT);
        }

        statusValue.setBorder(new EmptyBorder(5, 10, 5, 10));
        statusValuePanel.add(statusValue);

        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(statusValuePanel, BorderLayout.CENTER);

        // Agregar paneles a la información
        infoContent.add(namePanel);
        infoContent.add(idPanel);
        infoContent.add(statusPanel);

        // Título del panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(WHITE_COLOR);
        titlePanel.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel infoTitle = new JLabel("Información del Estudiante");
        infoTitle.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(infoTitle, BorderLayout.WEST);

        // Agregar componentes al panel de información
        studentInfoPanel.add(titlePanel, BorderLayout.NORTH);
        studentInfoPanel.add(infoContent, BorderLayout.CENTER);
    }

    private JPanel createInfoField(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(WHITE_COLOR);

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.PLAIN, 14));
        labelComponent.setForeground(Color.GRAY);

        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(labelComponent, BorderLayout.NORTH);
        panel.add(valueComponent, BorderLayout.CENTER);

        return panel;
    }

    private void setupAlertsPanel() {
        alertsPanel = new JPanel();
        alertsPanel.setLayout(new BoxLayout(alertsPanel, BoxLayout.Y_AXIS));

        // Alerta de vacuna pendiente
        JPanel pendingAlert = createAlert(
                "Vacuna Pendiente",
                "Vacuna de Influenza programada para el 01/08/2024",
                RED_LIGHT,
                RED_TEXT
        );

        // Alerta de última vacuna
        JPanel lastVaccineAlert = createAlert(
                "Última Vacuna Aplicada",
                "Triple Viral - Aplicada el 01/02/2024",
                GREEN_LIGHT,
                GREEN_TEXT
        );

        alertsPanel.add(pendingAlert);
        alertsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        alertsPanel.add(lastVaccineAlert);
    }

    private JPanel createAlert(String title, String message, Color bgColor, Color textColor) {
        JPanel alertPanel = new JPanel();
        alertPanel.setLayout(new BorderLayout());
        alertPanel.setBackground(bgColor);
        alertPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(textColor, 1),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(bgColor);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(textColor);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(textColor);

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(messageLabel);

        alertPanel.add(contentPanel, BorderLayout.CENTER);

        return alertPanel;
    }

    private void setupHistoryPanel() {
        historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        historyPanel.setBackground(WHITE_COLOR);
        historyPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Título del panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(WHITE_COLOR);
        titlePanel.setBorder(new EmptyBorder(15, 30, 15, 30));

        JLabel historyTitle = new JLabel("Historial de Vacunación");
        historyTitle.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(historyTitle, BorderLayout.WEST);

        // Tabla de historial
        String[] columns = {"Fecha", "Vacuna", "Doctor", "Ubicación", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Agregar datos a la tabla
        for (Vacuna vacuna : historialVacunas) {
            tableModel.addRow(new Object[] {
                    vacuna.getFecha(),
                    vacuna.getNombre(),
                    vacuna.getDoctor(),
                    vacuna.getUbicacion(),
                    vacuna.getEstado()
            });
        }

        JTable historyTable = new JTable(tableModel);
        historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
        historyTable.setRowHeight(35);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        historyTable.getTableHeader().setBackground(LIGHT_GRAY);
        historyTable.getTableHeader().setPreferredSize(new Dimension(0, 40));

        // Renderizador personalizado para la columna de estado
        historyTable.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 14));

                if (value.equals("Completada")) {
                    label.setBackground(GREEN_LIGHT);
                    label.setForeground(GREEN_TEXT);
                } else {
                    label.setBackground(YELLOW_LIGHT);
                    label.setForeground(YELLOW_TEXT);
                }

                label.setBorder(new EmptyBorder(5, 10, 5, 10));

                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                }

                return label;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.setBorder(null);

        // Agregar componentes al panel de historial
        historyPanel.add(titlePanel, BorderLayout.NORTH);
        historyPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void setupFooterPanel() {
        footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(WHITE_COLOR);
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, LIGHT_GRAY));

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(PRIMARY_COLOR);
        logoutButton.setForeground(WHITE_COLOR);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setPreferredSize(new Dimension(150, 35));

        logoutButton.addActionListener(e -> {
            dispose();
            new AuthApp().setVisible(true);
        });

        footerPanel.add(logoutButton);
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
                    EstudiantePanel frame = new EstudiantePanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /// Cuando haya base de datos esto deberia borrarse
    private class Estudiante {
        private String id;
        private String nombre;
        private String grado;
        private String estado;

        public Estudiante(String id, String nombre, String grado, String estado) {
            this.id = id;
            this.nombre = nombre;
            this.grado = grado;
            this.estado = estado;
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

        public String getEstado() {
            return estado;
        }
    }

    // Clase para representar una vacuna
    private class Vacuna {
        private String fecha;
        private String nombre;
        private String doctor;
        private String ubicacion;
        private String estado;

        public Vacuna(String fecha, String nombre, String doctor, String ubicacion, String estado) {
            this.fecha = fecha;
            this.nombre = nombre;
            this.doctor = doctor;
            this.ubicacion = ubicacion;
            this.estado = estado;
        }

        public String getFecha() {
            return fecha;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDoctor() {
            return doctor;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public String getEstado() {
            return estado;
        }
    }

}
