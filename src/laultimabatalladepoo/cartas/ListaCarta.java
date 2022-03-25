package laultimabatalladepoo.cartas;

import java.util.ArrayList;
import laultimabatalladepoo.excepciones.GetCartaException;
import laultimabatalladepoo.excepciones.GetPositionException;

/**
 *
 * @author frank
 */

public class ListaCarta {//Esta clase nos va a ayudar a crear listas de cartas y a poder hacer diversas cosas con ellas, nos ayudara a mover cartas de sitio, recuperar cartas, etc
    private ArrayList<Carta> listadecartas; //Declaramos un ArrayList que contiene objetos Carta

    public ListaCarta() {    //Constructor: crea una lista de cartas vacía
        listadecartas = new ArrayList<>(); //Creamos el objeto de tipo ArrayList
    } //Constructor
    
    /**
     *Este método lo vamos a utilizar para poder reordenar las cartas, pero no vamos a utilizarlo para ninguna otra cosa
     * @return 
    */
    public ArrayList getListaDeCartas(){
        return listadecartas;
    }
    
    
    /**
     *Este método lo vamos a utilizar para añadir cartas a esta lista de cartas 
     * @param carta
    */
    public void addCarta(Carta carta) {//Este metodo sirve para añadir la carta dada a la lista que invoca el metodo
        listadecartas.add(carta);
    } //Add Carta a la lista, seria como el setter de la clase lista de cartas
    
    
    /**
     *Este método nos servirá para recuperar la carta de una posición específica
     * @param posicion
     * @return
     * @throws laultimabatalladepoo.excepciones.GetCartaException
    */
    public Carta getCarta (int posicion) throws GetCartaException{ //Este metodo sirve para coger la carta que esta en una posicion de la lista de cartas (debemos introducir la posicion de la que queremos cogerla)
        if (posicion > listadecartas.size()) {throw new GetCartaException();}//Si la posicion estuviese fuera de la lista lanzariamos la excepcion GetCartaException
        else{//Si todo va bien entonces seguimos
            if (posicion >= 0 && posicion < listadecartas.size() ) {//Si el indice es mayor que 0 entonces devolvemos la carta en esa posicion

                return listadecartas.get(posicion); }

            else {
                System.out.println("ERROR EN LISTA CARTA GETCARTA");
                return null;
            }//De otro modo retornamos null (nada)
        }
    }
    
    
    
    /**
     *Este método nos sirve para recuperar la posición de una carta específica
     * @param carta
     * @return 
     * @throws laultimabatalladepoo.excepciones.GetPositionException 
    */
    public int getPosition(Carta carta) throws GetPositionException{//Dada una carta este metodo busca la posicion en la que esta esa carta dentro de la lista que invoca el metodo
        int position=-1;//Iniciamos el valor con -1 para que si no podemos encontrar la carta se quede asi y lancemos una excepcion
        for(int i=0;i<listadecartas.size();i++){//Hacemos un bucle que recorre toda la lista de cartas
            if (listadecartas.get(i)==carta) {position = i;}//Si la carta en esa posicion es la carta que buscamos redefinimos posicion como la i en esa iteracion
        }
        if (position<0){//Si no hubiesemos encontrado la carta entonces
            throw new GetPositionException();//Lanzamos la excepcion GetPositionException que dira que la carta no se encuentra en la lista
        }
        return position;
    }
    
    
    /**
     *Este método nos sirve para saber la longitud de esta lista de cartas
     * @return 
    */
    
    public int getLength () { return listadecartas.size(); }//Metodo para ver la longitud de la lista
    
    
    
    //Utilizo bastante la combinacion removeCarta(getPosition(Carta carta)) que va buscar el indice de la carta carta dentro de la lista y luego va a eliminar la carta correspondiente a ese indice
    /**
     *Este método sirve para quitar la carta de una posición específica de esta lista de cartas
     * @param posicion
    */
    public void removeCarta (int posicion) {  //Método para eliminar una carta de la lista
        if (posicion >= 0 && posicion < listadecartas.size() ) {//Comprobamos que el indice sea correcto

            listadecartas.remove(posicion); }//Eliminamos la carta que se halla en este indice

        else {}
}
}
