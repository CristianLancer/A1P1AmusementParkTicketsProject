package inscaparrella.park1;

/*
* Authors:
* Cristian Camilo Cardenas Ramos
* Miguel Angel Sanchez Azuaje

*/

import java.util.Scanner;

public class AmusementParkTicketsMain {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        int opcio = -1;


        double Preu_Base = 12.0 ;
        double Preu_VIP = 4.0 ;
        double Preu_Discapacitat = 0.50;
       

        do {
            System.out.println("---- PARC D'ATRACCIONS DAMW ----");
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
                    } else {
                        System.out.println("Error: L'edat ha de ser un enter.");
                        scanner.nextLine(); 
                        edat = -1;
                    }
                    if (edat <= 0) {
                         System.out.println("Error: L'edat ha de ser un valor positiu.");
                    }
                } while (edat <= 0);

                boolean discapacitat = false;
                do {
                    System.out.print("Introdueix Discapacitat (Si/No): ");
                    discapacitatStr = scanner.nextLine().trim();
                    if (discapacitatStr.matches(regexSiNo)) {
                        String normalitzada = discapacitatStr.toLowerCase().replaceAll("[íìîï]", "i").replaceAll("[òóôö]", "o");
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
                        String normalitzada = entradaVIPStr.toLowerCase().replaceAll("[íìîï]", "i").replaceAll("[òóôö]", "o");
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

                double preuFinal = Preu_Base;

                if ( edat < 3 ) {
                    preuFinal = 0.0;
                }  
                else if ( edat >=3 && edat <=12 ) {
                    preuFinal = Preu_Base * 0.50;
                } 
                else if ( edat >=65 ) {
                    preuFinal = Preu_Base * 0.65;

                }
                
                if (discapacitat) {
                    preuFinal = preuFinal * Preu_Discapacitat;
                }
                if (entradaVIP) {
                    preuFinal = preuFinal + Preu_VIP;
                }

                

                
                System.out.println("[Pendent] Calcul del preu...");
                System.out.println("[Pendent] Generacio i emmagatzemament...");

                System.out.println("Dades del visitant recollides correctament:");
                System.out.println("Nom: " + nom + " " + cognom1 + " " + cognom2);
                System.out.println("Edat: " + edat);
                System.out.println("Discapacitat: " + (discapacitat ? "SI" : "NO"));
                System.out.println("VIP: " + (entradaVIP ? "SI" : "NO"));
                System.out.println("Targeta: " + targetaCredit);
                System.out.printf("Preu Final: %.2f euros%n", preuFinal);

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

        if (opcio == 2) {
          
           
        }
        
        scanner.close();
    }
} 