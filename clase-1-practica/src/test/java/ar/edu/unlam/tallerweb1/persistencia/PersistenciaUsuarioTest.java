package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

public class PersistenciaUsuarioTest extends SpringTest {

    // EJEMPLO DE TEST DE PERSISTENCIA
    @Test
    @Transactional @Rollback()
    public void alGuardarUnUsuarioDeberiaEstarEnLaBase(){
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setEmail("john@doe.com");
        usuarioPrueba.setPassword("lele");
        usuarioPrueba.setRol("LÍDER");
        Session session = getSession();
        session.save(usuarioPrueba); // Guarda el ID
        Usuario usuarioBuscado = session.get(Usuario.class, usuarioPrueba.getId());
        assertThat (usuarioBuscado).isNotNull();
    }
}
