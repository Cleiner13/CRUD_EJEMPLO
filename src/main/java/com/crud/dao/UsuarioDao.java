package com.crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crud.modelo.Usuario;

public class UsuarioDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/crud";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String INSERT_USERS_SQL = "INSERT INTO usuarios" + "  (nombre, email) VALUES "
			+ " (?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,nombre,email from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from usuarios";
	private static final String DELETE_USERS_SQL = "delete from usuarios where id = ?;";
	private static final String UPDATE_USERS_SQL = "update usuarios set nombre = ?,email= ? where id = ?;";
	
	public UsuarioDao() {
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public void insertarUsuario (Usuario user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getEmail());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Usuario seleccionarUsuario(int id) {
		Usuario user = null;

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				user = new Usuario(id, nombre, email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<Usuario> seleccionarUsuarios() {

		List<Usuario> usuarios = new ArrayList<>();
		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				usuarios.add(new Usuario(id, nombre, email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuarios;
	}

	public boolean eliminarUsuario(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean actualizarUsuario(Usuario user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("Actualizar Usuario:"+statement);
			statement.setString(1, user.getNombre());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}