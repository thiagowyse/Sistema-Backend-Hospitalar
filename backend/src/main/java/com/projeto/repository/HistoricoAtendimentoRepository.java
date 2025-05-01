package com.projeto.repository;

import com.projeto.model.HistoricoAtendimento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoricoAtendimentoRepository implements BaseRepository<HistoricoAtendimento, Long> {

	@Override
	public HistoricoAtendimento insert(HistoricoAtendimento historicoAtendimento) {
		String sql = "INSERT INTO historico_atendimento (paciente_id,medico_id,descricao,data_atendimento) VALUES (?,?,?,?)";

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, historicoAtendimento.getPaciente().getIdPaciente());
			stmt.setLong(2, historicoAtendimento.getMedico().getIdMedico());
			stmt.setString(3, historicoAtendimento.getDescricao());
			stmt.setDate(4, historicoAtendimento.getDataAtendimento());

			stmt.executeUpdate();
			System.out.println("Historico de atendimento inserido com sucesso.");

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					historicoAtendimento.setIdHistoricoAtendimento(generatedKeys.getLong(1));
				}
			}

		} catch (SQLException e) {
			System.err.println("Erro ao inserir historico de atendimento: " + e.getMessage());
		}
		return historicoAtendimento;
	}

	@Override
	public HistoricoAtendimento findById(Long id) {
		String sql = "SELECT * FROM historico_atendimento WHERE id = ?";
		HistoricoAtendimento historicoAtendimento;

		try (Connection connection = DataBaseConnection.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				historicoAtendimento = new HistoricoAtendimento();
				historicoAtendimento.setIdHistoricoAtendimento(rs.getLong("id"));

				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getLong("paciente_id"));
				historicoAtendimento.setPaciente(paciente);

				Medico medico = new Medico();
				medico.setIdMedico(rs.getLong("medico_id"));
				historicoAtendimento.setMedico(medico);
				historicoAtendimento.setDescricao(rs.getString("descricao"));
				historicoAtendimento.setDataAtendimento(rs.getDate("data_atendimento"));
				return historicoAtendimento;
			}

		} catch (SQLException e) {
			System.err.println("Erro ao buscar historico de atendimento: " + e.getMessage());

		}
		return null;
	}

	@Override
	public List<HistoricoAtendimento> findAll() {
		String sql = "SELECT * FROM historico_atendimento";
		HistoricoAtendimento historicoAtendimento;
        List<HistoricoAtendimento> historicosAtendimento = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	historicoAtendimento = new HistoricoAtendimento();
            	historicoAtendimento.setIdHistoricoAtendimento(rs.getLong("id"));

				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getLong("paciente_id"));
				historicoAtendimento.setPaciente(paciente);

				Medico medico = new Medico();
				medico.setIdMedico(rs.getLong("medico_id"));
				historicoAtendimento.setMedico(medico);
				historicoAtendimento.setDescricao(rs.getString("descricao"));
				historicoAtendimento.setDataAtendimento(rs.getDate("data_atendimento"));
                historicosAtendimento.add(historicoAtendimento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar historicos de atendimento: " + e.getMessage());
        }
        return historicosAtendimento;
	}

	@Override
	public void update(HistoricoAtendimento historicoAtendimento) {
		StringBuilder queryBuilder = new StringBuilder("UPDATE historico_atendimento SET ");
        boolean adicionouCampo = false;

		if(historicoAtendimento.getPaciente() != null){
			if(historicoAtendimento.getPaciente().getIdPaciente() != null){
				queryBuilder.append("paciente_id = ?");
				adicionouCampo = true;
			}
		}

		if(historicoAtendimento.getMedico() != null){
			if(historicoAtendimento.getMedico().getIdMedico() != null){
				if (adicionouCampo) queryBuilder.append(", ");
				queryBuilder.append("medico_id = ?");
				adicionouCampo = true;
			}
		}

        if (historicoAtendimento.getDescricao() != null) {
            if (adicionouCampo) queryBuilder.append(", ");
            queryBuilder.append("descricao = ?");
            adicionouCampo = true;
        }

        if(historicoAtendimento.getDataAtendimento() != null){
            if(adicionouCampo)queryBuilder.append(",");
            queryBuilder.append("data_atendimento = ?");
            adicionouCampo = true;
        }

        queryBuilder.append(" WHERE id = ?");

        if (!adicionouCampo) {
            System.out.println("Nenhum campo para atualizar.");
            return;
        }

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {

            int index = 1;

			if(historicoAtendimento.getPaciente() != null){
				if(historicoAtendimento.getPaciente().getIdPaciente() != null){
					preparedStatement.setLong(index++, historicoAtendimento.getPaciente().getIdPaciente());
				}
			}

			if(historicoAtendimento.getMedico() != null){
				if(historicoAtendimento.getMedico().getIdMedico() != null){
					preparedStatement.setLong(index++, historicoAtendimento.getMedico().getIdMedico());
				}
			}

            if (historicoAtendimento.getDescricao() != null) {
                preparedStatement.setString(index++, historicoAtendimento.getDescricao());
            }
            if (historicoAtendimento.getDataAtendimento() != null) {
                preparedStatement.setDate(index++, historicoAtendimento.getDataAtendimento());
            }


            preparedStatement.setLong(index, historicoAtendimento.getIdHistoricoAtendimento());
            preparedStatement.executeUpdate();

            System.out.println("Historico de atendimento atualizado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar historico de atendimento: " + e.getMessage());
        }
	}

	@Override
	public void delete(Long id) {
		 String sql = "DELETE FROM historico_atendimento WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(sql)) {

	            stmt.setLong(1, id);

	            stmt.executeUpdate();
	            System.out.println("Historico de atendimento deletado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao deletar historico de atendimento: " + e.getMessage());
	        }
	}
}
