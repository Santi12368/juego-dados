package io.github.santi12368;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido.\n");
        System.out.println("En este juego, los jugadores lanzaran 3 dados.");
        System.out.println("Si 3 dados son iguales, el jugador gana 1000 puntos. Si 2 dados son iguales, el jugador gana 500 puntos.");
        System.out.println("El primer jugador en llegar a 5000 puntos gana.\n");

        // Pregunta por la cantidad de jugadores que va a tener el juego,
        int cantidadJugadores = 0;
        while (cantidadJugadores <= 1) {
            System.out.print("Cuantas personas van a jugar? (Min. 2 jugadores): ");
            try {
                cantidadJugadores = scanner.nextInt();
                if (cantidadJugadores <= 1) { // Si la cantidad de jugadores es menor o igual a 1, se vuelve a preguntar
                    System.out.println("Por favor, ingrese un numero de jugadores valido.");
                }
            } catch (InputMismatchException e) { // Si el input no es un numero, se vuelve a preguntar
                System.out.println("Por favor, ingrese un numero de jugadores valido.");
                scanner.next(); // Limpia el input invalido
            }
        }

        scanner.nextLine(); // Limpia el buffer del scanner

        // Pregunta por los nombres de los jugadores, los asigna al array jugadores
        System.out.println("Ingrese los nombres de los jugadores:");
        String[] jugadores = new String[cantidadJugadores];
        for (int i = 0; i < cantidadJugadores; i++) {
            System.out.print("Jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine().trim();

            // Si el nombre esta vacio, se vuelve a preguntar
            while (nombre.isEmpty()) {
                System.out.println("Por favor, ingrese un nombre valido.");
                System.out.print("Jugador " + (i + 1) + ": ");
                nombre = scanner.nextLine().trim();
            }
            jugadores[i] = nombre;
        }

        // Inicializa el juego
        Juego juego = new Juego(jugadores);

        // Mientras el juego no haya terminado, sigue jugando
        while (!juego.haTerminado()) {
            // Para circular entre los jugadores
            for (int i = 0; i < cantidadJugadores; i++) {
                System.out.println("\nTurno de " + juego.getJugadorActual() + ":");
                System.out.println("Presione enter para lanzar los dados.");
                scanner.nextLine();

                // Lanza los dados
                juego.lanzarDados();

                // Muestra los puntos del jugador actual
                System.out.println("Total de puntos: " + juego.getPuntosJugador());

                // Si es el último jugador, muestra los puntos de todos los jugadores
                if (i == cantidadJugadores - 1 && !juego.haTerminado()) {
                    try { // Los delay son para enfatizar los puntos de todos
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\nPuntos totales:");
                    for (int j = 0; j < cantidadJugadores; j++) {
                        System.out.printf("%s: %d\n", jugadores[j], juego.getPuntosGrupo(jugadores[j]));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Si el juego termina, muestra el ganador y sale del bucle, terminando el programa
                if (juego.haTerminado()) {
                    // Muestra los puntos de todos los jugadores
                    System.out.println("\nPuntos totales:");
                    for (int j = 0; j < cantidadJugadores; j++) {
                        System.out.printf("%s: %d\n", jugadores[j], juego.getPuntosGrupo(jugadores[j]));
                    }

                    System.out.println("\n" + juego.getGanador() + " ha ganado!");
                    break;
                }
            }
        }
    }
}