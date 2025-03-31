package org.sigv.frontend;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sigv.config.HibernateUtil;
import org.sigv.model.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    // Datos del estudiante
    private Usuario estudiante;
    private String grado;
    private String estado;

    public EstudiantePanel() {
        // Obtener datos del estudiante desde la base de datos
        setupStudentData();

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

    private void setupStudentData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtener el usuario actual (estudiante)
            String hql = "FROM Usuario u WHERE u.tipoUsuario = 'estudiante'";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            List<Usuario> estudiantes = query.getResultList();
            
            if (!estudiantes.isEmpty()) {
                estudiante = estudiantes.get(0); // Tomar el primer estudiante por ahora
                
                // Obtener información adicional del estudiante
                String sql = "SELECT e.grado, e.estado_estudiante FROM estudiante e WHERE e.correo = :correo";
                Query<Object[]> queryEstudiante = session.createNativeQuery(sql);
                queryEstudiante.setParameter("correo", estudiante.getUsuario());
                Object[] result = queryEstudiante.uniqueResult();
                
                if (result != null) {
                    grado = (String) result[0];
                    estado = (String) result[1];
                } else {
                    grado = "No especificado";
                    estado = "Pendiente";
                }
            } else {
                throw new RuntimeException("No se encontró información del estudiante");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener datos del estudiante: " + e.getMessage());
        }
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
        JPanel idPanel = createInfoField("Usuario", estudiante.getUsuario());

        // Estado de vacunación
        JPanel statusPanel = new JPanel(new BorderLayout(0, 5));
        statusPanel.setBackground(WHITE_COLOR);

        JLabel statusLabel = new JLabel("Estado de Vacunación");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.GRAY);

        JPanel statusValuePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusValuePanel.setBackground(WHITE_COLOR);

        JLabel statusValue = new JLabel(estado);
        statusValue.setFont(new Font("Arial", Font.BOLD, 16));
        statusValue.setOpaque(true);

        if (estado.equals("al dia")) {
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

        // Obtener la última vacuna aplicada
        String ultimaVacuna = "No hay vacunas registradas";
        String fechaUltimaVacuna = "";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "SELECT v.nombre_vacuna, v.fecha_uso " +
                        "FROM vacunas v " +
                        "WHERE v.correo_estudiante = :correo " +
                        "ORDER BY v.fecha_uso DESC " +
                        "LIMIT 1";
            Query<Object[]> query = session.createNativeQuery(sql);
            query.setParameter("correo", estudiante.getUsuario());
            Object[] result = query.uniqueResult();
            
            if (result != null) {
                ultimaVacuna = (String) result[0];
                fechaUltimaVacuna = result[1].toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Alerta de última vacuna
        JPanel lastVaccineAlert = createAlert(
                "Última Vacuna Aplicada",
                ultimaVacuna + " - Aplicada el " + fechaUltimaVacuna,
                GREEN_LIGHT,
                GREEN_TEXT
        );

        // Alerta de estado de vacunación
        JPanel statusAlert = createAlert(
                "Estado de Vacunación",
                estado.equals("al dia") ? "Al día con todas las vacunas" : "Pendiente de vacunas",
                estado.equals("al dia") ? GREEN_LIGHT : YELLOW_LIGHT,
                estado.equals("al dia") ? GREEN_TEXT : YELLOW_TEXT
        );

        alertsPanel.add(lastVaccineAlert);
        alertsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        alertsPanel.add(statusAlert);
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
        String[] columns = {"Fecha", "Vacuna", "Dosis", "Fabricante", "Vía de Administración"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Obtener historial de vacunas desde la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "SELECT v.fecha_uso, v.nombre_vacuna, v.dosis, v.fabricante, v.via_administracion " +
                        "FROM vacunas v " +
                        "WHERE v.correo_estudiante = :correo " +
                        "ORDER BY v.fecha_uso DESC";
            Query<Object[]> query = session.createNativeQuery(sql);
            query.setParameter("correo", estudiante.getUsuario());
            List<Object[]> resultados = query.getResultList();

            for (Object[] row : resultados) {
                tableModel.addRow(new Object[] {
                    row[0], // fecha_uso
                    row[1], // nombre_vacuna
                    row[2], // dosis
                    row[3], // fabricante
                    row[4]  // via_administracion
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar el historial de vacunas: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }

        JTable historyTable = new JTable(tableModel);
        historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
        historyTable.setRowHeight(35);
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        historyTable.getTableHeader().setBackground(LIGHT_GRAY);
        historyTable.getTableHeader().setPreferredSize(new Dimension(0, 40));

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
