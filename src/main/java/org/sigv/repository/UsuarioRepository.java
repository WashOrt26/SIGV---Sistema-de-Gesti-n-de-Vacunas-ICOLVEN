package org.sigv.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.sigv.config.HibernateUtil;
import org.sigv.model.TipoUsuario;
import org.sigv.model.Usuario;

import java.util.List;

public class UsuarioRepository {

    // Método para guardar un usuario en la BD
    public void guardarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
            System.out.println("Usuario guardado exitosamente.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Método para consultar si un usuario existe
    public Usuario buscarUsuario(String usuario, String contrasena) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = :contrasena";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", contrasena);

            return query.uniqueResult(); // Devuelve el usuario o null si no existe
        }
    }
    // Método para consultar si un usuario existe
    public List<Usuario> listarEstudiantes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Usuario u WHERE u.tipoUsuario = :tipo";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("tipo", TipoUsuario.estudiante);

            return query.getResultList(); // Devuelve el usuario o null si no existe
        }
    }
}