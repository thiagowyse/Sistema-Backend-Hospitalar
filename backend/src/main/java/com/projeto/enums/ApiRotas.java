package com.projeto.enums;


public enum ApiRotas {

    //Rotas Agendamento
    AGENDAMENTO("/agendamento"),
    AGENDAMENTO_CREATE("/agendamento/create"),
    AGENDAMENTO_FIND_BY_ID("/agendamento/findById"),
    AGENDAMENTO_FIND_BY_FILTER("/agendamento/findByFilter"),
    AGENDAMENTO_FIND_ALL("/agendamento/findAll"),
    AGENDAMENTO_UPDATE("/agendamento/update"),
    AGENDAMENTO_DELETE("/agendamento/delete"),

    //Rotas Atestado
    ATESTADO("/atestado"),
    ATESTADO_CREATE("/atestado/create"),
    ATESTADO_FIND_BY_ID("/atestado/findById"),
    ATESTADO_FIND_BY_FILTER("/atestado/findByFilter"),
    ATESTADO_FIND_ALL("/atestado/findAll"),
    ATESTADO_UPDATE("/atestado/update"),
    ATESTADO_DELETE("/atestado/delete"),

    //Rotas Declaracao
    DECLARACAO("/declaracao"),
    DECLARACAO_CREATE("/declaracao/create"),
    DECLARACAO_FIND_BY_ID("/declaracao/findById"),
    DECLARACAO_FIND_BY_FILTER("/declaracao/findByFilter"),
    DECLARACAO_FIND_ALL("/declaracao/findAll"),
    DECLARACAO_UPDATE("/declaracao/update"),
    DECLARACAO_DELETE("/declaracao/delete"),

    //Rotas Enfermeiro
    ENFERMEIRO("/enfermeiro"),
    ENFERMEIRO_CREATE("/enfermeiro/create"),
    ENFERMEIRO_FIND_BY_ID("/enfermeiro/findById"),
    ENFERMEIRO_FIND_BY_FILTER("/enfermeiro/findByFilter"),
    ENFERMEIRO_FIND_ALL("/enfermeiro/findAll"),
    ENFERMEIRO_UPDATE("/enfermeiro/update"),
    ENFERMEIRO_DELETE("/enfermeiro/delete"),

    //Rotas Especialidade
    ESPECIALIDADE("/especialidade"),
    ESPECIALIDADE_CREATE("/especialidade/create"),
    ESPECIALIDADE_FIND_BY_ID("/especialidade/findById"),
    ESPECIALIDADE_FIND_BY_FILTER("/especialidade/findByFilter"),
    ESPECIALIDADE_FIND_ALL("/especialidade/findAll"),
    ESPECIALIDADE_UPDATE("/especialidade/update"),
    ESPECIALIDADE_DELETE("/especialidade/delete"),

    //Rotas Exame
    EXAME("/exame"),
    EXAME_CREATE("/exame/create"),
    EXAME_FIND_BY_ID("/exame/findById"),
    EXAME_FIND_BY_FILTER("/exame/findByFilter"),
    EXAME_FIND_ALL("/exame/findAll"),
    EXAME_UPDATE("/exame/update"),
    EXAME_DELETE("/exame/delete"),


    //Rotas Historico Atendimento
    HISTORICO_ATENDIMENTO("/historicoAtendimento"),
    HISTORICO_ATENDIMENTO_CREATE("/historicoAtendimento/create"),
    HISTORICO_ATENDIMENTO_FIND_BY_ID("/historicoAtendimento/findById"),
    HISTORICO_ATENDIMENTO_FIND_BY_FILTER("/historicoAtendimento/findByFilter"),
    HISTORICO_ATENDIMENTO_FIND_ALL("/historicoAtendimento/findAll"),
    HISTORICO_ATENDIMENTO_UPDATE("/historicoAtendimento/update"),
    HISTORICO_ATENDIMENTO_DELETE("/historicoAtendimento/delete"),

    //Rotas LogUsuario
    LOG_USUARIO("/logUsuario"),
    LOG_USUARIO_CREATE("/logUsuario/create"),
    LOG_USUARIO_FIND_BY_ID("/logUsuario/findById"),
    LOG_USUARIO_FIND_BY_FILTER("/logUsuario/findByFilter"),
    LOG_USUARIO_FIND_ALL("/logUsuario/findAll"),
    LOG_USUARIO_UPDATE("/logUsuario/update"),
    LOG_USUARIO_DELETE("/logUsuario/delete"),

