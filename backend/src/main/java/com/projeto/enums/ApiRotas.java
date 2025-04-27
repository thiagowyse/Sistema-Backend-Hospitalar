package com.projeto.enums;


public enum ApiRotas {


    //Rotas Usuario
    USUARIO("/usuario"),
    USUARIO_AUTHENTICATOR("/usuario/authenticator"),
    USUARIO_CREATE("/usuario/create"),
    USUARIO_FIND_ALL("/usuario/findAll"),
    USUARIO_UPDATE("/usuario/update"),
    USUARIO_DELETE("usuario/delete"),

    //Rotas Paciente
    PACIENTE("/paciente"),
    PACIENTE_CREATE("/paciente/create"),
    PACIENTE_FIND_ALL("/paciente/findAll"),
    PACIENTE_UPDATE("/paciente/update"),
    PACIENTE_DELETE("/paciente/delete"),


    //Rotas Recepcionista
    RECEPCIONISTA("/recepcionista"),
    RECEPCIONISTA_CREATE("/recepcionista/create"),
    RECEPCIONISTA_FIND_ALL("/recepcionista/findAll"),
    RECEPCIONISTA_UPDATE("/recepcionista/update"),
    RECEPCIONISTA_DELETE("/recepcionista/delete"),


    //Rotas Medico
    MEDICO("/medico"),
    MEDICO_CREATE("/medico/create"),
    MEDICO_FIND_ALL("/medico/findAll"),
    MEDICO_UPDATE("/medico/update"),
    MEDICO_DELETE("/medico/delete"),

    //Rotas Prontuario
    PRONTUARIO("/prontuario"),
    PRONTUARIO_CREATE("/prontuario/create"),
    PRONTUARIO_FIND_ALL("/prontuario/findAll"),
    PRONTUARIO_UPDATE("/prontuario/update"),
    PRONTUARIO_DELETE("/prontuario/delete"),


    //Rotas Perfil
    PERFIL("/perfil"),
    PERFIL_CREATE("/perfil/create"),
    PERFIL_FIND_ALL("/perfil/findAll"),
    PERFIL_UPDATE("/perfil/update"),
    PERFIL_DELETE("/perfil/delete"),


    //Rotas Atendimento
    ATENDIMENTO("/atendimento"),
    ATENDIMENTO_CREATE("/atendimento/create"),
    ATENDIMENTO_FIND_ALL("/atendimento/findAll"),
    ATENDIMENTO_UPDATE("/atendimento/update"),
    ATENDIMENTO_DELETE("/atendimento/delete"),


    //Rotas LogUsuario
    LOG_USUARIO("/logUsuario"),
    LOG_USUARIO_CREATE("/logUsuario/create"),
    LOG_USUARIO_FIND_ALL("/logUsuario/findAll"),
    LOG_USUARIO_UPDATE("/logUsuario/update"),
    LOG_USUARIO_DELETE("/logUsuario/delete"),


    //Rotas Declaracao
    DECLARACAO("/declaracao"),
    DECLARACAO_CREATE("/declaracao/"),
    DECLARACAO_FIND_ALL("/declaracao/findAll"),
    DECLARACAO_UPDATE("/declaracao/update"),
    DECLARACAO_DELETE("/declaracao/delete"),


    //Rotas Exame
    EXAME("/exame"),
    EXAME_CREATE("/exame/create"),
    EXAME_FIND_ALL("/exame/findAll"),
    EXAME_UPDATE("/exame/update"),
    EXAME_DELETE("/exame/delete");


    private final String path;

    ApiRotas(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
