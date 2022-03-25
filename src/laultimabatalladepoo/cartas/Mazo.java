/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laultimabatalladepoo.cartas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import laultimabatalladepoo.Jugador;
import laultimabatalladepoo.Utilidad;
import static laultimabatalladepoo.cartas.Habilidad.HabilidadEnum.*;
import static laultimabatalladepoo.cartas.Reino.*;
import laultimabatalladepoo.excepciones.GetCartaException;

/**
 *
 * @author frank
 */
public class Mazo { //El mazo sera donde el jugador tiene todas sus cartas y de donde las ira robando para ponerlas en su mano
    private Jugador owner;//Este va a ser el propietario del mazo
    private ListaCarta arraycartas;//Esta es la lista de cartas contenidas en el mazo
    private int[] cartas;

//AQUI EMPIEZA EL CONSTRUCTOR
    public Mazo(Jugador owner){//El constructor del mazo solo nos va a pedir como entrada el jugador que lo tiene
        cartas = new int[14];
        for(int i =0;i<14;i++){
            cartas[i] = 0;
        }
        this.owner = owner;
        arraycartas = new ListaCarta();//Generamos una lista de cartas nueva
        int randomvalue;//Inicializamos el valor aleatorio
        for(int i=0;i<14;i++){//Iteramos 14 veces para conseguir 14 criaturas aleatorias dentro de las que podemos obtener, los casos que hemos especificado.
            randomvalue = Utilidad.random(6);//Por cada iteracion el valor aleatorio cambia, dependiendo de este se va a añadir un tipo de carta u otro
            switch(randomvalue){ //Utilizamos el switch para cubrir los posibles valores del numero aleatorio
                case 1 -> {arraycartas.addCarta(new Criatura(2, 3, "Subdito Rocoso", owner, CAVERNAS));//Esta carta se llama subdito rocoso, tiene 2 de vida y 3 de ataque, su reino son las cavernas, su dueño es owner y no tiene habilidad
                cartas[1]++;
                }
                case 2 -> {arraycartas.addCarta(new Criatura(2, 3, "Subdito Industrial", owner, CIUDAD));//Esta carta se llama subdito industrial, tiene 2 de vida y 3 de ataque, su reino es la ciudad, su dueño es owner y no tiene habilidad
                cartas[2]++;
                }
                case 3 -> {arraycartas.addCarta(new Criatura(2, 3, "Subdito Salvaje", owner, BOSQUE));//Esta carta se llama subdito salvaje, tiene 2 de vida y 3 de ataque, su reino es el bosque, su dueño es owner y no tiene habilidad
                cartas[3]++;
                }
                case 4 -> {arraycartas.addCarta(new Criatura(5, 2, "Golem Rocoso", owner, CAVERNAS));//Esta carta se llama golem rocoso, tiene 5 de vida y 2 de ataque, su reino son las cavernas, su dueño es owner y no tiene habilidad
                cartas[4]++;
                }
                case 5 -> {arraycartas.addCarta(new Criatura(5, 2, "Golem industrial", owner, CIUDAD));//Esta carta se llama golem industrial, tiene 5 de vida y 2 de ataque, su reino es la ciudad, su dueño es owner y no tiene habilidad
                cartas[5]++;
                }
                case 6 -> {arraycartas.addCarta(new Criatura(5, 2, "Golem Salvaje", owner, BOSQUE));//Esta carta se llama golem salvaje, tiene 5 de vida y 2 de ataque, su reino es el bosque, su dueño es owner y no tiene habilidad
                cartas[6]++;
                }
            }
        }
        
        for(int i=0;i<9;i++){//Hacemos lo mismo pero esta vez solo 9 veces para las edificaciones
            randomvalue = Utilidad.random(4);
            switch (randomvalue){
                case 1 -> {arraycartas.addCarta(new Edificacion(8, 2, "Castillo", BOOSTHEALTHCAMPO, owner, true));//Esta edificacion se llama castillo, tiene 8 de vida y 2 de ataque y su habilidad es BOOSTHEALTHCAMPO, si puede defender
                cartas[7]++;
                }
                case 2 -> {arraycartas.addCarta(new Edificacion(8, 2, "Fuente de Vida", CURAR, owner, false));//Esta edificacion se llama fuente de vida, tiene 8 de vida y 2 de ataque y su habilidad es CURAR, no puede defender
                cartas[8]++;
                }
                case 3 -> {arraycartas.addCarta(new Edificacion(6, 5, "Barricada", BOOSTDAMAGECAMPO, owner, true));//Esta edificacion se llama barricada, tiene 6 de vida y 5 de ataque y su habilidad es BOOSTDAMAGECAMPO, puede defender
                cartas[9]++;
                }
                case 4 -> {arraycartas.addCarta(new Edificacion(8, 6, "Grada de fans", BOOSTDAMAGECAMPO, owner, false));//Esta edificacion se llama grada de fans, tiene 8 de vida y 6 de ataque y su habilidad es BOOSTDAMAGECAMPO, no puede defender
                cartas[10]++;
                }
            }
        }
        for(int i=0;i<=6;i++){//Ahora para los hechizos
            randomvalue = Utilidad.random(4);
            switch (randomvalue){
                case 1 -> {arraycartas.addCarta(new Encantamiento(2, 1, "Proteccion divina", owner));//Este es un encantamiento que sube en 2 el daño y en 1 la vida.
                cartas[11]++;
                }
                case 2 -> {arraycartas.addCarta(new Encantamiento(1, 2, "Pacto demoniaco", owner));//Este es un encantamiento que sube en 1 el daño y en 2 la vida.
                cartas[12]++;
                }
                case 3 -> {arraycartas.addCarta(new Conjuro(2, "Cohete", owner));//Este es un conjuro que inflinge 2 de daño
                cartas[13]++;
                }
                case 4 -> {arraycartas.addCarta(new Conjuro(3, "Bola de fuego", owner));//Este es un conjuro que inflinge 3 de daño
                cartas[0]++;
                }
            }
        }
    }
//AQUI TERMINA EL CONSTRUCTOR
    
//EMPIEZA EL SETTER GETTER
    public ListaCarta getArrayCartas(){//Este metodo sera para recuperar la lista de cartas asociada al mazo
        return arraycartas;
    }

