package io.github.santi12368;

import java.util.Random;

public class Juego {
    private String[] jugadores;
    private int[] puntos;
    private int jugadorActual;
    private Random random;
    private boolean terminado;
    private String ganador;

    // Constructor
    public Juego(String[] jugadores) {
        this.jugadores = jugadores;
        this.puntos = new int[jugadores.length];
        this.jugadorActual = 0;
        this.random = new Random();
        this.terminado = false;
        this.ganador = "";
    }

    // Lanza los dados
    public void lanzarDados() {
        // Genera un numero aleatorio entre 1 y 6 para cada dado
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int dado3 = random.nextInt(6) + 1;

        // Imprime el resultado de los dados con un delay de 500ms entre cada dado, para suspenso
        System.out.print(dado1 + "  ");
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(dado2 + "  ");
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(dado3);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (dado1 == dado2 && dado2 == dado3) { // Si los tres dados son iguales
            puntos[jugadorActual] += 1000;
            System.out.println("Los tres dados son iguales! Ganas 1000 puntos.");
        } else if (dado1 == dado2 || dado1 == dado3 || dado2 == dado3) { // Si dos dados son iguales
            puntos[jugadorActual] += 500;
            System.out.println("Dos dados son iguales! Ganas 500 puntos.");
        } else { // Si no hay dados iguales
            System.out.println("No hay dados iguales. No ganas puntos.");
        }

        if (puntos[jugadorActual] >= 3000) { // Si el jugador actual llega a 3000 puntos o mas, termina el juego
            terminado = true;
            ganador = jugadores[jugadorActual];
        }

        // Cambia al siguiente jugador. Si llega al final, vuelve al primer jugador
        jugadorActual = (jugadorActual + 1) % jugadores.length;
    }

    // Devuelve el nombre del jugador actual
    public String getJugadorActual() {
        return jugadores[jugadorActual];
    }

    // Devuelve los puntos del jugador actual
    public int getPuntosJugador() {
        int puntosJugador = (jugadorActual - 1 + jugadores.length) % jugadores.length;
        return puntos[puntosJugador];
    }

    // Devuelve los puntos de todos los jugadores
    public int getPuntosGrupo(String jugador) {
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].equals(jugador)) {
                return puntos[i];
            }
        }
        return 0;
    }

    // Verifica si el juego ha terminado
    public boolean haTerminado() {
        return terminado;
    }

    // Devuelve el nombre del ganador
    public String getGanador() {
        return ganador;
    }
}
