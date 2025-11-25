package inscaparrella.park1;

/*
* Authors:
* Cristian Camilo Cardenas Ramos
* Miguel Angel Sanchez Azuaje
*/

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AmusementParkTicketsMain {

    public static void main(String[] args) {

        final int MAX_ENTRADES = 1000;
        String[] entrades = new String[MAX_ENTRADES];
        int contadorEntrades = 0;

        Scanner scanner = new Scanner(System.in);
        int opcio = -1;

        double Preu_Base = 12.0;
        double Preu_VIP = 4.0;
        double Descompte_Infantil = 0.50;
        int Edat_Gratuita = 3;

        do {
            System.out.println("\n---- PARC D'ATRACCIONS DAMW ----");
            System.out.println("0. Sortir");
            System.out.println("1. Venda d'entrades");
            System.out.println("2. Us de l'entrada");
            System.out.println("3. Estadistiques del dia");
            System.out.print("Introdueix la teva opcio (0-3): ");

            if (scanner.hasNextInt()) {
                opcio = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcio = -1;
            }

            if (opcio == 1) {
                if (contadorEntrades < MAX_ENTRADES) {
                    System.out.println("--- 1. Venda d'entrades ---");

                    String nom = "";
                    String cognom1 = "";
                    String cognom2 = "";
                    int edat = -1;
                    String discapacitatStr = "";
                    String entradaVIPStr = "";
                    String targetaCredit = "";

                    String regexNom = "^[a-zA-ZàèéíòóúçÀÈÉÍÒÓÚÇ ]+$";
                    String regexSiNo = "^\\s*(?i)(s|n|s[íi]|n[oò])\\s*$";
                    String regexTargeta = "^\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";

                    System.out.println("--- Entrada de dades del visitant ---");

                    do {
                        System.out.print("Introdueix el Nom: ");
                        nom = scanner.nextLine().trim();
                        if (nom.isEmpty() || !nom.matches(regexNom)) {
                            System.out.println("Error: El nom no pot ser buit ni contenir numeros.");
                        }
                    } while (nom.isEmpty() || !nom.matches(regexNom));

                    do {
                        System.out.print("Introdueix el Primer Cognom: ");
                        cognom1 = scanner.nextLine().trim();
                        if (cognom1.isEmpty() || !cognom1.matches(regexNom)) {
                            System.out.println("Error: El primer cognom no pot ser buit ni contenir numeros.");
                        }
                    } while (cognom1.isEmpty() || !cognom1.matches(regexNom));

                    do {
                        System.out.print("Introdueix el Segon Cognom: ");
                        cognom2 = scanner.nextLine().trim();
                        if (cognom2.isEmpty() || !cognom2.matches(regexNom)) {
                            System.out.println("Error: El segon cognom no pot ser buit ni contenir numeros.");
                        }
                    } while (cognom2.isEmpty() || !cognom2.matches(regexNom));

                    do {
                        System.out.print("Introdueix l'Edat: ");
                        if (scanner.hasNextInt()) {
                            edat = scanner.nextInt();
                            scanner.nextLine();
                            if (edat <= 0)
                                System.out.println("Error: L'edat ha de ser un valor positiu.");
                        } else {
                            System.out.println("Error: L'edat ha de ser un enter.");
                            scanner.nextLine();
                            edat = -1;
                        }
                    } while (edat <= 0);

                    boolean discapacitat = false;
                    do {
                        System.out.print("Introdueix Discapacitat (Si/No): ");
                        discapacitatStr = scanner.nextLine().trim();
                        if (discapacitatStr.matches(regexSiNo)) {
                            String normalitzada = discapacitatStr.toLowerCase();
                            if (normalitzada.startsWith("s")) {
                                discapacitat = true;
                            }
                        } else {
                            System.out.println("Error: Resposta no valida. Introdueix Si o No.");
                            discapacitatStr = "";
                        }
                    } while (discapacitatStr.isEmpty());

                    boolean entradaVIP = false;
                    do {
                        System.out.print("Introdueix Entrada VIP (Si/No): ");
                        entradaVIPStr = scanner.nextLine().trim();
                        if (entradaVIPStr.matches(regexSiNo)) {
                            String normalitzada = entradaVIPStr.toLowerCase();
                            if (normalitzada.startsWith("s")) {
                                entradaVIP = true;
                            }
                        } else {
                            System.out.println("Error: Resposta no valida. Introdueix Si o No.");
                            entradaVIPStr = "";
                        }
                    } while (entradaVIPStr.isEmpty());

                    do {
                        System.out.print("Introdueix la Targeta de Credit (XXXX XXXX XXXX XXXX): ");
                        targetaCredit = scanner.nextLine().trim();
                        if (!targetaCredit.matches(regexTargeta)) {
                            System.out.println("Error: Format incorrecte. Ha de ser XXXX XXXX XXXX XXXX.");
                        }
                    } while (!targetaCredit.matches(regexTargeta));

                    double preuFinal = 0.0;
                    String tipusEntrada = "";

                    if (edat <= Edat_Gratuita || discapacitat) {
                        preuFinal = 0.0;
                        tipusEntrada = "Gratuïta";
                        entradaVIP = false;

                    } else if (edat < 12) {
                        preuFinal = Preu_Base * (1.0 - Descompte_Infantil);
                        tipusEntrada = "Amb descompte";
                        entradaVIP = false;
                    } else {
                        preuFinal = Preu_Base;
                        tipusEntrada = "Normal";

                        if (entradaVIP) {
                            preuFinal = preuFinal + Preu_VIP;
                            tipusEntrada = "VIP";
                        }
                    }

                    String tiquetGenerat = generarTicket(nom, cognom1, cognom2, edat, tipusEntrada, targetaCredit,
                            preuFinal, contadorEntrades);

                    entrades[contadorEntrades] = tiquetGenerat;
                    contadorEntrades = contadorEntrades + 1;
                    System.out.println("\nTicket Generat:");
                    System.out.println(tiquetGenerat);
                    System.out.println("\nEntrada guardada correctament.");

                } else {
                    System.out.println("Error: Aforament complet.");
                }

            } else if (opcio == 2) {
                System.out.println("Iniciant la Fase d'Us de l'Entrada...");
            } else if (opcio == 3) {
                System.out.println("Mostrant Estadistiques del Dia...");
            } else if (opcio == 0) {
                System.out.println("Gracies per utilitzar el sistema. Sortint.");
            } else {
                System.out.println("Opcio no valida. Si us plau, tria una opcio del 0 al 3.");
            }

            if (opcio != 0) {
                System.out.println("----------------------------------------------");
            }

        } while (opcio != 0);

        scanner.close();
    }

    private static String generarTicket(String nom, String cognom1, String cognom2, int edat, String tipus,
            String targeta, double preu, int numId) {
        String resultatFinal = "";

        char lletraInici = nom.charAt(0);
        char lletraCognom1 = cognom1.charAt(0);
        char lletraCognom2 = cognom2.charAt(0);
        String inicials = ("" + lletraInici + lletraCognom1 + lletraCognom2).toUpperCase();

        String idComplet = "DAMW-" + inicials + "-" + String.format("%03d", numId);

        String nomVisible = nom + " " + lletraCognom1 + ". " + lletraCognom2 + ".";

        Date dataActual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        String dataHora = sdf.format(dataActual);

        String ultims4 = targeta.substring(targeta.length() - 4);
        String targetaVisible = "**** **** **** " + ultims4;

        resultatFinal = "--------------------------------------------------\n" +
                "PARC D'ATRACCIONS DAMW\n" +
                "Número d'entrada: " + idComplet + "\n" +
                "Data i hora: " + dataHora + "\n" +
                "Nom del visitant: " + nomVisible + "\n" +
                "Edat: " + edat + " anys\n" +
                "Tipus d'entrada: " + tipus + "\n" +
                "Targeta: " + targetaVisible + "\n" +
                String.format("Preu final: %.2f€", preu) + "\n" +
                "\n" +
                "Nombre d'atraccions: 0\n" +
                "Nombre d'espectacles: 0\n" +
                "Nombre de serveis VIP: 0\n" +
                "--------------------------------------------------";

        return resultatFinal;
    }
}
//Este es un comentario de prueba para ver los cambios

//hola que tal
//Otro comentario de prueba