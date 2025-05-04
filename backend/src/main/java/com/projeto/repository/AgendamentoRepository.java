package com.projeto.repository;

import com.projeto.model.Agendamento;
import com.projeto.model.Medico;
import com.projeto.model.Paciente;
import com.projeto.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepository implements BaseRepository<Agendamento, Long>{

    @Override
    public Agendamento insert(Agendamento agendamento) {

        String query = "INSERT INTO agendamento (paciente_id, medico_id, data_consulta, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBaseConnection.getConnection()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
               preparedStatement.setLong(1, agendamento.getPaciente().getIdPaciente());
               preparedStatement.setLong(2,agendamento.getMedico().getIdMedico());
               preparedStatement.setDate(3, Date.valueOf(agendamento.getDataConsulta()));
               preparedStatement.setString(4, agendamento.getStatus());
               preparedStatement.executeUpdate();
               System.out.println("Agendamento inserido com sucesso.");

               try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        agendamento.setIdAgendamento(generatedKeys.getLong(1));
                    }
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Agendamento: " + e.getMessage());
        }
        return agendamento;
    }



    @Override
    public Agendamento findById(Long id) {
    	String sql = "SELECT * FROM agendamento WHERE id = ?";

        Agendamento agendamento = new Agendamento();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,id );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
				agendamento = new Agendamento();

            	agendamento.setIdAgendamento(rs.getLong("id"));

				Medico medico = new Medico();
				medico.setIdMedico(rs.getLong("medico_id"));
            	agendamento.setMedico(medico);

				Paciente paciente = new Paciente();
				paciente.setIdPaciente(rs.getLong("paciente_id"));
            	agendamento.setPaciente(paciente);

            	agendamento.setDataConsulta(rs.getDate("data_consulta").toLocalDate());
            	agendamento.setStatus(rs.getString("status"));
                return agendamento;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento: " + e.getMessage());

        }
        return agendamento;
    }

    @Override
    public List<Agendamento> findAll() {
    	 String sql = "SELECT * FROM agendamento";
	        Agendamento agendamento;

	        List<Agendamento> agendamentos = new ArrayList<>();

	        try (Connection connection = DataBaseConnection.getConnection()) {
                assert connection != null;
                try (PreparedStatement stmt = connection.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        agendamento = new Agendamento();

                        agendamento.setIdAgendamento(rs.getLong("id"));

                        Medico medico = new Medico();
                        medico.setIdMedico(rs.getLong("medico_id"));
                        agendamento.setMedico(medico);

                        Paciente paciente = new Paciente();
                        paciente.setIdPaciente(rs.getLong("paciente_id"));
                        agendamento.setPaciente(paciente);

                        agendamento.setDataConsulta(rs.getDate("data_consulta").toLocalDate());
                        agendamento.setStatus(rs.getString("status"));
                        agendamentos.add(agendamento);
                    }

                }
            } catch (SQLException e) {
	            System.err.println("Erro ao listar agendamentos: " + e.getMessage());
	        }
	        return agendamentos;
    }

    @Override
    public void update(Agendamento agendamento) {
    	 StringBuilder queryBuilder = new StringBuilder("UPDATE agendamento SET ");
	        boolean adicionouCampo = false;

			if(agendamento.getPaciente() != null){
				if (agendamento.getPaciente().getIdPaciente() != null) {
					queryBuilder.append("paciente_id = ?");
					adicionouCampo = true;
				}
			}

			if(agendamento.getMedico() != null){
				if (agendamento.getMedico().getIdMedico() != null) {
					if (adicionouCampo) queryBuilder.append(", ");
					queryBuilder.append("medico_id = ?");
					adicionouCampo = true;
				}
			}


	        if (agendamento.getDataConsulta() != null) {
	            if (adicionouCampo) queryBuilder.append(", ");
	            queryBuilder.append("data_consulta = ?");
	            adicionouCampo = true;
	        }

	        if(agendamento.getStatus() != null){
	            if(adicionouCampo)queryBuilder.append(",");
	            queryBuilder.append("status = ?");
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

				if(agendamento.getPaciente() != null){
					if (agendamento.getPaciente().getIdPaciente() != null) {
						preparedStatement.setLong(index++, agendamento.getPaciente().getIdPaciente());
					}
				}

				if(agendamento.getMedico() != null){
					if (agendamento.getMedico().getIdMedico() != null) {
						preparedStatement.setLong(index++, agendamento.getMedico().getIdMedico());
					}
				}

	            if (agendamento.getDataConsulta() != null) {
	                preparedStatement.setDate(index++, Date.valueOf(agendamento.getDataConsulta()));
	            }
	            if (agendamento.getStatus() != null) {
	                preparedStatement.setString(index++, agendamento.getStatus());
	            }


	            preparedStatement.setLong(index, agendamento.getIdAgendamento());
	            preparedStatement.executeUpdate();

	            System.out.println("Agendamento atualizado com sucesso.");

	        } catch (SQLException e) {
	            System.err.println("Erro ao atualizar agendamentos: " + e.getMessage());
	        }
    }

    @Override
    public void delete(Long id) {
    	 String sql = "DELETE FROM agendamento WHERE id = ?";

	        try (Connection connection = DataBaseConnection.getConnection()) {
                assert connection != null;
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setLong(1, id);

                    stmt.executeUpdate();
                    System.out.println("Agendamento deletado com sucesso.");

                }
            } catch (SQLException e) {
	            System.err.println("Erro ao deletar agendamento: " + e.getMessage());
	        }
	}
    }