    public void setArrayCartas(ListaCarta arraycartas){ //Este método serviría por si quisiésemos leer datos de un fichero externo.
        this.arraycartas = arraycartas;
    }
    
    /**
     *Este método nos servirá para recuperar el propietario de este mazo
    */
    
    public Jugador getOwner(){ //Si alguna vez queremos conocer el propietario de un mazo tendríamos que tener este método
        return owner;
    }
    
    
//TERMINA EL SETTER GETTER

//AQUI EMPIEZAN LOS METODOS DE CLASE
        /**
     *Este método nos servirá para ver qué carta se repite mas en un mazo
    */
    
    public void cualserepitemas() throws IOException, GetCartaException {
        int repite = 0;
        int posicion = 0;
        for (int j = 0; j < cartas.length; j++) {
            if (cartas[j] > repite) {
                repite = cartas[j];
                posicion = j;
            }
            String ruta = "C:/Users/frank/Desktop/NetBeansProjects/laultimabatalladepoo";
        File fichero = new File(ruta, "cartarepetida"+String.valueOf(owner.getNumJug())+".txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(fichero));
        bw.write("La carta que mas se repite es"+arraycartas.getCarta(posicion).toString());
        bw.close();
        }
        switch (posicion) {
            case 1 -> {
                System.out.println("La carta que mas se repite es el Subdito Rocoso");
            }
            case 2 -> {
                System.out.println("La carta que mas se repite es el Subdito Industrial");
            }
            case 3 -> {
                System.out.println("La carta que mas se repite es el Subdito Salvaje");
            }
            case 4 -> {
                System.out.println("La carta que mas se repite es el Golem Rocoso");
            }
            case 5 -> {
                System.out.println("La carta que mas se repite es el Golem Industrial");
            }
            case 6 -> {
                System.out.println("La carta que mas se repite es el Golem Salvaje");
            }
            case 7 -> {
                System.out.println("La carta que mas se repite es el Castillo");
            }
            case 8 -> {
                System.out.println("La carta que mas se repite es la Fuente de Vida");
            }
            case 9 -> {
                System.out.println("La carta que mas se repite es la Barricada");
            }
            case 10 -> {
                System.out.println("La carta que mas se repite es la Grada de fans");
            }
            case 11 -> {
                System.out.println("La carta que mas se repite es la Proteccion divina");
            }
            case 12 -> {
                System.out.println("La carta que mas se repite es el Pacto demoniaco");
            }
            case 13 -> {
                System.out.println("La carta que mas se repite es el cohete");
            }
            case 0 -> {
                System.out.println("La carta que mas se repite es la bola de fuego");
            }
        }

        System.out.println("hola");
    }

    /**
     *Este método nos sirve para ordenar las criaturas de un mazo dependiendo del daño de ataque que tengan
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    
    public void ordenarcriaturas() throws GetCartaException, IOException {
        int i, j;
        Carta aux;
        for (i = 0; i < arraycartas.getLength() - 1; i++) {
            for (j = 0; j < arraycartas.getLength() - i - 1; j++) {
                if (arraycartas.getCarta(j + 1) instanceof Criatura && arraycartas.getCarta(j) instanceof Criatura) {
                    if (arraycartas.getCarta(j + 1).getAtk() < arraycartas.getCarta(j).getAtk()) {
                        aux = arraycartas.getCarta(j + 1);
                        arraycartas.getListaDeCartas().set(j + 1, arraycartas.getCarta(j));
                        arraycartas.getListaDeCartas().set(j, aux);
                    }
                }

            }
        }
        String ruta = "C:/Users/frank/Desktop/NetBeansProjects/laultimabatalladepoo";
        File fichero = new File(ruta, "ResultadoOrdenacion"+String.valueOf(owner.getNumJug())+".txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(fichero));
        bw.write("Las criaturas han quedado en este orden");
        bw.newLine();
        for (int l = 0; l < arraycartas.getLength(); l++) {
            if(arraycartas.getCarta(l) instanceof Criatura){
                bw.write(arraycartas.getCarta(l).getNombre());
                bw.newLine();
            }
            
        }
        bw.close();
    }

    public void ficheromazo() throws IOException, GetCartaException {
        String ruta = "C:/Users/frank/Desktop/NetBeansProjects/laultimabatalladepoo";
        File fichero = new File(ruta, "FicheroMazo"+String.valueOf(owner.getNumJug())+".txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(fichero));
        bw.write("Cartas del mazo: ");
        bw.newLine();
        for (int i = 0; i < arraycartas.getLength(); i++) {
            bw.write(arraycartas.getCarta(i).getNombre());
        bw.newLine();
        }
        bw.close();
    }
    
    public String toString(){
        return String.valueOf(this);
    }
    
    public void equal(Mazo mazo){
        if (this==mazo){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }
    }
//AQUI TERMINAN LOS METODOS DE CLASE
}