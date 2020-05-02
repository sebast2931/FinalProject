package co.edu.ff.orders.products.domain;

import org.springframework.context.annotation.Bean;

public enum Estado {
    BORRADO,
    PUBLICADO;

/*
    @Bean
    public static String EstadoProduct(Estado estado){
        String message = null;

        switch (estado){
            case PUBLICADO:
                message = "PUBLICADO";
                return message;
            case BORRADO:
                message = "BORRADO";
                return message;
            default:
                throw new UnsupportedOperationException();
        }
    }*/
}