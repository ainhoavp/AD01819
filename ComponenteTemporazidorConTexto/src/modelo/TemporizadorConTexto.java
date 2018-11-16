/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;

/**
 *
 * @author Ainhoa
 */
public class TemporizadorConTexto extends JButton implements Serializable {

    private int segundos;
    private String texto;
    private Color color;
    private boolean decimales = false;
    private int segundosElegidos;
    private long segundosElegidosDecimal = segundos;
    

    CuentaAtrasFinalizadaListener listener;

    public TemporizadorConTexto() {
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDecimales() {
        return decimales;
    }

    public void setDecimales(boolean decimales) {
        this.decimales = decimales;
    }
    
    
    /**
     * Este método solo inicializa el listener igualando el que le pasas por
     * parámetro al que está instanciado en la clase. Esto lo hace para que, en
     * caso de que el usuario del componente quiera añadir una funcionalidad
     * cuando acabe la cuenta atrás pueda implemetar el método ejecutar() de la
     * interface CuentaAtrasFinalizadaListener que se llamará en el método
     * start() al finalizar la cuenta atrás.
     *
     * @param listener
     */
    public void addListener(CuentaAtrasFinalizadaListener listener) {
        this.listener = listener;
    }

    public void start(CuentaAtrasFinalizadaListener listener) {
        if(decimales){
            setText(Long.toString(segundosElegidosDecimal));
        }
        setText(Integer.toString(segundos));
        segundosElegidos = segundos;
        //Timer es una clase temporizador de Java.
        Timer temporizador = new Timer();

        //TimerTask es una clase que implementa el método run() y que realizará la tarea del temporizador.
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                if (segundosElegidos >= 0) {
                    setText(Integer.toString(segundosElegidos--));
                }else{
                    setText(texto);
                    setBackground(color);
                    cancel();
                    //TODO: aqui llamamos al interface y si el usuario quiere programarlo, es aquí donde añadirá el código.
                    if(listener != null){
                        listener.ejecutar();
                    }
                }
            }
        }, 0, 1000);
        //estos son el resto de parámetros del método schedule(TimerTask tareAprogramar, long retraso, long periodo)
        //periodo es el tiempo en milisegundos entre las ejecuciones de la tarea.
       
    }

}
