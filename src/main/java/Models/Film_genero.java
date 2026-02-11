/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author bigpa
 */
public class Film_genero {
    
    private int film_id;
    private int genero_id;

    public Film_genero(int film_id, int genero_id) {
        this.film_id = film_id;
        this.genero_id = genero_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(int genero_id) {
        this.genero_id = genero_id;
    }

    
    
    @Override
    public String toString() {
        return "Film_genero{" + "film_id=" + film_id + ", genero_id=" + genero_id + '}';
    }
    
    
    
}
