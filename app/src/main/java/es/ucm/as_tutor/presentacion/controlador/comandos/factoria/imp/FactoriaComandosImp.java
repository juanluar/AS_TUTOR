package es.ucm.as_tutor.presentacion.controlador.comandos.factoria.imp;


import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.ConsultarRetoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.CrearRetoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ConsultarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.CrearRetosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.CrearUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.EditarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.EliminarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ListadoUsuariosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.CrearUsuariosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.ConsultarTareasComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.CrearTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.DeshabilitarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EditarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EliminarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor.ConsultarTutorComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor.CrearTutorComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor.EditarTutorComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ConsultarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.AnyadirUsuariosEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ConsultarEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ConsultarUsuariosEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.CrearEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.CrearEventoConsultarUsuarios;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.EliminarEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.GuardarEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ListadoEventoComando;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class FactoriaComandosImp extends FactoriaComandos {
    @Override
    public Command getCommand(String comando) {
        Command ret = null;
        switch(comando){
            case ListaComandos.CREAR_TAREA:
                ret = new CrearTareaComando();
                break;
            case ListaComandos.EDITAR_TAREA:
                ret = new EditarTareaComando();
                break;
            case ListaComandos.ELIMINAR_TAREA:
                ret = new EliminarTareaComando();
                break;
            case ListaComandos.DESHABILITAR_TAREA:
                ret = new DeshabilitarTareaComando();
                break;
            case ListaComandos.CONSULTAR_TAREAS:
                ret = new ConsultarTareasComando();
                break;
            case ListaComandos.CREAR_USUARIOS:
                ret = new CrearUsuariosComando();
                break;
            case ListaComandos.CREAR_RETOS:
                ret = new CrearRetosComando();
                break;
            case ListaComandos.CREAR_USUARIO:
                ret = new CrearUsuarioComando();
                break;
            case ListaComandos.LISTADO_USUARIOS:
                ret = new ListadoUsuariosComando();
                break;
            case ListaComandos.CONSULTAR_USUARIO:
                ret = new ConsultarUsuarioComando();
                break;
            case ListaComandos.ELIMINAR_USUARIO:
                ret = new EliminarUsuarioComando();
                break;
            case ListaComandos.EDITAR_USUARIO:
                ret = new EditarUsuarioComando();
                break;
            case ListaComandos.CREAR_RETO:
                ret = new CrearRetoComando();
                break;
            case ListaComandos.CONSULTAR_RETO:
                ret = new ConsultarRetoComando();
                break;
            case ListaComandos.CONSULTAR_TUTOR:
                ret = new ConsultarTutorComando();
                break;
            case ListaComandos.CREAR_TUTOR:
                ret = new CrearTutorComando();
                break;
            case ListaComandos.EDITAR_TUTOR:
                ret = new EditarTutorComando();
                break;
            //Evento
            case ListaComandos.CREAR_EVENTO:
                ret = new CrearEventoComando();
                break;
            case ListaComandos.ELIMINAR_EVENTO:
                ret= new EliminarEventoComando();
                break;
            case ListaComandos.CONSULTAR_EVENTO:
                ret= new ConsultarEvento();
                break;
            case ListaComandos.GUARDAR_EVENTO:
                ret=new GuardarEventoComando();
                break;
            case ListaComandos.LISTADO_EVENTOS:
                ret= new ListadoEventoComando();
                break;
            case ListaComandos.CONSUTAR_USUARIOS_EVENTO:
                ret= new ConsultarUsuariosEvento();
                break;
            case ListaComandos.CREAR_EVENTO_CONSULTAR_USUARIOS:
                ret= new CrearEventoConsultarUsuarios();
                break;
            case ListaComandos.ANYADIR_USUARIOS_EVENTO:
                ret= new AnyadirUsuariosEvento();
                break;
            //Fin evento
        }

        return ret;
    }
}