    //Rotas Medicamento
    MEDICAMENTO("/medicamento"),
    MEDICAMENTO_CREATE("/medicamento/create"),
    MECDICAMENTO_FIND_BY_ID("/medicamento/findById"),
    MEDICAMENTO_FIND_BY_FILTER("/medicamento/findByFilter"),
    MEDICAMENTO_FIND_ALL("/medicamento/findAll"),
    MEDICAMENTO_UPDATE("/medicamento/update"),
    MEDICAMENTO_DELETE("/medicamento/delete"),

    //Rotas Medico
    MEDICO("/medico"),
    MEDICO_CREATE("/medico/create"),
    MEDICO_FIND_BY_ID("/medico/findById"),
    MEDICO_FIND_BY_FILTER("/medico/findByFilter"),
    MEDICO_FIND_ALL("/medico/findAll"),
    MEDICO_UPDATE("/medico/update"),
    MEDICO_DELETE("/medico/delete"),


    //Rotas Paciente
    PACIENTE("/paciente"),
    PACIENTE_CREATE("/paciente/create"),
    PACIENTE_FIND_BY_ID("/paciente/findById"),
    PACIENTE_FIND_BY_FILTER("/paciente/findByFilter"),
    PACIENTE_FIND_ALL("/paciente/findAll"),
    PACIENTE_UPDATE("/paciente/update"),
    PACIENTE_DELETE("/paciente/delete"),

    //Rotas Perfil
    PERFIL("/perfil"),
    PERFIL_CREATE("/perfil/create"),
    PERFIL_FIND_BY_ID("/perfil/findById"),
    PERFIL_FIND_BY_FILTER("/perfil/findByFilter"),
    PERFIL_FIND_ALL("/perfil/findAll"),
    PERFIL_UPDATE("/perfil/update"),
    PERFIL_DELETE("/perfil/delete"),


    //Rotas Prontuario
    PRONTUARIO("/prontuario"),
    PRONTUARIO_CREATE("/prontuario/create"),
    PRONTUARIO_FIND_BY_ID("/prontuario/findById"),
    PRONTUARIO_FIND_BY_FILTER("/prontuario/findByFilter"),
    PRONTUARIO_FIND_ALL("/prontuario/findAll"),
    PRONTUARIO_UPDATE("/prontuario/update"),
    PRONTUARIO_DELETE("/prontuario/delete"),


    //Rotas Receita
    RECEITA("/receita"),
    RECEITA_CREATE("/receita/create"),
    RECEITA_FIND_BY_ID("/receita/findById"),
    RECEITA_FIND_BY_FILTER("/receita/findByFilter"),
    RECEITA_FIND_ALL("/receita/findAll"),
    RECEITA_UPDATE("/receita/update"),
    RECEITA_DELETE("/receita/delete"),

    //Rotas Receita Medicamento
    RECEITA_MEDICAMENTO("/receitaMedicamento"),
    RECEITA_MEDICAMENTO_CREATE("/receitaMedicamento/create"),
    RECEITA_MEDICAMENTO_FIND_BY_ID("/receitaMedicamento/findById"),
    RECEITA_MEDICAMENTO_FIND_BY_FILTER("/receitaMedicamento/findByFilter"),
    RECEITA_MEDICAMENTO_FIND_ALL("/receitaMedicamento/findAll"),
    RECEITA_MEDICAMENTO_UPDATE("/receitaMedicamento/update"),
    RECEITA_MEDICAMENTO_DELETE("/receitaMedicamento/delete"),


    //Rotas Recepcionista
    RECEPCIONISTA("/recepcionista"),
    RECEPCIONISTA_CREATE("/recepcionista/create"),
    RECEPCIONISTA_FIND_ALL("/recepcionista/findAll"),
    RECEPCIONISTA_FIND_BY_ID("/recepcionista/findById"),
    RECEPCIONISTA_FIND_BY_FILTER("/recepcionista/findByFilter"),
    RECEPCIONISTA_UPDATE("/recepcionista/update"),
    RECEPCIONISTA_DELETE("/recepcionista/delete"),


    //Rotas Usuario
    USUARIO("/usuario"),
    USUARIO_AUTHENTICATOR("/usuario/authenticator"),
    USUARIO_CREATE("/usuario/create"),
    USUARIO_FIND_BY_ID("/usuario/findById"),
    USUARIO_FIND_BY_FILTER("/usuario/findByFilter"),
    USUARIO_FIND_ALL("/usuario/findAll"),
    USUARIO_UPDATE("/usuario/update"),
    USUARIO_DELETE("usuario/delete");


    private final String path;

    ApiRotas(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
