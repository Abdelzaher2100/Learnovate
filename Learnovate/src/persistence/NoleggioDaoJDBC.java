package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Noleggio;
import persistence.dao.NoleggioDao;
import persistence.dao.PrenotazioneDao;
import persistence.dao.VeicoloDao;

public class NoleggioDaoJDBC implements NoleggioDao{

	private DataSource dataSource;

	public NoleggioDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Noleggio noleggio) {
		Connection connection = this.dataSource.getConnection();
		try {
			int id = IdBroker.getId(connection);
			noleggio.setId(id);
			String insert = "insert into noleggio(id, prenotazione,veicolo,stato) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			
			statement.setInt(1, noleggio.getId());
			statement.setInt(2, noleggio.getPrenotazione().getIdPrenotazione());
			statement.setString(3, noleggio.getVeicolo().getTarga());
			statement.setString(4, noleggio.getStato());	
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Noleggio findByPrimaryKey(int id) {
		Connection connection = this.dataSource.getConnection();
		Noleggio noleggio = null;
		try {
			PreparedStatement statement;
			String query = "select * from noleggio where id = ?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				noleggio = new Noleggio();
				noleggio.setId(result.getInt("id"));
				noleggio.getPrenotazione().setIdPrenotazione(result.getInt("prenotazione"));
				noleggio.getVeicolo().setTarga(result.getString("targa"));	
				noleggio.setStato(result.getString("stato"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return noleggio;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Noleggio> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Noleggio noleggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Noleggio noleggio) {
		// TODO Auto-generated method stub
		
	}

	
	
}
