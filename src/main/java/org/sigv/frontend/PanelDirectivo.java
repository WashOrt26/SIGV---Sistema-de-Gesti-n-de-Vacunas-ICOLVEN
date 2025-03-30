package org.sigv.frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ListSelectionModel;
import java.awt.CardLayout;

public class PanelDirectivo extends JFrame {

    public PanelDirectivo() {
        setTitle("Panel Directivo - Sistema de Vacunación");
        setBounds(100, 100, 1600, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1280, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));
        
        // Panel principal con borde
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel superior con título
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Panel de directiva");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        // Botón Generar Reporte
        JButton btnGenerarReporte = new JButton("Generar reporte") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 35);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnGenerarReporte.setFont(new Font("Arial", Font.BOLD, 14));
        btnGenerarReporte.setBackground(Color.WHITE);
        btnGenerarReporte.setForeground(Color.BLACK);
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.setBorderPainted(false);
        btnGenerarReporte.setPreferredSize(new Dimension(150, 40));
        titlePanel.add(btnGenerarReporte, BorderLayout.EAST);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Panel para el contenido scrolleable
        JPanel scrollableContent = new JPanel();
        scrollableContent.setLayout(new BorderLayout());
        scrollableContent.setOpaque(false);

        // Panel para los bloques informativos
        JPanel blocksPanel = new JPanel();
        blocksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        blocksPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Crear los tres bloques informativos
        for (int i = 0; i < 3; i++) {
            JPanel block = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Dibujar sombra
                    g2.setColor(new Color(0, 0, 0, 30));
                    g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 15, 15);
                    
                    // Dibujar panel principal
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth()-4, getHeight()-4, 15, 15);
                    
                    super.paintComponent(g);
                    g2.dispose();
                }
            };
            block.setPreferredSize(new Dimension(400, 150));
            block.setBackground(Color.WHITE);
            block.setLayout(new BorderLayout());
            block.setOpaque(false);
            
            JLabel blockTitle = new JLabel("Bloque " + (i + 1), SwingConstants.CENTER);
            blockTitle.setFont(new Font("Arial", Font.BOLD, 18));
            blockTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            block.add(blockTitle, BorderLayout.NORTH);
            
            blocksPanel.add(block);
        }

        scrollableContent.add(blocksPanel, BorderLayout.NORTH);

        // Panel para los selectores de pestañas
        JPanel tabSelectorsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dibujar sombra
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 15, 15);
                
                // Dibujar panel principal
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-4, getHeight()-4, 15, 15);
                
                super.paintComponent(g);
                g2.dispose();
            }
        };
        tabSelectorsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        tabSelectorsPanel.setBackground(new Color(245, 245, 245));
        tabSelectorsPanel.setOpaque(false);
        tabSelectorsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Botones de selección de pestañas
        JButton btnEstudiantes = new JButton("Estudiantes") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 35);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnEstudiantes.setFont(new Font("Arial", Font.BOLD, 14));
        btnEstudiantes.setBackground(Color.WHITE);
        btnEstudiantes.setForeground(Color.BLACK);
        btnEstudiantes.setFocusPainted(false);
        btnEstudiantes.setBorderPainted(false);
        btnEstudiantes.setPreferredSize(new Dimension(150, 40));
        btnEstudiantes.setMargin(new Insets(0, 0, 0, 0));

        JButton btnUsuarios = new JButton("Usuarios") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 35);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnUsuarios.setFont(new Font("Arial", Font.BOLD, 14));
        btnUsuarios.setBackground(new Color(230, 230, 230));
        btnUsuarios.setForeground(Color.BLACK);
        btnUsuarios.setFocusPainted(false);
        btnUsuarios.setBorderPainted(false);
        btnUsuarios.setPreferredSize(new Dimension(150, 40));
        btnUsuarios.setMargin(new Insets(0, 0, 0, 0));

        tabSelectorsPanel.add(btnEstudiantes);
        tabSelectorsPanel.add(btnUsuarios);

        // Panel para el contenido de las pestañas
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dibujar sombra
                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, 15, 15);
                
                // Dibujar panel principal
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-4, getHeight()-4, 15, 15);
                
                super.paintComponent(g);
                g2.dispose();
            }
        };
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para el contenido de estudiantes
        JPanel estudiantesPanel = new JPanel();
        estudiantesPanel.setLayout(new BorderLayout(0, 20));
        estudiantesPanel.setOpaque(false);

        // Panel para el formulario de registro
        JPanel registroPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        registroPanel.setLayout(new BorderLayout(0, 20));
        registroPanel.setOpaque(false);
        registroPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título del formulario
        JLabel formTitleLabel = new JLabel("Registrar nuevo estudiante");
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        formTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        registroPanel.add(formTitleLabel, BorderLayout.NORTH);

        // Panel del formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);

        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0; // Hacer que los componentes se expandan horizontalmente

        // Primera fila - Nombre completo (izquierda)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JLabel nombreLabel = new JLabel("Nombre completo");
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nombreLabel, gbc);

        gbc.gridy = 1;
        JTextField nombreField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        nombreField.setPreferredSize(new Dimension(400, 35));
        nombreField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nombreField, gbc);

        // Fecha de nacimiento (derecha)
        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel fechaLabel = new JLabel("Fecha de nacimiento");
        fechaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(fechaLabel, gbc);

        gbc.gridy = 1;
        JTextField fechaField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        fechaField.setPreferredSize(new Dimension(400, 35));
        fechaField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(fechaField, gbc);

        // Segunda fila - Grado (izquierda)
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel gradoLabel = new JLabel("Grado");
        gradoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(gradoLabel, gbc);

        gbc.gridy = 3;
        String[] grados = {"1°", "2°", "3°", "4°", "5°", "6°"};
        JComboBox<String> gradoCombo = new JComboBox<>(grados) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        gradoCombo.setPreferredSize(new Dimension(400, 35));
        gradoCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(gradoCombo, gbc);

        // ID de estudiante (derecha)
        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel idLabel = new JLabel("ID de estudiante");
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(idLabel, gbc);

        gbc.gridy = 3;
        JTextField idField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        idField.setPreferredSize(new Dimension(400, 35));
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(idField, gbc);

        // Tercera fila - Información médica (ocupa todo el ancho)
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel infoLabel = new JLabel("Información médica relevante");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(infoLabel, gbc);

        gbc.gridy = 5;
        JTextArea infoMedica = new JTextArea(1, 40) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        infoMedica.setLineWrap(true);
        infoMedica.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane infoScroll = new JScrollPane(infoMedica);
        infoScroll.setPreferredSize(new Dimension(820, 35));
        formPanel.add(infoScroll, gbc);

        // Botón de registro
        gbc.gridy = 6;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JButton btnRegistrar = new JButton("Registrar estudiante") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(0x45997E));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(40, 40));
        formPanel.add(btnRegistrar, gbc);

        // Agregar el formulario al panel de registro
        registroPanel.add(formPanel, BorderLayout.CENTER);

        // Panel para la tabla de estudiantes
        JPanel tablaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        tablaPanel.setLayout(new BorderLayout(0, 10));
        tablaPanel.setOpaque(false);
        tablaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título de la tabla
        JLabel tablaTitleLabel = new JLabel("Estudiantes registrados");
        tablaTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tablaTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        tablaPanel.add(tablaTitleLabel, BorderLayout.NORTH);

        // Crear el modelo de la tabla
        String[] columnNames = {"ID", "Nombre", "Grado", "Estado de vacunación"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        JTable tablaEstudiantes = new JTable(tableModel);
        
        // Configurar la apariencia de la tabla
        tablaEstudiantes.setFillsViewportHeight(true);
        tablaEstudiantes.setRowHeight(50);
        tablaEstudiantes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaEstudiantes.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEstudiantes.setShowGrid(false);
        tablaEstudiantes.setIntercellSpacing(new Dimension(20, 10));
        tablaEstudiantes.setBackground(Color.WHITE);
        tablaEstudiantes.setForeground(new Color(100, 100, 100));
        tablaEstudiantes.getTableHeader().setForeground(new Color(100, 100, 100));
        tablaEstudiantes.getTableHeader().setBackground(Color.WHITE);
        tablaEstudiantes.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        // Configurar el renderizado de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                if (isSelected) {
                    c.setBackground(new Color(245, 245, 245));
                }
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        for (int i = 0; i < tablaEstudiantes.getColumnCount(); i++) {
            tablaEstudiantes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Configurar el ancho de las columnas
        tablaEstudiantes.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
        tablaEstudiantes.getColumnModel().getColumn(1).setPreferredWidth(300); // Nombre
        tablaEstudiantes.getColumnModel().getColumn(2).setPreferredWidth(100); // Grado
        tablaEstudiantes.getColumnModel().getColumn(3).setPreferredWidth(150); // Estado

        // Agregar la tabla a un JScrollPane con bordes personalizados
        JScrollPane tableScrollPane = new JScrollPane(tablaEstudiantes);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        tableScrollPane.setPreferredSize(new Dimension(800, 200)); // Altura fija para la tabla
        
        tablaPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Agregar los paneles al panel de estudiantes
        estudiantesPanel.add(registroPanel, BorderLayout.NORTH);
        estudiantesPanel.add(tablaPanel, BorderLayout.CENTER);

        // Panel para el contenido de usuarios
        JPanel usuariosPanel = new JPanel();
        usuariosPanel.setLayout(new BorderLayout(0, 20));
        usuariosPanel.setOpaque(false);

        // Panel para el formulario de registro de usuarios
        JPanel registroUsuariosPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        registroUsuariosPanel.setLayout(new BorderLayout(0, 20));
        registroUsuariosPanel.setOpaque(false);
        registroUsuariosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título del formulario
        JLabel usuariosTitleLabel = new JLabel("Crear nuevo usuario");
        usuariosTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usuariosTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        registroUsuariosPanel.add(usuariosTitleLabel, BorderLayout.NORTH);

        // Panel del formulario de usuarios
        JPanel usuariosFormPanel = new JPanel();
        usuariosFormPanel.setLayout(new GridBagLayout());
        usuariosFormPanel.setOpaque(false);

        // Configuración de GridBagConstraints
        GridBagConstraints gbcUsuarios = new GridBagConstraints();
        gbcUsuarios.fill = GridBagConstraints.HORIZONTAL;
        gbcUsuarios.insets = new Insets(10, 10, 10, 10);
        gbcUsuarios.weightx = 1.0;

        // Primera fila - Nombre completo (izquierda)
        gbcUsuarios.gridx = 0;
        gbcUsuarios.gridy = 0;
        gbcUsuarios.gridwidth = 1;
        JLabel nombreUsuarioLabel = new JLabel("Nombre completo");
        nombreUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usuariosFormPanel.add(nombreUsuarioLabel, gbcUsuarios);

        gbcUsuarios.gridy = 1;
        JTextField nombreUsuarioField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        nombreUsuarioField.setPreferredSize(new Dimension(400, 35));
        nombreUsuarioField.setFont(new Font("Arial", Font.PLAIN, 14));
        usuariosFormPanel.add(nombreUsuarioField, gbcUsuarios);

        // Email (derecha)
        gbcUsuarios.gridx = 1;
        gbcUsuarios.gridy = 0;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usuariosFormPanel.add(emailLabel, gbcUsuarios);

        gbcUsuarios.gridy = 1;
        JTextField emailField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        emailField.setPreferredSize(new Dimension(400, 35));
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        usuariosFormPanel.add(emailField, gbcUsuarios);

        // Segunda fila - Rol (izquierda)
        gbcUsuarios.gridx = 0;
        gbcUsuarios.gridy = 2;
        JLabel rolLabel = new JLabel("Rol");
        rolLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usuariosFormPanel.add(rolLabel, gbcUsuarios);

        gbcUsuarios.gridy = 3;
        String[] roles = {"Padre", "Médico", "Directivo"};
        JComboBox<String> rolCombo = new JComboBox<>(roles) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        rolCombo.setPreferredSize(new Dimension(400, 35));
        rolCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        usuariosFormPanel.add(rolCombo, gbcUsuarios);

        // Estudiante asociado (derecha)
        gbcUsuarios.gridx = 1;
        gbcUsuarios.gridy = 2;
        JLabel estudianteLabel = new JLabel("Estudiante asociado");
        estudianteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usuariosFormPanel.add(estudianteLabel, gbcUsuarios);

        gbcUsuarios.gridy = 3;
        JTextField estudianteField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        estudianteField.setPreferredSize(new Dimension(400, 35));
        estudianteField.setFont(new Font("Arial", Font.PLAIN, 14));
        usuariosFormPanel.add(estudianteField, gbcUsuarios);

        // Botón de registro
        gbcUsuarios.gridy = 4;
        gbcUsuarios.insets = new Insets(20, 10, 10, 10);
        gbcUsuarios.gridwidth = 1;
        gbcUsuarios.gridx = 1;
        gbcUsuarios.anchor = GridBagConstraints.CENTER;
        
        JButton btnRegistrarUsuario = new JButton("Crear usuario") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnRegistrarUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarUsuario.setBackground(new Color(0x45997E));
        btnRegistrarUsuario.setForeground(Color.WHITE);
        btnRegistrarUsuario.setFocusPainted(false);
        btnRegistrarUsuario.setBorderPainted(false);
        btnRegistrarUsuario.setPreferredSize(new Dimension(40, 40));
        usuariosFormPanel.add(btnRegistrarUsuario, gbcUsuarios);

        // Agregar el formulario al panel de registro
        registroUsuariosPanel.add(usuariosFormPanel, BorderLayout.CENTER);

        // Panel para la tabla de usuarios
        JPanel tablaUsuariosPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        tablaUsuariosPanel.setLayout(new BorderLayout(0, 10));
        tablaUsuariosPanel.setOpaque(false);
        tablaUsuariosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título de la tabla
        JLabel tablaUsuariosTitleLabel = new JLabel("Usuarios registrados");
        tablaUsuariosTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tablaUsuariosTitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        tablaUsuariosPanel.add(tablaUsuariosTitleLabel, BorderLayout.NORTH);

        // Crear el modelo de la tabla de usuarios
        String[] columnasUsuarios = {"Nombre", "Email", "Rol", "Estado"};
        DefaultTableModel tableModelUsuarios = new DefaultTableModel(columnasUsuarios, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaUsuarios = new JTable(tableModelUsuarios);
        
        // Configurar la apariencia de la tabla
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setRowHeight(50);
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.setShowGrid(false);
        tablaUsuarios.setIntercellSpacing(new Dimension(20, 10));
        tablaUsuarios.setBackground(Color.WHITE);
        tablaUsuarios.setForeground(new Color(100, 100, 100));
        tablaUsuarios.getTableHeader().setForeground(new Color(100, 100, 100));
        tablaUsuarios.getTableHeader().setBackground(Color.WHITE);
        tablaUsuarios.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        // Configurar el renderizado de las celdas
        DefaultTableCellRenderer centerRendererUsuarios = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                if (isSelected) {
                    c.setBackground(new Color(245, 245, 245));
                }
                return c;
            }
        };
        centerRendererUsuarios.setHorizontalAlignment(JLabel.CENTER);
        centerRendererUsuarios.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        for (int i = 0; i < tablaUsuarios.getColumnCount(); i++) {
            tablaUsuarios.getColumnModel().getColumn(i).setCellRenderer(centerRendererUsuarios);
        }
        
        // Configurar el ancho de las columnas
        tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(300); // Nombre
        tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(300); // Email
        tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150); // Rol
        tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(100); // Estado

        // Agregar la tabla a un JScrollPane
        JScrollPane tableScrollPaneUsuarios = new JScrollPane(tablaUsuarios);
        tableScrollPaneUsuarios.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPaneUsuarios.getViewport().setBackground(Color.WHITE);
        tableScrollPaneUsuarios.setPreferredSize(new Dimension(800, 200));
        
        tablaUsuariosPanel.add(tableScrollPaneUsuarios, BorderLayout.CENTER);

        // Agregar los paneles al panel de usuarios
        usuariosPanel.add(registroUsuariosPanel, BorderLayout.NORTH);
        usuariosPanel.add(tablaUsuariosPanel, BorderLayout.CENTER);

        // Agregar los paneles al contentPanel
        contentPanel.setLayout(new CardLayout());
        contentPanel.add(estudiantesPanel, "estudiantes");
        contentPanel.add(usuariosPanel, "usuarios");

        // Configurar colores iniciales de los botones
        btnEstudiantes.setBackground(Color.WHITE);
        btnUsuarios.setBackground(new Color(230, 230, 230));

        // Asegurar que la pestaña de estudiantes esté visible al inicio
        contentPanel.revalidate();
        contentPanel.repaint();

        // Agregar listeners para los botones de pestañas
        btnEstudiantes.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "estudiantes");
            btnEstudiantes.setBackground(Color.WHITE);
            btnUsuarios.setBackground(new Color(230, 230, 230));
        });

        btnUsuarios.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "usuarios");
            btnUsuarios.setBackground(Color.WHITE);
            btnEstudiantes.setBackground(new Color(230, 230, 230));
        });

        // Panel para contener selectores y contenido
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(0, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        bottomPanel.add(tabSelectorsPanel, BorderLayout.NORTH);
        bottomPanel.add(contentPanel, BorderLayout.CENTER);

        // Agregar el bottomPanel al scrollableContent
        scrollableContent.add(bottomPanel, BorderLayout.CENTER);

        // Crear el JScrollPane para el contenido scrolleable
        JScrollPane scrollPane = new JScrollPane(scrollableContent);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        // Agregar el scrollPane al mainPanel
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PanelDirectivo::new);
    }
}