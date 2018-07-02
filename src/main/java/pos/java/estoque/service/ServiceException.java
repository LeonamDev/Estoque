/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.estoque.service;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceException(String msg) {
        super(msg);
    }
}
